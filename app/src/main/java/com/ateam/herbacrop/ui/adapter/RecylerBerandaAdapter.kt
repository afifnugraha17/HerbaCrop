package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.NewsModel
import com.ateam.herbacrop.databinding.BerandaItemBinding
import com.bumptech.glide.Glide

class RecylerBerandaAdapter :RecyclerView.Adapter<RecylerBerandaAdapter.ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val dataList=  ArrayList<NewsModel>()

    fun setData(items: List<NewsModel>) {
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }


    class ViewHolder(val binding: BerandaItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(BerandaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = dataList[position]
        holder.binding.title.text = news.title
        holder.binding.desc.text = news.description
        Glide.with(holder.binding.root).load(news.image).into(holder.binding.profileImage)
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(dataList[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: NewsModel)
    }
}