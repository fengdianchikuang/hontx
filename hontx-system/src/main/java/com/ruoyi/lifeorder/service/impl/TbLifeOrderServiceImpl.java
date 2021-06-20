package com.ruoyi.lifeorder.service.impl;

import java.util.List;

import com.ruoyi.lifeorder.domain.vo.LifeOrderVo;
import com.ruoyi.lifeorder.service.ITbLifeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lifeorder.mapper.TbLifeOrderMapper;
import com.ruoyi.lifeorder.domain.TbLifeOrder;


/**
 * 寿险订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@Service
public class TbLifeOrderServiceImpl implements ITbLifeOrderService
{
    @Autowired
    private TbLifeOrderMapper tbLifeOrderMapper;

    /**
     * 查询寿险订单
     *
     * @param id 寿险订单ID
     * @return 寿险订单
     */
    @Override
    public TbLifeOrder selectTbLifeOrderById(Long id)
    {
        return tbLifeOrderMapper.selectTbLifeOrderById(id);
    }

    /**
     * 查询寿险订单列表
     *
     * @param tbLifeOrder 寿险订单
     * @return 寿险订单
     */
    @Override
    public List<LifeOrderVo> selectTbLifeOrderList(LifeOrderVo tbLifeOrder)
    {
        return tbLifeOrderMapper.selectTbLifeOrderList(tbLifeOrder);
    }

    /**
     * 新增寿险订单
     *
     * @param tbLifeOrder 寿险订单
     * @return 结果
     */
    @Override
    public int insertTbLifeOrder(TbLifeOrder tbLifeOrder)
    {
        return tbLifeOrderMapper.insertTbLifeOrder(tbLifeOrder);
    }

    /**
     * 修改寿险订单
     *
     * @param tbLifeOrder 寿险订单
     * @return 结果
     */
    @Override
    public int updateTbLifeOrder(TbLifeOrder tbLifeOrder)
    {
        return tbLifeOrderMapper.updateTbLifeOrder(tbLifeOrder);
    }

    /**
     * 批量删除寿险订单
     *
     * @param ids 需要删除的寿险订单ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeOrderByIds(Long[] ids)
    {
        return tbLifeOrderMapper.deleteTbLifeOrderByIds(ids);
    }

    /**
     * 删除寿险订单信息
     *
     * @param id 寿险订单ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeOrderById(Long id)
    {
        return tbLifeOrderMapper.deleteTbLifeOrderById(id);
    }

    /**
     * 根据订单Id查找
     * @param orderId
     * @return
     */
    @Override
    public LifeOrderVo selectByOrderId(String orderId) {
        return tbLifeOrderMapper.selectByOrderId(orderId);
    }
}
