package com.example.watermyplants.DB

import com.example.watermyplants.Model.PlantItem

interface RoomDataSource {
    suspend fun insertNewPlant(plantItem: PlantItem)
    suspend fun getAllPlants(): List<PlantItem>
    suspend fun getPlantPerId(id: Int): PlantItem?
    suspend fun deletePlantPerId(id: Int)
    suspend fun getIdPlantPerTitle(title:String):Int


}