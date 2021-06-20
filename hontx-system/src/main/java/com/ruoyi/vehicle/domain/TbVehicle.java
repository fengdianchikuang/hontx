package com.ruoyi.vehicle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * car对象 tb_vehicle
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public class TbVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long vId;

    /** 0:代表老数据1:代表悦保数据 */
    @Excel(name = "0:代表老数据1:代表悦保数据")
    private Integer flg;

    /** 车主Id */
    @ApiModelProperty(value = "车主Id")
    private Long ownerId;

    /** 车牌 */
    @Excel(name = "车牌")
    private String vehicleNo;

    /** $column.columnComment */
    private String channelId;

    /** $column.columnComment */
    private Date insrncEndDate;

    /** 发动机编号 */
    @Excel(name = "发动机编号")
    private String engineNo;

    /** 车架号/VIN码|frameNo */
    @Excel(name = "车架号/VIN码|frameNo")
    private String rackNo;

    /** 品牌|brandName */
    @Excel(name = "品牌|brandName")
    private String etBrand;

    /** 车型编码|modelCode */
    @Excel(name = "车型编码|modelCode")
    private String brandCode;

    /** 车辆估价 */
    @Excel(name = "车辆估价")
    private Integer purchasePrice;

    /** 车辆实际价格 */
    private Long actualPrice;

    /** 座位分类:六座以下101,六座到十座以下102,十坐到二十座103,二十座到三十六座104，三十六座及以上:105 */
    @Excel(name = "座位分类:六座以下101,六座到十座以下102,十坐到二十座103,二十座到三十六座104，三十六座及以上:105")
    private String vehicleCategory;

    /** 车辆类型:小型普通客车:K31,小型越野客车K32,轿车K33,微型普通客车K41,微型越野客车:K42,微型轿车:K43 */
    @Excel(name = "车辆类型:小型普通客车:K31,小型越野客车K32,轿车K33,微型普通客车K41,微型越野客车:K42,微型轿车:K43")
    private String vehicleType;

    /** 装备质量 */
    @Excel(name = "装备质量")
    private String vehicleValue;

    /** 总质量 */
    @Excel(name = "总质量")
    private Long displacement;

    /** 归属类型 */
    @Excel(name = "归属类型")
    private String belongType;

    /** useNature使用性质:非营业:100，营业:200 */
    @Excel(name = "useNature使用性质:非营业:100，营业:200")
    private String useType;

    /** 车型分类:小型汽车:101,大型汽车:102,小型新能源汽车:103,大型能源汽车:104,其他车型:199 */
    @Excel(name = "车型分类:小型汽车:101,大型汽车:102,小型新能源汽车:103,大型能源汽车:104,其他车型:199")
    private String plateType;

    /** 车牌底色:蓝101,黑:102,白:103,黄:104,白蓝:105,黄绿:106,渐变绿:107,其他:199 */
    @Excel(name = "车牌底色:蓝101,黑:102,白:103,黄:104,白蓝:105,黄绿:106,渐变绿:107,其他:199")
    private String plateColor;

    /** 能源类型:燃油:101,纯电动:102,燃料电池:103,插电混合动力:104,其他混合动力:199 */
    @Excel(name = "能源类型:燃油:101,纯电动:102,燃料电池:103,插电混合动力:104,其他混合动力:199")
    private String energyType;

    /** 使用性质分类:|当为营业时必填,101:家庭自用车,102:企业,103党政事业团体,199:其他非营业车辆,201:出租客车,202:城市公交,203:公路客运,205租凭客车,206营业货车 */
    @Excel(name = "使用性质分类:|当为营业时必填,101:家庭自用车,102:企业,103党政事业团体,199:其他非营业车辆,201:出租客车,202:城市公交,203:公路客运,205租凭客车,206营业货车")
    private String attachNature;

    /** 排气量:2.4T */
    @Excel(name = "排气量:2.4T")
    private String extMsr;

    /** 座位数|seatCount */
    @Excel(name = "座位数|seatCount")
    private String setNum;

    /** 吨位 */
    @Excel(name = "吨位")
    private String tonnage;

    /** 是否过户标识 */
    @Excel(name = "是否过户标识")
    private String changeOwnerFlag;

    /** 过户日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过户日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transferDate;

    /** 车主名 */
    private String owner;

    /** $column.columnComment */
    private String ownerCertType;

    /** $column.columnComment */
    private String ownerCertNo;

    /** $column.columnComment */
    private String ownerTel;

    /** $column.columnComment */
    private String agentId;

    /** $column.columnComment */
    private String queryId;

    /** $column.columnComment */
    private Date vCreateTime;

    /** $column.columnComment */
    private Date vUpdateTime;

    /** $column.columnComment */
    private Date registerDate;

    /** $column.columnComment */
    private Date insrncBgnTmSy;

    /** $column.columnComment */
    private Date insrncEndTmSy;

    /** $column.columnComment */
    private Date insrncBgnTmJq;

    /** $column.columnComment */
    private Date insrncEndTmJq;

    /** $column.columnComment */
    private String applicantName;

    /** $column.columnComment */
    private String applicantCertType;

    /** $column.columnComment */
    private String applicantCertNo;

    /** $column.columnComment */
    private String applicantTel;

    /** $column.columnComment */
    private String applicantSex;

    /** $column.columnComment */
    private String applicantAge;

    /** $column.columnComment */
    private String insuredName;

    /** $column.columnComment */
    private String insuredCertType;

    /** $column.columnComment */
    private String insuredCertNo;

    /** $column.columnComment */
    private String insuredTel;

    /** $column.columnComment */
    private String insuredSex;

    /** $column.columnComment */
    private String insuredAge;

    /** $column.columnComment */
    private String openid;

    /** $column.columnComment */
    private String kbId;

    /** $column.columnComment */
    private String gId;

    /** $column.columnComment */
    private String company;

    /** $column.columnComment */
    private String address;

    /** $column.columnComment */
    private String color;

    /** $column.columnComment */
    private String carName;

    /** $column.columnComment */
    private String kbInsrncCpyName;

    public void setvId(Long vId)
    {
        this.vId = vId;
    }

    public Long getvId()
    {
        return vId;
    }
    public void setFlg(Integer flg)
    {
        this.flg = flg;
    }

    public Integer getFlg()
    {
        return flg;
    }
    public void setOwnerId(Long ownerId)
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId()
    {
        return ownerId;
    }
    public void setVehicleNo(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo()
    {
        return vehicleNo;
    }
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getChannelId()
    {
        return channelId;
    }
    public void setInsrncEndDate(Date insrncEndDate)
    {
        this.insrncEndDate = insrncEndDate;
    }

    public Date getInsrncEndDate()
    {
        return insrncEndDate;
    }
    public void setEngineNo(String engineNo)
    {
        this.engineNo = engineNo;
    }

    public String getEngineNo()
    {
        return engineNo;
    }
    public void setRackNo(String rackNo)
    {
        this.rackNo = rackNo;
    }

    public String getRackNo()
    {
        return rackNo;
    }
    public void setEtBrand(String etBrand)
    {
        this.etBrand = etBrand;
    }

    public String getEtBrand()
    {
        return etBrand;
    }
    public void setBrandCode(String brandCode)
    {
        this.brandCode = brandCode;
    }

    public String getBrandCode()
    {
        return brandCode;
    }
    public void setPurchasePrice(Integer purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchasePrice()
    {
        return purchasePrice;
    }
    public void setActualPrice(Long actualPrice)
    {
        this.actualPrice = actualPrice;
    }

    public Long getActualPrice()
    {
        return actualPrice;
    }
    public void setVehicleCategory(String vehicleCategory)
    {
        this.vehicleCategory = vehicleCategory;
    }

    public String getVehicleCategory()
    {
        return vehicleCategory;
    }
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
    public void setVehicleValue(String vehicleValue)
    {
        this.vehicleValue = vehicleValue;
    }

    public String getVehicleValue()
    {
        return vehicleValue;
    }
    public void setDisplacement(Long displacement)
    {
        this.displacement = displacement;
    }

    public Long getDisplacement()
    {
        return displacement;
    }
    public void setBelongType(String belongType)
    {
        this.belongType = belongType;
    }

    public String getBelongType()
    {
        return belongType;
    }
    public void setUseType(String useType)
    {
        this.useType = useType;
    }

    public String getUseType()
    {
        return useType;
    }
    public void setPlateType(String plateType)
    {
        this.plateType = plateType;
    }

    public String getPlateType()
    {
        return plateType;
    }
    public void setPlateColor(String plateColor)
    {
        this.plateColor = plateColor;
    }

    public String getPlateColor()
    {
        return plateColor;
    }
    public void setEnergyType(String energyType)
    {
        this.energyType = energyType;
    }

    public String getEnergyType()
    {
        return energyType;
    }
    public void setAttachNature(String attachNature)
    {
        this.attachNature = attachNature;
    }

    public String getAttachNature()
    {
        return attachNature;
    }
    public void setExtMsr(String extMsr)
    {
        this.extMsr = extMsr;
    }

    public String getExtMsr()
    {
        return extMsr;
    }
    public void setSetNum(String setNum)
    {
        this.setNum = setNum;
    }

    public String getSetNum()
    {
        return setNum;
    }
    public void setTonnage(String tonnage)
    {
        this.tonnage = tonnage;
    }

    public String getTonnage()
    {
        return tonnage;
    }
    public void setChangeOwnerFlag(String changeOwnerFlag)
    {
        this.changeOwnerFlag = changeOwnerFlag;
    }

    public String getChangeOwnerFlag()
    {
        return changeOwnerFlag;
    }
    public void setTransferDate(Date transferDate)
    {
        this.transferDate = transferDate;
    }

    public Date getTransferDate()
    {
        return transferDate;
    }
    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getOwner()
    {
        return owner;
    }
    public void setOwnerCertType(String ownerCertType)
    {
        this.ownerCertType = ownerCertType;
    }

    public String getOwnerCertType()
    {
        return ownerCertType;
    }
    public void setOwnerCertNo(String ownerCertNo)
    {
        this.ownerCertNo = ownerCertNo;
    }

    public String getOwnerCertNo()
    {
        return ownerCertNo;
    }
    public void setOwnerTel(String ownerTel)
    {
        this.ownerTel = ownerTel;
    }

    public String getOwnerTel()
    {
        return ownerTel;
    }
    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getAgentId()
    {
        return agentId;
    }
    public void setQueryId(String queryId)
    {
        this.queryId = queryId;
    }

    public String getQueryId()
    {
        return queryId;
    }
    public void setvCreateTime(Date vCreateTime)
    {
        this.vCreateTime = vCreateTime;
    }

    public Date getvCreateTime()
    {
        return vCreateTime;
    }
    public void setvUpdateTime(Date vUpdateTime)
    {
        this.vUpdateTime = vUpdateTime;
    }

    public Date getvUpdateTime()
    {
        return vUpdateTime;
    }
    public void setRegisterDate(Date registerDate)
    {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate()
    {
        return registerDate;
    }
    public void setInsrncBgnTmSy(Date insrncBgnTmSy)
    {
        this.insrncBgnTmSy = insrncBgnTmSy;
    }

    public Date getInsrncBgnTmSy()
    {
        return insrncBgnTmSy;
    }
    public void setInsrncEndTmSy(Date insrncEndTmSy)
    {
        this.insrncEndTmSy = insrncEndTmSy;
    }

    public Date getInsrncEndTmSy()
    {
        return insrncEndTmSy;
    }
    public void setInsrncBgnTmJq(Date insrncBgnTmJq)
    {
        this.insrncBgnTmJq = insrncBgnTmJq;
    }

    public Date getInsrncBgnTmJq()
    {
        return insrncBgnTmJq;
    }
    public void setInsrncEndTmJq(Date insrncEndTmJq)
    {
        this.insrncEndTmJq = insrncEndTmJq;
    }

    public Date getInsrncEndTmJq()
    {
        return insrncEndTmJq;
    }
    public void setApplicantName(String applicantName)
    {
        this.applicantName = applicantName;
    }

    public String getApplicantName()
    {
        return applicantName;
    }
    public void setApplicantCertType(String applicantCertType)
    {
        this.applicantCertType = applicantCertType;
    }

    public String getApplicantCertType()
    {
        return applicantCertType;
    }
    public void setApplicantCertNo(String applicantCertNo)
    {
        this.applicantCertNo = applicantCertNo;
    }

    public String getApplicantCertNo()
    {
        return applicantCertNo;
    }
    public void setApplicantTel(String applicantTel)
    {
        this.applicantTel = applicantTel;
    }

    public String getApplicantTel()
    {
        return applicantTel;
    }
    public void setApplicantSex(String applicantSex)
    {
        this.applicantSex = applicantSex;
    }

    public String getApplicantSex()
    {
        return applicantSex;
    }
    public void setApplicantAge(String applicantAge)
    {
        this.applicantAge = applicantAge;
    }

    public String getApplicantAge()
    {
        return applicantAge;
    }
    public void setInsuredName(String insuredName)
    {
        this.insuredName = insuredName;
    }

    public String getInsuredName()
    {
        return insuredName;
    }
    public void setInsuredCertType(String insuredCertType)
    {
        this.insuredCertType = insuredCertType;
    }

    public String getInsuredCertType()
    {
        return insuredCertType;
    }
    public void setInsuredCertNo(String insuredCertNo)
    {
        this.insuredCertNo = insuredCertNo;
    }

    public String getInsuredCertNo()
    {
        return insuredCertNo;
    }
    public void setInsuredTel(String insuredTel)
    {
        this.insuredTel = insuredTel;
    }

    public String getInsuredTel()
    {
        return insuredTel;
    }
    public void setInsuredSex(String insuredSex)
    {
        this.insuredSex = insuredSex;
    }

    public String getInsuredSex()
    {
        return insuredSex;
    }
    public void setInsuredAge(String insuredAge)
    {
        this.insuredAge = insuredAge;
    }

    public String getInsuredAge()
    {
        return insuredAge;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }
    public void setKbId(String kbId)
    {
        this.kbId = kbId;
    }

    public String getKbId()
    {
        return kbId;
    }
    public void setgId(String gId)
    {
        this.gId = gId;
    }

    public String getgId()
    {
        return gId;
    }
    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getCompany()
    {
        return company;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }
    public void setCarName(String carName)
    {
        this.carName = carName;
    }

    public String getCarName()
    {
        return carName;
    }
    public void setKbInsrncCpyName(String kbInsrncCpyName)
    {
        this.kbInsrncCpyName = kbInsrncCpyName;
    }

    public String getKbInsrncCpyName()
    {
        return kbInsrncCpyName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vId", getvId())
            .append("flg", getFlg())
            .append("ownerId", getOwnerId())
            .append("vehicleNo", getVehicleNo())
            .append("channelId", getChannelId())
            .append("insrncEndDate", getInsrncEndDate())
            .append("engineNo", getEngineNo())
            .append("rackNo", getRackNo())
            .append("etBrand", getEtBrand())
            .append("brandCode", getBrandCode())
            .append("purchasePrice", getPurchasePrice())
            .append("actualPrice", getActualPrice())
            .append("vehicleCategory", getVehicleCategory())
            .append("vehicleType", getVehicleType())
            .append("vehicleValue", getVehicleValue())
            .append("displacement", getDisplacement())
            .append("belongType", getBelongType())
            .append("useType", getUseType())
            .append("plateType", getPlateType())
            .append("plateColor", getPlateColor())
            .append("energyType", getEnergyType())
            .append("attachNature", getAttachNature())
            .append("extMsr", getExtMsr())
            .append("setNum", getSetNum())
            .append("tonnage", getTonnage())
            .append("changeOwnerFlag", getChangeOwnerFlag())
            .append("transferDate", getTransferDate())
            .append("owner", getOwner())
            .append("ownerCertType", getOwnerCertType())
            .append("ownerCertNo", getOwnerCertNo())
            .append("ownerTel", getOwnerTel())
            .append("agentId", getAgentId())
            .append("queryId", getQueryId())
            .append("remark", getRemark())
            .append("vCreateTime", getvCreateTime())
            .append("vUpdateTime", getvUpdateTime())
            .append("registerDate", getRegisterDate())
            .append("insrncBgnTmSy", getInsrncBgnTmSy())
            .append("insrncEndTmSy", getInsrncEndTmSy())
            .append("insrncBgnTmJq", getInsrncBgnTmJq())
            .append("insrncEndTmJq", getInsrncEndTmJq())
            .append("applicantName", getApplicantName())
            .append("applicantCertType", getApplicantCertType())
            .append("applicantCertNo", getApplicantCertNo())
            .append("applicantTel", getApplicantTel())
            .append("applicantSex", getApplicantSex())
            .append("applicantAge", getApplicantAge())
            .append("insuredName", getInsuredName())
            .append("insuredCertType", getInsuredCertType())
            .append("insuredCertNo", getInsuredCertNo())
            .append("insuredTel", getInsuredTel())
            .append("insuredSex", getInsuredSex())
            .append("insuredAge", getInsuredAge())
            .append("openid", getOpenid())
            .append("kbId", getKbId())
            .append("gId", getgId())
            .append("company", getCompany())
            .append("address", getAddress())
            .append("color", getColor())
            .append("carName", getCarName())
            .append("kbInsrncCpyName", getKbInsrncCpyName())
            .toString();
    }
}
