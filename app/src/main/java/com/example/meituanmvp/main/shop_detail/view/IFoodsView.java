package com.example.meituanmvp.main.shop_detail.view;

import com.example.meituanmvp.pojo.GoodsMangerBean;
import com.example.meituanmvp.pojo.NearShopBean;

import java.util.List;

public interface IFoodsView {
    //获取数据成功
    void onSuccessOne(List<GoodsMangerBean.DataBean> list);
    //获取数据失败
    void onError(String error);
}
