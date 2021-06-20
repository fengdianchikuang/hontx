package com.ruoyi.vehicle.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VehicleInspectVo {
    @ApiModelProperty(value = "车主ID")
    private String ownerId;
    @ApiModelProperty(value = "车主")
    private String owner;
    @ApiModelProperty(value = "车牌 ")
    private String vehicleNo;
    @ApiModelProperty(value = "发动机编号")
    private String engineNO;
    @ApiModelProperty(value = "车架号")
    private String rackNo;
    @ApiModelProperty(value = "品牌")
    private String etBrand;
    @ApiModelProperty(value = "所属性质")
    private String belongType;
    @ApiModelProperty(value = "使用性质")
    private String useType;
    @ApiModelProperty(value = "核定载人数")
    private String setNum;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//json的返回格式
    @ApiModelProperty(value = "车辆注册日期")
    private Date registerDate;
    @ApiModelProperty(value = "00代表免上线,01代表上线")
    private String inspectCode;
    @ApiModelProperty(value = "车辆距离年审日期")
    private String inspectExpire;

    @ApiModelProperty(value = "询价单号")
    private String queryId;

    @ApiModelProperty(value = "保险费",hidden = true)
    private String premium;

    @ApiModelProperty(value = "商业保险费",hidden = true)
    private String premiumSy;

    @ApiModelProperty(value = "交强保险费",hidden = true)
    private String premiumJq;

    @ApiModelProperty(value = "税款",hidden = true)
    private String tax;

    @ApiModelProperty(value = "交强险保单号",hidden = true)
    private String jqAppNo;

    @ApiModelProperty(value = "商业险保单号",hidden = true)
    private String syAppNo;

    @ApiModelProperty(value = "商业险过期时间",hidden = true)
    private String syInsrncExpire;

    @ApiModelProperty(value = "交强险过期时间",hidden = true)
    private String jqINsrncExpire;
}
