package com.ruoyi.user.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class CarOwner implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "车主号")
    private Long ownerId;

    @ApiModelProperty(value = "车主实名Id")
    private Integer realId;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "00:身份证，01:营业执照")
    private String certType;

    @ApiModelProperty(value = "证件号")
    private String certNo;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "微信识别号")
    private String openid;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "用户头像")
    private String headimgurl;

    @ApiModelProperty(value = "用户状态:00,01激活，未激活")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "小程序openid")
    private String miniOpenid;

    @ApiModelProperty(value = "总收益")
    private String totalIncome;

    @ApiModelProperty(value = "可用余额")
    private String availBal;

    @ApiModelProperty(value = "A层级人数")
    private String ateamNum = "0";

    @ApiModelProperty(value = "B层级人数")
    private String bteamNum = "0";

    @ApiModelProperty(value = "A层级收益")
    private String aincome;

    @ApiModelProperty(value = "B层级收益")
    private String bincome;

    @ApiModelProperty(value = "代理人收益")
    private String agentincome;

    @ApiModelProperty(value = "车主银行")
    private String bankName;

    @ApiModelProperty(value = "车主卡号")
    private String accno;

    @ApiModelProperty(value = "推荐人id")
    private Long parentId;

    @ApiModelProperty(value = "推荐人手机号")
    private String parentPhone;

    @ApiModelProperty(value = "上级实名用户")
    private Integer parentRealId;

    @ApiModelProperty(value = "00:非代理人,01:代理人，经过实名认证")
    private String userType;

    @ApiModelProperty(value = "实名认证标志（00表示未认证；01表示审核中，02:表示认证未通过,03:表示认证通过）")
    private String realNameStatus;

    @ApiModelProperty(value = "0平台新用户,1平台老用户")

    private Integer oldStatus;


    private List<CarOwner> aTeams = new ArrayList<>();

    private List<CarOwner> bTeams = new ArrayList<>();
}
