package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.core.domain.model.LibraryModel
import com.ateam.herbacrop.databinding.LibraryItemBinding
import com.bumptech.glide.Glide

class RecylerLibraryAdapter :RecyclerView.Adapter<RecylerLibraryAdapter.ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<LibraryModel>()

    fun setData(items: List<LibraryModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: LibraryItemBinding):RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(LibraryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val library = list[position]
        holder.binding.titleLibrary.text  = library.title
        val imageSource = holder.binding.root.resources.getIdentifier(library.image, "drawable", holder.binding.root.context.packageName)
        Glide.with(holder.binding.root).load(imageSource).into(holder.binding.imageLibrary)
        holder.binding.root.setOnClickListener {
            onItemClickCallback.onItemClicked(list[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: LibraryModel)
    }
}


