package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.ui.fragment.MainLiveFragment;
import com.example.lenovo.text_music.ui.fragment.MainMusicFragment;
import com.example.lenovo.text_music.ui.fragment.MainVideoFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Module
public class MainModule {
    @Provides
    @Singleton
    MainMusicFragment providesMainFragment() {
        return new MainMusicFragment();
    }

    @Provides
    @Singleton
    MainVideoFragment providesMainVideoFragment() {
        return new MainVideoFragment();
    }

    @Provides
    @Singleton
    MainLiveFragment providesMainLiveFragment() {
        return new MainLiveFragment();
    }
}
