package com.ateam.herbacrop.ui.main.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.databinding.FragmentHomeBinding
import com.ateam.herbacrop.ui.main.adapter.RecylerBerandaAdapter
import com.ateam.herbacrop.ui.main.adapter.RecylerTrendingAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecylerBerandaAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBeranda.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecylerBerandaAdapter()
        }

        binding.rvTrending.apply {
            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter  = RecylerTrendingAdapter()
        }
    }
}