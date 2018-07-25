package com.example.meituanmvp.main.shop_detail.model;

import com.example.meituanmvp.pojo.GoodsMangerBean;

import java.util.List;

public interface IFoodsModel {
    //获取数据状态监听
    interface GetDataStateListener{
        //获取数据成功
        void onSuccess(List<GoodsMangerBean.DataBean> list);
        //获取数据失败
        void onError(String error);
    }
    void getUrl(String url, GetDataStateListener getDataStateListener);
}
