package com.example.watermyplants.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.watermyplants.Model.PlantItem
import com.example.watermyplants.Repository.CameraRepository

class CameraViewModel(application: Application, private val cameraRepository: CameraRepository) : AndroidViewModel(application) {


    suspend fun inserNewPlant(plantItem: PlantItem){
        cameraRepository.insertNewPlant(getApplication(), plantItem)
    }



}