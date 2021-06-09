package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.LibraryModel
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.ActivityLibraryListBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter
import com.ateam.herbacrop.ui.viewmodel.LibraryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LibraryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLibraryListBinding
    private lateinit var listAdapter: RecyclerSearchAdapter
    private val viewModel : LibraryViewModel by viewModel()


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

        when(data.type){
            "indoor" -> {
                viewModel.getIndoorList(data).observe(this, {
                    if (it.isNotEmpty()){
                        listAdapter.setData(it)
                        listAdapter.notifyDataSetChanged()
                    }
                })
            }
            "outdoor" -> {
                viewModel.getIndoorList(data).observe(this, {
                    if (it.isNotEmpty()){
                        listAdapter.setData(it)
                        listAdapter.notifyDataSetChanged()
                    }
                })
            }
            "herbal" -> {
                viewModel.getIndoorList(data).observe(this, {
                    if (it.isNotEmpty()){
                        listAdapter.setData(it)
                        listAdapter.notifyDataSetChanged()
                    }
                })
            }
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