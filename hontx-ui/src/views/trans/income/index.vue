<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="代理人" prop="agentName">
        <el-input
          v-model="queryParams.agentName"
          placeholder="请输入代理人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号码" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车牌号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被保人" prop="insuredName">
        <el-input
          v-model="queryParams.insuredName"
          placeholder="请输入代理人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
    </el-form-item>
      <el-form-item label="起止日期" prop="iCreateTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.startTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="起始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="effectDate">
        <el-date-picker clearable size="small"
                        v-model="queryParams.endTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="终止日期">
        </el-date-picker>
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
<!--          v-hasPermi="['trans:income:add']"-->
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
<!--          v-hasPermi="['trans:income:edit']"-->
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
<!--          v-hasPermi="['trans:income:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['trans:income:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="incomeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="iId" />
      <el-table-column label="所属机构" align="center" prop="org">
        <template slot-scope="scope">
          {{ scope.row.org | filterOrg }}
        </template>
      </el-table-column>
      <el-table-column label="地区" align="center" prop="region">
        <template slot-scope="scope">
          {{ scope.row.region | filterRegion }}
        </template>
      </el-table-column>
      <el-table-column label="月份" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}') }}</span>
        </template>
      </el-table-column>


      <el-table-column label="A团队" align="center" prop="aTeam" />
      <el-table-column label="B团队" align="center" prop="bTeam" />
<!--      <el-table-column label="代理人Id" align="center" prop="agentId" />-->
      <el-table-column label="代理人" align="center" prop="agentName" />
      <el-table-column label="月份" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>


<!--      <el-table-column label="发动机号" align="center" prop="engineNo" />-->
<!--      <el-table-column label="座位数" align="center" prop="setNum" />-->

      <el-table-column label="保险公司" align="center" prop="channelId" >
        <template slot-scope="scope">
          {{ scope.row.channelId | filterChannelId }}
        </template>
      </el-table-column>
      <el-table-column label="险种类型" align="center" prop="insrncType" >
        <template slot-scope="scope">
          {{ scope.row.insrncType | filterinsrncType }}
        </template>
      </el-table-column>
      <el-table-column label="营业性质" align="center" prop="vehicleType" >
        <template slot-scope="scope">
          {{ scope.row.vehicleType | filterVehicleType }}
        </template>
      </el-table-column>

      <el-table-column label="保单号" align="center" prop="appNo" />

      <el-table-column label="实收保费" align="center" prop="premium" />

      <el-table-column label="被保人" align="center" prop="insuredName" />
      <el-table-column label="身份证" align="center" width="150" prop="insuredCertNo" />

      <el-table-column label="起保时间" align="center" prop="insrncBgnTm" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insrncBgnTm, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到期日期" align="center" prop="insrncEndTm" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.insrncEndTm, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="车牌号码" align="center" width="100" prop="vehicleNo" />

      <el-table-column label="使用性质" align="center" prop="useType" >
        <template slot-scope="scope">
          {{ scope.row.useType | filterUserType }}
        </template>
      </el-table-column>

      <el-table-column label="所属性质" align="center" prop="belongType" >
        <template slot-scope="scope">
          {{ scope.row.belongType | filterBelongType }}
        </template>
      </el-table-column>

      <el-table-column label="车架" align="center" prop="rackNo" />
<!--      <el-table-column label="询价Id" align="center" prop="queryId" />-->

      <el-table-column label="初登日期" align="center" prop="registerDate" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="总代理费率" align="center" prop="totalRate" />

      <el-table-column label="总结算金额" align="center" prop="premium" />

      <el-table-column label="A管理费率" align="center" prop="totalIncome" />
      <el-table-column label="总结算金额" align="center" prop="agentRate" />
      <el-table-column label="代理输入" align="center" prop="agentIncome" />
      <el-table-column label="A管理费率" align="center" prop="aRate" />
      <el-table-column label="A管理津贴" align="center" prop="aIncome" />
      <el-table-column label="B管理费率" align="center" prop="bRate" />
      <el-table-column label="B管理津贴" align="center" prop="bIncome" />
      <el-table-column label="平台收益率" align="center" prop="pRate" />
      <el-table-column label="平台收益金额" align="center" prop="pIncome" />
      <el-table-column label="创建时间" align="center" prop="iCreateTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.iCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="iUpdateTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.iUpdateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['trans:income:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['trans:income:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改销售业绩对话框 -->
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
import { listIncome, getIncome, delIncome, addIncome, updateIncome, exportIncome } from "@/api/trans/income";

export default {
  name: "Income",
  components: {
  },
  filters:{
      filterOrg(val){
          let orgMap = {
              "2":"长沙泰昌",
              "9":"浏阳人寿"
          }
          return orgMap[val]
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
      },
      filterinsrncType(val){
          let insrncTypeMap = {
              "00":"单三责(非运营)",
              "01":"单交强(非运营)",
              "02":"三责+车损(非运营)",
              "03":"交强+商业(非运营)",
              "04":"单三责(运营)",
              "05":"单交强(运营)",
              "06":"三责+车损",
              "07":"交强+商业(运营)"
          }
          return insrncTypeMap[val]
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
      },
      filterVehicleType(val){
          let vehicleType = {
              "00":"非营业",
              "01":"营业"
          }
          return vehicleType[val]
      },
      filterBelongType(val){
          let belongTypeMap = {
              "00":"个人用车",
              "01":"企业用车"
          }
          return belongTypeMap[val]
      },
      // :非营业:100，营业:200
      filterUserType(val){
          const userTypeMap = {
              "00":"家庭自用汽车",
              "01":"出租租聘营运客车",
              "02":"城市公交营运客车",
              "03":"公路客运运营客车",
              "04":"营运货车",
              "05":"企业非营运客车",
              "06":"机关非营运客车",
              "07":"非营运货车",
              "08":"营运特种车",
              "09":"非营运特种车",
              "100":"非营业",
              "200":"营业"
          }
          return userTypeMap[val]
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
      // 销售业绩表格数据
      incomeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        agentName: null,
        vehicleNo: null,
        insuredName:null,
        startTime:null,
        endTime:null,
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
    /** 查询销售业绩列表 */
    getList() {
      this.loading = true;
      listIncome(this.queryParams).then(response => {
          debugger
        this.incomeList = response.rows;
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
        appNo: null,
        aTeamId: null,
        aTeam: null,
        bTeamId: null,
        bTeam: null,
        checkDate: null,
        agentId: null,
        agentName: null,
        vehicleNo: null,
        rackNo: null,
        engineNo: null,
        setNum: null,
        registerDate: null,
        belongType: null,
        specificUseType: null,
        queryId: null,
        channelId: null,
        insrncType: null,
        useType: null,
        vehicleType: null,
        insuredName: null,
        insuredCertNo: null,
        insrncBgnTm: null,
        insrncEndTm: null,
        queryServiceId: null,
        queryServiceName: null,
        checkServiceId: null,
        checkServiceName: null,
        premium: null,
        totalRate: null,
        totalIncome: null,
        agentRate: null,
        agentIncome: null,
        aRate: null,
        aIncome: null,
        bRate: null,
        bIncome: null,
        pRate: null,
        pIncome: null,
        iCreateTime: null,
        iUpdateTime: null
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
      this.title = "添加销售业绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const iId = row.iId || this.ids
      getIncome(iId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改销售业绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.iId != null) {
            updateIncome(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIncome(this.form).then(response => {
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
      this.$confirm('是否确认删除销售业绩编号为"' + iIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delIncome(iIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有销售业绩数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportIncome(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
