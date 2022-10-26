package com.example.watermyplants.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.watermyplants.DB.RoomMethods
import com.example.watermyplants.Model.PlantItem

class CameraViewModel(application: Application) : AndroidViewModel(application) {


    suspend fun inserNewPlant(plantItem: PlantItem) {
        RoomMethods(getApplication()).insertNewPlant(plantItem)

    }

    suspend fun getIdPerTitle(title: String): Int {
        return RoomMethods(getApplication()).getIdPlantPerTitle(title)
    }

    suspend fun getPlantPerId(id:Int): PlantItem {
        return RoomMethods(getApplication()).getPlantPerId(id)
    }



}