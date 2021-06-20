package com.ruoyi.vehicle.service;

import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import com.ruoyi.vehicle.domain.vo.VehicleVo;

import java.util.List;

public interface VehicleOwnerService {
    /**
     * 查找用户名下所关联的车辆
     * @param ownerId
     * @return
     */
    List<VehicleInfoVo> findByOwnerId(String ownerId);
}
