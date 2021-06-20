package com.ruoyi.life.mapper;

import java.util.List;
import com.ruoyi.life.domain.TbLifeProduct;

/**
 * 寿险产品列表Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-06
 */
public interface TbLifeProductMapper
{
    /**
     * 查询寿险产品列表
     *
     * @param id 寿险产品列表ID
     * @return 寿险产品列表
     */
    public TbLifeProduct selectTbLifeProductById(Long id);

    /**
     * 查询寿险产品列表列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 寿险产品列表集合
     */
    public List<TbLifeProduct> selectTbLifeProductList(TbLifeProduct tbLifeProduct);

    /**
     * 新增寿险产品列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 结果
     */
    public int insertTbLifeProduct(TbLifeProduct tbLifeProduct);

    /**
     * 修改寿险产品列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 结果
     */
    public int updateTbLifeProduct(TbLifeProduct tbLifeProduct);

    /**
     * 删除寿险产品列表
     *
     * @param id 寿险产品列表ID
     * @return 结果
     */
    public int deleteTbLifeProductById(Long id);

    /**
     * 批量删除寿险产品列表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbLifeProductByIds(Long[] ids);

    /**
     * 获取所有的保险产品
     * @return
     */
    List<TbLifeProduct> queryList();

}
