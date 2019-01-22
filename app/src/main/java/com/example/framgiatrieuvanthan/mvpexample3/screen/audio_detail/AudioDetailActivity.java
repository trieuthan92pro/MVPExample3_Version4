package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.framgiatrieuvanthan.mvpexample3.R;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.utils.AudioUtils;

import java.util.HashMap;

public class AudioDetailActivity extends AppCompatActivity
        implements AudioDetailActivityContract.View {
    public static String BROADCAST_PLAY_NEW_AUDIO = "PLAY_NEW_AUDIO";
    private Audio mAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        initData();

    }

    private void initData() {
        String audioId = getIntent().getStringExtra(AudioUtils.AUDIO_ITEM_ID);
        HashMap<String, Audio> hashMap = AudioUtils.getHashMapInstance();
        mAudio = hashMap.get(audioId);
        Log.e("", "initData: " + mAudio.getTitle() );
    }

    @Override
    public void showStartAvatarAnimation() {

    }

    @Override
    public void showStopAvatarAnimation() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showTimeCounter() {

    }

    @Override
    public void showAudioTitle() {

    }

    @Override
    public void showAudioArtist() {

    }

    @Override
    public void showSeekBarUpdating() {

    }

    @Override
    public void showRepeatOne() {

    }

    @Override
    public void showRepeatAll() {

    }

    @Override
    public void showRepeatOff() {

    }

    @Override
    public void showMoreOption() {

    }

    @Override
    public void showPreferenceMarked() {

    }

    @Override
    public void showPauseButton() {

    }

    @Override
    public void showPlayButton() {

    }

    @Override
    public void setPresenter(AudioDetailActivityContract.Presenter presenter) {

    }
}
