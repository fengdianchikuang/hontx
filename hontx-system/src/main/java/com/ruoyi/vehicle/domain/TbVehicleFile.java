package com.ruoyi.vehicle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * vehicle对象 tb_vehicle_file
 * 
 * @author ruoyi
 * @date 2021-04-09
 */
public class TbVehicleFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long vId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vehicleNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String rackNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String path;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date vCreateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date vUpdateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String vQueryId;

    public void setvId(Long vId) 
    {
        this.vId = vId;
    }

    public Long getvId() 
    {
        return vId;
    }
    public void setVehicleNo(String vehicleNo) 
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() 
    {
        return vehicleNo;
    }
    public void setRackNo(String rackNo) 
    {
        this.rackNo = rackNo;
    }

    public String getRackNo() 
    {
        return rackNo;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setvCreateTime(Date vCreateTime) 
    {
        this.vCreateTime = vCreateTime;
    }

    public Date getvCreateTime() 
    {
        return vCreateTime;
    }
    public void setvUpdateTime(Date vUpdateTime) 
    {
        this.vUpdateTime = vUpdateTime;
    }

    public Date getvUpdateTime() 
    {
        return vUpdateTime;
    }
    public void setFileCode(String fileCode) 
    {
        this.fileCode = fileCode;
    }

    public String getFileCode() 
    {
        return fileCode;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setvQueryId(String vQueryId) 
    {
        this.vQueryId = vQueryId;
    }

    public String getvQueryId() 
    {
        return vQueryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vId", getvId())
            .append("vehicleNo", getVehicleNo())
            .append("rackNo", getRackNo())
            .append("type", getType())
            .append("fileName", getFileName())
            .append("path", getPath())
            .append("vCreateTime", getvCreateTime())
            .append("vUpdateTime", getvUpdateTime())
            .append("fileCode", getFileCode())
            .append("status", getStatus())
            .append("vQueryId", getvQueryId())
            .toString();
    }
}
