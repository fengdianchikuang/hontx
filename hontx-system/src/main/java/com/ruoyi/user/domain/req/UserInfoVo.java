package com.ruoyi.user.domain.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.user.domain.TbUserFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value="UserInfo对象", description="用户扩展信息表")
@Data
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户证件信息")
    private List<TbUserFile> userFile;

    @ApiModelProperty(value = "用户Id")
    private Integer id;


    @ApiModelProperty(value = "车主Id")
    //@ApiModelProperty(value = "车主Id",hidden = true)
    private Long ownerId;


    @ApiModelProperty(value = "用户账号")
    //@ApiModelProperty(value = "用户账号",required = true)
    private String uName;

//    @NotEmpty(message = "账号名称不能为空")
//    @ApiModelProperty(value = "账户名称",required = true)
//    private String uAccountName;

    @ApiModelProperty(value = "用户密码")
    //@ApiModelProperty(value = "用户密码",required = true)
    private String uPassword;

    @ApiModelProperty(value = "用户描述")
    private String uDescription;


    @ApiModelProperty(value = "性别1:男,0:女")
    private Integer uSex;


    //    @ApiModelProperty(value = "出生日期",required = true)
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uBirthday;


    @ApiModelProperty(value = "电话")
    //@ApiModelProperty(value = "电话",hidden = true)
    private String uTelephone;


    @ApiModelProperty(value = "邮箱")
    private String uEmail;

    @ApiModelProperty(value = "住址")
    private String uAddress;


    @ApiModelProperty(value = "上级用户")
    private String uParentId;


    @ApiModelProperty(value = "推荐人手机号")
    private String uParentPhone;

    @ApiModelProperty(value = "真实姓名")
    private String uRealName;

    @ApiModelProperty(value = "用户身份证")
    private String uIdentity;

    @ApiModelProperty(value = "银行名称")
    private String uBankName;


    @ApiModelProperty(value = "银行分行")
    private String uBankBranch;


    @ApiModelProperty(value = "银行账号")
    private String uAccno;

    @ApiModelProperty(value = "实名认证标志（00表示未认证；01表示审核中，02:表示认证未通过,03:表示认证通过）",hidden = true)
    private String uRealNameStatus;

    @ApiModelProperty(value = "团队Id")
    private String tId;
    //@Length(min = 6,max = 10,message = "密码不能超出指定范围6到10位")
    //@ApiModelProperty(value = "支付密码",required = true)
    private String uPayPassword;


    @ApiModelProperty(value = "微信识别号")
    private String openid;

    private String idFrontUrl;

    private String idBackUrl;
}
