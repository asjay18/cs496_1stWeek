package com.example.cs496_1stweek.gallery

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File

class GalleryFileDelete {
    fun main(context: Context, filename: String) {
        val file = File("/data/user/0/com.example.cs496_1stweek/files/" + filename)
        file.delete()
        return
    }
}