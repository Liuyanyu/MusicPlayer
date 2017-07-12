package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.MusicLocalListMoudle;
import com.example.lenovo.text_music.ui.fragment.MainListLocalFragment;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/6.
 */

@Component(modules = MusicLocalListMoudle.class)
public interface MusicLocalListComponent {
    void inject(MainListLocalFragment musicListLocalFragment);
}
