package com.example.cs496_1stweek.gallery

import android.content.Context
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cs496_1stweek.R
import java.io.File


class GalleryAdapter (
    private val context : Context,
    private var dataset: List<GalleryItem>
    ) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

    var onItemClick : ((GalleryItem) -> Unit)? = null

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val pic: ImageView = view.findViewById(R.id.gallery_pic)
        val delete_button : Button = view.findViewById(R.id.delete_button)
    }

    fun getCameraPhotoOrientation(
        //context: Context,
        imageUri: Uri?,
    ): Int {
        val imagePath :String = imageUri.toString().substring(7)
        var rotate = 0
        try {
            //context.getContentResolver().notifyChange(imageUri, null)
            val imageFile = File(imagePath)
            val exif = ExifInterface(imageFile.getAbsolutePath())
            val orientation: Int = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
            Log.i("RotateImage", "Exif orientation: $orientation")
            Log.i("RotateImage90", (orientation == ExifInterface.ORIENTATION_ROTATE_90).toString())
            Log.i("RotateImage180", (orientation == ExifInterface.ORIENTATION_ROTATE_180).toString())
            Log.i("RotateImage270", (orientation == ExifInterface.ORIENTATION_ROTATE_270).toString())
            Log.i("RotateImage", "Rotate value: $rotate")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rotate
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.gallery_items, parent, false)
        return GalleryAdapter.ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        val rotateImage : Int = getCameraPhotoOrientation(item.pic.toUri()) //ViewGroup.context,
        Log.i("RotateImageResult", "Rotate value: $rotateImage")


        Glide.with(context)
            .load(item.pic.toUri())
            .override(2000, 2000)
            .fitCenter()
            .into(holder.pic)

            //.transform( RoundedTransformation(30, 0))
            //.placeholder(context.resources.getDrawable(R.drawable.ic_launcher_foreground))//it will show placeholder image when url is not valid.
            //.networkPolicy(NetworkPolicy.OFFLINE) //for caching the image url in case phone is offline

        //holder.pic.setImageURI(item.pic.toUri())
        //holder.pic.setRotation(rotateImage.toFloat())
        holder.delete_button.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}