package com.app.mcu.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.luck.picture.lib.engine.CropFileEngine
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropImageEngine


class ImageFileCropEngine : CropFileEngine{

    override fun onStartCrop(
        fragment: Fragment,
        srcUri: Uri,
        destinationUri: Uri,
        dataSource: ArrayList<String>,
        requestCode: Int
    ) {
        val options: UCrop.Options = buildOptions()
        val uCrop = UCrop.of(srcUri, destinationUri, dataSource)
        uCrop.withOptions(options)
        uCrop.setImageEngine(object : UCropImageEngine {
            override fun loadImage(context: Context, url: String, imageView: ImageView) {
                ImageLoaderUtils().loadGlideEngine(imageView,url,180,180)
            }

            override fun loadImage(
                context: Context,
                url: Uri,
                maxWidth: Int,
                maxHeight: Int,
                call: UCropImageEngine.OnCallbackListener<Bitmap?>?
            ) {
                ImageLoaderUtils().loadImageFileCropEngine(
                    context,url,
                    maxWidth,maxHeight,
                    { resource, _ ->call?.onCall(resource)},
                    { call?.onCall(null)})
            }
        })
        uCrop.start(fragment.requireActivity(), fragment, requestCode)
    }

    private fun buildOptions(): UCrop.Options {
        val options = UCrop.Options().apply {
            setHideBottomControls(false)
            setFreeStyleCropEnabled(true)
            setShowCropFrame(true)// 裁剪框
            setShowCropGrid(true)// 裁剪网格
            setCircleDimmedLayer(false)// 圆形头像模式
            withAspectRatio(1f,1f)// 比例
//            setCropOutputPathDir()// 输出路径

        }
        return options
    }
}