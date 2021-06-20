package com.ruoyi.vehicle.mapper;

import com.ruoyi.vehicle.domain.VehicleOwner;
import com.ruoyi.vehicle.domain.req.VehicleApplicantRequest;
import com.ruoyi.vehicle.domain.vo.VehicleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleOwnerMapper {
    /**
     * 根据车主Id 车架号 车牌 查找关联信息
     * @param vehicleApplicantRequest
     * @return
     */
    VehicleOwner findOwnerByOwnerAndFrameNoOrLicenseNo(VehicleApplicantRequest vehicleApplicantRequest);

    /**
     * 新增车辆用户关系
     * @param vehicleOwner
     * @return
     */
    int insert(VehicleOwner vehicleOwner);

    /**
     * 根据id修改车辆信息
     * @param vehicleOwner
     * @return
     */
    int updateById(VehicleOwner vehicleOwner);

    /**
     * 根据车主Id 和 车牌 or 车架删除用户关联
     * @param vehicleApplicantRequest
     * @return
     */
    int delRelation(VehicleApplicantRequest vehicleApplicantRequest);

    /**
     * 根据车主Id and  车架 查找用户车辆关系
     * @param ownerId
     * @param frameNo
     * @return
     */
    VehicleOwner findByFrameNoAndOwnerId(@Param("ownerId") String ownerId,@Param("frameNo") String frameNo);

    VehicleEntity relation(VehicleApplicantRequest vehicleApplicantRequest);

    /**
     * 根据车主Id查找关联表
     * @param ownerId
     * @return
     */
    List<VehicleOwner> findByOwnerId(String ownerId);
}
