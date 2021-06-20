package com.ruoyi.web.controller.lifeholder;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lifeholder.domain.TbLifeHolder;
import com.ruoyi.lifeholder.domain.vo.LifeHolderVo;
import com.ruoyi.lifeholder.service.ITbLifeHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 寿险客户Controller
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@RestController
@RequestMapping("/lifeholder/holder")
public class TbLifeHolderController extends BaseController
{
    @Autowired
    private ITbLifeHolderService tbLifeHolderService;

    /**
     * 查询寿险客户列表
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:list')")
    @GetMapping("/list")
    public TableDataInfo list(LifeHolderVo tbLifeHolder)
    {
        startPage();
        List<LifeHolderVo> list = tbLifeHolderService.selectTbLifeHolderList(tbLifeHolder);
        return getDataTable(list);
    }

    /**
     * 导出寿险客户列表
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:export')")
    @Log(title = "寿险客户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LifeHolderVo tbLifeHolder)
    {
        List<LifeHolderVo> list = tbLifeHolderService.selectTbLifeHolderList(tbLifeHolder);
        ExcelUtil<LifeHolderVo> util = new ExcelUtil<LifeHolderVo>(LifeHolderVo.class);
        return util.exportExcel(list, "holder");
    }

    /**
     * 获取寿险客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbLifeHolderService.selectTbLifeHolderById(id));
    }

    /**
     * 新增寿险客户
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:add')")
    @Log(title = "寿险客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbLifeHolder tbLifeHolder)
    {
        return toAjax(tbLifeHolderService.insertTbLifeHolder(tbLifeHolder));
    }

    /**
     * 修改寿险客户
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:edit')")
    @Log(title = "寿险客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbLifeHolder tbLifeHolder)
    {
        return toAjax(tbLifeHolderService.updateTbLifeHolder(tbLifeHolder));
    }

    /**
     * 删除寿险客户
     */
    @PreAuthorize("@ss.hasPermi('lifeholder:holder:remove')")
    @Log(title = "寿险客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbLifeHolderService.deleteTbLifeHolderByIds(ids));
    }
}
