package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.RemoteMusicMainModule;
import com.example.lenovo.text_music.ui.fragment.RemoteMusicMainFragment;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/14.
 */
@Component(modules = RemoteMusicMainModule.class)
public interface RemoteMusicMainComponent {
    void inject(RemoteMusicMainFragment remoteMusicMainFragment);
}
