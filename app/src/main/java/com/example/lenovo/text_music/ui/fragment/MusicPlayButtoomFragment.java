package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.MusicBean;
import com.example.lenovo.text_music.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/11.
 * 底部菜单 fragment
 */

public class MusicPlayButtoomFragment extends BaseFragment {

    @BindView(R.id.bottom_fragment_img)
    ImageView bottomFragmentImg;
    @BindView(R.id.bottom_fragment_progressBar)
    ProgressBar bottomFragmentProgressBar;
    @BindView(R.id.bottom_fragment_btn_close)
    ImageView bottomFragmentBtnClose;
    @BindView(R.id.bottom_fragment_name)
    TextView bottomFragmentName;
    @BindView(R.id.bottom_fragment_info)
    TextView bottomFragmentInfo;
    @BindView(R.id.bottom_fragment_btn_play)
    ImageView bottomFragmentBtnPlay;
    @BindView(R.id.bottom_fragment_btn_next)
    ImageView bottomFragmentBtnNext;
    Unbinder unbinder;


    //加载布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bottom_fragment_btn_close, R.id.bottom_fragment_btn_play, R.id.bottom_fragment_btn_next})
    public void onViewClicked(View view) {
        //获取 activity对象
        MainActivity ma = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.bottom_fragment_btn_play:
                //调用 activiy中的方法
                ma.playBtn(0);
                break;
            case R.id.bottom_fragment_btn_next:
                ma.playBtn(1);
                break;
            case R.id.bottom_fragment_btn_close:
                ma.playBtn(2);
                break;
        }
    }
    /**
     * 设置 播放按钮 状态
     * @param isPlaying true 表示正在播放
     */
    public void setPlaying(boolean isPlaying) {
        if (isPlaying) {
            bottomFragmentBtnPlay.setImageResource(R.drawable.p_btn);
        } else {
            bottomFragmentBtnPlay.setImageResource(R.drawable.play_btn);
        }
    }
    /**
     * 设置歌曲信息
     * 文件名
     * 歌手
     * @param musicInfo
     */
    public void setMusicInfo(MusicBean musicInfo) {
        bottomFragmentInfo.setText(musicInfo.getMusic_name());
        bottomFragmentName.setText(musicInfo.getMusic_artst());
    }


}
