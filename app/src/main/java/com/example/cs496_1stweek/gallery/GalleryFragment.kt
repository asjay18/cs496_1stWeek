package com.example.cs496_1stweek.gallery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class GalleryFragment : Fragment() {
    lateinit var bufferString:String

    fun getImagesFromJson(){
        try{
            val jsonString = this.requireContext().assets.open("data.json").reader().readText()
            val jsonArray = JSONArray(jsonString)
            Log.d("print2",jsonArray.toString())

        }catch(e: IOException){
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val galleryView = inflater.inflate(R.layout.gallery_frag, container, false)
        val imageView : ImageView = galleryView.findViewById(R.id.image_view1)

        //gallery recyclerview
        val recycleView: RecyclerView = galleryView.findViewById(R.id.gallery_recycler_view)
        val galleryItemList = mutableListOf<GalleryItem>()
        val adapter = GalleryAdapter(galleryItemList)
        recycleView.adapter = adapter

        //gallery addImage fab
        val fab: FloatingActionButton = galleryView.findViewById(R.id.addImage)

        val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
            var imageUri = it
            imageView.setImageURI(it)
        }

        fab.setOnClickListener {
            selectPictureLauncher.launch("image/*")
        }

        getImagesFromJson()

        return galleryView
    }
}
