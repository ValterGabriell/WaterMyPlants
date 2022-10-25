package com.example.watermyplants.DB

import com.example.watermyplants.Model.PlantItem

interface RoomDataSource {
    suspend fun insertNewPlant(plantItem: PlantItem)


}