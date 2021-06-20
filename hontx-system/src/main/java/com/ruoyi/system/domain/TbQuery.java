package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 已询价列表对象 tb_query
 *
 * @author ruoyi
 * @date 2021-03-30
 */
public class TbQuery extends BaseEntity
{
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insrncBgnTm;

    /** 商业险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insrncEndTm;

    /** 交强险开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insrncBgnTmJq;

    /** 交强险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date insrncEndTmJq;

    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    /** 服务Id */
    private String serviceId;

    /** 服务姓名 */
    private String serviceName;

    /** 内容 */
    private String content;

    /** 备注 **/
    private String remark;

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

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    /** $column.columnComment */
    private String templateFlag;

    /** $column.columnComment */
    private String templateJq;

    public void setqId(Long qId)
    {
        this.qId = qId;
    }

    public Long getqId()
    {
        return qId;
    }
    public void setQueryId(String queryId)
    {
        this.queryId = queryId;
    }

    public String getQueryId()
    {
        return queryId;
    }
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getChannelId()
    {
        return channelId;
    }
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }

    public String getDeptNo()
    {
        return deptNo;
    }
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo()
    {
        return vehicleNo;
    }
    public void setEngineNo(String engineNo)
    {
        this.engineNo = engineNo;
    }

    public String getEngineNo()
    {
        return engineNo;
    }
    public void setRackNo(String rackNo)
    {
        this.rackNo = rackNo;
    }

    public String getRackNo()
    {
        return rackNo;
    }
    public void setEtBrand(String etBrand)
    {
        this.etBrand = etBrand;
    }

    public String getEtBrand()
    {
        return etBrand;
    }
    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }

    public String getBrandCode()
    {
        return brandCode;
    }
    public void setVehicleValue(String vehicleValue)
    {
        this.vehicleValue = vehicleValue;
    }

    public String getVehicleValue()
    {
        return vehicleValue;
    }
    public void setBelongType(String belongType)
    {
        this.belongType = belongType;
    }

    public String getBelongType()
    {
        return belongType;
    }
    public void setUseType(String useType)
    {
        this.useType = useType;
    }

    public String getUseType()
    {
        return useType;
    }
    public void setExtMsr(String extMsr)
    {
        this.extMsr = extMsr;
    }

    public String getExtMsr()
    {
        return extMsr;
    }
    public void setSetNum(String setNum)
    {
        this.setNum = setNum;
    }

    public String getSetNum()
    {
        return setNum;
    }
    public void setTonnage(String tonnage)
    {
        this.tonnage = tonnage;
    }

    public String getTonnage()
    {
        return tonnage;
    }
    public void setChangeOwnerFlag(String changeOwnerFlag)
    {
        this.changeOwnerFlag = changeOwnerFlag;
    }

    public String getChangeOwnerFlag()
    {
        return changeOwnerFlag;
    }
    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }

    public Date getTransferDate()
    {
        return transferDate;
    }
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getOwner()
    {
        return owner;
    }
    public void setOwnerCertType(String ownerCertType)
    {
        this.ownerCertType = ownerCertType;
    }

    public String getOwnerCertType()
    {
        return ownerCertType;
    }
    public void setOwnerCertNo(String ownerCertNo)
    {
        this.ownerCertNo = ownerCertNo;
    }

    public String getOwnerCertNo()
    {
        return ownerCertNo;
    }
    public void setOwnerTel(String ownerTel)
    {
        this.ownerTel = ownerTel;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOwnerTel()
    {
        return ownerTel;
    }
    public void setInsrncBgnTm(Date insrncBgnTm)
    {
        this.insrncBgnTm = insrncBgnTm;
    }

    public Date getInsrncBgnTm()
    {
        return insrncBgnTm;
    }
    public void setInsrncEndTm(Date insrncEndTm)
    {
        this.insrncEndTm = insrncEndTm;
    }

    public Date getInsrncEndTm()
    {
        return insrncEndTm;
    }
    public void setInsrncBgnTmJq(Date insrncBgnTmJq)
    {
        this.insrncBgnTmJq = insrncBgnTmJq;
    }

    public Date getInsrncBgnTmJq()
    {
        return insrncBgnTmJq;
    }
    public void setInsrncEndTmJq(Date insrncEndTmJq)
    {
        this.insrncEndTmJq = insrncEndTmJq;
    }

    public Date getInsrncEndTmJq()
    {
        return insrncEndTmJq;
    }
    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }
    public void setApplicantName(String applicantName)
    {
        this.applicantName = applicantName;
    }

    public String getApplicantName()
    {
        return applicantName;
    }
    public void setApplicantCertType(String applicantCertType)
    {
        this.applicantCertType = applicantCertType;
    }

    public String getApplicantCertType()
    {
        return applicantCertType;
    }
    public void setApplicantCertNo(String applicantCertNo)
    {
        this.applicantCertNo = applicantCertNo;
    }

    public String getApplicantCertNo()
    {
        return applicantCertNo;
    }
    public void setApplicantTel(String applicantTel)
    {
        this.applicantTel = applicantTel;
    }

    public String getApplicantTel()
    {
        return applicantTel;
    }
    public void setApplicantSex(String applicantSex)
    {
        this.applicantSex = applicantSex;
    }

    public String getApplicantSex()
    {
        return applicantSex;
    }
    public void setApplicantAge(String applicantAge)
    {
        this.applicantAge = applicantAge;
    }

    public String getApplicantAge()
    {
        return applicantAge;
    }
    public void setInsuredName(String insuredName)
    {
        this.insuredName = insuredName;
    }

    public String getInsuredName()
    {
        return insuredName;
    }
    public void setInsuredCertType(String insuredCertType)
    {
        this.insuredCertType = insuredCertType;
    }

    public String getInsuredCertType()
    {
        return insuredCertType;
    }
    public void setInsuredCertNo(String insuredCertNo)
    {
        this.insuredCertNo = insuredCertNo;
    }

    public String getInsuredCertNo()
    {
        return insuredCertNo;
    }
    public void setInsuredTel(String insuredTel)
    {
        this.insuredTel = insuredTel;
    }

    public String getInsuredTel()
    {
        return insuredTel;
    }
    public void setInsuredSex(String insuredSex)
    {
        this.insuredSex = insuredSex;
    }

    public String getInsuredSex()
    {
        return insuredSex;
    }
    public void setInsuredAge(String insuredAge)
    {
        this.insuredAge = insuredAge;
    }

    public String getInsuredAge()
    {
        return insuredAge;
    }
    public void setInstituteCode(String instituteCode)
    {
        this.instituteCode = instituteCode;
    }

    public String getInstituteCode()
    {
        return instituteCode;
    }
    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getAgentId()
    {
        return agentId;
    }
    public void setAgentName(String agentName)
    {
        this.agentName = agentName;
    }

    public String getAgentName()
    {
        return agentName;
    }
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceId()
    {
        return serviceId;
    }
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setCalAppNo(String calAppNo)
    {
        this.calAppNo = calAppNo;
    }

    public String getCalAppNo()
    {
        return calAppNo;
    }
    public void setJqIsImmefc(String jqIsImmefc)
    {
        this.jqIsImmefc = jqIsImmefc;
    }

    public String getJqIsImmefc()
    {
        return jqIsImmefc;
    }
    public void setSyIsImmefc(String syIsImmefc)
    {
        this.syIsImmefc = syIsImmefc;
    }

    public String getSyIsImmefc()
    {
        return syIsImmefc;
    }
    public void setJqPlyAppNo(String jqPlyAppNo)
    {
        this.jqPlyAppNo = jqPlyAppNo;
    }

    public String getJqPlyAppNo()
    {
        return jqPlyAppNo;
    }
    public void setSyPlyAppNo(String syPlyAppNo)
    {
        this.syPlyAppNo = syPlyAppNo;
    }

    public String getSyPlyAppNo()
    {
        return syPlyAppNo;
    }
    public void setJqAppNo(String jqAppNo)
    {
        this.jqAppNo = jqAppNo;
    }

    public String getJqAppNo()
    {
        return jqAppNo;
    }
    public void setSyAppNo(String syAppNo)
    {
        this.syAppNo = syAppNo;
    }

    public String getSyAppNo()
    {
        return syAppNo;
    }
    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlag()
    {
        return flag;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setRespCode(String respCode)
    {
        this.respCode = respCode;
    }

    public String getRespCode()
    {
        return respCode;
    }
    public void setRespMsg(String respMsg)
    {
        this.respMsg = respMsg;
    }

    public String getRespMsg()
    {
        return respMsg;
    }
    public void setPremium(String premium)
    {
        this.premium = premium;
    }

    public String getPremium()
    {
        return premium;
    }
    public void setPremiumSy(String premiumSy)
    {
        this.premiumSy = premiumSy;
    }

    public String getPremiumSy()
    {
        return premiumSy;
    }
    public void setPremiumJq(String premiumJq)
    {
        this.premiumJq = premiumJq;
    }

    public String getPremiumJq()
    {
        return premiumJq;
    }
    public void setTax(String tax)
    {
        this.tax = tax;
    }

    public String getTax()
    {
        return tax;
    }
    public void setqCreateTime(Date qCreateTime)
    {
        this.qCreateTime = qCreateTime;
    }

    public Date getqCreateTime()
    {
        return qCreateTime;
    }
    public void setqUpdateTime(Date qUpdateTime)
    {
        this.qUpdateTime = qUpdateTime;
    }

    public Date getqUpdateTime()
    {
        return qUpdateTime;
    }
    public void setPayAppNo(String payAppNo)
    {
        this.payAppNo = payAppNo;
    }

    public String getPayAppNo()
    {
        return payAppNo;
    }
    public void setExpressType(String expressType)
    {
        this.expressType = expressType;
    }

    public String getExpressType()
    {
        return expressType;
    }
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }
    public void setOrgId(String orgId)
    {
        this.orgId = orgId;
    }

    public String getOrgId()
    {
        return orgId;
    }
    public void setTemplateFlag(String templateFlag)
    {
        this.templateFlag = templateFlag;
    }

    public String getTemplateFlag()
    {
        return templateFlag;
    }
    public void setTemplateJq(String templateJq)
    {
        this.templateJq = templateJq;
    }

    public String getTemplateJq()
    {
        return templateJq;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qId", getqId())
            .append("queryId", getQueryId())
            .append("channelId", getChannelId())
            .append("deptNo", getDeptNo())
            .append("vehicleNo", getVehicleNo())
            .append("engineNo", getEngineNo())
            .append("rackNo", getRackNo())
            .append("etBrand", getEtBrand())
            .append("brandCode", getBrandCode())
            .append("vehicleValue", getVehicleValue())
            .append("belongType", getBelongType())
            .append("useType", getUseType())
            .append("extMsr", getExtMsr())
            .append("setNum", getSetNum())
            .append("tonnage", getTonnage())
            .append("changeOwnerFlag", getChangeOwnerFlag())
            .append("transferDate", getTransferDate())
            .append("owner", getOwner())
            .append("ownerCertType", getOwnerCertType())
            .append("ownerCertNo", getOwnerCertNo())
            .append("ownerTel", getOwnerTel())
            .append("insrncBgnTm", getInsrncBgnTm())
            .append("insrncEndTm", getInsrncEndTm())
            .append("insrncBgnTmJq", getInsrncBgnTmJq())
            .append("insrncEndTmJq", getInsrncEndTmJq())
            .append("registerDate", getRegisterDate())
            .append("applicantName", getApplicantName())
            .append("applicantCertType", getApplicantCertType())
            .append("applicantCertNo", getApplicantCertNo())
            .append("applicantTel", getApplicantTel())
            .append("applicantSex", getApplicantSex())
            .append("applicantAge", getApplicantAge())
            .append("insuredName", getInsuredName())
            .append("insuredCertType", getInsuredCertType())
            .append("insuredCertNo", getInsuredCertNo())
            .append("insuredTel", getInsuredTel())
            .append("insuredSex", getInsuredSex())
            .append("insuredAge", getInsuredAge())
            .append("instituteCode", getInstituteCode())
            .append("agentId", getAgentId())
            .append("agentName", getAgentName())
            .append("serviceId", getServiceId())
            .append("serviceName", getServiceName())
            .append("content", getContent())
            .append("calAppNo", getCalAppNo())
            .append("jqIsImmefc", getJqIsImmefc())
            .append("syIsImmefc", getSyIsImmefc())
            .append("jqPlyAppNo", getJqPlyAppNo())
            .append("syPlyAppNo", getSyPlyAppNo())
            .append("jqAppNo", getJqAppNo())
            .append("syAppNo", getSyAppNo())
            .append("flag", getFlag())
            .append("status", getStatus())
            .append("respCode", getRespCode())
            .append("respMsg", getRespMsg())
            .append("premium", getPremium())
            .append("premiumSy", getPremiumSy())
            .append("premiumJq", getPremiumJq())
            .append("tax", getTax())
            .append("qCreateTime", getqCreateTime())
            .append("qUpdateTime", getqUpdateTime())
            .append("payAppNo", getPayAppNo())
            .append("expressType", getExpressType())
            .append("areaCode", getAreaCode())
            .append("orgId", getOrgId())
            .append("remark", getRemark())
            .append("templateFlag", getTemplateFlag())
            .append("templateJq", getTemplateJq())
            .toString();
    }
}
