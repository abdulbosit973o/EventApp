package com.example.eventapp.app

import android.app.Application
import com.example.eventapp.pref.MyShared

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        MyShared.init(this)
    }
}