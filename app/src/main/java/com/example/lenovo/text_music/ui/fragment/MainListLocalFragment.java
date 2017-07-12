package com.example.lenovo.text_music.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerMusicLocalListComponent;
import com.example.lenovo.text_music.inject.module.MusicLocalListMoudle;
import com.example.lenovo.text_music.presenter.contract.MusicLocalListContract;
import com.example.lenovo.text_music.presenter.impl.MusicLocalListPresenter;
import com.example.lenovo.text_music.ui.adapter.MainListAdapter;
import com.example.lenovo.text_music.view.MusicBean;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/6.
 */

public class MainListLocalFragment extends BaseFragment implements MusicLocalListContract.View {
    @BindView(R.id.music_local_list_title)
    TextView musicLocalListTitle;
    @BindView(R.id.music_local_list_list)
    RecyclerView musicLocalListList;
    Unbinder unbinder;

    MainListAdapter mainListAdapter;

    @Inject
    MusicLocalListPresenter musicLocalListPresenter;

    ArrayList<MusicBean> list = new ArrayList<>();

    private static final int ITEM_D = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_local_music, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerMusicLocalListComponent
                .builder()
                .musicLocalListMoudle(new MusicLocalListMoudle(getActivity(), this))
                .build()
                .inject(this);
        SetRecyclewView();
        musicLocalListPresenter.getLocalMusic();
        musicLocalListList.setItemAnimator(new DefaultItemAnimator());
        musicLocalListList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                Paint paint = new Paint();
                paint.setColor(getResources().getColor(R.color.fengexian));
                int left = parent.getPaddingLeft();
                int right = parent.getMeasuredWidth();
                int size = parent.getChildCount();
                for (int i = 0; i < size; i++) {
                    View child = parent.getChildAt(i);
                    int top = child.getBottom();
                    int bottom = top + ITEM_D;
                    c.drawRect(left, top, right, bottom, paint);
                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override         //偏移量
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(1, 1, 1, ITEM_D);

            }
        });

    }

    private void SetRecyclewView() {
        mainListAdapter = new MainListAdapter(getActivity(), list);
        musicLocalListList.setAdapter(mainListAdapter);
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
        mainListAdapter.notifyDataSetChanged();
    }

}
