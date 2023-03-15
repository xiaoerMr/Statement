package com.app.mcu.util.pictureselect

import android.content.Context
import android.widget.ImageView
import com.app.mcu.util.ImageLoaderUtils
import com.luck.picture.lib.engine.ImageEngine

class GlideEngine : ImageEngine {

    override fun loadImage(context: Context, url: String, imageView: ImageView) {
        ImageLoaderUtils().load(imageView,url)
    }

    override fun loadImage(
        context: Context,
        imageView: ImageView,
        url: String,
        maxWidth: Int,
        maxHeight: Int
    ) {
        ImageLoaderUtils().loadGlideEngine(imageView,url,maxWidth,maxHeight)
    }

    override fun loadAlbumCover(context: Context, url: String, imageView: ImageView) {
        ImageLoaderUtils().loadGlideEngineAlbumCover(imageView,url)
    }

    override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
        ImageLoaderUtils().loadGlideEngineGridImage(imageView,url)
    }

    override fun pauseRequests(context: Context?) {
    }

    override fun resumeRequests(context: Context?) {
    }
}