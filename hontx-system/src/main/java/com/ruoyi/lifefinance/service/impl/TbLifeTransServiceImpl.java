package com.ruoyi.lifefinance.service.impl;

import java.util.List;

import com.ruoyi.lifefinance.domain.WithdrawalTrans;
import com.ruoyi.lifefinance.domain.req.LifeTransReq;
import com.ruoyi.lifefinance.domain.vo.LifeTransVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lifefinance.mapper.TbLifeTransMapper;
import com.ruoyi.lifefinance.domain.TbLifeTrans;
import com.ruoyi.lifefinance.service.ITbLifeTransService;

/**
 * 交易明细Service业务层处理
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@Service
public class TbLifeTransServiceImpl implements ITbLifeTransService
{
    @Autowired
    private TbLifeTransMapper tbLifeTransMapper;

    /**
     * 查询交易明细
     *
     * @param id 交易明细ID
     * @return 交易明细
     */
    @Override
    public TbLifeTrans selectTbLifeTransById(Long id)
    {
        return tbLifeTransMapper.selectTbLifeTransById(id);
    }

    /**
     * 查询交易明细列表
     *
     * @param tbLifeTrans 交易明细
     * @return 交易明细
     */
    @Override
    public List<LifeTransVo> selectTbLifeTransList(LifeTransReq tbLifeTrans)
    {
        return tbLifeTransMapper.selectTbLifeTransList(tbLifeTrans);
    }

    /**
     * 新增交易明细
     *
     * @param tbLifeTrans 交易明细
     * @return 结果
     */
    @Override
    public int insertTbLifeTrans(TbLifeTrans tbLifeTrans)
    {
        return tbLifeTransMapper.insertTbLifeTrans(tbLifeTrans);
    }

    /**
     * 修改交易明细
     *
     * @param tbLifeTrans 交易明细
     * @return 结果
     */
    @Override
    public int updateTbLifeTrans(TbLifeTrans tbLifeTrans)
    {
        return tbLifeTransMapper.updateTbLifeTrans(tbLifeTrans);
    }

    /**
     * 批量删除交易明细
     *
     * @param ids 需要删除的交易明细ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeTransByIds(Long[] ids)
    {
        return tbLifeTransMapper.deleteTbLifeTransByIds(ids);
    }

    /**
     * 删除交易明细信息
     *
     * @param id 交易明细ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeTransById(Long id)
    {
        return tbLifeTransMapper.deleteTbLifeTransById(id);
    }

    /**
     * 查询提现列表
     * @param transReq
     * @return
     */
    @Override
    public List<WithdrawalTrans> selectWithdrawalList(LifeTransReq transReq) {
        return tbLifeTransMapper.selectWithdrawalList(transReq);
    }
}
