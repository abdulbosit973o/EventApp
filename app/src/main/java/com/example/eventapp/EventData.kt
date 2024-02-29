package com.example.eventapp

import android.widget.Switch

data class EventData(
    val id:Int,
    val name:String,
    val description:String,
    val image:Int,
    val switch: Int
)