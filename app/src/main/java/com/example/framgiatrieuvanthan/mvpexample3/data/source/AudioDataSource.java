package com.example.framgiatrieuvanthan.mvpexample3.data.source;

import android.support.annotation.NonNull;

import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;

import java.util.List;

public interface AudioDataSource {

    interface LoadAudioCallback {

         void onAudioLoaded(List<Audio> list);

         void onAudioNotAvailable();

    }

    interface GetAudioCallback {

        void onAudioLoaded(List<Audio> list);

        void onAudioNotAvailable();

    }

    void refreshAudioList();

    void getAudioList(LoadAudioCallback callback);

    void getAudio(@NonNull String audioId, GetAudioCallback callback);

    //TODO: add new method that needed

}
