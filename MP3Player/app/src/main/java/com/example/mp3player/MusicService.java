package com.example.mp3player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    BroadcastReceiver bcr;
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
//        bcr=new MusicBroadcastReceiver();
//        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
//        this.registerReceiver(bcr, intentFilter);

//        mediaPlayer=MediaPlayer.create(this, );
//        mediaPlayer.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service-onStartCommand()");
        String dataSource = intent.getStringExtra("data_path");
        Log.d(TAG, "Service-데이터경로:"+dataSource);
        mediaPlayer=MediaPlayer.create(this, Uri.parse(dataSource));
        mediaPlayer.start();
        Log.d(TAG, "Service-mediaPlayer.start()");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "Service-onDestroy()");
        super.onDestroy();
        mediaPlayer.stop();
//        unregisterReceiver(bcr);

    }
}
