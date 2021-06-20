package com.ruoyi.web.controller.vehicle;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.TbVehicleFile;
import com.ruoyi.vehicle.domain.req.MaintenanceDetailRequest;

import com.ruoyi.vehicle.domain.req.VehicleFileVo;
import com.ruoyi.vehicle.domain.req.VehicleReq;
import com.ruoyi.vehicle.domain.vo.*;
import com.ruoyi.vehicle.service.ITbVehicleService;
import com.ruoyi.vehicle.service.VehicleOwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * carController
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@RestController
@RequestMapping("/vehicle/index")
@Api(tags = "车辆信息",description = "车辆信息",value = "车辆信息")
public class TbVehicleController extends BaseController
{
    @Autowired
    private ITbVehicleService tbVehicleService;

    @Autowired
    private VehicleOwnerService vehicleOwnerService;


    @ApiOperation(value = "车辆信息登记/修改")
    @PostMapping(value = "/save")
    @ResponseBody
    public Object insertCarInfo(@RequestBody VehicleReq vehicleVo)  {
        tbVehicleService.saveVehicle(vehicleVo);
        return AjaxResult.success();
    }

    @ApiOperation(value = "查询维修履历工单")
    @PostMapping(value = "/queryMaintenance/{licenseNo}")
    @ResponseBody
    public Object findMaintenanceRecord(@PathVariable String licenseNo){
        String result = tbVehicleService.findMaintenanceRecord(licenseNo);
        JSONObject data = JSONObject.parseObject(result);
        return AjaxResult.success(data);
    }

    @ResponseBody
    @ApiOperation(value = "查询维修履历详情")
    @PostMapping(value = "/maintenanceDetail")
    public Object maintenanceRecordDetail(@RequestBody MaintenanceDetailRequest request){
        Map<String,Object> result = tbVehicleService.maintenanceRecordDetail(request);
        return AjaxResult.success(result);
    }


    @ApiOperation(value = "上传车辆证件照| type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other")
    @PostMapping(value = "/upload")
    @ResponseBody
    public Object uploadCert(MultipartFile file,  VehicleFileVo fileVo) throws Exception {
        Map<String, Object> param = tbVehicleService.saveVehicleFile(fileVo, file);
        return AjaxResult.success(param);
    }


    @ApiOperation(value = "获取用户所拥有车辆信息OwnerId:车辆拥有者ID")
    @GetMapping(value = "/info/{ownerId}")
    @ResponseBody
    public Object getCarInfoByOwnerId(@PathVariable String ownerId){
        List<VehicleInfoVo> list = vehicleOwnerService.findByOwnerId(ownerId);
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "根s据车架获取车辆信息|包含车辆证件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "frameNo", value = "车架(二选一)新车只能选车架"),
            @ApiImplicitParam(name = "frameNo", value = "车架(二选一)新车只能选车架")
    })
    @GetMapping(value = "/info")
    @ResponseBody
    public Object getCarInfoByLicenseNoOrFrameNo(String frameNo,String licenseNo){
        VehicleDetails vehicle = tbVehicleService.findDetailsByFrameNoAndLicenseNo(frameNo,licenseNo);
        return AjaxResult.success(vehicle);
    }

    @ApiOperation(value = "查询车主车辆年审/保险时间")
    @GetMapping(value = "/inspect/{ownerId}")
    @ResponseBody
    public Object selectInspect(@PathVariable String ownerId){
        List<VehicleInspectVo> list = tbVehicleService.vehicleInspect(ownerId);
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "根据id删除")
    @PostMapping(value = "/delete/{id}")
    @ResponseBody
    public Object deleteCar(@PathVariable Long id){
        tbVehicleService.deleteTbVehicleById(id);
        return AjaxResult.success();
    }







    /**
     * 查询car列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbVehicle tbVehicle)
    {
        startPage();
        List<TbVehicle> list = tbVehicleService.selectTbVehicleList(tbVehicle);
        return getDataTable(list);
    }

    /**
     * 导出car列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:export')")
    @Log(title = "car", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbVehicle tbVehicle)
    {
        List<TbVehicle> list = tbVehicleService.selectTbVehicleList(tbVehicle);
        ExcelUtil<TbVehicle> util = new ExcelUtil<TbVehicle>(TbVehicle.class);
        return util.exportExcel(list, "index");
    }


    /**
     * 获取car详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:query')")
    @GetMapping(value = "/{vId}")
    public AjaxResult getInfo(@PathVariable("vId") Long vId)
    {
        return AjaxResult.success(tbVehicleService.selectTbVehicleById(vId));
    }

    @PreAuthorize("@ss.hasPermi('vehicle:index:add')")
    @Log(title = "上传图片", businessType = BusinessType.OTHER)
    @PostMapping(value = "/uploadImg")
    public AjaxResult upload(MultipartFile file,String rackNo,String vehicleNo,String type) throws IOException {
        if(file != null){
            String filename = FileUploadUtils.extractFilename(file);
            String path = "vehicle/" + vehicleNo + "_" + rackNo + "/" + filename;
            FileUploadUtils.putObjectToAliOss(OssConstants.bucketName,path,file.getInputStream());
            TbVehicleFile tbVehicleFile = new TbVehicleFile();
            tbVehicleFile.setFileName(filename);
            tbVehicleFile.setPath(path);
            tbVehicleFile.setRackNo(rackNo);
            tbVehicleFile.setVehicleNo(vehicleNo);
            tbVehicleFile.setType(type);
            return toAjax(tbVehicleService.insertVehicleFile(tbVehicleFile));
        }
        return AjaxResult.error();
    }



    /**
     * 新增car
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:add')")
    @Log(title = "car", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbVehicle tbVehicle)
    {
        return toAjax(tbVehicleService.insertTbVehicle(tbVehicle));
    }

    /**
     * 修改car
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:edit')")
    @Log(title = "car", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbVehicle tbVehicle)
    {
        return toAjax(tbVehicleService.updateTbVehicle(tbVehicle));
    }
    @PreAuthorize("@ss.hasPermi('vehicle:index:remove')")
    @DeleteMapping("/upload/{id}")
    public AjaxResult delVehicleFile(@PathVariable Long id){
        return toAjax(tbVehicleService.delVehicleFileById(id));
    }
    /**
     * 删除car
     */
    @PreAuthorize("@ss.hasPermi('vehicle:index:remove')")
    @Log(title = "car", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vIds}")
    public AjaxResult remove(@PathVariable Long[] vIds)
    {
        return toAjax(tbVehicleService.deleteTbVehicleByIds(vIds));
    }
}
