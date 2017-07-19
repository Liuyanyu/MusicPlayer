package com.example.lenovo.text_music.callbreak;

/**
 * Created by yinm_pc on 2017/3/2.
 * 歌曲状态监听 接口
 */

public interface MusicStateListener {
    void playStateChanged(boolean isPlaying);
    void playingIndex(int index);
    void currentPositionAndDuration(int CurrentPosition, int Duration);
}
