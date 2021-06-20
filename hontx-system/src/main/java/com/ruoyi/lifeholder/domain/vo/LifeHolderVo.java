package com.ruoyi.lifeholder.domain.vo;

import com.ruoyi.lifeholder.domain.TbLifeHolder;

public class LifeHolderVo extends TbLifeHolder {

    private String agent;

    private String agentTel;

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
