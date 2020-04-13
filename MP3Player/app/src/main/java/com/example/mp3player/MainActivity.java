package com.example.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
//    private ArrayList<MusicData> list=new ArrayList<>();
    //new ArrayList<>();로 객체를 생성해줘야 함 그렇지 않으면 객체를 생성하지 못해서 list에 데이터를 추가할 수 없다...
    private LinearLayoutManager linearLayoutManager;
    private MusicAdapter musicAdapter;
//    MusicData musicData=new MusicData();
    PickMusicData pickMusicData = new PickMusicData();
    static ImageView mImgAlbum;
    static TextView mTvSinger, mTvTitle, mTvProgress, mTvTotalProgress;
    ImageButton btnPrevious, btnPlay_Pause, btnNext;
    SeekBar seekBar;
    Intent intent;
    int flag = 0;
    BroadcastReceiver bcr;
    private static final String TAG = "MP3입니다.";


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MODE_PRIVATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    pickMusicData.getMusicDataList();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        mImgAlbum=findViewById(R.id.mImgAlbum);
        mTvSinger=findViewById(R.id.mTvSinger);
        mTvTitle=findViewById(R.id.mTvTitle);
        mTvProgress=findViewById(R.id.mTvProgress);

        mTvTotalProgress=findViewById(R.id.mTvTotalProgress);
        btnPrevious=findViewById(R.id.btnPrevious);
        btnPlay_Pause=findViewById(R.id.btnPlay_Pause);
        btnNext=findViewById(R.id.btnNext);
        seekBar=findViewById(R.id.seekBar);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        musicAdapter = new MusicAdapter(R.layout.list_item, pickMusicData.getList());
        recyclerView.setAdapter(musicAdapter);
        intent=new Intent(getApplicationContext(), MusicService.class);


        btnPlay_Pause.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        bcr=new MusicBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(bcr, intentFilter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay_Pause :
                if(flag==0) {
                    intent.putExtra("data_path", pickMusicData.musicData.getDataPath());
                    Log.d(TAG, "플레이버튼 클릭 경로확인 :"+pickMusicData.musicData.getDataPath());
                    btnPlay_Pause.setImageResource(R.mipmap.pause);
                    startService(intent);
                    Log.d(TAG, "플레이버튼 클릭 : startService(intent)");
                    flag=1;
                } else {
                    btnPlay_Pause.setImageResource(R.mipmap.play);
                    stopService(intent);
                    flag=0;
                }

                break;

            case R.id.btnPrevious :

                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }
}





