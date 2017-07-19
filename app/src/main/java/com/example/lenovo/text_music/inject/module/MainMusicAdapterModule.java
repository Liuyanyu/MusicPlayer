package com.example.lenovo.text_music.inject.module;

import android.content.Context;

import com.example.lenovo.text_music.presenter.contract.MainMusicContract;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yinm_pc on 2017/6/28.
 */

@Module
public class MainMusicAdapterModule {
    Context context;
    ArrayList<String> list;
    private MainMusicContract.View view;

    @Provides
    MainMusicContract.View getMusicMainPresenter(){
        return view;
    }

    public MainMusicAdapterModule(Context context, ArrayList<String> list, MainMusicContract.View view) {
        this.context = context;
        this.list = list;
        this.view=view;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    ArrayList<String> providesArrayList() {
        return list;
    }
}
