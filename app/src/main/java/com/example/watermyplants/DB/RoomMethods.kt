package com.example.watermyplants.DB

import android.content.Context
import com.example.watermyplants.Model.PlantItem

class RoomMethods(context: Context) : RoomDataSource {

    private val getDao = DatabaseManager.getInstance(context).getDao()
    override suspend fun insertNewPlant(plantItem: PlantItem) {
        return getDao.insertNewPlant(PlantEntity.fromModelToEntity(plantItem))
    }

    override suspend fun getAllPlants(): List<PlantItem> {
        return getDao.getAllPlant().map { plantEntity ->
            plantEntity.getAll()
        }
    }

    override suspend fun getPlantPerId(id: Int): PlantItem {
        return getDao.getPlantPerId(id).getAll()


    }

    override suspend fun deletePlantPerId(id: Int) {
        return getDao.deletePlantPerId(id)
    }

    override suspend fun getIdPlantPerTitle(title: String): Int {
        return getDao.getIdPlantPerTitle(title)
    }

}