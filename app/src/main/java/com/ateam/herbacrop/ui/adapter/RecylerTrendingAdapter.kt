package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.core.domain.model.TrendingModel
import com.ateam.herbacrop.databinding.TrendingBerandaItemBinding
import com.bumptech.glide.Glide

class RecylerTrendingAdapter :RecyclerView.Adapter<RecylerTrendingAdapter.ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val dataList=  ArrayList<TrendingModel>()

    fun setData(items: List<TrendingModel>) {
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: TrendingBerandaItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(TrendingBerandaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trend = dataList[position]
        holder.binding.titleTrending.text = trend.name
        Glide.with(holder.binding.root).load(trend.image).into(holder.binding.imageView2)
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: TrendingModel)
    }
}