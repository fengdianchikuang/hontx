package com.ruoyi.trans.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "我的收益请求对象")
@Data
public class ProfitRequest {
    @ApiModelProperty(value = "当前页",example = "1")
    private int page;
    @ApiModelProperty(value = "显示数量",example = "5")
    private int size;
    @ApiModelProperty(value = "代理人Id",example = "10")
    private Long ownerId;
    @ApiModelProperty(value = "null默认所有,00:代理人收益,01:A层收益,02B层收益",example = "00")
    private String type;

}
