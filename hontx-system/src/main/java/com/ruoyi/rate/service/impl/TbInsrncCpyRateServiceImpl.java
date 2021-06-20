package com.ruoyi.rate.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.rate.mapper.TbInsrncCpyRateMapper;
import com.ruoyi.rate.domain.TbInsrncCpyRate;
import com.ruoyi.rate.service.ITbInsrncCpyRateService;

/**
 * 费率设置Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@Service
public class TbInsrncCpyRateServiceImpl implements ITbInsrncCpyRateService
{
    @Autowired
    private TbInsrncCpyRateMapper tbInsrncCpyRateMapper;

    /**
     * 查询费率设置
     *
     * @param iId 费率设置ID
     * @return 费率设置
     */
    @Override
    public TbInsrncCpyRate selectTbInsrncCpyRateById(Long iId)
    {
        return tbInsrncCpyRateMapper.selectTbInsrncCpyRateById(iId);
    }

    /**
     * 查询费率设置列表
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 费率设置
     */
    @Override
    public List<TbInsrncCpyRate> selectTbInsrncCpyRateList(TbInsrncCpyRate tbInsrncCpyRate)
    {
        return tbInsrncCpyRateMapper.selectTbInsrncCpyRateList(tbInsrncCpyRate);
    }

    /**
     * 新增费率设置
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 结果
     */
    @Override
    public int insertTbInsrncCpyRate(List<TbInsrncCpyRate> tbInsrncCpyRates)
    {
        int count = tbInsrncCpyRates.size();
        for (TbInsrncCpyRate tbInsrncCpyRate : tbInsrncCpyRates) {
            int i = tbInsrncCpyRateMapper.insertTbInsrncCpyRate(tbInsrncCpyRate);
            if(i == 1){
                count --;
            }
        }
        if(count == 0){
            return 1;
        }else {
            return 0;
        }

    }

    /**
     * 修改费率设置
     *
     * @param tbInsrncCpyRate 费率设置
     * @return 结果
     */
    @Override
    public int updateTbInsrncCpyRate(TbInsrncCpyRate tbInsrncCpyRate)
    {
        return tbInsrncCpyRateMapper.updateTbInsrncCpyRate(tbInsrncCpyRate);
    }

    /**
     * 批量删除费率设置
     *
     * @param iIds 需要删除的费率设置ID
     * @return 结果
     */
    @Override
    public int deleteTbInsrncCpyRateByIds(Long[] iIds)
    {
        return tbInsrncCpyRateMapper.deleteTbInsrncCpyRateByIds(iIds);
    }

    /**
     * 删除费率设置信息
     *
     * @param iId 费率设置ID
     * @return 结果
     */
    @Override
    public int deleteTbInsrncCpyRateById(Long iId)
    {
        return tbInsrncCpyRateMapper.deleteTbInsrncCpyRateById(iId);
    }

    @Override
    public int insertRate(TbInsrncCpyRate insrncCpyRate) {
        return tbInsrncCpyRateMapper.insertTbInsrncCpyRate(insrncCpyRate);
    }
    /**
     * 根据生效日期 保险公司Id 机构号 删除
     * @param delCpyRate
     * @return
     */
    @Override
    public int deleteByEffectDateAndChannel(TbInsrncCpyRate delCpyRate) {
        return tbInsrncCpyRateMapper.deleteByEffectDateAndChannel(delCpyRate);
    }
}
