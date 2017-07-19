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

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.MusicBean;
import com.example.lenovo.text_music.callbreak.RecyclerClickListener;
import com.example.lenovo.text_music.inject.component.DaggerMusicLocalListComponent;
import com.example.lenovo.text_music.inject.module.MusicLocalListMoudle;
import com.example.lenovo.text_music.presenter.contract.MusicLocalListContract;
import com.example.lenovo.text_music.presenter.impl.MusicLocalListPresenter;
import com.example.lenovo.text_music.ui.activity.MainActivity;
import com.example.lenovo.text_music.ui.adapter.MusicListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/6.
 */

public class MusicListLocalFragment extends BaseFragment implements MusicLocalListContract.View {
    @BindView(R.id.music_local_list_title)
    TextView musicLocalListTitle;
    @BindView(R.id.music_local_list_list)
    RecyclerView musicLocalListList;
    Unbinder unbinder;

    private static int ITEM_D = 2;

    @Inject
    MusicLocalListPresenter musicLocalListPresenter;

    private MusicListAdapter musicListAdapter;

    ArrayList<MusicBean> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_music_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = getArguments().getParcelableArrayList(MainActivity.DATA);
        DaggerMusicLocalListComponent
                .builder()
                .musicLocalListMoudle(new MusicLocalListMoudle(getActivity(), this))
                .build()
                .inject(this);

        MyItemDecoration mid = new MyItemDecoration();
        musicLocalListList.addItemDecoration(mid);
        //初始化listview
        SetRecyclewView();
        musicLocalListPresenter.getLocalMusic();//查
    }

    private void SetRecyclewView() {
        musicListAdapter = new MusicListAdapter(getActivity(), list);
        musicListAdapter.setOnItemClickListener(new RecyclerClickListener() {
            @Override
            public void onItemClick(Object item) {
                //调用 activity中的 播放指定歌曲 方法
                ((MainActivity) getActivity()).playIndex((Integer) item);
            }
        });

        musicLocalListList.setAdapter(musicListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        musicLocalListList.setLayoutManager(llm);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setLocalMusic(ArrayList<MusicBean> list) {
        this.list.clear();
        this.list.addAll(list);

    }

    public void setPlay(int index) {
        //调用 适配器中的 设置正在播放歌曲的下标 并且刷新
        musicListAdapter.setBGIndex(index);
    }

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
