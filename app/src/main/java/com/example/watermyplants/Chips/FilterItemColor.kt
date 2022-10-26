package com.example.watermyplants.Chips

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip

data class FilterItemColor(
    val id_filter: Int
)

fun FilterItemColor.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context).inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.yellow)
                this.text = "1"
            }
            1 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.ltgray)
                this.text = "2"
            }
            2 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.cyan)
                this.text = "3"
            }
        }



    }
    return chip
}