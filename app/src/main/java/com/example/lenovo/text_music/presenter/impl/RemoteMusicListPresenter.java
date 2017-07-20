package com.example.lenovo.text_music.presenter.impl;

import android.util.Log;

import com.example.lenovo.text_music.PunlicFlags;
import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.http.HttpUtils;
import com.example.lenovo.text_music.presenter.contract.RemoteMusicListContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2017/7/17.
 */

public class RemoteMusicListPresenter implements RemoteMusicListContract.Presenter {
    RemoteMusicListContract.View view;
    PunlicFlags flags;

    @Inject
    public RemoteMusicListPresenter(RemoteMusicListContract.View view) {
        this.view = view;
    }

    @Override
    public void getMusicList(int type, int offset, int size) {
        Map<String, String> map = new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.billboard.billList");
        map.put("format", "json");
        map.put("type", String.valueOf(type));
        map.put("offset", String.valueOf(offset));
        map.put("size", String.valueOf(size));

        HttpUtils.getInstance().getRestserver(new Callback<RemoteMusicListBean>() {
            @Override
            public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
                view.onNtificationListData(response.body());
                Log.e("onResponse", "========" + response.body().getSong_list().size());
            }

            @Override
            public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {

            }
        }, map);
    }

    @Override
    public void getSongInfoByID(String songId) {
        Map<String, String> map = new HashMap<>();
        map.put("from", "webapp_music");
        map.put("method", "baidu.ting.song.playAAC");
        map.put("format", "json");
        map.put("calback", "");
        map.put("songid",songId);

        HttpUtils
                .getInstance()
                .getSongInfo(
                        new Callback<RemoteMusicBean>() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                view.onSongInfo((RemoteMusicBean) response.body());
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Log.e(t.getLocalizedMessage(),t.toString());
                            }
                        },map)
        ;
    }
}

