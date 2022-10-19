package com.example.watermyplants.Chips

import android.content.Context
import android.view.ContextThemeWrapper
import com.google.android.material.R
import com.google.android.material.chip.Chip

data class FilterItemColor(
    val id_filter: Int
)

fun FilterItemColor.toChip(context: Context): Chip {
    val chip = Chip(ContextThemeWrapper(context, R.style.Widget_MaterialComponents_Chip_Choice))
    chip.apply {
        when(id_filter){
            0 -> {
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_green)
            }
            1->{
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_purple)
            }
            2->{
                setChipBackgroundColorResource(com.example.watermyplants.R.color.filter_blue)
            }
        }

    }
    return chip
}