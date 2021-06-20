package com.ruoyi.user.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户保险信息团队信息
 */
@ApiModel(value = "用户保险团队信息")
@Data
public class UserTeamVo {
    @ApiModelProperty(value = "a团队数")
    private Integer aTeamNum;
    @ApiModelProperty(value = "b团队数")
    private Integer bTeamNum;


    @ApiModelProperty(value = "A团队列表")
    private List<UserSimple> aTeams;
    @ApiModelProperty(value = "B团队列表")
    private List<UserSimple> bTeams;

}
