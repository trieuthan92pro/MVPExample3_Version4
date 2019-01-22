package com.example.framgiatrieuvanthan.mvpexample3.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Audio implements Parcelable {
    private String data, title, artist, album, id;
    private int duration;
    private Bitmap bitmapAudio;

    public Audio(){

    }

    protected Audio(Parcel in) {
        data = in.readString();
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        id = in.readString();
        duration = in.readInt();
        bitmapAudio = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Audio> CREATOR = new Creator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel in) {
            return new Audio(in);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(album);
        dest.writeString(id);
        dest.writeInt(duration);
        dest.writeParcelable(bitmapAudio, flags);
    }

    public Audio(String data, String title, String artist, String album,
                 String id, int duration, Bitmap bitmapAudio) {
        this.data = data;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.id = id;
        this.duration = duration;
        this.bitmapAudio = bitmapAudio;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Bitmap getBitmapAudio() {
        return bitmapAudio;
    }

    public void setBitmapAudio(Bitmap bitmapAudio) {
        this.bitmapAudio = bitmapAudio;
    }
}
