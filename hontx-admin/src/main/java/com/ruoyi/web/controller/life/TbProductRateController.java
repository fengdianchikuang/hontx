package com.ruoyi.web.controller.life;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.life.domain.TbProductRate;
import com.ruoyi.life.service.ITbProductRateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品费率表Controller
 *
 * @author ruoyi
 * @date 2021-05-07
 */
@RestController
@RequestMapping("/life/rate")
public class TbProductRateController extends BaseController
{
    @Autowired
    private ITbProductRateService tbProductRateService;

    /**
     * 查询产品费率表列表
     */
    @PreAuthorize("@ss.hasPermi('life:rate:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbProductRate tbProductRate)
    {
        startPage();
        List<TbProductRate> list = tbProductRateService.selectTbProductRateList(tbProductRate);
        return getDataTable(list);
    }

    /**
     * 导出产品费率表列表
     */
    @PreAuthorize("@ss.hasPermi('life:rate:export')")
    @Log(title = "产品费率表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbProductRate tbProductRate)
    {
        List<TbProductRate> list = tbProductRateService.selectTbProductRateList(tbProductRate);
        ExcelUtil<TbProductRate> util = new ExcelUtil<TbProductRate>(TbProductRate.class);
        return util.exportExcel(list, "rate");
    }

    /**
     * 获取产品费率表详细信息
     */
    @PreAuthorize("@ss.hasPermi('life:rate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbProductRateService.selectTbProductRateById(id));
    }

    /**
     * 新增产品费率表
     */
    @PreAuthorize("@ss.hasPermi('life:rate:add')")
    @Log(title = "产品费率表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbProductRate tbProductRate)
    {
        return toAjax(tbProductRateService.insertTbProductRate(tbProductRate));
    }

    /**
     * 修改产品费率表
     */
    @PreAuthorize("@ss.hasPermi('life:rate:edit')")
    @Log(title = "产品费率表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbProductRate tbProductRate)
    {
        return toAjax(tbProductRateService.updateTbProductRate(tbProductRate));
    }

    /**
     * 删除产品费率表
     */
    @PreAuthorize("@ss.hasPermi('life:rate:remove')")
    @Log(title = "产品费率表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbProductRateService.deleteTbProductRateByIds(ids));
    }
}
