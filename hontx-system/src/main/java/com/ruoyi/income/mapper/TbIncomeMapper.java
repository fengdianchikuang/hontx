package com.ruoyi.income.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.income.domain.HontxTransEntity;
import com.ruoyi.income.domain.InformEntity;
import com.ruoyi.income.domain.IntegralRateEntity;
import com.ruoyi.income.domain.TbIncome;
import com.ruoyi.income.domain.vo.IncomeVo;
import org.apache.ibatis.annotations.Param;

/**
 * 销售业绩Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-19
 */
public interface TbIncomeMapper
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
     * 删除销售业绩
     *
     * @param iId 销售业绩ID
     * @return 结果
     */
    public int deleteTbIncomeById(Long iId);

    /**
     * 批量删除销售业绩
     *
     * @param iIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbIncomeByIds(Long[] iIds);

    /**
     * 计算今日总收益
     * @param orgId
     * @param todayMax
     * @param todayMin
     * @return
     */
    Float queryTotalByToday(@Param("orgId") String orgId, @Param("todayMax") String todayMax, @Param("todayMin") String todayMin);



    Float queryLifeTotalByToday(@Param("orgId") Long orgId, @Param("todayMax") String todayMax,@Param("todayMin") String todayMin);

    Float queryShopTotalByToday(@Param("orgId") Long orgId, @Param("todayMax") String todayMax, @Param("todayMin") String todayMin,@Param("type") String type);

    /**
     * 查找积分费率
     * @param parameter
     * @return
     */
    IntegralRateEntity findIntegralRate(Map<String, Object> parameter);

    /**
     * 插入hontx流水
     * @param ht
     */
    void insertHontxTrans(HontxTransEntity ht);

    /**
     * 插入通知记录
     * @param informEntity
     * @return
     */
    int insertInform(InformEntity informEntity);
}
