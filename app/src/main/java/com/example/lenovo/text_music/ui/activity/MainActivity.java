package com.example.lenovo.text_music.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.lenovo.text_music.PunlicFlags;
import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.MusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.callbreak.MusicStateListener;
import com.example.lenovo.text_music.inject.module.FileUtil;
import com.example.lenovo.text_music.manager.MusicManager;
import com.example.lenovo.text_music.service.MusicService;
import com.example.lenovo.text_music.ui.fragment.BaseFragment;
import com.example.lenovo.text_music.ui.fragment.MainFragment;
import com.example.lenovo.text_music.ui.fragment.MusicListLocalFragment;
import com.example.lenovo.text_music.ui.fragment.MusicPlayButtoomFragment;
import com.example.lenovo.text_music.ui.fragment.RemoteMusicListFragment;
import com.example.lenovo.text_music.ui.fragment.RemoteMusicMainFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.lenovo.text_music.R.id.main_buttom;

public class MainActivity extends BaseActivity implements ServiceConnection, MusicStateListener {
    public static final String DATA = "data";
    public static final String DATA_BUNDLE = "data";
    @BindView(R.id.main_buttom)
    FrameLayout mainButtom;
    //音乐播放服务对象
    private MusicService musicService;
    //数据源
    private ArrayList<MusicBean> list;
    //音乐列表fragment
    private MusicListLocalFragment musicListLocalFragment;
    //底部菜单fragment
    private MusicPlayButtoomFragment musicPlayButtoomFragment;

    private MainFragment mainFragment;


    //bindService的接口  服务连接监听
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        //从IBinder里获取 服务对象
        musicService = ((MusicService.MyBinder) iBinder).getService();
    }

    //bindService的接口
    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //判断设备版本是否大于4.4  api19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //获取音乐文件数据  等于数据源
        list = FileUtil.getMusicFiles(this);
        //初始化fragment
        setFragment();
        //注册监听  相当于把activity对象保存到 MusicManager 里
        MusicManager.registerListener(this);
        //启动服务
        bootService();

    }

    private void bootService() {
        Intent intent = new Intent(this, MusicService.class);
        //实例化Bundle对象 用于传递 数据源
        Bundle bundle = new Bundle();
        //给bundle设置数据
        bundle.putParcelableArrayList(DATA_BUNDLE, list);
        intent.putExtra(DATA, bundle);
        //启动并绑定服务
        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    private void setFragment() {
        //实例化fragment
        musicListLocalFragment = new MusicListLocalFragment();
        musicPlayButtoomFragment = new MusicPlayButtoomFragment();
        mainFragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, mainFragment);
        transaction.add(main_buttom, musicPlayButtoomFragment);
        transaction.commit();


    }


    public void intent2local() {
//        Toast.makeText(this, "intent2Local", Toast.LENGTH_SHORT).show();
//        addFragment(new MusicListLocalFragment());
        initFragment();
    }

    public void intent2remote() {
        addFragment(new RemoteMusicMainFragment());
    }

    public void intent2remoteList(int musicApiTypeNew) {
        RemoteMusicListFragment rmlf = new RemoteMusicListFragment();
        Bundle arg = new Bundle();
        arg.putInt(PunlicFlags.MUSIC_API_TYPE, musicApiTypeNew);
        rmlf.setArguments(arg);
        addFragment(rmlf);
    }

    private void initFragment() {

        //获取音乐文件数据  等于数据源
        list = FileUtil.getMusicFiles(this);

        //实例化Bundle对象 用于传递 数据源
        Bundle data = new Bundle();
        data.putParcelableArrayList(DATA, list);
        //让fragment携带bundle 在fragment页面里面可以获取到该bundle 用于加载fragment时传递数据
        musicListLocalFragment.setArguments(data);
        //获取fragment管理器 并开启事务
        FragmentTransaction bt = getSupportFragmentManager()
                .beginTransaction();
        //替换fragment
        bt.add(R.id.main_pager, musicListLocalFragment);
        bt.addToBackStack(null);
        //提交替换
        bt.commit();
    }

    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // 设置状态栏透明状态
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(this);
        //取消注册监听 相当于 从MusicManager中移除 activity对象
        MusicManager.unregisterListener();
        //取消绑定服务
        unbindService(this);
    }

    //fragment 调用的方法
    public void playBtn(int btn) {
        switch (btn) {
            case 0:
                //通过服务对象调用 服务里面的 播放/暂停 方法
                musicService.play();
                break;
            case 1:
                //通过服务对象调用 服务里面 下一曲 方法
                musicService.nextMusic();
                break;
            case 2:
                break;
        }
    }

    //fragment 调用的方法
    //通过服务对象调用 服务里面 播放指定歌曲 方法
    public void playIndex(int index) {
        musicService.playIndex(index);
    }

    /**
     * 播放状态改变方法
     * 当歌曲 播放 或者 暂停时 服务会调用该方法
     *
     * @param isPlaying true 正在播放
     *                  <p>
     *                  service调用的方法
     */
    @Override
    public void playStateChanged(boolean isPlaying) {
        //更改 bottomPlayFragment 里面的 播放按钮 状态
        musicPlayButtoomFragment.setPlaying(isPlaying);
    }

    /**
     * 正在播放的歌曲 下标
     *
     * @param index 0 表示 数据源中 下标 为0的歌曲
     *              <p>
     *              service调用的方法
     */
    @Override
    public void playingIndex(int index) {
        //更改 bottomPlayFragment 里面的 歌曲信息
        musicPlayButtoomFragment.setMusicInfo(list.get(index));
        //更改 musicListFragment 里面的 歌曲列表 的状态
        musicListLocalFragment.setPlay(index);
    }

    @Override
    public void currentPositionAndDuration(int CurrentPosition, int Duration) {

    }

    public void setUrl2Service(RemoteMusicBean remoteMusicBean) {
        musicService.setUrlMode(remoteMusicBean);
    }
    @OnClick(R.id.main_buttom)
    public void onViewClicked() {
        Intent intent=new Intent(this,RemoteMusicActivity.class);
        startActivity(intent);
    }
}
