package com.example.lenovo.text_music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerSplashComponent;
import com.example.lenovo.text_music.inject.module.SplashModule;
import com.example.lenovo.text_music.presenter.contract.SplashContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * butterknife  MVP  mvvm   retrofit请求网络框架  dagger2依赖注入框架   rxjava  rxandroid
 * Created by lenovo on 2017/6/28.
 * a调用b 将a注入到c
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.splash_textview_tiaoguo)
    TextView splashTextviewTiaoguo;

    @Inject  //注解
            SplashContract.Presenter presenter;  //注入

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        DaggerSplashComponent
                .builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
        //开始计时跳转
        presenter.timingBegin();
    }

    @Override
    public void intent2Act() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //点击事件
    @OnClick(R.id.splash_textview_tiaoguo)
    public void onViewClicked() {
        presenter.startIntent();
    }

    //返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
