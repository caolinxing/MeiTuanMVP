package com.example.meituanmvp.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class UMeng extends Application {
    private static String UMKEY = "5b4d66c38f4a9d7760000068";
    @Override
    public void onCreate() {
        super.onCreate();
        //开启日志L
        UMConfigure.setLogEnabled(true);
        //日志加密
        UMConfigure.setEncryptEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, UMKEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                null);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

}
