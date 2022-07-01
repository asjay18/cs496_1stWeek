package com.example.cs496_1stweek.gallery

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File

class GalleryFileDelete {
    fun main(filename: String) {
        val file = File(filename)
        file.delete()
        return
    }
}