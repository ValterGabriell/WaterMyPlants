package com.example.watermyplants.DB

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.watermyplants.Model.PlantItem

@Entity(tableName = "Tabela de Plantas")
class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "ml")
    val ml: Int = 0,
    @ColumnInfo(name = "plantColor")
    val plantColor: Int = 0,
    @ColumnInfo(name = "light")
    val light: String = "",
    @ColumnInfo(name = "temperature")
    val temperature: Float = 0f,
    @ColumnInfo(name = "frequencyDay")
    val frequencyDay: Long = 0L,
    @ColumnInfo(name = "photo")
    val photo:Bitmap? = null,
    @ColumnInfo(name = "isWater")
    val isWater: Boolean = false
) {
    companion object {
        fun fromModelToEntity(plantItem: PlantItem) = PlantEntity(
            plantItem.id,
            plantItem.title,
            plantItem.ml,
            plantItem.plantColor,
            plantItem.light,
            plantItem.temperature,
            plantItem.frequencyDay,
            plantItem.photo,
            plantItem.isWater
        )
    }

    fun getAll() = PlantItem(id, title, ml, plantColor, light, temperature,frequencyDay, photo, isWater)
}