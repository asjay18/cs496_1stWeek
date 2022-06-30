package com.example.cs496_1stweek.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_1stweek.R

class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contact_view = inflater.inflate(R.layout.contact_frag, container, false)
        val recycleView: RecyclerView = contact_view.findViewById(R.id.contact_recycler_view)
        val myDataset = ContactItem().loadRecycleview()

        recycleView.adapter = ContactAdapter(this, myDataset)
        return contact_view
    }
}

