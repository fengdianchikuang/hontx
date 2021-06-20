package com.ruoyi.web.controller.income;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.income.domain.TbIncome;
import com.ruoyi.income.domain.vo.IncomeVo;
import com.ruoyi.income.service.ITbIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售业绩Controller
 *
 * @author ruoyi
 * @date 2021-04-19
 */
@RestController
@RequestMapping("/trans/income")
public class TbIncomeController extends BaseController
{
    @Autowired
    private ITbIncomeService tbIncomeService;

    /**
     * 查询销售业绩列表
     */
    @PreAuthorize("@ss.hasPermi('trans:income:list')")
    @GetMapping("/list")
    public TableDataInfo list(IncomeVo tbIncome)
    {
        startPage();
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbIncome.setOrg(user.getOrgId()+"");
        List<IncomeVo> list = tbIncomeService.selectTbIncomeList(tbIncome);
        return getDataTable(list);
    }

    /**
     * 导出销售业绩列表
     */
    @PreAuthorize("@ss.hasPermi('trans:income:export')")
    @Log(title = "销售业绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(IncomeVo tbIncome)
    {
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbIncome.setOrg(user.getOrgId()+"");
        List<IncomeVo> list = tbIncomeService.selectTbIncomeList(tbIncome);
        ExcelUtil<IncomeVo> util = new ExcelUtil<IncomeVo>(IncomeVo.class);
        return util.exportExcel(list, "income");
    }

    /**
     * 获取销售业绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('trans:income:query')")
    @GetMapping(value = "/{iId}")
    public AjaxResult getInfo(@PathVariable("iId") Long iId)
    {
        return AjaxResult.success(tbIncomeService.selectTbIncomeById(iId));
    }

    /**
     * 新增销售业绩
     */
    @PreAuthorize("@ss.hasPermi('trans:income:add')")
    @Log(title = "销售业绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbIncome tbIncome)
    {
        return toAjax(tbIncomeService.insertTbIncome(tbIncome));
    }

    /**
     * 修改销售业绩
     */
    @PreAuthorize("@ss.hasPermi('trans:income:edit')")
    @Log(title = "销售业绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbIncome tbIncome)
    {
        return toAjax(tbIncomeService.updateTbIncome(tbIncome));
    }

    /**
     * 删除销售业绩
     */
    @PreAuthorize("@ss.hasPermi('trans:income:remove')")
    @Log(title = "销售业绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iIds}")
    public AjaxResult remove(@PathVariable Long[] iIds)
    {
        return toAjax(tbIncomeService.deleteTbIncomeByIds(iIds));
    }
}
