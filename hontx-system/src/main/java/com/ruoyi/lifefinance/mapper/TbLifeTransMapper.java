package com.ruoyi.lifefinance.mapper;

import java.util.List;
import com.ruoyi.lifefinance.domain.TbLifeTrans;
import com.ruoyi.lifefinance.domain.WithdrawalTrans;
import com.ruoyi.lifefinance.domain.req.LifeTransReq;
import com.ruoyi.lifefinance.domain.vo.LifeTransVo;

/**
 * 交易明细Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public interface TbLifeTransMapper
{
    /**
     * 查询交易明细
     *
     * @param id 交易明细ID
     * @return 交易明细
     */
    public TbLifeTrans selectTbLifeTransById(Long id);

    /**
     * 查询交易明细列表
     *
     * @param tbLifeTrans 交易明细
     * @return 交易明细集合
     */
    public List<LifeTransVo> selectTbLifeTransList(LifeTransReq tbLifeTrans);

    /**
     * 新增交易明细
     *
     * @param tbLifeTrans 交易明细
     * @return 结果
     */
    public int insertTbLifeTrans(TbLifeTrans tbLifeTrans);

    /**
     * 修改交易明细
     *
     * @param tbLifeTrans 交易明细
     * @return 结果
     */
    public int updateTbLifeTrans(TbLifeTrans tbLifeTrans);

    /**
     * 删除交易明细
     *
     * @param id 交易明细ID
     * @return 结果
     */
    public int deleteTbLifeTransById(Long id);

    /**
     * 批量删除交易明细
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbLifeTransByIds(Long[] ids);

    /**
     * 查询提现列表
     * @param transReq
     * @return
     */
    List<WithdrawalTrans> selectWithdrawalList(LifeTransReq transReq);
}
