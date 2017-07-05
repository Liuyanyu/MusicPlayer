package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.ui.fragment.MainLiveFragment;
import com.example.lenovo.text_music.ui.fragment.MainMusicFragment;
import com.example.lenovo.text_music.ui.fragment.MainVideoFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2017/7/4.
 */

@Module
public class MainModule {
    @Provides
    @Singleton
    MainMusicFragment providesMainMusicFragment() {
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
