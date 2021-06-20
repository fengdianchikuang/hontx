package com.ruoyi.system.domain;



import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 过眼云烟
 * @since 2021-03-15
 */

public class VehicleFile implements Serializable {

    private static final long serialVersionUID = 1L;

//    @TableId(value = "v_id", type = IdType.AUTO)
    private Long vId;

//    @ApiModelProperty(value = "车牌号")
    private String vehicleNo;

//    @ApiModelProperty(value = "车架号/VIN码")
    private String rackNo;

//    @ApiModelProperty(value = "类型：00身份证正，01身份证背，03行驶证正，04行驶证副，05other")
    private String type;

//    @ApiModelProperty(value = "文件名")
    private String fileName;

//    @ApiModelProperty(value = "路径")
    private String path;

//    @ApiModelProperty(value = "web路径")
    private String url;

//    @ApiModelProperty(value = "创建时间")
    private LocalDateTime vCreateTime;

//    @ApiModelProperty(value = "修改时间")
    private LocalDateTime vUpdateTime;

//    @ApiModelProperty(value = "识别返回码")
    private String fileCode;

//    @ApiModelProperty(value = "识别状态")
    private String status;

//    @ApiModelProperty(value = "询价单号")
    private String vQueryId;

    public Long getvId() {
        return vId;
    }

    public void setvId(Long vId) {
        this.vId = vId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getvCreateTime() {
        return vCreateTime;
    }

    public void setvCreateTime(LocalDateTime vCreateTime) {
        this.vCreateTime = vCreateTime;
    }

    public LocalDateTime getvUpdateTime() {
        return vUpdateTime;
    }

    public void setvUpdateTime(LocalDateTime vUpdateTime) {
        this.vUpdateTime = vUpdateTime;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getvQueryId() {
        return vQueryId;
    }

    public void setvQueryId(String vQueryId) {
        this.vQueryId = vQueryId;
    }
}
