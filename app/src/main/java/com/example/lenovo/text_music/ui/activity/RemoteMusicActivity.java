package com.example.lenovo.text_music.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.ui.fragment.Remote_Music_Image_Fragment;
import com.example.lenovo.text_music.ui.fragment.Remote_Music_Lyric_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2017/7/21.
 */

public class RemoteMusicActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.music_local_list_title)
    TextView musicLocalListTitle;
    @BindView(R.id.remote_toolbar)
    Toolbar remoteToolbar;
    @BindView(R.id.remote_music_name)
    TextView remoteMusicName;
    @BindView(R.id.remote_last_img)
    ImageView remoteLastImg;
    @BindView(R.id.remote_play_img)
    ImageView remotePlayImg;
    @BindView(R.id.remote_next_img)
    ImageView remoteNextImg;
    @BindView(R.id.like_age_img)
    ImageView likeAgeImg;
    @BindView(R.id.history_img)
    ImageView historyImg;
    @BindView(R.id.remote_circle_img)
    ImageView remoteCircleImg;
    @BindView(R.id.remote_music_layout2)
    LinearLayout remoteMusicLayout2;
    @BindView(R.id.remote_music_layout)
    RelativeLayout remoteMusicLayout;
    @BindView(R.id.remote_tab)
    TabLayout remoteTab;
    @BindView(R.id.remote_viewpager)
    ViewPager remoteViewpager;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Remote_Music_Image_Fragment remote_music_image_fragment;
    private Remote_Music_Lyric_Fragment remote_music_lyric_fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romote_music);
        ButterKnife.bind(this);
        getFragment();
        setFragmentPagerAdapter();
    }

    private void getFragment() {
        remote_music_image_fragment = new Remote_Music_Image_Fragment();
        remote_music_lyric_fragment = new Remote_Music_Lyric_Fragment();
        fragments.add(remote_music_image_fragment);
        fragments.add(remote_music_lyric_fragment);
    }

    private void setFragmentPagerAdapter() {
        FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        remoteViewpager.setAdapter(fpa);
        remoteTab.setupWithViewPager(remoteViewpager);

        remoteTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        remoteViewpager.setOffscreenPageLimit(1);
        remoteViewpager.addOnPageChangeListener(this);
    }

    @OnClick({R.id.remote_last_img, R.id.remote_play_img, R.id.remote_next_img, R.id.like_age_img, R.id.history_img, R.id.remote_circle_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remote_last_img:
                break;
            case R.id.remote_play_img:
                break;
            case R.id.remote_next_img:
                break;
            case R.id.like_age_img:
                break;
            case R.id.history_img:
                break;
            case R.id.remote_circle_img:
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
