package com.example.watermyplants.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.watermyplants.DB.RoomMethods
import com.example.watermyplants.Model.PlantItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    suspend fun getAllPlants(): List<PlantItem> {
        return RoomMethods(getApplication()).getAllPlants()
    }


}