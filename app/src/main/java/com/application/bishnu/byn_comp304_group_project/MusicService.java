package com.application.bishnu.byn_comp304_group_project;
/**
 * Author Bishnu Khanal
 * date: 27/04/2018
 * subject: COMP304-003
 * Project: online book store.
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

public class MusicService extends Service
{
    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    //this method initialize and starts the media player
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.is_it_war);
        mediaPlayer.setLooping(true);
        mediaPlayer.start(); //starts the media player
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        mediaPlayer.stop(); //stops the media player
        super.onDestroy();
    }
}
