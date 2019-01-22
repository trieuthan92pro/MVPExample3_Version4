package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.framgiatrieuvanthan.mvpexample3.R;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.data.repository.AudioRepository;
import com.example.framgiatrieuvanthan.mvpexample3.data.source.local.AudioLocalDataSource;
import com.example.framgiatrieuvanthan.mvpexample3.screen.audio_detail.AudioDetailActivity;
import com.example.framgiatrieuvanthan.mvpexample3.utils.AudioUtils;
import com.example.framgiatrieuvanthan.mvpexample3.utils.PermissionInfo;

import java.util.List;

// Useful Link: https://pspdfkit.com/blog/2016/restoring-state-in-android-mvp-architecture/
// Other: https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf
// One more things: https://android.jlelse.eu/architectural-guidelines-to-follow-for-mvp-pattern-in-android-2374848a0157


public class MainActivity extends AppCompatActivity
        implements MainActivityContract.View, AudioAdapter.ItemClickListener {

    public static final int PERMISS_READ_EXTERNAL = 189;

    private RecyclerView mRecyclerView;
    private MainActivityContract.Presenter mPresenter;
    private List<Audio> mAudios;
    private AudioAdapter mAudioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();

        requestPermission();
    }


    private void initComponent() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mAudioAdapter = new AudioAdapter(this, mAudios, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAudioAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void setPresenter(@NonNull MainActivityContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingInprogress(boolean isLoading) {
        //TODO: add progress bar and play with him
    }

    @Override
    public void showListAudio(List<Audio> listAudio) {
        AudioUtils.setListAudioHashMap(listAudio);
        if(mAudios == null){
            mAudios = listAudio;
        } else{
            mAudios.clear();
            mAudios.addAll(listAudio);
        }
        mAudioAdapter.setAudioList(listAudio);
        mAudioAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNoDataLoaded() {
        Log.e("ERROR", "showNoDataLoaded: NO DATA" );
        Toast.makeText(this, "NOTHING TO SHOW", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingAudioError() {
        // for now, i just show a toast
        Toast.makeText(this, "LIST IS EMPTY", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionInfo.PERMISSION_COUNT = 0;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                PermissionInfo.PERMISSION_COUNT ++;
            }
        }
        loadAudioList();
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Intent audioDetailActivityIntent = new Intent(this, AudioDetailActivity.class);
        audioDetailActivityIntent.putExtra(AudioUtils.AUDIO_ITEM_ID, mAudios.get(clickedPosition).getId());
        startActivity(audioDetailActivityIntent);
    }

    private <T> T checkNotNull(T t){
        if(t == null){
            throw new NullPointerException("This parameter cannot be null");
        }
        return t;
    }

    private void requestPermission(){
        PermissionInfo.PERMISSION_COUNT = 0;
        for (int i = 0; i < PermissionInfo.permissions.length; i++) {
            if(ContextCompat.checkSelfPermission(this, PermissionInfo.permissions[i])
            != PackageManager.PERMISSION_GRANTED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PermissionInfo.permissions,
                            PermissionInfo.PERMISSION_REQUESCODE[i]);
                }
            } else{
                PermissionInfo.PERMISSION_COUNT++;
            }
        }
        loadAudioList();
        PermissionInfo.PERMISSION_COUNT = 0;
    }

    private void loadAudioList(){
        if(PermissionInfo.PERMISSION_COUNT == PermissionInfo.TOTAL_PERMISSION){
            AudioLocalDataSource audioLocalDataSource = AudioLocalDataSource
                    .getInstance(getContentResolver(), getResources());
            AudioRepository repository = AudioRepository.getInstance(audioLocalDataSource);
            mPresenter = new MainActivityPresenter(repository, this);
            mPresenter.start();
            mPresenter.loadListAudio();
        }
        // otherwise: do nothing here
    }
}
