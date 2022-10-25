package com.example.watermyplants.Repository

import android.app.Application
import com.example.watermyplants.DB.RoomMethods
import com.example.watermyplants.Model.PlantItem

class CameraRepository {

    suspend fun insertNewPlant(application: Application, plantItem: PlantItem) {
        RoomMethods(application).insertNewPlant(plantItem)
    }


}