package com.ruoyi.user.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@ApiModel(value="User对象", description="用户主表")
@Data
public class UserReq {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "车主Id")
    private Long ownerId;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "逻辑删除状态")
    private Integer deleteStatus;

    @ApiModelProperty(value = "是否锁定")
    private Integer locked;

    @ApiModelProperty(value = "用户描述")
    private String description;

    @ApiModelProperty(value = "加密盐")
    private String credentialsSalt;

    //    @ApiModelProperty(value = "创建者")
    private String creatorName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
