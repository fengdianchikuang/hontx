package com.ruoyi.income.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InformEntity {

    private Long id;

    private String aegntId;

    private String title;

    private String text;

    private String deviceType;

    private String respCode;

    private String respMsg;

    private Date createTime;
}
