package com.app.mcu.util


class CoilEngine{//: ImageEngine {
//    override fun loadImage(context: Context, url: String, imageView: ImageView) {
//        if (!ActivityCompatHelper.assertValidRequest(context))return
//
//        val target = ImageRequest.Builder(context).data(url).target(imageView).build()
//        context.imageLoader.equals(target)
//    }
//
//    override fun loadImage(
//        context: Context?,
//        imageView: ImageView?,
//        url: String?,
//        maxWidth: Int,
//        maxHeight: Int
//    ) {
//        if (!ActivityCompatHelper.assertValidRequest(context))return
//
//        context?.let {
//            val builder = ImageRequest.Builder(it)
//            if (maxWidth >0 && maxHeight>0){
//                builder.size(maxWidth,maxHeight)
//            }
//            imageView?.let { v-> builder.data(url).target(v) }
//            val request = builder.build()
//            it.imageLoader.enqueue(request)
//        }
//    }
//
//    override fun loadAlbumCover(context: Context, url: String, imageView: ImageView) {
//        if (!ActivityCompatHelper.assertValidRequest(context))return
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        val target = ImageRequest.Builder(context)
//            .data(url)
//            .transformations(RoundedCornersTransformation(8F))
//            .size(180,180)
//            .placeholder(com.luck.picture.lib.R.drawable.ps_image_placeholder)
//            .target(imageView)
//            .build()
//
//        context.imageLoader.enqueue(target)
//    }
//
//    override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
//        if (!ActivityCompatHelper.assertValidRequest(context))return
//
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        val target = ImageRequest.Builder(context)
//            .data(url)
//            .size(270, 270)
//            .placeholder(com.luck.picture.lib.R.drawable.ps_image_placeholder)
//            .target(imageView)
//            .build()
//        context.imageLoader.enqueue(target)
//    }
//
//    override fun pauseRequests(context: Context?) {
//    }
//
//    override fun resumeRequests(context: Context?) {
//    }
}