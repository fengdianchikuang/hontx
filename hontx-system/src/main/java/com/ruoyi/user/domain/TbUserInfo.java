package com.ruoyi.user.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户扩展信息对象 tb_user_info
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Data
@ApiModel(value = "用户详情表")
public class TbUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @ApiModelProperty(value = "用户Id")
    private Integer uId;

    /** 性别 */
    @Excel(name = "性别")

    private Integer uSex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uBirthday;

    /** 电话 */
    @Excel(name = "电话")
    private String uTelephone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String uEmail;

    /** 住址 */
    @Excel(name = "住址")
    private String uAddress;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uCreateTime;

    /** 上级用户Id */
    @Excel(name = "上级用户Id")
    private String uParentId;

    /**
     * 推荐人手机号码
     */
    private String parentPhone;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String uRealName;

    /** 身份证 */
    @Excel(name = "身份证")
    private String uIdentity;

    /** 银行名 */
    @Excel(name = "银行名")
    private String uBankName;

    /** 支行 */
    private String uBankBranch;

    /** 卡号 */
    @Excel(name = "卡号")
    private String uAccno;

    /** 佣金可用资金 */
    @Excel(name = "佣金可用资金")
    private String uAvailBal;

    /** 总收入 */
    private String uTotalIncome;

    /** a层收入 */
    private String uIncomeA;

    /** b层收入 */
    private String uIncomeB;

    /** 用户实名状态:00未认证,01审核中,02认证未通过,03认证通过 */
    @Excel(name = "用户实名状态:00未认证,01审核中,02认证未通过,03认证通过")
    private String uRealNameStatus;

    /** a团队人数 */
    @Excel(name = "a团队人数")
    private String uAteamNum;

    /** b团队人数 */
    @Excel(name = "b团队人数")
    private String uBteamNum;

    /** 认证生效日期 */
    @Excel(name = "认证生效日期")
    private String uEffectDate;

    /** 设备类型 */
    private String uDeviceType;

    /** 设备Id */
    private String uDeviceId;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String uAreaCode;

    /** 机构id */
    @Excel(name = "机构id")
    private String uOrgId;

    /** 支付密码 */
    private String uPayPassword;

    /** 微信充值可用余额(弃用) */
    private String uAvailableMoney;

    /** 公众号openId */
    private String openid;

    /** 00:非门店,01:门店 */
    @Excel(name = "00:非门店,01:门店")
    private String type;

    /** 00:普通代理人, 01: 团队长 */
    @Excel(name = "00:普通代理人, 01: 团队长")
    private String userType;

    /** 团队Id */
    @Excel(name = "团队Id")
    private String tId;

    /** $column.columnComment */
    private String depthToTeam;

    /** $column.columnComment */
    private String lifeAvailBal;

    /** $column.columnComment */
    private String lifeTotalBal;


}
