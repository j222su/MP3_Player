package com.example.mp3player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;



    private static final String TAG = "MP3입니다.";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service-onCreate()");
        mediaPlayer=new MediaPlayer();
        Log.d(TAG, "Service-new MediaPlayer()");


//        Uri uriDataPath=Uri.parse(String.valueOf(dataSource));


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service-onStartCommand()");
        String dataSource = intent.getStringExtra("data_path");
        Log.d(TAG, "Service-데이터경로:"+dataSource);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service-onDestroy()");
        super.onDestroy();
    }
}
