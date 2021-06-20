package com.ruoyi.lifeorder.mapper;

import java.util.List;
import com.ruoyi.lifeorder.domain.TbLifeOrder;
import com.ruoyi.lifeorder.domain.vo.LifeOrderVo;

/**
 * 寿险订单Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public interface TbLifeOrderMapper
{
    /**
     * 查询寿险订单
     *
     * @param id 寿险订单ID
     * @return 寿险订单
     */
    public TbLifeOrder selectTbLifeOrderById(Long id);

    /**
     * 查询寿险订单列表
     *
     * @param tbLifeOrder 寿险订单
     * @return 寿险订单集合
     */
    public List<LifeOrderVo> selectTbLifeOrderList(LifeOrderVo tbLifeOrder);

    /**
     * 新增寿险订单
     *
     * @param tbLifeOrder 寿险订单
     * @return 结果
     */
    public int insertTbLifeOrder(TbLifeOrder tbLifeOrder);

    /**
     * 修改寿险订单
     *
     * @param tbLifeOrder 寿险订单
     * @return 结果
     */
    public int updateTbLifeOrder(TbLifeOrder tbLifeOrder);

    /**
     * 删除寿险订单
     *
     * @param id 寿险订单ID
     * @return 结果
     */
    public int deleteTbLifeOrderById(Long id);

    /**
     * 批量删除寿险订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbLifeOrderByIds(Long[] ids);

    /**
     * 根据订单Id查找
     * @param orderId
     * @return
     */
    LifeOrderVo selectByOrderId(String orderId);
}
