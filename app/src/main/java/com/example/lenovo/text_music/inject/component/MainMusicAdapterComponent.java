package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.MainMusicAdapterModule;
import com.example.lenovo.text_music.ui.fragment.MainMusicFragment;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/4.
 */
@Component(modules = MainMusicAdapterModule.class)
public interface MainMusicAdapterComponent {
    void inject(MainMusicFragment activity);
}
