package com.ruoyi.web.controller.company;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.company.domain.TbInsrncCpy;
import com.ruoyi.company.service.ITbInsrncCpyService;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * companyController
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@RestController
@RequestMapping("/company/list")
public class TbInsrncCpyController extends BaseController
{
    @Autowired
    private ITbInsrncCpyService tbInsrncCpyService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询company列表
     */
    @PreAuthorize("@ss.hasPermi('company:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbInsrncCpy tbInsrncCpy)
    {
        startPage();
        List<TbInsrncCpy> list = tbInsrncCpyService.selectTbInsrncCpyList(tbInsrncCpy);
        return getDataTable(list);
    }

    /**
     * 导出company列表
     */
    @PreAuthorize("@ss.hasPermi('company:list:export')")
    @Log(title = "company", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbInsrncCpy tbInsrncCpy)
    {
        List<TbInsrncCpy> list = tbInsrncCpyService.selectTbInsrncCpyList(tbInsrncCpy);
        ExcelUtil<TbInsrncCpy> util = new ExcelUtil<TbInsrncCpy>(TbInsrncCpy.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 获取company详细信息
     */
    @PreAuthorize("@ss.hasPermi('company:list:query')")
    @GetMapping(value = "/{iId}")
    public AjaxResult getInfo(@PathVariable("iId") Long iId)
    {
        return AjaxResult.success(tbInsrncCpyService.selectTbInsrncCpyById(iId));
    }

    /**
     * 新增company
     */
    @PreAuthorize("@ss.hasPermi('company:list:add')")
    @Log(title = "company", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbInsrncCpy tbInsrncCpy)
    {
        return toAjax(tbInsrncCpyService.insertTbInsrncCpy(tbInsrncCpy));
    }

    /**
     * 修改company
     */
    @PreAuthorize("@ss.hasPermi('company:list:edit')")
    @Log(title = "company", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbInsrncCpy tbInsrncCpy)
    {
        return toAjax(tbInsrncCpyService.updateTbInsrncCpy(tbInsrncCpy));
    }

    /**
     * 删除company
     */
    @PreAuthorize("@ss.hasPermi('company:list:remove')")
    @Log(title = "company", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iIds}")
    public AjaxResult remove(@PathVariable Long[] iIds)
    {
        return toAjax(tbInsrncCpyService.deleteTbInsrncCpyByIds(iIds));
    }
}
