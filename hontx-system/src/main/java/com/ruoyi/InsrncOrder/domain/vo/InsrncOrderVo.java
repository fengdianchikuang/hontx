package com.ruoyi.InsrncOrder.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lw
 * @since 2021-01-22
 */

@ApiModel(value="InsrncOrder对象", description="")
@Data
public class InsrncOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "车主Id")
    private Long ownerId;

    @ApiModelProperty(value = "车架号")
    private String frameNo;

    @ApiModelProperty(value = "订单Id")
    private String orderId;

    @ApiModelProperty(value = "交强险折扣")
    private String tciScore;

    @ApiModelProperty(value = "保险公司名")
    private String companyName;

    @ApiModelProperty(value = "商业险开始日期")
    private Date vciStartDate;

    @ApiModelProperty(value = "交强险保费")
    private Float tciPremium;

    @ApiModelProperty(value = "总保费")
    private Float totalPremium;

    @ApiModelProperty(value = "车船税")
    private Float vehicleTax;

    @ApiModelProperty(value = "总折扣")
    private String totalScore;

    @ApiModelProperty(value = "交强险结束日期")
    private Date tciEndDate;

    @ApiModelProperty(value = "商业险保费")
    private Float vciPremium;

    @ApiModelProperty(value = "保险公司Id")
    private Integer companyId;

    @ApiModelProperty(value = "商业险折扣")
    private String vciScore;

    @ApiModelProperty(value = "商业险结束日期")
    private Date tciEndDateTime;

    @ApiModelProperty(value = "商业险折扣")
    private Float vciDiscount;

    @ApiModelProperty(value = "交强险开始日期")
    private Date tciStartDate;

    @ApiModelProperty(value = "交业险开始日期 详细")
    private Date tciStartDateTime;

    @ApiModelProperty(value = "商业险结束日期")
    private Date vciEndDate;

    @ApiModelProperty(value = "商业险开始日期 详细")
    private Date vciStartDateTime;

    @ApiModelProperty(value = "0报价失败,1,报价成功,2下发修改,3人工审核,4核保成功,5支付失败,6支付成功,7承保失败,8承保成功")
    private Integer policyStatus;

    @ApiModelProperty(value = "报价失败原因/核保失败原因")
    private String reason;

    private Date createTime;


}
