package com.example.watermyplants.Model

import android.graphics.Bitmap

data class PlantItem(
    val id:Int,
    val title: String,
    val ml: Int,
    val color: Int,
    val light: String,
    val temperature:Double,
    val frequencyDay:String,
    val photo:Bitmap? = null
)