package com.example.lenovo.text_music.http.service;

import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.http.API;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by lenovo on 2017/7/14.
 */

public interface RemoteMusicService {
    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<RemoteMusicListBean> getRestserverTing(@QueryMap Map<String, String> map);

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<RemoteMusicBean> getSongInfo(@QueryMap Map<String, String> map);
}
