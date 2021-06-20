package com.ruoyi.yuebao.domain.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "基础信息")
@Data
public class BaseInfoVo {

    @ApiModelProperty(value = "用户类型:00普通用户,01代理人,02门店",example = "00")
    private String userType;

    @ApiModelProperty(value = "车主Id",example = "37")
    private Long ownerId;

    @ApiModelProperty(value = "机构网点编码",hidden = true)
    private String organizationId;
    @ApiModelProperty(value = "投保保险公司代码",example = "57")
    private Integer companyId;
    @ApiModelProperty(value = "工号编码",example = "33444")
    private String channelId;
    @ApiModelProperty(value = "交强险保险起期|YYYY-MM-DD|商业交强至少一种",example = "2021-01-27 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date tciStartDate;
    //    @ApiModelProperty(value = "交强险保险止期|YYYY-MM-DD|商业交强至少一种",position = 5)
//    private String tciEndDate;
    @ApiModelProperty(value = "商业险保险起期|YYYY-MM-DD|商业交强至少一种",example = "2021-01-27 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date vciStartDate;
}
