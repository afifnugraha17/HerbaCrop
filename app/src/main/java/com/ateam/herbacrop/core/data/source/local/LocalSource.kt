package com.ateam.herbacrop.core.data.source.local

import androidx.lifecycle.LiveData
import com.ateam.herbacrop.core.data.source.local.database.DataDao
import com.ateam.herbacrop.core.data.source.local.entity.PlantData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LocalSource constructor(private val dao: DataDao) {
    suspend fun getFavList() : LiveData<List<PlantData>> =
        withContext(Dispatchers.IO){
            dao.getFavorite()
        }

    suspend fun addToDb(user: PlantData) =
        withContext(Dispatchers.IO){
            dao.postData(user)
        }

    fun checkFav(id:Int) : Int = runBlocking {
        val data = async {
            dao.checkFavorite(id)
        }
        data.start()
        data.await()
    }

    suspend fun setFavorite(id: Int) =
        withContext(Dispatchers.IO){
            dao.setFavorite(id)
        }

    suspend fun unSetFavorite(id: Int) =
        withContext(Dispatchers.IO){
            dao.unSetFavorite(id)
        }
}