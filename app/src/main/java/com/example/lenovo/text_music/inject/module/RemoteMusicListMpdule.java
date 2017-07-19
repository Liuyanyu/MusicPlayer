package com.example.lenovo.text_music.inject.module;

import com.example.lenovo.text_music.presenter.contract.RemoteMusicListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2017/7/17.
 */
//生成注入对象  构造器所需参数
@Module
public class RemoteMusicListMpdule {
    RemoteMusicListContract.View view;

    public RemoteMusicListMpdule(RemoteMusicListContract.View view) {
        this.view = view;
    }

    @Provides
    public RemoteMusicListContract.View getViewObj() {
        return view;
    }
}
