package com.ruoyi.web.controller.lifefinance;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lifefinance.domain.TbLifeTrans;
import com.ruoyi.lifefinance.domain.WithdrawalTrans;
import com.ruoyi.lifefinance.domain.req.LifeTransReq;
import com.ruoyi.lifefinance.domain.vo.LifeTransVo;
import com.ruoyi.lifefinance.service.ITbLifeTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交易明细Controller
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@RestController
@RequestMapping("/ifefinance/details")
public class TbLifeTransController extends BaseController
{
    @Autowired
    private ITbLifeTransService tbLifeTransService;

    /**
     * 修改提现状态
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:edit')")
    @PutMapping("/change/{id}")
    public AjaxResult changeStatus(@PathVariable Long id){
        TbLifeTrans tbLifeTrans = tbLifeTransService.selectTbLifeTransById(id);
        tbLifeTrans.setStatus("01");
        return toAjax(tbLifeTransService.updateTbLifeTrans(tbLifeTrans));
    }
    /**
     * 查询提现列表
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:list')")
    @GetMapping("/withdrawalList")
    public TableDataInfo withdrawalList(LifeTransReq transReq){
        startPage();
        List<WithdrawalTrans> list = tbLifeTransService.selectWithdrawalList(transReq);
        return getDataTable(list);
    }

    /**
     * 查询交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(LifeTransReq tbLifeTrans)
    {
        startPage();
        List<LifeTransVo> list = tbLifeTransService.selectTbLifeTransList(tbLifeTrans);
        return getDataTable(list);
    }

    /**
     * 导出交易明细列表
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:export')")
    @Log(title = "交易明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LifeTransReq tbLifeTrans)
    {
        List<LifeTransVo> list = tbLifeTransService.selectTbLifeTransList(tbLifeTrans);
        ExcelUtil<LifeTransVo> util = new ExcelUtil<LifeTransVo>(LifeTransVo.class);
        return util.exportExcel(list, "details");
    }

    /**
     * 获取交易明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbLifeTransService.selectTbLifeTransById(id));
    }

    /**
     * 新增交易明细
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:add')")
    @Log(title = "交易明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbLifeTrans tbLifeTrans)
    {
        return toAjax(tbLifeTransService.insertTbLifeTrans(tbLifeTrans));
    }

    /**
     * 修改交易明细
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:edit')")
    @Log(title = "交易明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbLifeTrans tbLifeTrans)
    {
        return toAjax(tbLifeTransService.updateTbLifeTrans(tbLifeTrans));
    }

    /**
     * 删除交易明细
     */
    @PreAuthorize("@ss.hasPermi('ifefinance:details:remove')")
    @Log(title = "交易明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbLifeTransService.deleteTbLifeTransByIds(ids));
    }
}
