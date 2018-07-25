package com.example.meituanmvp.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.meituanmvp.R;
import com.example.meituanmvp.adapter.RecyclerViewAdapter;
import com.example.meituanmvp.login.LoginActivity;
import com.example.meituanmvp.pojo.HeaderViewBean;

import java.util.ArrayList;

public class Fragment_mine extends Fragment {

    private ImageView mMenuBack1;
    private TextView mMenuTvTitle;
    private ImageView mMineIvTouxiang;
    private TextView mMineTvUname;
    private RecyclerView mMineGridView1;
    private RecyclerView mMineGridView2;
    private RecyclerView mMineGridView3;
    private String USER_MESSAGE="USER_MESSAGE";
    private ArrayList<HeaderViewBean> list1;
    private ArrayList<HeaderViewBean> list2;
    private ArrayList<HeaderViewBean> list3;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragemnt_mine, container, false);
        //初始化控件
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //加载数据
        LoadData1();
        //各模块点击事件
        setModelOnclickListener();
        //点击登入
        mMineTvUname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        loadTouXiangIcon();
    }

    private void loadTouXiangIcon() {
        //glide圆形参数
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        sp = getActivity().getSharedPreferences(USER_MESSAGE, Context.MODE_PRIVATE);
        //不存在默认为空
        String name = sp.getString("name", "");
        String img = sp.getString("img", "");
        if (!TextUtils.isEmpty(name)){
           mMineTvUname.setText(name);
        }
        if (!TextUtils.isEmpty(img)){
            Glide.with(this).load(img).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mMineIvTouxiang);
        }
    }

    private void setModelOnclickListener() {
        mMineGridView1.setAdapter(new RecyclerViewAdapter(getActivity(), list1, 0, new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "我的足迹", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "我的好友", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(), "答谢记录", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(), "我的地址", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }));
        //加载数据
        LoadData2();
        mMineGridView2.setAdapter(new RecyclerViewAdapter(getActivity(), list2, 0, new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "红包", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "代金卷", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "钱包", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "余额", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }));
        //加载数据
        LoadData3();
        mMineGridView3.setAdapter(new RecyclerViewAdapter(getActivity(), list3, 0, new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "邀请有奖", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "商家入驻", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "帮助与反馈", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "在线客服", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }));
    }

    private void LoadData1() {
        list1 = new ArrayList<>();
        list1.add(new HeaderViewBean(R.drawable.shoucang, "我的收藏"));
        list1.add(new HeaderViewBean(R.drawable.zuji, "我的足迹"));
        list1.add(new HeaderViewBean(R.drawable.pingjia, "我的评价"));
        list1.add(new HeaderViewBean(R.drawable.haoyou, "我的好友"));
        list1.add(new HeaderViewBean(R.drawable.daxiejilu, "答谢记录"));
        list1.add(new HeaderViewBean(R.drawable.myloaction, "我的地址"));
    }

    private void LoadData2() {
        list2 = new ArrayList<>();
        list2.add(new HeaderViewBean(R.drawable.hongbao, "红包"));
        list2.add(new HeaderViewBean(R.drawable.daijinjuan, "代金卷"));
        list2.add(new HeaderViewBean(R.drawable.qianbao, "钱包"));
        list2.add(new HeaderViewBean(R.drawable.yu_e, "余额"));
    }

    private void LoadData3() {
        list3 = new ArrayList<>();
        list3.add(new HeaderViewBean(R.drawable.yaoqingyoujiang, "邀请有奖"));
        list3.add(new HeaderViewBean(R.drawable.shangjiaruzhu, "商家入驻"));
        list3.add(new HeaderViewBean(R.drawable.help, "帮助与反馈"));
        list3.add(new HeaderViewBean(R.drawable.kefu, "在线客服"));
    }

    private void initView(View view) {
        mMenuBack1 = (ImageView) view.findViewById(R.id.menu_back1);
        mMenuTvTitle = (TextView) view.findViewById(R.id.menu_tv_title);
        mMineIvTouxiang = (ImageView) view.findViewById(R.id.mine_iv_touxiang);
        mMineTvUname = (TextView) view.findViewById(R.id.mine_tv_uname);
        mMineGridView1 = view.findViewById(R.id.mine_grid_view1);
        mMineGridView2 = view.findViewById(R.id.mine_grid_view2);
        mMineGridView3 = view.findViewById(R.id.mine_grid_view3);
        mMineGridView1.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mMineGridView2.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mMineGridView3.setLayoutManager(new GridLayoutManager(getActivity(),4));
    }

    @Override
    public void onResume() {
        loadTouXiangIcon();
        super.onResume();
    }
}