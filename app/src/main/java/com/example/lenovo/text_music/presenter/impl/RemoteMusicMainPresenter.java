package com.example.lenovo.text_music.presenter.impl;

import android.util.Log;

import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.http.HttpUtils;
import com.example.lenovo.text_music.presenter.contract.RemoteMusicMainContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2017/7/14.
 */

public class RemoteMusicMainPresenter implements RemoteMusicMainContract.Presenter {
    RemoteMusicMainContract.View view;

    @Inject
    public RemoteMusicMainPresenter(RemoteMusicMainContract.View view) {
        this.view = view;

    }

    @Override
    public void getMusicMsg() {
                        Map<String, String> map = new HashMap<>();
                        map.put("from", "qianqian");
                        map.put("version", "2.1.0");
                        map.put("method", "baidu.ting.billboard.billList");
                        map.put("format", "json");
                        map.put("type", "1");
                        map.put("offset", "0");
                        map.put("size", "3");

                        HttpUtils
                                .getInstance()
                                .getRestserver(new Callback<RemoteMusicListBean>() {
                                                   @Override
                                                   public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
                                                       view.onResponseNew(response.body());
                                                       Log.e("aa",response.body().toString());
                                                   }

                                                  @Override
                                                  public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {

                                                  }
                                              }
                , map);

        map.put("type", "2");
        HttpUtils.getInstance().getRestserver(new Callback<RemoteMusicListBean>() {
            @Override
            public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
                view.onResponseHot(response.body());
            }

            @Override
            public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {

            }
        },map);


        map.put("type", "18");
        HttpUtils.getInstance().getRestserver(new Callback<RemoteMusicListBean>() {
            @Override
            public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
                view.onResponseChinese(response.body());
            }

            @Override
            public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {

            }
        },map);

        map.put("type", "6");
        HttpUtils.getInstance().getRestserver(new Callback<RemoteMusicListBean>() {
            @Override
            public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
                view.onResponseKtv(response.body());
            }

            @Override
            public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {

            }
        },map);
    }
}
