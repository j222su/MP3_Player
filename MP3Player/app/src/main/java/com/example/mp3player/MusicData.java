package com.example.mp3player;

import android.net.Uri;

public class MusicData {
    private String id;
    private String albumId;
    private String title;
    private String artist;
    private long total;

    public MusicData() {
    }

    public MusicData(String id, String albumId, String title, String artist, long total) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.artist = artist;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}


