package com.example.lenovo.text_music.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.bean.MusicBean;
import com.example.lenovo.text_music.bean.RemoteMusicBean;
import com.example.lenovo.text_music.manager.MusicManager;
import com.example.lenovo.text_music.ui.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/7/11.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener {
    private static final String TAG = MusicService.class.getName();
    //广播的action
    public static final String ACTION_PLAY = "ACTION_PLAY";
    public static final String ACTION_NEXT = "ACTION_NEXT";
    public static final String ACTION_CLOSE = "ACTION_CLOSE";

    //多媒体类 用于播放歌曲
    private MediaPlayer mp = new MediaPlayer();

    //初始播放歌曲的下标
    private int musicIndex = 3;
    //数据源 歌曲数据
    private ArrayList<MusicBean> list = new ArrayList<>();

    //实例化 MyBinder onbind方法返回时使用
    private final IBinder binder = new MyBinder();

    //是否 暂停 标志
    private boolean isPause = false;

    //public void setList(ArrayList<MusicBean> list) {
    //this.list = list;
    //前台服务的通知id
    private static final int NOTIFICATION_ID = 1;

    /**
     * MediaPlayer 的 准备播放监听的 实现方法
     * 当 MediaPlayer 准备好播放某一个文件时 会调用该方法
     *
     * @param mp
     */

    @Override
    public void onPrepared(MediaPlayer mp) {
        //调用播放方法
        mp.start();
        //设置 控件资源（播放按钮状态,当前歌曲名和歌手名）
        setRes(list.get(musicIndex));
    }

    //MyBinder 类 用于返回 service对象
    public class MyBinder extends Binder {
        //返回service对象方法
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        //获取绑定服务时传递来的数据
        Bundle data = intent.getBundleExtra(MainActivity.DATA);
        list = data.getParcelableArrayList(MainActivity.DATA_BUNDLE);

        //设置 控件资源（播放按钮状态，当前歌曲名和歌手名）
        setRes(list.get(musicIndex));
        //返回 myBinder对象 可以在 服务连接监听中得到此对象
        return binder;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
        //注册广播接收器
        regBroadcast();
        //设置MediaPlayer的监听
        init();
//        RemoteViews view = new RemoteViews(getPackageName(), fragment_music_play);
//
////        PendingIntent pendingIntent = PendingIntent.getBroadcast(this
////                , 110
////                , new Intent(this, MainActivity.class)
////                , FLAG_ONE_SHOT);
////
////        view.setOnClickPendingIntent(R.id.play_start, pendingIntent);
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//        view.setTextViewText(R.id.bottom_fragment_name,m. );
//        view.setTextViewText(R.id.bottom_fragment_info, list.get(playingIndex).getMusic_artst());
//
//        Notification notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
////                .setContentTitle("IPlay")
//                .setContent(view)  //给他一个布局
//                .setContentIntent(pendingIntent)
//                .build();
//        startForeground(ID_NOFITICATION, notification);
    }


    //设置资源
    private void setRes(MusicBean musicBean) {
        //初始化 RemoteViews 用于 加载到 通知栏 布局上
        RemoteViews remoteViews = new RemoteViews(getPackageName()
                , R.layout.fragment_music_play);

        //给RemoteViews中的控件添加点击事件
        addClick(remoteViews);

        //给RemoteViews中的控件设置内容和资源  设置歌曲名字和歌手信息
        remoteViews.setTextViewText(R.id.bottom_fragment_name, musicBean.getMusic_name());
        remoteViews.setTextViewText(R.id.bottom_fragment_info, musicBean.getMusic_artst());

        //判断 MediaPlayer 是否正在播放
        //在播放 显示p_btn图标 否则 显示play_btn图标
        if (mp.isPlaying()) {
            remoteViews.setImageViewResource(R.id.bottom_fragment_btn_play, R.drawable.p_btn);
        } else {
            remoteViews.setImageViewResource(R.id.bottom_fragment_btn_play, R.drawable.play_btn);
        }

        //点击下一首
        remoteViews.setImageViewResource(R.id.bottom_fragment_btn_next, R.drawable.next_btn);

        //将当前的播放状态 通过 playStateChanged 方法 传递给 activity
        try {
            MusicManager.getListener().playStateChanged(mp.isPlaying());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将当前正在播放的歌曲下标 通过 playingIndex 方法 传递给 activity
        try {
            MusicManager.getListener().playingIndex(musicIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置 通知
        setNotification(remoteViews);
    }

    private void setNotification(RemoteViews remoteViews) {
        //初始化一个 默认的点击意图
        // 用于调转到主页activity
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pi = PendingIntent
                .getActivity(this, 1, intent, 0);

        //初始化 Notification
        Notification notification = new android.support.v7.app.NotificationCompat
                .Builder(this)
                .setContent(remoteViews)//设置 内容布局
                .setWhen(System.currentTimeMillis())//设置 通知的时间
                .setSmallIcon(R.mipmap.ic_launcher)//设置标题图片
                .setContentIntent(pi)//设置内容点击事件意图
                .build();//

        //设置 前台服务
        startForeground(NOTIFICATION_ID, notification);
    }

    //设置点击事件
    private void addClick(RemoteViews remoteViews) {
        Log.e("addClick", "xx");

        //初始化一个广播意图
        Intent playIntent = new Intent(ACTION_PLAY);
        PendingIntent intent_play = PendingIntent
                .getBroadcast(this, 1, playIntent, 0);
        //给RemoteViews中的控件设置点击事件
        //点击后将执行intent
        remoteViews.setOnClickPendingIntent(R.id.bottom_fragment_btn_play, intent_play);

        Intent nextIntent = new Intent(ACTION_NEXT);
        PendingIntent intent_next = PendingIntent
                .getBroadcast(this, 1, nextIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.bottom_fragment_btn_next, intent_next);

        Intent closeIntent = new Intent(ACTION_CLOSE);
        PendingIntent intent_close = PendingIntent
                .getBroadcast(this, 1, closeIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.bottom_fragment_btn_close, intent_close);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        //于 onBind方法作用相同
        Bundle data = intent.getBundleExtra(MainActivity.DATA);
        list = data.getParcelableArrayList(MainActivity.DATA_BUNDLE);
        setRes(list.get(musicIndex));
        Log.e(TAG, "onRebind" + list.size());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    //注册广播接收器
    private void regBroadcast() {
        MusicPlayServiceBroadcast playBroadcast = new MusicPlayServiceBroadcast();
        IntentFilter filter1 = new IntentFilter(ACTION_PLAY);
        registerReceiver(playBroadcast, filter1);

        MusicNextServiceBroadcast nextServiceBroadcast = new MusicNextServiceBroadcast();
        IntentFilter filter2 = new IntentFilter(ACTION_NEXT);
        registerReceiver(nextServiceBroadcast, filter2);

        MusicCloseServiceBroadcast closeServiceBroadcast = new MusicCloseServiceBroadcast();
        IntentFilter filter3 = new IntentFilter(ACTION_CLOSE);
        registerReceiver(closeServiceBroadcast, filter3);
    }

    //设置MediaPlayer的监听
    public void init() {
        //播放完成监听
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextMusic();
            }
        });
    }

    public void setUrlMode(RemoteMusicBean remoteMusicBean){
        if (mp != null) {
            try {
                //重置
                mp.reset();
                //设置要播放的文件
                mp.setDataSource(remoteMusicBean.getBitrate().getFile_link());
                //异步准备播放的资源
                mp.prepareAsync();
                try {
                    //设置 准备完成监听
                    //准备完成才可以播放 所以在设个监听的回调里
                    mp.setOnPreparedListener(this);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 播放/暂停  方法
    public void play() {
//        IMusicInterface act = MusicManager
//                .getListener()
//                .;
//        if (act != null) {
//            act.nofityMusicBottom(list.get(playingIndex));
//        }
        if (mp.isPlaying()) {//如果正在播放  则暂停
            mp.pause();
            isPause = true;
        } else if (!mp.isPlaying() && isPause) {//如果不在播放 并且 暂停标志是true 则播放
            mp.start();
            isPause = false;
        } else {// 其他情况 则 设置 播放的文件 并 从头开始播放
            try {
                mp.setDataSource(list.get(musicIndex).getMusic_file_path());
                mp.prepare();
                mp.seekTo(0);
                mp.setOnPreparedListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //调用设置资源方法
        setRes(list.get(musicIndex));
    }

    // 停止  方法
    public void stop() {
        if (mp != null) {
            mp.stop();
            isPause = false;
            try {
//                mp.prepare();
                mp.seekTo(0);
//                mp.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获取要播放的歌曲下标
    private int getMusicIndex() {
        //TODO 播放模式
        if (++musicIndex >= list.size())
            musicIndex = 3;
        return musicIndex;
    }

    // 下一首  方法
    public void nextMusic() {
        if (mp != null) {
//            mp.stop();
            try {
                //重置
                mp.reset();
                //设置播放源
                mp.setDataSource(list.get(getMusicIndex()).getMusic_file_path());
                //异步准备播放资源
                mp.prepareAsync();
//                mp.seekTo(0);
                try {
                    //设置 准备完成监听
                    //准备完成才可以播放 所以在这个监听的回调里调用播放方法
                    mp.setOnPreparedListener(this);
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //播放指定下标的歌曲 方法
    public void playIndex(int index) {
        if (mp != null) {
            try {
                //重置
                mp.reset();
                //设置要播放的文件
                mp.setDataSource(list.get(index).getMusic_file_path());
                //异步准备播放的资源
                mp.prepareAsync();
                //设置当前播放的歌曲下标
                musicIndex = index;
                try {
                    //设置 准备完成监听
                    mp.setOnPreparedListener(this);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //广播接收器
    class MusicPlayServiceBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收到广播后调用 播放/暂停 方法
            play();
        }
    }

    //广播接收器
    class MusicNextServiceBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收到广播后调用 下一首 方法
            nextMusic();
        }
    }

    //广播接收器
    class MusicCloseServiceBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接收到广播后 停止播放  关闭服务  结束程序
            stop();//停止播放
            stopForeground(false);//取消前台服务 （调用此方法后通知还在）
            stopSelf();//结束服务
            //结束进程
            android.os
                    .Process
                    .killProcess(android.os.Process.myPid());
        }
    }
}
