package com.ruoyi.vehicle.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "车辆文件上传参数")
@Data
public class VehicleFileVo {

    @ApiModelProperty(value = "车牌号")
    private String vehicleNo;

    @ApiModelProperty(value = "车架号")
    private String rackNo;

    @ApiModelProperty(value = "type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other")
    private String type;

    private int flg = 0;

}
