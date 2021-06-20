package com.ruoyi.ai.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "行驶证识别结果")
public class DrivingFrontVo implements Serializable {

    private static final Long SerialVersionUID = 1L;

    @ApiModelProperty(value = "座位数")
    private String seatCount;

    //TODO 映射字段
    @ApiModelProperty(value = "车架号/VIN码")
    private String frameNo;

    @ApiModelProperty(value = "车牌号")
    private String licenseNo;

    @ApiModelProperty(value = "住址")
    private String address;

    @ApiModelProperty(value = "注册日期")
    private String enrollDate;

    @ApiModelProperty(value = "品牌型号")
    private String brandName;

    @ApiModelProperty(value = "车辆类型:小型普通客车:K31,小型越野客车:K32,轿车:K33,微型普通客车:K41,微型越野客车:K42,微型轿车:K43")
    private String vehicleType;

    @ApiModelProperty(value = "所有人")
    private String owner;

    @ApiModelProperty(value = "使用性质")
    private String useNature;

    @ApiModelProperty(value = "发动机编号")
    private String engineNo;

}
