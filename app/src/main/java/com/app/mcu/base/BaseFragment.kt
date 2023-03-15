package com.app.mcu.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.mcu.R
import com.app.mcu.ext.saveAs
import com.app.mcu.ext.saveAsUnChecked
import com.kongzue.dialogx.dialogs.WaitDialog
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment(){
    /**
     * 是否初始化过布局
     */
    private var isViewInitiated: Boolean = false
    /**
     * 是否加载过数据
     */
    private var isDataInitiated: Boolean = false

    protected val viewBinding: VB
        get() {
            return _viewBinding
        }

    private val _viewBinding: VB by lazy {
        val type = javaClass.genericSuperclass
        val vbClass: Class<VB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(this, layoutInflater)!!.saveAsUnChecked()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isViewInitiated = true
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog()

        initView()
    }

    open fun showLoadingDialog() {

    }


    //通过在onResume中实现懒加载
    override fun onResume() {
        super.onResume()
        if(!isDataInitiated && isViewInitiated){
            initData()
            initListener()
            isDataInitiated = true
        }
    }
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()

    override fun onDestroyView() {
        super.onDestroyView()
        isDataInitiated = false
        isViewInitiated = false
    }

    fun loadingDialogShow(txt: String = getString(R.string.loading)){
        WaitDialog.show(txt)
    }

    fun loadingDialogDismiss(){
        WaitDialog.dismiss()
    }
}