package com.example.watermyplants.Utils

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.view.children
import com.example.watermyplants.Chips.FilterItemColor
import com.example.watermyplants.Chips.FilterItemFrequency
import com.example.watermyplants.Chips.FilterItemLight
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.datetime.*
import java.io.ByteArrayOutputStream

object Constants {
    const val LOW = "Pouca luz"
    const val MEDIUM = "Média luz"
    const val HIGH = "Muita luz"
    const val TAG = "TAG"
    const val PLANT_ID = "PLANT_ID"

    const val DAQUI1DIA = "Diariamente"
    const val DAQUI2DIAS = "2 em 2 dias"
    const val DAQUI3DIAS = "3 em 3 dias"
    const val DAQUI4DIAS = "4 em 4 dias"
    const val DAQUI5DIAS = "5 em 5 dias"
    const val DAQUI6DIAS = "6 em 6 dias"
    const val DAQUI7DIAS = "7 em 7 dias"


    const val channelID = "Canal de notificação-ID"
    const val channelName = "Canal de notificação"


    val filters_color = arrayOf(
        FilterItemColor(0),
        FilterItemColor(1),
        FilterItemColor(2)
    )

    val filters_light = arrayOf(
        FilterItemLight(0),
        FilterItemLight(1),
        FilterItemLight(2),
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


    fun Constants.makeAToast(context: Context, string: String, one_to_long_zero_to_short: Int) {
        Toast.makeText(context, string, one_to_long_zero_to_short).show()
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