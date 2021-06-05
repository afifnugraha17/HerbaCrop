package com.ateam.herbacrop.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ateam.herbacrop.core.data.source.local.LocalSource
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.repository.IRepository
import com.ateam.herbacrop.core.utils.DataMapper
import kotlinx.coroutines.runBlocking


class Repository constructor(private val local: LocalSource) : IRepository {
    override fun addToDb(id: PlantModel) {
        val data = DataMapper.domainToEntity(id)

        return runBlocking { local.addToDb(data) }
    }

    override fun getFavorite(): LiveData<List<PlantModel>> {
        val data = runBlocking { local.getFavList() }
        return Transformations.map(data){
            DataMapper.entitiesToDomain(it)
        }
    }

    override fun checkFavorite(id: Int): Int = local.checkFav(id)

    override fun setFavorite(id: Int) = runBlocking { local.setFavorite(id) }

    override fun unSetFavorite(id: Int) = runBlocking { local.unSetFavorite(id) }

}