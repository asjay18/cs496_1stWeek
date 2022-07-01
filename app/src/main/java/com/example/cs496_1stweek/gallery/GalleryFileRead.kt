package com.example.cs496_1stweek.gallery

import android.content.Context
import android.util.Log

class GalleryFileRead {
    fun main(context: Context) : Array<out String>? {
        return context.fileList()
        /*context.openFileInput(filename).use { stream ->
            val text = stream.bufferedReader().use {
                it.readText()
            }
            //Log.d("filename3", "LOADED: $text")
            return text.toByteArray()
        }*/
    }
}