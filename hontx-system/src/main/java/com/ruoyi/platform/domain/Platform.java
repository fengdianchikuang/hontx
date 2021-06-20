package com.ruoyi.platform.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 保司平台信息对象 tb_platform
 * 
 * @author ruoyi
 * @date 2021-06-16
 */
public class Platform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNo;

    /** 车主id */
    @Excel(name = "车主id")
    private Long ownerId;

    /** 无赔款优待系数 */
    @Excel(name = "无赔款优待系数")
    private String ncd;

    /** 交通违法率 */
    @Excel(name = "交通违法率")
    private String trafficTransgressRate;

    /** 信道费率 */
    @Excel(name = "信道费率")
    private String channelRate;

    /** 承销费率 */
    @Excel(name = "承销费率")
    private String underwritingRate;

    /** 价格费率 */
    @Excel(name = "价格费率")
    private String priceRate;

    /** 投保年限 */
    @Excel(name = "投保年限")
    private Integer insureYears;

    /** 交强险分数 */
    @Excel(name = "交强险分数")
    private String tciScore;

    /** 商业险分数 */
    @Excel(name = "商业险分数")
    private String vciScore;

    /** 交强险预期赔付率 */
    @Excel(name = "交强险预期赔付率")
    private String tciExpectPayoutRatio;

    /** 商业险预期赔付率 */
    @Excel(name = "商业险预期赔付率")
    private String vciExpectPayoutRatio;

    /** 出险次数 */
    @Excel(name = "出险次数")
    private Integer ncdLevel;

    /** 连续承保期间出险次数 */
    @Excel(name = "连续承保期间出险次数")
    private Integer claimTimes;

    /** 总分 */
    @Excel(name = "总分")
    private String totalScore;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFrameNo(String frameNo) 
    {
        this.frameNo = frameNo;
    }

    public String getFrameNo() 
    {
        return frameNo;
    }
    public void setOwnerId(Long ownerId) 
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() 
    {
        return ownerId;
    }
    public void setNcd(String ncd) 
    {
        this.ncd = ncd;
    }

    public String getNcd() 
    {
        return ncd;
    }
    public void setTrafficTransgressRate(String trafficTransgressRate) 
    {
        this.trafficTransgressRate = trafficTransgressRate;
    }

    public String getTrafficTransgressRate() 
    {
        return trafficTransgressRate;
    }
    public void setChannelRate(String channelRate) 
    {
        this.channelRate = channelRate;
    }

    public String getChannelRate() 
    {
        return channelRate;
    }
    public void setUnderwritingRate(String underwritingRate) 
    {
        this.underwritingRate = underwritingRate;
    }

    public String getUnderwritingRate() 
    {
        return underwritingRate;
    }
    public void setPriceRate(String priceRate) 
    {
        this.priceRate = priceRate;
    }

    public String getPriceRate() 
    {
        return priceRate;
    }
    public void setInsureYears(Integer insureYears) 
    {
        this.insureYears = insureYears;
    }

    public Integer getInsureYears() 
    {
        return insureYears;
    }
    public void setTciScore(String tciScore) 
    {
        this.tciScore = tciScore;
    }

    public String getTciScore() 
    {
        return tciScore;
    }
    public void setVciScore(String vciScore) 
    {
        this.vciScore = vciScore;
    }

    public String getVciScore() 
    {
        return vciScore;
    }
    public void setTciExpectPayoutRatio(String tciExpectPayoutRatio) 
    {
        this.tciExpectPayoutRatio = tciExpectPayoutRatio;
    }

    public String getTciExpectPayoutRatio() 
    {
        return tciExpectPayoutRatio;
    }
    public void setVciExpectPayoutRatio(String vciExpectPayoutRatio) 
    {
        this.vciExpectPayoutRatio = vciExpectPayoutRatio;
    }

    public String getVciExpectPayoutRatio() 
    {
        return vciExpectPayoutRatio;
    }
    public void setNcdLevel(Integer ncdLevel) 
    {
        this.ncdLevel = ncdLevel;
    }

    public Integer getNcdLevel() 
    {
        return ncdLevel;
    }
    public void setClaimTimes(Integer claimTimes) 
    {
        this.claimTimes = claimTimes;
    }

    public Integer getClaimTimes() 
    {
        return claimTimes;
    }
    public void setTotalScore(String totalScore) 
    {
        this.totalScore = totalScore;
    }

    public String getTotalScore() 
    {
        return totalScore;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("frameNo", getFrameNo())
            .append("ownerId", getOwnerId())
            .append("ncd", getNcd())
            .append("trafficTransgressRate", getTrafficTransgressRate())
            .append("channelRate", getChannelRate())
            .append("underwritingRate", getUnderwritingRate())
            .append("priceRate", getPriceRate())
            .append("insureYears", getInsureYears())
            .append("tciScore", getTciScore())
            .append("vciScore", getVciScore())
            .append("tciExpectPayoutRatio", getTciExpectPayoutRatio())
            .append("vciExpectPayoutRatio", getVciExpectPayoutRatio())
            .append("ncdLevel", getNcdLevel())
            .append("claimTimes", getClaimTimes())
            .append("totalScore", getTotalScore())
            .toString();
    }
}
