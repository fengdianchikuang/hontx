package com.ruoyi.policy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投保信息对象 tb_policy
 *
 * @author ruoyi
 * @date 2021-06-14
 */
public class Policy
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车主id */
    @Excel(name = "车主id")
    private Long ownerId;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNo;

    /** 保险公司代号 */
    @Excel(name = "保险公司代号")
    private Integer companyId;

    /** 保险公司名称 */
    @Excel(name = "保险公司名称")
    private String companyName;

    /** 交强险保费 */
    @Excel(name = "交强险保费")
    private Long tciPremium;

    /** 商业险保费 */
    @Excel(name = "商业险保费")
    private Long vciPremium;

    /** 车船税; */
    @Excel(name = "车船税;")
    private Long vehicleTax;

    /** 交强险折扣 */
    @Excel(name = "交强险折扣")
    private Long tciDiscount;

    /** 商业险折扣 */
    @Excel(name = "商业险折扣")
    private Long vciDiscount;

    /** 交强险保单号 */
    @Excel(name = "交强险保单号")
    private String tciPolicyNo;

    /** 商业险保单号 */
    @Excel(name = "商业险保单号")
    private String vciPolicyNo;

    /** 交强险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交强险结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tciPolicyEndDate;

    /** 商业险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商业险结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vciPolicyEndDate;

    /** 交强险结束详细日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交强险结束详细日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date tciPolicyEndDateTime;

    /** 商业险结束详细日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商业险结束详细日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vciPolicyEndDateTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setFrameNo(String frameNo)
    {
        this.frameNo = frameNo;
    }

    public String getFrameNo()
    {
        return frameNo;
    }
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }

    public Integer getCompanyId()
    {
        return companyId;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setTciPremium(Long tciPremium)
    {
        this.tciPremium = tciPremium;
    }

    public Long getTciPremium()
    {
        return tciPremium;
    }
    public void setVciPremium(Long vciPremium)
    {
        this.vciPremium = vciPremium;
    }

    public Long getVciPremium()
    {
        return vciPremium;
    }
    public void setVehicleTax(Long vehicleTax)
    {
        this.vehicleTax = vehicleTax;
    }

    public Long getVehicleTax()
    {
        return vehicleTax;
    }
    public void setTciDiscount(Long tciDiscount)
    {
        this.tciDiscount = tciDiscount;
    }

    public Long getTciDiscount()
    {
        return tciDiscount;
    }
    public void setVciDiscount(Long vciDiscount)
    {
        this.vciDiscount = vciDiscount;
    }

    public Long getVciDiscount()
    {
        return vciDiscount;
    }
    public void setTciPolicyNo(String tciPolicyNo)
    {
        this.tciPolicyNo = tciPolicyNo;
    }

    public String getTciPolicyNo()
    {
        return tciPolicyNo;
    }
    public void setVciPolicyNo(String vciPolicyNo)
    {
        this.vciPolicyNo = vciPolicyNo;
    }

    public String getVciPolicyNo()
    {
        return vciPolicyNo;
    }
    public void setTciPolicyEndDate(Date tciPolicyEndDate)
    {
        this.tciPolicyEndDate = tciPolicyEndDate;
    }

    public Date getTciPolicyEndDate()
    {
        return tciPolicyEndDate;
    }
    public void setVciPolicyEndDate(Date vciPolicyEndDate)
    {
        this.vciPolicyEndDate = vciPolicyEndDate;
    }

    public Date getVciPolicyEndDate()
    {
        return vciPolicyEndDate;
    }
    public void setTciPolicyEndDateTime(Date tciPolicyEndDateTime)
    {
        this.tciPolicyEndDateTime = tciPolicyEndDateTime;
    }

    public Date getTciPolicyEndDateTime()
    {
        return tciPolicyEndDateTime;
    }
    public void setVciPolicyEndDateTime(Date vciPolicyEndDateTime)
    {
        this.vciPolicyEndDateTime = vciPolicyEndDateTime;
    }

    public Date getVciPolicyEndDateTime()
    {
        return vciPolicyEndDateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ownerId", getOwnerId())
            .append("frameNo", getFrameNo())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("tciPremium", getTciPremium())
            .append("vciPremium", getVciPremium())
            .append("vehicleTax", getVehicleTax())
            .append("tciDiscount", getTciDiscount())
            .append("vciDiscount", getVciDiscount())
            .append("tciPolicyNo", getTciPolicyNo())
            .append("vciPolicyNo", getVciPolicyNo())
            .append("tciPolicyEndDate", getTciPolicyEndDate())
            .append("vciPolicyEndDate", getVciPolicyEndDate())
            .append("tciPolicyEndDateTime", getTciPolicyEndDateTime())
            .append("vciPolicyEndDateTime", getVciPolicyEndDateTime())
            .toString();
    }
}
