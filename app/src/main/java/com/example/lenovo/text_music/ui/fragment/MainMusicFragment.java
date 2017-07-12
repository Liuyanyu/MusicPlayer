package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerMainMusicAdapterComponent;
import com.example.lenovo.text_music.inject.module.MainMusicAdapterModule;
import com.example.lenovo.text_music.presenter.contract.MusicMainContract;
import com.example.lenovo.text_music.presenter.impl.MusicMainParsenter;
import com.example.lenovo.text_music.ui.activity.MainActivity;
import com.example.lenovo.text_music.ui.adapter.SongListAdapter;
import com.example.lenovo.text_music.view.ScrollRecyclerLayoutManager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/4.
 */

public class MainMusicFragment extends BaseFragment implements MusicMainContract.View {
    @BindView(R.id.music_main_fragment_card_local)
    CardView musicMainFragmentCardLocal;
    @BindView(R.id.music_main_fragment_card_remote)
    CardView musicMainFragmentCardRemote;
    @BindView(R.id.music_main_fragment_card_download)
    CardView musicMainFragmentCardDownload;
    @BindView(R.id.music_main_fragment_card_lay)
    LinearLayout musicMainFragmentCardLay;
    @BindView(R.id.music_main_fragment_card_lately)
    CardView musicMainFragmentCardLately;
    @BindView(R.id.music_main_fragment_card_like)
    CardView musicMainFragmentCardLike;
    @BindView(R.id.music_main_fragment_card_recommend)
    CardView musicMainFragmentCardRecommend;
    @BindView(R.id.music_main_fragment_card_lay2)
    LinearLayout musicMainFragmentCardLay2;
    @BindView(R.id.music_main_fragment_song_list_title)
    LinearLayout musicMainFragmentSongListTitle;
    @BindView(R.id.fragment_music_main_list)
    RecyclerView fragmentMusicMainList;
    Unbinder unbinder;

    @Inject
    SongListAdapter songListAdapter;
    @Inject
    MusicMainParsenter parsenter;

    ArrayList<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = getList();
        DaggerMainMusicAdapterComponent
                .builder()
                .mainMusicAdapterModule(new MainMusicAdapterModule(getActivity(), list,this))
                .build()
                .inject(this);
        setListView();
    }

    private void setListView() {
        fragmentMusicMainList.setAdapter(songListAdapter);
        fragmentMusicMainList.setLayoutManager(new ScrollRecyclerLayoutManager(getActivity()));
        //recyclerView和ScrollView解决冲突方法
        fragmentMusicMainList.setNestedScrollingEnabled(false);
    }


    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("我想");
        list.add("当你");
        list.add("全部都是你");
        list.add("东京不太热");
        list.add("一个人的北京");
        list.add("避难所 ");
        list.add("歌单");
        list.add("朋友名义");
        list.add("眼里只有你");
        list.add("老街");
        list.add("飘洋过海来看你");
        list.add("回忆总想哭");
        list.add("拜托");
        list.add("我怀念的");
        list.add("成都");
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.music_main_fragment_card_local, R.id.music_main_fragment_card_remote, R.id.music_main_fragment_card_download, R.id.music_main_fragment_card_lately, R.id.music_main_fragment_card_like, R.id.music_main_fragment_card_recommend})
    public void onViewClicked(View view) {
        int id = 0;
        switch (view.getId()) {
            case R.id.music_main_fragment_card_local:
                id = R.id.music_main_fragment_card_local;
                break;
            case R.id.music_main_fragment_card_remote:
                id = R.id.music_main_fragment_card_remote;
                break;
            case R.id.music_main_fragment_card_download:
                id = R.id.music_main_fragment_card_download;
                break;
            case R.id.music_main_fragment_card_lately:
                id = R.id.music_main_fragment_card_lately;
                break;
            case R.id.music_main_fragment_card_like:
                id = R.id.music_main_fragment_card_like;
                break;
            case R.id.music_main_fragment_card_recommend:
                id = R.id.music_main_fragment_card_recommend;
                break;
        }
        parsenter.cardEnvnt(id);
    }

    @Override
    public void card2new(int id) {

        switch (id) {
            case R.id.music_main_fragment_card_local:
                ((MainActivity) getParentFragment().getActivity()).intent2local();
                break;
            case R.id.music_main_fragment_card_remote:
                break;
            case R.id.music_main_fragment_card_download:
                break;
            case R.id.music_main_fragment_card_lately:
                break;
            case R.id.music_main_fragment_card_like:
                break;
            case R.id.music_main_fragment_card_recommend:
                break;
        }

    }

    @Override
    public void song2new() {

    }
}
