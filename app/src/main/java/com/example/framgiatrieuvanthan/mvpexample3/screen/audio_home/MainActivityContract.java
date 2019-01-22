package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_home;

import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.screen.base.BasePresenter;
import com.example.framgiatrieuvanthan.mvpexample3.screen.base.BaseView;

import java.util.List;

public interface MainActivityContract {

    interface View extends BaseView<Presenter> {

        void setLoadingInprogress(boolean isLoading);

        void showListAudio(List<Audio> listAudio);

        void showNoDataLoaded();

        void showLoadingAudioError();

    }

    interface Presenter extends BasePresenter {

        void loadListAudio();

        void reloadListAudio(boolean isNeeded);

    }

}
