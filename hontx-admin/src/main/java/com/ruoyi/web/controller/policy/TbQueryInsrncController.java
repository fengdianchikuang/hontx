package com.ruoyi.web.controller.policy;

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
import com.ruoyi.system.domain.TbQueryInsrnc;
import com.ruoyi.system.service.ITbQueryInsrncService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * insrncController
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@RestController
@RequestMapping("/policy/insrnc")
public class TbQueryInsrncController extends BaseController
{
    @Autowired
    private ITbQueryInsrncService tbQueryInsrncService;

    /**
     * 查询insrnc列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbQueryInsrnc tbQueryInsrnc)
    {
        startPage();
        List<TbQueryInsrnc> list = tbQueryInsrncService.selectTbQueryInsrncList(tbQueryInsrnc);
        return getDataTable(list);
    }

    /**
     * 导出insrnc列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:export')")
    @Log(title = "insrnc", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbQueryInsrnc tbQueryInsrnc)
    {
        List<TbQueryInsrnc> list = tbQueryInsrncService.selectTbQueryInsrncList(tbQueryInsrnc);
        ExcelUtil<TbQueryInsrnc> util = new ExcelUtil<TbQueryInsrnc>(TbQueryInsrnc.class);
        return util.exportExcel(list, "insrnc");
    }

    /**
     * 获取insrnc详细信息
     */
    @PreAuthorize("@ss.hasPermi('policy:query:query')")
    @GetMapping(value = "/{qId}")
    public AjaxResult getInfo(@PathVariable("qId") Long qId)
    {
        return AjaxResult.success(tbQueryInsrncService.selectTbQueryInsrncById(qId));
    }

    /**
     * 新增insrnc
     */
    @PreAuthorize("@ss.hasPermi('policy:query:add')")
    @PostMapping
    public AjaxResult add(@RequestBody TbQueryInsrnc tbQueryInsrnc)
    {
        return toAjax(tbQueryInsrncService.insertTbQueryInsrnc(tbQueryInsrnc));
    }

    /**
     * 修改insrnc
     */
    @PreAuthorize("@ss.hasPermi('policy:query:edit')")
    @Log(title = "insrnc", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbQueryInsrnc tbQueryInsrnc)
    {
        return toAjax(tbQueryInsrncService.updateTbQueryInsrnc(tbQueryInsrnc));
    }

    /**
     * 删除insrnc
     */
    @PreAuthorize("@ss.hasPermi('policy:query:remove')")
    @Log(title = "insrnc", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbQueryInsrncService.deleteTbQueryInsrncByIds(ids));
    }
}
