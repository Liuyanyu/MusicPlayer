package com.example.lenovo.text_music.presenter.impl;

import com.example.lenovo.text_music.presenter.contract.MainMusicContract;

import javax.inject.Inject;

/**
 * Created by lenovo on 2017/7/5.
 */

public class MainMusicPresenter implements MainMusicContract.Presenter {
    private MainMusicContract.View view;

    @Inject
    public MainMusicPresenter(MainMusicContract.View view) {
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
