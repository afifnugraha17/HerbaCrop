package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.FavoriteItemBinding
import com.bumptech.glide.Glide

class RecyclerFavoriteAdapter : RecyclerView.Adapter<RecyclerFavoriteAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val dataList=  ArrayList<PlantModel>()

    fun setData(items: List<PlantModel>) {
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = dataList[position]
        val binding = holder.binding
        binding.tvNamaTumbuhan.text = result.nama
        binding.tvType.text = result.type
        Glide.with(binding.root).load(result.image).into(binding.imageFavorit)

        holder.binding.fbFavoriteDetail.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.absoluteAdapterPosition])
        }

        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: PlantModel)
    }
}