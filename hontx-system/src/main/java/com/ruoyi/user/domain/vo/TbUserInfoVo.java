package com.ruoyi.user.domain.vo;

import com.ruoyi.user.domain.TbUserFile;
import com.ruoyi.user.domain.TbUserInfo;

import java.util.List;

public class TbUserInfoVo extends TbUserInfo {
    private String parentName;

    private String url01;

    private String url02;

    private String url03;

    private String url04;

    private List<TbUserFile> files;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<TbUserFile> getFiles() {
        return files;
    }

    public void setFiles(List<TbUserFile> files) {
        this.files = files;
    }

    public String getUrl01() {
        return url01;
    }

    public void setUrl01(String url01) {
        this.url01 = url01;
    }

    public String getUrl02() {
        return url02;
    }

    public void setUrl02(String url02) {
        this.url02 = url02;
    }

    public String getUrl03() {
        return url03;
    }

    public void setUrl03(String url03) {
        this.url03 = url03;
    }

    public String getUrl04() {
        return url04;
    }

    public void setUrl04(String url04) {
        this.url04 = url04;
    }
}
