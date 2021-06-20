package com.ruoyi.vehicle.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "车辆信息")
@Data
public class VehicleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "ID",hidden = true)
    private Long Id;

    private Integer flg;

    //TODO  车辆信息  **start**
    //TODO 映射字段
    @ApiModelProperty(value = "车牌号")
    private String licenseNo;

    //TODO 映射字段
    @ApiModelProperty(value = "品牌型号")
    private String brandName;

    //TODO 映射字段
    @ApiModelProperty(value = "车架号/VIN码")
    private String frameNo;

    //TODO 映射字段
    @ApiModelProperty(value = "车型编码")
    private String modelCode;


    @ApiModelProperty(value = "车辆购置价")
    private Integer purchasePrice;

    @ApiModelProperty(value = "核定载人数")
    private String seatCount;

    @ApiModelProperty(value = "发动机编号")
    private String engineNo;

    //TODO 映射字段
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "注册日期")
    private Date enrollDate;

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
    @ApiModelProperty(value = "使用性质:非营业:100,营业:200")
    private String useNature;

    @ApiModelProperty(example = "101",value = "使用性质细分|当为营业时必填:(\"家庭自用车\",\"101\"),\n" +
            "(\"企业\",\"102\"),\n" +
            "(\"党政机关，事业团体\",\"103\"),\n" +
            "(\"其他非营业车辆\",\"199\"),\n" +
            "(\"出租客车\",\"201\"),\n" +
            "(\"城市公交\",\"202\"),\n" +
            "(\"公路客运\",\"203\"),\n" +
            "(\"旅游客运\",\"204\"),\n" +
            "(\"租赁客车\",\"205\"),\n" +
            "(\"营业货车\",\"206\"),\n" +
            "(\"其他营业车辆\",\"211\"),")
    private String attachNature;


    @ApiModelProperty(example = "86975.8",value = "实际价格")
    private Float actualPrice;
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
    //TODO 车辆信息  ***end**


    @ApiModelProperty(value = "渠道Id",hidden = true)
    private String channelId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "保险失效日期",hidden = true)
    private Date insrncEndDate;


    @ApiModelProperty(value = "总质量")
    private String vehicleValue;


    @ApiModelProperty(value = "所属性质:00个人,01企业....")
    private String belongType;


    @ApiModelProperty(value = "排气量",hidden = true)
    private String extMsr;


    @ApiModelProperty(value = "排量/功率")
    private Float displacement;
    //TODO 映射字段

    @ApiModelProperty(value = "整备质量")
    private String curbWeight;


    @ApiModelProperty(value = "核定载质量")
    private String tonnage;



    @ApiModelProperty(value = "过户车标志:否0，是1",hidden = true)
    private String changeOwnerFlag;


    @ApiModelProperty(value = "过户日期",hidden = true)
    private Date transferDate;


    /**
     * 车主姓名
     */
    @ApiModelProperty(value = "车主姓名")
    private String ownerName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date updateTime;
    //
//
    @ApiModelProperty(value = "图片路径00",hidden = true)
    private String image_path_00;


    @ApiModelProperty(value = "图片路径01",hidden = true)
    private String image_path_01;


    @ApiModelProperty(value = "图片路径02",hidden = true)
    private String image_path_02;


    @ApiModelProperty(value = "图片路径03",hidden = true)
    private String image_path_03;


    @ApiModelProperty(value = "图片路径04",hidden = true)
    private String image_path_04;
}
