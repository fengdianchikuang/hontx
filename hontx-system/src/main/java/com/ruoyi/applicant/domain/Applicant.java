package com.ruoyi.applicant.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 保险关系人信息对象 tb_applicant
 *
 * @author ruoyi
 * @date 2021-06-15
 */
@Data
public class Applicant
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

    /** 姓名 */
    @Excel(name = "姓名")
    @ApiModelProperty(value = "关系人名称",position = 32,example = "吴孟")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    @ApiModelProperty(value = "联系电话",example = "17382109808")
    private String phone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    @ApiModelProperty(example = "101",value = "证件类型:(\"个人\",\"100\",\"\"),\n" +
            "(\"企业\",\"200\",\"\"),\n" +
            "(\"机构\",\"300\",\"\"),\n" +
            "(\"身份证\",\"101\",\"100\"),\n" +
            "(\"护照\",\"102\",\"100\"),\n" +
            "(\"军人证\",\"103\",\"100\"),\n" +
            "(\"港澳通行证\",\"104\",\"100\"),\n" +
            "(\"台胞证\",\"105\",\"100\"),\n" +
            "(\"回乡证\",\"111\",\"100\"),\n" +
            "0(\"统一社会信用代码\",\"201\",\"200\"),\n" +
            "1(\"组织机构代码\",\"202\",\"200\"),\n" +
            "2(\"税务登记证\",\"203\",\"200\"),\n" +
            "3(\"营业执照\",\"204\",\"200\"),\n" +
            "4(\"其他\",\"299\",\"200\"),\n" +
            "5(\"统一社会信用代码\",\"301\",\"300\"),\n" +
            "6(\"组织机构代码\",\"302\",\"300\"),\n" +
            "7(\"税务登记证\",\"303\",\"300\"),\n" +
            "8(\"营业执照\",\"304\",\"300\"),\n" +
            "9(\"其他\",\"399\",\"300\");")
    private String certificateType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    @ApiModelProperty(value = "证件号码",example = "430681198807273710")
    private String certificateNo;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value ="关系人出生日期|证件类别为个人，且证件类型不是身份证时必填；格式：YYYY-MM-DD",example = "1988-07-27")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** 性别:1代表男,2代表女 */
    @Excel(name = "性别:1代表男,2代表女")
    @ApiModelProperty(value = "关系人性别|证件类别为个人，且证件类型不是身份证时必填；1 男性 2女性",example = "1")
    private Integer sex;

    /** 地址 */
    @Excel(name = "地址")
    @ApiModelProperty(value = "联系地址|被投保人不需要填",example = "岳阳市汨罗市")
    private String address;

    /** 1:代表车主,2代表被投保人,3代表投保人 */
    @Excel(name = "1:代表车主,2代表被投保人,3代表投保人")
    @ApiModelProperty(value = "关系人类别|1车主/2被保人/3投保人",position = 31,example = "1")
    private Integer insuredFlag;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    @ApiModelProperty(value = "邮箱|电子保单接收邮箱",example = "76603593@qq.com")
    private String email;

}
