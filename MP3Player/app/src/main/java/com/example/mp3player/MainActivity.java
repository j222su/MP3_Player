package com.example.mp3player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MusicAdapter musicAdapter;
    PickMusicData pickMusicData=new PickMusicData();
    MusicData musicData=new MusicData();
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
        Log.d(TAG, "onRequestPermissionsResult()");
        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        pickMusicData.getMusicDataList(this);
                    Log.d(TAG, "onRequestPermissionsResult() getMusicDataList()");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d(TAG, "퍼미션거부");
                }
                return;
            }
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


        MusicPermission.getInstance().permissionCheck(this, this);

        btnPlay_Pause.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        bcr=new MusicBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        this.registerReceiver(bcr, intentFilter);

        pickMusicData.getMusicDataList(this);
        Log.d(TAG, "list에 담겨있는지 확인-----------");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay_Pause :
                if(flag==0) {
                    intent=new Intent(getApplicationContext(), MusicService.class);
                    intent.putExtra("data_path", MusicAdapter.sDataPath);
                    Log.d(TAG, "플레이버튼 클릭 경로확인 :"+MusicAdapter.sDataPath);
                    btnPlay_Pause.setImageResource(R.mipmap.pause);
                    startService(intent);
                    Log.d(TAG, "플레이버튼 클릭 : startService(intent)");
                    flag=1;
                } else {
                    Log.d(TAG, "pause버튼 클릭");
                    btnPlay_Pause.setImageResource(R.mipmap.play);
                    stopService(intent);
                    Log.d(TAG, "pause버튼 클릭 : stopService()");
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
        Log.d(TAG, "Main onDestroy()--: stopService()");
        super.onDestroy();
    }
}





