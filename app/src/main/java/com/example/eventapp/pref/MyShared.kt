package com.example.eventapp.pref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.eventapp.ActionEnum

object MyShared {
    private lateinit var sharedPreferences: SharedPreferences
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Event_App", Context.MODE_PRIVATE)
    }
    fun setSwitcher(name: String, actionEnum: ActionEnum) {
        "Set Switcher Method "+ name.myLog()
        val switcher: Int = when (actionEnum) {
            ActionEnum.SWITCH_OFF -> 0
            ActionEnum.SWITCH_ON -> 1
        }
        sharedPreferences.edit().putInt(name, switcher).apply()
    }
    fun getSwitcher(name: String): Int {
        "GET Switcher Method "+ name.myLog()
        return sharedPreferences.getInt(name, 0)
    }
}
fun String.myLog(string: String = "TTT") {
    Log.d(string, this)
}