package com.app.mcu

import androidx.recyclerview.widget.GridLayoutManager
import com.app.lib_comment.base.BaseActivity
import com.app.mcu.adapter.PictureSelectAdapter
import com.app.mcu.databinding.ActivityPictureSelectorBinding
import com.app.lib_comment.util.pictureselect.PictureSelectorUtils

class PictureSelectorActivity : BaseActivity<ActivityPictureSelectorBinding>() {

    private val adapter by lazy { PictureSelectAdapter() }

    override fun initView() {
        mViewBinding.vTitleBar.apply {
            title = "图片选择框架"
        }
        mViewBinding.let {
            it.vList.layoutManager = GridLayoutManager(this,4)
            it.vList.adapter = adapter
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        loadingDialogDismiss()

        mViewBinding.let {
            it.vSelectCamera.setOnClickListener {
                PictureSelectorUtils().selectCamera(
                    context = this,
                    resultListener = { res -> adapter.addAll(res) }
                )
            }

            it.vSelectGallery.setOnClickListener {
                PictureSelectorUtils().selectGallery(
                    context = this,
                    resultListener = { res -> adapter.addAll(res) },
                )
            }

            it.vSelectGallerySys.setOnClickListener {
                PictureSelectorUtils().selectGallerySystem(
                    context = this,
                    resultListener = { res -> adapter.addAll(res) }
                )
            }
        }
    }

    override fun showLoadingDialog() {
        loadingDialogShow()
    }

}