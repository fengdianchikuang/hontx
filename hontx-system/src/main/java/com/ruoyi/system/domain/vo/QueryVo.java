package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;


public class QueryVo {
    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long qId;

    /** 询价Id */
    @Excel(name = "询价Id")
    private String queryId;

    /** 保险Id */
    @Excel(name = "保险Id")
    private String channelId;

    /** $column.columnComment */
    private String deptNo;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehicleNo;

    /** 发动机号 */
    private String engineNo;

    /** 车架号 */
    private String rackNo;

    /** 品牌 */
    private String etBrand;

    /** 品牌型号 */
    private String brandCode;

    /** 车辆重量 */
    private String vehicleValue;

    /** 所属性质 */
    private String belongType;

    /** 使用类型 */
    private String useType;

    /** 排气量 */
    private String extMsr;

    /** 座位数 */
    private String setNum;

    /** 吨位 */
    private String tonnage;

    /** 转让标识 */
    private String changeOwnerFlag;

    /** 转让日期 */
    private Date transferDate;

    /** 车主 */
    @Excel(name = "车主")
    private String owner;

    /** 车主证件类型 */
    private String ownerCertType;

    /** 车主证件号 */
    private String ownerCertNo;

    /** 车主电话 */
    private String ownerTel;

    /** 商业险开始日期 */
    private Date insrncBgnTm;

    /** 商业险结束日期 */
    private Date insrncEndTm;

    /** 交强险开始日期 */
    private Date insrncBgnTmJq;

    /** 交强险结束日期 */
    private Date insrncEndTmJq;

    /** 注册日期 */
    private Date registerDate;

    /** 投保人姓名 */
    private String applicantName;

    /** 投保人证件类型：00  身份证 */
    private String applicantCertType;

    /** 投保人证件号 */
    private String applicantCertNo;

    /** 投保人电话 */
    private String applicantTel;

    /** 投保人性别 */
    private String applicantSex;

    /** 投保人年龄 */
    private String applicantAge;

    /** 被投保人姓名 */
    private String insuredName;

    /** 被投保人证件类型 */
    private String insuredCertType;

    /** 被投保人证件号 */
    private String insuredCertNo;

    /** 被投保人电话 */
    private String insuredTel;

    /** 被投保人性别 */
    private String insuredSex;

    /** 被投保人年龄 */
    private String insuredAge;

    /** 保险类型 */
    private String instituteCode;

    /** 代理人Id */
    private String agentId;

    /** 代理人姓名 */
    @Excel(name = "代理人姓名")
    private String agentName;
    /**
     * 代理人电话
     */
    private String agentTel;

    /** 服务Id */
    private String serviceId;

    /** 服务姓名 */
    private String serviceName;

    /** 内容 */
    private String content;

    /** 报价单号 */
    private String calAppNo;

    /** $column.columnComment */
    private String jqIsImmefc;

    /** $column.columnComment */
    private String syIsImmefc;

    /** 交强险投保单号 */
    private String jqPlyAppNo;

    /** 商业险投保单号 */
    private String syPlyAppNo;

    /** 交强险单号 */
    private String jqAppNo;

    /** 商业险单号 */
    private String syAppNo;

    /** 询价标识0自动，1人工 */
    @Excel(name = "询价标识0自动，1人工")
    private String flag;

    /** 询价状态 */
    @Excel(name = "询价状态")
    private String status;

    /** $column.columnComment */
    private String respCode;

    /** $column.columnComment */
    private String respMsg;

    /** 保费 */
    @Excel(name = "保费")
    private String premium;

    /** 商业险保费 */
    @Excel(name = "商业险保费")
    private String premiumSy;

    /** 交强险保费 */
    @Excel(name = "交强险保费")
    private String premiumJq;

    /** 车船税 */
    @Excel(name = "车船税")
    private String tax;

    private String remark;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qUpdateTime;

    /** 支付单号 */
    private String payAppNo;

    /** $column.columnComment */
    private String expressType;

    /** 区域码 */
    private String areaCode;

    /** 机构Id */
    private String orgId;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getQueryId() {
        return queryId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getEtBrand() {
        return etBrand;
    }

    public void setEtBrand(String etBrand) {
        this.etBrand = etBrand;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(String vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getExtMsr() {
        return extMsr;
    }

    public void setExtMsr(String extMsr) {
        this.extMsr = extMsr;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public String getChangeOwnerFlag() {
        return changeOwnerFlag;
    }

    public void setChangeOwnerFlag(String changeOwnerFlag) {
        this.changeOwnerFlag = changeOwnerFlag;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerCertType() {
        return ownerCertType;
    }

    public void setOwnerCertType(String ownerCertType) {
        this.ownerCertType = ownerCertType;
    }

    public String getOwnerCertNo() {
        return ownerCertNo;
    }

    public void setOwnerCertNo(String ownerCertNo) {
        this.ownerCertNo = ownerCertNo;
    }

    public String getOwnerTel() {
        return ownerTel;
    }

    public void setOwnerTel(String ownerTel) {
        this.ownerTel = ownerTel;
    }

    public Date getInsrncBgnTm() {
        return insrncBgnTm;
    }

    public void setInsrncBgnTm(Date insrncBgnTm) {
        this.insrncBgnTm = insrncBgnTm;
    }

    public Date getInsrncEndTm() {
        return insrncEndTm;
    }

    public void setInsrncEndTm(Date insrncEndTm) {
        this.insrncEndTm = insrncEndTm;
    }

    public Date getInsrncBgnTmJq() {
        return insrncBgnTmJq;
    }

    public void setInsrncBgnTmJq(Date insrncBgnTmJq) {
        this.insrncBgnTmJq = insrncBgnTmJq;
    }

    public Date getInsrncEndTmJq() {
        return insrncEndTmJq;
    }

    public void setInsrncEndTmJq(Date insrncEndTmJq) {
        this.insrncEndTmJq = insrncEndTmJq;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantCertType() {
        return applicantCertType;
    }

    public void setApplicantCertType(String applicantCertType) {
        this.applicantCertType = applicantCertType;
    }

    public String getApplicantCertNo() {
        return applicantCertNo;
    }

    public void setApplicantCertNo(String applicantCertNo) {
        this.applicantCertNo = applicantCertNo;
    }

    public String getApplicantTel() {
        return applicantTel;
    }

    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel;
    }

    public String getApplicantSex() {
        return applicantSex;
    }

    public void setApplicantSex(String applicantSex) {
        this.applicantSex = applicantSex;
    }

    public String getApplicantAge() {
        return applicantAge;
    }

    public void setApplicantAge(String applicantAge) {
        this.applicantAge = applicantAge;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredCertType() {
        return insuredCertType;
    }

    public void setInsuredCertType(String insuredCertType) {
        this.insuredCertType = insuredCertType;
    }

    public String getInsuredCertNo() {
        return insuredCertNo;
    }

    public void setInsuredCertNo(String insuredCertNo) {
        this.insuredCertNo = insuredCertNo;
    }

    public String getInsuredTel() {
        return insuredTel;
    }

    public void setInsuredTel(String insuredTel) {
        this.insuredTel = insuredTel;
    }

    public String getInsuredSex() {
        return insuredSex;
    }

    public void setInsuredSex(String insuredSex) {
        this.insuredSex = insuredSex;
    }

    public String getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(String insuredAge) {
        this.insuredAge = insuredAge;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCalAppNo() {
        return calAppNo;
    }

    public void setCalAppNo(String calAppNo) {
        this.calAppNo = calAppNo;
    }

    public String getJqIsImmefc() {
        return jqIsImmefc;
    }

    public void setJqIsImmefc(String jqIsImmefc) {
        this.jqIsImmefc = jqIsImmefc;
    }

    public String getSyIsImmefc() {
        return syIsImmefc;
    }

    public void setSyIsImmefc(String syIsImmefc) {
        this.syIsImmefc = syIsImmefc;
    }

    public String getJqPlyAppNo() {
        return jqPlyAppNo;
    }

    public void setJqPlyAppNo(String jqPlyAppNo) {
        this.jqPlyAppNo = jqPlyAppNo;
    }

    public String getSyPlyAppNo() {
        return syPlyAppNo;
    }

    public void setSyPlyAppNo(String syPlyAppNo) {
        this.syPlyAppNo = syPlyAppNo;
    }

    public String getJqAppNo() {
        return jqAppNo;
    }

    public void setJqAppNo(String jqAppNo) {
        this.jqAppNo = jqAppNo;
    }

    public String getSyAppNo() {
        return syAppNo;
    }

    public void setSyAppNo(String syAppNo) {
        this.syAppNo = syAppNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getPremiumSy() {
        return premiumSy;
    }

    public void setPremiumSy(String premiumSy) {
        this.premiumSy = premiumSy;
    }

    public String getPremiumJq() {
        return premiumJq;
    }

    public void setPremiumJq(String premiumJq) {
        this.premiumJq = premiumJq;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Date getqCreateTime() {
        return qCreateTime;
    }

    public void setqCreateTime(Date qCreateTime) {
        this.qCreateTime = qCreateTime;
    }

    public Date getqUpdateTime() {
        return qUpdateTime;
    }

    public void setqUpdateTime(Date qUpdateTime) {
        this.qUpdateTime = qUpdateTime;
    }

    public String getPayAppNo() {
        return payAppNo;
    }

    public void setPayAppNo(String payAppNo) {
        this.payAppNo = payAppNo;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
