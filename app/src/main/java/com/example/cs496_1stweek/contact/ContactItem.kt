package com.example.cs496_1stweek.contact

import com.example.cs496_1stweek.R

class ContactItem {
    fun loadRecycleview(): List<RecycleView> {
        return listOf<RecycleView>(
            RecycleView(R.string.recycleview1),
            RecycleView(R.string.recycleview2),
            RecycleView(R.string.recycleview3),
            RecycleView(R.string.recycleview4),
            RecycleView(R.string.recycleview5),
            RecycleView(R.string.recycleview6)
        )
    }

}

data class RecycleView(val stringResourceID: Int)