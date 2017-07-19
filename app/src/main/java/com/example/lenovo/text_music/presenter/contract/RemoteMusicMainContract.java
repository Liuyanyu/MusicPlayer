package com.example.lenovo.text_music.presenter.contract;

import com.example.lenovo.text_music.bean.RemoteMusicListBean;

/**
 * Created by lenovo on 2017/7/14.
 */

public interface RemoteMusicMainContract {
    interface View{
        void onResponseNew(RemoteMusicListBean remoteMusicListBean);
        void onResponseHot(RemoteMusicListBean remoteMusicListBean);
        void onResponseChinese(RemoteMusicListBean remoteMusicListBean);
        void onResponseKtv(RemoteMusicListBean remoteMusicListBean);
    }
    interface Presenter{
        void getMusicMsg();
    }
}
