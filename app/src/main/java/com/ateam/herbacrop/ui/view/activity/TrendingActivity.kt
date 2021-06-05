package com.ateam.herbacrop.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.model.TrendingModel
import com.ateam.herbacrop.databinding.ActivityTrendingBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TrendingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrendingBinding
    private lateinit var trendingAdapter: RecyclerSearchAdapter
    private val list = ArrayList<PlantModel>()
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        trendingAdapter = RecyclerSearchAdapter()

        val data = intent.getParcelableExtra<TrendingModel>(EXTRA_USERS) as TrendingModel
        val name = data.name

        supportActionBar?.title = "Tanaman untuk $name"

        koleksi.document("home").collection("library")
            .orderBy("manfaat").endAt('\uf8ff'+name+'\uf8ff').get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> list.add(it1) }
                        trendingAdapter.setData(list)
                    }
                    trendingAdapter.notifyDataSetChanged()
                }
            }
    }
}