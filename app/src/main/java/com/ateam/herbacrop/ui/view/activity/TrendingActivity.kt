package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.core.domain.model.TrendingModel
import com.ateam.herbacrop.databinding.ActivityTrendingBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.ateam.herbacrop.ui.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrendingBinding
    private lateinit var trendingAdapter: RecyclerSearchAdapter
    private val viewModel : HomeViewModel by viewModel()


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

        viewModel.getPlantByTrending(name).observe(this,{
            if (it.isNotEmpty()){
                trendingAdapter.setData(it)
                trendingAdapter.notifyDataSetChanged()
            }
        })

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