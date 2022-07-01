package com.example.cs496_1stweek.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class GalleryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryView = inflater.inflate(R.layout.gallery_frag, container, false)
        val recycleView: RecyclerView = galleryView.findViewById(R.id.gallery_recycler_view)

        val galleryItemList = mutableListOf<GalleryItem>()
        val adapter = GalleryAdapter(galleryItemList)

        recycleView.adapter = adapter
        //context?.let { GalleryFileWrite().main(it, ) }
        return galleryView
        //return inflater.inflate(R.layout.gallery_frag, container, false)
    }
}
