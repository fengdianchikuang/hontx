package com.ruoyi.vehicle.mapper;


import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.TbVehicleFile;
import com.ruoyi.vehicle.domain.VehicleInsrncEntity;
import com.ruoyi.vehicle.domain.VehicleOwner;
import com.ruoyi.vehicle.domain.req.VehicleApplicantRequest;
import com.ruoyi.vehicle.domain.req.VehicleReq;
import com.ruoyi.vehicle.domain.vo.VehicleDetails;
import com.ruoyi.vehicle.domain.vo.VehicleEntity;
import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import com.ruoyi.vehicle.domain.vo.VehicleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * carMapper接口
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Mapper
public interface TbVehicleMapper
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
     * 删除car
     *
     * @param vId carID
     * @return 结果
     */
    public int deleteTbVehicleById(Long vId);

    /**
     * 批量删除car
     *
     * @param vIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbVehicleByIds(Long[] vIds);

    /**
     * 插入车辆证件照
     * @param tbVehicleFile
     * @return
     */
    int insertVehicleFile(TbVehicleFile tbVehicleFile);

    /**
     * 根据id删除zhengjian照
     * @param id
     * @return
     */
    int delVehicleFileById(Long id);

    /**
     * 根据id获取文件对象
     * @param id
     * @return
     */
    TbVehicleFile findVehicleFieById(Long id);

    /**
     * 查询车辆数,按时间
     * @param maxToday
     * @param minToday
     * @return
     */
    int queryListByTodayNum(@Param("maxToday") String maxToday, @Param("minToday") String minToday);

    /**
     * 根据车牌或车架查找车辆
     * @param vehicleVo
     * @return
     */
    VehicleEntity selectOneByFrameNoOrLicenseNo(VehicleInfoVo vehicleVo);

    /**
     * 新增车辆(悦保字段)
     * @param vehicleVo
     * @return
     */
    int insertVehicleInfo(VehicleInfoVo vehicleVo);

    /**
     * 修改车辆(悦保字段)根据车牌或车架
     * @param vehicleVo
     * @return
     */
    int updateByFrameNoOrLicenseNo(VehicleInfoVo vehicleVo);


    /**
     *根据车牌车架查找车辆 (自动)
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    List<TbVehicle> findByVehicleNoAndRackNoAuto(@Param("vehicleNo") String vehicleNo, @Param("rackNo") String rackNo);

    /**
     * 根据车牌车架查找车辆 (手动)
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    List<TbVehicle> findByVehicleNoAndRackNoManual(@Param("vehicleNo") String vehicleNo, @Param("rackNo") String rackNo);

    /**
     * 根据车牌 车架 删除车辆
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    int deleteVehicleByVehicleNoAndRackNo(@Param("vehicleNo") String vehicleNo, @Param("rackNo") String rackNo);

    /**
     * 插入车辆保险项
     * @param vehicleInsrncEntity
     * @return
     */
    int insertVehicleInsrnc(VehicleInsrncEntity vehicleInsrncEntity);

    /**
     * 根据车牌or车架删除
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    int deleteByVehicleNoAndRackNo(@Param("vehicleNo") String vehicleNo, @Param("rackNo") String rackNo);

    TbVehicle findByFrameNoAndLicenseNo(@Param("frameNo") String frameNo, @Param("licenseNo") String licenseNo);

    /**
     * 根据车辆Id 修改
     * @param vehicleVo
     * @return
     */
    int updateById(VehicleReq vehicleVo);

    /**
     * 插入小程序添加车辆
     * @param vehicleVo
     * @return
     */
    int insert(VehicleReq vehicleVo);

    /**
     * 根据车架Or车架查找车辆证件照
     * @param rackNo
     * @return
     */
    List<TbVehicleFile> findVehicleFileByFrameNo(String rackNo);

    /**
     * 根据id删除车辆文件
     * @param id
     * @return
     */
    int deleteVehicleFileById(Long id);

    /**
     * 根据车架号(多个)查找
     * @param frameNos
     * @return
     */
    List<VehicleInfoVo> findVehicleByFrameNos(List<String> frameNos);

    /**
     * 根据车牌车架 查找 车辆详情(包含证件照)
     * @param frameNo
     * @param licenseNo
     * @return
     */
    VehicleDetails findVehicleAndFilesByFrameNoAndLicenseNo(@Param("frameNo") String frameNo, @Param("licenseNo") String licenseNo);

    /**
     * 查找车辆 根据车牌和车架
     * @param licenseNo
     * @param frameNo
     * @return
     */
    List<TbVehicle> findVehicleByLicenseNoAndFrameNo(@Param("licenseNo") String licenseNo, @Param("frameNo") String frameNo);
}


