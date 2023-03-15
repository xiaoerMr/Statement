package com.app.mcu.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.app.mcu.R
import com.app.mcu.ext.saveAs
import com.app.mcu.ext.saveAsUnChecked
import com.gyf.immersionbar.ktx.immersionBar
import com.kongzue.dialogx.dialogs.WaitDialog
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(){

    val TAG by lazy { this.localClassName }

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog()
        initSystemBar()

        setContentView(viewBinding.root)

        initView()
        initData()
        initListener()
    }


    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()


    /**
     * 如果需要，子类须重写该方法
     */
    open fun showLoadingDialog() {

    }
    fun loadingDialogShow(txt: String = getString(R.string.loading)){
        WaitDialog.show(txt)
    }

    fun loadingDialogDismiss(){
        WaitDialog.dismiss()
    }


    /**
     * 状态栏导航栏初始化
     */
    fun initSystemBar() {
        immersionBar {
            transparentStatusBar()
            statusBarDarkFont(true)
            navigationBarColor(R.color.color_white)
            navigationBarDarkIcon(true)
        }
    }
}