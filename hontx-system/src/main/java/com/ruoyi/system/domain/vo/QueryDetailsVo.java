package com.ruoyi.system.domain.vo;



import com.ruoyi.system.domain.QueryInsrnc;
import com.ruoyi.system.domain.VehicleFile;

import java.time.LocalDateTime;
import java.util.List;


public class QueryDetailsVo {
    private Long qId;
    private String queryId;
//    @ApiModelProperty(value = "人工标志:当为1时，全部渠道走人工；当为0时，非对接的走人工，对接的走非人工")
    private String flag;
//    @ApiModelProperty(value = "车主姓名")
    private String owner;
//    @ApiModelProperty(value = "投保人姓名")
    private String applicantName;
//    @ApiModelProperty(value = "投保人证件号")
    private String applicantCertNo;
//    @ApiModelProperty(value = "投保人电话号码")
    private String applicantTel;
//    @ApiModelProperty(value = "车牌号")
    private String vehicleNo;
//    @ApiModelProperty(value = "车架号")
    private String rackNo;
//    @ApiModelProperty(value = "渠道id：华安渠道为00代表保险公司的出单工号")
    private String channelId;
//    @ApiModelProperty(value = "险种代码")
    private String instituteCode;
//    @ApiModelProperty(value = "保险费")
    private String premium;
//    @ApiModelProperty(value = "商业保险费")
    private String premiumSy;
//    @ApiModelProperty(value = "交强保险费")
    private String premiumJq;
//    @ApiModelProperty(value = "车船税")
    private String tax;
//    @ApiModelProperty(value = "询价状态00:待询价01:已询价02:待投保03:驳回修改04:待支付05:已投保")
    private String status;
//    @ApiModelProperty(value = "使用类型:00个人,01企业")
    private String useType;
    private String belongType;
//    @ApiModelProperty(value = "代理人Id（即用户Id）")
    private String agentId;
//    @ApiModelProperty(value = "代理人姓名")
    private String agentName;
//    @ApiModelProperty(value = "未实名昵称")
    private String name;
//    @ApiModelProperty(value = "代理人电话")
    private String agentTel;
//    @ApiModelProperty(value = "区号")
    private String areaCode;
//    @ApiModelProperty(value = "机构码")
    private String orgId;
//    @ApiModelProperty(value = "创建时间")
    private LocalDateTime qCreateTime;

    private String content;
    private String remark;
    private String url01;
    private String url02;
    private String url03;
    private String url04;
//    @ApiModelProperty(value = "更新时间")
    private LocalDateTime qUpdateTime;
//    @ApiModelProperty(value = "过户日期")
    private LocalDateTime transferDate;
    private List<VehicleFile> vehicleFile;
    private List<QueryInsrnc> queryInsrnc;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantCertNo() {
        return applicantCertNo;
    }

    public void setApplicantCertNo(String applicantCertNo) {
        this.applicantCertNo = applicantCertNo;
    }

    public String getApplicantTel() {
        return applicantTel;
    }

    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public void setInstituteCode(String instituteCode) {
        this.instituteCode = instituteCode;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getPremiumSy() {
        return premiumSy;
    }

    public void setPremiumSy(String premiumSy) {
        this.premiumSy = premiumSy;
    }

    public String getPremiumJq() {
        return premiumJq;
    }

    public void setPremiumJq(String premiumJq) {
        this.premiumJq = premiumJq;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public LocalDateTime getqCreateTime() {
        return qCreateTime;
    }

    public void setqCreateTime(LocalDateTime qCreateTime) {
        this.qCreateTime = qCreateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public LocalDateTime getqUpdateTime() {
        return qUpdateTime;
    }

    public void setqUpdateTime(LocalDateTime qUpdateTime) {
        this.qUpdateTime = qUpdateTime;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public List<VehicleFile> getVehicleFile() {
        return vehicleFile;
    }

    public void setVehicleFile(List<VehicleFile> vehicleFile) {
        this.vehicleFile = vehicleFile;
    }

    public List<QueryInsrnc> getQueryInsrnc() {
        return queryInsrnc;
    }

    public void setQueryInsrnc(List<QueryInsrnc> queryInsrnc) {
        this.queryInsrnc = queryInsrnc;
    }
}
