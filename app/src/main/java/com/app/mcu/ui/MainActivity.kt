package com.app.mcu.ui

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.app.lib_comment.base.BaseVMActivity
import com.app.lib_comment.update.UpdateManager
import com.app.mcu.PictureSelectorActivity
import com.app.mcu.databinding.ActivityMainBinding
import com.app.mcu.ui.vm.MainViewModel

class MainActivity : BaseVMActivity<ActivityMainBinding, MainViewModel>() {

    override fun initView() {

    }

    override fun initData() {
        lifecycleScope.launchWhenResumed {
            UpdateManager(this@MainActivity).checkVersion()
        }
    }

    override fun initListener() {
        mViewBinding.vGoHttp.setOnClickListener {
            mViewModel.getNewList()
        }

        mViewBinding.vGoSelectPhone.setOnClickListener {
            startActivity(Intent(this,PictureSelectorActivity::class.java))
        }
    }

}