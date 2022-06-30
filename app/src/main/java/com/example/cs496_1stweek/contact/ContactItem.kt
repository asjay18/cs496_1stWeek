package com.example.cs496_1stweek.contact

import android.media.Image
import com.example.cs496_1stweek.R

class ContactItem {
    fun loadRecycleview(): List<RecycleView> {
        return listOf<RecycleView>(
            RecycleView(0,"aaa","111"),
            RecycleView(0,"bbb","222"),
            RecycleView(0,"ccc","333"),
            RecycleView(0,"ddd","444"),
            RecycleView(0,"eee","555"),
            RecycleView(0,"fff","666")
        )
    }

}

data class RecycleView (var pic: Int, var name: String, var phoneNum: String)