package com.ruoyi.web.controller.trans;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.trans.domain.vo.TransDetailVo;
import com.ruoyi.trans.domain.vo.TransVo;
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
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.service.ITbTransService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易明细Controller
 *
 * @author ruoyi
 * @date 2021-04-19
 */
@RestController
@RequestMapping("/trans/detailed")
public class TbTransInfoController extends BaseController
{
    @Autowired
    private ITbTransService tbTransService;

    /**
     * 查询交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransDetailVo tbTrans)
    {
        startPage();

        List<TransDetailVo> list = tbTransService.selectTbTransInfoList(tbTrans);
        return getDataTable(list);
    }

    /**
     * 导出交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:export')")
    @Log(title = "交易明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransDetailVo tbTrans)
    {
        List<TransDetailVo> list = tbTransService.selectTbTransInfoList(tbTrans);
        ExcelUtil<TransDetailVo> util = new ExcelUtil<TransDetailVo>(TransDetailVo.class);
        return util.exportExcel(list, "detailed");
    }

    /**
     * 获取交易明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:query')")
    @GetMapping(value = "/{tId}")
    public AjaxResult getInfo(@PathVariable("tId") Long tId)
    {
        return AjaxResult.success(tbTransService.selectTbTransById(tId));
    }

    /**
     * 新增交易明细
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:add')")
    @Log(title = "交易明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbTrans tbTrans)
    {
        return toAjax(tbTransService.insertTbTrans(tbTrans));
    }

    /**
     * 修改交易明细
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:edit')")
    @Log(title = "交易明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbTrans tbTrans)
    {
        return toAjax(tbTransService.updateTbTrans(tbTrans));
    }

    /**
     * 删除交易明细
     */
    @PreAuthorize("@ss.hasPermi('trans:detailed:remove')")
    @Log(title = "交易明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tIds}")
    public AjaxResult remove(@PathVariable Long[] tIds)
    {
        return toAjax(tbTransService.deleteTbTransByIds(tIds));
    }
}
