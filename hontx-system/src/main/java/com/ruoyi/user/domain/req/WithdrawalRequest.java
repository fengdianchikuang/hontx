package com.ruoyi.user.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申请提现 请求
 */
@Data
@ApiModel(value = "申请提现请求")
public class WithdrawalRequest {
    @ApiModelProperty(value = "车主Id")
    private Long ownerId;
    @ApiModelProperty(value = "金额")
    private Float amount;

}
