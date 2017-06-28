package com.example.lenovo.text_music.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.text_music.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/6/28.
 */

public class SplashActivity extends AppCompatActivity {
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intent2Main();
        }
    };

    public void intent2Main() {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    @BindView(R.id.splash_textview_tiaoguo)
    TextView splashTextviewTiaoguo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(1,3000);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.splash_textview_tiaoguo)
    public void onViewClicked() {
        handler.removeMessages(1);
        intent2Main();
    }
}
