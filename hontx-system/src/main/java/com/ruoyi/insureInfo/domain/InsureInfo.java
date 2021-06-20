package com.ruoyi.insureInfo.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 核保信息对象 tb_insure_info
 *
 * @author ruoyi
 * @date 2021-06-16
 */
@Data
public class InsureInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车主Id */
    @Excel(name = "车主Id")
    private Long ownerId;

    /** 投保公司 */
    @Excel(name = "投保公司")
    private Integer companyId;

    /** 订单Id */
    @Excel(name = "订单Id")
    private String orderId;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNo;

    /** 投保单号 */
    @Excel(name = "投保单号")
    private String proposalNo;

    /** 交强险单号 */
    @Excel(name = "交强险单号")
    private String tciProposalNo;

    /** 商业险单号 */
    @Excel(name = "商业险单号")
    private String vciProposalNo;

    /** 交强险保单状态 */
    @Excel(name = "交强险保单状态")
    private Integer tciPolicyStatus;

    /** 商业险保单状态 */
    @Excel(name = "商业险保单状态")
    private Integer vciPolicyStatus;

    /** 交强险状态信息 */
    @Excel(name = "交强险状态信息")
    private String tciStatusMessage;

    /** 商业险状态信息 */
    @Excel(name = "商业险状态信息")
    private String vciStatusMessage;

    /** 原因,说明 */
    @Excel(name = "原因,说明")
    private String reason;

    /** 保单状态0：报价失败 */
    @Excel(name = "保单状态0：报价失败")
    private Integer policyStatus;

    /** 0:未申请支付,1:已申请支付,2:已支付成功 */
    @Excel(name = "0:未申请支付,1:已申请支付,2:已支付成功")
    private Integer payStatus;

    /** 交强险开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交强险开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tciStartDateTime;

    /** 商业险开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商业险开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vciStartDateTime;


}
