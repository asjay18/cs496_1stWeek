package com.example.cs496_1stweek.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496_1stweek.R


class GalleryAdapter (
    private val context : Context,
    private var dataset: List<GalleryItem>
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

    var onItemClick : ((GalleryItem) -> Unit)? = null

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pic: ImageView = view.findViewById(R.id.gallery_pic)
        val deleteButton : Button = view.findViewById(R.id.delete_button)
        val uploadDate : TextView = view.findViewById(R.id.upload_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_items, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        Glide.with(context)
            .load(item.pic.toUri())
            .override(2000, 2000)
            .fitCenter()
            .into(holder.pic)
            //.transform( RoundedTransformation(30, 0))
            //.placeholder(context.resources.getDrawable(R.drawable.ic_launcher_foreground))//it will show placeholder image when url is not valid.
            //.networkPolicy(NetworkPolicy.OFFLINE) //for caching the image url in case phone is offline

        holder.uploadDate.text = item.date
        holder.deleteButton.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}