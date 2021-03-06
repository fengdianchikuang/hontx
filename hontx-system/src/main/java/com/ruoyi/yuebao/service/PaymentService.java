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
     * ????????????
     * @param paymentRequest
     * @return
     */
    public String payment(PaymentRequest paymentRequest) {
        String orderId = paymentRequest.getOrderId();
        //??????????????????
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("orderId", orderId);
        String payResult = null;
        try {
            payResult = OkHttpUtils.postFrom(url + "/queryPolicyInfo", formBody, LoginService.getToken());
        }catch (SocketTimeoutException e) {
            throw new CustomException("????????????,???????????????????????????");
        }
        catch (IOException e) {
            throw new CustomException("????????????");
        }
        JSONObject queryPolicyInfo = JSONObject.parseObject(payResult).getJSONObject("data");
        if("6".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",1);
            message.put("message","???????????????,?????????????????????");
            //??????????????????
            updateInsureInfoStatus(orderId,6);

            return message.toString();
        }
        if("7".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",1);
            message.put("message","??????????????????,???????????????");
            //??????????????????
            updateInsureInfoStatus(orderId,7);
            return message.toJSONString();
        }
        if("8".equals(queryPolicyInfo.getString("policyStatus"))){
            JSONObject message = new JSONObject();
            message.put("status",200);
            message.put("message","?????????,?????????????????????");
            InsureInfo insureInfo = insureInfoService.findByOrderId(orderId);
            log.info("??????????????????,?????????:{},?????????:{},??????Id:{}",insureInfo.getOrderId(),insureInfo.getFrameNo(),insureInfo.getOwnerId());
            if(insureInfo.getPayStatus() ==2 ){
                return message.toString();
            }
            //???????????????
            InsrncOrderVo insrncOrder = insrncOrderService.findByOrderId(orderId);
            if(insrncOrder == null){
                return null;
            }
            insrncOrder.setPolicyStatus(8);
            insrncOrderService.updateTbInsrncOrder(insrncOrder);
            //??????????????? ????????????
            TbQuery tbQuery = queryService.findByQueryId(orderId);
            tbQuery.setStatus("5");
            queryService.updateTbQuery(tbQuery);
            //?????????????????????
            updateInsureInfoStatus(orderId,8);
            //????????????????????????
            RenewalReq renewalReq = new RenewalReq();
            renewalReq.setOwnerId(insureInfo.getOwnerId());
            renewalReq.setFrameNo(insureInfo.getFrameNo());
            renewalReq.setStatus(1); //????????????
            renewalReq.setCardLastNo("261076"); //????????????6???
            try {
                renewalService.getRenewal(renewalReq);
            } catch (Exception e) {
                message.put("message","??????????????????,???????????????");
            }
            //??????????????????
            //??????????????????
//            incomeUpdate(insureInfo.getOwnerId(),insureInfo.getFrameNo(),insureInfo.getCompanyId(),queryPolicyInfo,insureInfo.getOrderId(),tbQuery);
            incomeService.calculationIncome(tbQuery);
            //???????????? ?????????????????????8?????????
            insrncOrderService.deleteByFrameNoAndNotStatus(insureInfo.getFrameNo(),8);
            insureInfoService.deleteByFrameNoAndNotStatus(insureInfo.getFrameNo(),8);
        }

        //????????????????????????????????????????????????????????????insureINfo???
        InsureInfo insureInfo = insureInfoService.findByOrderId(paymentRequest.getOrderId());
        insureInfo.setPayStatus(1); //???????????????
        insureInfoService.updateInsureInfo(insureInfo);

        FormBody.Builder form = new FormBody.Builder();
        form.add("orderId",paymentRequest.getOrderId());
        try {
            String result = OkHttpUtils.postFrom(url + "/payment", form, LoginService.getToken());
            log.info("????????????????????????:{}",result);
            return result;

        } catch (IOException e) {
            throw new CustomException("?????????????????????");
        }

    }
    //??????????????????
//    private void incomeUpdate(Long ownerId, String frameNo, Integer companyId, JSONObject queryPolicyInfo, String orderId,TbQuery tbQuery) {
//        log.info("????????????????????????:{},????????????{}",companyId,queryPolicyInfo.toJSONString());
//        TbUserInfoVo userInfo = userInfoService.findUserDetailByOwnerId(ownerId + "");
//        //TODO ???????????????????????????????????????????????????????????????
//        if(!"03".equals(userInfo.getURealNameStatus())){
//            //TODO u_parent_id ????????????????????????????????????
//            if("0".equals(userInfo.getUParentId()) || userInfo.getUParentId() == null || "".equals(userInfo.getUParentId())){
//                return;
//            }else {
//                userInfo = userInfoService.selectTbUserInfoById(Integer.parseInt(userInfo.getUParentId()));
//            }
//        }
//        //???????????????
//        InsrncOrderVo insrncOrderVo = insrncOrderService.findByOrderId(orderId);
//        TbUserInfoVo aUserInfo = null;
//        TbUserInfoVo bUserInfo = null;
//        String aId = userInfo.getUParentId();
//        String bId = "0";
//        String aTeam = "??????";
//        String bTeam = "??????";
//
//        //?????????0
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
//        //??????companyId????????????????????????
//        TbInsrncCpy tbInsrncCpy = insrncCpyService.findByCompanyId(companyId);
//        //???????????????
//        BigDecimal tPremium = new BigDecimal("0");
//        //???????????????
//        BigDecimal vPremium = new BigDecimal("0");
//
//
//        if(insrncOrderVo.getTciPremium() != 0){
//            tPremium = tPremium.add(new BigDecimal(insrncOrderVo.getTciPremium()+""));
//        }
//        if(insrncOrderVo.getVciPremium() != 0){
//            vPremium = vPremium.add(new BigDecimal(insrncOrderVo.getVciPremium()+""));
//        }
//        //???????????????
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
//        //????????????
//        if(insrncOrderVo.getTciPremium() > 0L){
//            //?????????????????????
//            Map<String,Object> rateParam = new HashMap<>();
//            rateParam.put("channelId",tbInsrncCpy.getChannelId());
//            rateParam.put("insrncType","01");//?????????
//            if(!"100".equals(vehicle.getUseType())){
//                rateParam.put("useType","01"); //??????
//
//            }else {
//                rateParam.put("useType","00"); //?????????
//            }
//            //????????????????????????????????????
//            rateParam.put("effectDate", df.format(now));
//            //TODO ??????????????????????????????????????????
//            rateParam.put("belongType","00"); //???????????????
//            //TODO areaCode ??????????????????????????????????????????
//            //????????????
//            if(StringUtils.isNotEmpty(vehicle.getVehicleNo())){
//                rateParam.put("vehicleType","01"); //??????
//            }else {
//                rateParam.put("vehicleType","00"); //??????
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
//                //????????????
//                if(StringUtils.isEmpty(userInfo.getUParentId()) || "0".equals(userInfo.getUParentId())){
//                    pRate = new BigDecimal(rate.getaRate()).add(new BigDecimal(rate.getbRate())).add(pRate);
//                }else {
//                    if(StringUtils.isEmpty(bId) || "0".equals(bId)){
//                        //??????????????????
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
//            incomeSy.setInsrncType("00"); //?????????
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
//            //?????????????????????
//            Map<String,Object> rateParam = new HashMap<>();
//            rateParam.put("channelId",tbQuery.getChannelId());
//            rateParam.put("insrncType",incomeSy.getInsrncType());
//            rateParam.put("belongType","00"); //???????????????
//            if(!"100".equals(vehicle.getUseType())){
//                rateParam.put("useType","01"); //??????
//
//            }else {
//                rateParam.put("useType","00"); //?????????
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




    //??????????????????
    private void updateInsureInfoStatus(String orderId, int policyStatus) {
        InsureInfo insureInfo = insureInfoService.findByOrderId(orderId);
        insureInfo.setPolicyStatus(policyStatus);
        insureInfo.setPayStatus(2); //???????????????
        insureInfoService.updateInsureInfo(insureInfo);
    }
}
