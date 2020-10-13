package com.example.media.app.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.media.app.R

class ImageUtil {

    fun load(
        context: Context?,
        url: String,
        targetView: ImageView
    ) {
        context?.let {
            val imageBuilder = Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.drawable.ic_sample_placeholder)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.ic_image_error)
            imageBuilder.into(targetView)
        }
    }
}