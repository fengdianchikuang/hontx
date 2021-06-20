package com.ruoyi.vehicle.service.impl;

import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.VehicleOwner;
import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import com.ruoyi.vehicle.domain.vo.VehicleVo;
import com.ruoyi.vehicle.mapper.TbVehicleMapper;
import com.ruoyi.vehicle.mapper.VehicleOwnerMapper;
import com.ruoyi.vehicle.service.VehicleOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 车辆 关联
 */
@Service
public class VehicleOwnerServiceImpl implements VehicleOwnerService {
    @Autowired
    private VehicleOwnerMapper vehicleOwnerMapper;

    @Autowired
    private TbVehicleMapper tbVehicleMapper;

    @Override
    public List<VehicleInfoVo> findByOwnerId(String ownerId) {
        //获取车辆关系人表
        List<VehicleOwner> vehicleOwnerList = vehicleOwnerMapper.findByOwnerId(ownerId);
        if(vehicleOwnerList != null && vehicleOwnerList.size() > 0){
            List<String> frameNos = vehicleOwnerList.stream().map(o -> o.getFrameNo()).collect(Collectors.toList());
            List<VehicleInfoVo> list  = tbVehicleMapper.findVehicleByFrameNos(frameNos);
            return list;
        }
        return new ArrayList<VehicleInfoVo>();
    }
}
