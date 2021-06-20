<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="机构名称" prop="orgName">
        <el-input
          v-model="queryParams.orgName"
          placeholder="请输入机构名称"
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
          v-hasPermi="['org:list:add']"
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
          v-hasPermi="['org:list:edit']"
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
          v-hasPermi="['org:list:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['org:list:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :border="true" :header-cell-style="{background: '#409EFF',color: '#FFF'}" :data="listList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="oId" />
      <el-table-column label="机构名称" align="center" prop="orgName" />
      <el-table-column label="机构类型" width="110" align="center" prop="type" >
        <template slot-scope="scope">
          {{ scope.row.type | filterType }}
        </template>
      </el-table-column>
      <el-table-column label="上级机构名称" align="center" prop="parentOrg" />
      <el-table-column label="上级机构类型" align="center" prop="parentType" >
        <template slot-scope="scope">
          {{ scope.row.parentType | filterType }}
        </template>
      </el-table-column>
      <el-table-column label="所属地区" align="center" prop="areaCode" />
      <el-table-column label="法人" align="center" prop="legalPerson" />
      <el-table-column label="营业执照号" align="center" prop="lincenseNo" />
      <el-table-column label="联系人" align="center" prop="contactName" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
      <el-table-column label="联系邮箱" align="center" width="160" prop="contactEmail" />
      <el-table-column label="地址" align="center" width="160" prop="address" />
      <el-table-column label="状态" align="center" prop="status" />

      <el-table-column label="创建时间" align="center" prop="oCreateTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="更新时间" align="center" prop="oUpdateTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="关联公司Id" align="center" prop="companyId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['org:list:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['org:list:remove']"
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

    <!-- 添加或修改机构管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="机构名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入机构名称" />
        </el-form-item>
        <el-form-item label="机构类型" prop="type">
          <el-select v-model="form.type"  placeholder="请选择机构类型">
            <el-option v-for="item in typeOption"
                       :label="item.value"
                       :value="item.key"
                       :key="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属地区"  prop="areaCode">
          <el-select placeholder="请选择所属省份" v-model="form.parentCode" @change="changeRegionByType"  @focus="changeRegion">
            <el-option v-for="item in regionOption"
                       :label="item.name"
                       :value="item.code"
                       :key="item.name"
            />
          </el-select>
          <el-select placeholder="请选择所属地市" v-model="form.areaCode">
            <el-option v-for="item in cityOption"
                       :label="item.name"
                       :value="item.code"
                       :key="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="法人" prop="legalPerson">
          <el-input v-model="form.legalPerson" placeholder="请输入法人" />
        </el-form-item>
        <el-form-item label="营业执照号" prop="lincenseNo">
          <el-input v-model="form.lincenseNo" placeholder="请输入营业执照号" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactName">
          <el-input v-model="form.contactName" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="联系邮箱" prop="contactEmail">
          <el-input v-model="form.contactEmail" placeholder="请输入联系邮箱" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
<!--        <el-form-item label="状态">-->
<!--          <el-radio-group v-model="form.status">-->
<!--            <el-radio label="1">请选择字典生成</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="oCreateTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.oCreateTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新时间" prop="oUpdateTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.oUpdateTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择更新时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="上级机构" prop="parentOrgid">
          <el-select v-model="form.parentOrgid" @focus="handleParentOrgId" placeholder="请输入上级机构名称">
            <el-option v-for="item in parentOption"
                       :key="item.orgName"
                       :label="item.orgName"
                       :value="item.oId"/>
          </el-select>
<!--          <el-input v-model="form.parentOrgid" placeholder="请输入上级机构名称" />-->
        </el-form-item>
<!--        <el-form-item label="关联保险公司" prop="companyId">-->
<!--          <el-input v-model="form.companyId" placeholder="请输入关联公司Id" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listList, getList, delList, addList, updateList, exportList,getParentOrg } from "@/api/org/list";
import {listRegion} from "@/api/regoin/regoin"

export default {
  name: "List",
  components: {
  },
  filters:{
    filterType(val){
        let typeMap = {
            "00":"省级直属机构",
            "01":"地区直属机构",
            "03":"地区合伙人",
        }
        return typeMap[val];
    }
  },
  data() {
    return {
      typeOption:[
          {key:"00",value:"省级直属机构"},
          {key:"01",value:"地区直属机构"},
          {key:"03",value:"地区合伙人"},
      ],
      parentOption:[],
      //省份
      regionOption:[],
      //城市
      cityOption: [],
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
      // 机构管理表格数据
      listList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orgName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orgName: [
          { required: true, message: "机构名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //获取上级机构
    handleParentOrgId(){
        getParentOrg().then(res =>{
            this.parentOption = res.data;
        })

    },
    //获取城市
    changeRegionByType(val){
        let data = {
          parentcode: this.form.parentCode,
        }
        listRegion(data).then(res => {
            debugger
            this.cityOption = res.data;
        })
    },
    //获取省份
    changeRegion(val){
        debugger
      if(val){
          let data = {
              type: 1,
          }
          listRegion(data).then(res => {
              debugger
              this.regionOption = res.data;
          })
      }

    },
    /** 查询机构管理列表 */
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
        oId: null,
        orgName: null,
        type: null,
        areaCode: null,
        legalPerson: null,
        lincenseNo: null,
        contactName: null,
        contactPhone: null,
        contactEmail: null,
        address: null,
        status: "0",
        oCreateTime: null,
        oUpdateTime: null,
        parentOrgid: null,
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
      this.ids = selection.map(item => item.oId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加机构管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const oId = row.oId || this.ids
      getList(oId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改机构管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.oId != null) {
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
      const oIds = row.oId || this.ids;
      this.$confirm('是否确认删除机构管理编号为"' + oIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delList(oIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有机构管理数据项?', "警告", {
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
<style>

</style>
