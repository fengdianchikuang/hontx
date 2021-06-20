package com.ruoyi.system.domain;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 过眼云烟
 * @since 2021-03-12
 */

public class QueryInsrnc implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "q_id", type = IdType.AUTO)
    private Long qId;

//    @ApiModelProperty(value = "询价Id")
    private String queryId;

//    @ApiModelProperty(value = "险种代码")
    private String insrncCode;

//    @ApiModelProperty(value = "玻璃类型:00：国产；01进口")
    private String glassType;

//    @ApiModelProperty(value = "保险金额/赔偿限额(元）")
    private String amount;

//    @ApiModelProperty(value = "不计免赔：0，否；1，是")
    private String franchiseFlag;

//    @ApiModelProperty(value = "之前保费")
    private String beforePremium;

//    @ApiModelProperty(value = "费用")
    private String rate;

//    @ApiModelProperty(value = "保费")
    private String premium;

//    @ApiModelProperty(value = "不扣保险费")
    private String nonDeductPremium;

//    @ApiModelProperty(value = "扣除保险费")
    private String deductPremium;

//    @ApiModelProperty(value = "数量")
    private Integer quantity;

//    @ApiModelProperty(value = "单位金额")
    private Float unitAmount;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getInsrncCode() {
        return insrncCode;
    }

    public void setInsrncCode(String insrncCode) {
        this.insrncCode = insrncCode;
    }

    public String getGlassType() {
        return glassType;
    }

    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFranchiseFlag() {
        return franchiseFlag;
    }

    public void setFranchiseFlag(String franchiseFlag) {
        this.franchiseFlag = franchiseFlag;
    }

    public String getBeforePremium() {
        return beforePremium;
    }

    public void setBeforePremium(String beforePremium) {
        this.beforePremium = beforePremium;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getNonDeductPremium() {
        return nonDeductPremium;
    }

    public void setNonDeductPremium(String nonDeductPremium) {
        this.nonDeductPremium = nonDeductPremium;
    }

    public String getDeductPremium() {
        return deductPremium;
    }

    public void setDeductPremium(String deductPremium) {
        this.deductPremium = deductPremium;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Float unitAmount) {
        this.unitAmount = unitAmount;
    }
}
