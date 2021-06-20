package com.ruoyi.vehicle.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "车辆信息")
public class VehicleInfoVo {


    @ApiModelProperty(value = "车主Id")
    private String ownerId;

    /**
     * 车主姓名
     */

    @ApiModelProperty(value = "车主姓名")
    private String ownerName;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @TableField(value = "v_create_time")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间",hidden = true)
//    @TableField(value = "v_update_time")
    private Date updateTime;


    //TODO  车辆信息  **start**
    //TODO 映射字段
//    @TableField(value = "vehicle_no")
    @ApiModelProperty(value = "车牌号")
    private String licenseNo;

    //TODO 映射字段
//    @TableField(value = "et_brand")
    @ApiModelProperty(value = "品牌型号")
    private String brandName;

    //TODO 映射字段
//    @TableField(value = "rack_no")
    @ApiModelProperty(value = "车架号/VIN码")
    private String frameNo;

    //TODO 映射字段
//    @TableField(value = "brand_code")
    @ApiModelProperty(value = "车型编码")
    private String modelCode;

    @ApiModelProperty(value = "车辆估价")
    private Integer purchasePrice;

    //    @TableField(value = "set_num")
    @ApiModelProperty(value = "核定载人数")
    private String seatCount;

    @ApiModelProperty(value = "发动机编号")
    private String engineNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd")
    @ApiModelProperty(value = "过户日期|YYYY-MM-DD|过户车辆必填")
    private Date transferDate;

    //TODO 映射字段
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyyMMdd")
//    @TableField(value = "register_date")
    @ApiModelProperty(value = "注册日期")
    private Date enrollDate;

    @ApiModelProperty(value = "排量/功率|单位：升/KW",position = 21)
    private Float displacement;
    @ApiModelProperty(value = "整备质量|单位：吨",position = 22)
    private Float curbWeight;
    @ApiModelProperty(value = "核定载质量|单位：吨",position = 23)
    private Float tonnage;

    /**
     * 交管车辆类型
     */
    @ApiModelProperty(value = "车辆类型:小型普通客车:K31,小型越野客车:K32,轿车:K33,微型普通客车:K41,微型越野客车:K42,微型轿车:K43")
    private String vehicleType;
    /**
     * 车辆载人分类
     */
    @ApiModelProperty(value = "座位数量分类:六座以下:101,六座到十座以下客车:102,十座到二十座:103,二十座到三十六座:104,三十六座及三十六座以上:105")
    private String vehicleCategory;

    /**
     * 车辆使用性质
     */
    //TODO 映射字段
//    @TableField(value = "use_type")
    @ApiModelProperty(value = "使用性质:非营业:100,营业:200")
    private String useNature;
    /**
     * 车辆大小分类
     */
    @ApiModelProperty(value = "车型分类:小型汽车:101,大型汽车:102,小型新能源汽车:103,大型新能源汽车:104,其他车型:199")
    private String plateType;

    /**
     * 车牌底色
     */
    @ApiModelProperty(value = "车牌底色:蓝:101,黑:102,白,103,黄:104,白蓝:105,黄绿:106,渐变绿:107,其他:199")
    private String plateColor;
    /**
     * 能源类型
     */

    @ApiModelProperty(value = "能源类型:燃油:101,纯电动:102,燃料电池:103,插电混合动力:104,其他混合动力:199")
    private String energyType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insrncEndJq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date insrncEndSy;



}
