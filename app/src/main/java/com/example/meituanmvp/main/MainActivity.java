package com.example.meituanmvp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.meituanmvp.R;
import com.example.meituanmvp.fragment.Fragment_dingdan;
import com.example.meituanmvp.fragment.home.view.Fragment_home;
import com.example.meituanmvp.fragment.mine.Fragment_mine;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RadioButton mRadioHome;
    private RadioButton mRadioDingdan;
    private RadioButton mRadioMine;
    private FragmentManager manager;
    private Fragment_home fragment_home;
    private Fragment_dingdan fragment_dingdan;
    private Fragment_mine fragment_mine;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        Intent intent = getIntent();
        location = intent.getStringExtra("location");
        manager = getSupportFragmentManager();
        //初始化fragment页面
        initShowFragment();
        //切换fragment监听
        setRadioGroupListener();
    }

    private void setRadioGroupListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                if (fragment_home!=null){
                    transaction.hide(fragment_home);
                }
                if (fragment_dingdan!=null){
                    transaction.hide(fragment_dingdan);
                }
                if (fragment_mine!=null){
                    transaction.hide(fragment_mine);
                }
                switch (checkedId){
                    case R.id.radio_home:
                        if (fragment_home==null){
                            fragment_home = new Fragment_home();
                            transaction.add(R.id.main_frame_layout,fragment_home);
                        }else {
                            transaction.show(fragment_home);
                        }
                        break;
                    case R.id.radio_dingdan:
                        if (fragment_dingdan==null){
                            fragment_dingdan = new Fragment_dingdan();
                            transaction.add(R.id.main_frame_layout,fragment_dingdan);
                        }else {
                            transaction.show(fragment_dingdan);
                        }
                        break;
                    case R.id.radio_mine:
                        if (fragment_mine==null){
                            fragment_mine = new Fragment_mine();
                            transaction.add(R.id.main_frame_layout,fragment_mine);
                        }else {
                            transaction.show(fragment_mine);
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void initShowFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("location",location);
        fragment_home = new Fragment_home();
        transaction.add(R.id.main_frame_layout,fragment_home).commit();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioHome = (RadioButton) findViewById(R.id.radio_home);
        mRadioDingdan = (RadioButton) findViewById(R.id.radio_dingdan);
        mRadioMine = (RadioButton) findViewById(R.id.radio_mine);
    }
}
