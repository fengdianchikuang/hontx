<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车主" prop="owner">
        <el-input
          v-model="queryParams.owner"
          placeholder="请输入车主"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证" prop="ownerCertNo">
        <el-input
          v-model="queryParams.ownerCertNo"
          placeholder="请输入身份证"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商业险开始日期" prop="insrncBgnTm">
        <el-date-picker clearable size="small"
          v-model="queryParams.insrncBgnTm"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择商业险开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="商业险结束日期" prop="insrncEndTm">
        <el-date-picker clearable size="small"
          v-model="queryParams.insrncEndTm"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择商业险结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="代理人" prop="agentId">
        <el-input
          v-model="queryParams.agentId"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['policy:freeInquiry:add']"
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
          v-hasPermi="['policy:freeInquiry:edit']"
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
          v-hasPermi="['policy:freeInquiry:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['policy:freeInquiry:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="freeInquiryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="qId" />
      <el-table-column label="车牌号" align="center" prop="vehicleNo" />
      <el-table-column label="车架号" align="center" prop="rackNo" />
      <el-table-column label="所属性质" align="center" prop="belongType" />
      <el-table-column label="使用性质" align="center" prop="useType" />
      <el-table-column label="座位数" align="center" prop="setNum" />
      <el-table-column label="车主" align="center" prop="owner" />
      <el-table-column label="身份证" align="center" prop="ownerCertNo" />
      <el-table-column label="商业险开始日期" align="center" prop="insrncBgnTm" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insrncBgnTm, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="商业险结束日期" align="center" prop="insrncEndTm" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insrncEndTm, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="投保人" align="center" prop="applicantName" />
      <el-table-column label="险别" align="center" prop="instituteCode" />
      <el-table-column label="代理人" align="center" prop="agentId" />
      <el-table-column label="询价状态" align="center" prop="status" />
      <el-table-column label="保费总额" align="center" prop="premium" />
      <el-table-column label="商业险" align="center" prop="premiumSy" />
      <el-table-column label="交强险" align="center" prop="premiumJq" />
      <el-table-column label="车船税" align="center" prop="tax" />
      <el-table-column label="创建时间" align="center" prop="qCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="qUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地区" align="center" prop="areaCode" />
      <el-table-column label="所属机构" align="center" prop="orgId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['policy:freeInquiry:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['policy:freeInquiry:remove']"
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

    <!-- 添加或修改自由询价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFreeInquiry } from "@/api/policy/query";

export default {
  name: "FreeInquiry",
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
      // 自由询价表格数据
      freeInquiryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        owner: null,
        ownerCertNo: null,
        insrncBgnTm: null,
        insrncEndTm: null,
        agentId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询自由询价列表 */
    getList() {
      this.loading = true;
      listFreeInquiry(this.queryParams).then(response => {
        this.freeInquiryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
    // 取消按钮
    // cancel() {
    //   this.open = false;
    //   this.reset();
    // },
    // // 表单重置
    // reset() {
    //   this.form = {
    //     qId: null,
    //     queryId: null,
    //     channelId: null,
    //     deptNo: null,
    //     vehicleNo: null,
    //     engineNo: null,
    //     rackNo: null,
    //     etBrand: null,
    //     brandCode: null,
    //     vehicleValue: null,
    //     belongType: null,
    //     useType: null,
    //     extMsr: null,
    //     setNum: null,
    //     tonnage: null,
    //     changeOwnerFlag: null,
    //     transferDate: null,
    //     owner: null,
    //     ownerCertType: null,
    //     ownerCertNo: null,
    //     ownerTel: null,
    //     insrncBgnTm: null,
    //     insrncEndTm: null,
    //     insrncBgnTmJq: null,
    //     insrncEndTmJq: null,
    //     registerDate: null,
    //     applicantName: null,
    //     applicantCertType: null,
    //     applicantCertNo: null,
    //     applicantTel: null,
    //     applicantSex: null,
    //     applicantAge: null,
    //     insuredName: null,
    //     insuredCertType: null,
    //     insuredCertNo: null,
    //     insuredTel: null,
    //     insuredSex: null,
    //     insuredAge: null,
    //     instituteCode: null,
    //     agentId: null,
    //     agentName: null,
    //     serviceId: null,
    //     serviceName: null,
    //     content: null,
    //     calAppNo: null,
    //     jqIsImmefc: null,
    //     syIsImmefc: null,
    //     jqPlyAppNo: null,
    //     syPlyAppNo: null,
    //     jqAppNo: null,
    //     syAppNo: null,
    //     flag: null,
    //     status: "0",
    //     respCode: null,
    //     respMsg: null,
    //     premium: null,
    //     premiumSy: null,
    //     premiumJq: null,
    //     tax: null,
    //     qCreateTime: null,
    //     qUpdateTime: null,
    //     payAppNo: null,
    //     expressType: null,
    //     areaCode: null,
    //     orgId: null,
    //     remark: null,
    //     templateFlag: null,
    //     templateJq: null
    //   };
    //   this.resetForm("form");
    // },
    // /** 搜索按钮操作 */
    // handleQuery() {
    //   this.queryParams.pageNum = 1;
    //   this.getList();
    // },
    // /** 重置按钮操作 */
    // resetQuery() {
    //   this.resetForm("queryForm");
    //   this.handleQuery();
    // },
    // // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.qId)
    //   this.single = selection.length!==1
    //   this.multiple = !selection.length
    // },
    // /** 新增按钮操作 */
    // handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加自由询价";
    // },
    // /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const qId = row.qId || this.ids
    //   getFreeInquiry(qId).then(response => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改自由询价";
    //   });
    // },
    // /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.qId != null) {
    //         updateFreeInquiry(this.form).then(response => {
    //           this.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addFreeInquiry(this.form).then(response => {
    //           this.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    // /** 删除按钮操作 */
    // handleDelete(row) {
    //   const qIds = row.qId || this.ids;
    //   this.$confirm('是否确认删除自由询价编号为"' + qIds + '"的数据项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return delFreeInquiry(qIds);
    //     }).then(() => {
    //       this.getList();
    //       this.msgSuccess("删除成功");
    //     })
    // },
    // /** 导出按钮操作 */
    // handleExport() {
    //   const queryParams = this.queryParams;
    //   this.$confirm('是否确认导出所有自由询价数据项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return exportFreeInquiry(queryParams);
    //     }).then(response => {
    //       this.download(response.msg);
    //     })
    // }
  }
};
</script>
