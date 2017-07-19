package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.presenter.contract.RemoteMusicMainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2017/7/14.
 */
@Module
public class RemoteMusicMainModule {
    RemoteMusicMainContract.View view;

    public RemoteMusicMainModule(RemoteMusicMainContract.View view) {
        this.view = view;
    }

    @Provides
    public RemoteMusicMainContract.View getView() {
        return view;
    }
}
