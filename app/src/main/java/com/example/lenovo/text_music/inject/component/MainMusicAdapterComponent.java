package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.MainMusicAdapterModule;
import com.example.lenovo.text_music.ui.fragment.MainMusicFragment;

import dagger.Component;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Component(modules = MainMusicAdapterModule.class)
public interface   MainMusicAdapterComponent {
    void inject(MainMusicFragment activity);
}
