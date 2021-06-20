package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbQueryInsrncMapper;
import com.ruoyi.system.domain.TbQueryInsrnc;
import com.ruoyi.system.service.ITbQueryInsrncService;

/**
 * insrncService业务层处理
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class TbQueryInsrncServiceImpl implements ITbQueryInsrncService
{
    @Autowired
    private TbQueryInsrncMapper tbQueryInsrncMapper;

    /**
     * 查询insrnc
     *
     * @param qId insrncID
     * @return insrnc
     */
    @Override
    public TbQueryInsrnc selectTbQueryInsrncById(Long qId)
    {
        return tbQueryInsrncMapper.selectTbQueryInsrncById(qId);
    }

    /**
     * 查询insrnc列表
     *
     * @param tbQueryInsrnc insrnc
     * @return insrnc
     */
    @Override
    public List<TbQueryInsrnc> selectTbQueryInsrncList(TbQueryInsrnc tbQueryInsrnc)
    {
        return tbQueryInsrncMapper.selectTbQueryInsrncList(tbQueryInsrnc);
    }

    /**
     * 新增insrnc
     *
     * @param tbQueryInsrnc insrnc
     * @return 结果
     */
    @Override
    public int insertTbQueryInsrnc(TbQueryInsrnc tbQueryInsrnc)
    {
        return tbQueryInsrncMapper.insertTbQueryInsrnc(tbQueryInsrnc);
    }

    /**
     * 修改insrnc
     *
     * @param tbQueryInsrnc insrnc
     * @return 结果
     */
    @Override
    public int updateTbQueryInsrnc(TbQueryInsrnc tbQueryInsrnc)
    {
        return tbQueryInsrncMapper.updateTbQueryInsrnc(tbQueryInsrnc);
    }

    /**
     * 批量删除insrnc
     *
     * @param qIds 需要删除的insrncID
     * @return 结果
     */
    @Override
    public int deleteTbQueryInsrncByIds(Long[] qIds)
    {
        return tbQueryInsrncMapper.deleteTbQueryInsrncByIds(qIds);
    }

    /**
     * 删除insrnc信息
     *
     * @param qId insrncID
     * @return 结果
     */
    @Override
    public int deleteTbQueryInsrncById(Long qId)
    {
        return tbQueryInsrncMapper.deleteTbQueryInsrncById(qId);
    }

    /**
     * 根据queryId删除
     * @param orderId
     * @return
     */
    @Override
    public int deleteByQueryId(String orderId) {
        return tbQueryInsrncMapper.deleteByQueryId(orderId);
    }
}
