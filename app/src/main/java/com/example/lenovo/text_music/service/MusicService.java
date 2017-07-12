package com.example.lenovo.text_music.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.lenovo.text_music.R;
import com.example.lenovo.text_music.ui.activity.MainActivity;

/**
 * Created by lenovo on 2017/7/11.
 */

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getName();
    private static final int ID_NOFITICATION = 135;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.fragment_music_play);

//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this
//                , 110
//                , new Intent(this, MainActivity.class)
//                , FLAG_ONE_SHOT);
//
//        view.setOnClickPendingIntent(R.id.play_start, pendingIntent);
//
//        Notification notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
////                .setContentTitle("MusicPlayer")
//                .setContent(view)  //给他一个布局
//                .setContentIntent(pendingIntent)
//                .build();
//        startForeground(ID_NOFITICATION, notification);
           Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("MusicPlayer")
                .setContent(view)  //给他一个布局
                .setContentIntent(pendingIntent)
                .build();
        startForeground(ID_NOFITICATION, notification);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }

    public void play() {

    }

    public void next() {

    }
}
