package com.ruoyi.web.controller.yuebao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.yuebao.domain.req.PaymentRequest;
import com.ruoyi.yuebao.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "保险支付,回调,查询",tags = "支付,回调,保单查询")
@RequestMapping("/Insurance")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "申请支付")
    @PostMapping(value = "/payment")
    public Object payment(@RequestBody PaymentRequest paymentRequest) {
        log.info("申请支付请求参数:{}", JSON.toJSON(paymentRequest));
        String payment = paymentService.payment(paymentRequest);
        JSONObject jsonObject = JSONObject.parseObject(payment);
        if(jsonObject.getInteger("status") == 200){
            return AjaxResult.success(jsonObject.getJSONObject("data"));
        }
        return AjaxResult.error(jsonObject.getString("message"));
    }

}
