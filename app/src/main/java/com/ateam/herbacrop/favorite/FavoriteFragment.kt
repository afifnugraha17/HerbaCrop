package com.ateam.herbacrop.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.databinding.FragmentFavoriteBinding
import com.ateam.herbacrop.ui.adapter.RecyclerSearchAdapter


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: RecyclerSearchAdapter
    private val viewModel: FavoriteViewModel by viewModels()

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
        favoriteAdapter = RecyclerSearchAdapter()

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
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }


}