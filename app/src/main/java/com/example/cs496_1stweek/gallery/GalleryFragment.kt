package com.example.cs496_1stweek.gallery

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment() {

    private val galleryItemList = mutableListOf<GalleryItem>()
    private val adapter = GalleryAdapter(galleryItemList)

    fun loadImages() : MutableList<GalleryItem>{
        val galleryItemList = mutableListOf<GalleryItem>()
        val imagesIterator = GalleryFileRead().main(requireContext())?.iterator()
        while(imagesIterator!!.hasNext()) {
            val imageuri = Uri.parse("file:///data/user/0/com.example.cs496_1stweek/files/" + imagesIterator.next())
            galleryItemList.add(GalleryItem(imageuri.toString()))
        }
        Log.d("checking", galleryItemList.size.toString())
        return galleryItemList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryView = inflater.inflate(R.layout.gallery_frag, container, false)
        val recycleView: RecyclerView = galleryView.findViewById(R.id.gallery_recycler_view)
        

        val iterator = loadImages().iterator()
        while(iterator.hasNext()) {
            galleryItemList.add(iterator.next())
        }

        recycleView.adapter = adapter

        //gallery addImage fab
        val fab: FloatingActionButton = galleryView.findViewById(R.id.addImage)
        val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
            val imageUri = it
            val newuri = GalleryFileWrite().main(requireContext(), imageUri)
            galleryItemList.add(GalleryItem(newuri.toString()))
            Log.d("checking43", newuri.toString())
            adapter.notifyDataSetChanged()
        }
        fab.setOnClickListener {
            selectPictureLauncher.launch("image/*")
        }
        //loadImages()
        return galleryView
    }

}
