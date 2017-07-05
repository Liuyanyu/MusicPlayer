package com.example.lenovo.text_music.presenter.impl;

import android.os.Handler;
import android.os.Message;

import com.example.lenovo.text_music.presenter.contract.SplashContract;

/**
 * Created by lenovo on 2017/7/4.
 */

public class SplashPresenterImpl implements SplashContract.Presenter {
    //延时
    private static final int DELAYED_TIME = 3600;
    private static final int MESSAGE_WHAT = 103;


    private SplashContract.View view;

    //接到消息并且处理
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            view.intent2Act();
        }
    };

    public SplashPresenterImpl(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void timingBegin() {
        handler.sendEmptyMessageDelayed(MESSAGE_WHAT, DELAYED_TIME);
    }

    @Override
    public void startIntent() {
        //还没有发送的消息  删除掉消息
        handler.removeMessages(MESSAGE_WHAT);
        //直接跳转
        view.intent2Act();
    }
}
