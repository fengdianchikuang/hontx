package com.ruoyi.lifeholder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 寿险客户对象 tb_life_holder
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public class TbLifeHolder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idtype;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idno;

    /** 投保人 */
    @Excel(name = "投保人")
    private String name;

    /** 手机密文 */
    @Excel(name = "手机密文")
    private String ciphermobile;

    /** 手机号 */
    @Excel(name = "手机号")
    private String plainmobile;

    /** 代理人 */
    @Excel(name = "代理人")
    private String agentid;

    /** 创建时间 */
    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    /** 更新时间 */
    @Excel(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
    public void setIdtype(String idtype)
    {
        this.idtype = idtype;
    }

    public String getIdtype()
    {
        return idtype;
    }
    public void setIdno(String idno)
    {
        this.idno = idno;
    }

    public String getIdno()
    {
        return idno;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCiphermobile(String ciphermobile)
    {
        this.ciphermobile = ciphermobile;
    }

    public String getCiphermobile()
    {
        return ciphermobile;
    }
    public void setPlainmobile(String plainmobile)
    {
        this.plainmobile = plainmobile;
    }

    public String getPlainmobile()
    {
        return plainmobile;
    }
    public void setAgentid(String agentid)
    {
        this.agentid = agentid;
    }

    public String getAgentid()
    {
        return agentid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idtype", getIdtype())
            .append("idno", getIdno())
            .append("name", getName())
            .append("ciphermobile", getCiphermobile())
            .append("plainmobile", getPlainmobile())
            .append("agentid", getAgentid())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .toString();
    }
}
