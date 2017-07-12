package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/11.
 */

public class MusicPlayButtoomFragment extends BaseFragment {

    @BindView(R.id.play_icon_image)
    ImageView playIconImage;
    @BindView(R.id.play_song_name)
    TextView playSongName;
    @BindView(R.id.play_song_artist)
    TextView playSongArtist;
    @BindView(R.id.play_last)
    ImageView playLast;
    @BindView(R.id.play_start)
    ImageView playStart;
    @BindView(R.id.play_next)
    ImageView playNext;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.play_start, R.id.play_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_start:
                ((MainActivity) getActivity()).playOnService();
                break;
            case R.id.play_next:
                break;
        }
    }
}
