package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.SplashModule;
import com.example.lenovo.text_music.ui.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/4.
 */
@Singleton
@Component(modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
