package com.ruoyi.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * company对象 tb_insrnc_cpy
 * 
 * @author ruoyi
 * @date 2021-04-12
 */
public class TbInsrncCpy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long iId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String insrncName;

    /** 公司代号 */
    @Excel(name = "公司代号")
    private String channelId;

    /** 状态 */
    @Excel(name = "状态")
    private String openFlag;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String artFlag;

    /** 积分状态 */
    @Excel(name = "积分状态")
    private String integralStatus;

    /** 保险公司Id */
    @Excel(name = "保险公司Id")
    private Integer companyId;

    public void setiId(Long iId) 
    {
        this.iId = iId;
    }

    public Long getiId() 
    {
        return iId;
    }
    public void setInsrncName(String insrncName) 
    {
        this.insrncName = insrncName;
    }

    public String getInsrncName() 
    {
        return insrncName;
    }
    public void setChannelId(String channelId) 
    {
        this.channelId = channelId;
    }

    public String getChannelId() 
    {
        return channelId;
    }
    public void setOpenFlag(String openFlag) 
    {
        this.openFlag = openFlag;
    }

    public String getOpenFlag() 
    {
        return openFlag;
    }
    public void setArtFlag(String artFlag) 
    {
        this.artFlag = artFlag;
    }

    public String getArtFlag() 
    {
        return artFlag;
    }
    public void setIntegralStatus(String integralStatus) 
    {
        this.integralStatus = integralStatus;
    }

    public String getIntegralStatus() 
    {
        return integralStatus;
    }
    public void setCompanyId(Integer companyId) 
    {
        this.companyId = companyId;
    }

    public Integer getCompanyId() 
    {
        return companyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iId", getiId())
            .append("insrncName", getInsrncName())
            .append("channelId", getChannelId())
            .append("openFlag", getOpenFlag())
            .append("artFlag", getArtFlag())
            .append("remark", getRemark())
            .append("integralStatus", getIntegralStatus())
            .append("companyId", getCompanyId())
            .toString();
    }
}
