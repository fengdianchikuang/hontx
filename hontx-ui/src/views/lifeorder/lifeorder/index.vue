<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="被保人证件号" prop="insurederidno">
        <el-input
          v-model="queryParams.insurederidno"
          placeholder="请输入被保人证件号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被保人姓名" prop="insuredername">
        <el-input
          v-model="queryParams.insuredername"
          placeholder="请输入被保人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="代理人" prop="agentid">
        <el-input
          v-model="queryParams.agent"
          placeholder="请输入代理人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>



      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" border :header-cell-style="{background:'#f0f9eb',color:'#80878f'}" :data="lifeorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="" align="center" prop="id" />-->
      <el-table-column label="订单号" width="170" align="center" prop="orderId" >
        <template slot-scope="scope">
          <div @click="updateOrder(scope.row)">{{scope.row.orderId}}</div>
        </template>
      </el-table-column>
      <el-table-column label="代理人" align="center" prop="agentName" />
<!--      代理人电话-->
      <el-table-column label="代理人电话" width="110" align="center" prop="agentTel" />
      <!--投保人-->
      <el-table-column label="投保人" width="80" align="center" prop="holderName" />

      <!--      投保人证件号-->
      <el-table-column label="投保人证件号" width="170" align="center" prop="holderIdNo" />



<!--      投保人电话-->
      <el-table-column label="投保人电话" width="110" align="center" prop="holderTel" />
      <el-table-column label="支付时间" align="center" prop="paytime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="产品名" width="190" align="center" prop="productName" />

      <el-table-column label="被保人" align="center" prop="insurederName" />

      <el-table-column label="被保人证件号" width="170" align="center" prop="insurederIdNo" />

<!--      <el-table-column label="产品名" align="center" prop="productid" />-->

      <el-table-column label="订单状态" align="center" prop="status" >
        <template slot-scope="scope">
<!--          <div v-if="scope.row.status === '1'">-->
<!--            <el-tag-->
<!--              type="info"-->
<!--              effect="dark">待支付</el-tag>-->
<!--          </div>-->
          <div v-if="scope.row.status === 'unpaid'">
            <el-tag
              type="info"
              effect="dark">待支付</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'paid'">
            <el-tag
              type="success"
              effect="dark">已支付</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'invalid'">
            <el-tag
              type="info"
              effect="dark">无效</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'canceled'">
            <el-tag
              type="info"
              effect="dark">已撤单</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'accepted_insure'">
            <el-tag
              type="success"
              effect="dark">已承保</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'effected'">
            <el-tag
              type="success"
              effect="dark">已生效</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'terminated'">
            <el-tag
              type="warning"
              effect="dark">已终止</el-tag>
          </div>
          <div v-else-if="scope.row.status === 'surrendered'">
            <el-tag
              type="danger"
              effect="dark">已退保</el-tag>
          </div>
          <div v-else>
            <el-tag
              type="danger"
              effect="dark">未定义</el-tag>
          </div>

<!--          <div v-else-if="scope.row.status === '2'">-->
<!--            <el-tag-->
<!--              type="success"-->
<!--              effect="dark">生效</el-tag>-->
<!--          </div>-->
<!--          <div v-else="scope.row.status === '4'">-->
<!--            <el-tag-->
<!--              type="danger"-->
<!--              effect="dark">核保失败</el-tag>-->
<!--          </div>-->
        </template>
      </el-table-column>

<!--      <el-table-column label="外部参数" align="center" prop="extparams" />-->
<!--      <el-table-column label="担保期限" align="center" prop="guaranteeterm" />-->
<!--      <el-table-column label="担保类型" align="center" prop="guaranteetype" />-->

<!--      <el-table-column label="被保人证件类型" align="center" prop="insurederidtype" />-->
      <el-table-column label="缴费方式" align="center" prop="payType" >
        <template slot-scope="scope">
          <div v-if="scope.row.payType === '1'">
            <el-tag
              type=""
              effect="dark">年缴</el-tag>
          </div>
          <div v-else>
            <el-tag
              type="success"
              effect="dark">月娇</el-tag>
          </div>

        </template>
      </el-table-column>

      <el-table-column label="缴费期限" align="center" prop="payPerm" />

      <el-table-column label="保费" align="center" prop="premium" />

      <el-table-column label="保险开始时间" align="center" prop="guaranteestarttime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.guaranteestarttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="保险结束时间" align="center" prop="guaranteeendtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.guaranteeendtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="保单号" width="240" align="center" prop="policyNo" />

      <el-table-column label="保单状态" align="center" prop="policyStatus" />

      <el-table-column label="总保费" align="center" prop="premium" />

<!--      <el-table-column label="付款" align="center" prop="payment" />-->
<!--      -->

<!--      -->
<!--      -->
<!--      -->
<!--      <el-table-column label="上层产品" align="center" prop="upperproductid" />-->


      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!---->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改寿险订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单号" prop="orderid">
          <el-input v-model="form.orderid" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="保单号" prop="holderidno">
          <el-input v-model="form.holderidno" placeholder="请输入保单号" />
        </el-form-item>
        <el-form-item label="代理人" prop="agentid">
          <el-input v-model="form.agentid" placeholder="请输入代理人" />
        </el-form-item>
        <el-form-item label="产品名" prop="productid">
          <el-input v-model="form.productid" placeholder="请输入产品名" />
        </el-form-item>
        <el-form-item label="订单状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="外部参数" prop="extparams">
          <el-input v-model="form.extparams" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="担保期限" prop="guaranteeterm">
          <el-input v-model="form.guaranteeterm" placeholder="请输入担保期限" />
        </el-form-item>
        <el-form-item label="担保类型" prop="guaranteetype">
          <el-select v-model="form.guaranteetype" placeholder="请选择担保类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="支付时间" prop="paytime">
          <el-date-picker clearable size="small"
            v-model="form.paytime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择支付时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="被保人证件类型" prop="insurederidtype">
          <el-select v-model="form.insurederidtype" placeholder="请选择被保人证件类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="被保人证件号" prop="insurederidno">
          <el-input v-model="form.insurederidno" placeholder="请输入被保人证件号" />
        </el-form-item>
        <el-form-item label="被保人姓名" prop="insuredername">
          <el-input v-model="form.insuredername" placeholder="请输入被保人姓名" />
        </el-form-item>
        <el-form-item label="缴费期限" prop="payperm">
          <el-input v-model="form.payperm" placeholder="请输入缴费期限" />
        </el-form-item>
        <el-form-item label="支付类型" prop="paytype">
          <el-select v-model="form.paytype" placeholder="请选择支付类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="付款" prop="payment">
          <el-input v-model="form.payment" placeholder="请输入付款" />
        </el-form-item>
        <el-form-item label="保费" prop="premium">
          <el-input v-model="form.premium" placeholder="请输入保费" />
        </el-form-item>
        <el-form-item label="产品名" prop="productname">
          <el-input v-model="form.productname" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="保单号" prop="policyno">
          <el-input v-model="form.policyno" placeholder="请输入保单号" />
        </el-form-item>
        <el-form-item label="保单状态">
          <el-radio-group v-model="form.policystatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="总保费" prop="totalinsured">
          <el-input v-model="form.totalinsured" placeholder="请输入总保费" />
        </el-form-item>
        <el-form-item label="上层产品" prop="upperproductid">
          <el-input v-model="form.upperproductid" placeholder="请输入上层产品" />
        </el-form-item>
        <el-form-item label="保险开始时间" prop="guaranteestarttime">
          <el-date-picker clearable size="small"
            v-model="form.guaranteestarttime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择保险开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保险结束时间" prop="guaranteeendtime">
          <el-date-picker clearable size="small"
            v-model="form.guaranteeendtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择保险结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { updateOrderById,listLifeorder, getLifeorder, delLifeorder, addLifeorder, updateLifeorder, exportLifeorder } from "@/api/lifeorder/lifeorder";

export default {
  name: "Lifeorder",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 寿险订单表格数据
      lifeorderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        agent: null,
        orderid: null,
        holderidno: null,
        agentid: null,
        productid: null,
        status: null,
        extparams: null,
        guaranteeterm: null,
        guaranteetype: null,
        paytime: null,
        insurederidtype: null,
        insurederidno: null,
        insuredername: null,
        payperm: null,
        paytype: null,
        payment: null,
        premium: null,
        productname: null,
        policyno: null,
        policystatus: null,
        totalinsured: null,
        upperproductid: null,
        guaranteestarttime: null,
        guaranteeendtime: null,
        createtime: null,
        updatetime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderid: [
          { required: true, message: "订单号不能为空", trigger: "blur" }
        ],
        holderidno: [
          { required: true, message: "保单号不能为空", trigger: "blur" }
        ],
        agentid: [
          { required: true, message: "代理人不能为空", trigger: "blur" }
        ],
        productid: [
          { required: true, message: "产品名不能为空", trigger: "blur" }
        ],
        updatetime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //更新订单信息
    updateOrder(row){
        updateOrderById(row).then(res => {
            this.getList();
      })
    },
    /** 查询寿险订单列表 */
    getList() {
      this.loading = true;
      listLifeorder(this.queryParams).then(response => {
        this.lifeorderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        orderid: null,
        holderidno: null,
        agentid: null,
        productid: null,
        status: "0",
        extparams: null,
        guaranteeterm: null,
        guaranteetype: null,
        paytime: null,
        insurederidtype: null,
        insurederidno: null,
        insuredername: null,
        payperm: null,
        paytype: null,
        payment: null,
        premium: null,
        productname: null,
        policyno: null,
        policystatus: "0",
        totalinsured: null,
        upperproductid: null,
        guaranteestarttime: null,
        guaranteeendtime: null,
        createtime: null,
        updatetime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加寿险订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLifeorder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改寿险订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLifeorder(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLifeorder(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除寿险订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delLifeorder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有寿险订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportLifeorder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
