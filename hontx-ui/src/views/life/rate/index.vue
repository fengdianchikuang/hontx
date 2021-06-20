<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品名称" prop="productid">
        <el-select v-model="queryParams.productid" placeholder="请选择产品名称">
          <el-option v-for="item in productList"
                     :key="item.productname"
                     :label="item.productname"
                     :value="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="缴费类型" prop="paytype">
        <el-select v-model="queryParams.paytype" placeholder="请选择缴费类型" clearable size="small">
          <el-option label="未知" value="0" />
          <el-option label="日缴" value="1" />
          <el-option label="月缴" value="2" />
          <el-option label="年缴" value="3" />
          <el-option label="岁缴" value="4" />
          <el-option label="趸交" value="5" />
        </el-select>
      </el-form-item>
      <el-form-item label="缴费期限" prop="payment">
        <el-input
          v-model="queryParams.payment"
          placeholder="请输入缴费期限"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="返佣期" prop="backterm">
        <el-input
          v-model="queryParams.backterm"
          placeholder="请输入返佣期"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['life:rate:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['life:rate:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['life:rate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['life:rate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rateList" border :header-cell-style="{background:'#f0f9eb',color:'#80878f'}" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="产品名称" width="160" align="center" prop="productid" >
        <tempalte slot-scope="scope">
          {{ getProductName(scope.row.productid) }}
        </tempalte>
      </el-table-column>
      <el-table-column label="缴费类型" align="center" prop="paytype" >
        <template slot-scope="scope">
          {{ scope.row.paytype | filterPayType }}
        </template>
      </el-table-column>
      <el-table-column label="缴费期限" align="center" prop="payment" />
      <el-table-column label="返佣期" align="center" prop="backterm" />
      <el-table-column label="总费率" align="center" prop="totalrate" />
      <el-table-column label="代理人费率" align="center" prop="agentrate" />
      <el-table-column label="A层级费率" align="center" prop="arate" />
      <el-table-column label="B层级费率" align="center" prop="brate" />
      <el-table-column label="创建时间" align="center" prop="createtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['life:rate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['life:rate:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品费率表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productid">
          <el-select v-model="form.productid" placeholder="请选择产品名称">
            <el-option v-for="item in productList"
                       :key="item.productno"
                       :label="item.productname"
                       :value="item.productno"/>
          </el-select>
        </el-form-item>
        <el-form-item label="缴费类型" prop="paytype">
          <el-select v-model="form.paytype" placeholder="请选择缴费类型">
            <el-option label="未知" value="0" />
            <el-option label="日缴" value="1" />
            <el-option label="月缴" value="2" />
            <el-option label="年缴" value="3" />
            <el-option label="岁缴" value="4" />
            <el-option label="趸交" value="5" />
          </el-select>
        </el-form-item>
        <el-form-item label="缴费期限" prop="payment">
          <el-input
            v-model="form.payment"
            placeholder="请输入缴费期限"
            clearable
            size="small"
          />
        </el-form-item>
        <el-form-item label="返佣期" prop="backterm">
          <el-input v-model="form.backterm" placeholder="请输入返佣期" />
        </el-form-item>
        <el-form-item label="总费率" prop="totalrate">
          <el-input v-model="form.totalrate" placeholder="请输入总费率" />
        </el-form-item>
        <el-form-item label="代理人费率" prop="agentrate">
          <el-input v-model="form.agentrate" placeholder="请输入代理人费率" />
        </el-form-item>
        <el-form-item label="A层级费率" prop="arate">
          <el-input v-model="form.arate" placeholder="请输入A层级费率" />
        </el-form-item>
        <el-form-item label="B层级费率" prop="brate">
          <el-input v-model="form.brate" placeholder="请输入B层级费率" />
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
import { listRate, getRate, delRate, addRate, updateRate, exportRate } from "@/api/life/rate";
import { getAllList } from '@/api/life/product'

export default {
  name: "Rate",
  components: {
  },
  filters:{
      filterPayType(val){
          let payTypeMap = {
              "0":"未知",
              "1":"日缴",
              "2":"月缴",
              "3":"年缴",
              "4":"岁缴",
              "5":"趸交",
          }
      }
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
      // 产品费率表表格数据
      rateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productid: null,
        paytype: null,
        payment: null,
        backterm: null,
      },
      prodcutList: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productid: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        paytype: [
          { required: true, message: "缴费类型不能为空", trigger: "change" }
        ],
        payment: [
          { required: true, message: "缴费期限不能为空", trigger: "change" }
        ],
        backterm: [
          { required: true, message: "返佣期不能为空", trigger: "blur" }
        ],
        totalrate: [
          { required: true, message: "总费率不能为空", trigger: "blur" }
        ],
        agentrate: [
          { required: true, message: "代理人费率不能为空", trigger: "blur" }
        ],
        arate: [
          { required: true, message: "A层级费率不能为空", trigger: "blur" }
        ],
        brate: [
          { required: true, message: "B层级费率不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.selectProduct();
    this.getList();

  },
  methods: {
    getProductName(productId){
        for(let i = 0; i < this.productList.length; i ++){
            if(this.productList[i].productno === productId){
                return this.productList[i].productname;
            }
        }
    },
    //获取所有的保险产品
    selectProduct(){
      getAllList().then(res => {
          debugger
          this.productList = res.data;
      })
    },
    /** 查询产品费率表列表 */
    getList() {

      this.loading = true;
      listRate(this.queryParams).then(response => {
          debugger
        this.rateList = response.rows;
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
        productid: null,
        paytype: null,
        payment: null,
        backterm: null,
        totalrate: null,
        agentrate: null,
        arate: null,
        brate: null,
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
      this.title = "添加产品费率表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRate(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改产品费率表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRate(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRate(this.form).then(response => {
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
      this.$confirm('是否确认删除产品费率表编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRate(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有产品费率表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRate(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
