package com.ateam.herbacrop.core.data.source.local.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ateam.herbacrop.core.data.source.local.entity.PlantData

interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun postData(plant : PlantData)
}