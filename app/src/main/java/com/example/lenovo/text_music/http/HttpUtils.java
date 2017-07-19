package com.example.lenovo.text_music.http;

import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.http.service.RemoteMusicService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/7/14.
 */

public class HttpUtils {

    private static HttpUtils httpUtils;
    private RemoteMusicService service;

    public static synchronized HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    private RemoteMusicService getService() {
        if (service==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return service = retrofit.create(RemoteMusicService.class);
        }else {
            return service;
        }
    }

    public void getRestserver(Callback<RemoteMusicListBean> callback,Map<String,String> map){
        getService();
        Call<RemoteMusicListBean> call = service.getRestserverTing(map);
        call.enqueue(callback);

//        try {
//            RemoteMusicListBean body = call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        call.enqueue(new Callback<RemoteMusicListBean>() {
//            @Override
//            public void onResponse(Call<RemoteMusicListBean> call, Response<RemoteMusicListBean> response) {
//                Log.e("aa",response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<RemoteMusicListBean> call, Throwable t) {
//
//            }
//        });

    }
}
