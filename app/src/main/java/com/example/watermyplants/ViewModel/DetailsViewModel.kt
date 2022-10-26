package com.example.watermyplants.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.watermyplants.DB.RoomMethods
import com.example.watermyplants.Model.PlantItem

class DetailsViewModel(application: Application) : AndroidViewModel(application) {


    suspend fun getPlantPerId(id: Int): PlantItem {
        return RoomMethods(getApplication()).getPlantPerId(id)
    }

    suspend fun deletePlantPerId(id: Int) {
        RoomMethods(getApplication()).deletePlantPerId(id)
    }


}