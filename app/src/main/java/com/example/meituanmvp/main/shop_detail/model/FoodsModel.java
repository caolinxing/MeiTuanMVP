package com.example.meituanmvp.main.shop_detail.model;

import com.example.meituanmvp.pojo.GoodsMangerBean;
import com.example.meituanmvp.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FoodsModel implements IFoodsModel {
    private Gson gson;

    @Override
    public void getUrl(String url, final GetDataStateListener getDataStateListener) {
        OkHttpUtils.doGetEnqueue(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getDataStateListener.onError(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                gson = new Gson();
                GoodsMangerBean nearShopBean = gson.fromJson(str, GoodsMangerBean.class);
                List<GoodsMangerBean.DataBean> data = nearShopBean.getData();
                getDataStateListener.onSuccess(data);
            }
        });
    }
}
