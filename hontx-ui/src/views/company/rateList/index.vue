<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保险公司" prop="channelId">
        <el-select v-model="queryParams.channelId" placeholder="请选择保险公司" clearable size="small">
          <el-option
            v-for="item in channelIdOption"
            :key="item.value"
            :label="item.value"
            :value="item.key"/>
        </el-select>
      </el-form-item>

      <el-form-item label="所属性质"  prop="belongType" >
        <el-select v-model="queryParams.belongType"  placeholder="请选择用车性质"  clearable size="small">
          <el-option key="个人用车" label="个人用车" value="00"/>
          <el-option key="企业用车" label="企业用车" value="01"/>
          <el-option key="机关团体用车" label="机关团体用车" value="02"/>
        </el-select>
      </el-form-item>

      <el-form-item label="日期" prop="effectDate">
        <el-date-picker clearable size="small"
          v-model="queryParams.effectDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="省份"  prop="areaCodes" >
        <el-select v-model="queryParams.areaCodes"  placeholder="请选择省份"  @change="changeAreaCode($event)" clearable size="small">
          <el-option
            v-for="item in areaCodeOption"
            :key="item.name"
            :label="item.name"
            :value="item.code"/>
        </el-select>
<!--        <el-input-->
<!--          v-model="queryParams.areaCode"-->
<!--          placeholder="请输入区域码"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
      </el-form-item>
        <el-form-item label="城市" prop="areaCode">
          <el-select v-model="queryParams.areaCode" placeholder="请选择城市"  clearable size="small">
            <el-option
              v-for="item in cityCodeOption"
              :key="item.name"
              :label="item.name"
              :value="item.code"/>
          </el-select>

      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>

    </el-form>


    <vxe-table
      border
      resizable
      show-overflow
      :data="tableData"
      :edit-config="{trigger: 'click', mode: 'row',activeMethod: activeCellMethod}"
      @edit-disabled="editDisabledEvent"
    >
      <vxe-table-column field="insrncType" title="险种" :edit-render="{name: '$select', options: insnrcTypeList}"></vxe-table-column>
<!--      <vxe-table-column field="vehicleType" title="车辆类型" :edit-render="{name: '$select', options: vehicleTypeList}"></vxe-table-column>-->
      <vxe-table-column field="useType" title="车辆类型" :edit-render="{name: '$select', options: useTypeList }"></vxe-table-column>

      <vxe-table-column field="vehicleType" title="车辆类型" :edit-render="{name: '$select', options: vehicleTypeList}"></vxe-table-column>


      <vxe-table-column field="agentRate" title="代理人收益率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入昵称'}}"></vxe-table-column>

      <vxe-table-column field="aRate" title="A管理收益率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入昵称'}}"></vxe-table-column>

      <vxe-table-column field="bRate" title="B管理收益率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入昵称'}}"></vxe-table-column>

    </vxe-table>
    <br/>
<!--    <div style="align-content: center">-->
<!--      <el-button type="primary" @click="handleSave">保存</el-button>-->
<!--      <el-button type="primary" @click="handleReset">返回</el-button>-->
<!--    </div>-->
  </div>
</template>

<script>
import { listRate } from "@/api/rate/rate";
import { listRegion } from "@/api/regoin/regoin"

export default {
  name: "RateList",
  components: {
  },
  data() {
    return {
      channelIdOption:[
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
      {key:"20",value:"大家保险"},
          ],
      tableData:[],
      columns: [
          { field: 'insrncType', title: '险种组合', width: '150',edit:"{name:'$select', options:insnrcTypeList}" },
          { field: 'useType', title: '营运类型', width: '150',edit:"{name:'$select', options:useTypeList}" },
          { field: 'vehicleTypeList', title: '车辆类型', width: '150',edit:"{name:'$select', options:vehicleTypeList}" },
          { field: 'belongType', title: '所属性质', width: '150', edit:"{name:'$select', options:belongTypeList}"},
          { field: 'vehicleType', title: '车辆类型', width: '150',edit:"{name:'$select', options:vehicleTypeList}" },
          { field: 'totalRate', title: '总费率(%)', width: '150' ,edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}"},
          { field: 'agentRate', title: '代理人收益率(%)', width: '150',edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}" },
          { field: 'aRate', title: 'A管理收益率(%)', width: '150' ,edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}"},
          { field: 'bRate', title: 'B管理收益率(%)', width: '150',edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}" },
      ],
        useTypeList: [
          {value:'00',label:'非营运'},
          {value:'01',label:'营运'},
      ],
        insnrcCodeList: [
            {value:'0',label:'交强险'},
            {value:'1',label:'商业险'},
        ],
      insnrcTypeList: [
          {value:'00',label:'商业险'},
          {value:'01',label:'交强险'},
      ],
      vehicleTypeList: [
          {value:'00',label:'新车'},
          {value:'01',label:'旧车'},
      ],
      belongTypeList: [
          {value:'00',label:'家用'},
          {value:'01',label:'营用'},
      ],
      vehicleTypeList: [
          {value:'00',label:'新车'},
          {value:'01',label:'旧车'},
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
      // 费率设置表格数据
      rateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        channelId: null,
        pageNum: 1,
        pageSize: 10,
        belongType: null,
        effectDate: null,
        iCreateTime: null,
        areaCode: null,
      },
      areaCodeOption:[],
      cityCodeOption:[],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        channelId: [
          { required: true, message: "机构Id不能为空", trigger: "blur" }
        ],
        effectDate: [
          { required: true, message: "生效日期不能为空", trigger: "blur" }
        ],
        iUpdateTime: [
          { required: true, message: "修改日期不能为空", trigger: "blur" }
        ],
        areaCode: [
          { required: true, message: "区域码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
   this.selectAreaCode()
  },
  methods: {
    //查询费率
    handleQuery(){
        if(!this.queryParams.channelId){
            this.$message.error("保险公司不能为空");
            return
        }
        if(!this.queryParams.effectDate){
            this.$message.error("生效开始日期不能为空");
            return
        }
        if(!this.queryParams.belongType){
            this.$message.error("所属性质不能为空");
            return
        }
        if(!this.queryParams.areaCode){
            this.$message.error("生效区域不能为空");
            return
        }
        listRate(this.queryParams).then(res => {
            this.tableData = res.rows;
        })
    },
    //获取省地区码
    selectAreaCode(){
        let data = {
            "type": 1
        }
        listRegion(data).then(res => {
            this.areaCodeOption = res.data;
        })
    },
    //获取城市地区码
    changeAreaCode(row){
        let data = {
            "type": null,
            "parentcode":row
        }
        listRegion(data).then(res => {
            this.cityCodeOption = res.data;
        })
    },
    //改变字段
    handleChangeColumn({row,column}){

    },
    activeCellMethod({column,columnIndex}){
        if(columnIndex === 3){
            this.tableData[columnIndex].vehicleType = '01';
        }else if(columnIndex === 4)
        if(columnIndex === 0 || columnIndex === 1 || columnIndex === 2){
            return false;
        }
        return true
    },
    editDisabledEvent({row,column}){
      this.$XModal.message({content:'禁止编辑',status:'error'})
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
        channelId: null,
        insrncType: null,
        belongType: null,
        useType: null,
        vehicleType: null,
        totalRate: null,
        agentRate: null,
        aRate: null,
        bRate: null,
        effectDate: null,
        iCreateTime: null,
        iUpdateTime: null,
        areaCode: null,
        orgId: null
      };
      this.resetForm("form");
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleReset(){
        this.$route.go(-1)
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
      this.title = "添加费率设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const iId = row.iId || this.ids
      getRate(iId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改费率设置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.iId != null) {
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
      const iIds = row.iId || this.ids;
      this.$confirm('是否确认删除费率设置编号为"' + iIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRate(iIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有费率设置数据项?', "警告", {
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
