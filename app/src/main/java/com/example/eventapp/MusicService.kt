package com.example.eventapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class MusicService : Service() {
    private val CHANNEL = "DEMO"
    private val receiver = BroadcastReceiver()

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startMyService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return if (intent?.extras?.get("STOP") != "STOP") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(receiver, registerReceiver(), RECEIVER_EXPORTED)
            }
            START_NOT_STICKY
        } else {
            stopSelf()
            START_NOT_STICKY
        }


    }
    private fun registerReceiver() : IntentFilter {
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
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED")
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED")
        intentFilter.addAction("android.net.wifi.STATE_CHANGE")
        return intentFilter
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel =
                NotificationChannel("DEMO", CHANNEL, NotificationManager.IMPORTANCE_DEFAULT)
            channel.setSound(null, null)
            val service = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(channel)
        }
    }

    private fun startMyService() {
        val notifyIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent
            .getActivity(
                this,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat
            .Builder(this, CHANNEL)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Events Sound")
            .setCustomContentView(createNotificationLayout())
            .build()

        startForeground(1, notification)
    }

    private fun createNotificationLayout(): RemoteViews {
        val view = RemoteViews(packageName, R.layout.remote_view)
        view.setOnClickPendingIntent(R.id.closeBtn, createPendingIntent())
        view.setTextViewText(R.id.textMusicName, "Events App is running")
        view.setTextViewText(R.id.textArtistName, "Click to more options")
        return view
    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(this, MusicService::class.java)
        intent.putExtra("STOP", "STOP")
        return PendingIntent
            .getService(
                this,
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        receiver.clearReceiver()
        unregisterReceiver(receiver)
    }
}
