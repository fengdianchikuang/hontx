package com.ruoyi.yuebao.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "接口可能需要的其他数据")
@Data
public class Extend {
    @ApiModelProperty(value = "车辆关联系人名称|深圳人保公户车必填",position = 53)
    private String linkmanName;
    @ApiModelProperty(value = "车辆联系人身份证|诚泰公户车必填\n" +
            "太平洋支持填写\n" +
            "格式：区号-号码-分机号，如果没有分机号，号码后面部分不填写",position = 54)
    private String linkmanIdNo;
    @ApiModelProperty(value = "回调地址|异步回调模式必填；地址必须加入平台白名单",position = 55)
    private String callbackUrl;
    @ApiModelProperty(value = "检验码|需要前端录入校验码",position = 56)
    private String checkCode;
    @ApiModelProperty(value = "自主定价系数")
    private String priceRate;
}
