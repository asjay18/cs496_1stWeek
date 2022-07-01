package com.example.cs496_1stweek.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class GalleryAdapter (
    private var dataset: List<GalleryItem>
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pic: ImageView = view.findViewById(R.id.gallery_pic)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_items, parent, false)
        return GalleryAdapter.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.pic.setImageResource(R.drawable.ic_emoji)
        /*if(item.pic != "null") {
            holder.pic.setImageURI(item.pic.toUri())
        }else{
            holder.pic.setImageResource(R.drawable.ic_emoji)
        }*/
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}