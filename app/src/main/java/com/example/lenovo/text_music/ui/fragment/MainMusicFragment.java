package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.ScrollRecyclerLayoutManager;
import com.example.lenovo.text_music.inject.component.DaggerMainMusicAdapterComponent;
import com.example.lenovo.text_music.inject.module.MainMusicAdapterModule;
import com.example.lenovo.text_music.presenter.contract.MainMusicContract;
import com.example.lenovo.text_music.presenter.impl.MainMusicPresenter;
import com.example.lenovo.text_music.ui.activity.MainActivity;
import com.example.lenovo.text_music.ui.adapter.SongListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//import com.example.lenovo.iplay.inject.component.DaggerMainMusicAdapterComponent;

/**
 * Created by yinm_pc on 2017/6/28.
 */
public class MainMusicFragment extends BaseFragment implements MainMusicContract.View {
    @BindView(R.id.music_main_fragment_card_local)
    CardView cardLocal;
    @BindView(R.id.music_main_fragment_card_remote)
    CardView cardRemote;
    @BindView(R.id.music_main_fragment_card_download)
    CardView cardDownload;
    @BindView(R.id.music_main_fragment_card_lately)
    CardView cardLately;
    @BindView(R.id.music_main_fragment_card_like)
    CardView cardLike;
    @BindView(R.id.music_main_fragment_card_recommend)
    CardView cardRecommend;
    @BindView(R.id.fragment_music_main_list)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @Inject
    SongListAdapter songListAdapter;

    ArrayList<String> list;


    @Inject
    MainMusicPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_main, container, false);
        //绑定当前页面根布局
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = getList();

        DaggerMainMusicAdapterComponent
                .builder()
                .mainMusicAdapterModule(new MainMusicAdapterModule(getActivity(), list, this))
                .build()
                .inject(this);

        setListView();
    }

    private void setListView() {
        recyclerView.setAdapter(songListAdapter);
        recyclerView.setLayoutManager(new ScrollRecyclerLayoutManager(getActivity()));
        //recyclerView和ScrollView解决冲突方法
        recyclerView.setNestedScrollingEnabled(false);
    }

    @NonNull
    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("歌单1");
        list.add("歌单2");
        list.add("歌单3");
        list.add("歌单4");
        list.add("歌单5");
        list.add("歌单6");
        list.add("歌单7");
        list.add("歌单8");
        list.add("歌单9");
        list.add("歌单10");
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定当前页面根布局  fragment中需要调用此方法
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
        presenter.cardEnvnt(id);
    }

    @Override
    public void card2new(int id) {
        switch (id) {
            case R.id.music_main_fragment_card_local:
                ((MainActivity) getParentFragment().getActivity()).intent2local();
                break;
            case R.id.music_main_fragment_card_remote:
                ((MainActivity) getParentFragment().getActivity()).intent2remote();
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
