package com.ruoyi.feign;


import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.user.domain.req.CarOwner;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "shop-service",url = "http://106.12.217.177:9200")
@RestController
public interface ICarOwnerService {

    @PostMapping(value = "/carOwner/change")
    AjaxResult changeRealId(@RequestParam(value = "realId") Integer realId, @RequestParam(value = "carOwnerId") Long carOwnerId);

    @GetMapping(value = "/carOwner/find/{ownerId}")
    CarOwner findById(@PathVariable("ownerId") Long ownerId);

    @PostMapping(value = "/carOwner/updateAmount")
    int updateAmount(@RequestBody CarOwner owner);
}
