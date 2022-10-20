package com.example.watermyplants.Chips

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import com.example.watermyplants.Constants
import com.example.watermyplants.Constants.makeAToast
import com.google.android.material.R
import com.google.android.material.chip.Chip

data class FilterItemColor(
    val id_filter: Int
)

fun FilterItemColor.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context).inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_green)
                this.text = "1"
            }
            1 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_purple)
                this.text = "2"
            }
            2 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_blue)
                this.text = "3"
            }
        }



    }
    return chip
}