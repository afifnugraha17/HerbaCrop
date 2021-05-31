package com.ateam.herbacrop.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ateam.herbacrop.R

class RecylerTrendingAdapter :RecyclerView.Adapter<RecylerTrendingAdapter.ViewHolder>(){
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        var title: TextView =itemView.findViewById(R.id.title_Tranding)
//        var desc: TextView =itemView.findViewById(R.id.desc)

        init {
            itemView.setOnClickListener{
                    v:View -> val position:Int = adapterPosition
                Toast.makeText(itemView.context,"click ${position +1}",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private val title = arrayOf("DBD", "COVID",
        "kaki Gajah", "Malaria",
        "TBC", "Sakit mata",
        "Sakit Hati", "Sakit Ginjal","Darah Suci","Darah Tinggi","Buta Warna","Flu Burung")

    private val desc = arrayOf("Penyakit Berbahaya lorem ipsum...",
        "Penyakit Berbahaya lorem ipsum...", "Penyakit Berbahaya lorem ipsum...", "Penyakit Berbahaya lorem ipsum...",
        "Penyakit Berbahaya lorem ipsum...", "Penyakit Berbahaya lorem ipsum...", "Penyakit Berbahaya lorem ipsum...",
        "Penyakit Berbahaya lorem ipsum...","Penyakit Berbahaya lorem ipsum...","Penyakit Berbahaya lorem ipsum...","Penyakit Berbahaya lorem ipsum...")



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val v =LayoutInflater.from(parent.context)
           .inflate(R.layout.trending_beranda_item,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text  =title[position]
//        holder.desc.text = desc[position]
    }

    override fun getItemCount(): Int {
       return title.size
    }
}