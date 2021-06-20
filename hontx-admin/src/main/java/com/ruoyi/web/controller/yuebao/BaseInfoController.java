package com.ruoyi.web.controller.yuebao;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.yuebao.service.BaseInfoConService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = "车辆保险基本信息请求")
@RestController
@RequestMapping("/baseInfo")
public class BaseInfoController {
    @Autowired
    private BaseInfoConService baseInfoService;


    @ApiOperation(value = "请求平台属性字段")
    @PostMapping(value = "/query/policyConfig")
    public Object queryPolicyConfig() throws IOException {
        return AjaxResult.success("data",baseInfoService.queryPolicyConfig());
    }

    @ApiOperation(value = "查询通用主险保险项(主险)")
    @PostMapping(value = "/common/queryRiskConfig")
    public Object queryRiskConfig() throws IOException {
        return AjaxResult.success("data",baseInfoService.queryRiskConfig());
    }

    @ApiOperation(value = "根据保险公司Id获取险种选项(附加险)")
    @PostMapping(value = "/common/queryCompanyRiskConfig/{companyId}")
    public Object queryCompanyRiskConfig(@PathVariable Integer companyId) throws IOException {
        return AjaxResult.success("data",baseInfoService.updateCompanyRiskConfig(companyId+""));
    }

    @ApiOperation(value = "查询可用机构网点(查询本公司机构代号)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationId", value = "机构网点编码")
    })
    @PostMapping(value = "/queryOrganization")
    public Object queryOrganization() throws IOException {
        return AjaxResult.success("data",baseInfoService.queryOrganization());
    }

    @ApiOperation(value = "更新本公司能用保险公司(宏泰祥可用)")
    @PostMapping(value = "/updateCompany")
    public Object queryCompany(HttpServletRequest httpServletRequest) throws IOException {
        return AjaxResult.success("data",baseInfoService.updateCompany(httpServletRequest));
    }
}
