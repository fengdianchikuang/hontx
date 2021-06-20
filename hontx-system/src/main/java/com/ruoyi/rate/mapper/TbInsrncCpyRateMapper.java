package com.ruoyi.rate.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.rate.domain.TbInsrncCpyRate;

/**
 * 费率设置Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-12
 */
public interface TbInsrncCpyRateMapper
{
    /**
     * 查询费率设置
     *
     * @param iId 费率设置ID
     * @return 费率设置
     */
    public TbInsrncCpyRate selectTbInsrncCpyRateById(Long iId);

    /**
     * 查询费率设置列表
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 费率设置集合
     */
    public List<TbInsrncCpyRate> selectTbInsrncCpyRateList(TbInsrncCpyRate tbInsrncCpyRate);

    /**
     * 新增费率设置
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 结果
     */
    public int insertTbInsrncCpyRate(TbInsrncCpyRate tbInsrncCpyRate);

    /**
     * 修改费率设置
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 结果
     */
    public int updateTbInsrncCpyRate(TbInsrncCpyRate tbInsrncCpyRate);

    /**
     * 删除费率设置
     *
     * @param iId 费率设置ID
     * @return 结果
     */
    public int deleteTbInsrncCpyRateById(Long iId);

    /**
     * 批量删除费率设置
     *
     * @param iIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbInsrncCpyRateByIds(Long[] iIds);

    /**
     * 查询费率 map
     * @param rateParam
     * @return
     */
    List<TbInsrncCpyRate> selectTbInsrncCpyRateListByMap(Map<String, Object> rateParam);

    /**
     * 根据生效日期 保险公司Id 机构号 删除
     * @param delCpyRate
     * @return
     */
    int deleteByEffectDateAndChannel(TbInsrncCpyRate delCpyRate);
}
