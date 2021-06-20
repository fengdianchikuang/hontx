package com.ruoyi.user.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.user.domain.TbUserFile;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.req.UserAccountReq;
import com.ruoyi.user.domain.req.UserInfoVo;
import com.ruoyi.user.domain.req.WithdrawalListRequest;
import com.ruoyi.user.domain.req.WithdrawalRequest;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.domain.vo.UserTeamVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户扩展信息Service接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface ITbUserInfoService
{
    /**
     * 查询用户扩展信息
     *
     * @param uId 用户扩展信息ID
     * @return 用户扩展信息
     */
    public TbUserInfoVo selectTbUserInfoById(Integer uId);

    /**
     * 查询用户扩展信息列表
     *
     * @param tbUserInfo 用户扩展信息
     * @return 用户扩展信息集合
     */
    public List<TbUserInfo> selectTbUserInfoList(TbUserInfo tbUserInfo);

    /**
     * 新增用户扩展信息
     *
     * @param tbUserInfo 用户扩展信息
     * @return 结果
     */
    public int insertTbUserInfo(TbUserInfo tbUserInfo);

    /**
     * 修改用户扩展信息
     *
     * @param tbUserInfo 用户扩展信息
     * @return 结果
     */
    public int updateTbUserInfo(TbUserInfo userInfoVo);

    /**
     * 批量删除用户扩展信息
     *
     * @param uIds 需要删除的用户扩展信息ID
     * @return 结果
     */
    public int deleteTbUserInfoByIds(Integer[] uIds);

    /**
     * 删除用户扩展信息信息
     *
     * @param uId 用户扩展信息ID
     * @return 结果
     */
    public int deleteTbUserInfoById(Integer uId);

    int insertUserFile(TbUserFile tbUserFile);

    /**
     * 根据文件名获取文件对象
     * @param fileName
     * @return
     */
    TbUserFile findByFileName(String fileName);

    TbUserFile findByUid(Long uId);

    /**
     * 删除用户文件根据Id
     * @param getuId
     * @return
     */
    int delUserFileById(Long getuId);

    /**
     * 查询机构今天的总数
     * @param orgId
     * @param today
     * @param yesterday
     * @return
     */
    int queryListByTodayNum(String orgId, String today, String yesterday);

    /**
     * 查询用户(所有属性)
     * @param uId
     * @return
     */
    TbUserInfo selectUserInfoById(Integer uId);

    /**
     * 修改推荐人的上下级关系
     * @param tbUserInfo
     * @return
     */
    int updateUserParent(TbUserInfo tbUserInfo);

    /**
     * 修改用户账号资料
     * @param request
     * @return
     */
    int updateBank(UserAccountReq request);

    /**
     * 新增实名用户
     * @param userInfo
     * @param request
     * @return
     */
    int createUser(UserInfoVo userInfo, HttpServletRequest request);

    /**
     * 根据车主Id获取保险团队信息
     * @param ownerId
     * @return
     */
    UserTeamVo queryTeamInfoByUserId(Long ownerId);

    /**
     * 申请提现请求
     * @param request
     * @return
     */
    int withdrawalRequest(WithdrawalRequest request);

    /**
     * 查看提现列表
     * @param request
     * @return
     */
    List<TbTrans> withdrawalList(WithdrawalListRequest request);

    /**
     * 上传用户证件照
     * @param file
     * @param type
     * @param s
     * @return
     */
    Map<String, Object> uploadRealUserFile(MultipartFile file, String type, String s) throws IOException;

    /**
     * 根据车主Id查找用户信息及图片
     * @param
     * @return
     */
    TbUserInfoVo findUserDetailByOwnerId(String s);

    /**
     * 根据电话号码获取用户实列
     * @param phone
     * @return
     */
    TbUserInfo findByPhone(String phone);

    int updateUserInfo(UserInfoVo oldUser);
}
