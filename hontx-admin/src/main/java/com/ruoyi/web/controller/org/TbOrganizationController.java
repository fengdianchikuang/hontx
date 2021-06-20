package com.ruoyi.web.controller.org;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.domain.vo.OrganizationVo;
import com.ruoyi.org.service.ITbOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构管理Controller
 *
 * @author ruoyi
 * @date 2021-04-20
 */
@RestController
@RequestMapping("/org/list")
public class TbOrganizationController extends BaseController
{
    @Autowired
    private ITbOrganizationService tbOrganizationService;

    @PreAuthorize("@ss.hasPermi('org:list:list')")
    @GetMapping("/type")
    public AjaxResult getParent(){
        List<OrganizationVo> list = tbOrganizationService.getParent();
        return AjaxResult.success(list);
    }

    /**
     * 查询机构管理列表
     */
    @PreAuthorize("@ss.hasPermi('org:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrganizationVo tbOrganization)
    {
        startPage();
        List<OrganizationVo> list = tbOrganizationService.selectTbOrganizationList(tbOrganization);
        return getDataTable(list);
    }

    /**
     * 导出机构管理列表
     */
    @PreAuthorize("@ss.hasPermi('org:list:export')")
    @Log(title = "机构管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OrganizationVo tbOrganization)
    {
        List<OrganizationVo> list = tbOrganizationService.selectTbOrganizationList(tbOrganization);
        ExcelUtil<OrganizationVo> util = new ExcelUtil<OrganizationVo>(OrganizationVo.class);
        return util.exportExcel(list, "list");
    }

    /**
     * 获取机构管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('org:list:query')")
    @GetMapping(value = "/{oId}")
    public AjaxResult getInfo(@PathVariable("oId") Long oId)
    {
        return AjaxResult.success(tbOrganizationService.selectTbOrganizationById(oId));
    }

    /**
     * 新增机构管理
     */
    @PreAuthorize("@ss.hasPermi('org:list:add')")
    @Log(title = "机构管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbOrganization tbOrganization)
    {
        return toAjax(tbOrganizationService.insertTbOrganization(tbOrganization));
    }

    /**
     * 修改机构管理
     */
    @PreAuthorize("@ss.hasPermi('org:list:edit')")
    @Log(title = "机构管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbOrganization tbOrganization)
    {
        return toAjax(tbOrganizationService.updateTbOrganization(tbOrganization));
    }

    /**
     * 删除机构管理
     */
    @PreAuthorize("@ss.hasPermi('org:list:remove')")
    @Log(title = "机构管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oIds}")
    public AjaxResult remove(@PathVariable Long[] oIds)
    {
        return toAjax(tbOrganizationService.deleteTbOrganizationByIds(oIds));
    }
}
