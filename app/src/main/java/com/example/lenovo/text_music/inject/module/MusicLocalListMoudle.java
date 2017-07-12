package com.example.lenovo.text_music.inject.module;

import android.content.Context;

import com.example.lenovo.text_music.presenter.contract.MusicLocalListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2017/7/6.
 */

@Module
public class MusicLocalListMoudle {
    Context context;
    MusicLocalListContract.View view;

    public MusicLocalListMoudle(Context context, MusicLocalListContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Provides
    Context getContextProvides() {
        return context;
    }

    @Provides
    MusicLocalListContract.View getViewProvides() {
        return view;
    }
}
