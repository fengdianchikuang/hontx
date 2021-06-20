package com.ruoyi.yuebao.domain.req;

import com.ruoyi.applicant.domain.Applicant;
import com.ruoyi.risks.domain.Risks;
import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel(value = "保费计算")
@Accessors(chain =true)
@Data
public class QuoteReq {
    @ApiModelProperty(value = "基础信息",required = true)
    private BaseInfoVo baseInfo;
    @ApiModelProperty(value = "车辆信息",required = true)
    private VehicleInfoVo vehicleInfo;
    @ApiModelProperty(value = "关系人信息",required = true)
    private List<Applicant> applicants;

    @ApiModelProperty(value = "商业险别信息|投保商业险必传（费改新险别）")
    private List<Risks> risks;


    @ApiModelProperty(value = "驾意险投保信息|投保驾意险必传")
    private List<AccidentRisk> noCars;
    @ApiModelProperty(value = "扩展属性")
    private Extend extend;
}
