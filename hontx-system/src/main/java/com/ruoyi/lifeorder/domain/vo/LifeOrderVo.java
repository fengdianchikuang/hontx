package com.ruoyi.lifeorder.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.lifeorder.domain.TbLifeOrder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class LifeOrderVo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderId; //订单Id

    private String agentId;

    private String agentName; //代理人

    private String agentTel; //代理人电话

    private String holderName; //投保人姓名

    private String holderIdNo; //投保人证件号

    private String holderTel; //投保人电话

    private String payTime; //支付时间

    private String productName; //投保产品

    private String insurederName; //被报人

    private String insurederIdNo; //被保人证件号

    private String status; //订单状态状态

    private String payType; //支付类型

    private String payPerm; //已字符时间

    private String policyStatus; //保单状态

    private String policyNo; //保单号

    private String premium; //保费
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date guaranteeStartTime; //保单开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date guaranteeEndTime; //保单结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime; //更新时间


}



