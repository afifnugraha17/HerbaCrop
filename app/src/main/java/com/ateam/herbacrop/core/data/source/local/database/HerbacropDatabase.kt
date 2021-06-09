package com.ateam.herbacrop.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ateam.herbacrop.core.data.source.local.entity.PlantData

@Database(entities = [PlantData::class], version = 1)
abstract class HerbacropDatabase : RoomDatabase() {
    abstract fun plantDao() : DataDao
}