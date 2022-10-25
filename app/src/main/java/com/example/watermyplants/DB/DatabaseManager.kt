package com.example.watermyplants.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlantEntity::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "Banco de Dados Plant"
        private var INSTANCE: DatabaseManager? = null

        private fun createDatabase(context: Context): DatabaseManager {
            return Room.databaseBuilder(context, DatabaseManager::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }

        fun getInstance(context: Context):DatabaseManager{
            return (INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            })
        }
    }

    //estudar sobre abstração de funcao
    abstract fun getDao() : PlantDAO



}