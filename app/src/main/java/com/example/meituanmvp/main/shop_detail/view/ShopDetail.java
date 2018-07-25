package com.example.meituanmvp.main.shop_detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.meituanmvp.R;
import com.example.meituanmvp.adapter.MyFragmentViewPagerAdaptr;
import com.example.meituanmvp.fragment.home.presenter.IPresenter;
import com.example.meituanmvp.fragment.home.presenter.Presenter;
import com.example.meituanmvp.fragment.home.view.IView;
import com.example.meituanmvp.main.shop_detail.fragment.Fragment_one;
import com.example.meituanmvp.main.shop_detail.fragment.Fragment_three;
import com.example.meituanmvp.main.shop_detail.fragment.Fragment_two;
import com.example.meituanmvp.main.shop_detail.presenter.FoodsPresenter;
import com.example.meituanmvp.pojo.NearShopBean;
import com.example.meituanmvp.utils.ApiUtils;
import com.example.meituanmvp.zdy_views.AutoScrollTextView;

import java.util.ArrayList;
import java.util.List;

public class ShopDetail extends AppCompatActivity implements IView {

    private ImageView mMenuBack1;
    private TextView mShopTvTitle;
    private ImageView mDetailIcon;
    private TextView mDatailPeisong;
    private AutoScrollTextView mTextview;
    private List<String> name = new ArrayList<>();
    private IPresenter presenter;
    private TextView mDatailJieshao;
    private int j = 0;
    private FoodsPresenter presenter1;
    private String TAG = "shopdetail";
    private ViewPager mDetailViewLayout;
    private ArrayList<Fragment> list;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        initView();
        //持有presenter层
        presenter = new Presenter(this);
        //网络url
        Intent intent = getIntent();
        String restaurant_name = intent.getStringExtra("restaurant_name");
        url = intent.getStringExtra("url");
        presenter.setUrl(ApiUtils.SHOP_API3 + restaurant_name);
        //加载fragmentlist
        loadFragmentData();
        mDetailViewLayout.setAdapter(new MyFragmentViewPagerAdaptr(getSupportFragmentManager(), list));
       // mRecyclerRight.setAdapter();
    }

    private void loadFragmentData() {
        list = new ArrayList<>();
        Fragment_one fragment_one = new Fragment_one();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragment_one.setArguments(bundle);
        list.add(fragment_one);
        list.add(new Fragment_two());
        list.add(new Fragment_three());
    }

    private void setAutoScrollTextView(List<NearShopBean.DataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            j = i;
            name.add("   " + list.get(0).getBulletin());
        }
        mTextview.setText(name.get(j));
        mTextview.setList(name);
        mTextview.startScroll();
        mTextview.setClickLisener(new AutoScrollTextView.ItemClickLisener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), name.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mMenuBack1 = (ImageView) findViewById(R.id.menu_back1);
        mShopTvTitle = (TextView) findViewById(R.id.shop_tv_title);
        mDetailIcon = (ImageView) findViewById(R.id.detail_icon);
        mDatailPeisong = (TextView) findViewById(R.id.datail_peisong);
        mTextview = (AutoScrollTextView) findViewById(R.id.textview);
        mDatailJieshao = (TextView) findViewById(R.id.datail_jieshao);
        mDetailViewLayout = (ViewPager) findViewById(R.id.detail_view_layout);
    }

    //获取数据成功
    @Override
    public void onSuccess(final List<NearShopBean.DataBean> list) {
        //TopBar控件赋值
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadTopData(list);
            }
        });
    }


    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: " + error);
    }

    private void loadTopData(List<NearShopBean.DataBean> list) {
        setAutoScrollTextView(list);
        mDatailPeisong.setText(list.get(0).getMin_price_tip() + " | " + list.get(0).getShipping_fee_tip() + " | " + list.get(0).getDelivery_time_tip());
        mShopTvTitle.setText(list.get(0).getName());
        Glide.with(ShopDetail.this).load(list.get(0).getPic_url()).into(mDetailIcon);
        mDatailJieshao.setText(list.get(0).getBulletin());
    }
}
