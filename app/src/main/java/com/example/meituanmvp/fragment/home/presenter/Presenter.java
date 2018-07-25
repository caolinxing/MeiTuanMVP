package com.example.meituanmvp.fragment.home.presenter;

import com.example.meituanmvp.fragment.home.model.IModel;
import com.example.meituanmvp.fragment.home.model.Model;
import com.example.meituanmvp.fragment.home.view.Fragment_home;
import com.example.meituanmvp.fragment.home.view.IView;
import com.example.meituanmvp.pojo.NearShopBean;

import java.util.List;

public class Presenter implements IPresenter,IModel.GetDataStateListener {
    private IView iView;
    private IModel iModel;

    public Presenter(IView iView) {
        this.iView = iView;
        iModel = new Model();
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
    public void onSuccess(List<NearShopBean.DataBean> list) {
        iView.onSuccess(list);
    }

    @Override
    public void onError(String error) {
        iView.onError(error);
    }
}
