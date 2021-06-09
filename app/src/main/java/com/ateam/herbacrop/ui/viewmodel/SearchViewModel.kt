package com.ateam.herbacrop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase

class SearchViewModel constructor(private val useCase: PlantUseCase) : ViewModel() {
    fun getSearchList(query: String) : LiveData<List<PlantModel>> = useCase.searchPlant(query)
}