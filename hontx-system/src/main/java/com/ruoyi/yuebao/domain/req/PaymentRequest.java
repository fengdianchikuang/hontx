package com.ruoyi.yuebao.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "支付申请参数")
@Data
public class PaymentRequest {
    @ApiModelProperty(value = "报价返回的订单号",required = true,example = "QCHSU37Y2021F005613C")
    private String orderId;
//    @ApiModelProperty(value = "用户实名Id")
//    private Integer realId;
//    @ApiModelProperty(value = "上级实名Id")
//    private Integer parentRealId;
//    @ApiModelProperty(value = "实名认证短信验证码回填")
//    private String verifyCode;
//    @ApiModelProperty(value = "实名认证验证码短信重新发送标识1：重新发")
//    private String resend;
//    @ApiModelProperty(value = "刷新支付码|1：刷新")
//    private String refresh;
}
