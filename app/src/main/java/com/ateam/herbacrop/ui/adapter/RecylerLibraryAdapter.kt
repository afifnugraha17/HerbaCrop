package com.ateam.herbacrop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.R

class RecylerLibraryAdapter :RecyclerView.Adapter<RecylerLibraryAdapter.ViewHolder>(){
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        var title: TextView =itemView.findViewById(R.id.tv_library_title)
        var desc: TextView =itemView.findViewById(R.id.tv_library_desc)

        init {
            itemView.setOnClickListener{
                    v:View -> val position:Int = adapterPosition
                Toast.makeText(itemView.context,"click ${position +1}",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private val title = arrayOf("Daun Jarak", "Lidah Buaya",
        "Daun Kelor")

    private val desc = arrayOf("Daun ini berguna untuk mengobati penyakit lorem ipsum...",
        "Daun ini berguna untuk mengobati penyakit lorem ipsum...", "Daun ini berguna untuk mengobati penyakit lorem ipsum...")



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val v =LayoutInflater.from(parent.context)
           .inflate(R.layout.library_item,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text  =title[position]
        holder.desc.text = desc[position]
    }

    override fun getItemCount(): Int {
       return title.size
    }
}