package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_home;

import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.data.repository.AudioRepository;
import com.example.framgiatrieuvanthan.mvpexample3.data.source.AudioDataSource;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private AudioRepository mAudioRepository;
    private MainActivityContract.View mAudioView;
    private boolean isShowLoadingAudio = false;
    // add something that needed

    public MainActivityPresenter(AudioRepository repository, MainActivityContract.View view){
        mAudioRepository = repository;
        mAudioView = view;
        mAudioView.setPresenter(this);
    }


    @Override
    public void loadListAudio() {
        mAudioView.setLoadingInprogress(true);
        // at this time, i don't do more thing right here, just do load audio list
        mAudioRepository.getAudioList(new AudioDataSource.LoadAudioCallback() {
            @Override
            public void onAudioLoaded(List<Audio> list) {
                // first, we need stop show loading audio progress
                if(isShowLoadingAudio){
                    mAudioView.setLoadingInprogress(false);
                }
                // then we show audio info that we got from list audio
                showListAudio(list);
            }

            @Override
            public void onAudioNotAvailable() {
                // there was an error occured
                mAudioView.showLoadingAudioError();
            }
        });
    }

    /**
     * this method show audio info from list to home screen of the app
     * if we have no audio, show an empty screen with some message
     * otherwise, show data in the list that we got
     * @param list
     */
    private void showListAudio(List<Audio> list) {
        if(list.isEmpty()){
            mAudioView.showNoDataLoaded();
        }else{
            mAudioView.showListAudio(list);
        }
    }

    @Override
    public void reloadListAudio(boolean isNeeded) {
        //TODO: do something that i was missing here
    }

    @Override
    public void start() {
        loadListAudio();
    }
}
