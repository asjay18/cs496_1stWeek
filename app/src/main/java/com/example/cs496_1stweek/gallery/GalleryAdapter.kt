package com.example.cs496_1stweek.gallery

import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R
import java.io.File


class GalleryAdapter (
    private var dataset: List<GalleryItem>
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

    var onItemClick : ((GalleryItem) -> Unit)? = null

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pic: ImageView = view.findViewById(R.id.gallery_pic)
        val deleteButton : Button = view.findViewById(R.id.delete_button)
        val uploadDate : TextView = view.findViewById(R.id.upload_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_items, parent, false)
        return GalleryAdapter.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.pic.setImageURI(item.pic.toUri())
        holder.uploadDate.text = item.date
        holder.deleteButton.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}