package com.ruoyi.web.controller.policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ruoyi.common.constant.UrlConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.income.service.ITbIncomeService;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.service.ITbOrganizationService;
import com.ruoyi.system.domain.QueryInsrnc;
import com.ruoyi.system.domain.VehicleFile;
import com.ruoyi.system.domain.req.QueryReq;
import com.ruoyi.system.domain.req.RegionReq;
import com.ruoyi.system.domain.vo.*;
import org.apache.commons.lang3.StringUtils;
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
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.service.ITbQueryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 已询价列表Controller
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@RestController
@RequestMapping("/policy/query")
public class TbQueryController extends BaseController
{
    @Autowired
    private ITbOrganizationService tbOrganizationService;

    @Autowired
    private ITbQueryService tbQueryService;

    @Autowired
    private ITbIncomeService tbIncomeService;

    /**
     * 查找自由询价的单号
     * @return
     */
    @PreAuthorize("@ss.hasPermi('policy:freeInquiry:list')")
    @GetMapping("/freeInquiry/list")
    public TableDataInfo findFreeQuery(QueryReq queryReq){
        SysUser currentUser = getCurrentUser();
        TbOrganization organization = tbOrganizationService.selectTbOrganizationById(currentUser.getOrgId());//currentUser.getOrgId()
        if(organization != null){
            if(!"00".equals(organization)){
                queryReq.setOrgId(currentUser.getOrgId()+"");
            }
        }
        startPage();
        List<QueryFreeVo> list = tbQueryService.listFreeQuery(queryReq);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('policy:query:add')")
    @PostMapping("/insrnc")
    public AjaxResult saveInsrnc(@RequestBody QueryInsrnc queryInsrnc){
        int result = tbQueryService.saveInsrnc(queryInsrnc);
        return AjaxResult.success(result);
    }

    /**
     * 获取地区码
     * @param regionReq
     * @return
     */
    @PostMapping("/region")
    public AjaxResult queryRegion(@RequestBody RegionReq regionReq){
        List<AreaCodeVo> list = tbQueryService.queryRegion(regionReq);
        return AjaxResult.success(list);
    }

    /**
     * 查询询价详情
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('policy:query:list')")
    @PostMapping("/detail/{queryId}")
    public AjaxResult queryDetail(@PathVariable String queryId){
        QueryDetailsVo result = tbQueryService.queryByQueryId(queryId);
        List<VehicleFile> files = result.getVehicleFile();
        if(files != null && files.size() > 0){
            for (VehicleFile file : files) {
                SetUrl(file,result);
            }
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询询价数量 00报价中的,01已看过没有填价格的
     * @return
     */
    @GetMapping("/getSize")
    public AjaxResult queryNum(){
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        TbQuery tbQuery = new TbQuery();

        //查找机构
        TbOrganization organization = tbOrganizationService.selectTbOrganizationById(user.getOrgId());
        if(organization != null){
            if(!"00".equals(organization)){
                tbQuery.setOrgId(organization.getoId()+"");
            }
        }
        //人工标识
        tbQuery.setFlag("1");  //修改测试
        tbQuery.setStatus("00"); //测试完后改成00
        List<QueryVo> queryVos = tbQueryService.selectTbQueryList(tbQuery);
        //没操作的
        Map<String,Object> params = new HashMap<>();
        params.put("zero",queryVos.size());
        tbQuery.setStatus("01");
        List<QueryVo> queryVos1 = tbQueryService.selectTbQueryList(tbQuery);
        params.put("one",queryVos1.size());
        return AjaxResult.success(params);
    }

    /**
     * 修改询价详情,返回询价实体并将status 00 改为01
     * @return
     */
    @PostMapping("/updateList")
    public AjaxResult editQuery(){
        //获取当前用户的机构号
        QueryDetailsVo startQuery = tbQueryService.findStartQuery(false);
        List<VehicleFile> files = startQuery.getVehicleFile();
        if(files != null && files.size() > 0){
            for (VehicleFile file : files) {
                SetUrl(file,startQuery);
            }
        }
        return AjaxResult.success(startQuery);
    }

//    /**
//     * 修改状态
//     * @param list
//     * @return
//     */
//    @PostMapping("/status")
//    public AjaxResult updateList(@RequestBody List<TbQuery> list){
//        int rows = tbQueryService.updateList(list);
//        return toAjax(rows);
//    }

    /**
     * 查询已询价列表列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbQuery tbQuery)
    {
        startPage();
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbQuery.setOrgId(user.getOrgId()+"");
        List<QueryVo> list = tbQueryService.selectTbQueryList(tbQuery);
        return getDataTable(list);
    }

    /**
     * 导出已询价列表列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:export')")
    @Log(title = "已询价列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbQuery tbQuery)
    {
        //获取当前用户的机构号
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbQuery.setOrgId(user.getOrgId()+"");
        List<QueryVo> list = tbQueryService.selectTbQueryList(tbQuery);
        ExcelUtil<QueryVo> util = new ExcelUtil<QueryVo>(QueryVo.class);
        return util.exportExcel(list, "query");
    }

    /**
     * 获取已询价列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('policy:query:query')")
    @GetMapping(value = "/{qId}")
    public AjaxResult getInfo(@PathVariable("qId") Long qId)
    {
        return AjaxResult.success(tbQueryService.selectTbQueryById(qId));
    }

    /**
     * 新增已询价列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:add')")
    @Log(title = "已询价列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbQuery tbQuery)
    {
        return toAjax(tbQueryService.insertTbQuery(tbQuery));
    }

    /**
     * 修改已询价列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:edit')")
//    @Log(title = "已询价列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbQuery tbQuery)
    {
        if("05".equals(tbQuery.getStatus())){
            //投保之前还需要检查费率是否设置,如果未设置,不允许投保
            if(!tbIncomeService.isSetFee(tbQuery)){
                return AjaxResult.error("对应的保险公司费率未设置,暂不能投保");
            }
        }

        return toAjax(tbQueryService.updateTbQuery(tbQuery));
    }

    /**
     * 删除已询价列表
     */
    @PreAuthorize("@ss.hasPermi('policy:query:remove')")
    @Log(title = "已询价列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{qIds}")
    public AjaxResult remove(@PathVariable Long[] qIds)
    {
        return toAjax(tbQueryService.deleteTbQueryByIds(qIds));
    }
    //设置url
    private void SetUrl(VehicleFile file, QueryDetailsVo result) {
        String path = file.getPath();
        if(StringUtils.isEmpty(path)){
            return;
        }
        boolean flg = judgeUrlPrefix(path);
        switch (file.getType()){
            case "00":
                if(flg){
                    result.setUrl01(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
                  }else {
                        result.setUrl01(UrlConstants.OSS_PREFIX + path);
                  }
              break;
          case "01":

              if(flg){
                    result.setUrl02(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
              }else {
                    result.setUrl02(UrlConstants.OSS_PREFIX + path);
              }
              break;
          case "02":
              if(flg){
                    result.setUrl03(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
              }else {
                    result.setUrl03(UrlConstants.OSS_PREFIX + path);
              }
              break;
          case "03":
              if(flg){
                    result.setUrl04(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
              }else {
                    result.setUrl04(UrlConstants.OSS_PREFIX + path);
              }
                break;
        }
    }
    //判断前缀，是app，还是小程序
    private boolean judgeUrlPrefix(String path){
        return path.contains("/home");
    }
}
