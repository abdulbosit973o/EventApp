package com.example.eventapp

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventapp.databinding.ActivityMainBinding
import com.example.eventapp.pref.MyShared
import com.example.eventapp.pref.myLog

class MainActivity : AppCompatActivity() {
    private val batteryReceiver = BroadcastReceiver()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS)) {

            }
        }

        setSwitchedSwitchers()

        val musicIntent = Intent(this, MusicService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(musicIntent)
        }
        this.startService(musicIntent)
        initListeners()
    }

    private fun setSwitchedSwitchers() {
        "Activity is restarted".myLog()

        if (MyShared.getSwitcher(Intent.ACTION_POWER_CONNECTED) == 1) {
            binding.switcherPowerConnected.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherPowerConnected.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_POWER_DISCONNECTED) == 1) {
            binding.switcherPowerDisconnected.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherPowerDisconnected.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_TIMEZONE_CHANGED) == 1) {
            binding.switcherTimezoneChanged.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherTimezoneChanged.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}off") == 1) {
            binding.switcherAirplaneModeOff.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherAirplaneModeOff.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}on") == 1) {
            binding.switcherAirplaneModeOn.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherAirplaneModeOn.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_SCREEN_OFF) == 1) {
            binding.switcherScreenOff.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherScreenOff.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_BATTERY_LOW) == 1) {
            binding.switcherBatteryLow.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherBatteryLow.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_BATTERY_OKAY) == 1) {
            binding.switcherBatteryFull.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherBatteryFull.setImageResource(R.drawable.switch_off)
        }

        if (MyShared.getSwitcher(Intent.ACTION_SCREEN_ON) == 1) {
            binding.switcherScreenOn.setImageResource(R.drawable.switch_on)
        } else {
            binding.switcherScreenOn.setImageResource(R.drawable.switch_off)
        }
    }

    private fun initListeners() {

        binding.switcherWifiCon.setOnClickListener {
            val switcher = MyShared.getSwitcher("android.net.wifi.STATE_CHANGECON")
            if (switcher == 0) {
                MyShared.setSwitcher("android.net.wifi.STATE_CHANGECON", ActionEnum.SWITCH_ON)
                binding.switcherWifiCon.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher("android.net.wifi.STATE_CHANGECON", ActionEnum.SWITCH_OFF)
                binding.switcherWifiCon.setImageResource(R.drawable.switch_off)
            }

        }
        binding.switcherWifiDiscon.setOnClickListener {
            val switcher = MyShared.getSwitcher("android.net.wifi.STATE_CHANGEDISCON")
            if (switcher == 0) {
                MyShared.setSwitcher("android.net.wifi.STATE_CHANGEDISCON", ActionEnum.SWITCH_ON)
                binding.switcherWifiDiscon.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher("android.net.wifi.STATE_CHANGEDISCON", ActionEnum.SWITCH_OFF)
                binding.switcherWifiDiscon.setImageResource(R.drawable.switch_off)
            }
        }

        binding.switcherPowerConnected.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_POWER_CONNECTED)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_POWER_CONNECTED, ActionEnum.SWITCH_ON)
                binding.switcherPowerConnected.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_POWER_CONNECTED, ActionEnum.SWITCH_OFF)
                binding.switcherPowerConnected.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherPowerDisconnected.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_POWER_DISCONNECTED)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_POWER_DISCONNECTED, ActionEnum.SWITCH_ON)
                binding.switcherPowerDisconnected.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_POWER_DISCONNECTED, ActionEnum.SWITCH_OFF)
                binding.switcherPowerDisconnected.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherTimezoneChanged.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_TIMEZONE_CHANGED)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_TIMEZONE_CHANGED, ActionEnum.SWITCH_ON)
                binding.switcherTimezoneChanged.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_TIMEZONE_CHANGED, ActionEnum.SWITCH_OFF)
                binding.switcherTimezoneChanged.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherAirplaneModeOff.setOnClickListener {
            val switcher = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}off")
            if (switcher == 0) {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}off",
                    ActionEnum.SWITCH_ON
                )
                binding.switcherAirplaneModeOff.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}off",
                    ActionEnum.SWITCH_OFF
                )
                binding.switcherAirplaneModeOff.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherAirplaneModeOn.setOnClickListener {
            val switcher = MyShared.getSwitcher("${Intent.ACTION_AIRPLANE_MODE_CHANGED}on")
            if (switcher == 0) {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}on",
                    ActionEnum.SWITCH_ON
                )
                binding.switcherAirplaneModeOn.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(
                    "${Intent.ACTION_AIRPLANE_MODE_CHANGED}on",
                    ActionEnum.SWITCH_OFF
                )
                binding.switcherAirplaneModeOn.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherScreenOff.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_SCREEN_OFF)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_SCREEN_OFF, ActionEnum.SWITCH_ON)
                binding.switcherScreenOff.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_SCREEN_OFF, ActionEnum.SWITCH_OFF)
                binding.switcherScreenOff.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherBatteryLow.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_BATTERY_LOW)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_LOW, ActionEnum.SWITCH_ON)
                binding.switcherBatteryLow.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_LOW, ActionEnum.SWITCH_OFF)
                binding.switcherBatteryLow.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherBatteryFull.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_BATTERY_OKAY)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_OKAY, ActionEnum.SWITCH_ON)
                binding.switcherBatteryFull.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_BATTERY_OKAY, ActionEnum.SWITCH_OFF)
                binding.switcherBatteryFull.setImageResource(R.drawable.switch_off)
            }
        }
        binding.switcherScreenOn.setOnClickListener {
            val switcher = MyShared.getSwitcher(Intent.ACTION_SCREEN_ON)
            if (switcher == 0) {
                MyShared.setSwitcher(Intent.ACTION_SCREEN_ON, ActionEnum.SWITCH_ON)
                binding.switcherScreenOn.setImageResource(R.drawable.switch_on)
            } else {
                MyShared.setSwitcher(Intent.ACTION_SCREEN_ON, ActionEnum.SWITCH_OFF)
                binding.switcherScreenOn.setImageResource(R.drawable.switch_off)
            }
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

    override fun onDestroy() {
        super.onDestroy()
    }
}