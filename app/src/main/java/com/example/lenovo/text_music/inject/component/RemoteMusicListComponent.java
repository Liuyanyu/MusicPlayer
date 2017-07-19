package com.example.lenovo.text_music.inject.component;

import com.example.lenovo.text_music.inject.module.RemoteMusicListMpdule;
import com.example.lenovo.text_music.ui.fragment.RemoteMusicListFragment;

import dagger.Component;

/**
 * Created by lenovo on 2017/7/17.
 */
@Component(modules = RemoteMusicListMpdule.class)
public interface RemoteMusicListComponent {
    void inject(RemoteMusicListFragment remoteMusicListFragment);
}
