package com.example.eventapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.provider.Settings
import com.example.eventapp.pref.MyShared

class BroadcastReceiver : BroadcastReceiver() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onReceive(context: Context?, intent: Intent?) {
        mediaPlayer = MediaPlayer.create(context, R.raw.salom)

        when (intent?.action) {
            Intent.ACTION_BATTERY_LOW -> {
                when (MyShared.getSwitcher(Intent.ACTION_BATTERY_LOW)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.battery_low)
                        mediaPlayer.start()
                    }
                }
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (context?.let { isAirplaneModeOn(it) } == true) {
                    if (MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}on") == 1) {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.airplane_on)
                        mediaPlayer.start()
                    }
                } else {
                    if (MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}off") == 1) {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.airplane_off)
                        mediaPlayer.start()
                    }
                }
            }

            Intent.ACTION_BATTERY_OKAY -> {
                when (MyShared.getSwitcher(Intent.ACTION_BATTERY_OKAY)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.battery_full)
                        mediaPlayer.start()
                    }
                }
            }


//            Intent.ACTION_DATE_CHANGED -> {
//
//            }

            Intent.ACTION_SCREEN_ON -> {
                when (MyShared.getSwitcher(Intent.ACTION_SCREEN_ON)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.ekran_yondi)
                        mediaPlayer.start()
                    }
                }
            }

            Intent.ACTION_SCREEN_OFF -> {
                when (MyShared.getSwitcher(Intent.ACTION_SCREEN_OFF)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.ekran_ochdi)
                        mediaPlayer.start()
                    }
                }
            }

            Intent.ACTION_POWER_CONNECTED -> {
                when (MyShared.getSwitcher(Intent.ACTION_POWER_CONNECTED)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.power_connected)
                        mediaPlayer.start()
                    }
                }

            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                when (MyShared.getSwitcher(Intent.ACTION_POWER_DISCONNECTED)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.power_disconnected)
                        mediaPlayer.start()
                    }
                }

            }

            Intent.ACTION_TIMEZONE_CHANGED -> {
                when (MyShared.getSwitcher(Intent.ACTION_TIMEZONE_CHANGED)) {
                    1 -> {
                        if(mediaPlayer.isPlaying) return
                        mediaPlayer = MediaPlayer.create(context, R.raw.vaqt)
                        mediaPlayer.start()
                    }
                }
            }
            "android.bluetooth.device.action.ACL_CONNECTED" -> {
//                MusicService.ACTION_PLAY = R.raw.vaqt
//                context?.startService(musicIntent)
            }

            "android.bluetooth.device.action.ACL_DISCONNECTED" -> {
//                MusicService.ACTION_PLAY = R.raw.vaqt
//                context?.startService(musicIntent)
            }
            "android.net.wifi.STATE_CHANGE" -> {
                when (MyShared.getSwitcher("android.net.wifi.STATE_CHANGECON")) {
                    1 -> {
                        val wifiInfo: WifiInfo? = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO)
                        if (wifiInfo != null) {
                            val supplicantState = wifiInfo.supplicantState
                            if (supplicantState == SupplicantState.COMPLETED) {
                                if(mediaPlayer.isPlaying) return
                                mediaPlayer = MediaPlayer.create(context, R.raw.wifi_connected)
                                mediaPlayer.start()
                            } else {
//                        // Wi-Fi is not connected
//                        if(mediaPlayer.isPlaying
 return//                        mediaPlayer = MediaPlayer.create(context, R.raw.wifi_disconnected)
//                        mediaPlayer.start()
                            }
                        }
                    }
                }

                when (MyShared.getSwitcher("android.net.wifi.STATE_CHANGEDISCON")) {
                    1 -> {
                        val wifiInfo: WifiInfo? = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO)
                        if (wifiInfo == null) {
                            if(mediaPlayer.isPlaying) return
                            mediaPlayer = MediaPlayer.create(context, R.raw.wifi_disconnected)
                            mediaPlayer.start()
                        }
                    }
                }
            }
        }
    }

    fun clearReceiver() {

    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }
}
