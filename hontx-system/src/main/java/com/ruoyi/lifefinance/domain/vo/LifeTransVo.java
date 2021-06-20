package com.ruoyi.lifefinance.domain.vo;

import com.ruoyi.lifefinance.domain.TbLifeTrans;

public class LifeTransVo extends TbLifeTrans {
    private String agent;
    private String agentTel;
    private String insurederName;
    private String insurederIdNo;
    private String productName;
    private String premium;

    public String getInsurederName() {
        return insurederName;
    }

    public void setInsurederName(String insurederName) {
        this.insurederName = insurederName;
    }

    public String getInsurederIdNo() {
        return insurederIdNo;
    }

    public void setInsurederIdNo(String insurederIdNo) {
        this.insurederIdNo = insurederIdNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }
}
