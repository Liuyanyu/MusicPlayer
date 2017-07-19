package com.example.lenovo.text_music.manager;

import com.example.lenovo.text_music.callbreak.MusicStateListener;

/**
 * Created by lenovo on 2017/7/12.
 */

public class MusicManager {

    //监听对象
    private static MusicStateListener musicStateListener;

    //注册监听
    public static void registerListener(MusicStateListener musicStateListener) {
        MusicManager.musicStateListener = musicStateListener;
    }

    //取消注册
    public static void unregisterListener() {
        MusicManager.musicStateListener = null;
    }

    //获取监听对象
    public static MusicStateListener getListener(){
        return musicStateListener;
    }

}
