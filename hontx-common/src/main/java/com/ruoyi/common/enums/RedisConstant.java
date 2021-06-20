package com.ruoyi.common.enums;

public class RedisConstant {
    /**
     * 可用的保险公司集合
     */
    public static final String INSURANCE_COMPANY = "insurance_company";

    /**
     * 通用保险选项配置
     */
    public static final String INSURANCE_COMMON_RISK = "insurance_common_risk_config";

    /**
     * 每家保险公司保险选项配置
     */
    public static final String INSURANCE_COMPANY_RISK = "insurance_company_risk_config";

    /**
     * token key前缀
     */
    public static final String USER_TOKEN_PREFIX = "user_token_";

    /**
     * token过期时间 1个小时
     */
    public static final Long USER_TOKEN_EXPIRE = 1000 * 60 * 60L;

    public static final String IMAGE_TYPE_MAP_KEY = "image_type";

    /**
     * 保险公司名称前缀
     */
    public static final String INSURANCE_COMPANY_PREFIX = "insurance_";


    /**
     * 支付回调redis key
     */
    public static final String PAY_REDIS_KEY = "pay_notify";
}
