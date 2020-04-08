package com.example.mp3player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<MusicData> list=new ArrayList<>();
    //new ArrayList<>();로 객체를 생성해줘야 함 그렇지 않으면 객체를 생성하지 못해서 list에 데이터를 추가할 수 없다...
    private LinearLayoutManager linearLayoutManager;
    private MusicAdapter musicAdapter;
    MusicData musicData=new MusicData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        ActivityCompat.requestPermissions(this, new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        getMusicList();
        
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        musicAdapter = new MusicAdapter(R.layout.list_item, list);
        recyclerView.setAdapter(musicAdapter);

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
                list.add(musicData);
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

}





