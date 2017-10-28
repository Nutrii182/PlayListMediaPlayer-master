package com.outlook.gonzasosa.code.firebaseanonymousauth.Models;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.outlook.gonzasosa.code.firebaseanonymousauth.TracksAdapter;

/**
 * Created by nart1 on 26/10/2017.
 */

public class My_Services extends Service implements MediaPlayer.OnPreparedListener{
    @Nullable

    MediaPlayer mediaplayer = new MediaPlayer();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate () {
        super.onCreate();
    }

    //@Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        try {
            mediaplayer.setDataSource("/storage/8235-1CE9/Musica/"+TracksAdapter.cancion+".mp3"); // celular de martin

            mediaplayer.setOnPreparedListener(this);
            mediaplayer.prepare();


        } catch (Exception e) {
            //Log.d("Muestra" , e.getMessage());
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaplayer.stop();

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaplayer.start();
    }
}
