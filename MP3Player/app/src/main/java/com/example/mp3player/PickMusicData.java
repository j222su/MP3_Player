package com.example.mp3player;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class PickMusicData {
    public static ArrayList<MusicData> list=new ArrayList<>();
    MusicData musicData=new MusicData();
    public static final String TAG="MP3입니다.";
//    Context context;

    public PickMusicData() {

    }

    public void getMusicDataList(Context context) {
        Log.d(TAG, "getMusicDataList()");
        ContentResolver contentResolver = context.getContentResolver();
        // 음악 앱의 데이터베이스에 접근해서 mp3 정보들을 가져온다.

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);
        cursor.moveToFirst();
        Log.d(TAG, "getMusicList() 음악파일개수 : "+cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            do {
                musicData.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                musicData.setAlbumId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                musicData.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                musicData.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                musicData.setTotal(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                musicData.setDataPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                Log.d(TAG, "getMusicList() 경로 : "+cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                Log.d(TAG, "getMusicList() 앨범아이디 : "+cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                list.add(musicData);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}
