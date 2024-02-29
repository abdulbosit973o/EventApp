package com.example.eventapp

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val batteryReceiver = BroadcastReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter()

        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
        intentFilter.addAction(Intent.ACTION_CALL)
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)


        registerReceiver(batteryReceiver, intentFilter)
    }
    override fun onDestroy() {
        super.onDestroy()
        // Receiver'ni bekor qilish
        unregisterReceiver(batteryReceiver)
    }
}