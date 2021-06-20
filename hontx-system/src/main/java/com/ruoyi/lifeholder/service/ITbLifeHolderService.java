package com.ruoyi.lifeholder.service;

import com.ruoyi.lifeholder.domain.TbLifeHolder;
import com.ruoyi.lifeholder.domain.vo.LifeHolderVo;

import java.util.List;

public interface ITbLifeHolderService {
    TbLifeHolder selectTbLifeHolderById(Long id);
    List<LifeHolderVo> selectTbLifeHolderList(LifeHolderVo tbLifeHolder);
    int insertTbLifeHolder(TbLifeHolder tbLifeHolder);
    int updateTbLifeHolder(TbLifeHolder tbLifeHolder);
    int deleteTbLifeHolderByIds(Long[] ids);
    int deleteTbLifeHolderById(Long id);
    TbLifeHolder selectTbLifeHolderByHolderIdNoAndAgentId(String holderidno, String agentid);
}
