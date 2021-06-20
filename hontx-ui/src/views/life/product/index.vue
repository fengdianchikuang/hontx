<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品类型" prop="producttype">
        <el-select v-model="queryParams.producttype" placeholder="请选择产品类型" clearable size="small">
          <el-option label="重疾" value="00" />
          <el-option label="医疗" value="02" />
          <el-option label="终身寿险" value="03" />
          <el-option label="定期寿险" value="04" />
          <el-option label="意外" value="05" />
        </el-select>
      </el-form-item>

      <el-form-item label="保险公司" prop="insrnccpy">
        <el-input
          v-model="queryParams.insrnccpy"
          placeholder="请输入保险公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="产品名称" prop="productname">
        <el-input
          v-model="queryParams.productname"
          placeholder="请输入产品名称"
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
          v-hasPermi="['life:product:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['life:product:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['life:product:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['life:product:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :header-cell-style="{background:'#f0f9eb',color:'#80878f'}" :data="productList" border @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="产品名称" align="center" prop="productname" width="150"/> >

      <el-table-column label="产品类型" align="center" prop="producttype" >
        <tempalte slot-scope="scope">
            {{ scope.row.producttype | filterProductType }}
        </tempalte>
      </el-table-column>
      <el-table-column label="保险公司" align="center" prop="insrnccpy" width="150"/>
      <el-table-column label="URL连接" align="center" prop="url" width="800px"/>
<!--      <el-table-column label="状态" align="center" prop="status" >-->
<!--        <tempalte slot-scope="scope">-->
<!--          {{ scope.row.status | filterStatus }}-->
<!--        </tempalte>-->
<!--      </el-table-column>-->
      <el-table-column label="创建时间" align="center" prop="createtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['life:product:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['life:product:remove']"
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

    <!-- 添加或修改寿险产品列表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="产品名称" prop="productname">
          <el-input v-model="form.productname" placeholder="请输产品名称" />
        </el-form-item>
        <el-form-item label="产品类型" prop="producttype">
          <el-select v-model="form.producttype" placeholder="请选择产品类型">
            <el-option v-for="item in typeOptions"
                       :key="item.key"
                       :label="item.key"
                       :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="保险公司" prop="insrnccpy">
          <el-input v-model="form.insrnccpy" placeholder="请输入保险公司" />
        </el-form-item>
        <el-form-item label="URL连接" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容" />
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
import { listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct } from "@/api/life/product";

export default {
  name: "Product",
  components: {
  },
  filters:{
    filterProductType(val){
        let typeMap = {
            "00":'重疾',
            "02":'医疗',
            "03":'终身寿险',
            "04":'定期寿险',
            "05":'意外',
        }
       return typeMap[val]
      },
      filterStatus(val){
        let statusMap = {
            "00":"启用",
            "01":"下线"
        }
        return statusMap[val];
      }
  },
  data() {
    return {
      typeOptions: [{key:'重疾',value:'00'},
          {key:'医疗',value:'02'},
          {key:'终身寿险',value:'03'},
          {key:'定期寿险',value:'04'},
          {key:'意外',value:'05'}],
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
      // 寿险产品列表表格数据
      productList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productname: null,
        producttype: null,
        insrnccpy: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productname: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        producttype: [
              { required: true, message: "产品类型不能为空", trigger: "blur" }
        ],
        insrnccpy: [
            { required: true, message: "保险公司不能为空", trigger: "blur" }
        ],
        url: [
              { required: true, message: "URL连接不能为空", trigger: "blur" }
          ],
      }
    };
  },
  created() {
    this.getList();
  },

  methods: {
    /** 查询寿险产品列表列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
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
        productname: null,
        producttype: null,
        insrnccpy: null,
        url: null,
        status: "00",
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
      this.title = "添加寿险产品列表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改寿险产品列表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduct(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduct(this.form).then(response => {
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
      this.$confirm('是否确认删除寿险产品列表编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProduct(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有寿险产品列表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProduct(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
