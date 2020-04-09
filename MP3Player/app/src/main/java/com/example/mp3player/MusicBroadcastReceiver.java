package com.example.mp3player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MusicBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "이어폰 연결/해제 확인", Toast.LENGTH_LONG);
//        Intent intent1=new Intent();
//        context.startService(intent1);
    }
}
