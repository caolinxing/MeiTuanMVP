package com.example.meituanmvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.meituanmvp.R;
import com.example.meituanmvp.main.shop_detail.GoodsManger;
import com.example.meituanmvp.main.shop_detail.view.ShopDetail;
import com.example.meituanmvp.pojo.GoodsMangerBean;

import java.util.List;

public class MyLeftRecyclerViewAdapter extends BaseQuickAdapter<GoodsMangerBean.DataBean, BaseViewHolder>  {

    public MyLeftRecyclerViewAdapter(int layoutResId, @Nullable List<GoodsMangerBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsMangerBean.DataBean item) {
        helper.setText(R.id.tv_recycler_left,item.getName());
    }
}
