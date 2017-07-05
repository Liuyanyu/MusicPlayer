package com.example.lenovo.text_music.presenter.impl;

import com.example.lenovo.text_music.presenter.contract.MusicMainContract;

import javax.inject.Inject;

/**
 * Created by lenovo on 2017/7/5.
 */

public class MusicMainParsenter implements MusicMainContract.Presenter {
    private MusicMainContract.View view;

    @Inject
    public MusicMainParsenter(MusicMainContract.View view) {
        this.view = view;
    }

    @Override
    public void cardEnvnt(int id) {
        view.card2new(id);
    }

    @Override
    public void songListEnvnt() {
        view.song2new();
    }
}
