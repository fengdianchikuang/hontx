package com.ruoyi.yuebao.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.yuebao.domain.NoCarRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NoCarService {
    @Value("${echeli.organizationId}")
    private String organization;

    @Value("${ybinsure.url}")
    private String url;


    /**
     * 查询费车险
     * @param noCarRequest
     */
    public String getNoCarRisk(NoCarRequest noCarRequest) {
        noCarRequest.getBaseInfo().setOrganizationId(organization);
        String result = OkHttpUtils.postJSON(url + "/noCar/queryRisk", JSON.toJSONString(noCarRequest),null);
        return result;
    }
}
