package com.example.watermyplants

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.core.view.children
import com.example.watermyplants.Chips.FilterItemColor
import com.example.watermyplants.Chips.FilterItemFrequency
import com.example.watermyplants.Chips.FilterItemLight
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

object Constants {
    const val LOW = "Pouca"
    const val MEDIUM = "Média"
    const val HIGH = "Muita"
    const val DAILY = "Dia"
    const val WEEK = "Semana"
    const val TAG = "TAG"

    const val PERMISSION_CODE = 1
    const val CAMERA = 2
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)


    val filters_color = arrayOf(
        FilterItemColor(0),
        FilterItemColor(1),
        FilterItemColor(2)
    )

    val filters_light = arrayOf(
        FilterItemLight(0),
        FilterItemLight(1)
    )

    val filter_frequency = arrayOf(
        FilterItemFrequency(0),
        FilterItemFrequency(1),
        FilterItemFrequency(2),
        FilterItemFrequency(3),
        FilterItemFrequency(4),
        FilterItemFrequency(5),
        FilterItemFrequency(6)
    )


    fun Constants.makeAToast(context: Context, string: String, long_or_short: Int) {
        Toast.makeText(context, string, long_or_short).show()
    }

    fun Constants.filterChipToSave(chipGroup: ChipGroup): String {
        val name = chipGroup.children
            .filter {
                (it as Chip).isChecked
            }.map {
                (it as Chip).text.toString()
            }.toList()
        return name.toString().replace("[", "").replace("]", "")
    }



}