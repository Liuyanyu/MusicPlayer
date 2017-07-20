package com.example.lenovo.text_music.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicListBean;
import com.example.lenovo.text_music.callback.OnRecyclerItemClick;
import com.example.lenovo.text_music.inject.component.DaggerRemoteMusicListComponent;
import com.example.lenovo.text_music.inject.module.RemoteMusicListMpdule;
import com.example.lenovo.text_music.presenter.contract.RemoteMusicListContract;
import com.example.lenovo.text_music.presenter.impl.RemoteMusicListPresenter;
import com.example.lenovo.text_music.ui.activity.MainActivity;
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

    private static int ITEM_D = 2;

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

        RemoteMusicListFragment.MyItemDecoration mid = new RemoteMusicListFragment.MyItemDecoration();
        musicRemoteList.addItemDecoration(mid);
    }

    private void setRecyclerView() {
        remoteMusicListAdapter = new RemoteMusicListAdapter(getActivity(), list);
        remoteMusicListAdapter.setOnRecyclerItemClick(new OnRecyclerItemClick<RemoteMusicListBean.SongListBean>() {
            @Override
            public void onClick(RemoteMusicListBean.SongListBean item) {
                item.getSong_id();
                //TODO 根据songid 请求歌曲信息
                remoteMusicListPresenter.getSongInfoByID(item.getSong_id());

            }
        });

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
        requset_size += REQUST_SIZE;
    }
    @Override
    public void onSongInfo(RemoteMusicBean remoteMusicBean) {
        ((MainActivity)getActivity()).setUrl2Service(remoteMusicBean);
    }

    //设置分割线
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.music_fragment_card_bg));
            int left = parent.getPaddingLeft();
            int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            int size = parent.getChildCount();
            for (int i = 0; i < size; i++) {
                View child = parent.getChildAt(i);
                int top = child.getBottom();
                int bottom = top + ITEM_D;
                //设置矩形的左。上。右。下边界坐标  以及画笔
                c.drawRect(left, top, right, bottom, paint);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, ITEM_D);
        }
    }

}
