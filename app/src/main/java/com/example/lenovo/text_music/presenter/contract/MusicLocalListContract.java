package com.example.lenovo.text_music.presenter.contract;

import com.example.lenovo.text_music.bean.MusicBean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/6.
 */

public interface MusicLocalListContract {
    interface View{
        void setLocalMusic(ArrayList<MusicBean> list);
    }
    interface Presenter{
        void getLocalMusic();
    }
}
