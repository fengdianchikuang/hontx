package com.ruoyi.life.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 寿险产品列表对象 tb_life_product
 *
 * @author ruoyi
 * @date 2021-05-06
 */
public class TbLifeProduct
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productname;
    /** 产品Id */
    @Excel(name = "产品Id")
    private String productno;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private String producttype;

    /** 保险公司 */
    @Excel(name = "保险公司")
    private String insrnccpy;

    /** URL连接 */
    @Excel(name = "URL连接")
    private String url;

    /** 状态 */
    @Excel(name = "状态")
    private String status;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatetime;

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProductname(String productname)
    {
        this.productname = productname;
    }

    public String getProductname()
    {
        return productname;
    }
    public void setProducttype(String producttype)
    {
        this.producttype = producttype;
    }

    public String getProducttype()
    {
        return producttype;
    }
    public void setInsrnccpy(String insrnccpy)
    {
        this.insrnccpy = insrnccpy;
    }

    public String getInsrnccpy()
    {
        return insrnccpy;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
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
}
