package com.example.cs496_1stweek.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment() {

    fun loadImages() : MutableList<GalleryItem>{
        val galleryItemList = mutableListOf<GalleryItem>()
        val imagesIterator = GalleryFileRead().main(requireContext())?.iterator()
        while(imagesIterator!!.hasNext()) {
            val checkCustomString = imagesIterator.next()
            if(checkCustomString.length > 15) {
                val imageuri = "file:///data/user/0/com.example.cs496_1stweek/files/" + checkCustomString
                val date = imageuri.takeLast(10)
                val generateDate = "20"+date[0]+date[1]+"."+date[2]+date[3]+"."+date[4]+date[5]+" "+date[6]+date[7]+":"+date[8]+date[9]
                galleryItemList.add(0,GalleryItem(imageuri, generateDate))
            }
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
                val date = newuri.takeLast(10)
                val generateDate = "20"+date[0]+date[1]+"."+date[2]+date[3]+"."+date[4]+date[5]+" "+date[6]+date[7]+":"+date[8]+date[9]
                galleryItemList.add(0, GalleryItem(newuri, generateDate))
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
        Log.d("checking", galleryItemList.size.toString())
        //loadImages()
        return galleryView
    }

}
