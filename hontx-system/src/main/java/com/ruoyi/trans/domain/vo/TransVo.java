package com.ruoyi.trans.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

public class TransVo {
//    @Excel(name = "编号", readConverterExp = "$column.readConverterExp()")
    @Excel(name = "编号")
    private Long id;
    /** 提现人姓名 */
    @Excel(name = "提现人姓名")
    private String name; //姓名
    @Excel(name = "电话")
    private String phone; //手机
    @Excel(name = "提现人身份证")
    private String idNo; //身份证
    @Excel(name = "金额")
    private String amount; //提现金额
    /** 用户余额 */
    @Excel(name = "用户余额")
    private String balance; //余额
    @Excel(name = "卡号")
    private String bankNo; //卡号
    @Excel(name = "银行")
    private String bankName; //银行名
    @Excel(name = "开户行")
    private String branch; //支行
    @Excel(name = "所属机构")
    private String orgId; //所属机构
    @Excel(name = "地区")
    private String region; //地区
    /** $column.columnComment */
    @Excel(name = "提现状态")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30)
//    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String createTime; //申请时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
