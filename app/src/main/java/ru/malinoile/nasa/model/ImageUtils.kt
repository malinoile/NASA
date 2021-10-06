package ru.malinoile.nasa.model

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtils {
    companion object {
        fun renderImage(context: Context, url: String?, imageView: ImageView) {
            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    }
}