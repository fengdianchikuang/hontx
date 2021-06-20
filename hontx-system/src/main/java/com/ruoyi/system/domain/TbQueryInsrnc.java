package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * insrnc对象 tb_query_insrnc
 * 
 * @author ruoyi
 * @date 2021-04-07
 */
public class TbQueryInsrnc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long qId;

    /** 询价Id */
    private String queryId;

    /** 保险项 */
    private String insrncCode;

    /** 玻璃类型 */
    private String glassType;

    /** 金额 */
    private String amount;

    /** 不计免赔 */
    private String franchiseFlag;

    /** 之前保费 */
    private String beforePremium;

    /** 费率 */
    private String rate;

    /** $column.columnComment */
    private String premium;

    /** $column.columnComment */
    private String nonDeductPremium;

    /** $column.columnComment */
    private String deductPremium;

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
    public void setInsrncCode(String insrncCode) 
    {
        this.insrncCode = insrncCode;
    }

    public String getInsrncCode() 
    {
        return insrncCode;
    }
    public void setGlassType(String glassType) 
    {
        this.glassType = glassType;
    }

    public String getGlassType() 
    {
        return glassType;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setFranchiseFlag(String franchiseFlag) 
    {
        this.franchiseFlag = franchiseFlag;
    }

    public String getFranchiseFlag() 
    {
        return franchiseFlag;
    }
    public void setBeforePremium(String beforePremium) 
    {
        this.beforePremium = beforePremium;
    }

    public String getBeforePremium() 
    {
        return beforePremium;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setPremium(String premium) 
    {
        this.premium = premium;
    }

    public String getPremium() 
    {
        return premium;
    }
    public void setNonDeductPremium(String nonDeductPremium) 
    {
        this.nonDeductPremium = nonDeductPremium;
    }

    public String getNonDeductPremium() 
    {
        return nonDeductPremium;
    }
    public void setDeductPremium(String deductPremium) 
    {
        this.deductPremium = deductPremium;
    }

    public String getDeductPremium() 
    {
        return deductPremium;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qId", getqId())
            .append("queryId", getQueryId())
            .append("insrncCode", getInsrncCode())
            .append("glassType", getGlassType())
            .append("amount", getAmount())
            .append("franchiseFlag", getFranchiseFlag())
            .append("beforePremium", getBeforePremium())
            .append("rate", getRate())
            .append("premium", getPremium())
            .append("nonDeductPremium", getNonDeductPremium())
            .append("deductPremium", getDeductPremium())
            .toString();
    }
}
