package com.ruoyi.risks.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 保险Code对象 tb_risks
 *
 * @author ruoyi
 * @date 2021-06-15
 */
@ApiModel(value = "保险选项")
@Data
public class Risks implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车主id */
    @Excel(name = "车主id")
    private Integer companyId;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNo;
    @ApiModelProperty(hidden = true)
    private String riskCode;
    /** 险种代码 */
    @Excel(name = "险种代码")
    @ApiModelProperty(example = "a100",value = "商业险代码:交强险\t000\n" +
            "机动车损失险\t007\n" +
            "第三者责任险\t001\n" +
            "司机责任险\t002\n" +
            "乘客责任险\t003\n" +
            "车身划痕险\t010\n" +
            "修理补偿险\t014\n" +
            "车轮损失险\t015\n" +
            "三者险节假日翻倍险\t016\n" +
            "道路救援服务\t017\n" +
            "车辆安全检测\t018\n" +
            "代为驾驶服务\t019\n" +
            "代为送检服务\t020\n" +
            "精神抚慰金险 (三者)\t021\n" +
            "精神抚慰金险 (司机)\t022\n" +
            "精神抚慰金险 (乘客)\t023\n" +
            "医保外用药险 (三者)\t024\n" +
            "医保外用药险 (司机)\t025\n" +
            "医保外用药险 (乘客)\t026\n" +
            "绝对免赔率 (车损)\t027\n" +
            "绝对免赔率 (三者)\t028\n" +
            "绝对免赔率 (司机)\t029\n" +
            "绝对免赔率 (乘客)\t030\n" +
            "发动机损坏除外特约\t031\n")

//    @ApiModelProperty(value = "险种代码,请求数据使用(riskCode 返回数据字段（相同）)")
    private String code;

    /** 险种名称 */
    @Excel(name = "险种名称")
    @ApiModelProperty(hidden = true)
    private String riskName;
//
    @ApiModelProperty(value = "险种名称")
    private String name;

    /** 投险金额 */
    @Excel(name = "投险金额")
    @ApiModelProperty(value = "保额:投保机动车损失险：若没有修改需求不传值，保司有默认保额；\n" +
            "投保第三者责任险、司机责任险、车轮损失险、车身划痕险、精神抚慰金险 (三者)、精神抚慰金险 (司机)、医保外用药险 (三者)、医保外用药险 (司机)传值",position = 43)
    private Float amount;

    /** 保险费 */
    @Excel(name = "保险费")
    private Float premium;

    /** 免赔额 */
    @Excel(name = "免赔额")
    private Float isDeductible;

    /** 扣除保险费 */
    @Excel(name = "扣除保险费")
    private Float deductPremium;

    /** 数量 */
    @Excel(name = "数量")
    @ApiModelProperty(value = "投保数量")
    private Integer quantity;


}
