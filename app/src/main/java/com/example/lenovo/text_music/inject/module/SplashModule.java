package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.presenter.contract.SplashContract;
import com.example.lenovo.text_music.presenter.impl.SplashPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2017/7/4.
 */
@Module
public class SplashModule {
     SplashContract.Presenter splashpresenter;

    public SplashModule(SplashContract.View activity){
        this.splashpresenter=new SplashPresenterImpl(activity);
    }

    @Provides
    @Singleton
    SplashContract.Presenter provideMainPreesenter(){
        return splashpresenter;
    }
}
