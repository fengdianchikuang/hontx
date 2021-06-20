package com.ruoyi.trans.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.domain.req.ProfitRequest;
import com.ruoyi.trans.domain.vo.CompletionVo;
import com.ruoyi.trans.domain.vo.TransDetailVo;
import com.ruoyi.trans.domain.vo.TransVo;

/**
 * 提交列表Service接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
public interface ITbTransService
{
    /**
     * 查询提交列表
     *
     * @param tId 提交列表ID
     * @return 提交列表
     */
    public TbTrans selectTbTransById(Long tId);

    /**
     * 查询提交列表列表
     *
     * @param tbTrans 提交列表
     * @return 提交列表集合
     */
    public List<TransVo> selectTbTransList(TransVo tbTrans);

    /**
     * 新增提交列表
     *
     * @param tbTrans 提交列表
     * @return 结果
     */
    public int insertTbTrans(TbTrans tbTrans);

    /**
     * 修改提交列表
     *
     * @param tbTrans 提交列表
     * @return 结果
     */
    public int updateTbTrans(TbTrans tbTrans);

    /**
     * 批量删除提交列表
     *
     * @param tIds 需要删除的提交列表ID
     * @return 结果
     */
    public int deleteTbTransByIds(Long[] tIds);

    /**
     * 删除提交列表信息
     *
     * @param tId 提交列表ID
     * @return 结果
     */
    public int deleteTbTransById(Long tId);

    /**
     * 提现申请
     * @param id
     * @return
     */
    int withdrawal(Long id);

    /**
     * 获取交易明细
     * @param tbTrans
     * @return
     */
    List<TransDetailVo> selectTbTransInfoList(TransDetailVo tbTrans);

    /**
     * 获取用户收益列表
     * @param request
     * @return
     */
    List<TransVo> profit(ProfitRequest request);

    /**
     * 当月保险收益与可用余额
     * @param request
     * @return
     */
    Map<String, Object> balance(ProfitRequest request);

    /**
     * 计算团队完成商业险金额及个数
     * @param userId
     */
    CompletionVo CompletionRate(Integer userId);
}
