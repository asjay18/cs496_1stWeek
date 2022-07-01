package com.example.cs496_1stweek.gallery

import android.content.Context
import android.net.Uri
import java.io.IOException

class GalleryFileWrite {
    @Throws(IOException::class)
    private fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }

    fun main(context: Context, fileUri : Uri) : String {
        val filename = fileUri.hashCode().toString()
        val fileContents = readBytes(context, fileUri)

        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents)
        }
        return filename
    }

}