package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.MainModule;
import com.example.lenovo.text_music.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/4.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
