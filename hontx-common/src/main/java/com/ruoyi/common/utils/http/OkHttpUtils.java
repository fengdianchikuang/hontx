package com.ruoyi.common.utils.http;

import com.ruoyi.common.exception.APIException;
import com.ruoyi.common.utils.StringUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {
    public static final MediaType APPLICATION_JSON_CHARSET_UTF_8 = MediaType.get("application/json;charset=utf8");
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
            .callTimeout(120, TimeUnit.SECONDS)    // 设置请求超时时间
            .connectTimeout(30, TimeUnit.SECONDS)    // 设置连接超时时间
            .readTimeout(60, TimeUnit.SECONDS)    // 设置socket读写超时时间
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();
    /**
     * get请求不带参数
     * @param url
     * @return
     */
    public static String get(String url) {

        System.out.println("HTTP GET URL——" + url);

        Request getRequest = new Request.Builder().get().url(url).build();

        Call call = OK_HTTP_CLIENT.newCall(getRequest);

        try (Response getResponse = call.execute()) {
            return handle(getResponse);
        } catch (IOException e) {
            throw new APIException(1, "网络异常");
        }
    }



    /**
     * post请求 创富云使用
     * @param url
     * @param body json
     * @return
     */
    public static String postJSON(String url, String body,String token) {
        RequestBody requestBody = RequestBody.create(APPLICATION_JSON_CHARSET_UTF_8, body);
        Request postRequest = null;
            if(StringUtils.isNotEmpty(token)){
                postRequest = new Request.Builder()
                        .post(requestBody)
                        .addHeader("Authorization", token)
                        .url(url)
                        .build();
            }else {
                postRequest = new Request.Builder()
                        .post(requestBody)
                        .url(url)
                        .build();
            }

        Call call = OK_HTTP_CLIENT.newCall(postRequest);
        try (Response postResponse = call.execute()) {
            return handle(postResponse);
        } catch (IOException e) {
            throw  new APIException(1, "网络异常,请重新再试");
        }
    }
    public static String postFrom(String url,FormBody.Builder formBody,String token){
        Request request = null;
        if(StringUtils.isNotEmpty(token)){
            if(formBody != null){
                request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", token)
                        .post(formBody.build()).build();
            }else {
                request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", token)
                        .post(RequestBody.create(null,"")).build();
            }

        }else {
            if(formBody != null){
                request = new Request.Builder()
                        .url(url)
                        .post(formBody.build()).build();
            }else {
                request = new Request.Builder()
                        .url(url)
                        .post(RequestBody.create(null,"")).build();
            }

        }
        Call call = OK_HTTP_CLIENT.newCall(request);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post 请求 表单
     * @param url
     * @param params map类型
     * @return
     */
    public static String postMap(String url, Map<String,String> params,String token){

        FormBody.Builder formBody = new FormBody.Builder();
        for (String key : params.keySet()) {
            if(StringUtils.isNotEmpty(params.get(key)))
                formBody.add(key,params.get(key));
        }
        //创建Request....formBody...new formBody.Builder()...add()....build()
        Request request = null;
            if(StringUtils.isNotEmpty(token)){
                request = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", token)
                        .post(formBody.build()).build();
            }else {
                request = new Request.Builder()
                        .url(url)
                        .post(formBody.build()).build();
            }


        Call call = OK_HTTP_CLIENT.newCall(request);
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String handle(Response httpResponse) throws IOException {
        // 从响应模型中获取响应实体
        ResponseBody responseBody = httpResponse.body();

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
