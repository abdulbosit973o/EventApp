package com.example.eventapp

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onstartcommand: Ishladi")
        mediaPlayer = MediaPlayer.create(this, ACTION_PLAY) // "your_music_file" ni o'zingizning musiqa fayl nomingizga almashtiring
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        if (intent?.action == ACTION_PLAY) {
        mediaPlayer = MediaPlayer.create(this, ACTION_PLAY)
        Log.d("TAG", "onstartcommand: Ishladi")
//            // Musiqa ijro etish
            mediaPlayer.start()
//        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Musiqa xizmati tugatilganda mediaplayerni yopamiz
        mediaPlayer.release()
    }

    companion object {
//       const val ACTION_CALL = R.raw.ekran_yondi
        var ACTION_PLAY = R.raw.internet_connected
    }
}
