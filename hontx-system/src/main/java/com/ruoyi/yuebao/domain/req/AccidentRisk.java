package com.ruoyi.yuebao.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "驾意险投保信息")
@Data
public class AccidentRisk {
    @ApiModelProperty(value = "险种编码")
    private String riskCode;
    @ApiModelProperty(value = "产品编码|驾意险必传")
    private String productCode;
    @ApiModelProperty(value = "产品名称|驾意险必传")
    private String productName;
    @ApiModelProperty(value = "套餐编码|驾意险必传")
    private String packageCode;
    @ApiModelProperty(value = "套餐名称|驾意险必传")
    private String packageName;
    @ApiModelProperty(value = "购买份数|驾意险必传")
    private Integer buyBatch;
    @ApiModelProperty(value = "总保费|单份保费 * 购买份数")
    private Float premium;
}
