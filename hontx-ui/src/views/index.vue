<template>
  <div class="app-container-style">
    <el-row>
      <el-col :span="6">
        <div class="tebale_card">
          <br/>
          <br/>
          <span class="font-left-style">今日新增车主</span><br/><br/>
          <el-row>
            <el-col :span="12">
              <span class="font-left-style">{{ indexData.vehicleAdd }}</span>
            </el-col>
            <el-col :span="12">
              <div v-if="indexData.vehiclePercentage < 1">
                 <span class="d5"/> <span class="font-right-style">-{{indexData.vehiclePercentage}}% </span>
              </div>
              <div v-else>
                <span class="d4"/> <span class="font-right-style">+{{indexData.vehiclePercentage}}% </span>
              </div>
            </el-col>
          </el-row>
          <br/><br/>
          <span class="font-left-style">总车主数</span><br/><br/><br/>
          <span class="font-left-style">{{ indexData.vehicleTotal }}</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="tebale_red">
          <br/>
          <br/>
          <!--          <blockquote class="text-in" style="font-size: 25px;height: 300px">-->
          <span class="font-left-style">今日新增代理</span><br/><br/>
          <el-row>
            <el-col :span="12">
              <span class="font-left-style">{{ indexData.userAdd }}</span>
            </el-col>
            <el-col :span="12">
              <div v-if="indexData.userPercentage < 1">
                <span class="d5"/> <span class="font-right-style">-{{indexData.userPercentage}}% </span>
              </div>
              <div v-else>
                <span class="d4"/> <span class="font-right-style">+{{indexData.userPercentage}}% </span>
              </div>
            </el-col>
          </el-row>
          <br/><br/>
          <span class="font-left-style">总代理数</span><br/><br/><br/>
          <span class="font-left-style">{{ indexData.userTotal }}</span>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="tebale_purple">
          <br/>
          <br/>
          <!--          <blockquote class="text-in" style="font-size: 25px;height: 300px">-->
          <span class="font-left-style">车险今日业绩</span><br/><br/>
          <el-row>
            <el-col :span="12">
              <span class="font-left-style">{{ indexData.incomeAdd }}</span>
            </el-col>
            <el-col :span="12">
              <div v-if="indexData.incomePercentage < 1">
                <span class="d5"/> <span class="font-right-style">-{{indexData.incomePercentage}}% </span>
              </div>
              <div v-else>
                <span class="d4"/> <span class="font-right-style">+{{indexData.incomePercentage}}% </span>
              </div>
            </el-col>
          </el-row>
          <br/><br/>
          <span class="font-left-style">车险总业务</span><br/><br/><br/>
          <span class="font-left-style">{{ indexData.incomeTotal }}</span>
          <br />
        </div>
      </el-col>

      <el-col :span="6">
        <div class="tebale_yellow">
          <br/>
          <br/>
          <!--          <blockquote class="text-in" style="font-size: 25px;height: 300px">-->
          <span class="font-left-style">寿险今日业绩</span><br/><br/>
          <el-row>
            <el-col :span="12">
              <span class="font-left-style">{{ indexData.lifeAdd }}</span>
            </el-col>
            <el-col :span="12">
              <div v-if="indexData.lifePercentage < 1">
                <span class="d5"/> <span class="font-right-style">-{{indexData.lifePercentage}}% </span>
              </div>
              <div v-else>
                <span class="d4"/> <span class="font-right-style">+{{indexData.lifeTotal}}% </span>
              </div>
            </el-col>
          </el-row>
          <br/><br/>
          <span class="font-left-style">寿险总业绩</span><br/><br/><br/>
          <span class="font-left-style">{{ indexData.lifeTotal }}</span>
          <br />
        </div>
      </el-col>
    </el-row>
    <br/>


    <el-col :span="24">
      <div class="box_block_color">
        <br/>
        <span style="font-size: 20px;font-weight: 700;margin-left: 50px">&nbsp;商城收入概览</span><br/><br/>
        <br/>
        <br/>
        <el-row>
          <el-col :span="12">
            <div style="margin-left: 400px">
              <i style="width:15px;height:15px;border-radius:50%;background-color:#9F35ff;display: inline-block"></i>
              <span style="margin-left: 10px">积分收入(车险结算)</span><br/><br/><br/>
              <span class="font-down-style">{{indexData.lifeAdd}}</span><br/><br/><br/>
              <span style="margin-left: 20px">环比</span><span style="margin-left: 60px">{{indexData.integralPercentage}}%</span>
            </div>
          </el-col>
          <el-col :span="12">
            <div>
              <i style="width:15px;height:15px;border-radius:50%;background-color:#67C23A;display: inline-block"></i>
              <span style="margin-left: 10px">代金券购买(微信支付)</span><br/><br/><br/>
              <span class="font-down-style">{{indexData.shopAdd}}</span><br/><br/><br/>
              <span style="margin-left: 20px">环比</span><span style="margin-left: 60px">{{indexData.shopPercentage}}%</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-col>
  </div>
</template>

<script>
import { getIndex } from "@/api/login";

export default {
  name: "index",
  data() {
    return {
      // 版本号
      version: "3.4.0",
      indexData: {}
    };
  },
  created(){
    this.getList();
  },
  methods: {
    getList(){
      getIndex().then(res => {
          this.indexData = res.data;
      })
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.font-down-style {
  margin-left: 15px;
  font-family: 新宋体;
  color: black;
  font-size: 60px;
  font-weight: 900;
}
.d5{
  margin-top: 0px;
  margin-left: 10px;
  float: left;
  width: 0;
  height: 0;
  border-width: 12px;
  border-style: solid;
  border-color: transparent green transparent transparent;
  transform: rotate(270deg); /*顺时针旋转90°*/
}
.d4{
  margin-top: 0px;
  margin-left: 10px;
  float: left;
  width: 0;
  height: 0;
  border-width: 12px;
  border-style: solid;
  border-color: transparent red transparent transparent;
  transform: rotate(90deg); /*顺时针旋转90°*/
}
.font-right-style {
  margin-left: 15px;
  font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
  color: #FFFFFF;
  font-size: 15px;
  font-weight: 400;
}
.font-left-style {
  margin-left: 25px;
  font-family: "宋体";
  color: #FFFFFF;
  font-size: 20px;
  font-weight: 700;
}
.app-container-style {
  background-color: #FEF0F0;
}
.tebale_card {
  background-color: #67C23A;
  height: 300px;
  width: 350px;
}
.tebale_red {
  background-color: #F56C6C;
  height: 300px;
  width: 350px;
}
.tebale_purple {

  background-color: #9F35ff;
  height: 300px;
  width: 350px;
}
.tebale_yellow {
  background-color: #E6A23C;
  height: 300px;
  width: 350px;
}
.box_block_color {
  background-color: #E1f3d8;
  height: 500px;
}

</style>

