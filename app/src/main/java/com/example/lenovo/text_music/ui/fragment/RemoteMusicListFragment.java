package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.text_music.PunlicFlags;
import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.inject.component.DaggerRemoteMusicListComponent;
import com.example.lenovo.text_music.inject.module.RemoteMusicListMpdule;
import com.example.lenovo.text_music.presenter.contract.RemoteMusicListContract;
import com.example.lenovo.text_music.presenter.impl.RemoteMusicListPresenter;
import com.example.lenovo.text_music.ui.adapter.RemoteMusicListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/16.
 */

public class RemoteMusicListFragment extends BaseFragment implements RemoteMusicListContract.View {
    @BindView(R.id.music_remote_list_title)
    TextView musicRemoteListTitle;
    @BindView(R.id.music_remote_list)
    RecyclerView musicRemoteList;
    Unbinder unbinder;

    RemoteMusicListAdapter remoteMusicListAdapter;

    ArrayList<RemoteMusicListBean.SongListBean> list = new ArrayList<>();

    @Inject
    RemoteMusicListPresenter remoteMusicListPresenter;

    private static final int REQUST_SIZE = 50;

    private int requset_size = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_music_main_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();

        DaggerRemoteMusicListComponent
                .builder()
                .remoteMusicListMpdule(new RemoteMusicListMpdule(this))
                .build()
                .inject(this);

        int type = getArguments().getInt(PunlicFlags.MUSIC_API_TYPE);

        switch (type) {
            case PunlicFlags.MUSIC_API_TYPE_NEW:
                musicRemoteListTitle.setText(R.string.fragment_remote_list_title_new);
                break;
            case PunlicFlags.MUSIC_API_TYPE_HOT:
                musicRemoteListTitle.setText(R.string.fragment_remote_list_title_hot);
                break;
            case PunlicFlags.MUSIC_API_TYPE_CHINESE:
                musicRemoteListTitle.setText(R.string.fragment_remote_list_title_chinese);
                break;
            case PunlicFlags.MUSIC_API_TYPE_KTV:
                musicRemoteListTitle.setText(R.string.fragment_remote_list_title_ktv);
                break;
        }
        remoteMusicListPresenter.getMusicList(type, requset_size, REQUST_SIZE);
    }

    private void setRecyclerView() {
        remoteMusicListAdapter = new RemoteMusicListAdapter(getActivity(), list);
        musicRemoteList.setAdapter(remoteMusicListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        musicRemoteList.setLayoutManager(llm);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //处理请求结果
    @Override
    public void onNtificationListData(RemoteMusicListBean bean) {
        //TODO 刷新recyclrview 数据
        list.addAll(bean.getSong_list());
        remoteMusicListAdapter.notifyDataSetChanged();
//        requset_size += REQUST_SIZE;

    }
}
