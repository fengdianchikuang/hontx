package com.ruoyi.vehicle.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "车辆关系请求对象")
public class VehicleApplicantRequest {
    @ApiModelProperty(value = "车主Id")
    private Long ownerId;

    @ApiModelProperty(value = "车牌|二选一")
    private String licenseNo;

    @ApiModelProperty(value = "车架|二选一")
    private String frameNo;
}
