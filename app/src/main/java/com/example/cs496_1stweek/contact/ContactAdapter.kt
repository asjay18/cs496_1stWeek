package com.example.cs496_1stweek.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class ContactAdapter (
    private var dataset: List<ContactItem>
) : RecyclerView.Adapter<ContactAdapter.ItemViewHolder>() {

    var onItemClick : ((ContactItem) -> Unit)? = null

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pic: ImageView = view.findViewById(R.id.contact_pic)
        val contactName: TextView = view.findViewById(R.id.contact_name)
        val phoneNum: TextView = view.findViewById(R.id.contact_phoneNum)
        val callbtn: Button = view.findViewById(R.id.calllbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_items, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        if(item.pic != "null") {
            holder.pic.setImageURI(item.pic.toUri())
        }else{
            holder.pic.setImageResource(R.drawable.ic_emoji)
        }
        holder.contactName.text = item.name
        holder.phoneNum.text = item.phoneNum
        holder.callbtn.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}