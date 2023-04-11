package com.app.mcu.ui

import com.app.lib_comment.base.BaseVMActivity
import com.app.mcu.databinding.ActivityMainBinding
import com.app.mcu.ui.vm.MainViewModel

class MainActivity : BaseVMActivity<ActivityMainBinding, MainViewModel>() {

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        mViewBinding.vGoHttp.setOnClickListener {
            mViewModel.getNewList()
        }
    }

}