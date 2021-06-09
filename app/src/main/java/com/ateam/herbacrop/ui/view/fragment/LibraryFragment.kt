package com.ateam.herbacrop.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.core.domain.model.LibraryModel
import com.ateam.herbacrop.databinding.FragmentLibraryBinding
import com.ateam.herbacrop.ui.adapter.RecylerLibraryAdapter
import com.ateam.herbacrop.ui.view.activity.LibraryListActivity

class LibraryFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBinding
    private lateinit var libraryAdapter: RecylerLibraryAdapter
    private val data = listOf(
        LibraryModel(1, "Library Tanaman Outdoor", "@drawable/outdoor", "outdoor"),
        LibraryModel(2, "Library Tanaman Indoor", "@drawable/indoor", "indoor"),
        LibraryModel(3, "Library Tanaman Obat", "@drawable/herbal", "herbal")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLibraryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        libraryAdapter = RecylerLibraryAdapter()

        libraryAdapter.setData(data)

        binding.rvLibrary.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = libraryAdapter
        }

        libraryAdapter.setOnItemClickCallback(object : RecylerLibraryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: LibraryModel) {
                showSelected(data)
            }
        })
    }

    private fun showSelected(data: LibraryModel) {
        val moveWithDataIntent = Intent(context, LibraryListActivity::class.java)
        moveWithDataIntent.putExtra(LibraryListActivity.EXTRA_USERS, data)
        startActivity(moveWithDataIntent)
    }
}