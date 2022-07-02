package com.example.cs496_1stweek.gallery

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.Target
import com.example.cs496_1stweek.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment() {

    fun loadImages() : MutableList<GalleryItem>{
        val galleryItemList = mutableListOf<GalleryItem>()
        val imagesIterator = GalleryFileRead().main(requireContext())?.iterator()
        while(imagesIterator!!.hasNext()) {
            val imageuri = "file:///data/user/0/com.example.cs496_1stweek/files/" + imagesIterator.next()
            Log.d("check", imageuri.takeLast(10))
            galleryItemList.add(0, GalleryItem(imageuri))
        }
        return galleryItemList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryItemList = mutableListOf<GalleryItem>()
        val adapter = GalleryAdapter(requireContext(), galleryItemList)
        val galleryView = inflater.inflate(R.layout.gallery_frag, container, false)
        val recycleView: RecyclerView = galleryView.findViewById(R.id.gallery_recycler_view)
        recycleView.layoutManager = LinearLayoutManager(context)

        val iterator = loadImages().iterator()
        while(iterator.hasNext()) {
            galleryItemList.add(iterator.next())
        }

        recycleView.adapter = adapter
        //GalleryFileDelete().main(requireContext(), filename)

        //gallery addImage fab
        val fab: FloatingActionButton = galleryView.findViewById(R.id.addImage)
        val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
            if(it != null) {
                val imageUri = it
                val newuri = GalleryFileWrite().main(requireContext(), imageUri).toString()
                galleryItemList.add(0, GalleryItem(newuri))
                adapter.notifyDataSetChanged()
            }
        }
        fab.setOnClickListener {
            selectPictureLauncher.launch("image/*")
        }
        adapter.onItemClick = {
            GalleryFileDelete().main(it.pic.substring(7, ))
            galleryItemList.remove(it)
            adapter.notifyDataSetChanged()


        }

        //loadImages()
        return galleryView
    }

}
