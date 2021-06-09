package com.ateam.herbacrop.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.model.TrendingModel
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase

class HomeViewModel constructor(private val useCase: PlantUseCase) : ViewModel() {
    fun getNewsList() : LiveData<List<NewsModel>> = useCase.getNewsData()
    fun getTrendingList(): LiveData<List<TrendingModel>> = useCase.getTrendingData()
    fun getPlantByTrending(query: String) : LiveData<List<PlantModel>> = useCase.getPlantByTrending(query)
}