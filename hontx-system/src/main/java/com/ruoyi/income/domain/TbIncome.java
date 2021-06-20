package com.ruoyi.income.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售业绩对象 tb_income
 *
 * @author ruoyi
 * @date 2021-04-19
 */
public class TbIncome extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "编号")
    private Long iId;

    /** 保单号 */
    @Excel(name = "保单号")
    private String appNo;

    /** a团队Id */
    private String aTeamId;

    /** A团队 */
    @Excel(name = "A团队")
    private String aTeam;

    /** $column.columnComment */
    private String bTeamId;

    /** B团队 */
    @Excel(name = "B团队")
    private String bTeam;

    /** 月份 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 代理人Id */
    @Excel(name = "代理人Id")
    private String agentId;

    /** 代理人 */
    @Excel(name = "代理人")
    private String agentName;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String vehicleNo;

    /** 车架 */
    @Excel(name = "车架")
    private String rackNo;

    /** 发动机号 */
    @Excel(name = "发动机号")
    private String engineNo;

    /** 座位数 */
    @Excel(name = "座位数")
    private String setNum;

    /** 初登日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "初登日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerDate;

    /** 所属性质 */
    @Excel(name = "所属性质")
    private String belongType;

    /** 使用性质 */
    private String specificUseType;

    /** 询价Id */
    @Excel(name = "询价Id")
    private String queryId;

    /** 保险公司 */
    @Excel(name = "保险公司")
    private String channelId;

    /** 保险类型 */
    @Excel(name = "保险类型")
    private String insrncType;

    /** 使用性质 */
    @Excel(name = "使用性质")
    private String useType;

    /** $column.columnComment */
    private String vehicleType;

    /** 被保人 */
    @Excel(name = "被保人")
    private String insuredName;

    /** 身份证 */
    @Excel(name = "身份证")
    private String insuredCertNo;

    /** 起保时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起保时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insrncBgnTm;

    /** 到期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insrncEndTm;

    /** $column.columnComment */
    private String queryServiceId;

    /** $column.columnComment */
    private String queryServiceName;

    /** $column.columnComment */
    private String checkServiceId;

    /** $column.columnComment */
    private String checkServiceName;

    /** 总结算金额 */
//    @Excel(name = "总结算金额")
    private BigDecimal premium;

    /** 总代理费率 */
//    @Excel(name = "总代理费率")
    private BigDecimal totalRate;

    /** A管理费率 */
//    @Excel(name = "A管理费率")
    private BigDecimal totalIncome;

    /** 总结算金额 */
//    @Excel(name = "总结算金额")
    private BigDecimal agentRate;

    /** 代理输入 */
//    @Excel(name = "代理输入")
    private BigDecimal agentIncome;

    /** A管理费率 */
//    @Excel(name = "A管理费率")
    private BigDecimal aRate;

    /** A管理津贴 */
//    @Excel(name = "A管理津贴")
    private BigDecimal aIncome;

    /** B管理费率 */
//    @Excel(name = "B管理费率")
    private BigDecimal bRate;

    /** B管理津贴 */
//    @Excel(name = "B管理津贴")
    private BigDecimal bIncome;

    /** 平台收益率 */
//    @Excel(name = "平台收益率")
    private BigDecimal pRate;

    /** 平台收益金额 */
//    @Excel(name = "平台收益金额")
    private BigDecimal pIncome;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date iCreateTime;

    /** 修改时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date iUpdateTime;

    public void setiId(Long iId)
    {
        this.iId = iId;
    }

    public Long getiId()
    {
        return iId;
    }
    public void setAppNo(String appNo)
    {
        this.appNo = appNo;
    }

    public String getAppNo()
    {
        return appNo;
    }
    public void setaTeamId(String aTeamId)
    {
        this.aTeamId = aTeamId;
    }

    public String getaTeamId()
    {
        return aTeamId;
    }
    public void setaTeam(String aTeam)
    {
        this.aTeam = aTeam;
    }

    public String getaTeam()
    {
        return aTeam;
    }
    public void setbTeamId(String bTeamId)
    {
        this.bTeamId = bTeamId;
    }

    public String getbTeamId()
    {
        return bTeamId;
    }
    public void setbTeam(String bTeam)
    {
        this.bTeam = bTeam;
    }

    public String getbTeam()
    {
        return bTeam;
    }
    public void setCheckDate(Date checkDate)
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate()
    {
        return checkDate;
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
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo()
    {
        return vehicleNo;
    }
    public void setRackNo(String rackNo)
    {
        this.rackNo = rackNo;
    }

    public String getRackNo()
    {
        return rackNo;
    }
    public void setEngineNo(String engineNo)
    {
        this.engineNo = engineNo;
    }

    public String getEngineNo()
    {
        return engineNo;
    }
    public void setSetNum(String setNum)
    {
        this.setNum = setNum;
    }

    public String getSetNum()
    {
        return setNum;
    }
    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }
    public void setBelongType(String belongType)
    {
        this.belongType = belongType;
    }

    public String getBelongType()
    {
        return belongType;
    }
    public void setSpecificUseType(String specificUseType)
    {
        this.specificUseType = specificUseType;
    }

    public String getSpecificUseType()
    {
        return specificUseType;
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
    public void setInsrncType(String insrncType)
    {
        this.insrncType = insrncType;
    }

    public String getInsrncType()
    {
        return insrncType;
    }
    public void setUseType(String useType)
    {
        this.useType = useType;
    }

    public String getUseType()
    {
        return useType;
    }
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
    public void setInsuredName(String insuredName)
    {
        this.insuredName = insuredName;
    }

    public String getInsuredName()
    {
        return insuredName;
    }
    public void setInsuredCertNo(String insuredCertNo)
    {
        this.insuredCertNo = insuredCertNo;
    }

    public String getInsuredCertNo()
    {
        return insuredCertNo;
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
    public void setQueryServiceId(String queryServiceId)
    {
        this.queryServiceId = queryServiceId;
    }

    public String getQueryServiceId()
    {
        return queryServiceId;
    }
    public void setQueryServiceName(String queryServiceName)
    {
        this.queryServiceName = queryServiceName;
    }

    public String getQueryServiceName()
    {
        return queryServiceName;
    }
    public void setCheckServiceId(String checkServiceId)
    {
        this.checkServiceId = checkServiceId;
    }

    public String getCheckServiceId()
    {
        return checkServiceId;
    }
    public void setCheckServiceName(String checkServiceName)
    {
        this.checkServiceName = checkServiceName;
    }

    public String getCheckServiceName()
    {
        return checkServiceName;
    }
    public void setPremium(BigDecimal premium)
    {
        this.premium = premium;
    }

    public BigDecimal getPremium()
    {
        return premium;
    }
    public void setTotalRate(BigDecimal totalRate)
    {
        this.totalRate = totalRate;
    }

    public BigDecimal getTotalRate()
    {
        return totalRate;
    }
    public void setTotalIncome(BigDecimal totalIncome)
    {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalIncome()
    {
        return totalIncome;
    }
    public void setAgentRate(BigDecimal agentRate)
    {
        this.agentRate = agentRate;
    }

    public BigDecimal getAgentRate()
    {
        return agentRate;
    }
    public void setAgentIncome(BigDecimal agentIncome)
    {
        this.agentIncome = agentIncome;
    }

    public BigDecimal getAgentIncome()
    {
        return agentIncome;
    }
    public void setaRate(BigDecimal aRate)
    {
        this.aRate = aRate;
    }

    public BigDecimal getaRate()
    {
        return aRate;
    }
    public void setaIncome(BigDecimal aIncome)
    {
        this.aIncome = aIncome;
    }

    public BigDecimal getaIncome()
    {
        return aIncome;
    }
    public void setbRate(BigDecimal bRate)
    {
        this.bRate = bRate;
    }

    public BigDecimal getbRate()
    {
        return bRate;
    }
    public void setbIncome(BigDecimal bIncome)
    {
        this.bIncome = bIncome;
    }

    public BigDecimal getbIncome()
    {
        return bIncome;
    }
    public void setpRate(BigDecimal pRate)
    {
        this.pRate = pRate;
    }

    public BigDecimal getpRate()
    {
        return pRate;
    }
    public void setpIncome(BigDecimal pIncome)
    {
        this.pIncome = pIncome;
    }

    public BigDecimal getpIncome()
    {
        return pIncome;
    }
    public void setiCreateTime(Date iCreateTime)
    {
        this.iCreateTime = iCreateTime;
    }

    public Date getiCreateTime()
    {
        return iCreateTime;
    }
    public void setiUpdateTime(Date iUpdateTime)
    {
        this.iUpdateTime = iUpdateTime;
    }

    public Date getiUpdateTime()
    {
        return iUpdateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iId", getiId())
            .append("appNo", getAppNo())
            .append("aTeamId", getaTeamId())
            .append("aTeam", getaTeam())
            .append("bTeamId", getbTeamId())
            .append("bTeam", getbTeam())
            .append("checkDate", getCheckDate())
            .append("agentId", getAgentId())
            .append("agentName", getAgentName())
            .append("vehicleNo", getVehicleNo())
            .append("rackNo", getRackNo())
            .append("engineNo", getEngineNo())
            .append("setNum", getSetNum())
            .append("registerDate", getRegisterDate())
            .append("belongType", getBelongType())
            .append("specificUseType", getSpecificUseType())
            .append("queryId", getQueryId())
            .append("channelId", getChannelId())
            .append("insrncType", getInsrncType())
            .append("useType", getUseType())
            .append("vehicleType", getVehicleType())
            .append("insuredName", getInsuredName())
            .append("insuredCertNo", getInsuredCertNo())
            .append("insrncBgnTm", getInsrncBgnTm())
            .append("insrncEndTm", getInsrncEndTm())
            .append("queryServiceId", getQueryServiceId())
            .append("queryServiceName", getQueryServiceName())
            .append("checkServiceId", getCheckServiceId())
            .append("checkServiceName", getCheckServiceName())
            .append("premium", getPremium())
            .append("totalRate", getTotalRate())
            .append("totalIncome", getTotalIncome())
            .append("agentRate", getAgentRate())
            .append("agentIncome", getAgentIncome())
            .append("aRate", getaRate())
            .append("aIncome", getaIncome())
            .append("bRate", getbRate())
            .append("bIncome", getbIncome())
            .append("pRate", getpRate())
            .append("pIncome", getpIncome())
            .append("iCreateTime", getiCreateTime())
            .append("iUpdateTime", getiUpdateTime())
            .toString();
    }
}
