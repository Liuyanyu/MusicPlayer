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

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.service.MusicService;
import com.example.lenovo.text_music.ui.fragment.BaseFragment;
import com.example.lenovo.text_music.ui.fragment.MainFragment;
import com.example.lenovo.text_music.ui.fragment.MainListLocalFragment;
import com.example.lenovo.text_music.ui.fragment.MusicPlayButtoomFragment;

public class MainActivity extends BaseActivity implements ServiceConnection {
    MainFragment mainFragment;
    MusicPlayButtoomFragment musicPlayButtoomFragment;
    MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断设备版本是否大于4.4   api 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        setContentView(R.layout.activity_main);

        setFragment();
        bootService();
    }
    private void bootService() {
        Intent intent=new Intent(this, MusicService.class);
        bindService(intent,this,BIND_AUTO_CREATE);
        startService(intent);
    }
    public void intent2local() {
//        Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show();
        addFragment(new MainListLocalFragment());
    }

    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_pager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setFragment() {
         mainFragment = new MainFragment();
         musicPlayButtoomFragment = new MusicPlayButtoomFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, mainFragment);
        transaction.add(R.id.main_buttom, musicPlayButtoomFragment);
        transaction.commit();
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

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        musicService = ((MusicService.MyBinder) iBinder).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
    public void playOnService(){
        musicService.play();
    }
}
