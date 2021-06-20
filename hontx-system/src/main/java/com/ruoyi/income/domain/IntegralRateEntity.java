package com.ruoyi.income.domain;

import lombok.Data;

import java.util.Date;
@Data
public class IntegralRateEntity {

    private static final long serialVersionUID = 1L;
    private String address_id;

    private String province_code;

    private String city_code;

    private String county_code;

    private String address;

    private String agent_id;

    private String addressee;

    private String addressee_tel;

    private Date a_create_time;

    private Date a_update_time;

    private String channelId;

    private String insrncName;

    private String totalRate;

    private String agentRate;

    private String aRate;

    private String bRate;

    private String orgId;

    private Date effectDate;

    private Date createTime;

    private Date updateTime;
}
