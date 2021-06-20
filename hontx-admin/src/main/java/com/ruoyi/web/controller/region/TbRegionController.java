package com.ruoyi.web.controller.region;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.region.domain.TbRegion;
import com.ruoyi.region.service.ITbRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地区码Controller
 *
 * @author ruoyi
 * @date 2021-04-13
 */
@RestController
@RequestMapping("/region/address")
public class TbRegionController extends BaseController
{
    @Autowired
    private ITbRegionService tbRegionService;

    /**
     * 查询地区码列表
     */
//    @PreAuthorize("@ss.hasPermi('region:poiving:list')")
    @GetMapping("/list")
    public AjaxResult list(TbRegion tbRegion)
    {
        List<TbRegion> list = tbRegionService.selectTbRegionList(tbRegion);
        return AjaxResult.success(list);
    }


    /**
     * 导出地区码列表
     */
//    @PreAuthorize("@ss.hasPermi('region:poiving:export')")
    @Log(title = "地区码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbRegion tbRegion)
    {
        List<TbRegion> list = tbRegionService.selectTbRegionList(tbRegion);
        ExcelUtil<TbRegion> util = new ExcelUtil<TbRegion>(TbRegion.class);
        return util.exportExcel(list, "poiving");
    }

    /**
     * 获取地区码详细信息
     */
//    @PreAuthorize("@ss.hasPermi('region:poiving:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") Long code)
    {
        return AjaxResult.success(tbRegionService.selectTbRegionById(code));
    }

    /**
     * 新增地区码
     */
//    @PreAuthorize("@ss.hasPermi('region:poiving:add')")
    @Log(title = "地区码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbRegion tbRegion)
    {
        return toAjax(tbRegionService.insertTbRegion(tbRegion));
    }

//    /**
//     * 修改地区码
//     */
////    @PreAuthorize("@ss.hasPermi('region:poiving:edit')")
//    @Log(title = "地区码", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody TbRegion tbRegion)
//    {
//        return toAjax(tbRegionService.updateTbRegion(tbRegion));
//    }

//    /**
//     * 删除地区码
//     */
//    @PreAuthorize("@ss.hasPermi('region:poiving:remove')")
//    @Log(title = "地区码", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{codes}")
//    public AjaxResult remove(@PathVariable Long[] codes)
//    {
//        return toAjax(tbRegionService.deleteTbRegionByIds(codes));
//    }
}
