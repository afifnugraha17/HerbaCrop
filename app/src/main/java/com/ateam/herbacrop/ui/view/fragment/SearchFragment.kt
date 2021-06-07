package com.ateam.herbacrop.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.FragmentSearchBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.ateam.herbacrop.ui.view.activity.DetailActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: RecyclerSearchAdapter
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")
    private var list = mutableListOf<PlantModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = RecyclerSearchAdapter()
        searchAdapter.notifyDataSetChanged()

        binding.homeBtnSearch.setOnClickListener {
            val search = binding.homeEtSearch.text.toString().trim()
            println("search edit text : $search")
            if (search.isEmpty()){
                binding.homeEtSearch.error = "Tulis sesuatu!"
            }

            koleksi.document("home").collection("library")
                .orderBy("nama").startAt(search).endAt(search+'\uf8ff').get()
                .addOnSuccessListener {
                    var list2 = mutableListOf<PlantModel>()
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: PlantModel? = document.toObject(PlantModel::class.java)
                        println("hasil search : $newsModel")
                        newsModel?.let { it1 -> list2.add(it1) }
                    }
                    list = list2
                }
            binding.message.visibility = View.GONE
            searchAdapter.setData(list)
            searchAdapter.notifyDataSetChanged()
        }

        searchAdapter.setOnItemClickCallback(object: RecyclerSearchAdapter.OnItemClickCallback{
            override fun onItemClicked(data: PlantModel) {
                showSelectedPlant(data)
            }
        })

        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchAdapter
        }
    }

    private fun showSelectedPlant(data: PlantModel) {
        val moveWithDataIntent = Intent(context, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }

}