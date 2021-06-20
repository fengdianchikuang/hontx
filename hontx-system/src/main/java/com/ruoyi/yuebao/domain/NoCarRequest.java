package com.ruoyi.yuebao.domain;

import com.ruoyi.vehicle.domain.vo.VehicleInfoVo;
import com.ruoyi.yuebao.domain.req.BaseInfoVo;
import lombok.Data;

@Data
public class NoCarRequest {
    private BaseInfoVo baseInfo;
    private VehicleInfoVo vehicleInfo;
}
