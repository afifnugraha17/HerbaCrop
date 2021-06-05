package com.ateam.herbacrop.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ateam.herbacrop.core.domain.model.PlantModel

interface PlantUseCase {
    fun addToDb(id: PlantModel)

    fun getFavorite() : LiveData<List<PlantModel>>

    fun checkFavorite(id:Int) : Int

    fun setFavorite(id: Int)

    fun unSetFavorite(id: Int)
}