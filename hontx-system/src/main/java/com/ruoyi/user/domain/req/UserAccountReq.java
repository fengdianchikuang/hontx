package com.ruoyi.user.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户账号修改请求")
public class UserAccountReq {
    @ApiModelProperty(value = "车主Id")
    private Long ownerId;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "银行账号")
    private String uAccno;
    @ApiModelProperty(value = "支行")
    private String uBankBranch;
    @ApiModelProperty(value = "银行名")
    private String uBankName;
}
