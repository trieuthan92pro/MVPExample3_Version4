package com.example.framgiatrieuvanthan.mvpexample3.data.repository;

import android.support.annotation.NonNull;

import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.data.source.AudioDataSource;

import java.util.List;

public class AudioRepository implements AudioDataSource{

    private static AudioRepository INSTANCE = null;
    private AudioDataSource mLocalAudioDataSource;

    // prevent directly instantiate object of this class
    private AudioRepository(AudioDataSource localAudioDataSource){
        mLocalAudioDataSource = checkNotNull(localAudioDataSource);
    }

    public static AudioRepository getInstance(AudioDataSource localAudioDataSource){
        if(INSTANCE == null){
            INSTANCE = new AudioRepository(localAudioDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void refreshAudioList() {

    }

    @Override
    public void getAudioList(final LoadAudioCallback callback) {
        mLocalAudioDataSource.getAudioList(new LoadAudioCallback() {
            @Override
            public void onAudioLoaded(List<Audio> list) {
                callback.onAudioLoaded(list);
            }

            @Override
            public void onAudioNotAvailable() {
                // just do nothing at this moment
            }
        });
    }

    @Override
    public void getAudio(@NonNull String audioId, GetAudioCallback callback) {

    }

    // remove this instance of this class to recycle bin
    // then create a new one
    public static void onDestroy(){
        INSTANCE = null;
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
