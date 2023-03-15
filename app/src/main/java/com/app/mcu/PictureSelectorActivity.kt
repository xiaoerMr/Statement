package com.app.mcu

import androidx.recyclerview.widget.GridLayoutManager
import com.app.mcu.adapter.PictureSelectAdapter
import com.app.mcu.base.BaseActivity
import com.app.mcu.databinding.ActivityMainBinding
import com.app.mcu.util.pictureselect.PictureSelectorUtils

class PictureSelectorActivity : BaseActivity<ActivityMainBinding>() {

    private val adapter by lazy { PictureSelectAdapter() }

    override fun initView() {
        viewBinding.vTitleBar.apply {
            title = "首页"
        }
        viewBinding.let {
            it.vList.layoutManager = GridLayoutManager(this,4)
            it.vList.adapter = adapter
        }
    }

    override fun initData() {

    }

    override fun initListener() {
        loadingDialogDismiss()

        viewBinding.let {
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