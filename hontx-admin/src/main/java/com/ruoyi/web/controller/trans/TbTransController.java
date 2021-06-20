package com.ruoyi.web.controller.trans;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.domain.req.ProfitRequest;
import com.ruoyi.trans.domain.vo.CompletionVo;
import com.ruoyi.trans.domain.vo.TransVo;
import com.ruoyi.trans.service.ITbTransService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 保险收益流水相关Controller
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@RestController
@RequestMapping("/trans/index")
@Api(value = "保险收益流水相关",tags = "保险流水相关",description = "保险流水")
@Slf4j
public class TbTransController extends BaseController
{
    @Autowired
    private ITbTransService tbTransService;

    @ApiOperation(value = "我的保险收益列表")
    @PostMapping(value = "/profit")
    public Object profit(@RequestBody ProfitRequest request){
        PageHelper.startPage(request.getPage(),request.getSize());
        List<TransVo> profit = tbTransService.profit(request);
        PageInfo<TransVo> list = new PageInfo<>(profit);
        return AjaxResult.success(list);
    }
    @ApiOperation(value = "当月保险收益与可用余额")
    @PostMapping(value = "/balance")
    public Object totalAndAmount(@RequestBody ProfitRequest request){
        Map<String,Object> param =  tbTransService.balance(request);
        return AjaxResult.success(param);
    }

    @ApiOperation(value = "团队完成率")
    @PostMapping(value = "/complete/{userId}")
    public Object CompletionRate(@PathVariable Integer userId){
        CompletionVo completionVo = tbTransService.CompletionRate(userId);
        return AjaxResult.success(completionVo);
    }



    /**
     * 查询提现列表列表
     */
    @PreAuthorize("@ss.hasPermi('trans:index:list')")
    @GetMapping("/list")
    public TableDataInfo list(TransVo tbTrans)
    {
        startPage();
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbTrans.setOrgId(user.getOrgId()+"");
        List<TransVo> list = tbTransService.selectTbTransList(tbTrans);
        return getDataTable(list);
    }

    /**
     * 导出提现列表列表
     */
    @PreAuthorize("@ss.hasPermi('trans:index:export')")
    @Log(title = "提交列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransVo tbTrans)
    {
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbTrans.setOrgId(user.getOrgId()+"");
        List<TransVo> list = tbTransService.selectTbTransList(tbTrans);
        ExcelUtil<TransVo> util = new ExcelUtil<TransVo>(TransVo.class);
        return util.exportExcel(list, "index");
    }

    /**
     * 获取提现列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('trans:index:query')")
    @GetMapping(value = "/{tId}")
    public AjaxResult getInfo(@PathVariable("tId") Long tId)
    {
        return AjaxResult.success(tbTransService.selectTbTransById(tId));
    }

    @PutMapping("/{id}")
    @Log(title = "提现受理", businessType = BusinessType.UPDATE)
    @PreAuthorize("@ss.hasPermi('trans:index:remove')")
    public AjaxResult update(@PathVariable Long id){
        return toAjax(tbTransService.withdrawal(id));
    }

    /**
     * 新增提交列表
     */
    @PreAuthorize("@ss.hasPermi('trans:index:add')")
    @Log(title = "提现列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbTrans tbTrans)
    {
        return toAjax(tbTransService.insertTbTrans(tbTrans));
    }

    /**
     * 修改提交列表
     */
    @PreAuthorize("@ss.hasPermi('trans:index:edit')")
    @Log(title = "提现列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbTrans tbTrans)
    {
        return toAjax(tbTransService.updateTbTrans(tbTrans));
    }

    /**
     * 删除提交列表
     */
    @PreAuthorize("@ss.hasPermi('trans:index:remove')")
    @Log(title = "提现列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tIds}")
    public AjaxResult remove(@PathVariable Long[] tIds)
    {
        return toAjax(tbTransService.deleteTbTransByIds(tIds));
    }
}
