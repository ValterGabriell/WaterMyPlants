package com.example.watermyplants.Model

import android.graphics.Bitmap

data class PlantItem(
    val id:Int,
    val title: String,
    val ml: Int,
    val plantColor: Int,
    val light: String,
    val temperature:Float,
    val frequencyDay:String,
    val photo:Bitmap? = null,
    val isWater : Boolean = false
)