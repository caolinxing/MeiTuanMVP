package com.example.meituanmvp.fragment.home.view;

import com.example.meituanmvp.pojo.NearShopBean;

import java.util.List;

public interface IView {
    //获取数据成功
    void onSuccess(List<NearShopBean.DataBean> list);
    //获取数据失败
    void onError(String error);
}
