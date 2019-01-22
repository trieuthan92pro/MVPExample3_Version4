package com.example.framgiatrieuvanthan.mvpexample3.utils;

import android.provider.MediaStore;

import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;

import java.util.HashMap;
import java.util.List;

public class AudioUtils {
    public static String AUDIO_ITEM_ID = "AUDIO_ITEM_ID";
    public static String ACTION_STOP = "com.example.framgiatrieuvanthan.mvpexample3.STOP";
    public static String ACTION_PLAY = "com.example.framgiatrieuvanthan.mvpexample3.PLAY";
    public static String ACTION_PAUSE = "com.example.framgiatrieuvanthan.mvpexample3.PAUSE";
    public static String ACTION_NEXT = "com.example.framgiatrieuvanthan.mvpexample3.NEXT";
    public static String ACTION_PREV = "com.example.framgiatrieuvanthan.mvpexample3.PREV";
    public static String ACTION_RESUME = "com.example.framgiatrieuvanthan.mvpexample3.RESUME";
    public static String ACTION_REPEAT_ONE = "com.example.framgiatrieuvanthan.mvpexample3.RE_ONE";
    public static String ACTION_REPEAT_ALL = "com.example.framgiatrieuvanthan.mvpexample3.RE_ALL";
    public static final String SONG_PATH = "SONG_PATH";
    public static final String SERVICE_STATE = "SERVICE_STATATE";

    public static String[] paths = new String[]{
            "/sdcard/Music","/storage/emulated/0/Music",
            "/storage/emulated/0/Download",
            "/sdcard/Download"
    };

    public static String[] PROJECTIONS = {
            MediaStore.Audio.Media._ID,     //0
            MediaStore.Audio.Media.ARTIST,  //1
            MediaStore.Audio.Media.TITLE,   //2
            MediaStore.Audio.Media.DATA,    //3
            MediaStore.Audio.Media.DISPLAY_NAME,//4
            MediaStore.Audio.Media.DURATION,//5
            MediaStore.Audio.Media.ALBUM,   //6
    };

    private static HashMap<String, Audio> sHashMap = new HashMap<>();

    public static void setListAudioHashMap(List<Audio> list){
        sHashMap.clear();
        for (int i = 0; i < list.size(); i++) {
            sHashMap.put(list.get(i).getId(), list.get(i));
        }
    }

    public static HashMap<String, Audio> getHashMapInstance(){
        return sHashMap;
    }

}
