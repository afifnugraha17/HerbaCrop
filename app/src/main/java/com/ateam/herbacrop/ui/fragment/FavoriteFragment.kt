package com.ateam.herbacrop.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.databinding.FragmentFavoriteBinding
import com.ateam.herbacrop.ui.adapter.RecylerLibraryAdapter


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater,container, false)
        return binding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvFavorit.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecylerLibraryAdapter()
        }
    }

    
}