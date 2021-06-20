package com.ruoyi.trans.mapper;

import java.util.List;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.domain.vo.TransDetailVo;
import com.ruoyi.trans.domain.vo.TransVo;
import org.apache.ibatis.annotations.Param;

/**
 * 提交列表Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-16
 */
public interface TbTransMapper
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
     * 删除提交列表
     *
     * @param tId 提交列表ID
     * @return 结果
     */
    public int deleteTbTransById(Long tId);

    /**
     * 批量删除提交列表
     *
     * @param tIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbTransByIds(Long[] tIds);

    /**
     * 获取交易明细信息
     * @param tbTrans
     * @return
     */
    List<TransDetailVo> selectTbTransInfoList(TransDetailVo tbTrans);

    /**
     * 根据用户Id 查找status 00 01 提现列表
     * @param userId
     * @return
     */
    List<TbTrans> findByUserIdAndStatus(String userId);

    /**
     * 我的收益列表
     * @param uId
     * @param type
     * @return
     */
    List<TransVo> findMyProfit(@Param("userId") Integer userId,@Param("type") String type);

    /**
     * 查询用户当月收益
     * @param firstDay
     * @param lastDay
     * @param userId
     * @return
     */
    List<String> selectAmount(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay, @Param("userId") Integer userId);

    /**
     * 查询单月用户完成情况
     * @param first
     * @param now
     * @return
     */
    List<TbTrans> selectTrans(@Param("first") String first, @Param("now") String now,@Param("userId") String userId);
}
