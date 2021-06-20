<template>
  <div class="app-container">
    <div style="font-size: 20px">
      当前有 <span style="background-color: #F2F6FC;font-size: 30px;color: #F56C6C">{{ statusNum.status01 }}</span> 个等待询价的工单,
      有 <span style="background-color: #F2F6FC;font-size: 30px;color: #F56C6C">{{ statusNum.status02 }}</span> 个正在询价的工单
    </div>
    <br/>
    <el-button type="primary" @click="handleSelect" :disabled="isAble">开始询价</el-button>

  <hr />
    <div v-if="showFlag">
    <div>
      <vxe-form :data="policy" title-align="right" title-width="100" prevent-submit title-colon>

        <vxe-form-item span="24" >
          <el-divider content-position="center"><h1 style="color: #5cacee">基本信息</h1></el-divider>
        </vxe-form-item>

        <vxe-form-item title="询价单号"  span="12" >
          <template #default>
            <span>{{policy.queryId}}</span>
          </template>
        </vxe-form-item>

        <vxe-form-item title="询价时间"  span="12"  :title-prefix="{ message: '请输入汉字！', icon: 'fa fa-exclamation-circle' }">
          <template #default>
            <span>{{policy.qcreateTime}}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="保险公司" field="sex" span="12" >
          <!--                        <vxe-select v-model="policy.channelId" placeholder="请选择保险公司" clearable>-->
          <!--                            <vxe-option value="1" label="女"></vxe-option>-->
          <!--                            <vxe-option value="2" label="男"></vxe-option>-->
          <!--                        </vxe-select>-->
          <template #default>
            <span>{{policy.channelId | companyFilter }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="代理人" span="12">
          <template #default>
            <span>{{policy.agentName}}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="代理人联系电话" span="12" >
          <template #default>
            <span>{{policy.agentTel}}</span>
          </template>
          <!--                        <vxe-select v-model="policy.agentTel" placeholder="代理人电话" clearable>-->
          <!--                            <vxe-option value="0" label="失败"></vxe-option>-->
          <!--                            <vxe-option value="1" label="成功"></vxe-option>-->
          <!--                        </vxe-select>-->
        </vxe-form-item>
        <vxe-form-item title="机构地区" span="12" >
          <template #default>
            <span>{{policy.orgId}}</span>
          </template>
        </vxe-form-item>

        <vxe-form-item span="24" >
          <template #default>
            <el-divider content-position="center"><h1 style="color: #5cacee">行驶证信息</h1></el-divider>
          </template>
        </vxe-form-item>

        <vxe-form-item title="车主" span="12">
          <template #default>
            <span>{{ policy.owner }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="车牌" field="date" span="12">
          <template #default>
            <span>{{ policy.vehicleNo }}</span>
          </template>
          <!--                        <vxe-input v-model="policy.vehicleNo" type="date" placeholder="请输入车牌" clearable></vxe-input>-->
        </vxe-form-item>
        <vxe-form-item title="车辆识别代码" field="single" span="12" >
          <!--                        <vxe-input v-model="policy.rackNo" type="date" placeholder="请输入车辆识别代码" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.rackNo}}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="品牌型号" field="flagList" span="12">
          <!--                    <vxe-input v-model="policy.etBrand" type="date" placeholder="请输入品牌型号" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.etBrand}}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="具体车型" field="flagList" span="12">
          <!--                    <vxe-input v-model="policy.brandCode" type="date" placeholder="请输入具体车型" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.brandCode}}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="所属性质" field="flagList" span="12">
          <!--                    <vxe-radio name="n1" v-model="policy.useType" label="00" content="个人" size="medium"></vxe-radio>-->
          <!--                    <vxe-radio name="n1" v-model="policy.useType" label="01" content="企业" size="medium"></vxe-radio>-->
          <template #default>
            <span>{{policy.useType | filterUseType }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="使用性质" field="flagList" span="12">
          <!--                        <vxe-radio name="n1" v-model="policy.belongType" label="00" content="营运" size="medium"></vxe-radio>-->
          <!--                        <vxe-radio name="n1" v-model="policy.belongType" label="01" content="非营运" size="medium"></vxe-radio>-->
          <template #default>
            <span>{{policy.belongType | filterBelongType }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="排气量" field="flagList" span="8">
          <!--                    <vxe-input v-model="policy.extMsr" type="date" placeholder="请输入排气量" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.extMsr }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="核定载人数" field="flagList" span="8">
          <!--                    <vxe-input v-model="policy.setNum" type="date" placeholder="请输入核定载人数" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.setNum }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="整车质量" field="flagList" span="8">
          <!--                    <vxe-input v-model="policy.tonnage" type="date" placeholder="请输入整车质量" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.tonnage }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="初登日期" field="flagList" span="8" >
          <!--                    <vxe-input v-model="policy.registerDate" type="date" placeholder="请输入初登日期" clearable></vxe-input>-->
          <template #default>
            <span>{{policy.registerDate }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="是否过户" field="flagList" span="8">
          <!--                    <vxe-radio name="n1" v-model="policy.changeOwnerFlag" label="0" content="否" size="medium"></vxe-radio>-->
          <!--                    <vxe-radio name="n1" v-model="policy.changeOwnerFlag" label="1" content="是" size="medium"></vxe-radio>-->
          <template #default>
            <span>{{policy.changeOwnerFlag | filterChangeOwner }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="过户时间" field="flagList" span="8">
          <!--                    <vxe-input v-model="policy.transferDate" type="date" placeholder="过户时间" clearable></vxe-input>-->
          <template #default>
            <span>{{ policy.transferDate }}</span>
          </template>
        </vxe-form-item>

        <vxe-form-item span="12" title="驾驶证正本">
          <template #default>

            <!--                            <el-avatar shape="square" :size="100" :src="policy.url01"></el-avatar>-->
            <div class="demo-image__preview">
              <el-image
                style="width: 250px; height: 250px"
                :src="policy.url03"
                :preview-src-list="srcList">
              </el-image>
            </div>

          </template>
        </vxe-form-item>
        <vxe-form-item span="12" title="驾驶证副本本">
          <template #default>

            <!--                            <el-avatar shape="square" :size="100"  :src="policy.url02"></el-avatar>-->

            <div class="demo-image__preview">
              <el-image
                style="width: 250px; height: 250px"
                :src="policy.url04"
                :preview-src-list="srcList">
              </el-image>
            </div>
          </template>
        </vxe-form-item>
        <!--                :fit="fit"-->
        <vxe-form-item span="24" :item-render="{}">
          <el-divider content-position="center"><h1 style="color: #5cacee">投保人信息</h1></el-divider>
        </vxe-form-item>

        <vxe-form-item title="投保人" field="flagList" span="8" >
          <!--                    <vxe-input v-model="policy.applicantName" type="date" placeholder="请输入投保人名称" clearable></vxe-input>-->
          <template #default>
            <span>{{ policy.applicantName }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="证件号码" field="flagList" span="8">
          <!--                    <vxe-input v-model="policy.applicantCertNo" type="date" placeholder="请输入核定载人数" clearable></vxe-input>-->
          <template #default>
            <span>{{ policy.applicantCertNo }}</span>
          </template>
        </vxe-form-item>

        <vxe-form-item title="投保人电话" field="flagList" span="8">
          <template #default>
            <span>{{ policy.applicantTel }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item span="12" title="身份证正面">
          <template #default>
            <div class="demo-image__preview">
              <el-image
                style="width: 250px; height: 250px"
                :src="policy.url01"
                :preview-src-list="srcList">
              </el-image>
            </div>
          </template>
        </vxe-form-item>

        <vxe-form-item span="12" tilte="身份证背面">
          <template #default>
            <div class="demo-image__preview">
              <el-image
                style="width: 250px; height: 250px"
                :src="policy.url02"
                :preview-src-list="srcList">
              </el-image>
            </div>
          </template>
        </vxe-form-item>
        <vxe-form-item span="24" :item-render="{}">
          <el-divider content-position="center"><h1 style="color: #5cacee">保险产品</h1></el-divider>
        </vxe-form-item>

        <vxe-form-item title="代理人" field="flagList" span="12">
          <!--                    <vxe-input v-model="policy.applicantCertNo" type="date" placeholder="请输入核定载人数" clearable></vxe-input>-->
          <template #default>
            <span>{{ policy.agentName }}</span>
          </template>
        </vxe-form-item>
        <vxe-form-item title="代理人电话" field="flagList" span="12">
          <!--                    <vxe-input v-model="policy.applicantCertNo" type="date" placeholder="请输入核定载人数" clearable></vxe-input>-->
          <template #default>
            <span>{{ policy.agentTel }}</span>
          </template>
        </vxe-form-item>
      </vxe-form>
    </div>

    <!--        :current-page.sync="currentPage1"-->
    <div>
      <vxe-toolbar>
        <template #buttons>
          <vxe-button icon="fa fa-plus" @click="insertEvent()">新增</vxe-button>
        </template>
      </vxe-toolbar>
      <vxe-table
        border
        resizable
        show-overflow
        keep-source
        ref="xTable"
        :data="tableData"
        :edit-config="{trigger: 'manual', mode: 'row'}">
        <vxe-table-column type="checkbox" width="60"></vxe-table-column>
        <!--        <vxe-table-column field="queryId" title="询价Id"></vxe-table-column>-->
        <vxe-table-column field="insrncCode" title="险种" width="180" :edit-render="{name: '$select', options: codeList}"></vxe-table-column>
        <!--        <vxe-table-column field="glassType" title="玻璃类型" :edit-render="{name: '$select', options: glassList}"></vxe-table-column>-->
        <vxe-table-column field="amount" title="保险金额/赔偿限额(元）" width="180" :edit-render="{name: 'input', attrs: {type: 'text'}}">
          <template slot-scope="scope" >
            <div v-if="scope.row.amount == 0">
              对应保险公司默认价格
            </div>
            <div v-else>
              {{ scope.row.amount }}
            </div>
          </template>
        </vxe-table-column>
        <!--        <vxe-table-column field="franchiseFlag" title="不计免赔" :edit-render="{name: '$select', options: flgList}"></vxe-table-column>-->
        <!--        <vxe-table-column field="beforePremium" title="之前保费"></vxe-table-column>-->
        <vxe-table-column field="premium" title="保费" width="160" :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="nonDeductPremium" title="不扣保险费" width="120" :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="deductPremium" title="扣除保险费" width="120" :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
        <vxe-table-column field="premium" title="保费小计" width="120" :edit-render="{name: 'input', attrs: {type: 'number'}}"></vxe-table-column>
        <!--        <vxe-table-column field="unitAmount" title="单位金额" :edit-render="{name: 'input', attrs: {type: 'number'}}" ></vxe-table-column>-->
        <vxe-table-column title="操作" width="160">
          <template #default="{ row }">
            <template v-if="$refs.xTable.isActiveByRow(row)">
              <vxe-button @click="saveRowEvent(row)">保存</vxe-button>
              <vxe-button @click="cancelRowEvent(row)">取消</vxe-button>
            </template>
            <template v-else>
              <vxe-button @click="editRowEvent(row)">编辑</vxe-button>
            </template>
          </template>
        </vxe-table-column>
      </vxe-table>
    </div>

    <div>
      <br/>
      <br/>
      <el-form :label-position="labelPosition" :model="policy" label-width="80px">
        <el-form-item label="保费小计">
          <el-input v-model="policy.premium" @click.native="handleClickPremium"></el-input>
        </el-form-item>
        <el-form-item label="交强险保费">
          <el-input v-model="policy.premiumJq" @click.native="handleClickPremiumJq"></el-input>
        </el-form-item>

        <el-form-item label="车船税">
          <el-input v-model="policy.tax"></el-input>
        </el-form-item>

        <el-form-item label="返回意见">
          <el-input type="textarea" v-model="policy.content"></el-input>
        </el-form-item>

        <el-form-item label="备注">
          <el-input type="textarea" v-model="policy.remark" ></el-input>
        </el-form-item>
        <div>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">保存</el-button>
            <el-button type="primary" @click="updateQuery">驳回</el-button>
            <el-button @click="$router.back(-1)">返回</el-button>
          </el-form-item>
        </div>

      </el-form>
    </div>

  </div>
  </div>
</template>

<script>
 import { getSize,listQuery,updateListStatus,updateQuery } from "@/api/policy/query";
 import { updateInsrnc,addInsrnc } from '@/api/policy/insrnc'

export default {
  name: "QueryStar",
  components: {
  },
  filters:{
      filterUseType(val){
          let useType = {
              "00": "个人",
              "01": "企业",
              "101": "家庭自用车",
              "102": "企业",
              "103": "党政机关，事业团体",
              "199": "其他非营业车辆",
              "201": "出租客车",
              "202": "城市公交",
              "203": "公路客运",
              "204": "旅游客运",
              "205": "租赁客车",
              "206": "营业货车",
              "211": "其他营业车辆"

          }
          return useType[val];
      },
      companyFilter(val){
          let company = {
              "00": "华安保险",
              "01": "中华保险",
              "02": "太平洋保险",
              "03": "平安保险",
              "04": "人保保险",
              "05": "诚泰保险",
              "06": "人寿保险",
              "07": "大地保险",
              "08": "长安保险",
              "09": "平安众安联合车险",
              "10": "国任财险",
              "11": "阳光保险",
              "12": "天安财险",
              "13": "太平财险",
              "14": "紫金财险",
              "15": "亚太财险",
              "16": "天平保险",
              "17": "永诚保险",
              "18": "华泰保险",
              "19": "永安保险",
              "20": "大家保险",
              "21": "中银保险",
              "22": "都邦保险",
              "23": "安城保险",
              "24": "渤海保险",
              "1": "平安",
              "2": "太平洋",
              "31": "人保",
              "57": "中国人寿",
              "54": "亚太"
          }
          return company[val];
      },
      filterChangeOwner(val){
          let changeOwner = {
              "0":"是",
              "1":"否"
          }
          return changeOwner[val]
      },
      filterBelongType(val){
          let changeUseType = {
              "00":"非营运",
              "01":"营运"
          }
          return changeUseType[val]
      },

  },
  data() {
    return {
      srcList: [],

      policy: {
          // premium:null,
          // premiumJq:null,
          // tax:null,
          // content:null,
          // remark:null,
      },
      tableData: [
      ],
      flgList: [
          { label: '', value: '' },
          { label: '0', value: '否'},
          { label: '1', value: '是'},
      ],
      glassList: [
          { label: '', value: ''},
          { label: '国产', value: '00'},
          { label: '进口', value: '01'},
      ],
      from:{},
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
      showFlag: false,
      // 总条数
      total: 0,
      // 保险询价表格数据
      queryStarList: [],
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
      queryInsrnc:[],


      codeList: [
          { label: '', value: '' },
          { label: '交强险', value: '000' },
          { label: '第三者责任险', value: '001' },
          { label: '司机责任险', value: '002' },
          { label: '乘客责任险', value: '003' },
          { label: '盗抢险', value: '004' },
          { label: '车上货物险', value: '005' },
          { label: '精神损害抚慰金责任险', value: '006' },
          { label: '车损险', value: '007' },
          { label: '玻璃险', value: '008' },
          { label: '自燃险', value: '009' },
          { label: '划痕线', value: '010' },
          { label: '涉水险', value: '011' },
          { label: '无法找到第三方险', value: '012' },
          { label: '指定专修险', value: '013' },
          { label: '驾意险', value: '100' },
          { label: '机动车损失险', value: 'a100' },
          { label: '第三方责任险', value: 'a101' },
          { label: '司机责任险', value: 'a102' },
          { label: '乘客责任险', value: 'a103' },
          { label: '绝对免赔率(车损)', value: 'a104' },
          { label: '绝对免赔率(三者)', value: 'a105' },
          { label: '绝对免赔率(司机)', value: 'a106' },
          { label: '绝对免赔率(乘客)', value: 'a107' },
          { label: '车轮损失险', value: 'a108' },
          { label: '车身划痕险', value: 'a109' },
          { label: '修理补偿险', value: 'a110' },
          { label: '发动机损坏除外特约', value: 'a111' },
          { label: '三者险节假日翻倍险', value: 'a112' },
          { label: '精神抚慰金险(三者)', value: 'a113' },
          { label: '精神抚慰金险(司机)', value: 'a114' },
          { label: '精神抚慰金险(乘客)', value: 'a115' },
          { label: '医保外用药险(三者)', value: 'a116' },
          { label: '医保外用药险(司机)', value: 'a117' },
          { label: '医保外用药险(乘客)', value: 'a118' },
          { label: '道路救援险', value: 'a119' },
          { label: '车辆安全检测', value: 'a120' },
          { label: '代为驾驶服务', value: 'a121' },
          { label: '代为送检服务', value: 'a122' },
      ],
      //返回status 00 01 数量
      statusNum:{
        status01:0,
        status02:0
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      isAble: false,
      isRefresh: false,
      showFlag: false,
    };
  },
  created() {
    this.getListSize();
  },
  methods: {
      //交强险赋值
      handleClickPremiumJq(){
          if(!this.policy.queryInsrnc){
              this.policy.premiumJq = '0';
          }else {
              let mun = 0;
              for(let i = 0;i< this.policy.queryInsrnc.length;i++){
                  if(this.policy.queryInsrnc[i].insrncCode === '000'){
                      this.policy.premiumJq = this.policy.queryInsrnc[i].premium
                  }
              }

          }
      },
      //保费小计
      handleClickPremium(){

          if(!this.policy.queryInsrnc){
              this.policy.premium = '0';
          }else {
              let mun = 0;
              let munSy = 0;
              for(let i = 0;i< this.policy.queryInsrnc.length;i++){
                  if(this.policy.queryInsrnc[i]){
                      mun += Number(this.policy.queryInsrnc[i].premium)
                      if(this.policy.queryInsrnc[i].insrncCode !== '000'){
                          munSy += Number(this.policy.queryInsrnc[i].premium)
                      }
                  }
              }

              this.policy.premium = mun;
              this.policy.premiumSy = munSy;

          }
      },
      //保存询价信息
      onSubmit(){
          debugger
          console.log('this.policy',this.policy)
          this.policy.status = '02';
          updateQuery(this.policy).then(res => {
              if(res.code == 200){
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  });
                  this.getListSize();
              }else {
                  this.$message.error('修改失败');
              }
          })
      },
      //保存询价信息
      updateQuery(){
          debugger
          console.log('this.policy',this.policy)
          this.policy.status = '10';
          updateQuery(this.policy).then(res => {
              if(res.code == 200){
                  this.$message({
                      message: '修改成功',
                      type: 'success'
                  });
                  this.getListSize();
              }else {
                  this.$message.error('修改失败');
              }
          })
      },
      handleSelect(){
          this.updateList();
      },
      //查询询价信息status 00 or 01
      getListSize() {
        this.loading = true;
        getSize().then(res => {
          this.statusNum.status01 = res.data.zero;
          this.statusNum.status02 = res.data.one;
          if(this.statusNum.status01 === 0){
              this.isAble = true;
          }
        });
      },

    //批量修改数据状态
    updateList(){
        this.showFlag = true;
        this.loading = true;
        updateListStatus().then(res => {
            if(res.code == 200){
                this.policy = res.data;
                this.srcList.push(res.data.url01);
                this.srcList.push(res.data.url02);
                this.srcList.push(res.data.url03);
                this.srcList.push(res.data.url04);
                this.tableData = res.data.queryInsrnc;
            }else {
                this.$message.error('获取信息失败');
            }

        })
    },
    editRowEvent (row) {
        this.$refs.xTable.setActiveRow(row)
    },
    //行内保存
    saveRowEvent (row) {
        // console.log("修改数据",row)
        this.$refs.xTable.clearActived().then(() => {
            this.loading = true
            //修改
            if(row.qId){
                updateInsrnc(row).then(res => {
                    if(res.code == 200){
                        this.$message({
                            type:"success",
                            message: "修改成功"
                        })
                    }else {
                        this.$message.error("修改失败")
                    }
                })
                //保存
            }else {
                addInsrnc(row).then(res => {

                })

            }

        })
    },
    //行内取消
    cancelRowEvent (row) {
        console.log("取消数九",row)
        const xTable = this.$refs.xTable
        xTable.clearActived().then(() => {
            // 还原行数据
            xTable.revertData(row)
        })
    },
      /**新增
       * */
    async insertEvent (row) {
        debugger
        const $table = this.$refs.xTable
        const record = {
            // queryId: this.queryInsrnc[0] != null ? this.queryInsrnc[0].queryId : new Date().getTime() + (Math.random() + 1) * Math.pow(10,4) ,
            queryId: this.policy.queryId,
        }
        const { row: newRow } = await $table.insertAt(record, row)
        await $table.setActiveCell(newRow, 'sex')
    },
    // async insertEvent (row) {
    //     let record = {
    //
    //         insrncCode: '',
    //         glassType: '',
    //         amount: '',
    //         franchiseFlag: '',
    //         beforePremium: '',
    //         premium: '',
    //         nonDeductPremium: '',
    //         deductPremium: '',
    //         quantity: '',
    //         unitAmount: ''
    //     }
    //     let { row: newRow } = await this.$refs.xTable.insertAt(record, row)
    //     await this.$refs.xTable.setActiveCell(newRow, 'sex')
    // },
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
      this.title = "添加保险询价";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const qId = row.qId || this.ids
      getQueryStar(qId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改保险询价";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qId != null) {
            updateQueryStar(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQueryStar(this.form).then(response => {
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
      this.$confirm('是否确认删除保险询价编号为"' + qIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQueryStar(qIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有保险询价数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportQueryStar(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
