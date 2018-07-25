package com.example.meituanmvp.main.shop_detail.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meituanmvp.R;
import com.example.meituanmvp.adapter.MyLeftRecyclerViewAdapter;
import com.example.meituanmvp.main.shop_detail.adapter.MyMutlipleItem;
import com.example.meituanmvp.main.shop_detail.adapter.RecyclerMultipleItemAdapter;
import com.example.meituanmvp.main.shop_detail.pojo.ModelBean;
import com.example.meituanmvp.main.shop_detail.presenter.FoodsPresenter;
import com.example.meituanmvp.main.shop_detail.view.IFoodsView;
import com.example.meituanmvp.pojo.GoodsMangerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_one extends Fragment implements IFoodsView {


    private RecyclerView mTvRecyclerLeft;
    private RecyclerView mTvRecyclerRight;
    private FoodsPresenter presenter1;
    private List<MyMutlipleItem> datas02 = new ArrayList<>();
    private List<ModelBean> data01 = new ArrayList<>();

    ModelBean modelBean;
    private List<GoodsMangerBean.DataBean.SpusBean> spus;


    public Fragment_one() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter1 = new FoodsPresenter(this);
        Bundle bundle = new Bundle();
        String url = getArguments().getString("url");
        presenter1.setUrl(url);
    }

    @Override
    public void onSuccessOne(final List<GoodsMangerBean.DataBean> list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //商品管理
                mTvRecyclerLeft.setAdapter(new MyLeftRecyclerViewAdapter(R.layout.recyclerview_left, list));
                for (int i = 0; i < list.size(); i++) {
                    modelBean = new ModelBean();
                    String name = list.get(i).getName();
                    spus = list.get(i).getSpus();
                    loopList(spus,name);
                }
                for (int i = 0; i <data01.size() ; i++) {
                    if (!TextUtils.isEmpty(data01.get(i).getName())){
                        datas02.add(new MyMutlipleItem(MyMutlipleItem.FIRST_TYPE,data01.get(i)));
                    }else {
                        datas02.add(new MyMutlipleItem(MyMutlipleItem.SECOND_TYPE,data01.get(i)));
                    }
                }
                RecyclerMultipleItemAdapter adapter = new RecyclerMultipleItemAdapter(datas02);
                mTvRecyclerRight.setAdapter(adapter);
            }
        });

    }

    private void loopList(List<GoodsMangerBean.DataBean.SpusBean> spus, String name) {
        for (int j = 0; j <spus.size() ; j++) {
            modelBean = new ModelBean();
            if (j==0){
                List<GoodsMangerBean.DataBean.SpusBean.SkusBean> skus = spus.get(j).getSkus();
                for (int i = 0; i <skus.size() ; i++) {
                    modelBean.setPrice(skus.get(i).getPrice());
                }
                modelBean.setXaioliang(spus.get(j).getMonth_saled_content());
                modelBean.setTaocan(spus.get(j).getName());
                modelBean.setPic_url(spus.get(j).getPic_url());
                modelBean.setName(name);
                data01.add(modelBean);
            }else{
                List<GoodsMangerBean.DataBean.SpusBean.SkusBean> skus = spus.get(j).getSkus();
                for (int i = 0; i <skus.size() ; i++) {
                    modelBean.setPrice(skus.get(i).getPrice());
                }
                modelBean.setXaioliang(spus.get(j).getMonth_saled_content());
                modelBean.setTaocan(spus.get(j).getName());
                modelBean.setPic_url(spus.get(j).getPic_url());
                data01.add(modelBean);
            }
        }
    }

    @Override
    public void onError(String error) {

    }

    private void initView(View view) {
        mTvRecyclerLeft = (RecyclerView)view.findViewById(R.id.tv_recycler_left);
        mTvRecyclerRight = (RecyclerView) view.findViewById(R.id.tv_recycler_right);
        mTvRecyclerRight.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mTvRecyclerLeft.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mTvRecyclerLeft.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mTvRecyclerRight.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
}
