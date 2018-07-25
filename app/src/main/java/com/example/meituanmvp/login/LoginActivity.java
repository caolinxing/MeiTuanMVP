package com.example.meituanmvp.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meituanmvp.R;
import com.example.meituanmvp.main.MainActivity;
import com.kakao.util.helper.log.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity implements loginView, View.OnClickListener {
    //设置密码不可见
    private boolean isPwdVisible = false;
    private EditText edi_username;
    private EditText edi_pwd;
    private ImageView iv_eye;
    private Button btn_login;
    private TextView mLoginTvDisanfang;
    private ImageView iv_qq;
    private ImageView iv_weinxin;
    private ImageView iv_zhanghao;
    private TextView tv_cancle;
    private PopupWindow popupWindow;
    private ProgressDialog dialog;
    private SHARE_MEDIA platform = SHARE_MEDIA.QQ;
    private UMShareAPI shareAPI;
    private UMAuthListener authListener;
    private String USER_MESSAGE="USER_MESSAGE";
    private SharedPreferences sp;
    private SharedPreferences.Editor  edit;
    private String LOGIN_STATE = "LOGIN_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //动态权限
        setPression();
        //初始化控件
        findView();
        setClickListener();
        //友盟
        uMeng();
    }

    private void uMeng() {
        dialog = new ProgressDialog(this);
        shareAPI = UMShareAPI.get(LoginActivity.this);
        authListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                SocializeUtils.safeShowDialog(dialog);
            }
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                //添加动态权限
                SocializeUtils.safeCloseDialog(dialog);
                Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Set<String> keySet = data.keySet();
                //创建轻量级存储---个人信息
                sp = getSharedPreferences(USER_MESSAGE,MODE_PRIVATE);
                //轻量级存储
                edit = sp.edit();
                for (String string:keySet) {
                    String str = data.get(string);

                    if (string.equals("name")){
                        edit.putString("name",str);
                    }
                    if (string.equals("iconurl")){
                        edit.putString("img",str);
                    }
                    if (string.equals("gender")){
                        edit.putString("gender",str);
                    }
                }
                edit.commit();
                //创建轻量级存储---登入状态
                SharedPreferences sp1 = getSharedPreferences(LOGIN_STATE, MODE_PRIVATE);
                //轻量级存储
                SharedPreferences.Editor edit1 = sp1.edit();
                edit1.putBoolean("login_state",true);
                edit1.commit();
                Logger.e(data.toString());
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                SocializeUtils.safeCloseDialog(dialog);
                Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
            public void onCancel(SHARE_MEDIA platform, int action) {
                SocializeUtils.safeCloseDialog(dialog);
                Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
            }
        };
    }

    private void setPression() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
                            Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
                            Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    private void setClickListener() {
        mLoginTvDisanfang.setOnClickListener(this);
    }

    private void findView() {
        edi_username = findViewById(R.id.edi_username);
        edi_pwd = findViewById(R.id.edi_pwd);
        iv_eye = findViewById(R.id.iv_eye);
        btn_login = findViewById(R.id.btn_login);
        mLoginTvDisanfang = (TextView) findViewById(R.id.login_tv_disanfang);
    }


    public void back(View view) {
        finish();
    }

    public void isShowPwd(View view) {
        //修改密码是否可见状态
        isPwdVisible = !isPwdVisible;
        if (isPwdVisible) {
            //设置密码为明文模式并切换眼睛图标
            edi_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            iv_eye.setImageResource(R.mipmap.eyes_o);
        } else {
            //设置密码为暗文模式并切换眼睛图标
            edi_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            iv_eye.setImageResource(R.mipmap.eye_off);
        }
    }

    @Override
    public void usernameError() {
        if (TextUtils.isEmpty(edi_username.getText().toString())) {

        } else {
            Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void passwordError() {
        if (TextUtils.isEmpty(edi_pwd.getText().toString())) {

        } else {
            Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void successNavigateToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tv_disanfang:
                onCreatePop();
            break;
            case R.id.tv_cancle:
                popupWindow.dismiss();
                break;
            case R.id.iv_qq:
                shareAPI.getPlatformInfo(LoginActivity.this, platform, authListener);
                popupWindow.dismiss();
                break;
            case R.id.iv_weixin:
                popupWindow.dismiss();
                break;
        }
    }

    private void onCreatePop() {
        View popview = View.inflate(this, R.layout.pop_layout,null);
        iv_qq = (ImageView)popview.findViewById(R.id.iv_qq);
        iv_weinxin = (ImageView)popview.findViewById(R.id.iv_weixin);
        iv_zhanghao = (ImageView)popview.findViewById(R.id.iv_zhanghao);
        tv_cancle = (TextView)popview.findViewById(R.id.tv_cancle);
        popupWindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //可点击
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        //设置背景
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //获取属性
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                attributes.alpha=1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(attributes);
            }
        });
        //设置动画（下——>上）
        popupWindow.setAnimationStyle(R.style.pop_animation);
        //显示试图及其位置
        popupWindow.showAtLocation(popview, Gravity.BOTTOM,0,0);
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        tv_cancle.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        iv_weinxin.setOnClickListener(this);
        iv_zhanghao.setOnClickListener(this);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shareAPI.onActivityResult(requestCode, resultCode, data);
        Logger.e(" " + requestCode + "..." +resultCode );
        // Logger.e(data.getDataString());
    }
    //回调函数
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 123){
            Logger.e("dddddddddddddd");
        }
    }

    @Override
    public void successToOther() {

    }
}
