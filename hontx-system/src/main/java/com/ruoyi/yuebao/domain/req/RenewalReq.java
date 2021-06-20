package com.ruoyi.yuebao.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("续保查询参数")
public class RenewalReq {
    @ApiModelProperty(value = "车牌号", example = "湘AC7C86")
    private String licenseNo;
    @ApiModelProperty(value = "车主身份证后6位", example = "261076")
    private String cardLastNo;
    @ApiModelProperty(value = "车架号/vin", example = "LSVNF45E4HN116832")
    private String frameNo;
    @ApiModelProperty(value = "车主Id")
    private Long ownerId;
    @ApiModelProperty(value = "0:按正常流程:(保险到期更新续保信息),1:强制更新续保日期")
    private Integer status = 0;
}
