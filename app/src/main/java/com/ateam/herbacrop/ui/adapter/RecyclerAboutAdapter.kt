package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.AboutModel
import com.ateam.herbacrop.databinding.AboutItemBinding
import com.bumptech.glide.Glide


class RecyclerAboutAdapter() : RecyclerView.Adapter<RecyclerAboutAdapter.ViewHolder>() {

    private val dataList = ArrayList<AboutModel>()

    fun setData(items:List<AboutModel>){
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
    }


    class ViewHolder(val binding:AboutItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAboutAdapter.ViewHolder = ViewHolder(AboutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: RecyclerAboutAdapter.ViewHolder, position: Int) {
        val about = dataList[position]
        holder.binding.namaAbout.text = about.name
        holder.binding.tvPath.text  =about.role
        Glide.with(holder.binding.root).load(about.profile_photo).into(holder.binding.profileImage)

    }

    override fun getItemCount(): Int {
      return dataList.size
    }

}