package com.ruoyi.ai.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.ai.domain.vo.DrivingFrontVo;
import com.ruoyi.ai.domain.vo.IdCardVo;
import com.ruoyi.ai.service.AiService;
import com.ruoyi.common.constant.BaiDuConstant;
import com.ruoyi.common.exception.APIException;
import com.ruoyi.common.exception.AiBaiDuException;
import com.ruoyi.common.utils.base64.Base64Util;
import com.ruoyi.common.utils.http.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import okhttp3.*;
import org.apache.commons.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@Data
@Slf4j
public class AiServiceImpl implements AiService {
    //请求url
    @Value("${baidu.ai.idcord}")
    private String aiIdCord;

    @Value("${baidu.grant_type}")
    private String grantType;

    @Value("${baidu.client_id}")
    private String clientId;

    @Value("${baidu.client_secret}")
    private String clientSecret;

    @Value("${baidu.url}")
    private String url;

    /**
     * 获取百度认证token
     * @return
     * @throws IOException
     */
    @Override
    public String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("grant_type", grantType)
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .build();
        Request request = new Request.Builder()
                .url(url + "/token")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        String result = call.execute().body().string();
        JSONObject jsonObject = JSONObject.parseObject(result);
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }

    /**
     *获取身份识别
     * @param file
     * @param id_card_side
     * @return
     */
    @Override
    public IdCardVo distinguishIdCard(MultipartFile file, String id_card_side) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        byte[] imgData = new byte[0];
        try {
            imgData = file.getBytes();
        } catch (IOException e) {
            throw new APIException(100,"身份证正面文件读取异常,请重新上传");
        }
        String imgStr = Base64Util.encode(imgData);
        String imgParam = null;
        try {
            imgParam = URLEncoder.encode(imgStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new APIException(201,"文件读取异常");
        }
        String param = "id_card_side=" + id_card_side + "&image=" + imgParam;

        log.info("百度token:"+ BaiDuConstant.ACCESS_TOKEN);
        String result = null;
        try {
            result = HttpUtil.post(url, BaiDuConstant.ACCESS_TOKEN, param);
        } catch (Exception e) {
            throw new APIException(202,"网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("words_result");
        String name = jsonObject.getJSONObject("姓名").getString("words");
        String nation = jsonObject.getJSONObject("民族").getString("words");
        String address = jsonObject.getJSONObject("住址").getString("words");
        String IdCored = jsonObject.getJSONObject("公民身份号码").getString("words");
        String birthday = jsonObject.getJSONObject("出生").getString("words");
        String sex = jsonObject.getJSONObject("性别").getString("words");

        IdCardVo idCardVo = new IdCardVo();
        idCardVo.setName(name);
        idCardVo.setNation(nation);
        idCardVo.setAddress(address);
        idCardVo.setBirthday(birthday);
        idCardVo.setIdCored(IdCored);
        idCardVo.setSex(sex);

        return idCardVo;
    }

    /**
     * 百度驾驶证识别
     * @param file
     * @param driving_front
     * @return
     */
    @Override
    public DrivingFrontVo distinguDriving(MultipartFile file, String driving_front) {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vehicle_license";
        byte[] imgData = new byte[0];
        try {
            imgData = file.getBytes();
        } catch (IOException e) {
            throw new APIException(100,"身份证正面文件读取异常,请重新上传");
        }
        String imgStr = Base64Util.encode(imgData);
        String imgParam = null;
        try {
            imgParam = URLEncoder.encode(imgStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new APIException(201,"文件读取异常");
        }
        String param = "image=" + imgParam + "&vehicle_license_side="+driving_front +"&unified=true";
        String result = null;
        try {
            result = HttpUtil.post(url, BaiDuConstant.ACCESS_TOKEN, param);
        } catch (Exception e) {
            throw new APIException(202,"网络异常");
        }
        JSONObject words_result = JSONObject.parseObject(result).getJSONObject("words_result");
        DrivingFrontVo drivingFrontVo = new DrivingFrontVo();
        try {
            drivingFrontVo.setSeatCount(words_result.getJSONObject("核定载人数").getString("words"));
        } catch (Exception e) {

        }
        try {
            drivingFrontVo.setFrameNo(words_result.getJSONObject("车辆识别代号").getString("words"));
            drivingFrontVo.setAddress(words_result.getJSONObject("住址").getString("words"));
            drivingFrontVo.setBrandName(words_result.getJSONObject("品牌型号").getString("words"));
            drivingFrontVo.setVehicleType(words_result.getJSONObject("车辆类型").getString("words"));
            drivingFrontVo.setOwner(words_result.getJSONObject("所有人").getString("words"));
            drivingFrontVo.setUseNature(words_result.getJSONObject("使用性质").getString("words"));
            drivingFrontVo.setEngineNo(words_result.getJSONObject("发动机号码").getString("words"));
            drivingFrontVo.setLicenseNo(words_result.getJSONObject("号牌号码").getString("words"));
            drivingFrontVo.setEnrollDate(words_result.getJSONObject("注册日期").getString("words"));
        } catch (Exception e) {

        }
        return drivingFrontVo;
    }

    /**
     * 百度识别身份证
     * @param file
     * @param id_card_side
     * @return
     */
    @Override
    public IdCardVo aiIdCard(MultipartFile file, String id_card_side) {

        byte[] imgData = new byte[0];
        try {
            imgData = file.getBytes();
        } catch (IOException e) {
            throw new AiBaiDuException(100,"身份证正面文件读取异常,请重新上传");
        }
        String imgStr = Base64Util.encode(imgData);
        String imgParam = null;
        try {
            imgParam = URLEncoder.encode(imgStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AiBaiDuException(201,"文件读取异常");
        }
        String param = "id_card_side=" + id_card_side + "&image=" + imgParam;
        log.info("百度token:"+ BaiDuConstant.ACCESS_TOKEN);
        String result = null;
        try {
            result = HttpUtil.post(aiIdCord, BaiDuConstant.ACCESS_TOKEN, param);
        } catch (Exception e) {
            throw new AiBaiDuException(202,"网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("words_result");
        String name = jsonObject.getJSONObject("姓名").getString("words");
        String nation = jsonObject.getJSONObject("民族").getString("words");
        String address = jsonObject.getJSONObject("住址").getString("words");
        String IdCored = jsonObject.getJSONObject("公民身份号码").getString("words");
        String birthday = jsonObject.getJSONObject("出生").getString("words");
        String sex = jsonObject.getJSONObject("性别").getString("words");
        IdCardVo idCardVo = new IdCardVo();
        idCardVo.setName(name);
        idCardVo.setNation(nation);
        idCardVo.setAddress(address);
        idCardVo.setBirthday(birthday);
        idCardVo.setIdCored(IdCored);
        idCardVo.setSex(sex);
        return idCardVo;
    }

}
