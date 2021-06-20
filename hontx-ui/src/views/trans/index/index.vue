<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入提现人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择受理状态" clearable size="small">
          <el-option v-for="item in statusOption"
                     :key="item.key"
                     :value="item.value"
                     :label="item.key"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['trans:index:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['trans:index:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleQuery"-->
<!--          v-hasPermi="['trans:index:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['trans:index:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="indexList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" width="60" prop="id" />
      <el-table-column label="提现人姓名" align="center" width="70" prop="name" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="身份证" align="center" width="160"prop="idNo" />
      <el-table-column label="提现金额" align="center" width="65" prop="amount" />
      <el-table-column label="用户余额" align="center" width="65" prop="balance" />
      <el-table-column label="银行卡号" align="center" width="170" prop="bankNo" />
      <el-table-column label="银行名" align="center" prop="bankName" />
      <el-table-column label="开户支行" align="center" prop="branch" />
      <el-table-column label="所属机构" align="center" prop="orgId" >
        <template slot-scope="scope">
          {{ scope.row.orgId | filterOrgId }}
        </template>
      </el-table-column>
      <el-table-column label="地区" align="center" prop="region" >
        <template slot-scope="scope">
          {{ scope.row.region | filterRegion }}
        </template>
      </el-table-column>

      <el-table-column label="申请时间" align="center" prop="tCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <div v-if="scope.row.status === '00'">
            <el-button
              type="danger"
              @click="handleWithdrawal(scope.row)"
              v-hasPermi="['trans:index:remove']"
              plain
            >未受理</el-button>
          </div>
          <div v-if="scope.row.status === '01'">
            <el-button
              type="success"
              plain
            >已受理</el-button>
          </div>
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

    <!-- 添加或修改提交列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="申请时间" prop="tCreateTime">
          <el-date-picker clearable size="small"
            v-model="form.tCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择申请时间">
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
import { listIndex, getIndex, delIndex, addIndex, updateIndex, exportIndex } from "@/api/trans/trans";
import {listRegion} from "@/api/regoin/regoin";

let than;
export default {
  name: "Index",
  components: {
  },

  filters:{
      filterOrgId(val){
          let orgMap ={
              "2":"长沙泰昌",
              "9":"浏阳人寿"
          }
          return orgMap[val];
      },
      filterRegion(val){
          let regionMap = {
              "430100":"长沙",
              "430200":"株洲",
              "430300":"湘潭",
              "430400":"衡阳",
              "430500":"邵阳",
              "430600":"岳阳",
              "430700":"常德",
              "430800":"张家界",
              "430900":"益阳",
              "431000":"郴州",
              "431100":"永州",
              "431200":"怀化",
              "431300":"娄底",
              "433100":"湘西"
          }
          return regionMap[val];
          // than.regoinOption.forEach(item => {
          //     if(val == item.code){
          //         return item.name;
          //     }
          // })
      }
  },
  data() {
    return {
      statusOption:[
          {key:'已受理',value:'01'},
          {key:'未受理',value:'00'}
      ],


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
      // 提交列表表格数据
      indexList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.queryRegoin();

    this.getList();
  },
  methods: {

    queryRegoin(){
        const data = {
            type: 2
            }
        listRegion(data).then(res => {
            debugger
          this.regoinOption = res.data;
        })
    },
    /** 查询提交列表列表 */
    getList() {
      this.loading = true;
      listIndex(this.queryParams).then(response => {
        this.indexList = response.rows;
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
        tId: null,
        transNo: null,
        transType: null,
        couponNo: null,
        amount: null,
        tciAmount: null,
        vciAmount: null,
        userId: null,
        userAmount: null,
        vehicleId: null,
        queryId: null,
        incomeId: null,
        status: "0",
        remark: null,
        tCreateTime: null,
        tUpdateTime: null
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
      this.ids = selection.map(item => item.tId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加提交列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tId = row.tId || this.ids
      getIndex(tId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改提交列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tId != null) {
            updateIndex(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIndex(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 提现按钮操作 */
    handleWithdrawal(row) {
      this.$confirm('是否确认受理"' + row.name + '"的申请?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
            // this.form = {}
            // this.form.tId = row.id;
            // this.form.status = '01';
          return updateIndex(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("操作成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有提交列表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportIndex(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
