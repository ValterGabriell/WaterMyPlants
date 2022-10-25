package com.example.watermyplants.Utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.core.view.children
import com.example.watermyplants.Chips.FilterItemColor
import com.example.watermyplants.Chips.FilterItemFrequency
import com.example.watermyplants.Chips.FilterItemLight
import com.example.watermyplants.Model.PlantItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.datetime.*

object Constants {
    const val LOW = "Pouca"
    const val MEDIUM = "Média"
    const val HIGH = "Muita"
    const val DAILY = "Dia"
    const val WEEK = "Semana"
    const val TAG = "TAG"
    const val PLANT_ID = "PLANT_ID"

    const val DAQUI1DIA = "Daqui 1 dia"
    const val DAQUI2DIAS = "Daqui 2 dias"
    const val DAQUI3DIAS = "Daqui 3 dias"
    const val DAQUI4DIAS = "Daqui 4 dias"
    const val DAQUI5DIAS = "Daqui 5 dias"
    const val DAQUI6DIAS = "Daqui 6 dias"
    const val DAQUI7DIAS = "Daqui 7 dias"




    const val notificationID = 156
    const val channelID = "Canal de notificação-ID"
    const val channelName = "Canal de notificação"


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


    fun Constants.testData(): ArrayList<PlantItem> {
        return arrayListOf(
            PlantItem(
                1,
                "Primeira Planta",
                250,
                Color.YELLOW,
                LOW,
                24.4f,
                123L,
                null
            ),
            PlantItem(
                2,
                "Segunda Planta",
                250,
                Color.CYAN,
                LOW,
                24.4f,
                123,
                null
            ),
            PlantItem(
                3,
                "Terceira Planta",
                250,
                Color.LTGRAY,
                LOW,
                24.4f,
                123,
                null
            )
        )
    }


    fun Constants.filterDay(day: String): Long {
        val now = Clock.System.now()
        val timezone = TimeZone.currentSystemDefault()
        var diffInMillis: Long = 0L
        when (day) {
            this.DAQUI1DIA -> {
                val add = now.plus(1, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)

            }
            this.DAQUI2DIAS -> {
                val add = now.plus(2, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
            this.DAQUI3DIAS -> {
                val add = now.plus(3, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
            this.DAQUI4DIAS -> {
                val add = now.plus(4, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
            this.DAQUI5DIAS -> {
                val add = now.plus(5, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
            this.DAQUI6DIAS -> {
                val add = now.plus(6, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
            this.DAQUI7DIAS -> {
                val add = now.plus(7, DateTimeUnit.DAY, timezone)
                diffInMillis = now.until(add, DateTimeUnit.MILLISECOND, timezone)
            }
        }
        Log.i(this.TAG, diffInMillis.toString())
        return diffInMillis

    }
}