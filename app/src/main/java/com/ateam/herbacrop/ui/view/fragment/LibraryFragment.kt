package com.ateam.herbacrop.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.databinding.FragmentLibraryBinding
import com.ateam.herbacrop.ui.adapter.RecylerLibraryAdapter

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecylerLibraryAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLibrary.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = RecylerLibraryAdapter()
        }
    }

}