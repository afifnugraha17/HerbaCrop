package com.ateam.herbacrop.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ateam.herbacrop.core.domain.model.AboutDescModel
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.core.domain.model.*

interface PlantUseCase {
    fun addToDb(id: PlantModel)

    fun getFavorite() : LiveData<List<PlantModel>>

    fun checkFavorite(id:Int) : Int

    fun setFavorite(id: Int)

    fun unSetFavorite(id: Int)

    fun searchPlant(query : String) : LiveData<List<PlantModel>>

    fun getLibraryIndoor(query: LibraryModel) : LiveData<List<PlantModel>>

    fun getLibraryOutdoor(query: LibraryModel) : LiveData<List<PlantModel>>

    fun getLibraryHerbal(query: LibraryModel) : LiveData<List<PlantModel>>

    fun getNewsData() : LiveData<List<NewsModel>>

    fun getTrendingData() : LiveData<List<TrendingModel>>

    fun getPlantByTrending(query: String) : LiveData<List<PlantModel>>

    fun getAboutData() : LiveData<AboutDescModel>

    fun getDeveloperData() : LiveData<List<AboutModel>>
}