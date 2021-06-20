package com.ruoyi.life.mapper;

import java.util.List;
import com.ruoyi.life.domain.TbProductRate;

/**
 * 产品费率表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-07
 */
public interface TbProductRateMapper 
{
    /**
     * 查询产品费率表
     * 
     * @param id 产品费率表ID
     * @return 产品费率表
     */
    public TbProductRate selectTbProductRateById(Long id);

    /**
     * 查询产品费率表列表
     * 
     * @param tbProductRate 产品费率表
     * @return 产品费率表集合
     */
    public List<TbProductRate> selectTbProductRateList(TbProductRate tbProductRate);

    /**
     * 新增产品费率表
     * 
     * @param tbProductRate 产品费率表
     * @return 结果
     */
    public int insertTbProductRate(TbProductRate tbProductRate);

    /**
     * 修改产品费率表
     * 
     * @param tbProductRate 产品费率表
     * @return 结果
     */
    public int updateTbProductRate(TbProductRate tbProductRate);

    /**
     * 删除产品费率表
     * 
     * @param id 产品费率表ID
     * @return 结果
     */
    public int deleteTbProductRateById(Long id);

    /**
     * 批量删除产品费率表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbProductRateByIds(Long[] ids);
}
