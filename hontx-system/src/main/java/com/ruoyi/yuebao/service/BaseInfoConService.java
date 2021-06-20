package com.ruoyi.yuebao.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.PolicyConfigConstant;
import com.ruoyi.common.enums.RedisConstant;
import com.ruoyi.common.utils.http.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BaseInfoConService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${ybinsure.url}")
    private String url;


    @Value("${echeli.organizationId}")
    private String organizationId;

    @Value("${echeli.organizationId}")
    private String organization;

    /**
     * 从redis中获取悦保字段属性
     * @return
     */
    public Map<String, Object> queryPolicyConfig() throws IOException {
        Map<String,Object> data  = new HashMap<>();
        Object vehicle_type = redisTemplate.opsForValue().get(PolicyConfigConstant.vehicle_type);
        if(vehicle_type != null){
            data.put(PolicyConfigConstant.vehicle_type,vehicle_type);
        }

        Object use_nature = redisTemplate.opsForValue().get(PolicyConfigConstant.use_nature);
        if(use_nature != null){
            data.put(PolicyConfigConstant.use_nature,use_nature);
        }

        Object attach_nature = redisTemplate.opsForValue().get(PolicyConfigConstant.attach_nature);
        if(attach_nature !=null){
            data.put(PolicyConfigConstant.attach_nature,attach_nature);
        }

        Object plate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.plate_type);
        if(plate_type != null){
            data.put(PolicyConfigConstant.plate_type,plate_type);
        }

        Object plate_color = redisTemplate.opsForValue().get(PolicyConfigConstant.plate_color);
        if(plate_color != null){
            data.put(PolicyConfigConstant.plate_type,plate_color);
        }

        Object energy_type = redisTemplate.opsForValue().get(PolicyConfigConstant.energy_type);
        if(energy_type != null){
            data.put(PolicyConfigConstant.energy_type,energy_type);
        }

        Object certificate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.certificate_type);
        if (certificate_type != null){
            data.put(PolicyConfigConstant.certificate_type,certificate_type);
        }

        Object vehicle_certificate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.vehicle_certificate_type);
        if(vehicle_certificate_type != null){
            data.put(PolicyConfigConstant.vehicle_certificate_type,vehicle_certificate_type);
        }

        Object verify_type = redisTemplate.opsForValue().get(PolicyConfigConstant.verify_type);
        if(verify_type != null){
            data.put(PolicyConfigConstant.verify_type,verify_type);
        }

        Object verify_result = redisTemplate.opsForValue().get(PolicyConfigConstant.verify_result);
        if(verify_result != null){
            data.put(PolicyConfigConstant.verify_result,verify_result);
        }
        //redis中数据为空，则从悦保请求数据存入
        if(data.isEmpty()){
            return queryYuebaoPolicyConfig();
        }
        return data;
    }
    /**
     * 远程获取悦保属性字段
     * @return
     * @throws
     */
    private Map<String, Object> queryYuebaoPolicyConfig() throws IOException {
        String result = OkHttpUtils.postFrom(url + "/common/queryPolicyConfig", null, LoginService.getToken());
        JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
        Map<String, Object> innerMap = jsonObject.getInnerMap();

        Map<String,Object> data = new HashMap<>();
        List<Map<String,String>> vehicleTypes = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.vehicle_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.vehicle_type, com.alibaba.fastjson.JSON.toJSONString(vehicleTypes));
        Object vehicle_type = redisTemplate.opsForValue().get(PolicyConfigConstant.vehicle_type);
        data.put(PolicyConfigConstant.vehicle_type,vehicle_type);

        List<Map<String,String>> useNature = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.use_nature);
        redisTemplate.opsForValue().set(PolicyConfigConstant.use_nature, com.alibaba.fastjson.JSON.toJSONString(useNature));
        Object use_nature = redisTemplate.opsForValue().get(PolicyConfigConstant.use_nature);
        data.put(PolicyConfigConstant.use_nature,use_nature);

        List<Map<String,String>> attachNature = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.attach_nature);
        redisTemplate.opsForValue().set(PolicyConfigConstant.attach_nature, com.alibaba.fastjson.JSON.toJSONString(attachNature));
        Object attach_nature = redisTemplate.opsForValue().get(PolicyConfigConstant.attach_nature);
        data.put(PolicyConfigConstant.attach_nature,attach_nature);

        List<Map<String,String>> plateType = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.plate_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.plate_type, com.alibaba.fastjson.JSON.toJSONString(plateType));
        Object plate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.plate_type);
        data.put(PolicyConfigConstant.plate_type,plate_type);

        List<Map<String,String>> plateColor = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.plate_color);
        redisTemplate.opsForValue().set(PolicyConfigConstant.plate_color, com.alibaba.fastjson.JSON.toJSONString(plateColor));
        Object plate_color = redisTemplate.opsForValue().get(PolicyConfigConstant.plate_color);
        data.put(PolicyConfigConstant.plate_type,plate_color);

        List<Map<String,String>> energyType = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.energy_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.energy_type, com.alibaba.fastjson.JSON.toJSONString(energyType));
        Object energy_type = redisTemplate.opsForValue().get(PolicyConfigConstant.energy_type);
        data.put(PolicyConfigConstant.energy_type,energy_type);

        List<Map<String,String>> certificateType = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.certificate_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.certificate_type, com.alibaba.fastjson.JSON.toJSONString(certificateType));
        Object certificate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.certificate_type);
        data.put(PolicyConfigConstant.certificate_type,certificate_type);

        List<Map<String,String>> vehicleCertificateType = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.vehicle_certificate_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.vehicle_certificate_type, com.alibaba.fastjson.JSON.toJSONString(vehicleCertificateType));
        Object vehicle_certificate_type = redisTemplate.opsForValue().get(PolicyConfigConstant.vehicle_certificate_type);
        data.put(PolicyConfigConstant.vehicle_certificate_type,vehicle_certificate_type);


        List<Map<String,String>> verifyType = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.verify_type);
        redisTemplate.opsForValue().set(PolicyConfigConstant.verify_type, com.alibaba.fastjson.JSON.toJSONString(verifyType));
        Object verify_type = redisTemplate.opsForValue().get(PolicyConfigConstant.verify_type);
        data.put(PolicyConfigConstant.verify_type,verify_type);

        List<Map<String,String>> verifyReason = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.verify_reason);
        redisTemplate.opsForValue().set(PolicyConfigConstant.verify_reason, com.alibaba.fastjson.JSON.toJSONString(verifyReason));
        Object verify_reason = redisTemplate.opsForValue().get(PolicyConfigConstant.verify_reason);
        data.put(PolicyConfigConstant.verify_reason,verify_reason);

        List<Map<String,String>> verifyResult = (List<Map<String,String>>)innerMap.get(PolicyConfigConstant.verify_result);
        redisTemplate.opsForValue().set(PolicyConfigConstant.verify_result, com.alibaba.fastjson.JSON.toJSONString(verifyResult));
        Object verify_result = redisTemplate.opsForValue().get(PolicyConfigConstant.verify_result);
        data.put(PolicyConfigConstant.verify_result,verify_result);
        return data;
    }
    /**
     * 请求通用保险别配置
     * @return
     */
    public Object queryRiskConfig() throws IOException {
        Object  data = redisTemplate.opsForValue().get(RedisConstant.INSURANCE_COMMON_RISK);
        if(data == null){

            String result = OkHttpUtils.postFrom(url + "/common/queryRiskConfig", null, LoginService.getToken());
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("list");
            //存入redis
            ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(RedisConstant.INSURANCE_COMMON_RISK,jsonArray);
            return jsonArray;
        }
        return data;
    }

    /**
     * 根据保险公司id获取保险项
     *
     * @return
     */
    public Object updateCompanyRiskConfig(String companyId) throws IOException {
        Object result = redisTemplate.opsForValue().get(RedisConstant.INSURANCE_COMPANY_PREFIX + companyId);
        if(result ==null){
            //请求保险配置数据
            FormBody.Builder formBody = new FormBody.Builder();
            formBody.add("companyId",companyId);
             String res = OkHttpUtils.postFrom(url + "/common/queryCompanyRiskConfig", formBody, LoginService.getToken());
            JSONArray jsonArray = JSONObject.parseObject(res).getJSONObject("data").getJSONArray("list");
            redisTemplate.opsForValue().set(RedisConstant.INSURANCE_COMPANY_PREFIX + companyId,jsonArray);
            return jsonArray;
        }
        return result;
    }

    /**
     * 查询可用机构网点
     * @return
     */
    public String queryOrganization() throws IOException {
        String result = OkHttpUtils.postFrom(url + "/channel/queryOrganization", null, LoginService.getToken());
        return result;
    }

    /**
     * 更新宏泰祥可用的保险公司
     * @return
     */
    public Object updateCompany(HttpServletRequest httpServletRequest) throws IOException {
        String companyId = httpServletRequest.getHeader("companyId");
        JSONArray jsonArr = (JSONArray)redisTemplate.opsForValue().get(RedisConstant.INSURANCE_COMPANY);
        if(jsonArr!=null){
            List<Object> list = null;
            if("1".equals(companyId)){
                list = new ArrayList<>();
                for (int i = 0; i < jsonArr.size(); i++) {
                    if(57 == jsonArr.getJSONObject(i).getInteger("id")){
                        list.add(jsonArr.getJSONObject(i));
                    }
                }
            }else {
                return jsonArr;
            }
            return list;
        }
        FormBody.Builder formBody = new FormBody.Builder();

        formBody.add("organizationId", organizationId);
        String result = OkHttpUtils.postFrom(url + "/channel/queryCompany", formBody, LoginService.getToken());
        JSONObject jsonObject = JSONObject.parseObject(result);
        jsonArr = jsonObject.getJSONObject("data").getJSONArray("list");
        for (int i = 0; i < jsonArr.size(); i++) {
            if("1143150138".equals(jsonArr.getJSONObject(i).getString("channelAccount"))){
                jsonArr.getJSONObject(i).put("name","人保-长沙中支");
            }else if("1143159932".equals(jsonArr.getJSONObject(i).getString("channelAccount"))){
                jsonArr.getJSONObject(i).put("name","人保-株洲中支");
            }
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(RedisConstant.INSURANCE_COMPANY,jsonArr);

        return jsonArr;
    }
}
