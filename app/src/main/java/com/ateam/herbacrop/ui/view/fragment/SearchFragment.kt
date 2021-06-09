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
import com.ateam.herbacrop.ui.viewmodel.SearchViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: RecyclerSearchAdapter
    private val viewModel: SearchViewModel by viewModel()

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

            viewModel.getSearchList(search).observe(viewLifecycleOwner,{
                if (it.isNotEmpty()){
                    binding.message.visibility = View.GONE
                    searchAdapter.setData(it)
                    searchAdapter.notifyDataSetChanged()
                }
            })

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