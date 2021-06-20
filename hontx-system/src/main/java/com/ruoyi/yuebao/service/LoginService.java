package com.ruoyi.yuebao.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoginService {


    private static String USERNAME;
    private static String PASSWORD;
    private static String CHANNEL_CODE;
    private static String URL;

    @Value("${ybinsure.username}")
    public void setUsername(String username) {
        USERNAME = username;
    }

    @Value("${ybinsure.password}")
    public void setPassword(String password) {
        PASSWORD = password;
    }

    @Value("${ybinsure.channelCode}")
    public void setChannelCode(String channelCode) {
        CHANNEL_CODE = channelCode;
    }

    @Value("${ybinsure.url}")
    public void setUrl(String url) {
        URL = url;
    }

    private static String token;
    private static Long currentTime;
    private static long expires;

    public static void login() throws IOException {

        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("username", USERNAME)
                .add("password", PASSWORD)
                .add("channelCode", CHANNEL_CODE)
                .build();

        Request request = new Request.Builder()
                .url(URL + "/login")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        String result = call.execute().body().string();
        log.info(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.getInteger("status") == 200) {
            JSONObject data = json.getJSONObject("data");
            currentTime = System.currentTimeMillis();
            expires = data.getInteger("expires");
            token = data.getString("token");
        }else {
            throw new CustomException(json.getString("message"));
        }
    }

    public static String getToken() throws IOException {
        if (currentTime != null) {
            long distance = System.currentTimeMillis() - currentTime;
            if (distance > expires * 1000) {
                login();
            }
        } else {
            login();
        }
        return token;
    }

    public static void main(String args[]) throws IOException {
        LoginService.login();

    }
}
