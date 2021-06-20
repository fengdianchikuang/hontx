<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">-->
<!--      <el-form-item label="车主" prop="owner">-->
<!--        <el-input-->
<!--          v-model="queryParams.owner"-->
<!--          placeholder="请输入车主"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="身份证" prop="ownerCertNo">-->
<!--        <el-input-->
<!--          v-model="queryParams.ownerCertNo"-->
<!--          placeholder="请输入身份证"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="商业险开始日期" prop="insrncBgnTm">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.insrncBgnTm"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择商业险开始日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="商业险结束日期" prop="insrncEndTm">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.insrncEndTm"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择商业险结束日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="代理人" prop="agentId">-->
<!--        <el-input-->
<!--          v-model="queryParams.agentId"-->
<!--          placeholder="请输入代理人"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

<!--    <el-row :gutter="10" class="mb8">-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['policy:freeInquiry:add']"-->
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
<!--          v-hasPermi="['policy:freeInquiry:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['policy:freeInquiry:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['policy:freeInquiry:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
<!--      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
<!--    </el-row>-->

    <el-table v-loading="loading" :data="freeInquiryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="qId" />
      <el-table-column label="询价单号" width="120px" align="center" prop="queryId" />
      <el-table-column label="询价标识" align="center" prop="flag" >
        <template slot-scope="scope">
          <div v-if="scope.row.flag === 0">
            <el-tag type="success">自动</el-tag>
          </div>
          <div v-else>
            <el-tag type="warning">人工</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="车主" align="center" prop="owner" />
      <el-table-column label="投保人" align="center" prop="applicant" />
      <el-table-column label="车牌号" align="center" prop="vehicleNo" >
        <template slot-scope="scope">
          <el-tag effect="dark">{{ scope.row.vehicleNo }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="投保公司" align="center" prop="channelId" >
        <template slot-scope="scope">
          {{ scope.row.channelId |  filterChannelId }}
        </template>
      </el-table-column>
      <el-table-column label="险别" align="center" prop="instituteCode" >
        <template slot-scope="scope">
          {{ scope.row.instituteCode }}
        </template>
      </el-table-column>
      <el-table-column label="保费总额" align="center" prop="premium" >
        <template slot-scope="scope">
          {{ scope.row.premium }}
        </template>
      </el-table-column>

      <el-table-column label="商业险" align="center" prop="premiumSy" >
        <template slot-scope="scope">
          {{ scope.row.premiumSy }}
        </template>
      </el-table-column>

      <el-table-column label="交强险" align="center" prop="premiumJq" >
        <template slot-scope="scope">
          {{ scope.row.premiumJq }}
        </template>
      </el-table-column>

      <el-table-column label="车船税" align="center" prop="tax" >
        <template slot-scope="scope">
          {{ scope.row.tax }}
        </template>
      </el-table-column>

      <el-table-column label="状态" align="center" prop="status" >
        <template slot-scope="scope">
          <div v-if="scope.row.status === '00'">
            <el-tag type="warning">待询价</el-tag>
          </div>
          <div v-if="scope.row.status === '01'">
            <el-tag type="danger">询价中</el-tag>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="代理人" align="center" prop="agent" >
        <template slot-scope="scope">
          {{ scope.row.agent }}
        </template>
      </el-table-column>

      <el-table-column label="代理人电话" align="center" prop="agentTel" >
        <template slot-scope="scope">
          {{ scope.row.agentTel }}
        </template>
      </el-table-column>

      <el-table-column label="所属机构" align="center" prop="orgId">
        <template slot-scope="scope">
          <span>{{ scope.row.orgId | filterOrgId }}</span>
        </template>
      </el-table-column>

      <el-table-column label="地区" align="center" prop="areaCode">
        <template slot-scope="scope">
          <span>{{ scope.row.areaCode | filterAreaCode }}</span>
        </template>
      </el-table-column>

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
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listFreeInquiry } from "@/api/policy/query";

export default {
  name: "FreeInquiry",
  components: {
  },
  filters:{
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
      },
      //保险标识
      filterOrgId(val){
          let orgOption = {
              "2":"长沙泰昌",
              "9": "浏阳人寿"
          }
          return orgOption[val];
      },
      filterAreaCode(val){
          //  let name;
          // that.areaCodeOption.map(res => {
          //     if(val == res.code){
          //         name =  res.name;
          //     }
          // });
          return "长沙";
      },
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

    handleUpdate(row){

        this.$router.push({ name: 'freeEdit', params: { queryId: row.queryId }})
    },

    /** 查询自由询价列表 */
    getList() {
      this.loading = true;
      listFreeInquiry(this.queryParams).then(response => {
        this.freeInquiryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
  }
};
</script>
