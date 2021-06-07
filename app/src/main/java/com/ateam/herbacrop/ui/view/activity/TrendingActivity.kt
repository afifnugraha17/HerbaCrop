package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
    private var list = mutableListOf<PlantModel>()
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<TrendingModel>(EXTRA_USERS) as TrendingModel
        val name = data.name

        trendingAdapter = RecyclerSearchAdapter()
        trendingAdapter.notifyDataSetChanged()

        supportActionBar?.title = "Tanaman untuk $name"

        koleksi.document("home").collection("library")
            .orderBy("manfaat").startAt(name).endAt('\uf8ff'+name+'\uf8ff').get()
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
                    trendingAdapter.setData(list)
                    trendingAdapter.notifyDataSetChanged()
                }
            }

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(this@TrendingActivity)
            adapter = trendingAdapter
        }

        trendingAdapter.setOnItemClickCallback(object : RecyclerSearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: PlantModel) {
                showSelectedPlant(data)
            }
        })
    }
    private fun showSelectedPlant(data: PlantModel) {
        val moveWithDataIntent = Intent(this, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }
}