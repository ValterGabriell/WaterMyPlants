package com.example.watermyplants.Chips

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.chip.Chip

data class FilterItemLight(
    val id_filter: Int

)

fun FilterItemLight.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context).inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                this.text = "Muita luz"

            }
            1 -> {

                this.text = "Baixa luz"
            }
        }
    }
    return chip
}


