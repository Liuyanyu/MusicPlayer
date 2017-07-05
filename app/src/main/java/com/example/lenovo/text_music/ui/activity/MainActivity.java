package com.example.lenovo.text_music.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.ui.fragment.BaseFragment;
import com.example.lenovo.text_music.ui.fragment.MainFragment;
import com.example.lenovo.text_music.ui.fragment.MainLiveFragment;

public class MainActivity extends BaseActivity {
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断设备版本是否大于4.4   api 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        setContentView(R.layout.activity_main);
        Log.e("1234","52452");
        setFragment();

    }

    public void intent2local() {
        Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show();
        addFragment(new MainLiveFragment());
    }

    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setFragment() {
        mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, mainFragment);
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


}
