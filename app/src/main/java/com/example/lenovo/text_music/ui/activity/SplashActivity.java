package com.example.lenovo.text_music.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerSplashComponent;
import com.example.lenovo.text_music.inject.module.SplashModule;
import com.example.lenovo.text_music.presenter.contract.SplashContract;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.example.lenovo.iplay.inject.component.DaggerSplashComponent;

/**
 * 启动页
 * Created by yinm_pc on 2017/6/28.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @BindView(R.id.splash_txt)
    TextView splashTxt;
    @BindView(R.id.splash_img)
    ImageView splashImg;

    //presenter层对象  接口类型
    @Inject
    SplashContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //绑定当前页面根布局
        ButterKnife.bind(this);

        DaggerSplashComponent
                .builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
        //开始计时跳转
        presenter.timingBegin();
    }

    //点击事件
    @OnClick({R.id.splash_txt, R.id.splash_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.splash_txt:
                //直接跳转
                presenter.startIntent();
                break;
            case R.id.splash_img:
                break;
        }
    }


    //跳转方法
    @Override
    public void intent2Act() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
