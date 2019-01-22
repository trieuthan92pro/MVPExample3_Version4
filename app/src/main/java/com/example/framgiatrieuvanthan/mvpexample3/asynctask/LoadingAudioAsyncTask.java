package com.example.framgiatrieuvanthan.mvpexample3.asynctask;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import com.example.framgiatrieuvanthan.mvpexample3.R;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;
import com.example.framgiatrieuvanthan.mvpexample3.utils.AudioUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadingAudioAsyncTask extends AsyncTask<Void, Void, List<Audio>> {
    public static final int LOAD_AUDIO_DONE = 100;
    private static int sId = 1000;
    private Handler mHandler;
    private ContentResolver mContentResolver;
    private Resources resources;
    private List<Audio> list;
    private MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    public LoadingAudioAsyncTask( ContentResolver contentResolver, Resources resources, Handler handler){
        mHandler = handler;
        mContentResolver = contentResolver;
        this.resources = resources;
        list = new ArrayList<>();
    }

    @Override
    protected List<Audio> doInBackground(Void... voids) {
        list = findAllMedia();//
        return list;
    }

    public List<Audio> findAllMedia() {
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = AudioUtils.PROJECTIONS;
        Cursor cursor = mContentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);
        List<Audio> songs = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String duraion = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

            int intDuration = Integer.parseInt(duraion);
            Bitmap bitmap = BitmapFactory.decodeResource(resources,
                        R.drawable.compact_disc);
            Audio song = new Audio(data, title, artist, album, id, intDuration, bitmap);
            if (title == null) {
                String[] songNames = data.split("/");
                int len = songNames.length;
                String name = songNames[len - 1];
                int nameLen = name.length();
                song.setTitle(name.substring(0, nameLen - 4));
            }

            songs.add(song);
//            Log.e("SONG", cursor.getString(2));
        }
        cursor.close();
        return songs;
    }

    @Override
    protected void onPostExecute(List<Audio> audioList) {
        super.onPostExecute(audioList);
        Message message = new Message();
        message.what = LOAD_AUDIO_DONE;
        message.obj = list;
//        Log.e("WHERE ARE U NOW?", "onPostExecute: Process go here" );
        mHandler.sendMessage(message);
    }

    private Uri getAlbumUri(Context mContext, String album_id){
        if(mContext!=null) {
            Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
            Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
            return imageUri;
        }
        return null;
    }

    private ArrayList<HashMap<String, String>> getPlaylist(String path) {
        ArrayList<HashMap<String, String>> playlist = new ArrayList<>();
        HashMap<String, String> aSong = null;
        try {
            File rootFolder = new File(path);
            File[] files = rootFolder.listFiles();// get all file from a folder
            if (files != null) {
                ArrayList<HashMap<String, String>> list;
                for (File file : files) {
                    String absolutePath = file.getAbsolutePath();
                    if (file.isDirectory()) {
                        list = getPlaylist(absolutePath);
                        if (list != null) {
                            playlist.addAll(list);
                        }
                    } else if (file.getName().endsWith(".mp3")) {
                        aSong = new HashMap<>();
                        aSong.put("file_path", absolutePath);
                        playlist.add(aSong);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlist;
    }

    private List<Audio> findAllSong(String[] myPath) {
        List<Audio> playList = new ArrayList<>();
        for (String path : myPath) {
            ArrayList<HashMap<String, String>> songs = getPlaylist(path);
            if (songs != null) {
                int size = songs.size();
                String filePath = "";
                for (int i = 0; i < size; i++) {
                    filePath = songs.get(i).get("file_path");
                    // extract info form a song
                    Audio song = extractSongInfo(filePath);
                    playList.add(song);
//                    Log.e("SONG TITLE", "findAllSong: "+song.getTitle());
                }
            }
        }

        return playList;
    }

    private Audio extractSongInfo(String filePath) {
        mmr.setDataSource(filePath);
        String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String stringDuraion = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        String data = filePath;
        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        Bitmap bitmap = null;
        byte[] artImg = mmr.getEmbeddedPicture();
        if (artImg != null) {
            bitmap = BitmapFactory.decodeByteArray(artImg, 0, artImg.length);
        }
        if (bitmap == null) {
            // it alway must be context.getResources()
            bitmap = BitmapFactory.decodeResource(resources,
                    R.drawable.compact_disc);
        }
        String id = sId++ + "";
        int duraion = Integer.parseInt(stringDuraion);
        /*
        String data, String title, String artist, String album,
                 String id, int duration, Bitmap bitmapAudio
         */
        Audio song = new Audio(data, title,artist, album, id, duraion, bitmap);

        if (title == null) {
            String[] songNames = filePath.split("/");
            int len = songNames.length;
            String name = songNames[len - 1];
            int nameLen = name.length();
            song.setTitle(name.substring(0, nameLen - 4));
        }

        return song;
    }


}
