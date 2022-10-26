package com.example.watermyplants.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlantDAO {

    @Insert
    fun insertNewPlant(plantEntity: PlantEntity)

    @Query("SELECT * FROM `Tabela de Plantas`")
    fun getAllPlant():List<PlantEntity>

    @Query("SELECT * FROM `Tabela de Plantas` WHERE id = :id")
    fun getPlantPerId(id:Int):PlantEntity

    @Query("SELECT id FROM `Tabela de Plantas` WHERE title = :title")
    fun getIdPlantPerTitle(title:String):Int

    @Query("DELETE FROM `Tabela de Plantas` WHERE id = :id")
    fun deletePlantPerId(id:Int)


}