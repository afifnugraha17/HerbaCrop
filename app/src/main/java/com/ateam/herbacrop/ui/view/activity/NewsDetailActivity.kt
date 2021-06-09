package com.ateam.herbacrop.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.databinding.ActivityNewsDetailBinding
import com.bumptech.glide.Glide

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<NewsModel>(EXTRA_USERS) as NewsModel
        BindData(data)
    }

    private fun BindData(data: NewsModel){
        binding.newsTitle.text = data.title
        binding.newsContent.text = data.description
        Glide.with(binding.root).load(data.image).into(binding.newsImage)
    }
}