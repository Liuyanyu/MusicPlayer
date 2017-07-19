package com.example.lenovo.text_music.presenter.contract;

/**
 * 启动页相关接口
 * Created by yinm_pc on 2017/6/28.
 */

public interface SplashContract {
    /**
     * view接口
     */
    interface View {
        void intent2Act();
    }

    /**
     * presenter接口
     */
    interface Presenter {
        void timingBegin();

        void startIntent();
    }
}
