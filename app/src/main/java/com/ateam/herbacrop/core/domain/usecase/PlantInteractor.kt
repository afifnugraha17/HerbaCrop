package com.ateam.herbacrop.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.repository.IRepository

class PlantInteractor constructor(private val repository: IRepository) : PlantUseCase {
    override fun addToDb(id: PlantModel) = repository.addToDb(id)

    override fun getFavorite(): LiveData<List<PlantModel>> = repository.getFavorite()

    override fun checkFavorite(id: Int): Int = repository.checkFavorite(id)

    override fun setFavorite(id: Int) = repository.setFavorite(id)

    override fun unSetFavorite(id: Int) = repository.unSetFavorite(id)
}