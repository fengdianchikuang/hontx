package com.ruoyi.vehicle.domain.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "修配保请求参数")
public class MaintenanceDetailRequest {
    @ApiModelProperty(value = "工单号")
    private String jobNo;
    @ApiModelProperty(value = "店号")
    private String shopId;
}
