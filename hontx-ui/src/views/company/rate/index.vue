<template>
  <div class="app-container">
    <el-form :model="form" ref="form" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保险公司" prop="channelId">
        <el-select v-model="form.channelId" placeholder="请选择保险公司" clearable size="small">
          <el-option
            v-for="item in channelIdOption"
            :key="item.value"
            :label="item.value"
            :value="item.key"/>
        </el-select>
      </el-form-item>
      <el-form-item label="起止日期" prop="effectDate">
        <el-date-picker clearable size="small"
          v-model="form.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="起始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="" prop="iCreateTime">
        <el-date-picker clearable size="small"
          v-model="form.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="终止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="所属性质"  prop="belongType" >
        <el-select v-model="form.belongType"  placeholder="请选择用车性质"  clearable size="small">
          <el-option key="个人用车" label="个人用车" value="00"/>
          <el-option key="企业用车" label="企业用车" value="01"/>
          <el-option key="机关团体用车" label="机关团体用车" value="02"/>
        </el-select>
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
          <el-select v-model="form.areaCode" placeholder="请选择城市"  clearable size="small">
            <el-option
              v-for="item in cityCodeOption"
              :key="item.name"
              :label="item.name"
              :value="item.code"/>
          </el-select>

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




    <vxe-table
      border
      resizable
      show-overflow
      :span-method="rowspanMethod"
      :data="tableData"
      :edit-config="{trigger: 'click', mode: 'row',activeMethod: activeCellMethod}"
      @edit-disabled="editDisabledEvent"
    >
<!--      -->
<!--      <vxe-table-column field="insnrcCode" title="险种" :edit-render="{name: '$select', options: insnrcCodeList}"></vxe-table-column>-->
      <vxe-table-column field="insrncType" title="险种" :edit-render="{name: '$select', options: insnrcTypeList}"></vxe-table-column>
      <vxe-table-column field="useType" title="营运性质" :edit-render="{name: '$select', options: useTypeList}"></vxe-table-column>
<!--      <vxe-table-column field="vehicleType" title="车辆类型" :edit-render="{name: '$select', options: vehicleTypeList}"></vxe-table-column>-->

      <vxe-table-column field="vehicleType" title="车辆类型" :edit-render="{name: '$select', options: vehicleTypeList}"></vxe-table-column>

      <vxe-table-column field="totalRate" title="总费率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入总费率'}}"></vxe-table-column>

      <vxe-table-column field="agentRate" title="代理人费率" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入代理人'}}"></vxe-table-column>

      <vxe-table-column field="aRate" title="A管理收益率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入A管理'}}"></vxe-table-column>

      <vxe-table-column field="bRate" title="B管理收益率(%)" :edit-render="{name: 'input', attrs: {type: 'text', placeholder: '请输入B管理'}}"></vxe-table-column>

    </vxe-table>
    <br/>
    <div style="align-content: center">
      <el-button type="primary" @click="handleSave">保存</el-button>
      <el-button type="primary" @click="handleReset">返回</el-button>
    </div>
  </div>
</template>

<script>
import { listRate, getRate, delRate, addRate, updateRate, exportRate } from "@/api/rate/rate";
import { listRegion } from "@/api/regoin/regoin"

export default {
  name: "Rate",
  components: {
  },
  data() {
    return {

      tableData:[
          {
              "insrncType":"01", //交强险
              "useType":"00", //非运营
              "vehicleType": "00", //新车
              "totalRate":"10",
              "agentRate":"10",
              "aRate":"0.5",
              "bRate":"0.25"


          },
        {
            "insrncType":"01", //交强险
            "useType":"00", //非运营
            "vehicleType": "01", //旧车
            "totalRate":"10",
            "agentRate":"10",
            "aRate":"0.5",
            "bRate":"0.25"
        },
          {

              "useType":"01", //非运营
              "insrncType":"01",

              "vehicleType": "00", //新车
              "totalRate":"10",
              "agentRate":"10",
              "aRate":"0.5",
              "bRate":"0.25"


          },
        {

            "useType": "01", //非运营
            "insrncType": "01",

            "vehicleType": "01", //旧车
            "totalRate":"10",
            "agentRate":"10",
            "aRate":"0.5",
            "bRate":"0.25"
        },
          {

              "useType":"00", //非运营
              "insrncType":"00", //商业险
              "belongType":"00",

              "vehicleType": "00", //新车
              "totalRate":"10",
              "agentRate":"10",
              "aRate":"0.5",
              "bRate":"0.25"


          },
        {

            "useType":"00", //非运营
            "insrncType":"00", //商业险
            "belongType":"00",

            "vehicleType": "01", //旧车
            "totalRate":"10",
            "agentRate":"10",
            "aRate":"0.5",
            "bRate":"0.25"
        },
          {

              "useType":"01", //运营
              "insrncType":"00", //商业险
              "belongType":"00",

              "vehicleType": "00", //新车
              "totalRate":"10",
              "agentRate":"10",
              "aRate":"0.5",
              "bRate":"0.25"


          },
        {

            "useType": "01", //运营
            "insrncType": "00", //商业险
            "belongType": "00",

            "vehicleType": "01", //旧车
            "totalRate":"10",
            "agentRate":"10",
            "aRate":"0.5",
            "bRate":"0.25"
        },

      ],


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

      columns: [
          { field: 'insrncType', title: '险种', width: '150',edit:"{name:'$select', options:insnrcTypeList}" },
          { field: 'useTypeList', title: '营运性质', width: '150',edit:"{name:'$select', options:useTypeList}" },
          { field: 'belongType', title: '所属性质', width: '150', edit:"{name:'$select', options:belongTypeList}"},
          { field: 'vehicleType', title: '车辆类型', width: '150',edit:"{name:'$select', options:vehicleTypeList}" },
          { field: 'totalRate', title: '总费率(%)', width: '150' ,edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}"},
          { field: 'agentRate', title: '代理人收益率(%)', width: '150',edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}" },
          { field: 'aRate', title: 'A管理收益率(%)', width: '150' ,edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}"},
          { field: 'bRate', title: 'B管理收益率(%)', width: '150',edit:"{name:'$input', attrs:{type:text,placeholder:'请输入'}" },
      ],
      insnrcCodeList: [
          {value:'0',label:'交强险'},
          {value:'1',label:'商业险'},
      ],
        vehicleTypeList: [
            {value:'00',label:'新车'},
            {value:'01',label:'旧车'},
        ],
      insnrcTypeList: [
          {value:'00',label:'商业险'},
          {value:'01',label:'交强险'},
          // {value:'02',label:'三责+车损(非运营)'},
          // {value:'03',label:'交强+商业(非运营)'},
          // {value:'02',label:'商业险(运营)'},
          // {value:'03',label:'交强险(运营)'},
          // {value:'06',label:'三责+车损(运营)'},
          // {value:'07',label:'交强+商业(运营)'},
      ],
        useTypeList:[
            {value:'00',label:'非运营'},
            {value:'01',label:'运营'},
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
   this.getList()
  },
  methods: {

    // 通用行合并函数（将相同多列数据合并为一行）
    rowspanMethod ({ row, _rowIndex, column, visibleData }) {
        let fields = ['insrncType','useType','vehicleType']
        let cellValue = row[column.property]
        if (cellValue && fields.includes(column.property)) {
            let prevRow = visibleData[_rowIndex - 1]
            let nextRow = visibleData[_rowIndex + 1]
            if (prevRow && prevRow[column.property] === cellValue) {
                return {rowspan: 0, colspan: 0}
            } else {
                let countRowspan = 1
                while (nextRow && nextRow[column.property] === cellValue) {
                    nextRow = visibleData[++countRowspan + _rowIndex]
                }
                if (countRowspan > 1) {
                    return {rowspan: countRowspan, colspan: 1}
                }
            }
        }
    },

    //保存
      //保存
    handleSave(){
        this.form.data = this.tableData;
        if(!this.form.channelId){
            this.$message.error("保险公司不能为空");
            return
        }
        if(!this.form.startDate){
            this.$message.error("生效开始日期不能为空");
            return
        }
        if(!this.form.endDate){
            this.$message.error("生效结束日期不能为空");
            return
        }
        if(!this.form.belongType){
            this.$message.error("所属性质不能为空");
            return
        }
        if(!this.form.areaCode){
            this.$message.error("生效区域不能为空");
            return
        }
        // let data = []
        // let flg = true;
        // this.tableData.forEach(i => {
        //     let item1 = {}
        //     let item2 = {}
        //
        //     i.vehicleTypes.forEach(e => {
        //         debugger
        //         if(e.vehicleType === '00'){
        //             item1 = e;
        //             if(!item1.channelId){
        //                 this.$message.error("保险公司不能为空")
        //                 flg = false;
        //             }
        //             item1.channelId = this.queryParams.channelId;
        //             if(!item1.iCreateTime){
        //                 this.$message.error("开始时间不能为空")
        //                 flg = false;
        //             }
        //             item1.iCreateTime = this.queryParams.iCreateTime;
        //             if(!item1.effectDate){
        //                 this.$message.error("生效时间不能为空")
        //                 flg = false;
        //             }
        //             item1.effectDate = this.queryParams.effectDate;
        //             if(!item1.areaCode){
        //                 this.$message.error("请选择地区")
        //                 flg = false;
        //             }
        //             item1.areaCode = this.queryParams.areaCode;
        //
        //         }else {
        //             item2 = e;
        //             item2.channelId = this.queryParams.channelId
        //             item2.iCreateTime = this.queryParams.iCreateTime;
        //             item2.effectDate = this.queryParams.effectDate;
        //             item1.areaCode = this.queryParams.areaCode;
        //         }
        //     })
        //
        //     item1.insrncType = i.insrncType
        //     item1.belongType = i.belongType
        //
        //     item2.insrncType = i.insrncType
        //     item2.belongType = i.belongType
        //
        //     data.push(item1)
        //     data.push(item2)
        // })
        // if(!flg){
        //     debugger
        //     return;
        // }
        this.$confirm('确认修改此费率, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            addRate(this.form).then(response => {
                this.msgSuccess("保存成功");
                this.open = false;
                this.getList();
            });
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消保存'
            });
        });


    },
      //获取省地区码
    selectAreaCode(){
        this.form.channelId = this.$route.query.companyId;
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
    /** 查询费率设置列表 */
    getList() {
      // this.loading = true;
      //   console.log("设置保险公司",item.channelId)
      this.queryParams.channelId = this.$route.query.companyId;
      this.tableData.forEach(item => {
          item.channelId = this.$route.query.companyId;
          console.log("设置保险公司",item.channelId)
      })
      // listRate(this.queryParams).then(response => {
      //   this.rateList = response.rows;
      //   this.total = response.total;
      //   this.loading = false;
      // });
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

