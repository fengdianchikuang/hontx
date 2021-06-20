<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="电话" prop="uTelephone">
        <el-input
          v-model="queryParams.uTelephone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="真实姓名" prop="uRealName">
        <el-input
          v-model="queryParams.uRealName"
          placeholder="请输入真实姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实名状态" prop="uRealNameStatus">
        <el-select v-model="queryParams.uRealNameStatus" placeholder="请选择用户实名状态" clearable size="small">
          <el-option label="未认证" value="00" />
          <el-option label="审核中" value="01" />
          <el-option label="认证未通过" value="02" />
          <el-option label="认证通过" value="03" />
        </el-select>
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
          v-hasPermi="['user:list:add']"
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
          v-hasPermi="['user:list:edit']"
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
          v-hasPermi="['user:list:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['user:list:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="listList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="用户id" align="center" prop="uId" />-->
<!--      <el-table-column label="性别" align="center" prop="uSex" />-->
<!--      <el-table-column label="出生日期" align="center" prop="uBirthday" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.uBirthday, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="电话" align="center" prop="uTelephone" width="120" />
<!--      <el-table-column label="邮箱" align="center" prop="uEmail" />-->
<!--      <el-table-column label="住址" align="center" prop="uAddress" />-->
      <el-table-column label="创建时间" align="center" prop="uCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="上级用户Id" align="center" prop="uParentId" />-->
      <el-table-column label="真实姓名" align="center" prop="uRealName" />
<!--      <el-table-column label="身份证" align="center" prop="uIdentity" />-->
      <el-table-column label="银行名" align="center" prop="uBankName" />
      <el-table-column label="卡号" align="center" prop="uAccno" />
      <el-table-column label="可用资金" align="center" prop="uAvailBal" />
<!--      00未认证,01审核中,02认证未通过,03认证通过-->
      <el-table-column label="实名状态" align="center" prop="uRealNameStatus" >
        <template slot-scope="scope">
<!--          <span>{{ scope.row.uRealNameStatus | filterRealNameStatus }}</span>-->
          <div v-if="scope.row.uRealNameStatus === '00'">
            <el-tag type="info">未认证</el-tag>
          </div>
          <div v-else-if="scope.row.uRealNameStatus === '01'">
            <el-tag type="warning">审核中</el-tag>
          </div>
          <div v-else-if="scope.row.uRealNameStatus === '02'">
            <el-tag type="danger">审核中</el-tag>
          </div>
          <div v-else-if="scope.row.uRealNameStatus === '03'">
            <el-tag type="success">已认证</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="a团队人数" align="center" prop="uAteamNum" />
      <el-table-column label="b团队人数" align="center" prop="uBteamNum" />
      <el-table-column label="认证生效日期" align="center" prop="uEffectDate" />
      <el-table-column label="邮政编码" align="center" prop="uAreaCode" />
      <el-table-column label="机构id" align="center" prop="uOrgId" />
      <el-table-column label="00:非门店,01:门店" align="center" prop="type" />
      <el-table-column label="00:普通代理人, 01: 团队长" align="center" prop="userType" />
      <el-table-column label="团队Id" align="center" prop="tId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['user:list:edit']"
          >认证</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleSelectDetail(scope.row)"-->
<!--            v-hasPermi="['user:list:edit']"-->
<!--          >详情</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['user:list:remove']"
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

    <!-- 添加或修改用户扩展信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="性别" prop="uSex">-->
<!--          <el-select v-model="form.uSex" placeholder="请选择性别">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="出生日期" prop="uBirthday">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.uBirthday"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择出生日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="电话" prop="uTelephone">
          <el-input v-model="form.uTelephone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="uEmail">
          <el-input v-model="form.uEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="住址" prop="uAddress">
          <el-input v-model="form.uAddress" placeholder="请输入住址" />
        </el-form-item>
        <el-form-item label="创建时间" prop="uCreateTime">
          <el-date-picker clearable size="small"
            v-model="form.uCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="推荐人姓名" prop="uParentId">
          <el-input v-model="form.parentName" placeholder="请输入上级用户Id" />
        </el-form-item>
        <el-form-item label="推荐人手机" prop="parentPhone">
          <el-input v-model="form.parentPhone" placeholder="请输入推荐人手机" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="uRealName">
          <el-input v-model="form.uRealName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="身份证" prop="uIdentity">
          <el-input v-model="form.uIdentity" placeholder="请输入身份证" />
        </el-form-item>
        <el-form-item label="银行名" prop="uBankName">
          <el-input v-model="form.uBankName" placeholder="请输入银行名" />
        </el-form-item>
        <el-form-item label="卡号" prop="uAccno">
          <el-input v-model="form.uAccno" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="佣金可用资金" prop="uAvailBal">
          <el-input v-model="form.uAvailBal" placeholder="请输入佣金可用资金" />
        </el-form-item>
        <el-form-item label="用户实名状态">
          <el-radio-group v-model="form.uRealNameStatus">
            <el-radio label="00">未认证</el-radio>
            <el-radio label="01">审核中</el-radio>
            <el-radio label="02">认证未通过</el-radio>
            <el-radio label="03">认证通过</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="身份证正面">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList01"
            :data="{'type':'00'}"
            :on-preview="handlePictureCardPreview1"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
<!--          :on-preview="handlePictureCardPreview"-->
          <el-dialog :visible.sync="dialogVisible1">
            <img v-if="dialogImageUrl01" :src="dialogImageUrl01" class="avatar">
          </el-dialog>
        </el-form-item>

        <el-form-item label="身份证背面" prop="type">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList02"
            :on-preview="handlePictureCardPreview2"
            :data="{'type':'01'}"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <!--          :on-preview="handlePictureCardPreview"-->
          <el-dialog :visible.sync="dialogVisible2">
            <img v-if="dialogImageUrl02" :src="dialogImageUrl02" class="avatar">
          </el-dialog>
        </el-form-item>

        <el-form-item label="手持身份证">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList03"
            :on-preview="handlePictureCardPreview3"
            :data="{'type':'02'}"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>

          <el-dialog :visible.sync="dialogVisible3">
            <img v-if="dialogImageUrl03" :src="dialogImageUrl03" class="avatar">
          </el-dialog>
        </el-form-item>

        <el-form-item label="a团队人数" prop="uAteamNum">
          <el-input v-model="form.uAteamNum" placeholder="请输入a团队人数" />
        </el-form-item>
        <el-form-item label="b团队人数" prop="uBteamNum">
          <el-input v-model="form.uBteamNum" placeholder="请输入b团队人数" />
        </el-form-item>
        <el-form-item label="认证生效日期" prop="uEffectDate">
          <el-input v-model="form.uEffectDate" placeholder="请输入认证生效日期" />
        </el-form-item>
        <el-form-item label="邮政编码" prop="uAreaCode">
          <el-select v-model="form.uAreaCode" placeholder="请选择邮政编码">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="机构id" prop="uOrgId">
          <el-input v-model="form.uOrgId" placeholder="请输入机构id" />
        </el-form-item>
        <el-form-item label="00:非门店,01:门店" prop="type">
          <el-select v-model="form.type" placeholder="请选择00:非门店,01:门店">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="00:普通代理人, 01: 团队长" prop="userType">
          <el-select v-model="form.userType" placeholder="请选择00:普通代理人, 01: 团队长">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="团队Id" prop="tId">
          <el-input v-model="form.tId" placeholder="请输入团队Id" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm(0)">保存</el-button>
        <el-button type="primary" @click="submitForm(1)">认证通过</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listList, getList, delList, addList, updateList, exportList,uploadFile,delFile } from "@/api/user/list";

export default {
  name: "List",
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
      // 用户扩展信息表格数据
      listList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //回调地址
      fileList01: [],
      fileList02: [],
      fileList03: [],
      dialogImageUrl01: '',
      dialogImageUrl02: '',
      dialogImageUrl03: '',
      dialogImageUrl04: '',
      dialogVisible1: false, //是否显示
      dialogVisible2: false, //是否显示
      dialogVisible3: false, //是否显示
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uTelephone: null,
        uRealName: null,
        uRealNameStatus: null,
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
    //上传身份证正面
    uploadPositive(param){
        debugger
        console.log("上传参数",param)
        let userId = this.form.uId;
        let fileFrom = new FormData();
        fileFrom.append("file",param.file);
        fileFrom.append("userId",userId);
        fileFrom.append("type",param.data.type);
        uploadFile(fileFrom).then(res => {
            debugger
            if(res.code === 200 ){
                let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + res.data.path;
                if(res.data.type === '00'){
                    this.dialogImageUrl01 = url;
                }else if(res.data.type === '01'){
                    this.dialogImageUrl02 = url;
                }else if(res.data.type === '02'){
                    this.dialogImageUrl03 = url;
                }
            }
        })
    },
    //删除
    handleRemove(file, fileList) {
        debugger
        console.log(file, fileList);
        delFile(file.id).then(res => {
            debugger
            if(res.code === 200){
                this.msgSuccess("删除成功");
            }else {
                this.msgError("删除失败")
            }
        })
    },
    //查看大图
    handlePictureCardPreview1(file) {
        console.log("查看大图",file)
        this.dialogImageUrl01 = file.url;
        this.dialogVisible1 = true;
    },
    handlePictureCardPreview2(file) {
        console.log("查看大图",file)
        this.dialogImageUrl02 = file.url;
        this.dialogVisible2 = true;
    },
    handlePictureCardPreview3(file) {
        console.log("查看大图",file)
        this.dialogImageUrl03 = file.url;
        this.dialogVisible3 = true;
    },

    //查看详情
    handleSelectDetail(){

    },

    //上传成功后回调
    handleAvatarSuccess(){

    },
    //上传之前
    beforeAvatarUpload(){

    },
    /** 查询用户扩展信息列表 */
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
        uId: null,
        uSex: null,
        uBirthday: null,
        uTelephone: null,
        uEmail: null,
        uAddress: null,
        uCreateTime: null,
        uParentId: null,
        uRealName: null,
        uIdentity: null,
        uBankName: null,
        uBankBranch: null,
        uAccno: null,
        uAvailBal: null,
        uTotalIncome: null,
        uIncomeA: null,
        uIncomeB: null,
        uRealNameStatus: "0",
        uAteamNum: null,
        uBteamNum: null,
        uEffectDate: null,
        uDeviceType: null,
        uDeviceId: null,
        uAreaCode: null,
        uOrgId: null,
        uPayPassword: null,
        uAvailableMoney: null,
        openid: null,
        type: null,
        userType: null,
        tId: null,
        depthToTeam: null,
        lifeAvailBal: null,
        lifeTotalBal: null
      };
      this.fileList01 = [],
      this.fileList02 = [],
      this.fileList03 = [],
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
      this.ids = selection.map(item => item.uId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户扩展信息";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uId = row.uId || this.ids
      getList(uId).then(response => {
        this.form = response.data;
        let than = this;
        debugger
        this.form.files.forEach((item,index) => {
          if(item.path){
            if(item.type === '00'){
                debugger
                    if(item.path.indexOf('/home/') != -1){
                        let url = 'http://api.echeli.com:8080/upload' + item.path + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList01.push(obj);
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList01.push(obj);
                    }
                }else if(item.type === '01'){
                    debugger
                    if(item.path.indexOf('/home/') != -1){
                        let url = 'http://api.echeli.com:8080/upload' + item.path + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList02.push(obj)
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList02.push(obj)
                    }
                }else {
                    debugger
                    if(item.path.indexOf('/home/') != -1){
                        let url = 'http://api.echeli.com:8080/upload' + item.path + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList03.push(obj);
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.id = item.uId;
                        than.fileList03.push(obj);
                    }
                }
            }

        })
        this.open = true;
        this.title = "修改用户信息信息";
      });
    },
    /** 提交按钮 */
    submitForm(flg) {
      this.$refs["form"].validate(valid => {
        if (valid) {
            debugger
            if(flg === 1){
                this.form.uRealNameStatus = '03';
            }
          if (this.form.uId != null) {
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
      const uIds = row.uId || this.ids;
      this.$confirm('是否确认删除用户扩展信息编号为"' + uIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delList(uIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户扩展信息数据项?', "警告", {
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
