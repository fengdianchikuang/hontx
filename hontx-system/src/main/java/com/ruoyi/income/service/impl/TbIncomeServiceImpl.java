package com.ruoyi.income.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.company.domain.TbInsrncCpy;
import com.ruoyi.company.mapper.TbInsrncCpyMapper;
import com.ruoyi.income.domain.HontxTransEntity;
import com.ruoyi.income.domain.InformEntity;
import com.ruoyi.income.domain.IntegralRateEntity;
import com.ruoyi.income.domain.vo.IncomeVo;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.mapper.TbOrganizationMapper;
import com.ruoyi.rate.domain.TbInsrncCpyRate;
import com.ruoyi.rate.mapper.TbInsrncCpyRateMapper;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.domain.TbQueryInsrnc;
import com.ruoyi.system.mapper.TbQueryInsrncMapper;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.mapper.TbTransMapper;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.mapper.TbUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.income.mapper.TbIncomeMapper;
import com.ruoyi.income.domain.TbIncome;
import com.ruoyi.income.service.ITbIncomeService;

/**
 * 销售业绩Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-19
 */
@Service
@Slf4j
public class TbIncomeServiceImpl implements ITbIncomeService
{
    @Autowired
    private TbIncomeMapper tbIncomeMapper;

    @Autowired
    private TbOrganizationMapper organizationMapper;

    @Autowired
    private TbInsrncCpyRateMapper tbInsrncCpyRateMapper;

    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @Autowired
    private TbQueryInsrncMapper tbQueryInsrncMapper;

    @Autowired
    private TbTransMapper tbTransMapper;

    @Autowired
    private TbInsrncCpyMapper tbInsrncCpyMapper;


    /**
     * 查询销售业绩
     *
     * @param iId 销售业绩ID
     * @return 销售业绩
     */
    @Override
    public TbIncome selectTbIncomeById(Long iId)
    {
        return tbIncomeMapper.selectTbIncomeById(iId);
    }

    /**
     * 查询销售业绩列表
     *
     * @param tbIncome 销售业绩
     * @return 销售业绩
     */
    @Override
    public List<IncomeVo> selectTbIncomeList(IncomeVo tbIncome)
    {
        return tbIncomeMapper.selectTbIncomeList(tbIncome);
    }

    /**
     * 新增销售业绩
     *
     * @param tbIncome 销售业绩
     * @return 结果
     */
    @Override
    public int insertTbIncome(TbIncome tbIncome)
    {
        return tbIncomeMapper.insertTbIncome(tbIncome);
    }

    /**
     * 修改销售业绩
     *
     * @param tbIncome 销售业绩
     * @return 结果
     */
    @Override
    public int updateTbIncome(TbIncome tbIncome)
    {
        return tbIncomeMapper.updateTbIncome(tbIncome);
    }

    /**
     * 批量删除销售业绩
     *
     * @param iIds 需要删除的销售业绩ID
     * @return 结果
     */
    @Override
    public int deleteTbIncomeByIds(Long[] iIds)
    {
        return tbIncomeMapper.deleteTbIncomeByIds(iIds);
    }

    /**
     * 删除销售业绩信息
     *
     * @param iId 销售业绩ID
     * @return 结果
     */
    @Override
    public int deleteTbIncomeById(Long iId)
    {
        return tbIncomeMapper.deleteTbIncomeById(iId);
    }

    @Override
    public Float queryTotalByToday(Long orgId, String todayMax, String todayMin) {
        return tbIncomeMapper.queryTotalByToday(orgId+"",todayMax,todayMin);
    }


    /**
     * 计算寿险今日收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @return
     */
    @Override
    public Float queryLifeTotalByToday(Long orgId, String todayMax, String todayMin) {
        return tbIncomeMapper.queryLifeTotalByToday(orgId,todayMax,todayMin);
    }

    /**
     * 计算今日商城收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @param type
     * @return
     */
    @Override
    public Float queryShopTotalByToday(Long orgId, String todayMax, String todayMin, String type) {

        return tbIncomeMapper.queryShopTotalByToday(orgId,todayMax,todayMin,type);
    }

    /**
     * 检查是否设置费率
     * @param tbQuery
     * @return
     */
    @Override
    public boolean isSetFee(TbQuery tbQuery) {
        Map<String, Object> rateParam = new HashMap<String, Object>();
        if(tbQuery.getOrgId() != null && !"".equals(tbQuery.getOrgId())){
            TbOrganization organization = organizationMapper.selectTbOrganizationById(Long.valueOf(tbQuery.getOrgId()));
            if(organization != null && !"".equals(organization)){
                TbOrganization orgParent = organizationMapper.selectTbOrganizationById(Long.valueOf(organization.getParentOrgid()));

                if(orgParent != null && !"".equals(orgParent)){
                    //type 00 为省级机构
                    if(!"00".equals(orgParent.getType())){
                        //父级机构Id
                        rateParam.put("org_id",orgParent.getoId()+"");
                    }else {
                        //机构Id
                        rateParam.put("org_id",organization.getoId()+"");
                    }
                }
            }

        }
        rateParam.put("channelId", tbQuery.getChannelId());
        //按照当天日期进行收益计算
        rateParam.put("effectDate", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        rateParam.put("areaCode", tbQuery.getAreaCode());
        rateParam.put("belongType", tbQuery.getBelongType());

        List<TbInsrncCpyRate> tbInsrncCpyRates = tbInsrncCpyRateMapper.selectTbInsrncCpyRateListByMap(rateParam);
        if(tbInsrncCpyRates == null || tbInsrncCpyRates.size() == 0){
            throw new CustomException("未设置费率，请设置");
        }
        return true;
    }

    /**
     * 算收益
     * @param tbQuery
     */
    @Override
    public void calculationIncome(TbQuery tbQuery) {
        BigDecimal bPremiumJq = new BigDecimal("0");
        if(tbQuery.getPremiumJq() != null && !"".equals(tbQuery.getPremiumJq())){
            bPremiumJq = new BigDecimal(tbQuery.getPremiumJq());
        }
        BigDecimal bPremiumSy = new BigDecimal("0");
        if(tbQuery.getPremiumSy() != null && !"".equals(tbQuery.getPremiumSy())){
            bPremiumSy = new BigDecimal(tbQuery.getPremiumSy());
        }
        BigDecimal premium = bPremiumJq.add(bPremiumSy);
        TbUserInfoVo tbUserInfoVo = tbUserInfoMapper.selectTbUserInfoById(Integer.parseInt(tbQuery.getAgentId()));
        TbUserInfoVo aUserInfo = null;
        TbUserInfoVo bUserInfo = null;
        String aId = tbUserInfoVo.getUParentId();
        String bId = "0";
        String aTeam = "平台";
        String bTeam = "平台";
        //平台为0
        if(StringUtils.isNotEmpty(aId) && !"0".equals(aId)){
            aUserInfo = tbUserInfoMapper.selectTbUserInfoById(Integer.parseInt(aId));
            aTeam = aUserInfo.getURealName();
            bId = aUserInfo.getUParentId();
            if(bId.equals("0")){
                bId = "0";
            }
            if(StringUtils.isNotEmpty(bId) && !"0".equals(bId)){
                bUserInfo = tbUserInfoMapper.selectTbUserInfoById(Integer.parseInt(bId));
            }
        }
        TbIncome incomeJq = new TbIncome();
        TbIncome incomeSy = new TbIncome();

        String ut = tbQuery.getUseType();
        String useType = "";
        if("00".equals(ut) || "05".equals(ut) || "06".equals(ut)){
            useType = "00";
        }
        if("01".equals(ut) || "02".equals(ut) || "03".equals(ut) || "04".equals(ut) || "08".equals(ut)
        || "07".equals(ut) || "09".equals(ut)){
            useType = "01";
        }
        if(StringUtils.isEmpty(useType)){
            return;
        }
        if(bPremiumJq.compareTo(new BigDecimal("0")) > 0){
            incomeJq.setAppNo(tbQuery.getJqAppNo());
            incomeJq.setAgentId(tbQuery.getAgentId());
            incomeJq.setAgentName(tbUserInfoVo.getURealName());
            incomeJq.setiCreateTime(new Date());
            incomeJq.setaTeamId(aId);
            incomeJq.setbTeam(bId);
            incomeJq.setaTeam(aTeam);
            incomeJq.setbTeam(bTeam);
            incomeJq.setQueryId(tbQuery.getQueryId());
            incomeJq.setCheckDate(new Date());
            incomeJq.setPremium(bPremiumJq);
            incomeJq.setVehicleNo(tbQuery.getVehicleNo());
            incomeJq.setRackNo(tbQuery.getRackNo());

            incomeJq.setRegisterDate(tbQuery.getRegisterDate());
            incomeJq.setBelongType(tbQuery.getBelongType());

            incomeJq.setChannelId(tbQuery.getChannelId());
            incomeJq.setInsrncType("01");
            //现在只区分，交强和商业
//            if(bPremiumSy.compareTo(new BigDecimal("0")) > 0){
                //00 商业 01 交强
//                incomeJq.setInsrncType("01");
//            }
            incomeJq.setUseType(useType);
            //新车 旧车
            if(tbQuery.getVehicleNo() == null || "".equals(tbQuery.getVehicleNo())){
                incomeJq.setVehicleType("00");
            }else {
                incomeJq.setVehicleType("01");
            }
            incomeJq.setInsuredName(tbQuery.getInsuredName());
            incomeJq.setInsuredCertNo(tbQuery.getInsuredCertNo());
            incomeJq.setInsrncBgnTm(tbQuery.getInsrncBgnTm());
            incomeJq.setInsrncEndTm(tbQuery.getInsrncEndTm());
            incomeJq.setQueryServiceId(tbQuery.getServiceId());
            incomeJq.setQueryServiceName("");
            incomeJq.setCheckServiceId("");
            incomeJq.setCheckServiceName("");


            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Map<String, Object> rateParam = new HashMap<String, Object>();
            rateParam.put("channelId", incomeJq.getChannelId());
            rateParam.put("insrncType", incomeJq.getInsrncType());
            rateParam.put("belongType", incomeJq.getBelongType());
            rateParam.put("useType", useType);
            rateParam.put("vehicleType", incomeJq.getVehicleType());
            //按照当天日期进行收益计算
            rateParam.put("effectDate", sdf.format(new Date()));
            rateParam.put("areaCode", tbQuery.getAreaCode());
            if(tbQuery.getOrgId() != null && !"".equals(tbQuery.getOrgId())){
                TbOrganization organization = organizationMapper.selectTbOrganizationById(Long.valueOf(tbQuery.getOrgId()));
                if(organization != null && !"".equals(organization)){
                    TbOrganization orgParent = organizationMapper.selectTbOrganizationById(Long.valueOf(organization.getParentOrgid()));

                    if(orgParent != null && !"".equals(orgParent)){
                        //type 00 为省级机构
                        if(!"00".equals(orgParent.getType())){
                            //父级机构Id
//                            rateParam.setOrgId(orgParent.getoId()+"");
                            rateParam.put("orgId",orgParent.getoId());
                        }else {
                            //机构Id
//                            tbInsrncCpyRate.setOrgId(organization.getoId()+"");
                            rateParam.put("orgId",organization.getoId());
                        }
                    }
                }

            }

            List<TbInsrncCpyRate> tbInsrncCpyRates = tbInsrncCpyRateMapper.selectTbInsrncCpyRateListByMap(rateParam);
            if(tbInsrncCpyRates != null && tbInsrncCpyRates.size() > 0){
                TbInsrncCpyRate rate = tbInsrncCpyRates.get(0);
                incomeJq.setTotalRate(new BigDecimal(rate.getTotalRate()));
                incomeJq.setTotalIncome(bPremiumJq.multiply(incomeJq.getTotalRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
                incomeJq.setAgentRate(new BigDecimal(rate.getAgentRate()));
                incomeJq.setAgentIncome(bPremiumJq.multiply(incomeJq.getAgentRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));

                BigDecimal pRate = new BigDecimal("0");
                pRate = new BigDecimal(rate.getTotalRate())
                        .subtract(new BigDecimal(rate.getTotalRate()))
                        .subtract(new BigDecimal(rate.getaRate()))
                        .subtract(new BigDecimal(rate.getbRate()));
                //没有上级
                if(StringUtils.isEmpty(aId) || "0".equals(aId)){
                    pRate = new BigDecimal(rate.getaRate()).add(new BigDecimal(rate.getbRate())).add(pRate);
                }else {
                    if(StringUtils.isEmpty(bId) || "0".equals(bId)){
                        //只有一个上级
                        pRate = new BigDecimal(rate.getbRate()).add(pRate);
                    }
                }
                incomeJq.setpRate(pRate);
                incomeJq.setpIncome(bPremiumJq.multiply(pRate).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
                tbIncomeMapper.insertTbIncome(incomeJq);
            }
        }else {
            incomeJq.setAgentIncome(new BigDecimal("0"));
            incomeJq.setaIncome(new BigDecimal("0"));
            incomeJq.setbIncome(new BigDecimal("0"));
        }

        if(bPremiumSy.compareTo(new BigDecimal("0"))>0){
            incomeSy.setAppNo(tbQuery.getSyAppNo());
            incomeSy.setAgentId(tbQuery.getAgentId());
            incomeSy.setAgentName(tbUserInfoVo.getURealName());
            incomeSy.setaTeamId(aId);
            incomeSy.setbTeamId(bId);
            incomeSy.setaTeam(aTeam);
            incomeSy.setbTeam(bTeam);
            incomeSy.setCheckDate(new Date());
            incomeSy.setiCreateTime(new Date());
            incomeSy.setPremium(bPremiumSy);
            incomeSy.setVehicleNo(tbQuery.getVehicleNo());
            incomeSy.setRackNo(tbQuery.getRackNo());

            incomeSy.setRegisterDate(tbQuery.getRegisterDate());
            incomeSy.setBelongType(tbQuery.getBelongType());

            incomeSy.setQueryId(tbQuery.getQueryId());
            incomeSy.setChannelId(tbQuery.getChannelId());
            //保险类型：00，商业，01，交强
            incomeSy.setInsrncType("00");
            TbQueryInsrnc tbQueryInsrnc = new TbQueryInsrnc();
            tbQueryInsrnc.setQueryId(tbQuery.getQueryId());
            List<TbQueryInsrnc> queryInsrncList = tbQueryInsrncMapper.selectTbQueryInsrncList(tbQueryInsrnc);

            boolean only = true;
            for(TbQueryInsrnc qie : queryInsrncList){
                String insrncCode = qie.getInsrncCode();
                if(!"000".equals(insrncCode)&&!"001".equals(insrncCode)){
                    only = false;
                }
            }
            if(only){
                incomeSy.setInsrncType("00"); //设置商业险
            }

            incomeSy.setUseType(useType);
            //新车，旧车
            if(tbQuery.getVehicleNo()==null||"".equals(tbQuery.getVehicleNo()))
                incomeSy.setVehicleType("00");
            else
                incomeSy.setVehicleType("01");
            incomeSy.setInsuredName(tbQuery.getInsuredName());
            incomeSy.setInsuredCertNo(tbQuery.getInsuredCertNo());
            incomeSy.setInsrncBgnTm(tbQuery.getInsrncBgnTm());
            incomeSy.setInsrncEndTm(tbQuery.getInsrncEndTm());
            incomeSy.setQueryServiceId(tbQuery.getQueryId());
            incomeSy.setQueryServiceName("");
            incomeSy.setCheckServiceId("");
            incomeSy.setCheckServiceName("");

            Map<String, Object> rateParam = new HashMap<String, Object>();

            rateParam.put("channelId", incomeSy.getChannelId());
            rateParam.put("insrncType", incomeSy.getInsrncType());
            rateParam.put("belongType", incomeSy.getBelongType());
            rateParam.put("useType", useType);
            rateParam.put("vehicleType", incomeSy.getVehicleType());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate time = LocalDate.now();
            String localTime = df.format(time);

            rateParam.put("effectDate", localTime);
            rateParam.put("areaCode", tbQuery.getAreaCode());

            if(tbQuery.getOrgId()!=null&&!"".equals(tbQuery.getOrgId())){
                TbOrganization  orge= organizationMapper.selectTbOrganizationById(Long.valueOf(tbQuery.getOrgId()));
                if(orge!=null&&!"".equals(orge)) {
                    if(orge.getParentOrgid()!=null && !"".equals(orge.getParentOrgid())){
                        TbOrganization  orgaEntity= organizationMapper.selectTbOrganizationById(Long.valueOf(orge.getParentOrgid()));
                        if(orgaEntity!=null && !"".equals(orgaEntity)){
                            //Type 00 为省级机构
                            if(!orgaEntity.getType().equals("00")){
                                //父级机构id
//                                cpyRate.setOrgId(orgaEntity.getoId()+"");
                                rateParam.put("orgId",orgaEntity.getoId());
                            }else {
                                //机构ID
//                                cpyRate.setOrgId(tbQuery.getOrgId());
                                rateParam.put("orgId",tbQuery.getOrgId());
                            }
                        }
                    }
                }
            }

            List<TbInsrncCpyRate> insrncRateList = tbInsrncCpyRateMapper.selectTbInsrncCpyRateListByMap(rateParam);

            if(insrncRateList!=null && insrncRateList.size()>0) {

                TbInsrncCpyRate insrncRate = insrncRateList.get(0);

                incomeSy.setTotalRate(new BigDecimal(insrncRate.getTotalRate()));
                incomeSy.setTotalIncome(bPremiumSy.multiply(incomeSy.getTotalRate()).multiply(new BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_UP));
                incomeSy.setAgentRate(new BigDecimal(insrncRate.getAgentRate()));
                incomeSy.setAgentIncome(bPremiumSy.multiply(incomeSy.getAgentRate()).multiply(new BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_UP));
                incomeSy.setaRate(new BigDecimal(insrncRate.getaRate()));
                incomeSy.setaIncome(bPremiumSy.multiply(incomeSy.getaRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));
                incomeSy.setbRate(new BigDecimal(insrncRate.getbRate()));
                incomeSy.setbIncome(bPremiumSy.multiply(incomeSy.getbRate()).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));

                BigDecimal pRate = new BigDecimal("0");
                pRate = new BigDecimal(insrncRate.getTotalRate())
                        .subtract(new BigDecimal(insrncRate.getAgentRate()))
                        .subtract(new BigDecimal(insrncRate.getaRate()))
                        .subtract(new BigDecimal(insrncRate.getbRate()));


                //没有上级
                if (org.apache.commons.lang3.StringUtils.isEmpty(aId)||"0".equals(aId)) {
                    pRate = new BigDecimal(insrncRate.getaRate()).add(new BigDecimal(insrncRate.getbRate())).add(pRate);
                } else {

                    if (org.apache.commons.lang3.StringUtils.isEmpty(bId)||"0".equals(bId)) {
                        //只有一个上级
                        pRate = new BigDecimal(insrncRate.getbRate()).add(pRate);
                    }
                }
                incomeSy.setpRate(pRate);
                incomeSy.setpIncome(bPremiumJq.multiply(pRate).multiply(new BigDecimal("0.01")).setScale(2,BigDecimal.ROUND_HALF_UP));

                tbIncomeMapper.insertTbIncome(incomeSy);
            }
        }else{
            incomeSy.setAgentIncome(new BigDecimal("0"));
            incomeSy.setaIncome(new BigDecimal("0"));
            incomeSy.setbIncome(new BigDecimal("0"));
        }

        //出奇兵才能制胜
        //直接代理人收益
        BigDecimal amount = new BigDecimal("0");
        if(incomeJq.getAgentIncome() != null){
            amount = amount.add(incomeJq.getAgentIncome());
        }
        if(incomeSy.getAgentIncome() != null){
            amount = amount.add(incomeSy.getAgentIncome());
        }
        BigDecimal jqAgent = new BigDecimal("0");
        if(incomeJq.getAgentIncome() != null){
            jqAgent = jqAgent.add(incomeJq.getAgentIncome());
        }
        BigDecimal syAgent = new BigDecimal("0");
        if(incomeSy.getAgentIncome() != null){
            syAgent = syAgent.add(incomeSy.getAgentIncome());
        }
        String remark = "交强险收益:"+jqAgent.toString()+", 商业险收益："+syAgent.toString();
        //代理人收益
        calTrans(tbQuery, tbUserInfoVo, amount, "00", remark);

        if(org.apache.commons.lang3.StringUtils.isNotEmpty(aId)&&!"0".equals(aId)){
            //A层级收入
            BigDecimal aAmount = new BigDecimal("0");
            if(incomeSy.getaIncome() != null){
                aAmount = aAmount.add(incomeSy.getaIncome());
            }
            if(incomeJq.getaIncome() != null){
                aAmount = aAmount.add(incomeJq.getaIncome());
            }
            if(aAmount.compareTo(BigDecimal.ZERO) !=  0 ){
                calTrans(tbQuery, aUserInfo, aAmount, "01", "");
            }

            if(org.apache.commons.lang3.StringUtils.isNotEmpty(bId)&& !"0".equals(bId)){
                //B层级收入
                BigDecimal bAmount = new BigDecimal("0");
                if(incomeSy.getbIncome() != null){
                    bAmount = bAmount.add(incomeSy.getbIncome());
                }
                if(incomeJq.getbIncome() != null){
                    bAmount = bAmount.add(incomeJq.getbIncome());
                }
                if(amount.compareTo(BigDecimal.ZERO) !=  0 ){
                    calTrans(tbQuery, bUserInfo, bAmount, "02", "");
                }

            }
        }

    }

    /**
     * 算积分
     * @param tbQuery
     */
    @Override
    public void insertIntegral(TbQuery tbQuery) {
        //运营车辆不记积分，不区分个人用车和单位用车
        //判断使用性质，暂时不考虑单交强
        //不考虑交强险的意见如下：
        //  交强险没有几个点，然后还要拆分为收益和积分，可能一台车的积分还不够到商城买任何东西，
        //  所以，还不如收益里面调高一点，积分就不要计
        log.info("cal:{}",tbQuery.getQueryId());
        TbInsrncCpy tbInsrncCpy = new TbInsrncCpy();
        tbInsrncCpy.setChannelId(tbQuery.getChannelId());
        List<TbInsrncCpy> iList = tbInsrncCpyMapper.selectTbInsrncCpyList(tbInsrncCpy);
        if (iList == null || iList.size() == 0) {
            log.info("木有找到此保险公司" + tbQuery.getChannelId() + "，返回");
            return;
        } else {
            TbInsrncCpy ice = iList.get(0);
            if ("0".equals(ice.getIntegralStatus())) {
                log.info(ice.getInsrncName() + "积分状态未启用");
                return;
            }
        }
        String ut = tbQuery.getUseType();
        if ("01".equals(ut) || "02".equals(ut) || "03".equals(ut) || "04".equals(ut) || "08".equals(ut) || "07".equals(ut) || "09".equals(ut)) {
            log.info("询价单号：" + tbQuery.getQueryId() + ", 使用性质代码为：" + ut + ", 不计积分");
            return;
        }

        BigDecimal bPremiumSy = new BigDecimal("0");
        if (tbQuery.getPremiumSy() != null && !"".equals(tbQuery.getPremiumSy()))
            bPremiumSy = new BigDecimal(tbQuery.getPremiumSy());

        if (bPremiumSy.compareTo(new BigDecimal("0")) <= 0) {
            log.info("单交强险暂时不考虑");
        }

        String agentId = tbQuery.getAgentId();
        TbUserInfoVo user = tbUserInfoMapper.selectTbUserInfoById(Integer.parseInt(agentId));
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("channelId", tbQuery.getChannelId());
        parameter.put("orgId", user.getUOrgId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        parameter.put("effectDate", sdf.format(new Date()));

        IntegralRateEntity ir = tbIncomeMapper.findIntegralRate(parameter);
        if (ir == null) {
            log.info("积分费率未设置");
            return;
        }
        BigDecimal bAmount = bPremiumSy.multiply(new BigDecimal(ir.getAgentRate()))
                .multiply(new BigDecimal("0.01"))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal bAm = bAmount.add(new BigDecimal(user.getUAvailableMoney()));
        HontxTransEntity ht = new HontxTransEntity();
        ht.setTransNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) +
                IdUtils.fastSimpleUUID());
        ht.setTransType("06");
        ht.setAmount(bAmount.toString());
        ht.setAgentId(agentId);
        ht.setAvailableMoney(bAm.toString());
        ht.setVehicleNo(tbQuery.getVehicleNo());
        ht.setRackNo(tbQuery.getRackNo());
        ht.setQueryId(tbQuery.getQueryId());
        ht.setRemark("积分收益");
        ht.setStatus("01");
        tbIncomeMapper.insertHontxTrans(ht);

        // 更新账户余额
        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setUId(user.getUId());
        tbUserInfo.setUAvailableMoney(bAm.toString());
        tbUserInfoMapper.updateTbUserInfo(tbUserInfo);
    }

    /**
     * 插入通知记录
     * @param informEntity
     * @return
     */
    @Override
    public int insertInform(InformEntity informEntity) {
        return tbIncomeMapper.insertInform(informEntity);
    }

    public void calTrans(TbQuery queryEntity, TbUserInfoVo userEntity, BigDecimal amount, String transType, String remark){

        String availBal = userEntity.getUAvailBal();
        availBal = new BigDecimal(availBal).add(amount).toString();
        userEntity.setUAvailBal(availBal);
        String totalIncome = userEntity.getUTotalIncome();
        totalIncome = new BigDecimal(totalIncome).add(amount).toString();
        userEntity.setUTotalIncome(totalIncome);
        tbUserInfoMapper.updateTbUserInfo(userEntity);

        TbTrans transEntity = new TbTrans();
        transEntity.setTransNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + IdUtils.getFixLengthString(3));
        transEntity.setTransType(transType);
//        transEntity.setCreateTime(new Date());
        transEntity.settCreateTime(new Date());
        transEntity.setUserId(String.valueOf(userEntity.getUId()));
        transEntity.setAmount(amount.toString());
        transEntity.setUserAmount(availBal);
        transEntity.setVehicleId(queryEntity.getVehicleNo());
        transEntity.setQueryId(queryEntity.getQueryId());
        transEntity.setRemark(remark);
        tbTransMapper.insertTbTrans(transEntity);
    }
}
