package com.ruoyi.vehicle.domain.vo;

import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.TbVehicleFile;

import java.util.List;

public class VehicleVo extends TbVehicle {
    private List<TbVehicleFile> files;

    public List<TbVehicleFile> getFiles() {
        return files;
    }

    public void setFiles(List<TbVehicleFile> files) {
        this.files = files;
    }
}
