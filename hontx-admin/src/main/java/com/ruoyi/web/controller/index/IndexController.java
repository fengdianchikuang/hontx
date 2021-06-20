package com.ruoyi.web.controller.index;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.income.service.ITbIncomeService;
import com.ruoyi.trans.service.ITbTransService;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.service.ITbUserInfoService;
import com.ruoyi.vehicle.service.ITbVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RequestMapping("/home")
@RestController
public class IndexController {


    @Autowired
    private ITbUserInfoService tbUserInfoService;

    @Autowired
    private ITbTransService tbTransService;

    @Autowired
    private ITbVehicleService tbVehicleService;

    @Autowired
    private ITbIncomeService incomeService;


    @GetMapping("/index")
    public AjaxResult index(){
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setUOrgId(user.getOrgId()+"");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        //获取今天0点时间
        LocalDateTime minToday = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        String todayMin = df.format(minToday);
        //获取今天24点时间
        LocalDateTime maxToday = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        String todayMax = df.format(maxToday);

        //获取昨天0点时间
        LocalDateTime minYesterday = LocalDateTime.of(LocalDate.now().minusDays(1L), LocalTime.MIN);
        String yesterdayMin = df.format(minYesterday);

        //获取昨天24点时间
        LocalDateTime maxYesterday = LocalDateTime.of(LocalDate.now().minusDays(1L), LocalTime.MAX);
        String yesterdayMax = df.format(maxYesterday);

        Map<String,Object> params = new HashMap<>();

        //今天之前的数据
        int totalCount = tbUserInfoService.queryListByTodayNum(user.getOrgId()+"",null,null);

        //昨天新增的数据
        int agentBeforeCount = tbUserInfoService.queryListByTodayNum(tbUserInfo.getUOrgId(),yesterdayMax,yesterdayMin);
        //今天新增
        int agentNowCount = tbUserInfoService.queryListByTodayNum(tbUserInfo.getUOrgId(),todayMax,todayMin);

        float agentPercentage = 0;

        if(agentBeforeCount > 0){
            agentPercentage = (agentNowCount / agentBeforeCount) * 100;
        }
        params.put("userAdd",agentNowCount);
        params.put("userPercentage",agentPercentage);
        params.put("userTotal",totalCount);

        //车辆统计
        //今日新增
        int vehicleNowCount = tbVehicleService.queryListByTodayNum(todayMax,todayMin);
        //昨天新增
        int vehicleYesterdayCount = tbVehicleService.queryListByTodayNum(yesterdayMax,yesterdayMin);
        //总车辆
        int vehicleBeforeCount = tbVehicleService.queryListByTodayNum(null,null);
        float vehiclePercentage = 0;
        if(vehicleYesterdayCount > 0){
            vehiclePercentage = (vehicleNowCount / vehicleYesterdayCount) * 100;
        }
        params.put("vehicleAdd",vehicleNowCount);
        params.put("vehiclePercentage",vehiclePercentage);
        params.put("vehicleTotal",vehicleBeforeCount);
        //收益统计
        //计算今日收益
        Float todayIncome = incomeService.queryTotalByToday(user.getOrgId(),todayMax,todayMin);
        if(todayIncome == null){
            todayIncome = 0f;
        }
        //计算昨天收益
        Float yesterdayIncome = incomeService.queryTotalByToday(user.getOrgId(),yesterdayMax,yesterdayMin);
        if(yesterdayIncome == null){
            yesterdayIncome = 0f;
        }
        //计算以前收益
        Float beforeIncome = incomeService.queryTotalByToday(user.getOrgId(),null,null);
        if(beforeIncome == null){
            beforeIncome = 0f;
        }
        float incomePercentage = 0;
        if(yesterdayIncome > 0){
            incomePercentage = (todayIncome / yesterdayIncome ) * 100;
        }
        params.put("incomeAdd",todayIncome);
        params.put("incomePercentage",incomePercentage);
        params.put("incomeTotal",beforeIncome);
        //计算寿险收益（暂时公用车险）
        //今日收益
        Float lifeTodayIncome = incomeService.queryLifeTotalByToday(user.getOrgId(),todayMax,todayMin);
        if(lifeTodayIncome == null){
            lifeTodayIncome = 0f;
        }
        //昨天收益
        Float lifeYesterdayIncome = incomeService.queryLifeTotalByToday(user.getOrgId(),yesterdayMax,yesterdayMin);
        if(lifeYesterdayIncome == null){
            lifeYesterdayIncome = 0f;
        }
        //总收益
        Float lifeTotalIncome = incomeService.queryLifeTotalByToday(user.getOrgId(),null,null);
        if(lifeTotalIncome == null){
            lifeTotalIncome = 0f;
        }
        float lifePercentage = 0;
        if(yesterdayIncome > 0){
            lifePercentage = (lifeTodayIncome / lifeYesterdayIncome) * 100;
        }
        params.put("lifeAdd",lifeTodayIncome);
        params.put("lifePercentage",lifePercentage);
        params.put("lifeTotal",lifeTotalIncome);
        //计算商城收益(暂时公用车险收益)
        //计算商城收益()transType = 06 车险结算 transType = 05 代金券购买
        Float shopTotalIncome = incomeService.queryShopTotalByToday(user.getOrgId(),todayMax,todayMin,"05");
        if(shopTotalIncome == null){
            shopTotalIncome = 0f;
        }
        Float shopYesterdayIncome = incomeService.queryShopTotalByToday(user.getOrgId(),yesterdayMax,yesterdayMin,"05");
        if(shopYesterdayIncome == null){
            shopYesterdayIncome = 0f;
        }
        Float shopTotal = incomeService.queryShopTotalByToday(user.getOrgId(),null,null,"05");
        if(shopTotal == null){
            shopTotal = 0f;
        }
        float shopPercentage = 0;
        if(shopYesterdayIncome > 0){
            shopPercentage = (shopTotalIncome / shopYesterdayIncome) * 100;
        }
        params.put("shopAdd",shopTotalIncome);
        params.put("shopPercentage",shopPercentage);
        params.put("shopTotal",shopTotal);
        //代金券
        Float integralTotalIncome = incomeService.queryShopTotalByToday(user.getOrgId(),todayMax,todayMin,"06");
        if(integralTotalIncome == null){
            integralTotalIncome = 0f;
        }
        Float integralYesterdayIncome = incomeService.queryShopTotalByToday(user.getOrgId(),yesterdayMax,yesterdayMin,"06");
        if(integralYesterdayIncome == null){
            integralYesterdayIncome = 0f;
        }
        Float integralTotal = incomeService.queryShopTotalByToday(user.getOrgId(),null,null,"06");
        if(integralTotal == null){
            integralTotal = 0f;
        }
        float integralPercentage = 0;
        if(shopYesterdayIncome > 0){
            integralPercentage = (integralTotalIncome / integralYesterdayIncome) * 100;
        }
        params.put("integralAdd",integralTotalIncome);
        params.put("integralPercentage",integralPercentage);
        params.put("integralTotal",integralTotal);
        return AjaxResult.success(params);
    }
}
