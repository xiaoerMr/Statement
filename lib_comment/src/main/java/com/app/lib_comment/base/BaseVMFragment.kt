package com.app.lib_comment.base

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.app.mcu.ext.saveAs
import java.lang.reflect.ParameterizedType

abstract class BaseVMFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    // 可以使用注入
    val viewModel: VM by lazy {
        val type = javaClass.genericSuperclass
        val modelClass: Class<VM> = type!!.saveAs<ParameterizedType>().actualTypeArguments[1].saveAs()
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(modelClass)
    }


}