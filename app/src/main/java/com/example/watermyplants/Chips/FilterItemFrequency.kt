package com.example.watermyplants.Chips

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip

data class FilterItemFrequency(
    val id_filter: Int
)


fun FilterItemFrequency.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context).inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                this.text = "D"
            }
            1 -> {
                this.text = "S"
            }
            2 -> {
                this.text = "T"
            }
            3 -> {
                this.text = "Q"
            }
            4 -> {
                this.text = "Q"
            }
            5 -> {
                this.text = "S"
            }
            6 -> {
                this.text = "S"
            }

        }
    }
    return chip
}
