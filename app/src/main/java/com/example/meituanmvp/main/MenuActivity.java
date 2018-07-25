package com.example.meituanmvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meituanmvp.R;
import com.example.meituanmvp.adapter.MyHomeListAdapter;
import com.example.meituanmvp.fragment.home.presenter.IPresenter;
import com.example.meituanmvp.fragment.home.presenter.Presenter;
import com.example.meituanmvp.fragment.home.view.IView;
import com.example.meituanmvp.pojo.NearShopBean;
import com.example.meituanmvp.utils.ApiUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private String TAG = "menu";
    private IPresenter presenter;
    private ArrayList<NearShopBean.DataBean> shopNewsList = new ArrayList<>();
    private MyHomeListAdapter adapter;
    private TextView mMenuTvTitle;
    private PullToRefreshListView mMenuPullToListView;
    private ImageView menu_back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //初始化控件
        initView();
        Intent intent = getIntent();
        String title_name = intent.getStringExtra("title_name");
        //设置标题
        mMenuTvTitle.setText(title_name);
        //持有presneter
        presenter = new Presenter(this);
        //设置适配器
        adapter = new MyHomeListAdapter(this, shopNewsList);
        mMenuPullToListView.setAdapter(adapter);
        presenter.setUrl(ApiUtils.BASE_API + ApiUtils.SHOP_API1);
        //刷新加载
        setRefreshAndLoad();
        //点击监听
        setOnclickListener();
    }

    private void setOnclickListener() {
        menu_back1.setOnClickListener(this);
    }

    private void initView() {
        menu_back1 = (ImageView) findViewById(R.id.menu_back1);
        mMenuTvTitle = (TextView) findViewById(R.id.menu_tv_title);
        mMenuPullToListView = (PullToRefreshListView) findViewById(R.id.menu_pullToListView);
        mMenuPullToListView.setMode(PullToRefreshBase.Mode.BOTH);

    }

    private void setRefreshAndLoad() {
        mMenuPullToListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                shopNewsList.clear();
                presenter.setUrl(ApiUtils.BASE_API + ApiUtils.SHOP_API1);
                mMenuPullToListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMenuPullToListView.onRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.setUrl(ApiUtils.BASE_API + ApiUtils.SHOP_API2);
                mMenuPullToListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onSuccess(List<NearShopBean.DataBean> list) {
        shopNewsList.addAll(list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                mMenuPullToListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: " + error);
    }

    @Override
    public void onDestroy() {
       // presenter.onDestorys();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
