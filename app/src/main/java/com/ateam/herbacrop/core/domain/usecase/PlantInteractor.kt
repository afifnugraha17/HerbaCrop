package com.ateam.herbacrop.core.domain.usecase

import androidx.lifecycle.LiveData
import com.ateam.herbacrop.core.domain.model.AboutDescModel
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.core.domain.model.*
import com.ateam.herbacrop.core.domain.repository.IRepository

class PlantInteractor constructor(private val repository: IRepository) : PlantUseCase {
    override fun addToDb(id: PlantModel) = repository.addToDb(id)

    override fun getFavorite(): LiveData<List<PlantModel>> = repository.getFavorite()

    override fun checkFavorite(id: Int): Int = repository.checkFavorite(id)

    override fun setFavorite(id: Int) = repository.setFavorite(id)

    override fun unSetFavorite(id: Int) = repository.unSetFavorite(id)

    override fun searchPlant(query: String): LiveData<List<PlantModel>> =repository.searchPlant(query)

    override fun getLibraryIndoor(query: LibraryModel): LiveData<List<PlantModel>> = repository.getLibraryIndoor(query)

    override fun getLibraryOutdoor(query: LibraryModel): LiveData<List<PlantModel>> = repository.getLibraryOutdoor(query)

    override fun getLibraryHerbal(query: LibraryModel): LiveData<List<PlantModel>> = repository.getLibraryHerbal(query)

    override fun getNewsData(): LiveData<List<NewsModel>> = repository.getNewsData()

    override fun getTrendingData(): LiveData<List<TrendingModel>> = repository.getTrendingData()

    override fun getPlantByTrending(query: String): LiveData<List<PlantModel>> = repository.getPlantByTrending(query)

    override fun getAboutData(): LiveData<AboutDescModel> = repository.getAboutData()

    override fun getDeveloperData(): LiveData<List<AboutModel>> = repository.getDeveloperData()

}