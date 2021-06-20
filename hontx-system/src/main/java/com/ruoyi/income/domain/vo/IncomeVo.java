package com.ruoyi.income.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.income.domain.TbIncome;

public class IncomeVo  extends TbIncome {
    @Excel(name = "机构号")
    private String org;
    @Excel(name = "地区编码")
    private String region;
    @Excel(name = "开始时间")
    private String startTime;
    @Excel(name = "结束时间")
    private String endTime;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
