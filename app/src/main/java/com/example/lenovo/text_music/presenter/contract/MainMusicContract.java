package com.example.lenovo.text_music.presenter.contract;

/**
 * Created by lenovo on 2017/7/5.
 */

public interface MainMusicContract {
    interface View {
        void card2new(int id);
        void song2new();
    }

    interface Presenter {
        void cardEnvnt(int id);

        void songListEnvnt();
    }
}
