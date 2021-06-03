package com.ateam.herbacrop.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.databinding.FragmentHomeBinding
import com.ateam.herbacrop.ui.adapter.RecylerBerandaAdapter
import com.ateam.herbacrop.ui.adapter.RecylerTrendingAdapter
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var berandaAdapter: RecylerBerandaAdapter
    private val db = Firebase.firestore
    private val list = ArrayList<NewsModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        berandaAdapter = RecylerBerandaAdapter()

        val koleksi = db.collection("main_data")
            koleksi.document("home").collection("news")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: NewsModel? = document.toObject(NewsModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> list.add(it1) }
                        berandaAdapter.setData(list)
                    }

                    berandaAdapter.notifyDataSetChanged()

                }
            }

        binding.rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = berandaAdapter
        }

        binding.rvTrending.apply {
            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter  = RecylerTrendingAdapter()
        }
    }
}