package com.ruoyi.life.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.life.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.life.mapper.TbProductRateMapper;
import com.ruoyi.life.domain.TbProductRate;
import com.ruoyi.life.service.ITbProductRateService;

/**
 * 产品费率表Service业务层处理
 *
 * @author ruoyi
 * @date 2021-05-07
 */
@Service
public class TbProductRateServiceImpl extends BaseService implements ITbProductRateService
{
    @Autowired
    private TbProductRateMapper tbProductRateMapper;

    /**
     * 查询产品费率表
     *
     * @param id 产品费率表ID
     * @return 产品费率表
     */
    @Override
    public TbProductRate selectTbProductRateById(Long id)
    {
        return tbProductRateMapper.selectTbProductRateById(id);
    }

    /**
     * 查询产品费率表列表
     *
     * @param tbProductRate 产品费率表
     * @return 产品费率表
     */
    @Override
    public List<TbProductRate> selectTbProductRateList(TbProductRate tbProductRate)
    {
        return tbProductRateMapper.selectTbProductRateList(tbProductRate);
    }

    /**
     * 新增产品费率表
     *
     * @param tbProductRate 产品费率表
     * @return 结果
     */
    @Override
    public int insertTbProductRate(TbProductRate tbProductRate)
    {
        tbProductRate.setCreatetime(new Date());
        return tbProductRateMapper.insertTbProductRate(tbProductRate);
    }

    /**
     * 修改产品费率表
     *
     * @param tbProductRate 产品费率表
     * @return 结果
     */
    @Override
    public int updateTbProductRate(TbProductRate tbProductRate)
    {
        return tbProductRateMapper.updateTbProductRate(tbProductRate);
    }

    /**
     * 批量删除产品费率表
     *
     * @param ids 需要删除的产品费率表ID
     * @return 结果
     */
    @Override
    public int deleteTbProductRateByIds(Long[] ids)
    {
        return tbProductRateMapper.deleteTbProductRateByIds(ids);
    }

    /**
     * 删除产品费率表信息
     *
     * @param id 产品费率表ID
     * @return 结果
     */
    @Override
    public int deleteTbProductRateById(Long id)
    {
        return tbProductRateMapper.deleteTbProductRateById(id);
    }
}
