package com.app.lib_comment.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


class ImageLoaderUtils {
    private val icon = com.luck.picture.lib.R.drawable.ps_image_placeholder

    fun load(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .placeholder(icon)
            .error(icon)
            .into(view)
    }


    //----------------  专用 -------------------
    fun loadGlideEngine(
        view: ImageView,
        url: String,
        overrideWidth: Int,
        overrideHeight: Int,
    ) {
        Glide.with(view.context)
            .load(url)
            .override(overrideWidth, overrideHeight)
            .into(view)
    }

    fun loadGlideEngineAlbumCover(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .asBitmap()
            .load(url)
            .override(180, 180)
            .sizeMultiplier(0.5f)
            .transform(CenterCrop(), RoundedCorners(8))
            .placeholder(icon)
            .into(imageView)
    }

    fun loadGlideEngineGridImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .override(200, 200)
            .centerCrop()
            .placeholder(icon)
            .into(imageView)
    }

    // ImageFileCropEngine 专用
    fun loadImageFileCropEngine(
        context: Context, url: Uri,
        overrideWidth: Int = 0,
        overrideHeight: Int = 0,
        resourceReady: (resource: Bitmap, transition: Transition<in Bitmap>?) -> Unit,
        loadCleared: (placeholder: Drawable?) -> Unit
    ) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .override(overrideWidth, overrideHeight).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    resourceReady.invoke(resource, transition)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    loadCleared.invoke(placeholder)
                }

            })
    }
}