package com.ateam.herbacrop.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.core.domain.model.TrendingModel
import com.ateam.herbacrop.databinding.FragmentHomeBinding
import com.ateam.herbacrop.ui.adapter.RecylerBerandaAdapter
import com.ateam.herbacrop.ui.adapter.RecylerTrendingAdapter
import com.ateam.herbacrop.ui.view.activity.NewsDetailActivity
import com.ateam.herbacrop.ui.view.activity.TrendingActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var berandaAdapter: RecylerBerandaAdapter
    private lateinit var trendingAdapter: RecylerTrendingAdapter
    private val db = Firebase.firestore
    private val koleksi = db.collection("main_data")
    private val list = ArrayList<NewsModel>()
    private val trend = ArrayList<TrendingModel>()


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
        trendingAdapter = RecylerTrendingAdapter()

        koleksi.document("home").collection("trending")
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty){
                    val collection: List<DocumentSnapshot> = it.documents
                    for (document in collection){
                        val newsModel: TrendingModel? = document.toObject(TrendingModel::class.java)
                        println("home : $newsModel")
                        newsModel?.let { it1 -> trend.add(it1) }
                        trendingAdapter.setData(trend)
                    }
                    trendingAdapter.notifyDataSetChanged()
                }
            }

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

        berandaAdapter.setOnItemClickCallback(object : RecylerBerandaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: NewsModel) {
                showSelectedNews(data)
            }
        })

        trendingAdapter.setOnItemClickCallback(object : RecylerTrendingAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TrendingModel) {
                showSelectedTrending(data)
            }
        })

        binding.rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = berandaAdapter
        }

        binding.rvTrending.apply {
            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter  = trendingAdapter
        }
    }

    private fun showSelectedNews(data: NewsModel) {
        val moveWithDataIntent = Intent(context, NewsDetailActivity::class.java)
        moveWithDataIntent.putExtra(NewsDetailActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }

    private fun showSelectedTrending(data: TrendingModel) {
        val moveWithDataIntent = Intent(context, TrendingActivity::class.java)
        moveWithDataIntent.putExtra(TrendingActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }
}