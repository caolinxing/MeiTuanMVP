package com.example.meituanmvp.fragment.home.model;

import com.example.meituanmvp.pojo.NearShopBean;

import java.util.List;

public interface IModel {
    //获取数据状态监听
    interface GetDataStateListener{
        //获取数据成功
        void onSuccess(List<NearShopBean.DataBean> list);
        //获取数据失败
        void onError(String error);
    }
    void getUrl(String url,GetDataStateListener getDataStateListener);
}
