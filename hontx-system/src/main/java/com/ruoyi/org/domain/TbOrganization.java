package com.ruoyi.org.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 机构管理对象 tb_organization
 * 
 * @author ruoyi
 * @date 2021-04-20
 */
public class TbOrganization extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long oId;

    /** 机构名称 */
    @Excel(name = "机构名称")
    private String orgName;

    /** 机构类型 */
    @Excel(name = "机构类型")
    private String type;

    /** 所属地区 */
    @Excel(name = "所属地区")
    private String areaCode;

    /** 法人 */
    @Excel(name = "法人")
    private String legalPerson;

    /** 营业执照号 */
    @Excel(name = "营业执照号")
    private String lincenseNo;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 联系邮箱 */
    @Excel(name = "联系邮箱")
    private String contactEmail;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oUpdateTime;

    /** 上级机构名称 */
    @Excel(name = "上级机构名称")
    private String parentOrgid;

    /** 关联公司Id */
    @Excel(name = "关联公司Id")
    private Integer companyId;

    public void setoId(Long oId) 
    {
        this.oId = oId;
    }

    public Long getoId() 
    {
        return oId;
    }
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }

    public String getOrgName() 
    {
        return orgName;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setLegalPerson(String legalPerson) 
    {
        this.legalPerson = legalPerson;
    }

    public String getLegalPerson() 
    {
        return legalPerson;
    }
    public void setLincenseNo(String lincenseNo) 
    {
        this.lincenseNo = lincenseNo;
    }

    public String getLincenseNo() 
    {
        return lincenseNo;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }
    public void setContactEmail(String contactEmail) 
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() 
    {
        return contactEmail;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setoCreateTime(Date oCreateTime) 
    {
        this.oCreateTime = oCreateTime;
    }

    public Date getoCreateTime() 
    {
        return oCreateTime;
    }
    public void setoUpdateTime(Date oUpdateTime) 
    {
        this.oUpdateTime = oUpdateTime;
    }

    public Date getoUpdateTime() 
    {
        return oUpdateTime;
    }
    public void setParentOrgid(String parentOrgid) 
    {
        this.parentOrgid = parentOrgid;
    }

    public String getParentOrgid() 
    {
        return parentOrgid;
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
            .append("oId", getoId())
            .append("orgName", getOrgName())
            .append("type", getType())
            .append("areaCode", getAreaCode())
            .append("legalPerson", getLegalPerson())
            .append("lincenseNo", getLincenseNo())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("contactEmail", getContactEmail())
            .append("address", getAddress())
            .append("status", getStatus())
            .append("oCreateTime", getoCreateTime())
            .append("oUpdateTime", getoUpdateTime())
            .append("parentOrgid", getParentOrgid())
            .append("companyId", getCompanyId())
            .toString();
    }
}
