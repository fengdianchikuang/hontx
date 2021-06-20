package com.ruoyi.user.mapper;


import com.ruoyi.user.domain.TbUserFile;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.domain.vo.UserTeamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户扩展信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface TbUserInfoMapper
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
    public int updateTbUserInfo(TbUserInfo tbUserInfo);

    /**
     * 删除用户扩展信息
     *
     * @param uId 用户扩展信息ID
     * @return 结果
     */
    public int deleteTbUserInfoById(Integer uId);

    /**
     * 批量删除用户扩展信息
     *
     * @param uIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbUserInfoByIds(Integer[] uIds);

    /**
     * 插入用户图片
     * @param tbUserFile
     * @return
     */
    int insertUserFile(TbUserFile tbUserFile);

    /**
     * 根据文件名获取文件对象
     * @param fileName
     * @return
     */
    TbUserFile findByFileName(String fileName);

    /**
     * 根据文件Id获取文件对象
     * @param uId
     * @return
     */
    TbUserFile findByUid(Long uId);

    /**
     * 根据id删除用户文件
     * @param id
     * @return
     */
    int delUserFileById(Long id);

    /**
     * 查询机构今天的新增代理数
     * @param orgId
     * @param today
     * @param yesterday
     * @return
     */
    int queryListByTodayNum(@Param("orgId") String orgId,@Param("today") String today, @Param("yesterday") String yesterday);

    /**
     * 查询用户信息(所有属性)根据Id
     * @param uId
     * @return
     */
    TbUserInfo selectUserInfoById(Integer uId);

    /**
     * 根据手机号查找用户
     * @param phone
     * @return
     */
    TbUserInfo selectUserByPhone(String phone);

    /**
     * 根据车主Id查找用户详情
     * @param ownerId
     * @return
     */
    TbUserInfo findByOwnerId(Long ownerId);

    /**
     * 根据手机号码查询用户是否已注册
     * @param uTelephone
     * @return
     */
    TbUserInfo findByPhone(String uTelephone);

    /**
     * 根据车主id查找保险团队信息
     * @param ownerId
     * @return
     */
    UserTeamVo queryTeamInfoByUserId(Long ownerId);

    /**
     * 根据车主Id查找用户文件
     * @param ownerId
     * @return
     */
    List<TbUserFile> findUserFileByUserId(String ownerId);

    /**
     * 更新用户文件
     * @param tbUserFile
     * @return
     */
    int updateUserFile(TbUserFile tbUserFile);

    /**
     * 根据车主Id查找用户信息及图片
     * @param ownerId
     * @return
     */
    TbUserInfoVo findUserDetailByOwnerId(String ownerId);


}
