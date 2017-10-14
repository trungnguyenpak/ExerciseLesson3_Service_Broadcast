package com.trungnguyeen.exerciselesson3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by trungnguyeen on 10/14/17.
 */

public class PlaySongService extends Service {

    public static boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    private static final String TAG = PlaySongService.class.getSimpleName();


    public PlaySongService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.langleyeunhau);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        mediaPlayer.start();
        isPlaying = true;
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        mediaPlayer.release();
        isPlaying = false;
        super.onDestroy();
    }
}
