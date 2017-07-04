package com.example.lenovo.text_music.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.inject.component.DaggerMainComponent;
import com.example.lenovo.text_music.inject.module.MainModule;
import com.example.lenovo.text_music.ui.fragment.BaseFragment;
import com.example.lenovo.text_music.ui.fragment.MainLiveFragment;
import com.example.lenovo.text_music.ui.fragment.MainMusicFragment;
import com.example.lenovo.text_music.ui.fragment.MainVideoFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;

    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    @Inject
    MainMusicFragment musicFragment;
    @Inject
    MainVideoFragment videoFragment;
    @Inject
    MainLiveFragment liveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断设备版本是否大于4.4   api 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerMainComponent.builder().mainModule(new MainModule()).build().inject(this);

        Log.e("123","123");
        //添加fragment到集合中
        addFragment();
        //设置viewPager
        setViewpager();
    }

    //设置状态栏透明状态
    private void setTranslucentStatus(boolean b) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (b) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void setViewpager() {
        //初始化适配器
        final FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            //设置pager标题
            public CharSequence getPageTitle(int position) {
                //获取string.xml中的StringArray的值
                return getResources().getStringArray(R.array.fragment_list)[position];
            }
        };
        mainViewpager.setAdapter(fragmentPagerAdapter);
        mainTab.setupWithViewPager(mainViewpager);
    }

    private void addFragment() {
        fragments.add(musicFragment);
        fragments.add(videoFragment);
        fragments.add(liveFragment);
    }
}
