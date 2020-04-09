package com.example.mp3player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<MusicData> list=new ArrayList<>();
    //new ArrayList<>();로 객체를 생성해줘야 함 그렇지 않으면 객체를 생성하지 못해서 list에 데이터를 추가할 수 없다...
    private LinearLayoutManager linearLayoutManager;
    private MusicAdapter musicAdapter;
    MusicData musicData=new MusicData();
    static ImageView mImgAlbum;
    static TextView mTvSinger, mTvTitle, mTvProgress, mTvTotalProgress;
    ImageButton btnPrevious, btnPlay, btnNext;
    SeekBar seekBar;
    Intent intent;
    int flag = 0;


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
        btnPlay=findViewById(R.id.btnPlay);
        btnNext=findViewById(R.id.btnNext);
        seekBar=findViewById(R.id.seekBar);
        ActivityCompat.requestPermissions(this, new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        getMusicList();
        
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        musicAdapter = new MusicAdapter(R.layout.list_item, list);
        recyclerView.setAdapter(musicAdapter);


        intent=new Intent(getApplicationContext(), MusicService.class);
        btnPlay.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);



    }

    private void getMusicList() {
        ContentResolver contentResolver = getContentResolver();
        // 음악 앱의 데이터베이스에 접근해서 mp3 정보들을 가져온다.

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);
        cursor.moveToFirst();
        System.out.println("음악파일 개수 = " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            do {
                musicData.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                musicData.setAlbumId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                musicData.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                musicData.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                musicData.setTotal(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                list.add(musicData);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlay :
                if(flag==0) {
                    btnPlay.setImageResource(R.mipmap.pause);
                    startService(intent);
                } else {
                    btnPlay.setImageResource(R.mipmap.play);
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





