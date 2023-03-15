package com.app.mcu.base

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.app.mcu.ext.saveAs
import java.lang.reflect.ParameterizedType

abstract class BaseVMActivity<VB : ViewBinding, VM : BaseViewModel> : BaseActivity<VB>() {

    val viewModel: VM by lazy {
        val type = javaClass.genericSuperclass
        val vmClass: Class<VM> = type!!.saveAs<ParameterizedType>().actualTypeArguments[1].saveAs()
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(vmClass)
    }



}