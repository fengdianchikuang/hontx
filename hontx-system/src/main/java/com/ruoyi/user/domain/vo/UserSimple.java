package com.ruoyi.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户简单信息")
@Data
public class UserSimple {
    @ApiModelProperty(value = "用户实名Id")
    private Integer userId;
    @ApiModelProperty(value = "用户实名姓名")
    private String realName;
    @ApiModelProperty(value = "电话号码")
    private String phone;
    @ApiModelProperty(value = "保险总收益")
    private String totalIncome;
}
