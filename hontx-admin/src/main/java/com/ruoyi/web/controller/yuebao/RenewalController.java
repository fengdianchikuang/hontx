package com.ruoyi.web.controller.yuebao;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.yuebao.domain.req.InsureRequest;
import com.ruoyi.yuebao.domain.req.QuoteReq;
import com.ruoyi.yuebao.domain.req.RenewalReq;
import com.ruoyi.yuebao.service.RenewalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "保费计算,续保,核保接口")
@RestController
@RequestMapping("/car")
public class RenewalController {
    @Autowired
    private RenewalService renewalService;

    @ApiOperation("续保查询")
    @PostMapping("/getRenewal")
    public Object getRenewal(@RequestBody RenewalReq renewalRequest) throws Exception {
        return AjaxResult.success(renewalService.getRenewal(renewalRequest));
    }
    @ApiOperation(value = "保费计算")
    @PostMapping(value = "/calculation")
    public Object insuranceCalculation(@RequestBody QuoteReq quote, HttpServletRequest request) throws Exception {
        JSONObject jsonObject = renewalService.insuranceCalculation(quote,request);
        return AjaxResult.success(jsonObject);
    }
    @ApiOperation(value = "核保下单",notes = "接口返回907需要上传影像|行驶证:数量1,type\":\"T04_TAR04\"投保人身份证：数量2,正反：type\":\"T01_001\"")
    @PostMapping(value = "/insure")
    public Object underwriting(@RequestBody InsureRequest insureRequest) throws Exception {
        return AjaxResult.success(renewalService.insure(insureRequest));
    }

}
