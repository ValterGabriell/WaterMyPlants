package com.example.watermyplants.Chips

import android.content.Context
import android.view.LayoutInflater
import com.example.watermyplants.Utils.Constants
import com.google.android.material.chip.Chip

data class FilterItemLight(
    val id_filter: Int

)

fun FilterItemLight.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context)
        .inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                this.text = Constants.LOW

            }
            1 -> {

                this.text = Constants.MEDIUM
            }
            2->{
                this.text = Constants.HIGH
            }
        }
    }
    return chip
}


