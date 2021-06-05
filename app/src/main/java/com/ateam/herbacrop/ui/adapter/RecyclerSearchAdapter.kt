package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.PlantModel
import com.ateam.herbacrop.databinding.PlantItemBinding
import com.bumptech.glide.Glide

class RecyclerSearchAdapter : RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder>() {
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

    class ViewHolder(val binding: PlantItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(PlantItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = dataList[position]
        val binding = holder.binding
        binding.titlePlant.text = result.nama
        Glide.with(binding.root).load(result.image).into(binding.imagePlant)
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: PlantModel)
    }
}