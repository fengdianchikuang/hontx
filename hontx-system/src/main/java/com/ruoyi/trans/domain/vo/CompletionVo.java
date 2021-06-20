package com.ruoyi.trans.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "保险完成情况")
@Data
public class CompletionVo {
    @ApiModelProperty(value = "当前用户出单件数")
    private Integer agentCount;
    @ApiModelProperty(value = "商业险当月保费总额")
    private String vciPremium;

}
