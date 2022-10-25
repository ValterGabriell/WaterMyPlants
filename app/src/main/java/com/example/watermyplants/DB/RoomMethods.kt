package com.example.watermyplants.DB

import android.content.Context
import com.example.watermyplants.Model.PlantItem

class RoomMethods(context: Context) : RoomDataSource {

    private val getDao = DatabaseManager.getInstance(context).getDao()
    override suspend fun insertNewPlant(plantItem: PlantItem) {
        getDao.insertNewPlant(PlantEntity.fromModelToEntity(plantItem))
    }

}