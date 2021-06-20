package com.ruoyi.web.controller.yuebao;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.yuebao.domain.NoCarRequest;
import com.ruoyi.yuebao.service.NoCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "非车险接口查询")
@RequestMapping("/noCar")
public class NoCarController {
    @Autowired
    private NoCarService noCarService;

    @ApiOperation("查询非车险种")
    @PostMapping(value = "/queryRisk")
    public Object getNoCarRisk(@RequestBody NoCarRequest noCarRequest){
        String noCarRisk = noCarService.getNoCarRisk(noCarRequest);
        JSONObject jsonObject = JSONObject.parseObject(noCarRisk);
        return AjaxResult.success("data",jsonObject);
    }


}
