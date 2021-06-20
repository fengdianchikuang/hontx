package com.ruoyi.lifeholder.service.impl;

import java.util.List;

import com.ruoyi.lifeholder.domain.vo.LifeHolderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lifeholder.mapper.TbLifeHolderMapper;
import com.ruoyi.lifeholder.domain.TbLifeHolder;
import com.ruoyi.lifeholder.service.ITbLifeHolderService;

/**
 * 寿险客户Service业务层处理
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@Service
public class TbLifeHolderServiceImpl implements ITbLifeHolderService
{
    @Autowired
    private TbLifeHolderMapper tbLifeHolderMapper;

    /**
     * 查询寿险客户
     *
     * @param id 寿险客户ID
     * @return 寿险客户
     */
    @Override
    public TbLifeHolder selectTbLifeHolderById(Long id)
    {
        return tbLifeHolderMapper.selectTbLifeHolderById(id);
    }

    /**
     * 查询寿险客户列表
     *
     * @param tbLifeHolder 寿险客户
     * @return 寿险客户
     */
    @Override
    public List<LifeHolderVo> selectTbLifeHolderList(LifeHolderVo tbLifeHolder)
    {
        return tbLifeHolderMapper.selectTbLifeHolderList(tbLifeHolder);
    }

    /**
     * 新增寿险客户
     *
     * @param tbLifeHolder 寿险客户
     * @return 结果
     */
    @Override
    public int insertTbLifeHolder(TbLifeHolder tbLifeHolder)
    {
        return tbLifeHolderMapper.insertTbLifeHolder(tbLifeHolder);
    }

    /**
     * 修改寿险客户
     *
     * @param tbLifeHolder 寿险客户
     * @return 结果
     */
    @Override
    public int updateTbLifeHolder(TbLifeHolder tbLifeHolder)
    {
        return tbLifeHolderMapper.updateTbLifeHolder(tbLifeHolder);
    }

    /**
     * 批量删除寿险客户
     *
     * @param ids 需要删除的寿险客户ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeHolderByIds(Long[] ids)
    {
        return tbLifeHolderMapper.deleteTbLifeHolderByIds(ids);
    }

    /**
     * 删除寿险客户信息
     *
     * @param id 寿险客户ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeHolderById(Long id)
    {
        return tbLifeHolderMapper.deleteTbLifeHolderById(id);
    }
    /**
     * 根据证件号 和 代理人Id，查找投保人
     * @param holderidno
     * @return
     */
    @Override
    public TbLifeHolder selectTbLifeHolderByHolderIdNoAndAgentId(String holderidno, String agentid) {
        return tbLifeHolderMapper.selectTbLifeHolderByHolderIdNoAndAgentId(holderidno,agentid);
    }

}
