package com.app.mcu

import androidx.recyclerview.widget.GridLayoutManager
import com.app.mcu.adapter.PictureSelectAdapter
import com.app.mcu.base.BaseActivity
import com.app.mcu.databinding.ActivityMainBinding

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
            it.vSelectPhone.setOnClickListener {


            }

            it.vSelectGallery.setOnClickListener {
    //                d(TAG,"file= "+ FileUtils().getRootFilePath(this))
    //                d(TAG,"file= "+ FileUtils().getRootFilePathDocuments())

            }

            it.vSelectGallerySys.setOnClickListener {

            }
        }
    }

    override fun showLoadingDialog() {
        loadingDialogShow()
    }

}