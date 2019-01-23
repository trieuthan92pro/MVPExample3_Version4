package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.framgiatrieuvanthan.mvpexample3.R;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.utils.AudioUtils;

import java.util.HashMap;

public class AudioDetailActivity extends AppCompatActivity
        implements AudioDetailActivityContract.View {
    public static String BROADCAST_PLAY_NEW_AUDIO = "PLAY_NEW_AUDIO";
    private Audio mAudio;
    private AudioDetailActivityContract.Presenter mPresenter;
    private TextView mTextViewAudioInfo;
    private TextView mTextViewCurrentPosition;
    private TextView mTextViewTotalDuration;
    private TextView mTextViewCurrentDirectory;
    private ImageView mImageViewRotate;
    private ImageView mImageViewFavorite;
    private ImageView mImageViewOption;
    private ImageButton mImageButtonShuffle;
    private ImageButton mImageButtonPrev;
    private ImageButton mImageButtonPlayPause;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonRepeat;
    private SeekBar mSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);
        initView();
        initData();
        showInfo();
    }

    private void showInfo() {
        showAudioTitle();
        showAudioArtist();
        mTextViewTotalDuration.setText(mAudio.getDuration());
    }

    private void initView() {
        mTextViewAudioInfo = findViewById(R.id.text_audio_info);
        mTextViewCurrentDirectory = findViewById(R.id.text_directory);
        mTextViewCurrentPosition = findViewById(R.id.text_current_time);
        mTextViewTotalDuration = findViewById(R.id.text_total_duration);
        mImageButtonNext = findViewById(R.id.img_button_next);
        mImageButtonPlayPause = findViewById(R.id.img_button_play_pause);
        mImageButtonPrev = findViewById(R.id.img_button_prev);
        mImageButtonRepeat = findViewById(R.id.img_button_replay);
        mImageButtonShuffle = findViewById(R.id.img_button_shuffle);
        mImageViewFavorite = findViewById(R.id.img_favorite);
        mImageViewOption = findViewById(R.id.img_option);
        mImageViewRotate = findViewById(R.id.img_rotate);
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
        mTextViewAudioInfo.setText(mAudio.getTitle()+"-");
    }

    @Override
    public void showAudioArtist() {
        mTextViewAudioInfo.setText(mTextViewAudioInfo.getText().toString() + mAudio.getArtist());
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
