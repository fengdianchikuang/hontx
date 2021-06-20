package com.ruoyi.ai.domain.vo;



public class IdCardVo {
    private static final long serialVersionUID = 1L;
    //姓名
    private String name;
    //民族
    private String nation;
    //地址
    private String address;
    //身份证
    private String IdCored;
    //出生日期
    private String birthday;
    //性别
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCored() {
        return IdCored;
    }

    public void setIdCored(String idCored) {
        IdCored = idCored;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
