package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.MainModule;
import com.example.lenovo.text_music.ui.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainFragment activity);
}
