package com.ruoyi.life.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 产品费率表对象 tb_product_rate
 *
 * @author ruoyi
 * @date 2021-05-07
 */
@Data
public class TbProductRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productid;

    //产品名
    private String productname;

    /** 缴费类型 */
    @Excel(name = "缴费类型")
    private String paytype;

    /** 缴费期限 */
    @Excel(name = "缴费期限")
    private String payment;

    /** 返佣期 */
    @Excel(name = "返佣期")
    private String backterm;

    /** 总费率 */
    @Excel(name = "总费率")
    private String totalrate;

    /** 代理人费率 */
    @Excel(name = "代理人费率")
    private String agentrate;

    /** A层级费率 */
    @Excel(name = "A层级费率")
    private String arate;

    /** B层级费率 */
    @Excel(name = "B层级费率")
    private String brate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;


}
