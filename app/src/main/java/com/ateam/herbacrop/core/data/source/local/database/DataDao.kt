package com.ateam.herbacrop.core.data.source.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ateam.herbacrop.core.data.source.local.entity.PlantData

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun postData(plant : PlantData)

    @Query("SELECT * FROM plants WHERE favorite = 1")
    fun getFavorite() : LiveData<List<PlantData>>

    @Query("UPDATE plants SET favorite = 1 WHERE id = :id")
    fun setFavorite(id: Int)

    @Query("UPDATE plants SET favorite = 0 WHERE id = :id")
    fun unSetFavorite(id: Int)

    @Query("SELECT count(*) FROM plants WHERE favorite = 1 AND id = :id")
    suspend fun checkFavorite(id: Int): Int
}