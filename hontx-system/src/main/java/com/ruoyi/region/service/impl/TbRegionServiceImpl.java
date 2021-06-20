package com.ruoyi.region.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.region.mapper.TbRegionMapper;
import com.ruoyi.region.domain.TbRegion;
import com.ruoyi.region.service.ITbRegionService;

/**
 * 地区码Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-13
 */
@Service
public class TbRegionServiceImpl implements ITbRegionService 
{
    @Autowired
    private TbRegionMapper tbRegionMapper;

    /**
     * 查询地区码
     * 
     * @param code 地区码ID
     * @return 地区码
     */
    @Override
    public TbRegion selectTbRegionById(Long code)
    {
        return tbRegionMapper.selectTbRegionById(code);
    }

    /**
     * 查询地区码列表
     * 
     * @param tbRegion 地区码
     * @return 地区码
     */
    @Override
    public List<TbRegion> selectTbRegionList(TbRegion tbRegion)
    {
        return tbRegionMapper.selectTbRegionList(tbRegion);
    }

    /**
     * 新增地区码
     * 
     * @param tbRegion 地区码
     * @return 结果
     */
    @Override
    public int insertTbRegion(TbRegion tbRegion)
    {
        return tbRegionMapper.insertTbRegion(tbRegion);
    }

    /**
     * 修改地区码
     * 
     * @param tbRegion 地区码
     * @return 结果
     */
    @Override
    public int updateTbRegion(TbRegion tbRegion)
    {
        return tbRegionMapper.updateTbRegion(tbRegion);
    }

    /**
     * 批量删除地区码
     * 
     * @param codes 需要删除的地区码ID
     * @return 结果
     */
    @Override
    public int deleteTbRegionByIds(Long[] codes)
    {
        return tbRegionMapper.deleteTbRegionByIds(codes);
    }

    /**
     * 删除地区码信息
     * 
     * @param code 地区码ID
     * @return 结果
     */
    @Override
    public int deleteTbRegionById(Long code)
    {
        return tbRegionMapper.deleteTbRegionById(code);
    }
}
