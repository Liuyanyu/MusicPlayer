package com.example.lenovo.text_music.presenter.contract;

import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;

/**
 * Created by lenovo on 2017/7/17.
 */

public interface RemoteMusicListContract {

    //给presente调用
    interface View{
        void onNtificationListData(RemoteMusicListBean list);
        void  onSongInfo(RemoteMusicBean remoteMusicBean);
    }

    //供给fragment调用
    interface Presenter{
        void getMusicList(int type, int offset, int size);
        void getSongInfoByID(String songId);

    }
}
