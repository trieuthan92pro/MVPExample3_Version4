package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_detail;

import com.example.framgiatrieuvanthan.mvpexample3.screen.base.BasePresenter;
import com.example.framgiatrieuvanthan.mvpexample3.screen.base.BaseView;

public interface AudioDetailActivityContract {

    interface View extends BaseView<Presenter> {

        void showStartAvatarAnimation();

        void showStopAvatarAnimation();

        void showError();

        void showTimeCounter();

        void showAudioTitle();

        void showAudioArtist();

        void showSeekBarUpdating();

        void showRepeatOne();

        void showRepeatAll();

        void showRepeatOff();

        void showMoreOption();

        void showPreferenceMarked();

        void showPauseButton();

        void showPlayButton();

    }

    interface Presenter extends BasePresenter {

        void playAudio();

        void resumeAudio(int position);

        void pauseAudio();

        void stopAudio();

        void nextAudio();

        void prevAudio();

        void optionItemChose();

        void repeatChose();

        void seekBarChange();

        void lovedIconChange();

    }

}
