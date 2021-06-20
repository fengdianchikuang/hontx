package com.ruoyi.lifeorder.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 寿险订单对象 tb_life_order
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public class TbLifeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderid;

    /** 保单号 */
    @Excel(name = "保单号")
    private String holderidno;

    /** 代理人 */
    @Excel(name = "代理人")
    private String agentid;

    /** 产品名 */
    @Excel(name = "产品名")
    private String productid;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** 外部参数 */
    @Excel(name = "外部参数")
    private String extparams;

    /** 担保期限 */
    @Excel(name = "担保期限")
    private String guaranteeterm;

    /** 担保类型 */
    @Excel(name = "担保类型")
    private String guaranteetype;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paytime;

    /** 被保人证件类型 */
    @Excel(name = "被保人证件类型")
    private String insurederidtype;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String insurederidno;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String insuredername;

    /** 缴费期限 */
    @Excel(name = "缴费期限")
    private String payperm;

    /** 支付类型 */
    @Excel(name = "支付类型")
    private String paytype;

    /** 付款 */
    @Excel(name = "付款")
    private String payment;

    /** 保费 */
    @Excel(name = "保费")
    private String premium;

    /** 产品名 */
    @Excel(name = "产品名")
    private String productname;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyno;

    /** 保单状态 */
    @Excel(name = "保单状态")
    private String policystatus;

    /** 总保费 */
    @Excel(name = "总保费")
    private String totalinsured;

    /** 上层产品 */
    @Excel(name = "上层产品")
    private String upperproductid;

    /** 保险开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保险开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date guaranteestarttime;

    /** 保险结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保险结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date guaranteeendtime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatetime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }

    public String getOrderid()
    {
        return orderid;
    }
    public void setHolderidno(String holderidno)
    {
        this.holderidno = holderidno;
    }

    public String getHolderidno()
    {
        return holderidno;
    }
    public void setAgentid(String agentid)
    {
        this.agentid = agentid;
    }

    public String getAgentid()
    {
        return agentid;
    }
    public void setProductid(String productid)
    {
        this.productid = productid;
    }

    public String getProductid()
    {
        return productid;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setExtparams(String extparams)
    {
        this.extparams = extparams;
    }

    public String getExtparams()
    {
        return extparams;
    }
    public void setGuaranteeterm(String guaranteeterm)
    {
        this.guaranteeterm = guaranteeterm;
    }

    public String getGuaranteeterm()
    {
        return guaranteeterm;
    }
    public void setGuaranteetype(String guaranteetype)
    {
        this.guaranteetype = guaranteetype;
    }

    public String getGuaranteetype()
    {
        return guaranteetype;
    }
    public void setPaytime(Date paytime)
    {
        this.paytime = paytime;
    }

    public Date getPaytime()
    {
        return paytime;
    }
    public void setInsurederidtype(String insurederidtype)
    {
        this.insurederidtype = insurederidtype;
    }

    public String getInsurederidtype()
    {
        return insurederidtype;
    }
    public void setInsurederidno(String insurederidno)
    {
        this.insurederidno = insurederidno;
    }

    public String getInsurederidno()
    {
        return insurederidno;
    }
    public void setInsuredername(String insuredername)
    {
        this.insuredername = insuredername;
    }

    public String getInsuredername()
    {
        return insuredername;
    }
    public void setPayperm(String payperm)
    {
        this.payperm = payperm;
    }

    public String getPayperm()
    {
        return payperm;
    }
    public void setPaytype(String paytype)
    {
        this.paytype = paytype;
    }

    public String getPaytype()
    {
        return paytype;
    }
    public void setPayment(String payment)
    {
        this.payment = payment;
    }

    public String getPayment()
    {
        return payment;
    }
    public void setPremium(String premium)
    {
        this.premium = premium;
    }

    public String getPremium()
    {
        return premium;
    }
    public void setProductname(String productname)
    {
        this.productname = productname;
    }

    public String getProductname()
    {
        return productname;
    }
    public void setPolicyno(String policyno)
    {
        this.policyno = policyno;
    }

    public String getPolicyno()
    {
        return policyno;
    }
    public void setPolicystatus(String policystatus)
    {
        this.policystatus = policystatus;
    }

    public String getPolicystatus()
    {
        return policystatus;
    }
    public void setTotalinsured(String totalinsured)
    {
        this.totalinsured = totalinsured;
    }

    public String getTotalinsured()
    {
        return totalinsured;
    }
    public void setUpperproductid(String upperproductid)
    {
        this.upperproductid = upperproductid;
    }

    public String getUpperproductid()
    {
        return upperproductid;
    }
    public void setGuaranteestarttime(Date guaranteestarttime)
    {
        this.guaranteestarttime = guaranteestarttime;
    }

    public Date getGuaranteestarttime()
    {
        return guaranteestarttime;
    }
    public void setGuaranteeendtime(Date guaranteeendtime)
    {
        this.guaranteeendtime = guaranteeendtime;
    }

    public Date getGuaranteeendtime()
    {
        return guaranteeendtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderid", getOrderid())
            .append("holderidno", getHolderidno())
            .append("agentid", getAgentid())
            .append("productid", getProductid())
            .append("status", getStatus())
            .append("extparams", getExtparams())
            .append("guaranteeterm", getGuaranteeterm())
            .append("guaranteetype", getGuaranteetype())
            .append("paytime", getPaytime())
            .append("insurederidtype", getInsurederidtype())
            .append("insurederidno", getInsurederidno())
            .append("insuredername", getInsuredername())
            .append("payperm", getPayperm())
            .append("paytype", getPaytype())
            .append("payment", getPayment())
            .append("premium", getPremium())
            .append("productname", getProductname())
            .append("policyno", getPolicyno())
            .append("policystatus", getPolicystatus())
            .append("totalinsured", getTotalinsured())
            .append("upperproductid", getUpperproductid())
            .append("guaranteestarttime", getGuaranteestarttime())
            .append("guaranteeendtime", getGuaranteeendtime())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
