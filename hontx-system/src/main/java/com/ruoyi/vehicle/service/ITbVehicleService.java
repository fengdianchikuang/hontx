package com.ruoyi.vehicle.service;


import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.TbVehicleFile;
import com.ruoyi.vehicle.domain.VehicleInsrncEntity;
import com.ruoyi.vehicle.domain.req.MaintenanceDetailRequest;
import com.ruoyi.vehicle.domain.req.VehicleApplicantRequest;
import com.ruoyi.vehicle.domain.req.VehicleFileVo;
import com.ruoyi.vehicle.domain.req.VehicleReq;
import com.ruoyi.vehicle.domain.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * carService接口
 *
 * @author ruoyi
 * @date 2021-04-09
 */
public interface ITbVehicleService
{




    /**
     * 查询car
     *
     * @param vId carID
     * @return car
     */
    public VehicleVo selectTbVehicleById(Long vId);

    /**
     * 查询car列表
     *
     * @param tbVehicle car
     * @return car集合
     */
    public List<TbVehicle> selectTbVehicleList(TbVehicle tbVehicle);

    /**
     * 新增car
     *
     * @param tbVehicle car
     * @return 结果
     */
    public int insertTbVehicle(TbVehicle tbVehicle);

    /**
     * 修改car
     *
     * @param tbVehicle car
     * @return 结果
     */
    public int updateTbVehicle(TbVehicle tbVehicle);

    /**
     * 批量删除car
     *
     * @param vIds 需要删除的carID
     * @return 结果
     */
    public int deleteTbVehicleByIds(Long[] vIds);

    /**
     * 删除car信息
     *
     * @param vId carID
     * @return 结果
     */
    public int deleteTbVehicleById(Long vId);

    /**
     * 插入车辆证件照
     * @param tbVehicleFile
     * @return
     */
    int insertVehicleFile(TbVehicleFile tbVehicleFile);

    /**
     * 删除证件照
     * @param id
     * @return
     */
    int delVehicleFileById(Long id);

    /**
     * 根据文件Id获取文件对象
     * @param id
     * @return
     */
    TbVehicleFile findVehicleFieById(Long id);

    /**
     * 查询车辆
     * @param maxToday
     * @param minToday
     * @return
     */
    int queryListByTodayNum( String maxToday, String minToday);

    /**
     * 修改 保存 车辆
     * @param vehicleVo
     */
    int saveAndUpdate(VehicleInfoVo vehicleVo);

    /**
     *关联用户车辆
     * @param vehicleApplicantRequest
     * @return
     */
    VehicleEntity relation(VehicleApplicantRequest vehicleApplicantRequest);

    /**
     * 根据车主Id 车架 or 车牌 删除用户关联关系
     * @param vehicleApplicantRequest
     * @return
     */
    int delRelation(VehicleApplicantRequest vehicleApplicantRequest);

    /**
     * 查询维修履历工单
     * @param licenseNo
     * @return
     */
    String findMaintenanceRecord(String licenseNo);

    /**
     * 查询维修履历详情
     * @param request
     * @return
     */
    Map<String, Object> maintenanceRecordDetail(MaintenanceDetailRequest request);

    /**
     * 上传车辆证件照| type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other
     * @param fileVo
     * @param file
     * @return
     */
    Map<String, Object> uploadVehicleFile(VehicleFileVo fileVo, MultipartFile file) throws UnsupportedEncodingException;


    String productList();

    /**
     * 根据车牌or车架查找车辆
     * @param vehicleNo rackNo flag
     * @return
     */
    List<TbVehicle> findByVehicleNoAndRackNO(String vehicleNo, String rackNo, String flag);

    /**
     * 根据车牌 and 车架 删除车辆
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    int deleteVehicleByVehicleNoAndRackNo(String vehicleNo, String rackNo);

    /**
     * 插入车辆保险项
     * @param vehicleInsrncEntity
     * @return
     */
    int insertVehicleInsrnc(VehicleInsrncEntity vehicleInsrncEntity);

    int deleteByVehicleNoAndRackNo(String vehicleNo, String rackNo);

    int saveVehicle(VehicleReq vehicleVo);

    /**
     *上传车辆证件照| type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other
     * @param fileVo
     * @param file
     * @return
     */
    Map<String, Object> saveVehicleFile(VehicleFileVo fileVo, MultipartFile file) throws IOException;


    /**
     * 根据车架 or 车牌查找车辆详情
     * @param frameNo
     * @return
     */
    VehicleDetails findDetailsByFrameNoAndLicenseNo(String frameNo, String licenseNo);

    /**
     * 查询车主车辆年审/保险时间
     * @param ownerId
     * @return
     */
    List<VehicleInspectVo> vehicleInspect(String ownerId);

    /**
     * 根据车牌和车架查找
     * @param licenseNo
     * @param frameNo
     * @return
     */
    List<TbVehicle> findVehicleByLicenseNoAndFrameNo(String licenseNo, String frameNo);
}
