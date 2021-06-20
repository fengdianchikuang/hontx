package com.ruoyi.policy.mapper;


import com.ruoyi.policy.domain.Policy;

import java.util.List;

/**
 * 投保信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-06-14
 */
public interface PolicyMapper
{
    /**
     * 查询投保信息
     *
     * @param id 投保信息ID
     * @return 投保信息
     */
    public Policy selectPolicyById(Long id);

    /**
     * 查询投保信息列表
     *
     * @param policy 投保信息
     * @return 投保信息集合
     */
    public List<Policy> selectPolicyList(Policy policy);

    /**
     * 新增投保信息
     *
     * @param policy 投保信息
     * @return 结果
     */
    public int insertPolicy(Policy policy);

    /**
     * 修改投保信息
     *
     * @param policy 投保信息
     * @return 结果
     */
    public int updatePolicy(Policy policy);

    /**
     * 删除投保信息
     *
     * @param id 投保信息ID
     * @return 结果
     */
    public int deletePolicyById(Long id);

    /**
     * 批量删除投保信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyByIds(Long[] ids);

    /**
     * 根据车架查找投保信息
     * @param frameNo
     * @return
     */
    Policy findByFrameNo(String frameNo);
}
