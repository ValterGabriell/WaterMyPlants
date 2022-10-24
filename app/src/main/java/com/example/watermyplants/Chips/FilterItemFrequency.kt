package com.example.watermyplants.Chips

import android.content.Context
import android.view.LayoutInflater
import com.example.watermyplants.Utils.Constants
import com.google.android.material.chip.Chip

data class FilterItemFrequency(
    val id_filter: Int
)


fun FilterItemFrequency.toChip(context: Context): Chip {
    val chip = LayoutInflater.from(context).inflate(com.example.watermyplants.R.layout.chip_choice, null, false) as Chip
    chip.apply {
        when (id_filter) {
            0 -> {
                this.text = Constants.DAQUI1DIA
            }
            1 -> {
                this.text = Constants.DAQUI2DIAS
            }
            2 -> {
                this.text = Constants.DAQUI3DIAS
            }
            3 -> {
                this.text = Constants.DAQUI4DIAS
            }
            4 -> {
                this.text = Constants.DAQUI5DIAS
            }
            5 -> {
                this.text = Constants.DAQUI6DIAS
            }
            6 -> {
                this.text = Constants.DAQUI7DIAS
            }

        }
    }
    return chip
}
