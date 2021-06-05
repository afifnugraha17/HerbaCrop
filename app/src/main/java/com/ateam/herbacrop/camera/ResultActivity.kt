package com.ateam.herbacrop.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.ActivityResultBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.ateam.herbacrop.ui.view.activity.DetailActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var plantAdapter: RecyclerSearchAdapter
    private val list = ArrayList<PlantModel>()
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")

    companion object {
        const val EXTRA_USERS = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plantAdapter = RecyclerSearchAdapter()

        val data = intent.getStringExtra(EXTRA_USERS)

        koleksi.document("home").collection("library")
            .orderBy("nama").startAt(data).endAt(data+'\uf8ff').get()
            .addOnSuccessListener {
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list.add(it1) }
                    plantAdapter.setData(list)
                }
                plantAdapter.notifyDataSetChanged()
            }

        binding.rvResult.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            hasFixedSize()
            adapter = plantAdapter
        }

        plantAdapter.setOnItemClickCallback(object : RecyclerSearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: PlantModel) {
                showSelectedPlant(data)
            }
        })

        supportActionBar?.title = "Hasil Scan Gambar"

    }

    private fun showSelectedPlant(data: PlantModel) {
        val moveWithDataIntent = Intent(this, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }
}