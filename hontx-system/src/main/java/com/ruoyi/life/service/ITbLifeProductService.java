package com.ruoyi.life.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.life.domain.TbLifeProduct;

/**
 * 寿险产品列表Service接口
 *
 * @author ruoyi
 * @date 2021-05-06
 */
public interface ITbLifeProductService
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
     * 批量删除寿险产品列表
     *
     * @param ids 需要删除的寿险产品列表ID
     * @return 结果
     */
    public int deleteTbLifeProductByIds(Long[] ids);

    /**
     * 删除寿险产品列表信息
     *
     * @param id 寿险产品列表ID
     * @return 结果
     */
    public int deleteTbLifeProductById(Long id);

    /**
     * 获取所有的保险产品
     * @return
     */
    List<TbLifeProduct> queryList();

    /**
     * 获取创富云产品列表
     * @param page
     * @param size
     * @return
     */
    TableDataInfo allProduct(String page, String size);

    /**
     * 将创富云添加到弘泰祥
     * @param data
     * @return
     */
    int addProduct(JSONObject data);
}
