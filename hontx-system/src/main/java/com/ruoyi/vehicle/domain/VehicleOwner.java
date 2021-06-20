package com.ruoyi.vehicle.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lw
 * @since 2021-01-26
 */

@ApiModel(value="VehicleOwner对象", description="")
@Data
public class VehicleOwner implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    @ApiModelProperty(value = "车架")
    private String frameNo;

    @ApiModelProperty(value = "车牌")
    private String licenseNo;

    @ApiModelProperty(value = "车辆拥有人")
    private Long ownerId;

    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
