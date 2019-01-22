package com.example.framgiatrieuvanthan.mvpexample3.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.framgiatrieuvanthan.mvpexample3.asynctask.LoadingAudioAsyncTask;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.data.source.AudioDataSource;

import java.util.List;

public class AudioLocalDataSource extends AppCompatActivity
        implements AudioDataSource {

    private static AudioLocalDataSource INSTANCRE = null;
    private ContentResolver mContentResolver;
    private Resources mResources;

    private AudioLocalDataSource(){

    }

    private AudioLocalDataSource(ContentResolver contentResolver, Resources resources){
        this.mContentResolver = contentResolver;
        this.mResources = resources;
    }

    public static AudioLocalDataSource getInstance(ContentResolver contentResolver, Resources resources){
        if(INSTANCRE == null){
            INSTANCRE = new AudioLocalDataSource(contentResolver, resources);
        }
        return INSTANCRE;
    }

    @Override
    public void refreshAudioList() {

    }

    private Context getContext(){
        return getApplicationContext();
    }

    @Override
    public void getAudioList(final LoadAudioCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Handler handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        switch (msg.what){
                            case LoadingAudioAsyncTask.LOAD_AUDIO_DONE:
                                List<Audio> list = (List<Audio>) msg.obj;
                                callback.onAudioLoaded(list);
                                break;
                            default:
                                callback.onAudioNotAvailable();
                        }
                        return false;
                    }
                });

                LoadingAudioAsyncTask audioAsyncTask =
                        new LoadingAudioAsyncTask(mContentResolver, mResources, handler);
                audioAsyncTask.execute();
            }
        };

        runnable.run();
    }

    @Override
    public void getAudio(@NonNull String audioId, GetAudioCallback callback) {

    }
}
