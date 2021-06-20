package com.ruoyi.region.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区码对象 tb_region
 * 
 * @author ruoyi
 * @date 2021-04-13
 */
public class TbRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 代号 */
    private Long code;

    /** 上级代号 */
    @Excel(name = "上级代号")
    private Long parentcode;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 名称 */
    private String name;

    /** 全名 */
    private String fullname;

    public void setCode(Long code) 
    {
        this.code = code;
    }

    public Long getCode() 
    {
        return code;
    }
    public void setParentcode(Long parentcode) 
    {
        this.parentcode = parentcode;
    }

    public Long getParentcode() 
    {
        return parentcode;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setFullname(String fullname) 
    {
        this.fullname = fullname;
    }

    public String getFullname() 
    {
        return fullname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("code", getCode())
            .append("parentcode", getParentcode())
            .append("type", getType())
            .append("name", getName())
            .append("fullname", getFullname())
            .toString();
    }
}
