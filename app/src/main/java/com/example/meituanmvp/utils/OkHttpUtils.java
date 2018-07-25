package com.example.meituanmvp.utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {

    private static OkHttpClient client = null;

        public static OkHttpClient getInstance() {
        if (client == null){
            synchronized (OkHttpUtils.class){
                client = new OkHttpClient();
            }
        }
        return client;
    }

    private OkHttpUtils() {
    }

    //同步get请求
    public static void doGetExcute(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //异步get请求
    public static void doGetEnqueue(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    //异步post请求map
    public static void doPostMap(String url, Map<String,String> map,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key,map.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    //异步post请求json
    public static void doPostJson(String url, String json,Callback callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

}
