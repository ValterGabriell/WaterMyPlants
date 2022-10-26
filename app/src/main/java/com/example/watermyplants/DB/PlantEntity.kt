package com.example.watermyplants.DB

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.watermyplants.Model.PlantItem

@Entity(tableName = "Tabela de Plantas")
class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "ml")
    var ml: Int = 0,
    @ColumnInfo(name = "plantColor")
    var plantColor: Int = 0,
    @ColumnInfo(name = "light")
    var light: String = "",
    @ColumnInfo(name = "temperature")
    var temperature: Float = 0f,
    @ColumnInfo(name = "frequencyDay")
    var frequencyDay: String = "",
    @ColumnInfo(name = "photo")
    var photo: Bitmap? = null,
    @ColumnInfo(name = "isWater")
    var isWater: Boolean = false
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