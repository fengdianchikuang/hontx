package com.ruoyi.income.service;

import java.util.List;

import com.ruoyi.income.domain.InformEntity;
import com.ruoyi.income.domain.TbIncome;
import com.ruoyi.income.domain.vo.IncomeVo;
import com.ruoyi.system.domain.TbQuery;

/**
 * 销售业绩Service接口
 *
 * @author ruoyi
 * @date 2021-04-19
 */
public interface ITbIncomeService
{
    /**
     * 查询销售业绩
     *
     * @param iId 销售业绩ID
     * @return 销售业绩
     */
    public TbIncome selectTbIncomeById(Long iId);

    /**
     * 查询销售业绩列表
     *
     * @param tbIncome 销售业绩
     * @return 销售业绩集合
     */
    public List<IncomeVo> selectTbIncomeList(IncomeVo tbIncome);

    /**
     * 新增销售业绩
     *
     * @param tbIncome 销售业绩
     * @return 结果
     */
    public int insertTbIncome(TbIncome tbIncome);

    /**
     * 修改销售业绩
     *
     * @param tbIncome 销售业绩
     * @return 结果
     */
    public int updateTbIncome(TbIncome tbIncome);

    /**
     * 批量删除销售业绩
     *
     * @param iIds 需要删除的销售业绩ID
     * @return 结果
     */
    public int deleteTbIncomeByIds(Long[] iIds);

    /**
     * 删除销售业绩信息
     *
     * @param iId 销售业绩ID
     * @return 结果
     */
    public int deleteTbIncomeById(Long iId);

    /**
     * 计算今天收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @return
     */
    Float queryTotalByToday(Long orgId, String todayMax, String todayMin);



    /**
     * 计算收益今日收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @return
     */
    Float queryLifeTotalByToday(Long orgId, String todayMax, String todayMin);

    /**
     * 计算今日商城收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @param s
     * @return
     */
    Float queryShopTotalByToday(Long orgId, String todayMax, String todayMin, String s);

    /**
     * 检查是否设置费率
     * @param tbQuery
     * @return
     */
    boolean isSetFee(TbQuery tbQuery);

    /**
     * 算收益
     * @param tbQuery
     */
    void calculationIncome(TbQuery tbQuery);

    /**
     * 算积分
     * @param tbQuery
     */
    void insertIntegral(TbQuery tbQuery);

    /**
     * 插入通知记录
     * @param informEntity
     * @return
     */
    int insertInform(InformEntity informEntity);
}
