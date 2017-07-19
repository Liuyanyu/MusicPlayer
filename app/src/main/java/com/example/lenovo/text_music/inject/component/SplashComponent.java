package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.SplashModule;
import com.example.lenovo.text_music.ui.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Singleton
@Component(modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
