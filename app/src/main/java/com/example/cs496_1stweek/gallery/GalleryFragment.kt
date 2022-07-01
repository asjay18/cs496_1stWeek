package com.example.cs496_1stweek.gallery

import android.app.Activity
import android.content.Intent
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

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK) {
            var imageUri = it.data?.data
            Log.d("i got image", imageUri.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val galleryView = inflater.inflate(R.layout.gallery_frag, container, false)

        //gallery recyclerview
        val recycleView: RecyclerView = galleryView.findViewById(R.id.gallery_recycler_view)
        val galleryItemList = mutableListOf<GalleryItem>()
        val adapter = GalleryAdapter(galleryItemList)

        recycleView.adapter = adapter


        //gallery addImage fab
        val fab: FloatingActionButton = galleryView.findViewById(R.id.addImage)

        fab.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startForResult.launch(photoPickerIntent)
        }
        //context?.let { GalleryFileWrite().main(it, ) }

        return galleryView
    }
}
