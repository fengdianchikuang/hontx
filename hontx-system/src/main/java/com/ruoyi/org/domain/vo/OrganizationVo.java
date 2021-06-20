package com.ruoyi.org.domain.vo;

import com.ruoyi.org.domain.TbOrganization;

public class OrganizationVo extends TbOrganization {

    private String parentOrg;
    private String parentType;

    public String getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(String parentOrg) {
        this.parentOrg = parentOrg;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }
}
