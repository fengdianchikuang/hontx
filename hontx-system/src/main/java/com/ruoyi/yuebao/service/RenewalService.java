package com.ruoyi.yuebao.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.InsrncOrder.domain.vo.InsrncOrderVo;
import com.ruoyi.InsrncOrder.service.InsrncOrderService;
import com.ruoyi.applicant.domain.Applicant;
import com.ruoyi.applicant.service.ApplicantService;
import com.ruoyi.common.enums.CompanyOption;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.insureInfo.domain.InsureInfo;
import com.ruoyi.insureInfo.service.InsureInfoService;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.service.ITbOrganizationService;
import com.ruoyi.platform.domain.Platform;
import com.ruoyi.platform.service.PlatformService;
import com.ruoyi.policy.domain.Policy;
import com.ruoyi.policy.service.PolicyService;
import com.ruoyi.risks.domain.Risks;
import com.ruoyi.risks.service.RisksService;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.domain.TbQueryInsrnc;
import com.ruoyi.system.service.ITbQueryInsrncService;
import com.ruoyi.system.service.ITbQueryService;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import com.ruoyi.vehicle.service.ITbVehicleService;
import com.ruoyi.yuebao.domain.req.InsureRequest;
import com.ruoyi.yuebao.domain.req.QuoteReq;
import com.ruoyi.yuebao.domain.req.RenewalReq;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RenewalService {

    @Value("${echeli.organizationId}")
    private String organizationId;

    @Value("${ybinsure.url}")
    private String url;

    @Autowired
    private ITbVehicleService  vehicleService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private RisksService risksService;

    @Autowired
    private PlatformService platformService;

    @Autowired
    private InsrncOrderService insrncOrderService;

    @Autowired
    private ITbQueryService queryService;

    @Autowired
    private ITbOrganizationService organizationService;

    @Autowired
    private ITbQueryInsrncService queryInsrncService;

    @Autowired
    private InsureInfoService insureInfoService;


    /**
     * ????????????
     * @param renewalRequest
     * @return
     */
    public Map<String,Object> getRenewal(RenewalReq renewalRequest) {
        log.info("??????????????????:", JSON.toJSONString(renewalRequest));
        TbVehicle vehicle = null;
        List<TbVehicle> vehicles = vehicleService.findVehicleByLicenseNoAndFrameNo(renewalRequest.getLicenseNo(), renewalRequest.getFrameNo());
        if(vehicles != null && vehicles.size() > 0){
            vehicles  = vehicles.stream().filter(o -> o.getOwner() == null ).collect(Collectors.toList());
            if(vehicles != null && vehicles.size() > 0){
                 vehicle = vehicles.get(0);
            }
        }

        //????????????????????????????????????????????????
        if(vehicle != null && vehicle.getPurchasePrice() != 0){
            Policy policy = policyService.findByFrameNo(vehicle.getRackNo());
            //???????????? ????????????????????????????????????,???????????????
            boolean refresh = refreshPolicy(policy);
            if(refresh){
                VehicleInfoVo vehicleInfo = new VehicleInfoVo();
                if(!StringUtils.isEmpty(vehicle.getSetNum())){
                    vehicleInfo.setSeatCount(vehicle.getSetNum());
                }
                VehicleCopyProperties(vehicle,vehicleInfo);
                Map<String,Object> data = new HashMap<>();
                //?????????????????????????????????30???
                boolean flg = isLast30Days(policy);
                data.put("outOfTime",flg);
                //???????????????????????????
                List<Applicant> applicants =  applicantService.findByFrameNo(vehicle.getRackNo());
                //?????????????????????
                List<Risks> risks = risksService.findByFrameNo(vehicle.getRackNo());
                //??????????????????
                Platform platform = platformService.findByFrameNo(vehicle.getRackNo());
                data.put("policy",policy);
                data.put("vehicleInfo",vehicleInfo);
                data.put("applicants",applicants);
                data.put("risks",risks);
                data.put("platform",platform);
                data.put("renewalStatus",1);
                //status == 1 ,???????????????????????????
                if(renewalRequest.getStatus() == 0){
                    return data;
                }
            }
        }
        //???????????????????????????
        FormBody.Builder formBody = new FormBody.Builder();
        if (StringUtils.isEmpty(organizationId)) {  //46ee9e10190411eba7e000163e08d6b4
            formBody.add("organizationId", "46ee9e10190411eba7e000163e08d6b4");
        } else {
            formBody.add("organizationId", organizationId);
        }
        if (!StringUtils.isEmpty(renewalRequest.getLicenseNo())) {
            formBody.add("licenseNo", renewalRequest.getLicenseNo());
        }
        if (!StringUtils.isEmpty(renewalRequest.getCardLastNo())) {
            formBody.add("cardLastNo", renewalRequest.getCardLastNo());
        }
        if (!StringUtils.isEmpty(renewalRequest.getFrameNo())) {
            formBody.add("frameNo", renewalRequest.getFrameNo());
        }
        String result = null;
        Map<String,Object> data = new HashMap<>();
        try {
             result = OkHttpUtils.postFrom(url + "/queryRenewal", formBody, LoginService.getToken());
        } catch (
    SocketTimeoutException e) {
        log.error("SocketTimeoutException",e);
        throw new CustomException("????????????,???????????????????????????");
    }
        catch (CustomException e) {
            log.error("CustomException",e);
            throw new CustomException( e.getMessage());
        }
        catch (Exception e) {
        log.error("Exception",e);
        throw new CustomException(e.getMessage());
    }
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        if(jsonObject1.getInteger("status") == 526){
            Map<String,Object> msg = new HashMap<>();
            msg.put("outOfTime",true);
            return msg;
        }
        if(jsonObject1.getInteger("status") != 200){
            throw new CustomException(jsonObject1.getString("message"));
        }
        JSONObject jsonObject = jsonObject1.getJSONObject("data");
        VehicleInfoVo vehicleRequest = jsonObject.getObject("vehicle", VehicleInfoVo.class);



            //??????????????????,?????????,renewalStatus 2?????????????????????????????????
            if(vehicle == null && vehicleRequest == null) {
                //        0??????/1??????
                data.put("renewalStatus", jsonObject.getInteger("renewalStatus"));
            }else {
                //????????????????????????
                data.put("renewalStatus",0);
                //        0??????/1??????
                data.put("renewalStatus", jsonObject.getInteger("renewalStatus"));

                if(vehicleRequest != null) {
                    data.put("vehicleInfo", vehicleRequest);
                }
            }
        if(renewalRequest != null){
            data.put("vehicleInfo", vehicleRequest);
        }else if(vehicleRequest == null && vehicle != null){
            vehicleRequest = new VehicleInfoVo();
            VehicleCopyProperties(vehicle,vehicleRequest);
            data.put("vehicleInfo", vehicleRequest);
        }
        if(vehicle != null && vehicleRequest != null){
            VehicleCopyPropertiesIn(vehicle,vehicleRequest);
            vehicleService.updateTbVehicle(vehicle);
        }else if(vehicle == null && vehicleRequest != null){
            vehicle = new TbVehicle();
            VehicleCopyPropertiesIn(vehicle,vehicleRequest);
            vehicleService.insertTbVehicle(vehicle);
        }
        List<Applicant> applicants = applicantService.findByFrameNo(vehicleRequest.getFrameNo());
        JSONObject applicant = jsonObject.getJSONObject("applicant");
        //applicant????????????
        List<Applicant> applicantList = ApplicantCopyProperties(applicant, applicants, renewalRequest.getOwnerId(), vehicleRequest);
        for (Applicant applicant1 : applicantList) {
            if(applicant1.getId() == null || applicant1.getId() == 0L){
                applicantService.insertApplicant(applicant1);
            }else {
                applicantService.updateApplicant(applicant1);
            }
        }
        data.put("applicants",applicantList);

        //??????????????????
        Policy policy = jsonObject.getObject("policy", Policy.class);
        Policy policyOld = policyService.findByFrameNo(vehicleRequest.getFrameNo());
        //??????
        if(policy != null && policyOld == null){
            policy.setFrameNo(policyOld.getFrameNo());
            policy.setOwnerId(policyOld.getOwnerId());
        //??????
        } else if(policyOld != null && policy != null){
            policy.setId(policyOld.getId());
            policy.setFrameNo(policyOld.getFrameNo());
            policy.setOwnerId(policyOld.getOwnerId());
            policyService.updatePolicy(policy);
        }
        data.put("policy",policy);

        //?????????????????????
        risksService.deleteByframeNo(vehicleRequest.getFrameNo());
        JSONArray risks = jsonObject.getJSONArray("risks");
        List<Risks> risks1 = JSONArray.parseArray(risks.toJSONString(), Risks.class);
        for (Risks risks2 : risks1) {
            risks2.setFrameNo(vehicleRequest.getFrameNo());
            risks2.setCompanyId(policy.getCompanyId());
            risksService.insertRisks(risks2);
        }
        data.put("risks",risks);

        Platform platform = jsonObject.getObject("platform", Platform.class);
        //??????????????????
        Platform platformOld = platformService.findByFrameNo(vehicle.getRackNo());
        if(platformOld != null && platform != null){
            platform.setId(platformOld.getId());
            platformService.updatePlatform(platform);
        }else if(platformOld == null && platform != null){
            platform.setFrameNo(vehicleRequest.getFrameNo());
            platform.setOwnerId(renewalRequest.getOwnerId());
            platformService.insertPlatform(platform);
        }

        //?????????????????????30???,????????????30?????????????????????
            boolean flg = false;
            if(policy != null){
                flg = isLast30Days(policy);
            }
            data.put("outOfTime",flg);

        return data;
    }

    private List<Applicant> ApplicantCopyProperties(JSONObject applicant, List<Applicant> applicants, Long ownerId, VehicleInfoVo vehicleRequest) {
        if (applicants != null && applicants.size() > 0) {
            for (Applicant applicant1 : applicants) {
                switch (applicant1.getInsuredFlag()) {
                    case 1:
                        applicant1.setOwnerId(ownerId);
                        applicant1.setFrameNo(vehicleRequest.getFrameNo());
                        applicant1.setName(applicant.getString("ownerName"));
                        applicant1.setPhone(applicant.getString("ownerPhone"));
                        applicant1.setCertificateType(applicant.getString("ownerIdType"));
                        applicant1.setCertificateNo(applicant.getString("ownerIdNo"));
                        applicant1.setBirthday(applicant.getDate("ownerBirthday"));
                        applicant1.setSex(applicant.getInteger("ownerSex"));
                        applicant1.setInsuredFlag(1);
                        break;
                    case 2:
                        applicant1.setOwnerId(ownerId);
                        applicant1.setFrameNo(vehicleRequest.getFrameNo());
                        applicant1.setName(applicant.getString("holderName"));
                        applicant1.setPhone(applicant.getString("holderPhone"));
                        applicant1.setCertificateType(applicant.getString("holderIdType"));
                        applicant1.setCertificateNo(applicant.getString("holderIdNo"));
                        applicant1.setAddress(applicant.getString("holderAddr"));
                        applicant1.setBirthday(applicant.getDate("holderBirthday"));
                        applicant1.setSex(applicant.getInteger("holderSex"));
                        break;
                    case 3:
                        applicant1.setOwnerId(ownerId);
                        applicant1.setFrameNo(vehicleRequest.getFrameNo());
                        applicant1.setName(applicant.getString("insuredName"));
                        applicant1.setPhone(applicant.getString("insuredPhone"));
                        applicant1.setCertificateType(applicant.getString("insuredIdType"));
                        applicant1.setCertificateNo(applicant.getString("insuredIdNo"));
                        applicant1.setAddress(applicant.getString("insuredAddr"));
                        applicant1.setBirthday(applicant.getDate("insuredBirthday"));
                        applicant1.setSex(applicant.getInteger("insuredSex"));
                }
            }
            return applicants;
        }

        Applicant ownerApplicant = new Applicant();
        ownerApplicant.setOwnerId(ownerId);
        ownerApplicant.setFrameNo(vehicleRequest.getFrameNo());
        ownerApplicant.setName(applicant.getString("ownerName"));
        if(StringUtils.isEmpty(applicant.getString("ownerPhone"))){
            ownerApplicant.setPhone(applicant.getString("insuredPhone"));
        }
        ownerApplicant.setPhone(applicant.getString("ownerPhone"));
        ownerApplicant.setCertificateType(applicant.getString("ownerIdType"));
        ownerApplicant.setCertificateNo(applicant.getString("ownerIdNo"));
        ownerApplicant.setBirthday(applicant.getDate("ownerBirthday"));
        ownerApplicant.setSex(applicant.getInteger("ownerSex"));
        if(StringUtils.isEmpty(applicant.getString("ownerAddr"))){
            ownerApplicant.setAddress(applicant.getString("holderAddr"));
        }else {
            ownerApplicant.setAddress(applicant.getString("ownerAddr"));
        }
        applicants = new ArrayList<>();
        ownerApplicant.setInsuredFlag(1);
        applicants.add(ownerApplicant);
        Applicant holderApplicant = new Applicant();
        holderApplicant.setInsuredFlag(2);
        holderApplicant.setOwnerId(ownerId);
        holderApplicant.setFrameNo(vehicleRequest.getFrameNo());
        holderApplicant.setName(applicant.getString("holderName"));
        holderApplicant.setPhone(applicant.getString("holderPhone"));
        holderApplicant.setCertificateType(applicant.getString("holderIdType"));
        holderApplicant.setCertificateNo(applicant.getString("holderIdNo"));
        holderApplicant.setAddress(applicant.getString("holderAddr"));
        holderApplicant.setBirthday(applicant.getDate("holderBirthday"));
        holderApplicant.setSex(applicant.getInteger("holderSex"));
        applicants.add(holderApplicant);
        Applicant insuredApplication = new Applicant();
        insuredApplication.setInsuredFlag(3);
        insuredApplication.setOwnerId(ownerId);
        insuredApplication.setFrameNo(vehicleRequest.getFrameNo());
        insuredApplication.setName(applicant.getString("insuredName"));
        insuredApplication.setPhone(applicant.getString("insuredPhone"));
        insuredApplication.setCertificateType(applicant.getString("insuredIdType"));
        insuredApplication.setCertificateNo(applicant.getString("insuredIdNo"));
        insuredApplication.setAddress(applicant.getString("insuredAddr"));
        insuredApplication.setBirthday(applicant.getDate("insuredBirthday"));
        insuredApplication.setSex(applicant.getInteger("insuredSex"));
        applicants.add(insuredApplication);
        return applicants;
    }

    /**
     * ????????????????????????????????????
     * @param oldVehicle
     * @param vehicleRequest
     */
    private void VehicleCopyPropertiesIn (TbVehicle oldVehicle, VehicleInfoVo vehicleRequest){
        oldVehicle.setVehicleNo(vehicleRequest.getLicenseNo());
        oldVehicle.setEtBrand(vehicleRequest.getBrandName());
        oldVehicle.setBrandCode(vehicleRequest.getModelCode());
        oldVehicle.setFlg(1); //??????
        oldVehicle.setRackNo(vehicleRequest.getFrameNo());
        oldVehicle.setPurchasePrice(vehicleRequest.getPurchasePrice());
        oldVehicle.setSetNum(vehicleRequest.getSeatCount());
        oldVehicle.setEngineNo(vehicleRequest.getEngineNo());
        oldVehicle.setRegisterDate(vehicleRequest.getEnrollDate());
        oldVehicle.setVehicleType(vehicleRequest.getVehicleType());
        oldVehicle.setVehicleCategory(vehicleRequest.getVehicleCategory());
        oldVehicle.setUseType(vehicleRequest.getUseNature());
        oldVehicle.setPlateColor(vehicleRequest.getPlateColor());
        oldVehicle.setEnergyType(vehicleRequest.getEnergyType());

    }

    //?????????????????????????????????30???
    private boolean isLast30Days (Policy policy){
        long endTime = 0L;
        if (policy.getTciPolicyEndDateTime() != null) {
            endTime = policy.getTciPolicyEndDateTime().getTime();
        } else {
            endTime = policy.getVciPolicyEndDateTime().getTime();
        }

        long nowTime = new Date().getTime();

        long betweenDays = (endTime - nowTime) / (1000L * 3600L * 24L);

        if (betweenDays > 30) {
            return false;
        }
        return true;
    }

    /**
     * ??????????????????
     * @param vehicle
     * @param vehicleInfo
     */
    private void VehicleCopyProperties (TbVehicle vehicle, VehicleInfoVo vehicleInfo){
        vehicleInfo.setLicenseNo(vehicle.getVehicleNo());
        vehicleInfo.setBrandName(vehicle.getEtBrand());
        vehicleInfo.setFrameNo(vehicle.getRackNo());
        vehicleInfo.setModelCode(vehicle.getBrandCode());
        vehicleInfo.setPurchasePrice(vehicle.getPurchasePrice());
        vehicleInfo.setSeatCount(vehicle.getSetNum());
        vehicleInfo.setEngineNo(vehicle.getEngineNo());
        vehicleInfo.setEnrollDate(vehicle.getRegisterDate());
        vehicleInfo.setVehicleType(vehicle.getVehicleType());
        vehicleInfo.setVehicleCategory(vehicle.getVehicleCategory());
        vehicleInfo.setUseNature(vehicle.getUseType());
        vehicleInfo.setPlateType(vehicle.getPlateType());
        vehicleInfo.setPlateColor(vehicle.getPlateColor());
        vehicleInfo.setEnergyType(vehicle.getEnergyType());

    }

    /**
     * ???????????? ????????????????????????????????????,???????????????
     */
    private boolean refreshPolicy (Policy policy){
        if (policy == null) {
            return false;
        }
        if (policy.getTciPolicyEndDateTime() != null) {
            if (policy.getTciPolicyEndDateTime().getTime() < System.currentTimeMillis()) {
                return false;
            } else {
                return true;
            }
        }
        if (policy.getVciPolicyEndDateTime() != null) {
            if (policy.getVciPolicyEndDateTime().getTime() < System.currentTimeMillis()) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * ??????????????????
     * @param quote
     * @param request
     * @return
     */

    public JSONObject insuranceCalculation(QuoteReq quote, HttpServletRequest request) {
        //?????????????????????????????????????????????
        for (Applicant applicant : quote.getApplicants()) {
            if(StringUtils.isEmpty(applicant.getAddress())){
                applicant.setAddress("???????????????????????????");
            }
        }
        log.info("????????????????????????:{}",JSON.toJSONString(quote));
        //???????????????????????????Id?????????
        quote.getBaseInfo().setOrganizationId(organizationId);

        String result = null;
        try {
            result = OkHttpUtils.postJSON(url + "/quote", JSON.toJSONString(quote), LoginService.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        //??????????????????
        if(200 == jsonObject.getInteger("status")) {
            JSONObject data = jsonObject.getJSONObject("data");

            InsrncOrderVo baseInfo = data.getObject("baseInfo", InsrncOrderVo.class);
            InsrncOrderVo one = insrncOrderService.findByFrameNoAndCompanyIdAndOwnerId
                    (quote.getVehicleInfo().getFrameNo(),quote.getBaseInfo().getCompanyId(),quote.getBaseInfo().getOwnerId());
            if(one == null){
                baseInfo.setOwnerId(quote.getBaseInfo().getOwnerId());
                baseInfo.setCompanyId(quote.getBaseInfo().getCompanyId());
                baseInfo.setFrameNo(quote.getVehicleInfo().getFrameNo());
                insrncOrderService.insertTbInsrncOrder(baseInfo);
            }else {
                baseInfo.setId(one.getId());
                baseInfo.setOwnerId(quote.getBaseInfo().getOwnerId());
                baseInfo.setCompanyId(quote.getBaseInfo().getCompanyId());
                baseInfo.setFrameNo(quote.getVehicleInfo().getFrameNo());
                insrncOrderService.updateTbInsrncOrder(baseInfo);
            }
            TbVehicle vehicle = null;
            List<TbVehicle> vehicles = vehicleService.findVehicleByLicenseNoAndFrameNo(quote.getVehicleInfo().getLicenseNo(), quote.getVehicleInfo().getFrameNo());
            if(vehicles != null && vehicles.size() > 0){
                vehicles  = vehicles.stream().filter(o -> o.getOwner() == null ).collect(Collectors.toList());
                if(vehicles != null && vehicles.size() > 0){
                    vehicle = vehicles.get(0);
                }
            }
            //??????????????????????????????
            VehicleCopyPropertiesIn(vehicle,quote.getVehicleInfo());
            if(vehicle != null && vehicle.getvId() == null){
                vehicleService.insertTbVehicle(vehicle);
            }else if(vehicle != null && vehicle.getvId() != null){
                vehicleService.updateTbVehicle(vehicle);
            }
            //????????????
            JSONArray risks = data.getJSONArray("risks");
            List<Risks> resRisks = null;
            if(risks != null){
                resRisks = JSON.parseArray(risks.toJSONString(), Risks.class);
            }
//            ?????????????????????Id??????
            List<Risks> risksList = risksService.findByFrameNoAndCompanyId(quote.getVehicleInfo().getFrameNo(),quote.getBaseInfo().getCompanyId());
            for (Risks risks1 : risksList) {
                int res = risksService.deleteRisksById(risks1.getId());
            }
            //?????????
            for (Risks resRisk : resRisks) {
                resRisk.setCompanyId(quote.getBaseInfo().getCompanyId());
                resRisk.setFrameNo(quote.getVehicleInfo().getFrameNo());
                risksService.insertRisks(resRisk);
            }
            //??????????????????
            Platform platform = data.getObject("platform", Platform.class);
            if(platform != null){
                Platform byFrameNo = platformService.findByFrameNo(baseInfo.getFrameNo());
                if(byFrameNo != null){
                    platform.setFrameNo(baseInfo.getFrameNo());
                    platform.setId(byFrameNo.getId());
                    platformService.updatePlatform(platform);
                }else {
                    platform.setFrameNo(baseInfo.getFrameNo());
                    platformService.insertPlatform(platform);
                }

            }

            data.put("baseInfo",baseInfo);
            data.put("vehicleInfo",quote.getVehicleInfo());
            data.put("applicants",quote.getApplicants());
            //??????????????????????????????
            //00 ????????? 01 ????????? 02 ????????? 03 ???????????? 04 ????????? 05 ?????????
            insertQueryEntity(quote, baseInfo,request);
                return data;
        }else {
            throw new CustomException(jsonObject.getString("message"));
        }

    }
    //??????query??????
    private void insertQueryEntity(QuoteReq quote, InsrncOrderVo baseInfo, HttpServletRequest request) {
        TbQuery tbQuery = queryService.findByQueryId(baseInfo.getOrderId());
        if(tbQuery == null){
            tbQuery = new TbQuery();
        }
        //01???????????? (?????????????????????)
        //02?????????
        tbQuery.setStatus("02");
        tbQuery.setQueryId(baseInfo.getOrderId());
        //????????????Id(????????????e?????????????????????(????????????)??????
        String companyId = request.getHeader("companyId");
        //??????,??????e??????
        Integer orgId = companyId == null ? 0 : Integer.parseInt(companyId);
        TbOrganization organization = organizationService.findByCompanyId(orgId);
        tbQuery.setOrgId(organization.getoId()+"");
        String channel = CompanyOption.transformation(baseInfo.getCompanyId());
        tbQuery.setChannelId(channel);
        //????????????????????????
        tbQuery.setFlag("0");
        tbQuery.setPremium(baseInfo.getTotalPremium()+"");
        tbQuery.setPremiumJq(baseInfo.getTciPremium()+"");
        tbQuery.setPremiumSy(baseInfo.getVciPremium()+"");
        tbQuery.setTax(baseInfo.getVehicleTax()+"");
        if(baseInfo.getTciStartDate() != null){
            tbQuery.setInsrncBgnTmJq(baseInfo.getTciStartDate());
        }
        if(baseInfo.getVciStartDate() != null){
            tbQuery.setInsrncBgnTm(baseInfo.getVciStartDate());
        }
        //????????????
        if(quote.getVehicleInfo().getEnrollDate() != null){
            tbQuery.setChangeOwnerFlag("1");
            tbQuery.setTransferDate(quote.getVehicleInfo().getTransferDate());
        }
        tbQuery.setVehicleNo(quote.getVehicleInfo().getLicenseNo());
        tbQuery.setEtBrand(quote.getVehicleInfo().getBrandName());
        tbQuery.setRackNo(quote.getVehicleInfo().getFrameNo());
        tbQuery.setBrandCode(quote.getVehicleInfo().getModelCode());
        tbQuery.setSetNum(String.valueOf(quote.getVehicleInfo().getSeatCount()));
        tbQuery.setEngineNo(quote.getVehicleInfo().getEngineNo());
        if(StringUtils.isEmpty(quote.getVehicleInfo().getEnrollDate())){
            tbQuery.setRegisterDate(quote.getVehicleInfo().getEnrollDate());
        }

        //??????
        for (Applicant applicant : quote.getApplicants()) {
            if(applicant.getInsuredFlag() == 1){

                tbQuery.setOwner(applicant.getName());

                tbQuery.setOwner(applicant.getName());
                tbQuery.setOwner(applicant.getName());
                tbQuery.setOwnerCertType(applicant.getCertificateType());
                tbQuery.setOwnerCertNo(applicant.getCertificateNo());
                tbQuery.setOwnerTel(applicant.getPhone());

                //????????????
            }else if(applicant.getInsuredFlag() == 2){
                tbQuery.setInsuredName(applicant.getName());
                tbQuery.setInsuredCertType(applicant.getCertificateType());
                tbQuery.setInsuredCertNo(applicant.getCertificateNo());
                tbQuery.setInsuredTel(applicant.getPhone());
                //?????????
            }else if(applicant.getInsuredFlag() == 3 ){
                tbQuery.setApplicantName(applicant.getName());
                tbQuery.setApplicantCertType(applicant.getCertificateType());
                tbQuery.setApplicantCertNo(applicant.getCertificateNo());
                tbQuery.setApplicantTel(applicant.getPhone());
            }

        }
        //??????
        if(tbQuery.getqId() == null){
            tbQuery.setCreateTime(new Date());
            queryService.insertTbQuery(tbQuery);
        //??????
        }else {
            tbQuery.setUpdateTime(new Date());
            queryService.updateTbQuery(tbQuery);
        }
        //???????????????????????????
        //???????????????????????????
        //????????????Id??????????????????
        int res = queryInsrncService.deleteByQueryId(baseInfo.getOrderId());
        if(quote.getRisks() != null && quote.getRisks().size() > 0){
            for (Risks risk : quote.getRisks()) {
                TbQueryInsrnc queryInsrncEntity = new TbQueryInsrnc();
                queryInsrncEntity.setAmount(risk.getAmount()+"");
                queryInsrncEntity.setDeductPremium(risk.getDeductPremium()+"");
                queryInsrncEntity.setInsrncCode(risk.getRiskCode());
                if(risk.getPremium() == null || risk.getPremium() == 0){
                    queryInsrncEntity.setPremium("0");
                }else {
                    queryInsrncEntity.setPremium(risk.getPremium()+"");
                }
                queryInsrncEntity.setQueryId(baseInfo.getOrderId());
                queryInsrncService.insertTbQueryInsrnc(queryInsrncEntity);
            }
        }


    }

    /**
     * ????????????
     * @param insureRequest
     * @return
     */
    public JSONObject insure(InsureRequest insureRequest) {
        log.info("????????????????????????:{}", JSON.toJSONString(insureRequest));

        //????????????
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("orderId",insureRequest.getOrderId());
        String result = null;
        try {
             result = OkHttpUtils.postFrom(url + "/insure", formBody, LoginService.getToken());
        } catch (IOException e) {
            throw new CustomException("????????????,?????????");
        }
        log.info("???????????????????????????:{}",result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(jsonObject.getInteger("status") == 500){
            //????????????,??????????????????
            throw new CustomException(jsonObject.getString("message"));
        }

        JSONObject map = new JSONObject();
        //??????????????????
        Platform platform = platformService.findByFrameNo(insureRequest.getFrameNo());
        map.put("platform",platform);
        //???????????????
        List<Applicant> applicants = applicantService.findByFrameNo(insureRequest.getFrameNo());
        map.put("applicants",applicants);
        //??????????????????
        TbVehicle vehicle = null;
        List<TbVehicle> vehicles = vehicleService.findVehicleByLicenseNoAndFrameNo(null, insureRequest.getFrameNo());
        if(vehicles != null && vehicles.size() > 0){
            vehicles  = vehicles.stream().filter(o -> o.getOwner() == null ).collect(Collectors.toList());
            if(vehicles != null && vehicles.size() > 0){
                vehicle = vehicles.get(0);
            }
        }
        map.put("vehicleInfo",vehicle);
        //???????????????
        List<Risks> risks = risksService.findByFrameNo(insureRequest.getFrameNo());
        map.put("risks",risks);
        if(200 == jsonObject.getInteger("status")){
            JSONObject data = jsonObject.getJSONObject("data");
            InsrncOrderVo baseInfo = insrncOrderService.findByOrderId(insureRequest.getOrderId());
            InsureInfo insureInfoOld = insureInfoService.findByOrderId(insureRequest.getOrderId());
            InsureInfo insureInfo = data.getObject("baseInfo", InsureInfo.class);
            if(insureInfoOld != null){
                insureInfo.setId(insureInfoOld.getId());
                insureInfo.setFrameNo(baseInfo.getFrameNo());
                insureInfo.setOwnerId(baseInfo.getOwnerId());
                insureInfo.setCompanyId(baseInfo.getCompanyId());
                insureInfoService.updateInsureInfo(insureInfo);
            }else {
                insureInfo.setFrameNo(baseInfo.getFrameNo());
                insureInfo.setOwnerId(baseInfo.getOwnerId());
                insureInfo.setCompanyId(baseInfo.getCompanyId());
                insureInfoService.insertInsureInfo(insureInfo);
            }
            log.info("insureInfo?????????:{}",insureInfo.toString());
            log.info("baseInfo??????:{}",baseInfo.toString());
            map.put("orderInfo",insureInfo);
            map.put("baseInfo",baseInfo);
        }

        return map;
    }
}
