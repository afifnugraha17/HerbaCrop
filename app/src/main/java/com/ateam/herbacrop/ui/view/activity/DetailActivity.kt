package com.ateam.herbacrop.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading(true)

        val data = intent.getParcelableExtra<PlantModel>(EXTRA_USERS) as PlantModel
        BindData(data)
    }

    private fun showLoading(state: Boolean) {
        if (state){
            binding.progressbar1.visibility = View.VISIBLE
        }else{
            binding.progressbar1.visibility = View.GONE
        }
    }

    private fun BindData(data: PlantModel) {
        showLoading(false)
        binding.nameDetail.text = data.nama
        binding.caraTanaman.text = data.cara_menanam
        binding.manfaatTanaman.text = data.manfaat
        binding.budiDaya.text = data.budidaya
        Glide.with(binding.root).load(data.image).into(binding.avatarDetail)
    }
}