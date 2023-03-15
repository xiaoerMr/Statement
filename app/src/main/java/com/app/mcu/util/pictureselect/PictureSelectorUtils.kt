package com.app.mcu.util.pictureselect

import android.content.Context
import com.app.mcu.ext.d
import com.app.mcu.util.SystemInfo
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import java.util.ArrayList

/**
 * 图片选择框架
 * 拍照、图库、系统图库
 */
class PictureSelectorUtils {

//    getPath(); 指从MediaStore查询返回的路径；SDK_INT >=29 返回content://类型；其他情况返回绝对路径。
//    getRealPath(); 绝对路径；SDK_INT >=29且处于沙盒环境下直接使用会报FileNotFoundException异常；
//    getOriginalPath(); 原图路径；isOriginalImageControl(true);
//        且勾选了原图选项时返回；但SDK_INT >=29且未实现.setSandboxFileEngine();
//        直接使用会报FileNotFoundException异常；
//    getAvailablePath(); SDK_INT为任意版本都返回直接可用地址(但SDK_INT >29且未开启压缩、
//        裁剪或未实现setSandboxFileEngine，请参考getPath())，
//        但如果你需要具体业务场景下的地址，请参考如上几种路径；
//    getCompressPath(); 压缩路径；实现了setCompressEngine();时返回；
//    getCutPath(); 裁剪或编辑路径；实现了setCropEngine();或setEditMediaInterceptListener();时返回；
//    getSandboxPath(); SDK_INT >=29且实现了.setSandboxFileEngine();返回；
//
//    getVideoThumbnailPath(); 视频缩略图，需要实现setVideoThumbnailListener接口
//    getWatermarkPath(); 水印地址，需要实现setAddBitmapWatermarkListener接口



    fun selectCamera(
        context: Context,
        isCut: Boolean = false,
        isZip: Boolean = false,
        resultListener: (MutableList<LocalMedia>) -> Unit,
        cancelListener: () -> Unit = {}
    ) {
        PictureSelector.create(context)
            .openCamera(SelectMimeType.ofImage())
            .apply {
                if (isCut) setCropEngine(ImageFileCropEngine())//相册裁剪引擎
                if (isZip) setCompressEngine(ImageFileCompressEngine()) // 压缩
            }
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>?) {
                    setResultHandle(result, cancelListener, resultListener)
                }

                override fun onCancel() {
                    cancelListener.invoke()
                }
            })
    }

    fun selectGallery(
        context: Context,
        isSingeSelection: Boolean = true,
        isCut: Boolean = false,
        isZip: Boolean = false,
        isSandBox: Boolean = false,
        resultListener: (MutableList<LocalMedia>) -> Unit,
        cancelListener: () -> Unit = {}
    ) {
        PictureSelector.create(context)
            .openGallery(SelectMimeType.ofImage())
            .setImageEngine(GlideEngine())
            .apply {
                if (isSingeSelection) setSelectionMode(SelectModeConfig.SINGLE)//单选
                if (isCut) setCropEngine(ImageFileCropEngine())//相册裁剪引擎
                if (isZip) setCompressEngine(ImageFileCompressEngine()) // 压缩
                if (isSandBox) setSandboxFileEngine(MeSandboxFileEngine()) // 沙盒
            }.forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>?) {
                    setResultHandle(result, cancelListener, resultListener)
                }

                override fun onCancel() {
                    cancelListener.invoke()
                }
            })
    }

    fun selectGallerySystem(
        context: Context,
        isSingeSelection: Boolean = true,
        isCrop: Boolean = false,
        isZip: Boolean = false,
        isSandBox: Boolean = false,
        resultListener: (MutableList<LocalMedia>) -> Unit,
        cancelListener: () -> Unit = {}
    ) {
        PictureSelector.create(context)
            .openSystemGallery(SelectMimeType.ofImage())
            .apply {
                if (isSingeSelection) setSelectionMode(SelectModeConfig.SINGLE)//单选
                if (isCrop) setCropEngine(ImageFileCropEngine())//相册裁剪引擎
                if (isZip) setCompressEngine(ImageFileCompressEngine()) // 压缩
                if (isSandBox) setSandboxFileEngine(MeSandboxFileEngine()) // 沙盒
            }.forSystemResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>?) {
                    setResultHandle(result, cancelListener, resultListener)
                }

                override fun onCancel() {
                    cancelListener.invoke()
                }
            })
    }


    /**
     * 处理结果
     */
    private fun setResultHandle(
        result: ArrayList<LocalMedia>?,
        cancelListener: () -> Unit,
        resultListener: (MutableList<LocalMedia>) -> Unit
    ) {
        if (result == null || result.size < 1) {
            cancelListener.invoke()
        } else {
            resultListener.invoke(result)
//            loge(result)
        }
    }

    private fun loge(result: ArrayList<LocalMedia>) {
        result.forEach {
            d(this.javaClass.name, "-------> SDK : ${SystemInfo().getSDK()}")
            d(this.javaClass.name, "path = ${it.path}")
            d(this.javaClass.name, "available = ${it.availablePath}")
            d(this.javaClass.name, "real = ${it.realPath}")
        }
    }
}