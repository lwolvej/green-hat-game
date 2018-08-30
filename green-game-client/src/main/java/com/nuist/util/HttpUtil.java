package com.nuist.util;

import com.nuist.exception.MyException;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 网络请求工具类
 *
 * @author 陈裕豪
 */
public class HttpUtil {
    private static OkHttpClient client;

    public static OkHttpClient getCLIENT() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    public static Response doGet(String url, OkHttpClient client) {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        final Call call = client.newCall(request);
        Response response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new MyException("Happen IO exception!");
        }
        return response;
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //注册
    public static Response doPost1(String url, String userName, String password, OkHttpClient client) {
        String json = "{\"userName\":\"" + userName + "\",\"password\":\"" + password + "\"}";
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new MyException("Happen IO exception!");
        }
        return response;
    }

    //提交得分
    public static Response doPost2(String url, String userName, String score, OkHttpClient client) {
        String json = "{\"userName\":\"" + userName + "\",\"number\":" + score + "}";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new MyException("Happen IO exception!");
        }
        return response;
    }
}
