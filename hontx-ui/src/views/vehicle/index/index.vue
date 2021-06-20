<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌" prop="vehicleNo">
        <el-input
          v-model="queryParams.vehicleNo"
          placeholder="请输入车牌"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过户标识" prop="changeOwnerFlag" width="100">
        <el-select v-model="queryParams.changeOwnerFlag" placeholder="请选择过户标识" clearable size="small">
          <el-option label="未过户" value="0" />
          <el-option label="已过户" value="1" />
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
          v-hasPermi="['vehicle:index:add']"
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
          v-hasPermi="['vehicle:index:edit']"
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
          v-hasPermi="['vehicle:index:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['vehicle:index:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="indexList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="是否过户标识" align="center" prop="vId" />-->
      <el-table-column label="app/小程序" align="center" prop="flg" >
        <template slot-scope="scope">
          {{scope.row.flg | filterFlg }}
        </template>
      </el-table-column>
      <el-table-column label="车牌" width="90" align="center" prop="vehicleNo" >
        <template slot-scope="scope">
          <el-tag effect="dark">
            {{scope.row.vehicleNo}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发动机编号"  align="center" prop="engineNo" />
      <el-table-column label="车架号" width="180" align="center" prop="rackNo" />
      <el-table-column label="品牌|brandName" width="170" align="center" prop="etBrand" />
<!--      <el-table-column label="车型编码|modelCode" align="center" prop="brandCode" />-->
      <el-table-column label="车辆估价" align="center" prop="purchasePrice" />
      <el-table-column label="座位分类" align="center" prop="vehicleCategory" >
        <template slot-scope="scope">
          {{ scope.row.vehicleCategory | filterVehicleCateGory }}
        </template>
      </el-table-column>
      <el-table-column label="车辆类型" align="center" prop="vehicleType" >
        <template slot-scope="scope">
          {{ scope.row.vehicleType | filterVehicleType }}
        </template>
      </el-table-column>
      <el-table-column label="装备质量" align="center" prop="vehicleValue" />
      <el-table-column label="总质量" align="center" prop="displacement" />
      <el-table-column label="归属类型" align="center" prop="belongType" >
        <template slot-scope="scope">
          {{scope.row.belongType | filterBelongType}}
        </template>
      </el-table-column>
      <el-table-column label="使用性质" align="center" prop="useType" >
        <template slot-scope="scope">
          {{scope.row.useType | filterUserType}}
        </template>
      </el-table-column>
      <el-table-column label="车型分类" align="center" prop="plateType" >
        <template slot-scope="scope">
          {{scope.row.plateType | filterPlateType}}
        </template>
      </el-table-column>
      <el-table-column label="车牌底色" align="center" prop="plateColor" >
        <template slot-scope="scope">
          {{scope.row.plateColor | filterPlateColor}}
        </template>
      </el-table-column>
      <el-table-column label="能源类型" align="center" prop="energyType" >
        <template slot-scope="scope">
          {{scope.row.energyType | filterEnergyType }}
        </template>
      </el-table-column>
      <el-table-column label="性质细分" align="center" prop="attachNature" >
        <template slot-scope="scope">
          {{scope.row.attachNature | filterAttachNature}}
        </template>
      </el-table-column>
      <el-table-column label="排气量" align="center" prop="extMsr" />
      <el-table-column label="座位数" align="center" prop="setNum" />
      <el-table-column label="吨位" align="center" prop="tonnage" />
      <el-table-column label="过户标识" align="center" prop="changeOwnerFlag" >
        <template slot-scope="scope">
          <div v-if="scope.row.changeOwnerFlag === '0'">
            <el-tag type="success" effect="dark">
              未过户
            </el-tag>
          </div>
          <div v-else>
            <el-tag type="success" effect="danger">
              已过户
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="过户日期" align="center" prop="transferDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.transferDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['vehicle:index:edit']"
          >查看详情</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['vehicle:index:remove']"-->
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

    <!-- 添加或修改car对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1300px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌" prop="vehicleNo">
          <el-input v-model="form.vehicleNo" placeholder="请输入车牌" />
        </el-form-item>
        <el-form-item label="发动机编号" prop="engineNo">
          <el-input v-model="form.engineNo" placeholder="请输入发动机编号" />
        </el-form-item>
        <el-form-item label="车架号" prop="rackNo">
          <el-input v-model="form.rackNo" placeholder="请输入车架号/VIN码|frameNo" />
        </el-form-item>
        <el-form-item label="品牌" prop="etBrand">
          <el-input v-model="form.etBrand" placeholder="请输入品牌|brandName" />
        </el-form-item>
        <el-form-item label="座位分类" prop="vehicleCategory">
          <el-select v-model="form.vehicleCategory" placeholder="请输入座位分类">
            <el-option
              v-for="item in vehicleCategoryOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"/>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆类型" prop="vehicleType">
          <el-select v-model="form.vehicleType" placeholder="请选择车辆类型">
            <el-option
              v-for="item in vehicleTypeOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="装备质量" prop="vehicleValue">
          <el-input v-model="form.vehicleValue" placeholder="请输入装备质量" />
        </el-form-item>
        <el-form-item label="总质量" prop="displacement">
          <el-input v-model="form.displacement" placeholder="请输入总质量" />
        </el-form-item>
        <el-form-item label="归属类型" prop="belongType">
          <el-select v-model="form.belongType" placeholder="请选择归属类型">
            <el-option
              v-for="item in belongTypeOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="使用性质" prop="useType">
          <el-select v-model="form.useType" placeholder="请选择使用性质">
            <el-option
              v-for="item in useTypeOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="车型分类" prop="plateType">
          <el-select v-model="form.plateType" placeholder="请选择车型分类">
            <el-option
              v-for="item in plateTypeOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="车牌底色" prop="plateColor">
          <el-select v-modle="form.plateColor" placeholder="请选择车牌底色">
            <el-option
              v-for="item in plateColorOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="能源类型" prop="energyType">
          <el-select v-model="form.energyType" placeholder="请选择能源类型">
            <el-option
              v-for="item in energyTypeOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="使用性质分类" prop="attachNature">
          <el-select v-model="form.attachNature" placeholder="请选择使用性质分类">
            <el-option
              v-for="item in attachNatureOption"
              :key="item.key"
              :label="item.value"
              :value="item.key"/>
          </el-select>
        </el-form-item>
        <el-form-item label="排气量" prop="extMsr">
          <el-input v-model="form.extMsr" placeholder="请输入排气量:2.4T" />
        </el-form-item>
        <el-form-item label="座位数" prop="setNum">
          <el-input-number v-model="form.setNum" />
        </el-form-item>
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="过户标识" prop="changeOwnerFlag">
          <el-select v-model="form.changeOwnerFlag" placeholder="请选择过户标识">
            <el-option
              v-for="item in changeOwnerFlgOption"
              :key="item.key"
              :label="item.value"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="过户日期" prop="transferDate">
          <el-date-picker clearable size="small"
            v-model="form.transferDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择过户日期">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="行驶证正本">
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

        <el-form-item label="行驶证副本">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList02"
            :data="{'type':'01'}"
            :on-preview="handlePictureCardPreview2"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <!--          :on-preview="handlePictureCardPreview"-->
          <el-dialog :visible.sync="dialogVisible2">
            <img v-if="dialogImageUrl02" :src="dialogImageUrl02" class="avatar">
          </el-dialog>
        </el-form-item>

        <el-form-item label="车主身份证正面">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList03"
            :data="{'type':'02'}"
            :on-preview="handlePictureCardPreview3"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <!--          :on-preview="handlePictureCardPreview"-->
          <el-dialog :visible.sync="dialogVisible3">
            <img v-if="dialogImageUrl03" :src="dialogImageUrl03" class="avatar">
          </el-dialog>
        </el-form-item>

        <el-form-item label="车主身份证背面">
          <el-upload
            action="#"
            :http-request="uploadPositive"
            list-type="picture-card"
            :limit="1"
            :file-list="fileList04"
            :data="{'type':'03'}"
            :on-preview="handlePictureCardPreview4"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <!--          :on-preview="handlePictureCardPreview"-->
          <el-dialog :visible.sync="dialogVisible4">
            <img v-if="dialogImageUrl04" :src="dialogImageUrl04" class="avatar">
          </el-dialog>
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
import { listIndex, getIndex, delIndex, addIndex, updateIndex, exportIndex,uploadVehiclefile,delVehicleFile } from "@/api/vehicle/car";

export default {
  name: "Index",
  components: {
  },
  filters:{
      filterFlg(val){
          const flgMap = {
              "0": "app",
              "1": "小程序"
          }
          return flgMap[val];
      },
// :六座以下101,六座到十座以下102,十坐到二十座103,二十座到三十六座104，三十六座及以上:105
      filterVehicleCateGory(val){
          const vehicleCateGoryMap = {
              null: "空",
              "101": "六座以下",
              "102": "六座到十座以下",
              "103": "十坐到二十座",
              "104": "二十座到三十六座",
              "105": "三十六座及以上"

          }
          return vehicleCateGoryMap[val];
      },
// :小型普通客车:K31,小型越野客车K32,轿车K33,微型普通客车K41,微型越野客车:K42,微型轿车:K43
      filterVehicleType(val){
          const vehicleTypeMap = {
              null:"空",
              "K31":"小型普通客车",
              "K32":"小型越野客车",
              "K33":"轿车",
              "K41":"微型普通客车",
              "K42":"微型越野客车",
              "K43":"微型轿车"
          }
          return vehicleTypeMap[val];
      },
      filterBelongType(val){
          const belongTypeMap = {
              "00":"个人用车",
              "01":"企业用车",
              "03":"机关团体用车"
          }
          return belongTypeMap[val];
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
// :小型汽车:101,大型汽车:102,小型新能源汽车:103,大型能源汽车:104,其他车型:199
      filterPlateType(val){
          const plateTypeMap = {
              "101":"小型汽车",
              "102":"大型汽车",
              "103":"小型新能源汽车",
              "104":"大型能源汽车",
              "199":"其他车型"
          }
          return plateTypeMap[val];
      },
// :蓝101,黑:102,白:103,黄:104,白蓝:105,黄绿:106,渐变绿:107,其他:199
      filterPlateColor(val){
          const plateColorMap = {
              "101":"蓝",
              "102":"黑",
              "103":"白",
              "104":"黄",
              "105":"白蓝",
              "106":"黄绿",
              "107":"渐变绿",
              "199":"其他"
          }
          return plateColorMap[val];
      },
// :燃油:101,纯电动:102,燃料电池:103,插电混合动力:104,其他混合动力:199
      filterEnergyType(val){
          const energyTypeMap = {
              "101":"燃油",
              "102":"纯电动",
              "103":"燃料电池",
              "104":"插电混合动力",
              "199":"其他混合动力"
          }
          return energyTypeMap[val];
      },
    // ,101:家庭自用车,102:企业,103党政事业团体,199:其他非营业车辆,201:出租客车,202:城市公交,203:公路客运,205租凭客车,206营业货车
      filterAttachNature(val){
          const attachNatureMap = {
              "101":"家庭自用车",
              "102":"企业",
              "103":"党政事业团体",
              "199":"其他非营业车辆",
              "201":"出租客车",
              "202":"城市公交",
              "203":"公路客运",
              "205":"租凭客车",
              "206":"营业货车"
          }
          return attachNatureMap[val];
      }
  },
  data() {
    return {
      vehicleCategoryOption: [
          {key:"101", value:"六座以下"},
          {key:"102", value:"六座到十座以下"},
          {key:"103", value:"十坐到二十座"},
          {key:"104", value:"二十座到三十六座"},
          {key:"105", value:"三十六座及以上"}
      ],
      vehicleTypeOption: [
          {key:"K31",value:"小型普通客车"},
          {key:"K32",value:"小型越野客车"},
          {key:"K33",value:"轿车"},
          {key:"K41",value:"微型普通客车"},
          {key:"K42",value:"微型越野客车"},
          {key:"K43",value:"微型轿车"}
      ],
      belongTypeOption: [
          {key:"00",value:"个人用车"},
          {key:"01",value:"企业用车"},
          {key:"03",value:"机关团体用车"}
      ],
      useTypeOption: [
          {key:"00",value:"家庭自用汽车"},
          {key:"01",value:"出租租聘营运客车"},
          {key:"02",value:"城市公交营运客车"},
          {key:"03",value:"公路客运运营客车"},
          {key:"04",value:"营运货车"},
          {key:"05",value:"企业非营运客车"},
          {key:"06",value:"机关非营运客车"},
          {key:"07",value:"非营运货车"},
          {key:"08",value:"营运特种车"},
          {key:"09",value:"非营运特种车"},
          {key:"100",value:"非营业"},
          {key:"200",value:"营业"}
      ],
      plateTypeOption: [
          {key:"101",value:"小型汽车"},
          {key:"102",value:"大型汽车"},
          {key:"103",value:"小型新能源汽车"},
          {key:"104",value:"大型能源汽车"},
          {key:"199",value:"其他车型"}
      ],
      plateColorOption: [
          {key:"101",value:"蓝"},
          {key:"102",value:"黑"},
          {key:"103",value:"白"},
          {key:"104",value:"黄"},
          {key:"105",value:"白蓝"},
          {key:"106",value:"黄绿"},
          {key:"107",value:"渐变绿"},
          {key:"199",value:"其他"}
      ],
      energyTypeOption: [
          {key:"101",value:"燃油"},
          {key:"102",value:"纯电动"},
          {key:"103",value:"燃料电池"},
          {key:"104",value:"插电混合动力"},
          {key:"199",value:"其他混合动力"}
      ],
      attachNatureOption: [
          {key:"101",value:"家庭自用车"},
          {key:"102",value:"企业"},
          {key:"103",value:"党政事业团体"},
          {key:"199",value:"其他非营业车辆"},
          {key:"201",value:"出租客车"},
          {key:"202",value:"城市公交"},
          {key:"203",value:"公路客运"},
          {key:"205",value:"租凭客车"},
          {key:"206",value:"营业货车"}
      ],
      changeOwnerFlgOption: [
          {key:"0",value:"未过户"},
          {key:"0",value:"已过户"}
      ],
      //文件上传相关
      dialogImageUrl01: '',
      dialogImageUrl02: '',
      dialogImageUrl03: '',
      dialogImageUrl04: '',

        fileList01: [],
        fileList02: [],
        fileList03: [],
        fileList04: [],

        dialogVisible1: false,
        dialogVisible2: false,
        dialogVisible3: false,
        dialogVisible4: false,

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
      // car表格数据
      indexList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleNo: null,
        changeOwnerFlag: null,
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
    // 上传行驶证
    uploadPositive(params){
        debugger
        console.log("图片参数",params)
        let rackNo = this.form.rackNo;
        let vehicleNo = this.form.vehicleNo;
        let fileForm = new FormData();
        fileForm.append("file",params.file);
        fileForm.append("rackNo",rackNo);
        fileForm.append("vehicleNo",vehicleNo);
        fileForm.append("type",params.data.type)
        uploadVehiclefile(fileForm).then(res => {
            if(res.code === 200){
                let url = "http://ecl.oss-cn-hangzhou.aliyuncs.com/"+ res.data.path;
                if(res.data.type === '00'){
                    this.dialogImageUrl01 = url;
                }else if(res.data.type === '01'){
                    this.dialogImageUrl02 = url;
                }else if(res.data.type === '02'){
                    this.dialogImageUrl03 = url;
                }else {
                    this.dialogImageUrl04 = url;
                }
            }
        })
    },
      //查看大图
    handlePictureCardPreview1(file){
      this.dialogImageUrl01 = file.url;
      this.dialogVisible1 = true;
    },
    handlePictureCardPreview2(file){
        this.dialogImageUrl02 = file.url;
        this.dialogVisible2 = true;
    },
    handlePictureCardPreview3(file){
        this.dialogImageUrl03 = file.url;
        this.dialogVisible3 = true;
    },
    handlePictureCardPreview4(file){
        this.dialogImageUrl04 = file.url;
        this.dialogVisible4 = true;
    },
    //删除图片
    handleRemove(file, fileList) {
        console.log(file, fileList);
        delVehicleFile(file.id).then(res => {
            if(res.code === '200'){
                this.msgSuccess("删除成功");
            }else {
                this.msgError("删除失败")
            }
        })
    },
    /** 查询car列表 */
    getList() {
      this.loading = true;
      listIndex(this.queryParams).then(response => {
        this.indexList = response.rows;
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
        vId: null,
        flg: null,
        ownerId: null,
        vehicleNo: null,
        channelId: null,
        insrncEndDate: null,
        engineNo: null,
        rackNo: null,
        etBrand: null,
        brandCode: null,
        purchasePrice: null,
        actualPrice: null,
        vehicleCategory: null,
        vehicleType: null,
        vehicleValue: null,
        displacement: null,
        belongType: null,
        useType: null,
        plateType: null,
        plateColor: null,
        energyType: null,
        attachNature: null,
        extMsr: null,
        setNum: null,
        tonnage: null,
        changeOwnerFlag: null,
        transferDate: null,
        owner: null,
        ownerCertType: null,
        ownerCertNo: null,
        ownerTel: null,
        agentId: null,
        queryId: null,
        remark: null,
        vCreateTime: null,
        vUpdateTime: null,
        registerDate: null,
        insrncBgnTmSy: null,
        insrncEndTmSy: null,
        insrncBgnTmJq: null,
        insrncEndTmJq: null,
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
        openid: null,
        kbId: null,
        gId: null,
        company: null,
        address: null,
        color: null,
        carName: null,
        kbInsrncCpyName: null
      };
        this.fileList01 = [],
        this.fileList02 = [],
        this.fileList03 = [],
        this.fileList04 = [],
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
      this.ids = selection.map(item => item.vId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加car";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vId = row.vId || this.ids

      getIndex(vId).then(response => {
        this.form = response.data;
        let than = this;
        this.form.files.forEach((item,index) => {
            if(item.path){
                if(item.type === '00'){
                    debugger
                    if(item.path.indexOf('/home/') != -1){
                        let slice = item.path.substring(10);
                        let url = 'http://api.echeli.com:8080/upload' + slice + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList01.push(obj);
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList01.push(obj);
                    }
                }else if(item.type === '01'){
                    debugger
                    if(item.path.indexOf('/home/') != -1){
                        let slice = item.path.substring(10);
                        let url = 'http://api.echeli.com:8080/upload' + slice + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList02.push(obj)
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList02.push(obj)
                    }
                }else if(item.type === '02') {
                    debugger
                    if(item.path.indexOf('/home/') != -1){
                        let slice = item.path.substring(10);
                        let url = 'http://api.echeli.com:8080/upload' + slice + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList03.push(obj);
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList03.push(obj);
                    }
                }else {
                    if(item.path.indexOf('/home/') != -1){
                        let slice = item.path.substring(10);
                        let url = 'http://api.echeli.com:8080/upload' + slice + item.fileName;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList04.push(obj);
                    }else {
                        let url = 'http://ecl.oss-cn-hangzhou.aliyuncs.com/' + item.path;
                        let obj = new Object();
                        obj.url = url;
                        obj.name = item.fileName;
                        obj.rackNo = item.rackNo;
                        obj.id = item.vId;
                        than.fileList04.push(obj);
                    }
                }
            }
        })
        this.open = true;
        this.title = "修改car";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vId != null) {
            updateIndex(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIndex(this.form).then(response => {
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
      const vIds = row.vId || this.ids;
      this.$confirm('是否确认删除car编号为"' + vIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delIndex(vIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有car数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportIndex(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
