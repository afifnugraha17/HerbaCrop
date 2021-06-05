package com.ateam.herbacrop.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.ActivityDetailBinding
import com.ateam.herbacrop.favorite.FavoriteViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel : FavoriteViewModel by viewModels()

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

        var isChecked = false

        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkFavorite(data.id)
            Timber.tag("cek").d(count.toString())
            withContext(Dispatchers.Main){
                if (count > 0){
                    binding.toggleButton.isChecked = true
                    isChecked = true
                }else{
                    viewModel.postData(data)
                    binding.toggleButton.isChecked = false
                    isChecked = false
                }
            }
        }

        binding.toggleButton.setOnClickListener{
            isChecked = !isChecked
            if (isChecked){
                viewModel.setFavorite(data.id)
            }else{
                viewModel.unSetFavorite(data.id)
            }
            binding.toggleButton.isChecked = isChecked
        }
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