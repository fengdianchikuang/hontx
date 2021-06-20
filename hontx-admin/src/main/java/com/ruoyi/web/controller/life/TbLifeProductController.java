package com.ruoyi.web.controller.life;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.cfy.DESUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.life.domain.TbLifeProduct;
import com.ruoyi.life.service.ITbLifeProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 寿险产品列表Controller
 *
 * @author ruoyi
 * @date 2021-05-06
 */
@RestController
@RequestMapping("/life/product")
//@Api(value = "寿险产品列表",tags = "寿险产品")
public class TbLifeProductController extends BaseController
{
    @Autowired
    private ITbLifeProductService tbLifeProductService;

    /**
     * 查询创富云产品列表列表
     */
//    @ApiOperation(value = "获取创富云产品列表")
    @PreAuthorize("@ss.hasPermi('life:cfy:list')")
    @GetMapping("/productList/{pagePage}/{pageSize}")
    public TableDataInfo getAllProduct(@PathVariable String pagePage,@PathVariable String pageSize){
        TableDataInfo tableDataInfo = tbLifeProductService.allProduct(pagePage, pageSize);
        return tableDataInfo;
    }

//    @ApiOperation(value = "创富云添加加到弘泰祥")
    @PreAuthorize("@ss.hasPermi('life:cfy:list')")
    @PostMapping("/cfy")
    public AjaxResult addProduct(@RequestBody JSONObject data){
        int row = tbLifeProductService.addProduct(data);
        return toAjax(row);
    }

    /**
     * 获取所有的保险产品
     * @return
     */
//    @ApiOperation(value = "弘泰祥产品列表")
    @PreAuthorize("@ss.hasPermi('life:product:list')")
    @GetMapping("/all")
    public AjaxResult getAll(){
        return AjaxResult.success(tbLifeProductService.queryList());
    }

    /**
     * 查询寿险产品列表列表
     */
//    @ApiOperation(value = "查询寿险产品列表")
    @PreAuthorize("@ss.hasPermi('life:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbLifeProduct tbLifeProduct)
    {
        startPage();
        List<TbLifeProduct> list = tbLifeProductService.selectTbLifeProductList(tbLifeProduct);
        return getDataTable(list);
    }

    /**
     * 导出寿险产品列表列表
     */
//    @ApiOperation(value = "到处寿险产品列表")
    @PreAuthorize("@ss.hasPermi('life:product:export')")
    @Log(title = "寿险产品列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbLifeProduct tbLifeProduct)
    {
        List<TbLifeProduct> list = tbLifeProductService.selectTbLifeProductList(tbLifeProduct);
        ExcelUtil<TbLifeProduct> util = new ExcelUtil<TbLifeProduct>(TbLifeProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 获取寿险产品列表详细信息
     */
//    @ApiOperation(value = "获取寿险产品列表详情信息")
    @PreAuthorize("@ss.hasPermi('life:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbLifeProductService.selectTbLifeProductById(id));
    }

    /**
     * 新增寿险产品列表
     */
//    @ApiOperation(value = "新增产品")
    @PreAuthorize("@ss.hasPermi('life:product:add')")
    @Log(title = "寿险产品列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbLifeProduct tbLifeProduct)
    {
        return toAjax(tbLifeProductService.insertTbLifeProduct(tbLifeProduct));
    }

    /**
     * 修改寿险产品列表
     */
    @PreAuthorize("@ss.hasPermi('life:product:edit')")
    @Log(title = "寿险产品列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbLifeProduct tbLifeProduct)
    {
        return toAjax(tbLifeProductService.updateTbLifeProduct(tbLifeProduct));
    }

    /**
     * 删除寿险产品列表
     */
    @PreAuthorize("@ss.hasPermi('life:product:remove')")
    @Log(title = "寿险产品列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbLifeProductService.deleteTbLifeProductByIds(ids));
    }


}
