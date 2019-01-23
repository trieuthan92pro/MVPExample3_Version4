package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_detail;

public class AudioDetailActivityPresenter
        implements AudioDetailActivityContract.Presenter {



    @Override
    public void playAudio() {

    }

    @Override
    public void resumeAudio(int position) {

    }

    @Override
    public void pauseAudio() {

    }

    @Override
    public void stopAudio() {

    }

    @Override
    public void nextAudio() {

    }

    @Override
    public void prevAudio() {

    }

    @Override
    public void optionItemChose() {

    }

    @Override
    public void repeatChose() {

    }

    @Override
    public void seekBarChange() {

    }

    @Override
    public void lovedIconChange() {

    }

    @Override
    public void start() {

    }

    public String getTimerFormatted(String time){
        StringBuilder stringBuilder = new StringBuilder();
        int miliSec = Integer.parseInt(time);
        int min = miliSec/(1000*60);
        int remain = miliSec%(1000*60);
        int sec = remain/1000;
        stringBuilder.append(min+":"+sec);
        return stringBuilder.toString();
    }
}
