package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.LibraryModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.ActivityLibraryListBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LibraryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLibraryListBinding
    private lateinit var listAdapter: RecyclerSearchAdapter
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")
    private val list = mutableListOf<PlantModel>()

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<LibraryModel>(EXTRA_USERS) as LibraryModel

        supportActionBar?.title = data.title

        listAdapter = RecyclerSearchAdapter()

        koleksi.document("home").collection("library")
            .orderBy("type").startAt(data.type).endAt(
                if (data.type == "herbal"){
                    '\uf8ff'+data.type
                }else{
                    data.type+'\uf8ff'
                }
            ).get()
            .addOnSuccessListener {
                val collection: List<DocumentSnapshot> = it.documents
                for (document in collection){
                    val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                    println("hasil search : $newsModel")
                    newsModel?.let { it1 -> list.add(it1) }
                    listAdapter.setData(list)
                }
                listAdapter.notifyDataSetChanged()
            }

        binding.rvListLibrary.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        listAdapter.setOnItemClickCallback(object : RecyclerSearchAdapter.OnItemClickCallback{
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