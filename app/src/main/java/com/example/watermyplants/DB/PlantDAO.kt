package com.example.watermyplants.DB

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PlantDAO {

    @Insert
    fun insertNewPlant(plantEntity: PlantEntity)



}