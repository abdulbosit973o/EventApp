package com.example.eventapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.provider.Settings
import android.util.Log

class BroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val musicIntent = Intent(context, MusicService::class.java)
        when (intent?.action) {
            Intent.ACTION_BATTERY_LOW -> {
//                musicIntent.action = MusicService.ACTION_BATTERY_LOW
                context?.startService(musicIntent)
            }

            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                if (context?.let { isAirplaneModeOn(it) } == true) {
                    MusicService.ACTION_PLAY = R.raw.airplane_off
                } else {
                    MusicService.ACTION_PLAY = R.raw.airplane_on
                }
                context?.startService(musicIntent)
            }

            Intent.ACTION_BATTERY_OKAY -> {
//                musicIntent.action = MusicService.ACTION_BATTERY_LOW
                context?.startService(musicIntent)
            }

            Intent.ACTION_CALL -> {
//                musicIntent.action = MusicService.ACTION_CALL
                context?.startService(musicIntent)
            }

            Intent.ACTION_DATE_CHANGED -> {
                context?.startService(musicIntent)
            }

            Intent.ACTION_SCREEN_ON -> {
                MusicService.ACTION_PLAY = R.raw.ekran_yondi
                context?.startService(musicIntent)
            }

            Intent.ACTION_SCREEN_OFF -> {
                MusicService.ACTION_PLAY = R.raw.ekran_ochdi
                context?.startService(musicIntent)
            }

            Intent.ACTION_POWER_CONNECTED -> {
                MusicService.ACTION_PLAY = R.raw.power_connected
                context?.startService(musicIntent)
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                MusicService.ACTION_PLAY = R.raw.power_disconnected
                context?.startService(musicIntent)
            }

            Intent.ACTION_TIMEZONE_CHANGED -> {
                context?.startService(musicIntent)
            }

            Intent.ACTION_TIME_CHANGED -> {
                context?.startService(musicIntent)
            }
        }
        Log.d("TAG", "onReceive: Ishladi ${context.toString()}")
//        intent?.extras?.get(BatteryManager.EXTRA_LEVEL)

        if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
            val status: Int = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

            // Tizim batareyasi quvvat oldi
            if (status == BatteryManager.BATTERY_STATUS_FULL || status == BatteryManager.BATTERY_STATUS_CHARGING) {
                // Musiqa qo'yish uchun Intent yaratish
                val musicIntent = Intent(context, MusicService::class.java)

                // Musiqa xizmatini ishga tushirish

            }
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
    }
}
