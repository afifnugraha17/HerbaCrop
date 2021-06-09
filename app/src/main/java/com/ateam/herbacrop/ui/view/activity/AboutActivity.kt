package com.ateam.herbacrop.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ateam.herbacrop.databinding.ActivityAboutBinding
import com.ateam.herbacrop.ui.adapter.RecyclerAboutAdapter
import com.ateam.herbacrop.ui.viewmodel.AboutViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAboutBinding
    private lateinit var aboutAdapter:RecyclerAboutAdapter
    private val viewModel : AboutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "About"

        aboutAdapter = RecyclerAboutAdapter()

        viewModel.getAboutDesc().observe(this, {
            if (it != null){
                println("about : $it")
                binding.desctambah.text = it.abstract_4
                binding.desc2.text = it.abstract_5
                binding.textdesc1.text = it.abstract_6
            }
        })

        viewModel.getDevList().observe(this, {
            if (it.isNotEmpty()){
                aboutAdapter.setData(it)
                aboutAdapter.notifyDataSetChanged()
            }
        })

        binding.rvAbout.apply {
            val layoutManager = LinearLayoutManager(this@AboutActivity)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            adapter = aboutAdapter
        }
    }
}
