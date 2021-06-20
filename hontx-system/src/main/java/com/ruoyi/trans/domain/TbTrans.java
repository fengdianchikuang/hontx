package com.ruoyi.trans.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 提交列表对象 tb_trans
 * 
 * @author ruoyi
 * @date 2021-04-16
 */
public class TbTrans extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long tId;

    /** $column.columnComment */
    private String transNo;

    /** $column.columnComment */
    private String transType;

    /** $column.columnComment */
    private String couponNo;

    /** 金额 */
    private String amount;

    /** 交强险金额 */
    private String tciAmount;

    /** 商业险金额 */
    private String vciAmount;

    /** 提现人姓名 */
    @Excel(name = "提现人姓名")
    private String userId;

    /** 用户余额 */
    @Excel(name = "用户余额")
    private String userAmount;

    /** $column.columnComment */
    private String vehicleId;

    /** $column.columnComment */
    private String queryId;

    /** $column.columnComment */
    private String incomeId;

    /** $column.columnComment */
    @Excel(name = "用户余额")
    private String status;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tCreateTime;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tUpdateTime;

    public void settId(Long tId) 
    {
        this.tId = tId;
    }

    public Long gettId() 
    {
        return tId;
    }
    public void setTransNo(String transNo) 
    {
        this.transNo = transNo;
    }

    public String getTransNo() 
    {
        return transNo;
    }
    public void setTransType(String transType) 
    {
        this.transType = transType;
    }

    public String getTransType() 
    {
        return transType;
    }
    public void setCouponNo(String couponNo) 
    {
        this.couponNo = couponNo;
    }

    public String getCouponNo() 
    {
        return couponNo;
    }
    public void setAmount(String amount) 
    {
        this.amount = amount;
    }

    public String getAmount() 
    {
        return amount;
    }
    public void setTciAmount(String tciAmount) 
    {
        this.tciAmount = tciAmount;
    }

    public String getTciAmount() 
    {
        return tciAmount;
    }
    public void setVciAmount(String vciAmount) 
    {
        this.vciAmount = vciAmount;
    }

    public String getVciAmount() 
    {
        return vciAmount;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserAmount(String userAmount) 
    {
        this.userAmount = userAmount;
    }

    public String getUserAmount() 
    {
        return userAmount;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setQueryId(String queryId) 
    {
        this.queryId = queryId;
    }

    public String getQueryId() 
    {
        return queryId;
    }
    public void setIncomeId(String incomeId) 
    {
        this.incomeId = incomeId;
    }

    public String getIncomeId() 
    {
        return incomeId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void settCreateTime(Date tCreateTime) 
    {
        this.tCreateTime = tCreateTime;
    }

    public Date gettCreateTime() 
    {
        return tCreateTime;
    }
    public void settUpdateTime(Date tUpdateTime) 
    {
        this.tUpdateTime = tUpdateTime;
    }

    public Date gettUpdateTime() 
    {
        return tUpdateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tId", gettId())
            .append("transNo", getTransNo())
            .append("transType", getTransType())
            .append("couponNo", getCouponNo())
            .append("amount", getAmount())
            .append("tciAmount", getTciAmount())
            .append("vciAmount", getVciAmount())
            .append("userId", getUserId())
            .append("userAmount", getUserAmount())
            .append("vehicleId", getVehicleId())
            .append("queryId", getQueryId())
            .append("incomeId", getIncomeId())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("tCreateTime", gettCreateTime())
            .append("tUpdateTime", gettUpdateTime())
            .toString();
    }
}
