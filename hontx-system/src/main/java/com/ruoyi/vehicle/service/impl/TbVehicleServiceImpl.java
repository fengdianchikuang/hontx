package com.ruoyi.vehicle.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.InsrncOrder.domain.vo.InsrncOrderVo;
import com.ruoyi.InsrncOrder.mapper.InsrncOrderMapper;
import com.ruoyi.ai.domain.vo.DrivingFrontVo;
import com.ruoyi.ai.domain.vo.IdCardVo;
import com.ruoyi.ai.service.AiService;
import com.ruoyi.common.constant.BaiDuConstant;
import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.exception.APIException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.TbVehicleFile;
import com.ruoyi.vehicle.domain.VehicleInsrncEntity;
import com.ruoyi.vehicle.domain.VehicleOwner;
import com.ruoyi.vehicle.domain.req.MaintenanceDetailRequest;
import com.ruoyi.vehicle.domain.req.VehicleApplicantRequest;
import com.ruoyi.vehicle.domain.req.VehicleFileVo;
import com.ruoyi.vehicle.domain.req.VehicleReq;
import com.ruoyi.vehicle.domain.vo.*;
import com.ruoyi.vehicle.mapper.TbVehicleMapper;
import com.ruoyi.vehicle.mapper.VehicleOwnerMapper;
import com.ruoyi.vehicle.service.ITbVehicleService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * carService业务层处理
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Service
public class TbVehicleServiceImpl implements ITbVehicleService
{
    public static final MediaType APPLICATION_JSON_CHARSET_UTF_8 = MediaType.get("application/json;charset=utf8");
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
            .callTimeout(60, TimeUnit.SECONDS)    // 设置请求超时时间
            .connectTimeout(60, TimeUnit.SECONDS)    // 设置连接超时时间
            .readTimeout(60, TimeUnit.SECONDS)    // 设置socket读写超时时间
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    @Autowired
    private TbVehicleMapper tbVehicleMapper;

    @Autowired
    private VehicleOwnerMapper vehicleOwnerMapper;

    @Autowired
    private AiService aiService;

    @Autowired
    private InsrncOrderMapper insrncOrderMapper;





    /**
     * 查询car
     *
     * @param vId carID
     * @return car
     */
    @Override
    public VehicleVo selectTbVehicleById(Long vId)
    {
        return tbVehicleMapper.selectTbVehicleById(vId);
    }

    /**
     * 查询car列表
     *
     * @param tbVehicle car
     * @return car
     */
    @Override
    public List<TbVehicle> selectTbVehicleList(TbVehicle tbVehicle)
    {

        return tbVehicleMapper.selectTbVehicleList(tbVehicle);
    }

    /**
     * 新增car
     *
     * @param tbVehicle car
     * @return 结果
     */
    @Override
    public int insertTbVehicle(TbVehicle tbVehicle)
    {
        tbVehicle.setCreateTime(new Date());
        return tbVehicleMapper.insertTbVehicle(tbVehicle);
    }

    /**
     * 修改car
     *
     * @param tbVehicle car
     * @return 结果
     */
    @Override
    public int updateTbVehicle(TbVehicle tbVehicle)
    {
        tbVehicle.setUpdateTime(new Date());
        return tbVehicleMapper.updateTbVehicle(tbVehicle);
    }

    /**
     * 批量删除car
     *
     * @param vIds 需要删除的carID
     * @return 结果
     */
    @Override
    public int deleteTbVehicleByIds(Long[] vIds)
    {
        return tbVehicleMapper.deleteTbVehicleByIds(vIds);
    }

    /**
     * 删除car信息
     *
     * @param vId carID
     * @return 结果
     */
    @Override
    public int deleteTbVehicleById(Long vId)
    {
        return tbVehicleMapper.deleteTbVehicleById(vId);
    }

    /**
     * 插入车辆证件照
     * @param tbVehicleFile
     * @return
     */
    @Override
    public int insertVehicleFile(TbVehicleFile tbVehicleFile) {
        return tbVehicleMapper.insertVehicleFile(tbVehicleFile);
    }

    /**
     * 根据id删除证件照
     * @param id
     * @return
     */
    @Override
    public int delVehicleFileById(Long id) {
        return tbVehicleMapper.delVehicleFileById(id);
    }

    @Override
    public TbVehicleFile findVehicleFieById(Long id) {
        return tbVehicleMapper.findVehicleFieById(id);
    }

    /**
     * 查询车辆数据,按时间
     * @param maxToday
     * @param minToday
     * @return
     */
    @Override
    public int queryListByTodayNum(String maxToday, String minToday) {
        return tbVehicleMapper.queryListByTodayNum(maxToday,minToday);
    }

    /**
     * 新增 or 修改车辆
     * @param vehicleVo
     */
    @Override
    public int saveAndUpdate(VehicleInfoVo vehicleVo){
        if(StringUtils.isNotEmpty(vehicleVo.getFrameNo()) || StringUtils.isNotEmpty(vehicleVo.getLicenseNo())){
            //根据车牌或车架查找车辆信息
            VehicleEntity vehicleEntity = tbVehicleMapper.selectOneByFrameNoOrLicenseNo(vehicleVo);
            //新增
            if(vehicleEntity == null){
                return tbVehicleMapper.insertVehicleInfo(vehicleVo);
            //修改
            }else {
                return tbVehicleMapper.updateByFrameNoOrLicenseNo(vehicleVo);
            }
        }
        return 0;
    }

    /**
     * 关联用户车辆
     * @param vehicleApplicantRequest
     * @return
     */
    @Override
    public VehicleEntity relation(VehicleApplicantRequest vehicleApplicantRequest) {
        //根据车牌或车架查询车辆信息
        VehicleInfoVo vehicleInfoVo = new VehicleInfoVo();
        vehicleInfoVo.setFrameNo(vehicleApplicantRequest.getFrameNo());
        vehicleInfoVo.setLicenseNo(vehicleApplicantRequest.getLicenseNo());
        VehicleEntity vehicleEntity = tbVehicleMapper.selectOneByFrameNoOrLicenseNo(vehicleInfoVo);
        if(vehicleEntity != null){
            //查找用户关联车辆
            VehicleOwner vehicleOwner = vehicleOwnerMapper.findOwnerByOwnerAndFrameNoOrLicenseNo(vehicleApplicantRequest);
            //车辆与用户没有关联关系，建立关联
            if(vehicleOwner == null){
                vehicleOwner = new VehicleOwner();
                vehicleOwner.setFrameNo(vehicleEntity.getFrameNo());
                vehicleOwner.setLicenseNo(vehicleEntity.getLicenseNo());
                vehicleOwner.setOwnerId(vehicleApplicantRequest.getOwnerId());
                vehicleOwner.setCreateTime(new Date());
                int insert = vehicleOwnerMapper.insert(vehicleOwner);
            }else {
                int update = vehicleOwnerMapper.updateById(vehicleOwner);
            }
        }

        return vehicleEntity;
    }

    /**
     * 删除用户车辆关联关系
     * @param vehicleApplicantRequest
     * @return
     */
    @Override
    public int delRelation(VehicleApplicantRequest vehicleApplicantRequest) {
       int result = vehicleOwnerMapper.delRelation(vehicleApplicantRequest);
        return result;
    }

    /**
     * 根据车牌查找维修履历
     * @param licenseNo
     * @return
     */
    @Override
    public String findMaintenanceRecord(String licenseNo) {
        Map<String,String> params = new HashMap<>();
        params.put("modName","get_WorkBillEcl");
        params.put("cusCarNo",licenseNo);
        params.put("shopId","0");
        String url = "https://xpbhd.kbiso.com/postData.ashx?action=getData";
        String result = OkHttpUtils.postMap(url, params, null);
        return result;
    }

    /**
     * 查询维修履历详情
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> maintenanceRecordDetail(MaintenanceDetailRequest request) {
        Map<String,String> params = new HashMap<>();
        params.put("modName","workOrder.getWorkProList");
        params.put("jobno",request.getJobNo());
        params.put("shopId",request.getShopId());
        String url = "https://xpbhd.kbiso.com/postData.ashx?action=getData";
        Map<String,Object> result = new HashMap<>();
        String project = OkHttpUtils.postMap(url, params, null);
        result.put("project", JSONObject.parse(project));

        params.put("modName","workOrder.getWorkPartListA");
        String part = OkHttpUtils.postMap(url, params, null);
        result.put("part",JSONObject.parse(part));
        return result;
    }

    /**
     * 上传车辆证件照| type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other
     * @param fileVo
     * @param file
     * @return
     */
    @Override
    public Map<String, Object> uploadVehicleFile(VehicleFileVo fileVo, MultipartFile file) throws UnsupportedEncodingException {
        // type_XXXXXXXX.jpg
        IdCardVo idCardVo = null;
        Map<String,Object> data = new HashMap<>();
        if(fileVo.getFlg() != 1){
            switch (fileVo.getType()){
                case "00":
                    idCardVo = aiService.distinguishIdCard(file, BaiDuConstant.ID_CARD_Front);
                if(idCardVo != null){
                    String result = URLEncoder.encode(JSON.toJSONString(idCardVo),"UTF-8");

                }
                    break;
                case "02":
                    DrivingFrontVo drivingFrontVo = aiService.distinguDriving(file,BaiDuConstant.Driving_Front);
                    if(drivingFrontVo != null){
                        String result = URLEncoder.encode(JSON.toJSONString(drivingFrontVo),"UTF-8");
                        data.put("02",result);
                        fileVo.setRackNo(drivingFrontVo.getFrameNo());
                        fileVo.setVehicleNo(drivingFrontVo.getLicenseNo());
                    }
                    break;

            }
        }
        return null;
    }





    /**
     * 获取产品列表
     * @return
     */
    @Override
    public String productList() {
        long nowTime = System.currentTimeMillis();
        String url = "https://openapi.12354.com/merchant/product/list";
//        String url = "https://openapi.12354.com/merchant/sales/list";

        JSONObject jsonObject = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("access_key","BTEfGg8o6nHUEmrK");
        header.put("access_secret","7Q2OX3DOP4VEdqZpz1nDPaBJKvTJ73yY");
        header.put("request_at",nowTime+"");
        header.put("business_no","jq"+nowTime);
        jsonObject.put("header",header);
        JSONObject body = new JSONObject();
        body.put("page_size","10");
        body.put("page","1");
        jsonObject.put("body",body);
        RequestBody requestBody = RequestBody.create(APPLICATION_JSON_CHARSET_UTF_8, jsonObject.toJSONString());
        Request postRequest = null;
        postRequest = new Request.Builder()
                .post(requestBody)
//                .addHeader("access_key", "BTEfGg8o6nHUEmrK")
//                .addHeader("access_secret", "yw0Z01EedcxOrVGh16ABVgjPx8OrW7qE")
//                .addHeader("request_at", nowTime+"")
//                .addHeader("business_no", "jq"+nowTime)
                .url(url)
                .build();
        Call call = OK_HTTP_CLIENT.newCall(postRequest);
        try (Response postResponse = call.execute()) {
            return handle(postResponse);
        } catch (Exception e) {
            throw  new APIException(1, "网络异常,请重新再试");
        }

    }

    /**
     * 根据车牌or车架 查找车辆
     * @param vehicleNo rackNo flag
     * @param rackNo
     * @param flag
     * @return
     */
    @Override
    public List<TbVehicle> findByVehicleNoAndRackNO(String vehicleNo, String rackNo, String flag) {
        //自动报价 车辆车主为空
        if("0".equals(flag)){
            return  tbVehicleMapper.findByVehicleNoAndRackNoAuto(vehicleNo,rackNo);
        }else {
            return tbVehicleMapper.findByVehicleNoAndRackNoManual(vehicleNo,rackNo);
        }

    }

    /**
     * 根据车牌 车架 删除 （手动添加）
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    @Override
    public int deleteVehicleByVehicleNoAndRackNo(String vehicleNo, String rackNo) {
        return tbVehicleMapper.deleteVehicleByVehicleNoAndRackNo(vehicleNo,rackNo);
    }

    /**
     * 插入车辆保险项
     * @param vehicleInsrncEntity
     * @return
     */
    @Override
    public int insertVehicleInsrnc(VehicleInsrncEntity vehicleInsrncEntity) {
        return tbVehicleMapper.insertVehicleInsrnc(vehicleInsrncEntity);
    }

    /**
     * 删除车辆保险项
     * @param vehicleNo
     * @param rackNo
     * @return
     */
    @Override
    public int deleteByVehicleNoAndRackNo(String vehicleNo, String rackNo) {
        return tbVehicleMapper.deleteByVehicleNoAndRackNo(vehicleNo,rackNo);
    }

    /**
     * 修改保存车辆
     * @param vehicleVo
     * @return
     */
    @Override
    public int saveVehicle(VehicleReq vehicleVo) {
        TbVehicle vehicle = tbVehicleMapper.findByFrameNoAndLicenseNo(vehicleVo.getFrameNo(),vehicleVo.getLicenseNo());
        //根据车主Id 和 车架号查找 车辆关系表信息
        if(vehicle != null){
          VehicleOwner vehicleOwner = vehicleOwnerMapper.findByFrameNoAndOwnerId(vehicleVo.getOwnerId(),vehicleVo.getFrameNo());
            if(vehicleOwner == null){
                vehicleOwner = new VehicleOwner();
                vehicleOwner.setOwnerId(Long.valueOf(vehicleVo.getOwnerId()));
                vehicleOwner.setLicenseNo(vehicleVo.getLicenseNo());
                vehicleOwner.setLicenseNo(vehicleVo.getLicenseNo());
                vehicleOwner.setFrameNo(vehicleVo.getFrameNo());
                vehicleOwnerMapper.insert(vehicleOwner);
            }
            vehicleVo.setId(vehicle.getvId());
           return tbVehicleMapper.updateById(vehicleVo);
        }else {
            //车辆关系人
            VehicleOwner vehicleOwner = new VehicleOwner();
            vehicleOwner.setOwnerId(Long.valueOf(vehicleVo.getOwnerId()));
            vehicleOwner.setLicenseNo(vehicleVo.getLicenseNo());
            vehicleOwner.setLicenseNo(vehicleVo.getLicenseNo());
            vehicleOwner.setFrameNo(vehicleVo.getFrameNo());
            vehicleOwnerMapper.insert(vehicleOwner);
            return tbVehicleMapper.insert(vehicleVo);
        }

    }

    /**
     * 上传车辆证件照
     * @param fileVo
     * @param file
     * @return
     */
    @Override
    public Map<String, Object> saveVehicleFile(VehicleFileVo fileVo, MultipartFile file) throws IOException {
        //图片名称 type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other
        // type_XXXXXXXX.jpg
        Map<String,Object> data = new HashMap<>();
        IdCardVo idCardVo = null;
        if(fileVo.getFlg() != 1){
            //        type:00 身份证正面 01身份证背面 02行驶证正本 03行驶证副本 04 other
            switch (fileVo.getType()){
                case "00":
                    idCardVo = aiService.distinguishIdCard(file, BaiDuConstant.ID_CARD_Front);
                    if(idCardVo != null){
                        String result = URLEncoder.encode(JSON.toJSONString(idCardVo),"UTF-8");
                        data.put("00",result);
                    }
                    break;
                case "02":
                    DrivingFrontVo drivingFrontVo = aiService.distinguDriving(file,BaiDuConstant.Driving_Front);
                    if(drivingFrontVo != null){
                        String result = URLEncoder.encode(JSON.toJSONString(drivingFrontVo),"UTF-8");
                        data.put("02",result);
                        fileVo.setRackNo(drivingFrontVo.getFrameNo());
                        fileVo.setVehicleNo(drivingFrontVo.getLicenseNo());
                    }
                    break;
                case "03":
                    DrivingFrontVo drivingBackVo = aiService.distinguDriving(file,BaiDuConstant.Driving_back);
                    if(drivingBackVo != null){
                        String result = URLEncoder.encode(JSON.toJSONString(drivingBackVo),"UTF-8");
                        data.put("03",result);
                    }
                    break;
            }
        }
        List<TbVehicleFile> oldFiles = tbVehicleMapper.findVehicleFileByFrameNo(fileVo.getRackNo());
        for (TbVehicleFile oldFile : oldFiles) {
            //通类型的，删除，覆盖
            if(fileVo.getType().equals(oldFile.getType())){
                //存储阿里云,删除，包含/home/file/代表以前存放服务器上(已废弃)
                if(oldFile.getPath().contains("/home/file/")){
                    FileUploadUtils.deleteFileOne(OssConstants.bucketName,oldFile.getPath());
                }
                int res = tbVehicleMapper.deleteVehicleFileById(oldFile.getvId());
                return handleUploadFile(file,fileVo,data);
            }else {
               return handleUploadFile(file,fileVo,data);
            }
        }

        return handleUploadFile(file,fileVo,data);
    }

    /**
     * 根据车架查找车辆详情(包含证件照)
     * @param frameNo
     * @return
     */
    @Override
    public VehicleDetails findDetailsByFrameNoAndLicenseNo(String frameNo,String licenseNo) {
        return tbVehicleMapper.findVehicleAndFilesByFrameNoAndLicenseNo(frameNo, licenseNo);
    }

    /**
     * 查询车主车辆年审/保险时间
     * @param ownerId
     * @return
     */
    @Override
    public List<VehicleInspectVo> vehicleInspect(String ownerId) {
        //查找车辆关系人信息
        List<VehicleOwner> vehicleOwners = vehicleOwnerMapper.findByOwnerId(ownerId);
        if(vehicleOwners == null || vehicleOwners.size() < 0){
            return new ArrayList<>();
        }
        //查找车辆信息
        List<String> frameNos = vehicleOwners.stream().map(o -> o.getFrameNo()).collect(Collectors.toList());
        List<VehicleInfoVo> vehicles = tbVehicleMapper.findVehicleByFrameNos(frameNos);
        if(vehicles == null || vehicles.size() < 0){
            return new ArrayList<>();
        }
        for (VehicleInfoVo vehicle : vehicles) {
            VehicleInspectVo vehicleInspectVo = new VehicleInspectVo();
            //检查商业/交强险到期日期
            checkInsrncExpire(vehicle,vehicleInspectVo);
        }
        return null;
    }

    @Override
    public List<TbVehicle> findVehicleByLicenseNoAndFrameNo(String licenseNo, String frameNo) {
        return tbVehicleMapper.findVehicleByLicenseNoAndFrameNo(licenseNo,frameNo);
    }

    /**
     * 检查保险到期时间
     * @param vehicle
     * @param vehicleInspectVo
     */
    private void checkInsrncExpire(VehicleInfoVo vehicle, VehicleInspectVo vehicleInspectVo) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        int nowYear = LocalDateTime.now().getYear();
        //app存储在 tb_vehicle 里, 小程序 tb_insrnc_order里
        //交强险
        if(vehicle.getInsrncEndJq() != null){
            Date syInsrncExpire = vehicle.getInsrncEndJq();
            String format = df.format(syInsrncExpire);
            LocalDateTime syExpire = LocalDateTime.parse(format, dtf);
            if(syExpire.getYear() == nowYear){
                vehicleInspectVo.setSyInsrncExpire(df.format(vehicle.getInsrncEndJq()));
            }

        }
        //商业险
        if(vehicle.getInsrncEndSy() != null){
            Date insrncEndSy = vehicle.getInsrncEndSy();
            String format = df.format(insrncEndSy);
            LocalDateTime syExpire = LocalDateTime.parse(format, dtf);
            if(syExpire.getYear() == nowYear){
                vehicleInspectVo.setSyInsrncExpire(df.format(vehicle.getInsrncEndJq()));
            }
            vehicleInspectVo.setJqINsrncExpire(df.format(vehicle.getInsrncEndJq()));
        }
        //两个都为空,查询tb_insrc_ordery
        if(StringUtils.isEmpty(vehicleInspectVo.getJqINsrncExpire()) && StringUtils.isEmpty(vehicleInspectVo.getSyInsrncExpire())){
            List<InsrncOrderVo> insrncOrders = insrncOrderMapper.findByFrameNo(vehicle.getFrameNo());
            if(insrncOrders != null && insrncOrders.size() > 0){
                //获取已承保的信息
                List<InsrncOrderVo> lists = insrncOrders.stream().filter(o -> o.getPolicyStatus() == 8).collect(Collectors.toList());
                InsrncOrderVo insrncOrderVo = lists.get(lists.size() - 1);
//                if(insrncOrderVo.getPolicyStatus())
                if(insrncOrderVo.getTciEndDateTime()!= null){
                    Date tciEndDateTime = insrncOrderVo.getTciEndDateTime();
                    String jqEnd = df.format(tciEndDateTime);
                    vehicleInspectVo.setJqINsrncExpire(jqEnd);
                }
                if(insrncOrderVo.getVciEndDate() == null){
                    Date vciEndDate = insrncOrderVo.getVciEndDate();
                    String vciEnd = df.format(vciEndDate);
                    vehicleInspectVo.setSyInsrncExpire(vciEnd);
                }
            }

        }

    }

    //    上传车辆文件
    private Map<String, Object> handleUploadFile(MultipartFile file, VehicleFileVo fileVo,Map<String, Object> data) throws IOException {
        //文件名 车牌_车架
        String vehicleFolderName = FileUploadUtils.getVehicleFolderName(fileVo.getVehicleNo(),fileVo.getRackNo());
        String filePrefix = OssConstants.vehiclePath+vehicleFolderName;
        String vehicleFileName = FileUploadUtils.getVehicleFileName(file, fileVo.getType());
        String path = filePrefix + vehicleFileName;
        FileUploadUtils.putObjectToAliOss(OssConstants.bucketName, filePrefix+vehicleFileName,file.getInputStream());
        //插入新对象
        TbVehicleFile vehicle = new TbVehicleFile();
        vehicle.setPath(path);
        vehicle.setRackNo(fileVo.getRackNo());
        vehicle.setVehicleNo(fileVo.getVehicleNo());
        vehicle.setType(fileVo.getType());
        vehicle.setCreateTime(new Date());
        vehicle.setFileName(vehicleFileName);
        tbVehicleMapper.insertVehicleFile(vehicle);
        data.put("file",vehicle);
        return data;
    }

    private static String handle(Response httpResponse) throws IOException {
        // 从响应模型中获取响应实体
        okhttp3.ResponseBody responseBody = httpResponse.body();

        if (httpResponse.isSuccessful()) {
            if (responseBody != null) {
                return responseBody.string();
            } else {
                throw new APIException(1, "网络异常,请重新再试");
            }
        } else {

            throw new APIException(1, "网络异常,请重新再试");
        }

    }


}
