package com.ruoyi.policy.service;

import java.util.List;

import com.ruoyi.policy.domain.Policy;
import com.ruoyi.policy.mapper.PolicyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 投保信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-06-14
 */
@Service
public class PolicyService
{
    @Autowired
    private PolicyMapper policyMapper;

    /**
     * 查询投保信息
     *
     * @param id 投保信息ID
     * @return 投保信息
     */

    public Policy selectPolicyById(Long id)
    {
        return policyMapper.selectPolicyById(id);
    }

    /**
     * 查询投保信息列表
     *
     * @param policy 投保信息
     * @return 投保信息
     */

    public List<Policy> selectPolicyList(Policy policy)
    {
        return policyMapper.selectPolicyList(policy);
    }

    /**
     * 新增投保信息
     *
     * @param policy 投保信息
     * @return 结果
     */

    public int insertPolicy(Policy policy)
    {
        return policyMapper.insertPolicy(policy);
    }

    /**
     * 修改投保信息
     *
     * @param policy 投保信息
     * @return 结果
     */

    public int updatePolicy(Policy policy)
    {
        return policyMapper.updatePolicy(policy);
    }

    /**
     * 批量删除投保信息
     *
     * @param ids 需要删除的投保信息ID
     * @return 结果
     */

    public int deletePolicyByIds(Long[] ids)
    {
        return policyMapper.deletePolicyByIds(ids);
    }

    /**
     * 删除投保信息信息
     *
     * @param id 投保信息ID
     * @return 结果
     */

    public int deletePolicyById(Long id)
    {
        return policyMapper.deletePolicyById(id);
    }

    /**
     * 根据车架查找投保信息
     * @param rackNo
     * @return
     */
    public Policy findByFrameNo(String rackNo) {
        return policyMapper.findByFrameNo(rackNo);
    }
}
