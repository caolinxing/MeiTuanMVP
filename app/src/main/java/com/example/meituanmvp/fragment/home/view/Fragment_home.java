package com.example.meituanmvp.fragment.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meituanmvp.R;
import com.example.meituanmvp.adapter.MyHomeListAdapter;
import com.example.meituanmvp.adapter.MyViewPagerAdapter;
import com.example.meituanmvp.adapter.RecyclerViewAdapter;
import com.example.meituanmvp.fragment.home.presenter.Presenter;
import com.example.meituanmvp.main.LocationActivity;
import com.example.meituanmvp.main.MenuActivity;
import com.example.meituanmvp.main.shop_detail.view.ShopDetail;
import com.example.meituanmvp.pojo.HeaderViewBean;
import com.example.meituanmvp.pojo.NearShopBean;
import com.example.meituanmvp.utils.ApiUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home extends Fragment implements IView {
    private TextView mTvLocation;
    private TextView mTvSearch;
    private ViewPager mHomePagerView;
    private ArrayList<HeaderViewBean> mDataList;
    private int index = 0;
    private LinearLayout mAddView;
    private Presenter presenter;
    private List<NearShopBean.DataBean> shopNewsList = new ArrayList<>();
    private String TAG= "home";
    private MyHomeListAdapter adapter;
    private PullToRefreshListView mPullToListView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragemnt_home, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //持有presenter
        presenter = new Presenter(this);
        View headView = View.inflate(getActivity(),R.layout.home_page_layout,null);
        mAddView = (LinearLayout) headView.findViewById(R.id.addView);
        //分类导航菜单
        initDaoHangMenu();
        ListView refreshableView = mPullToListView.getRefreshableView();
        refreshableView.addHeaderView(headView);
        adapter = new MyHomeListAdapter(getActivity(),shopNewsList);
        mPullToListView.setAdapter(adapter);
        presenter.setUrl(ApiUtils.BASE_API+ApiUtils.SHOP_API1);
        //刷新加载
        setRefreshAndLoad();
        final Integer[] page = new Integer[]{32,33,35,36,38,39,41,42};
        //mPullToListView监听
        mPullToListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShopDetail.class);
                intent.putExtra("restaurant_name",shopNewsList.get(position-2).getName());
                intent.putExtra("url",ApiUtils.SHOP_API4+page[position-2]);
                startActivity(intent);
            }
        });
        setLocation();
    }

    private void setLocation() {
        mTvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LocationActivity.class));
            }
        });
    }

    private void setRefreshAndLoad() {
        mPullToListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                shopNewsList.clear();
                presenter.setUrl(ApiUtils.BASE_API+ApiUtils.SHOP_API1);
                mPullToListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToListView.onRefreshComplete();
                    }
                },1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                presenter.setUrl(ApiUtils.BASE_API+ApiUtils.SHOP_API2);
                mPullToListView.onRefreshComplete();
            }
        });
    }

    private void initDaoHangMenu() {
        //数据
        initData();
        /**Gridview塞入Viewpager中**/
        //1.获取每页的数量：2行4列
        int pageSize = getResources().getInteger(R.integer.HomePageHeaderColumn) * 2;
        //2.获取总的页数 总数/每页的数量，并取正math.ceil
        int pageCount = (int) Math.ceil(mDataList.size() * 1.0 / pageSize);
        //3.创建viewPager
        ViewPager viewPager = new ViewPager(getActivity());
        //4.创建viewpagerList集合
        final ArrayList<View> viewpagerList = new ArrayList<>();
        //5.根据数据数量创建grideview试图
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < pageCount; i++) {
            index = i;
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_viewpager, viewPager, false);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
            recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), mDataList, index, new RecyclerViewAdapter.onItemClickListener() {
                @Override
                public void onItemClick(View view, final int position) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] str =
                                    {"美食", "美团超市", "生鲜果蔬", "下午茶", "正餐优选", "汉堡披萨", "跑腿代沟", "快餐简餐", "地方菜", "炸鸡美食", "免费配送"};
                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            intent.putExtra("title_name",str[position]);
                            startActivity(intent);
                        }
                    });
                }
            }));
            //添加layout进viewpagerList
            viewpagerList.add(recyclerView);
        }

        //设置适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewpagerList));
        //TypedValue.applyDimension:转换尺寸单位为px(Android标准尺寸)
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, getResources().getDisplayMetrics()));
        viewPager.setLayoutParams(params);
        mAddView.addView(viewPager);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        mDataList.add(new HeaderViewBean(R.drawable.mieshi, "美食"));
        mDataList.add(new HeaderViewBean(R.drawable.market, "美团超市"));
        mDataList.add(new HeaderViewBean(R.drawable.shenxianguoshu, "生鲜果蔬"));
        mDataList.add(new HeaderViewBean(R.drawable.afternoon_tea, "下午茶"));
        mDataList.add(new HeaderViewBean(R.drawable.zhencan, "正餐优选"));
        mDataList.add(new HeaderViewBean(R.drawable.hanbao, "汉堡披萨"));
        mDataList.add(new HeaderViewBean(R.drawable.daigou, "跑腿代沟"));
        mDataList.add(new HeaderViewBean(R.drawable.kuaicanjiancan, "快餐简餐"));
        mDataList.add(new HeaderViewBean(R.drawable.bingjinlin, "地方菜"));
        mDataList.add(new HeaderViewBean(R.drawable.jitui, "炸鸡美食"));
        mDataList.add(new HeaderViewBean(R.drawable.maifei, "免费配送"));
    }

    private void initView(View view) {
        mTvLocation = (TextView) view.findViewById(R.id.tv_location);
        mTvSearch = (TextView) view.findViewById(R.id.tv_search);
        mPullToListView = (PullToRefreshListView) view.findViewById(R.id.pullToListView);
        mPullToListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void onSuccess(List<NearShopBean.DataBean> list) {
        shopNewsList.addAll(list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onError(String error) {
        Log.i(TAG, "onError: "+error);
    }

    @Override
    public void onDestroy() {
        presenter.onDestorys();
        super.onDestroy();
    }
}
