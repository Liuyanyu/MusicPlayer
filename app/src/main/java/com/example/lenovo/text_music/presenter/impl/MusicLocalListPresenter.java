package com.example.lenovo.text_music.presenter.impl;

import android.content.Context;

import com.example.lenovo.text_music.inject.module.FileUtil;
import com.example.lenovo.text_music.presenter.contract.MusicLocalListContract;

import javax.inject.Inject;

/**
 * Created by lenovo on 2017/7/6.
 */

public class MusicLocalListPresenter implements MusicLocalListContract.Presenter {

    MusicLocalListContract.View view;
    Context context;
    FileUtil fileUtil;

    @Inject
    public MusicLocalListPresenter(MusicLocalListContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getLocalMusic() {
        //直接调用getmusicfiles
        view.setLocalMusic(fileUtil.getMusicFiles(context));

    }
}
