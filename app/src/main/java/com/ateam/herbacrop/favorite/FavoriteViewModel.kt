package com.ateam.herbacrop.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase

class FavoriteViewModel constructor(private val useCase: PlantUseCase) : ViewModel() {

    fun loadFavorite() : LiveData<List<PlantModel>> = useCase.getFavorite()

    fun checkFavorite(id: Int) : Int = useCase.checkFavorite(id)

    fun setFavorite(id: Int) = useCase.setFavorite(id)

    fun unSetFavorite(id: Int) = useCase.unSetFavorite(id)

    fun postData(plant : PlantModel) = useCase.addToDb(plant)
}