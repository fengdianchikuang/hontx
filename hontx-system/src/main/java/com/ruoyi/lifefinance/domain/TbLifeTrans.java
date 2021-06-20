package com.ruoyi.lifefinance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 交易明细对象 tb_life_trans
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public class TbLifeTrans extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 交易流水号 */
    @Excel(name = "交易流水号")
    private String transno;

    /** 收支来源 */
    @Excel(name = "收支来源")
    private String transtype;

    /** 收支 */
    @Excel(name = "收支")
    private String amount;

    /** 代理人 */
    @Excel(name = "代理人")
    private String userid;

    /** 余额 */
    @Excel(name = "余额")
    private String useramount;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderid;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间")
    private Date updatetime;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTransno(String transno)
    {
        this.transno = transno;
    }

    public String getTransno()
    {
        return transno;
    }
    public void setTranstype(String transtype)
    {
        this.transtype = transtype;
    }

    public String getTranstype()
    {
        return transtype;
    }
    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getAmount()
    {
        return amount;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    public String getUserid()
    {
        return userid;
    }
    public void setUseramount(String useramount)
    {
        this.useramount = useramount;
    }

    public String getUseramount()
    {
        return useramount;
    }
    public void setOrderid(String orderid)
    {
        this.orderid = orderid;
    }

    public String getOrderid()
    {
        return orderid;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("transno", getTransno())
            .append("transtype", getTranstype())
            .append("amount", getAmount())
            .append("userid", getUserid())
            .append("useramount", getUseramount())
            .append("orderid", getOrderid())
            .append("status", getStatus())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
