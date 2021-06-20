package com.ruoyi.common.enums;

/**
 * 悦保与以前兼容
 */
public enum CompanyOption {
    transformation1(1,"03"),
    transformation2(2,"02"),
    transformation31(31,"04"),
    transformation44(44,"01"),
    transformation54(51,"15"),
    transformation57(57,"06");

    private Integer companyId;
    private String channel;

    CompanyOption(Integer companyId, String channel) {
        this.companyId = companyId;
        this.channel = channel;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getChannel() {
        return channel;
    }

    public static String transformation(Integer companyId){
        for (CompanyOption value : CompanyOption.values()) {
            if(value.getCompanyId() == companyId){
                return value.getChannel();
            }
        }
        return null;
    }
}
