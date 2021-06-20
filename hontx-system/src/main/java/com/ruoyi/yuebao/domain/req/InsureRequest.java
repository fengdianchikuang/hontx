package com.ruoyi.yuebao.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "核保下单请求参数")
@Data
public class InsureRequest {

//    @ApiModelProperty(value = "车主Id")
//    private Long ownerId;

//    @ApiModelProperty(value = "保险公司Id")
//    private Integer companyId;

    @ApiModelProperty(value = "核保下单返回订单",required = true,example = "QCHSU37Y2021F005613C")
    private String orderId;

    @ApiModelProperty(value = "车架号",required = true,example = "LH17CKBF3GH090713")
    private String frameNo;
}
