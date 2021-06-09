package com.ateam.herbacrop.core.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ateam.herbacrop.core.domain.model.AboutDescModel
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.core.domain.model.*
import com.ateam.herbacrop.core.domain.repository.IRepository
import com.ateam.herbacrop.core.utils.DataMapper
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ateam.herbacrop.core.data.source.local.LocalSource
import com.ateam.herbacrop.core.domain.model.PlantModel
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


    override fun searchPlant(query: String): LiveData<List<PlantModel>> {
        val live = MutableLiveData<List<PlantModel>>()
        var list: MutableList<PlantModel>
        Firebase.firestore.collection("main_data").document("home").collection("library")
            .orderBy("nama").startAt(query).endAt(query+'\uf8ff').get()
            .addOnSuccessListener {
                val list2 = mutableListOf<PlantModel>()
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list2.add(it1) }
                }
                list = list2
                live.postValue(list)
            }
        return live
    }

    override fun getLibraryIndoor(query: LibraryModel): LiveData<List<PlantModel>> {
        val live = MutableLiveData<List<PlantModel>>()
        var list: MutableList<PlantModel>
        Firebase.firestore.collection("main_data").document("home").collection("library")
            .orderBy("type").startAt(query.type).endAt(
                if (query.type == "herbal"){
                    '\uf8ff'+query.type
                }else{
                    query.type+'\uf8ff'
                }
            ).get()
            .addOnSuccessListener {
                val list2 = mutableListOf<PlantModel>()
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list2.add(it1) }
                }
                list = list2
                live.postValue(list)
            }
        return live
    }

    override fun getLibraryOutdoor(query: LibraryModel): LiveData<List<PlantModel>> {
        val live = MutableLiveData<List<PlantModel>>()
        var list: MutableList<PlantModel>
        Firebase.firestore.collection("main_data").document("home").collection("library")
            .orderBy("type").startAt(query.type).endAt(
                if (query.type == "herbal"){
                    '\uf8ff'+query.type
                }else{
                    query.type+'\uf8ff'
                }
            ).get()
            .addOnSuccessListener {
                val list2 = mutableListOf<PlantModel>()
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list2.add(it1) }
                }
                list = list2
                live.postValue(list)
            }
        return live
    }

    override fun getLibraryHerbal(query: LibraryModel): LiveData<List<PlantModel>> {
        val live = MutableLiveData<List<PlantModel>>()
        var list: MutableList<PlantModel>
        Firebase.firestore.collection("main_data").document("home").collection("library")
            .orderBy("type").startAt(query.type).endAt(
                if (query.type == "herbal"){
                    '\uf8ff'+query.type
                }else{
                    query.type+'\uf8ff'
                }
            ).get()
            .addOnSuccessListener {
                val list2 = mutableListOf<PlantModel>()
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list2.add(it1) }
                }
                list = list2
                live.postValue(list)
            }
        return live
    }

    override fun getNewsData(): LiveData<List<NewsModel>> {
        val live = MutableLiveData<List<NewsModel>>()
        var list : MutableList<NewsModel>
        Firebase.firestore.collection("main_data").document("home").collection("news")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val list2 = mutableListOf<NewsModel>()
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: NewsModel? = document.toObject(NewsModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> list2.add(it1) }
                    }
                    list = list2
                    live.postValue(list)
                }
            }
        return live
    }

    override fun getTrendingData(): LiveData<List<TrendingModel>> {
        val live = MutableLiveData<List<TrendingModel>>()
        var list: MutableList<TrendingModel>
        Firebase.firestore.collection("main_data").document("home").collection("trending")
            .get()
            .addOnSuccessListener {
                val trend = mutableListOf<TrendingModel>()
                if (!it.isEmpty){
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: TrendingModel? = document.toObject(TrendingModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> trend.add(it1) }
                    }
                    list = trend
                    live.postValue(list)
                }
            }
        return live
    }

    override fun getPlantByTrending(query: String): LiveData<List<PlantModel>> {
        val live = MutableLiveData<List<PlantModel>>()
        var list : MutableList<PlantModel>
        Firebase.firestore.collection("main_data").document("home").collection("library")
            .orderBy("manfaat").startAt(query).endAt('\uf8ff'+query+'\uf8ff').get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val list2 = mutableListOf<PlantModel>()
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> list2.add(it1) }
                        println("list setelah add : $list2")
                    }
                    list = list2
                    live.postValue(list)
                }
            }
        return live
    }

    @SuppressLint("NullSafeMutableLiveData")
    override fun getAboutData(): LiveData<AboutDescModel> {
        val aboutDesc = MutableLiveData<AboutDescModel>()
        Firebase.firestore.collection("main_data").document("home")
            .get()
            .addOnSuccessListener {
                if (it != null){

                    val collection: DocumentSnapshot = it
                    val aboutModel: AboutDescModel? = collection.toObject(AboutDescModel::class.java)
                    println("home :$aboutModel")
                    aboutDesc.postValue(aboutModel)
                }
            }
        return aboutDesc
    }

    override fun getDeveloperData(): LiveData<List<AboutModel>> {
        val live = MutableLiveData<List<AboutModel>>()
        var list = mutableListOf<AboutModel>()
        Firebase.firestore.collection("main_data").document("home").collection("about")
            .get()
            .addOnSuccessListener {
                val list2 = mutableListOf<AboutModel>()
                if (!it.isEmpty){
                    val collection:List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val aboutModel: AboutModel? = document.toObject(AboutModel::class.java)
                        println("home :$aboutModel")
                        aboutModel?.let { it1 -> list2.add(it1) }
                    }
                    list = list2
                }
                live.postValue(list)
            }
        return live
    }
}
