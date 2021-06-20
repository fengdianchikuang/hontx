package com.ruoyi.web.controller.rate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.rate.domain.TbInsrncCpyRate;
import com.ruoyi.rate.service.ITbInsrncCpyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 费率设置Controller
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@RestController
@RequestMapping("/rate/rate")
public class TbInsrncCpyRateController extends BaseController
{
    @Autowired
    private ITbInsrncCpyRateService tbInsrncCpyRateService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询费率设置列表
     */
    @PreAuthorize("@ss.hasPermi('company:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbInsrncCpyRate tbInsrncCpyRate)
    {
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbInsrncCpyRate.setOrgId(user.getOrgId()+"");
        startPage();
        List<TbInsrncCpyRate> list = tbInsrncCpyRateService.selectTbInsrncCpyRateList(tbInsrncCpyRate);
        return getDataTable(list);
    }

    /**
     * 导出费率设置列表
     */
    @PreAuthorize("@ss.hasPermi('company:list:export')")
    @Log(title = "费率设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbInsrncCpyRate tbInsrncCpyRate)
    {
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbInsrncCpyRate.setOrgId(user.getOrgId()+"");
        List<TbInsrncCpyRate> list = tbInsrncCpyRateService.selectTbInsrncCpyRateList(tbInsrncCpyRate);
        ExcelUtil<TbInsrncCpyRate> util = new ExcelUtil<TbInsrncCpyRate>(TbInsrncCpyRate.class);
        return util.exportExcel(list, "rate");
    }

    /**
     * 获取费率设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('company:list:query')")
    @GetMapping(value = "/{iId}")
    public AjaxResult getInfo(@PathVariable("iId") Long iId)
    {
        return AjaxResult.success(tbInsrncCpyRateService.selectTbInsrncCpyRateById(iId));
    }

    /**
     * 新增费率设置(多条)
     */
    @PreAuthorize("@ss.hasPermi('company:list:add')")
    @Log(title = "费率设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addList(@RequestBody JSONObject rates)
    {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String channelId = rates.getString("channelId");
        LocalDate startTime = LocalDate.parse(rates.getString("startDate"), df);
        SysUser currentUser = getCurrentUser();
        LocalDate endTime = LocalDate.parse(rates.getString("endDate"), df);

        String belongType = rates.getString("belongType");
        JSONArray cpyRates = rates.getJSONArray("data");


        //开始日期 小于 或 等于 结束日期
        while (startTime.isEqual(endTime) || startTime.isBefore(endTime)){
            Date effectDate = Date.from(startTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
            //删除相同日期，机构Id，保险公司数据,所属性质
            TbInsrncCpyRate delCpyRate = new TbInsrncCpyRate();
            delCpyRate.setOrgId(currentUser.getOrgId()+"");
            delCpyRate.setChannelId(channelId);
            String format = formatter.format(effectDate);
            delCpyRate.setEffectDateString(format);
            delCpyRate.setBelongType(belongType);
            tbInsrncCpyRateService.deleteByEffectDateAndChannel(delCpyRate);
            for (int i = 0; i < cpyRates.size(); i++) {
                JSONObject jsonObject = cpyRates.getJSONObject(i);
                TbInsrncCpyRate rate = JSONObject.parseObject(jsonObject.toJSONString(), TbInsrncCpyRate.class);
                rate.setInsrncType(jsonObject.getString("insrncType"));
                rate.setUseType(jsonObject.getString("useType"));
                rate.setChannelId(channelId);
                rate.setEffectDate(effectDate);
                rate.setBelongType(belongType);
                rate.setOrgId(currentUser.getOrgId()+"");
                rate.setCreateBy(currentUser.getUserName());
                rate.setiCreateTime(new Date());
                tbInsrncCpyRateService.insertRate(rate);

            }

            startTime = startTime.plusDays(1);
        }
        return AjaxResult.success();
    }

    /**
     * 新增费率设置
     */
//    @PreAuthorize("@ss.hasPermi('company:list:add')")
//    @Log(title = "费率设置", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody TbInsrncCpyRate tbInsrncCpyRate)
//    {
//        return toAjax(tbInsrncCpyRateService.insertTbInsrncCpyRate(tbInsrncCpyRate));
//    }

    /**
     * 修改费率设置
     */
    @PreAuthorize("@ss.hasPermi('company:list:edit')")
    @Log(title = "费率设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbInsrncCpyRate tbInsrncCpyRate)
    {
        return toAjax(tbInsrncCpyRateService.updateTbInsrncCpyRate(tbInsrncCpyRate));
    }

    /**
     * 删除费率设置
     */
    @PreAuthorize("@ss.hasPermi('company:list:remove')")
    @Log(title = "费率设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iIds}")
    public AjaxResult remove(@PathVariable Long[] iIds)
    {
        return toAjax(tbInsrncCpyRateService.deleteTbInsrncCpyRateByIds(iIds));
    }
}
