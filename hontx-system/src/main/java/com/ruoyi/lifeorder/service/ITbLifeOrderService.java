package com.ruoyi.lifeorder.service;

import com.ruoyi.lifeorder.domain.TbLifeOrder;
import com.ruoyi.lifeorder.domain.vo.LifeOrderVo;

import java.util.List;

public interface ITbLifeOrderService {
    TbLifeOrder selectTbLifeOrderById(Long id);
    List<LifeOrderVo> selectTbLifeOrderList(LifeOrderVo tbLifeOrder);
    int insertTbLifeOrder(TbLifeOrder tbLifeOrder);
    int updateTbLifeOrder(TbLifeOrder tbLifeOrder);
    int deleteTbLifeOrderByIds(Long[] ids);
    int deleteTbLifeOrderById(Long id);
    LifeOrderVo selectByOrderId(String orderId);
}
