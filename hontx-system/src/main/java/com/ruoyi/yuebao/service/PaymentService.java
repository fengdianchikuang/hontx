package com.ruoyi.yuebao.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.InsrncOrder.domain.vo.InsrncOrderVo;
import com.ruoyi.InsrncOrder.service.InsrncOrderService;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.company.domain.TbInsrncCpy;
import com.ruoyi.company.service.ITbInsrncCpyService;
import com.ruoyi.income.domain.TbIncome;
import com.ruoyi.income.mapper.TbIncomeMapper;
import com.ruoyi.income.service.ITbIncomeService;
import com.ruoyi.insureInfo.domain.InsureInfo;
import com.ruoyi.insureInfo.service.InsureInfoService;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.service.ITbOrganizationService;
import com.ruoyi.rate.domain.TbInsrncCpyRate;
import com.ruoyi.rate.mapper.TbInsrncCpyRateMapper;
import com.ruoyi.rate.service.ITbInsrncCpyRateService;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.service.ITbQueryService;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.service.ITbUserInfoService;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.service.ITbVehicleService;
import com.ruoyi.yuebao.domain.req.PaymentRequest;
import com.ruoyi.yuebao.domain.req.RenewalReq;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Handshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentService {

    @Value("${ybinsure.url}")
    private String url;

    @Autowired
    private InsureInfoService insureInfoService;

    @Autowired
    private InsrncOrderService insrncOrderService;

    @Autowired
    private ITbQueryService queryService;

    @Autowired
    private RenewalService renewalService;

    @Autowired
    private ITbUserInfoService userInfoService;

    @Autowired
    private ITbInsrncCpyService insrncCpyService;

    @Autowired
    private ITbVehicleService vehicleService;

    @Autowired
    private ITbOrganizationService organizationService;

//    @Autowired
//    private TbInsrncCpyRateMapper insrncCpyRateMapper;
//
//    @Autowired
//    private TbIncomeMapper tbIncomeMapper;

    private ITbIncomeService incomeService;
    /**
     * 申请支付
     * @param paymentRequest
     * @return
     */
    public String payment(PaymentRequest paymentRequest) {
        String orderId = paymentRequest.getOrderId();
        //查询支付状态
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("orderId", orderId);
        String payResult = null;
        try {
            payResult = OkHttpUtils.postFrom(url + "/queryPolicyInfo", formBody, LoginService.getToken());
        }catch (SocketTimeoutException e) {
            throw new CustomException("连接超时,请稍后尝试重新请求");
        }
        catch (IOException e) {
            throw new CustomException("登录异常");
        }
        JSONObject queryPolicyInfo = JSONObject.parseObject(payResult).getJSONObject("data");
        if("6".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",1);
            message.put("message","此单已支付,请等待核保通过");
            //更改核保状态
            updateInsureInfoStatus(orderId,6);

            return message.toString();
        }
        if("7".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",1);
            message.put("message","此单承保失败,请联系客服");
            //更改核保状态
            updateInsureInfoStatus(orderId,7);
            return message.toJSONString();
        }
        if("8".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",200);
            message.put("message","恭喜你,此单已核保成功");
            InsureInfo insureInfo = insureInfoService.findByOrderId(orderId);
            log.info("核保成功信息,订单号:{},车架号:{},车主Id:{}",insureInfo.getOrderId(),insureInfo.getFrameNo(),insureInfo.getOwnerId());
            if(insureInfo.getPayStatus() ==2 ){
                return message.toString();
            }
            //查找订单表
            InsrncOrderVo insrncOrder = insrncOrderService.findByOrderId(orderId);
            if(insrncOrder == null){
                return null;
            }
            insrncOrder.setPolicyStatus(8);
            insrncOrderService.updateTbInsrncOrder(insrncOrder);
            //更改询价表 保单状态
            TbQuery tbQuery = queryService.findByQueryId(orderId);
            tbQuery.setStatus("5");
            queryService.updateTbQuery(tbQuery);
            //更新核保信息表
            updateInsureInfoStatus(orderId,8);
            //重新刷新续保信息
            RenewalReq renewalReq = new RenewalReq();
            renewalReq.setOwnerId(insureInfo.getOwnerId());
            renewalReq.setFrameNo(insureInfo.getFrameNo());
            renewalReq.setStatus(1); //刷新续保
            renewalReq.setCardLastNo("261076"); //身份证后6位
            try {
                renewalService.getRenewal(renewalReq);
            } catch (Exception e) {
                message.put("message","续保更新失败,请重新请求");
            }
            //计算佣金收入
            //计算佣金收入
//            incomeUpdate(insureInfo.getOwnerId(),insureInfo.getFrameNo(),insureInfo.getCompanyId(),queryPolicyInfo,insureInfo.getOrderId(),tbQuery);
            incomeService.calculationIncome(tbQuery);
            //根据车架 把保单状态不是8的删除
            insrncOrderService.deleteByFrameNoAndNotStatus(insureInfo.getFrameNo(),8);
            insureInfoService.deleteByFrameNoAndNotStatus(insureInfo.getFrameNo(),8);
        }

        //以上状态都不是，申请支付，将申请支付写入insureINfo中
        InsureInfo insureInfo = insureInfoService.findByOrderId(paymentRequest.getOrderId());
        insureInfo.setPayStatus(1); //已申请支付
        insureInfoService.updateInsureInfo(insureInfo);

        FormBody.Builder form = new FormBody.Builder();
        form.add("orderId",paymentRequest.getOrderId());
        try {
            String result = OkHttpUtils.postFrom(url + "/payment", form, LoginService.getToken());
            log.info("申请支付返回结果:{}",result);
            return result;

        } catch (IOException e) {
            throw new CustomException("超时请稍后再试");
        }

    }
    //计算佣金收入
//    private void incomeUpdate(Long ownerId, String frameNo, Integer companyId, JSONObject queryPolicyInfo, String orderId,TbQuery tbQuery) {
//        log.info("计算保费保险公司:{},保单信息{}",companyId,queryPolicyInfo.toJSONString());
//        TbUserInfoVo userInfo = userInfoService.findUserDetailByOwnerId(ownerId + "");
//        //TODO （因不知道商城接口）暂时只支持实名用户购买
//        if(!"03".equals(userInfo.getURealNameStatus())){
//            //TODO u_parent_id 为空，所有佣金暂定为平台
//            if("0".equals(userInfo.getUParentId()) || userInfo.getUParentId() == null || "".equals(userInfo.getUParentId())){
//                return;
//            }else {
//                userInfo = userInfoService.selectTbUserInfoById(Integer.parseInt(userInfo.getUParentId()));
//            }
//        }
//        //查找订单表
//        InsrncOrderVo insrncOrderVo = insrncOrderService.findByOrderId(orderId);
//        TbUserInfoVo aUserInfo = null;
//        TbUserInfoVo bUserInfo = null;
//        String aId = userInfo.getUParentId();
//        String bId = "0";
//        String aTeam = "平台";
//        String bTeam = "平台";
//
//        //平台为0
//        if(StringUtils.isNotEmpty(aId) && !"0".equals(aId)){
//            aUserInfo = userInfoService.selectTbUserInfoById(Integer.parseInt(aId));
//            aTeam = aUserInfo.getURealName();
//            bId = aUserInfo.getUParentId();
//            if(bId.equals("0")){
//                bId = "0";
//            }
//            if(StringUtils.isNotEmpty(bId) && !"0".equals(bId)){
//                bUserInfo = userInfoService.selectTbUserInfoById(Integer.parseInt(bId));
//            }
//        }
//
//        //通过companyId查找保险公司代号
//        TbInsrncCpy tbInsrncCpy = insrncCpyService.findByCompanyId(companyId);
//        //交强险保费
//        BigDecimal tPremium = new BigDecimal("0");
//        //商业险保费
//        BigDecimal vPremium = new BigDecimal("0");
//
//
//        if(insrncOrderVo.getTciPremium() != 0){
//            tPremium = tPremium.add(new BigDecimal(insrncOrderVo.getTciPremium()+""));
//        }
//        if(insrncOrderVo.getVciPremium() != 0){
//            vPremium = vPremium.add(new BigDecimal(insrncOrderVo.getVciPremium()+""));
//        }
//        //查找车辆表
//        TbVehicle vehicle = null;
//        List<TbVehicle> vehicles = vehicleService.findVehicleByLicenseNoAndFrameNo(null, frameNo);
//        if(vehicles != null && vehicles.size() > 0){
//            vehicles  = vehicles.stream().filter(o -> o.getOwner() == null ).collect(Collectors.toList());
//            if(vehicles != null && vehicles.size() > 0){
//                vehicle = vehicles.get(0);
//            }
//        }
//        LocalDate now = LocalDate.now();
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        TbIncome incomeJq = new TbIncome();
//        TbIncome incomeSy = new TbIncome();
//        //有交强险
//        if(insrncOrderVo.getTciPremium() > 0L){
//            //查找交强险费率
//            Map<String,Object> rateParam = new HashMap<>();
//            rateParam.put("channelId",tbInsrncCpy.getChannelId());
//            rateParam.put("insrncType","01");//交强险
//            if(!"100".equals(vehicle.getUseType())){
//                rateParam.put("useType","01"); //运营
//
//            }else {
//                rateParam.put("useType","00"); //非运营
//            }
//            //按照当天日期进行收益计算
//            rateParam.put("effectDate", df.format(now));
//            //TODO 找不到对应类型，暂时都默认为
//            rateParam.put("belongType","00"); //默认为个人
//            //TODO areaCode 区域码暂时不能确定，默认不找
//            //查找机构
//            if(StringUtils.isNotEmpty(vehicle.getVehicleNo())){
//                rateParam.put("vehicleType","01"); //旧车
//            }else {
//                rateParam.put("vehicleType","00"); //新车
//            }
//            rateParam.put("orgId",userInfo.getUOrgId());
//
//
//            List<TbInsrncCpyRate> tbInsrncCpyRates = insrncCpyRateMapper.selectTbInsrncCpyRateListByMap(rateParam);
//            if(tbInsrncCpyRates != null && tbInsrncCpyRates.size() > 0) {
//                TbInsrncCpyRate rate = tbInsrncCpyRates.get(0);
//                incomeJq.setAgentId(userInfo.getUId()+"");
//                incomeJq.setAgentName(userInfo.getURealName());
//                incomeJq.setInsuredName(tbQuery.getInsuredName());
//                incomeJq.setInsuredCertNo(tbQuery.getInsuredCertNo());
//                incomeJq.setInsrncBgnTm(tbQuery.getInsrncBgnTm());
//                incomeJq.setInsrncEndTm(tbQuery.getInsrncEndTm());
//                incomeJq.setQueryServiceId(tbQuery.getServiceId());
//                incomeJq.setQueryServiceName("");
//                incomeJq.setCheckServiceId("");
//                incomeJq.setCheckServiceName("");
//                incomeJq.setTotalRate(new BigDecimal(rate.getTotalRate()));
//                incomeJq.setTotalIncome(tPremium.multiply(incomeJq.getTotalRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
//                incomeJq.setAgentRate(new BigDecimal(rate.getAgentRate()));
//                incomeJq.setAgentIncome(tPremium.multiply(incomeJq.getAgentRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
//
//                BigDecimal pRate = new BigDecimal("0");
//                pRate = new BigDecimal(rate.getTotalRate())
//                        .subtract(new BigDecimal(rate.getTotalRate()))
//                        .subtract(new BigDecimal(rate.getaRate()))
//                        .subtract(new BigDecimal(rate.getbRate()));
//                //没有上级
//                if(StringUtils.isEmpty(userInfo.getUParentId()) || "0".equals(userInfo.getUParentId())){
//                    pRate = new BigDecimal(rate.getaRate()).add(new BigDecimal(rate.getbRate())).add(pRate);
//                }else {
//                    if(StringUtils.isEmpty(bId) || "0".equals(bId)){
//                        //只有一个上级
//                        pRate = new BigDecimal(rate.getbRate()).add(pRate);
//                    }
//                }
//                incomeJq.setpRate(pRate);
//                incomeJq.setpIncome(tPremium.multiply(pRate).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
//                tbIncomeMapper.insertTbIncome(incomeJq);
//
//            }
//            }
//
//        incomeJq.setAgentIncome(new BigDecimal("0"));
//        incomeJq.setaIncome(new BigDecimal("0"));
//        incomeJq.setbIncome(new BigDecimal("0"));
//        if(insrncOrderVo.getVciPremium() > 0L){
//            incomeSy.setAppNo(tbQuery.getSyAppNo());
//            incomeSy.setAgentName(userInfo.getURealName());
//            incomeSy.setaTeamId(aId);
//            incomeSy.setbTeamId(bId);
//            incomeSy.setaTeam(aTeam);
//            incomeSy.setbTeam(bTeam);
//            incomeSy.setCheckDate(new Date());
//            incomeSy.setiCreateTime(new Date());
//            incomeSy.setPremium(vPremium);
//            incomeSy.setVehicleNo(tbQuery.getVehicleNo());
//            incomeSy.setRackNo(tbQuery.getRackNo());
//
//            incomeSy.setRegisterDate(tbQuery.getRegisterDate());
//            incomeSy.setBelongType(tbQuery.getBelongType());
//
//            incomeSy.setQueryId(tbQuery.getQueryId());
//            incomeSy.setChannelId(tbQuery.getChannelId());
//            incomeSy.setInsrncType("00"); //商业险
//            if(StringUtils.isNotEmpty(vehicle.getVehicleNo())){
//                incomeSy.setVehicleType("01");
//            }else {
//                incomeSy.setVehicleType("00");
//            }
//            incomeSy.setInsuredName(tbQuery.getInsuredName());
//            incomeSy.setInsuredCertNo(tbQuery.getInsuredCertNo());
//            incomeSy.setInsrncBgnTm(tbQuery.getInsrncBgnTm());
//            incomeSy.setInsrncEndTm(tbQuery.getInsrncEndTm());
//            incomeSy.setQueryServiceId(tbQuery.getQueryId());
//            incomeSy.setQueryServiceName("");
//            incomeSy.setCheckServiceId("");
//            incomeSy.setCheckServiceName("");
//
//            //查询商业险费率
//            Map<String,Object> rateParam = new HashMap<>();
//            rateParam.put("channelId",tbQuery.getChannelId());
//            rateParam.put("insrncType",incomeSy.getInsrncType());
//            rateParam.put("belongType","00"); //默认为个人
//            if(!"100".equals(vehicle.getUseType())){
//                rateParam.put("useType","01"); //运营
//
//            }else {
//                rateParam.put("useType","00"); //非运营
//            }
//            rateParam.put("vehicleType", incomeSy.getVehicleType());
//            LocalDate time = LocalDate.now();
//            String localTime = df.format(time);
//
//            rateParam.put("effectDate", localTime);
//            rateParam.put("areaCode", tbQuery.getAreaCode());
//            rateParam.put("orgId",userInfo.getUOrgId());
//            List<TbInsrncCpyRate> vRates = insrncCpyRateMapper.selectTbInsrncCpyRateListByMap(rateParam);
//
//        }
//
//        }




    //更改核保状态
    private void updateInsureInfoStatus(String orderId, int policyStatus) {
        InsureInfo insureInfo = insureInfoService.findByOrderId(orderId);
        insureInfo.setPolicyStatus(policyStatus);
        insureInfo.setPayStatus(2); //已申请支付
        insureInfoService.updateInsureInfo(insureInfo);
    }
}
