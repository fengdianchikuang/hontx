package com.ruoyi.user.mapper;

import com.ruoyi.user.domain.req.UserReq;
import com.ruoyi.user.domain.vo.UserTeamVo;

public interface TbUserMapper {
    /**
     * 新增用户主表
     * @param userReq
     * @return
     */
    int insertTbUser(UserReq userReq);

    /**
     * 更新用户主表
     * @param userReq
     * @return
     */
    int updateTbUser(UserReq userReq);

    /**
     * 插入用户角色表
     * @param userId
     * @param i
     */
    void insertUserRole(Integer userId, int i);

    /**
     * 根据车主Id查询保险团队信息
     * @param ownerId
     * @return
     */
    UserTeamVo queryTeamInfoByUserId(Long ownerId);
}
