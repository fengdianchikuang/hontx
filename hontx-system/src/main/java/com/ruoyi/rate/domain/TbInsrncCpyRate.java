package com.ruoyi.rate.domain;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费率设置对象 tb_insrnc_cpy_rate
 *
 * @author ruoyi
 * @date 2021-04-12
 */

public class TbInsrncCpyRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long iId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String channelId;

    /** 险种组合 */
    @Excel(name = "险种组合")
    private String insrncType;

    /** 所属性质 */
    @Excel(name = "所属性质")
    private String belongType;

    /** 使用性质 */
    @Excel(name = "使用性质")
    private String useType;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 总费率(%) */
    @Excel(name = "总费率(%)")
    private String totalRate;

    /** 代理人收益率(%) */
    @Excel(name = "代理人收益率(%)")
    private String agentRate;

    /** A管理收益率(%) */
    @Excel(name = "A管理收益率(%)")
    private String aRate;

    /** B管理收益率（%） */
    @Excel(name = "B管理收益率", readConverterExp = "%=")
    private String bRate;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectDate;

    private String effectDateString;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date iCreateTime;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date iUpdateTime;

    /** 区域码 */
    @Excel(name = "区域码")
    private String areaCode;

    /** 机构Id */
    @Excel(name = "机构Id")
    private String orgId;

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getInsrncType() {
        return insrncType;
    }

    public void setInsrncType(String insrncType) {
        this.insrncType = insrncType;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    public String getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(String agentRate) {
        this.agentRate = agentRate;
    }

    public String getaRate() {
        return aRate;
    }

    public void setaRate(String aRate) {
        this.aRate = aRate;
    }

    public String getbRate() {
        return bRate;
    }

    public void setbRate(String bRate) {
        this.bRate = bRate;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getEffectDateString() {
        return effectDateString;
    }

    public void setEffectDateString(String effectDateString) {
        this.effectDateString = effectDateString;
    }

    public Date getiCreateTime() {
        return iCreateTime;
    }

    public void setiCreateTime(Date iCreateTime) {
        this.iCreateTime = iCreateTime;
    }

    public Date getiUpdateTime() {
        return iUpdateTime;
    }

    public void setiUpdateTime(Date iUpdateTime) {
        this.iUpdateTime = iUpdateTime;
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

    @Override
    public String toString() {
        return "TbInsrncCpyRate{" +
                "iId=" + iId +
                ", channelId='" + channelId + '\'' +
                ", insrncType='" + insrncType + '\'' +
                ", belongType='" + belongType + '\'' +
                ", useType='" + useType + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", totalRate='" + totalRate + '\'' +
                ", agentRate='" + agentRate + '\'' +
                ", aRate='" + aRate + '\'' +
                ", bRate='" + bRate + '\'' +
                ", effectDate=" + effectDate +
                ", effectDateString='" + effectDateString + '\'' +
                ", iCreateTime=" + iCreateTime +
                ", iUpdateTime=" + iUpdateTime +
                ", areaCode='" + areaCode + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
