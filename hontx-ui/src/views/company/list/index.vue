<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名称" prop="insrncName">
        <el-input
          v-model="queryParams.insrncName"
          placeholder="请输入公司名称"
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
          v-hasPermi="['company:list:add']"
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
          v-hasPermi="['company:list:edit']"
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
          v-hasPermi="['company:list:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['company:list:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="listList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司名称" align="center" prop="iId" />
      <el-table-column label="公司名称" align="center" prop="insrncName" />
<!--      <el-table-column label="公司代号" align="center" prop="channelId" />-->
      <el-table-column label="状态" align="center" prop="openFlag" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.openFlag === '00' ? 'danger' : 'success'" effect="plain" @click="handleStatus(scope.row)">
            {{ scope.row.openFlag | filterOpenFlag }}
          </el-tag>
        </template>
      </el-table-column>
<!--      <el-table-column label="状态" align="center" prop="artFlag" />-->
<!--      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="积分状态" align="center" prop="integralStatus">
        <template slot-scope="scope">
          <div v-if="scope.row.integralStatus==='0'" @click="handleIntegralStatus(scope.row)">
            <el-tag type="danger" effect="plain">
              停用
            </el-tag>
          </div>
          <div v-else @click="handleIntegralStatus(scope.row)">
            <el-tag type="success" effect="plain" >
              启用
            </el-tag>
          </div>
        </template>
      </el-table-column>
<!--      <el-table-column label="保险公司Id" align="center" prop="companyId" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['company:list:edit']"
          >费率设置</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['company:list:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改company对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="insrncName">
          <el-input v-model="form.insrncName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="公司代号" prop="channelId">
          <el-input v-model="form.channelId" placeholder="请输入公司代号" />
        </el-form-item>
        <el-form-item label="状态" prop="openFlag">
          <el-input v-model="form.openFlag" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="状态" prop="artFlag">
          <el-input v-model="form.artFlag" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="积分状态">
          <el-radio-group v-model="form.integralStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="保险公司Id" prop="companyId">
          <el-input v-model="form.companyId" placeholder="请输入保险公司Id" />
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
import { listList, getList, delList, addList, updateList, exportList } from "@/api/company/list";

export default {
  name: "List",
  components: {
  },
  filters:{
      filterOpenFlag(val){
          let openFlagMap = {
              "00":"停用",
              "01":"启用"
          }
          return openFlagMap[val];
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
      // company表格数据
      listList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        insrncName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        insrncName: [
          { required: true, message: "公司名称不能为空", trigger: "blur" }
        ],
        channelId: [
          { required: true, message: "公司代号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //改变积分状态
    handleIntegralStatus(row){
      if(row.integralStatus === '0'){
          row.integralStatus = '1';
      }else {
          row.integralStatus = '0';
      }
      updateList(row).then(res =>{
          this.getList();
      })
    },
    //改变保险公司启用状态
    handleStatus(row){
        if(row.openFlag === '00'){
            row.openFlag = '01';
        }else {
            row.openFlag = '00';
        }
        updateList(row).then(res =>{
            this.getList();
        })
    },
    /** 查询company列表 */
    getList() {
      this.loading = true;
      listList(this.queryParams).then(response => {
        this.listList = response.rows;
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
        iId: null,
        insrncName: null,
        channelId: null,
        openFlag: null,
        artFlag: null,
        remark: null,
        integralStatus: "0",
        companyId: null
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
      this.ids = selection.map(item => item.iId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加company";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.$router.push({name:'rate',query: {companyId:row.channelId}})
      // const iId = row.iId || this.ids
      // getList(iId).then(response => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改company";
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.iId != null) {
            updateList(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addList(this.form).then(response => {
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
      const iIds = row.iId || this.ids;
      this.$confirm('是否确认删除company编号为"' + iIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delList(iIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有company数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportList(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
