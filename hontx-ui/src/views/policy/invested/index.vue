<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车主" prop="owner">
        <el-input
          v-model="queryParams.owner"
          placeholder="请输入车主"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理人姓名" prop="agentName">
        <el-input
          v-model="queryParams.agentName"
          placeholder="请输入代理人姓名"
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
          v-hasPermi="['policy:invested:add']"
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
          v-hasPermi="['policy:invested:edit']"
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
          v-hasPermi="['policy:invested:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['policy:invested:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="investedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键Id" align="center" prop="qId" />-->
      <el-table-column label="询价Id" align="center" prop="queryId" />
      <el-table-column label="投保公司" align="center" prop="channelId" >
        <template slot-scope="scope">
          {{ scope.row.channelId |  filterChannelId }}
        </template>
      </el-table-column>
      <el-table-column label="车牌号" align="center" prop="vehicleNo" >
        <template slot-scope="scope">
          <el-tag effect="dark">{{ scope.row.vehicleNo }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="车主" align="center" prop="owner" />
      <el-table-column label="代理人" align="center" prop="agentName" />
      <el-table-column label="询价方式" align="center" prop="flag" >
        <template slot-scope="scope">
          <el-tag effect="dark" :type="scope.row.flag === '0' ? 'success' : 'warning'">
            {{ scope.row.flag | filterFlg }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="询价状态"
        align="center"
        prop="status"
        show-overflow-tooltip >
        <template slot-scope="scope">
          <div v-if="scope.row.status === '00'">
            <el-tag>待询价</el-tag>
          </div>
          <div v-else-if="scope.row.status === '01'">
            <el-tag>已询价</el-tag>
          </div>
          <div v-else-if="scope.row.status ==='02'">
            <el-tag type="warning">已询价</el-tag>
          </div>
          <div v-else-if="scope.row.status ==='03'">
            <el-tag effect="plain" type="warning">待投保</el-tag>
          </div>
          <div v-else-if="scope.row.status === '05'">
            <el-tag type="success">已投保</el-tag>
          </div>
          <div v-else-if="scope.row.status === '10'">
            <el-tag type="success">驳回修改</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="保费" align="center" prop="premium" />
      <el-table-column label="商业险保费" align="center" prop="premiumSy" />
      <el-table-column label="交强险保费" align="center" prop="premiumJq" />
      <el-table-column label="车船税" align="center" prop="tax" />
      <el-table-column label="创建时间" align="center" prop="qCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qCreateTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="qUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.qUpdateTime, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleInsured(scope.row)"
            v-hasPermi="['policy:invested:edit']"
          >点击投保</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['policy:invested:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['policy:invested:remove']"
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

    <!-- 添加或修改待投保单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="保险公司" prop="channelId">
          <el-select v-model="form.channelId" placeholder="请选择保险公司">
            <el-option v-for="item in channelOptions"
                       :key="item.value"
                       :label="item.value"
                       :value="item.key"/>
          </el-select>
<!--          <el-input v-model="form.channelId" placeholder="请输入保险Id" />-->
        </el-form-item>
        <el-form-item label="车牌号" prop="vehicleNo">
          <el-input v-model="form.vehicleNo" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="发动机号" prop="engineNo">
          <el-input v-model="form.engineNo" placeholder="请输入发动机号" />
        </el-form-item>
        <el-form-item label="车架号" prop="rackNo">
          <el-input v-model="form.rackNo" placeholder="请输入车架号" />
        </el-form-item>
        <el-form-item label="品牌" prop="etBrand">
          <el-input v-model="form.etBrand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="品牌型号" prop="brandCode">
          <el-input v-model="form.brandCode" placeholder="请输入品牌型号" />
        </el-form-item>
        <el-form-item label="所属性质" prop="belongType">
          <el-select v-model="form.belongType" placeholder="请选择所属性质">
            <el-option
              v-for="dict in belongTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用类型" prop="useType">
          <el-select v-model="form.useType" placeholder="请选择使用类型">
            <el-option
              v-for="dict in useTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排气量" prop="extMsr">
          <el-input v-model="form.extMsr" placeholder="请输入排气量" />
        </el-form-item>
        <el-form-item label="座位数" prop="setNum">
<!--          <el-input v-model="form.setNum" placeholder="请输入座位数" />-->
          <el-input-number v-model="form.setNum" :min="1" :max="100"/>
        </el-form-item>
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="转让标识" prop="changeOwnerFlag">
          <el-input v-model="form.changeOwnerFlag" placeholder="请输入转让标识" />
        </el-form-item>
        <el-form-item label="转让日期" prop="transferDate">
          <el-date-picker clearable size="small"
            v-model="form.transferDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择转让日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车主" prop="owner">
          <el-input v-model="form.owner" placeholder="请输入车主" />
        </el-form-item>
        <el-form-item label="车主证件类型" prop="ownerCertType">
          <el-select v-model="form.ownerCertType" placeholder="请选择车主证件类型">
<!--            <el-option label="请选择字典生成" value="" />-->
            <el-option v-for="item in insuredCertTypeOption"
                       :key="item.key"
                       :label="item.value"
                       :value="item.key"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车主证件号" prop="ownerCertNo">
          <el-input v-model="form.ownerCertNo" placeholder="请输入车主证件号" />
        </el-form-item>
        <el-form-item label="车主电话" prop="ownerTel">
          <el-input v-model="form.ownerTel" placeholder="请输入车主电话" />
        </el-form-item>
        <el-form-item label="商业险开始日期" prop="insrncBgnTm">
          <el-date-picker clearable size="small"
            v-model="form.insrncBgnTm"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择商业险开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="商业险结束日期" prop="insrncEndTm">
          <el-date-picker clearable size="small"
            v-model="form.insrncEndTm"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择商业险结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交强险开始日期" prop="insrncBgnTmJq">
          <el-date-picker clearable size="small"
            v-model="form.insrncBgnTmJq"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交强险开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交强险结束日期" prop="insrncEndTmJq">
          <el-date-picker clearable size="small"
            v-model="form.insrncEndTmJq"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交强险结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册日期" prop="registerDate">
          <el-date-picker clearable size="small"
            v-model="form.registerDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择注册日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投保人姓名" prop="applicantName">
          <el-input v-model="form.applicantName" placeholder="请输入投保人姓名" />
        </el-form-item>
        <el-form-item label="投保人证件类型：00  身份证" prop="applicantCertType">
          <el-select v-model="form.applicantCertType" placeholder="请选择投保人证件类型：00  身份证">
            <el-option v-for="item in insuredCertTypeOption"
                       :key="item.key"
                       :label="item.value"
                       :value="item.key"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="投保人证件号" prop="applicantCertNo">
          <el-input v-model="form.applicantCertNo" placeholder="请输入投保人证件号" />
        </el-form-item>
        <el-form-item label="投保人电话" prop="applicantTel">
          <el-input v-model="form.applicantTel" placeholder="请输入投保人电话" />
        </el-form-item>
        <el-form-item label="投保人性别" prop="applicantSex">
          <el-select v-model="form.applicantSex" placeholder="请选择投保人性别">
            <el-option
              v-for="dict in insuredSexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="投保人年龄" prop="applicantAge">
<!--          <el-input v-model="form.applicantAge" placeholder="请输入投保人年龄" />-->
          <el-input-number v-model="form.applicantAge" :min="1" :max="130"/>
        </el-form-item>
        <el-form-item label="被投保人姓名" prop="insuredName">
          <el-input v-model="form.insuredName" placeholder="请输入被投保人姓名" />
        </el-form-item>
        <el-form-item label="被投保人证件类型" prop="insuredCertType">
          <el-select v-model="form.insuredCertType" placeholder="请选择被投保人证件类型">
            <el-option v-for="item in insuredCertTypeOption"
                       :key="item.value"
                       :label="item.value"
                       :value="item.key"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="被投保人证件号" prop="insuredCertNo">
          <el-input v-model="form.insuredCertNo" placeholder="请输入被投保人证件号" />
        </el-form-item>
        <el-form-item label="被投保人电话" prop="insuredTel">
          <el-input v-model="form.insuredTel" placeholder="请输入被投保人电话" />
        </el-form-item>
        <el-form-item label="被投保人性别" prop="insuredSex">
          <el-select v-model="form.insuredSex" placeholder="请选择被投保人性别">
            <el-option
              v-for="dict in insuredSexOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="被投保人年龄" prop="insuredAge">
          <el-input-number v-model="form.insuredAge" :min="1" :max="130"/>
<!--          <el-input v-model="form.insuredAge" placeholder="请输入被投保人年龄" />-->
        </el-form-item>
<!--        <el-form-item label="保险类型" prop="instituteCode">-->
<!--          <el-input v-model="form.instituteCode" placeholder="请输入保险类型" />-->
<!--        </el-form-item>-->
        <el-form-item label="代理人Id" prop="agentId">
          <el-input v-model="form.agentId" placeholder="请输入代理人Id" />
        </el-form-item>
        <el-form-item label="代理人姓名" prop="agentName">
          <el-input v-model="form.agentName" placeholder="请输入代理人姓名" />
        </el-form-item>
        <el-form-item label="服务Id" prop="serviceId">
          <el-input v-model="form.serviceId" placeholder="请输入服务Id" />
        </el-form-item>
        <el-form-item label="服务姓名" prop="serviceName">
          <el-input v-model="form.serviceName" placeholder="请输入服务姓名" />
        </el-form-item>
        <el-form-item label="内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="报价单号" prop="calAppNo">
          <el-input v-model="form.calAppNo" placeholder="请输入报价单号" />
        </el-form-item>
        <el-form-item label="报价单号" prop="jqIsImmefc">
          <el-input v-model="form.jqIsImmefc" placeholder="请输入报价单号" />
        </el-form-item>
        <el-form-item label="报价单号" prop="syIsImmefc">
          <el-input v-model="form.syIsImmefc" placeholder="请输入报价单号" />
        </el-form-item>
        <el-form-item label="交强险投保单号" prop="jqPlyAppNo">
          <el-input v-model="form.jqPlyAppNo" placeholder="请输入交强险投保单号" />
        </el-form-item>
        <el-form-item label="商业险投保单号" prop="syPlyAppNo">
          <el-input v-model="form.syPlyAppNo" placeholder="请输入商业险投保单号" />
        </el-form-item>
        <el-form-item label="交强险单号" prop="jqAppNo">
          <el-input v-model="form.jqAppNo" placeholder="请输入交强险单号" />
        </el-form-item>
        <el-form-item label="商业险单号" prop="syAppNo">
          <el-input v-model="form.syAppNo" placeholder="请输入商业险单号" />
        </el-form-item>
        <el-form-item label="询价标识0自动，1人工" prop="flag">
          <el-select v-model="form.flag" placeholder="请选择询价标识0自动，1人工">
            <el-option
              v-for="dict in flagOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="询价状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="询价状态" prop="respCode">
          <el-input v-model="form.respCode" placeholder="请输入询价状态" />
        </el-form-item>
        <el-form-item label="询价状态" prop="respMsg">
          <el-input v-model="form.respMsg" placeholder="请输入询价状态" />
        </el-form-item>
        <el-form-item label="保费" prop="premium">
          <el-input v-model="form.premium" placeholder="请输入保费" />
        </el-form-item>
        <el-form-item label="商业险保费" prop="premiumSy">
          <el-input v-model="form.premiumSy" placeholder="请输入商业险保费" />
        </el-form-item>
        <el-form-item label="交强险保费" prop="premiumJq">
          <el-input v-model="form.premiumJq" placeholder="请输入交强险保费" />
        </el-form-item>
        <el-form-item label="车船税" prop="tax">
          <el-input v-model="form.tax" placeholder="请输入车船税" />
        </el-form-item>
        <el-form-item label="创建时间" prop="qCreateTime">
          <el-date-picker clearable size="small"
            v-model="form.qCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="qUpdateTime">
          <el-date-picker clearable size="small"
            v-model="form.qUpdateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="支付单号" prop="payAppNo">
          <el-input v-model="form.payAppNo" placeholder="请输入支付单号" />
        </el-form-item>
        <el-form-item label="区域码" prop="areaCode">
          <el-input v-model="form.areaCode" placeholder="请输入区域码" />
        </el-form-item>
        <el-form-item label="机构Id" prop="orgId">
          <el-input v-model="form.orgId" placeholder="请输入机构Id" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listQuery, getQuery, delQuery, addQuery, updateQuery, exportQuery } from "@/api/policy/invested";
import Editor from '@/components/Editor';

export default {
  name: "Invested",
  components: {
    Editor,
  },
    filters:{

        filterFlg(val){
            let flgOption = {
                "0" : "自动",
                "1" : "人工"
            }
            return flgOption[val];
        },
        filterChannelId(val){
            let channelMap = {
                "00":"华安保险",
                "01":"中华保险",
                "02":"太平洋保险",
                "03":"平安",
                "04":"人保保险",
                "05":"诚泰保险",
                "06":"人寿保险",
                "07":"大地保险",
                "08":"长安保险",
                "09":"平安众安联合车险",
                "10":"国任财险",
                "11":"阳光财险",
                "12":"天安财险",
                "13":"太平财险",
                "14":"紫金财险",
                "15":"亚太财险",
                "16":"天平保险",
                "17":"永城保险",
                "18":"华泰保险",
                "19":"永安保险",
                "20":"大家保险"
            }
            return channelMap[val];
        }
    },
  data() {
    return {
      ns:[
          {key:"00",value:"华安保险"},
          {key:"01",value:"中华保险"},
          {key:"02",value:"太平洋保险"},
          {key:"03",value:"平安"},
          {key:"04",value:"人保保险"},
          {key:"05",value:"诚泰保险"},
          {key:"06",value:"人寿保险"},
          {key:"07",value:"大地保险"},
          {key:"08",value:"长安保险"},
          {key:"09",value:"平安众安联合车险"},
          {key:"10",value:"国任财险"},
          {key:"11",value:"阳光财险"},
          {key:"12",value:"天安财险"},
          {key:"13",value:"太平财险"},
          {key:"14",value:"紫金财险"},
          {key:"15",value:"亚太财险"},
          {key:"16",value:"天平保险"},
          {key:"17",value:"永城保险"},
          {key:"18",value:"华泰保险"},
          {key:"19",value:"永安保险"},
          {key:"20",value:"大家保险"}
      ],

      insuredCertTypeOption:[
          {key:"00",value:"身份证"},
          {key:"01",value:"组织机构代码"},
          {key:"03",value:"军官证"}],
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
      // 待投保单表格数据
      investedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 所属性质字典
      belongTypeOptions: [],
      // 使用类型字典
      useTypeOptions: [],
      // 被投保人性别字典
      insuredSexOptions: [],
      // 询价标识0自动，1人工字典
      flagOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleNo: null,
        owner: null,
        agentName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        channelId: [
          { required: true, message: "保险Id不能为空", trigger: "blur" }
        ],
        vehicleNo: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
        belongType: [
          { required: true, message: "所属性质不能为空", trigger: "change" }
        ],
        useType: [
          { required: true, message: "使用类型不能为空", trigger: "change" }
        ],
        setNum: [
          { required: true, message: "座位数不能为空", trigger: "blur" }
        ],
        owner: [
          { required: true, message: "车主不能为空", trigger: "blur" }
        ],
        ownerCertType: [
          { required: true, message: "车主证件类型不能为空", trigger: "change" }
        ],
        ownerCertNo: [
          { required: true, message: "车主证件号不能为空", trigger: "blur" }
        ],
        ownerTel: [
          { required: true, message: "车主电话不能为空", trigger: "blur" }
        ],
        qUpdateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("belong_type").then(response => {
      this.belongTypeOptions = response.data;
    });
    this.getDicts("sys_use_type").then(response => {
      this.useTypeOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.insuredSexOptions = response.data;
    });
    this.getDicts("sys_auto_flg").then(response => {
      this.flagOptions = response.data;
    });
  },
  methods: {
    /** 查询待投保单列表 */
    getList() {
      this.loading = true;
      this.queryParams.status = '03';
        listQuery(this.queryParams).then(response => {
        this.investedList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 所属性质字典翻译
    belongTypeFormat(row, column) {
      return this.selectDictLabel(this.belongTypeOptions, row.belongType);
    },
    // 使用类型字典翻译
    useTypeFormat(row, column) {
      return this.selectDictLabel(this.useTypeOptions, row.useType);
    },
    // 被投保人性别字典翻译
    insuredSexFormat(row, column) {
      return this.selectDictLabel(this.insuredSexOptions, row.insuredSex);
    },
    // 询价标识0自动，1人工字典翻译
    flagFormat(row, column) {
      return this.selectDictLabel(this.flagOptions, row.flag);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        qId: null,
        queryId: null,
        channelId: null,
        deptNo: null,
        vehicleNo: null,
        engineNo: null,
        rackNo: null,
        etBrand: null,
        brandCode: null,
        vehicleValue: null,
        belongType: null,
        useType: null,
        extMsr: null,
        setNum: null,
        tonnage: null,
        changeOwnerFlag: null,
        transferDate: null,
        owner: null,
        ownerCertType: null,
        ownerCertNo: null,
        ownerTel: null,
        insrncBgnTm: null,
        insrncEndTm: null,
        insrncBgnTmJq: null,
        insrncEndTmJq: null,
        registerDate: null,
        applicantName: null,
        applicantCertType: null,
        applicantCertNo: null,
        applicantTel: null,
        applicantSex: null,
        applicantAge: null,
        insuredName: null,
        insuredCertType: null,
        insuredCertNo: null,
        insuredTel: null,
        insuredSex: null,
        insuredAge: null,
        instituteCode: null,
        agentId: null,
        agentName: null,
        serviceId: null,
        serviceName: null,
        content: null,
        calAppNo: null,
        jqIsImmefc: null,
        syIsImmefc: null,
        jqPlyAppNo: null,
        syPlyAppNo: null,
        jqAppNo: null,
        syAppNo: null,
        flag: null,
        status: "0",
        respCode: null,
        respMsg: null,
        premium: null,
        premiumSy: null,
        premiumJq: null,
        tax: null,
        qCreateTime: null,
        qUpdateTime: null,
        payAppNo: null,
        expressType: null,
        areaCode: null,
        orgId: null,
        remark: null,
        templateFlag: null,
        templateJq: null
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
      this.ids = selection.map(item => item.qId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加待投保单";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const qId = row.qId || this.ids
        getQuery(qId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改待投保单";
      });
    },
    /***点击投保**/
    handleInsured(row){
        this.$confirm('确认用户已投保, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            let data = row;
            data.status = '05';
            updateQuery(data).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
            })
            this.$router.go(-1)
            this.$message({
                type: 'success',
                message: '修改成功!'
            });
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消修改'
            });
        });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qId != null) {
              updateQuery(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
              addQuery(this.form).then(response => {
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
      const qIds = row.qId || this.ids;
      this.$confirm('是否确认删除待投保单编号为"' + qIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQuery(qIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有待投保单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportQuery(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
