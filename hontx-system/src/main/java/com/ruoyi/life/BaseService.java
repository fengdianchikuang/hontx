package com.ruoyi.life;

import com.alibaba.fastjson.JSONObject;

public class BaseService {
    public static final String productListUrl = "https://openapi.12354.com";
    /**
     * 加密串
     */
    public static final  String DES = "sT3k93JZhUt6qT76JZhmUAYglqFSO8v0";

    /**
     * 获取创富云header JSON参数前缀
     * @return
     */
    public JSONObject getParams(){
        long nowTime = System.currentTimeMillis();
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("access_key","BTEfGg8o6nHUEmrK");
        header.put("access_secret","7Q2OX3DOP4VEdqZpz1nDPaBJKvTJ73yY");
        header.put("request_at",nowTime+"");
        header.put("business_no","jq"+nowTime);
        data.put("header",header);
        return data;
    }
}
