package com.example.meituanmvp.main.shop_detail.presenter;

import com.example.meituanmvp.main.shop_detail.model.FoodsModel;
import com.example.meituanmvp.main.shop_detail.model.IFoodsModel;
import com.example.meituanmvp.main.shop_detail.view.IFoodsView;
import com.example.meituanmvp.pojo.GoodsMangerBean;

import java.util.List;

public class FoodsPresenter implements IFoodsPresenter,IFoodsModel.GetDataStateListener {
    private IFoodsView iView;
    private IFoodsModel iModel;

    public FoodsPresenter(IFoodsView iView) {
        this.iView = iView;
        iModel = new FoodsModel();
    }


    @Override
    public void setUrl(String url) {
        iModel.getUrl(url,this);
    }

    @Override
    public void onDestorys() {
        if (iView!=null){
            iView=null;
        }
    }

    @Override
    public void onSuccess(List<GoodsMangerBean.DataBean> list) {
        iView.onSuccessOne(list);
    }

    @Override
    public void onError(String error) {
        iView.onError(error);
    }
}
