package com.ruoyi.income.domain;

import lombok.Data;

import java.util.Date;

@Data
public class HontxTransEntity  {

    private static final long serialVersionUID =1L;

    protected Long id;

    private String transNo ;

    private String transType ;

    private String amount  ;

    private String agentId  ;

    private String availableMoney ;

    private String vehicleNo ;

    private String rackNo  ;

    private String queryId ;

    private String status ;

    private String remark ;

//    private UserEntity userEntity;

    private Date createTime ;

    private Date updateTime;
}
