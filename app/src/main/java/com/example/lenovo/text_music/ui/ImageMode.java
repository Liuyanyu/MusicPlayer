package com.example.lenovo.text_music.ui;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.text_music.ui.activity.BaseActivity;
import com.example.lenovo.text_music.ui.fragment.BaseFragment;

/**
 * Created by lenovo on 2017/7/14.
 */

public class ImageMode {

    public static void setImg(BaseFragment fragment, String path, ImageView view) {
        Glide.with(fragment)
                .load(path)
                .override(150,150)
                .into(view);
    }


    public void setImg(BaseActivity activity, String path, ImageView view) {
        Glide.with(activity)
                .load(path)
                .into(view);
    }
}
