package com.ruoyi.region.service;

import java.util.List;
import com.ruoyi.region.domain.TbRegion;

/**
 * 地区码Service接口
 * 
 * @author ruoyi
 * @date 2021-04-13
 */
public interface ITbRegionService 
{
    /**
     * 查询地区码
     * 
     * @param code 地区码ID
     * @return 地区码
     */
    public TbRegion selectTbRegionById(Long code);

    /**
     * 查询地区码列表
     * 
     * @param tbRegion 地区码
     * @return 地区码集合
     */
    public List<TbRegion> selectTbRegionList(TbRegion tbRegion);

    /**
     * 新增地区码
     * 
     * @param tbRegion 地区码
     * @return 结果
     */
    public int insertTbRegion(TbRegion tbRegion);

    /**
     * 修改地区码
     * 
     * @param tbRegion 地区码
     * @return 结果
     */
    public int updateTbRegion(TbRegion tbRegion);

    /**
     * 批量删除地区码
     * 
     * @param codes 需要删除的地区码ID
     * @return 结果
     */
    public int deleteTbRegionByIds(Long[] codes);

    /**
     * 删除地区码信息
     * 
     * @param code 地区码ID
     * @return 结果
     */
    public int deleteTbRegionById(Long code);
}
