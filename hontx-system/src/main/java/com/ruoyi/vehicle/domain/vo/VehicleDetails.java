package com.ruoyi.vehicle.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.vehicle.domain.TbVehicleFile;
import lombok.Data;



@Data
public class VehicleDetails extends VehicleInfoVo {
    private TbVehicleFile files;


}
