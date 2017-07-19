package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.presenter.contract.SplashContract;
import com.example.lenovo.text_music.presenter.impl.SplashPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Module
public class SplashModule {
    SplashContract.Presenter splashPresenter;

    public SplashModule(SplashContract.View activity) {
        this.splashPresenter = new SplashPresenter(activity);
    }

    @Provides
    @Singleton
    SplashContract.Presenter providesMainPresenter() {
        return splashPresenter;
    }
}
