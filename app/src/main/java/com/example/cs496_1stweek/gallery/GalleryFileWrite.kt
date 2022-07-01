package com.example.cs496_1stweek.gallery

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.*


class GalleryFileWrite {

    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun convertUriToPath(context: Context, contentUri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        return context.contentResolver.query(contentUri!!, projection, null, null, null)
            ?.use { cursor ->
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                cursor.getString(columnIndex)
            }
    }

    fun main(context: Context, uri : Uri) : String {
        //val filename = uri.hashCode().toString()
        /*val filepath = convertUriToPath(context, uri)
        if (filepath != null) {
            Log.d("filename3", filepath)
        }*/

        val file : String = uri.hashCode().toString() + getRandomString(5)

        val outputStream : FileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
        val inputStream = context.contentResolver.openInputStream(uri)!!

        val bytes = inputStream.readBytes()
        outputStream.write(bytes)

        //Log.d("filename4", String(bytes, StandardCharsets.UTF_8))

        inputStream.close()
        outputStream.close()

        val uri = Uri.fromFile(context.getFileStreamPath(file))

        Log.d("filename5", uri.toString())
        return file
    }
}