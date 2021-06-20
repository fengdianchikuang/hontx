package com.ruoyi.life.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.APIException;
import com.ruoyi.common.utils.cfy.DESUtil;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.life.BaseService;
import com.ruoyi.life.domain.LifeProductVo;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.ruoyi.life.mapper.TbLifeProductMapper;
import com.ruoyi.life.domain.TbLifeProduct;
import com.ruoyi.life.service.ITbLifeProductService;

/**
 * 寿险产品列表Service业务层处理
 *
 * @author ruoyi
 * @date 2021-05-06
 */
@Service
public class TbLifeProductServiceImpl extends BaseService implements ITbLifeProductService
{

    @Value("${cfy.url}")
    private String cfyUrlPrefix;

    @Autowired
    private TbLifeProductMapper tbLifeProductMapper;

    /**
     * 查询寿险产品列表
     *
     * @param id 寿险产品列表ID
     * @return 寿险产品列表
     */
    @Override
    public TbLifeProduct selectTbLifeProductById(Long id)
    {
        return tbLifeProductMapper.selectTbLifeProductById(id);
    }

    /**
     * 查询寿险产品列表列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 寿险产品列表
     */
    @Override
    public List<TbLifeProduct> selectTbLifeProductList(TbLifeProduct tbLifeProduct)
    {
        return tbLifeProductMapper.selectTbLifeProductList(tbLifeProduct);
    }

    /**
     * 新增寿险产品列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 结果
     */
    @Override
    public int insertTbLifeProduct(TbLifeProduct tbLifeProduct)
    {
        tbLifeProduct.setCreatetime(new Date());
        return tbLifeProductMapper.insertTbLifeProduct(tbLifeProduct);
    }

    /**
     * 修改寿险产品列表
     *
     * @param tbLifeProduct 寿险产品列表
     * @return 结果
     */
    @Override
    public int updateTbLifeProduct(TbLifeProduct tbLifeProduct)
    {
        return tbLifeProductMapper.updateTbLifeProduct(tbLifeProduct);
    }

    /**
     * 批量删除寿险产品列表
     *
     * @param ids 需要删除的寿险产品列表ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeProductByIds(Long[] ids)
    {
        return tbLifeProductMapper.deleteTbLifeProductByIds(ids);
    }

    /**
     * 删除寿险产品列表信息
     *
     * @param id 寿险产品列表ID
     * @return 结果
     */
    @Override
    public int deleteTbLifeProductById(Long id)
    {
        return tbLifeProductMapper.deleteTbLifeProductById(id);
    }

    /**
     * 获取所有的保险产品
     * @return
     */
    @Override
    public List<TbLifeProduct> queryList() {
        return tbLifeProductMapper.queryList();
    }

    /**
     * 获取所有的创富云产品列表
     * @param page
     * @param size
     * @return
     */
    @Override
    public TableDataInfo allProduct(String page, String size) {
        String url = cfyUrlPrefix + "/merchant/product/list";
        JSONObject params = getParams();
        JSONObject body = new JSONObject();
        body.put("page_size",size);
        body.put("page",page);
        String bodyResult = DESUtil.encryptData(body.toJSONString(), DES);
        params.put("body",bodyResult);

        String result = OkHttpUtils.postJSON(url, params.toJSONString(), null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String body1 = jsonObject.getString("body");
        String bodyRes = DESUtil.decryptData(body1, DES);
        JSONObject jsonObject1 = JSONObject.parseObject(bodyRes);
        if(jsonObject1.getInteger("code") != 0){
            throw new APIException(1,"请求数据失败,请重新再试");
        }
        JSONObject data1 = jsonObject1.getJSONObject("data");
        TableDataInfo data = new TableDataInfo();
        data.setTotal(data1.getInteger("total"));
        data.setCode(HttpStatus.SUCCESS);
        data.setMsg("获取数据成功");
        JSONArray data2 = data1.getJSONArray("data");
        List<LifeProductVo> lifeProductVos = JSONArray.parseArray(data2.toJSONString(), LifeProductVo.class);
        data.setRows(lifeProductVos);
        return data;
    }

    /**
     * 添加产品到弘泰祥
     * @param data
     * @return
     */
    @Override
    public int addProduct(JSONObject data) {
        TbLifeProduct product = new TbLifeProduct();
        product.setProductname(data.getString("product_name"));
        product.setProductno(data.getString("product_no"));
        product.setInsrnccpy(data.getString("product_supplier_name"));
        product.setProducttype(data.getString("producttype"));
        product.setCreatetime(new Date());
        product.setStatus("00");
        int result = tbLifeProductMapper.insertTbLifeProduct(product);
        return result;
    }
}
