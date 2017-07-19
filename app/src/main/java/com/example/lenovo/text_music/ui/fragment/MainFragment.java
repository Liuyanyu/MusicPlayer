package com.example.lenovo.text_music.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerMainComponent;
import com.example.lenovo.text_music.inject.module.MainModule;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2017/7/5.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.main_viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;

    private ArrayList<BaseFragment> fragments = new ArrayList<>();

    @Inject
    MainMusicFragment musicFragment;
    @Inject
    MainVideoFragment videoFragment;
    @Inject
    MainLiveFragment liveFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerMainComponent
                .builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);

        //添加fragment到集合中
        addFragment();
        // 设置viewpager
        setViewpager();

    }

    private void setViewpager() {
        // 初始化 适配器
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            //设置pager标题
            //getResources()获取 value下的值
            @Override
            public CharSequence getPageTitle(int position) {
                // 获取 string.xml中的 StringArray的值
                return getResources().getStringArray(R.array.fragment_list)[position];
            }
        };
        // 给viewpager设置适配器
        mViewpager.setAdapter(fragmentPagerAdapter);
        // 设置 tablayout和viewpager联动
        mainTab.setupWithViewPager(mViewpager);

        // 选中标签字体颜色和未选中标签字体颜色
    }

    private void addFragment() {
        fragments.add(musicFragment);
        fragments.add(videoFragment);
        fragments.add(liveFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
