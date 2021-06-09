package com.ateam.herbacrop.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.FragmentFavoriteBinding
import com.ateam.herbacrop.ui.adapter.RecyclerFavoriteAdapter
import com.ateam.herbacrop.ui.view.activity.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: RecyclerFavoriteAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        favoriteAdapter = RecyclerFavoriteAdapter()

        viewModel.loadFavorite().observe(viewLifecycleOwner,{
            if (it.isNotEmpty()){
                println("favorite data : $it")
                showLoading(false)
                favoriteAdapter.setData(it)
            }
            favoriteAdapter.notifyDataSetChanged()
        })

        binding.rvFavorit.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnItemClickCallback(object: RecyclerFavoriteAdapter.OnItemClickCallback{
            override fun onItemClicked(data: PlantModel) {
                showSelected(data)
            }
        })
    }

    private fun showSelected(data: PlantModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USERS, data)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }


}