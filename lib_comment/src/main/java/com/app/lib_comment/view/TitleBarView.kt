package com.app.lib_comment.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.lib_comment.databinding.ViewTitleBarBinding
import com.app.lib_comment.R
import com.app.mcu.ext.getActivity
import com.app.mcu.ext.toVisibility

class TitleBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), View.OnClickListener {

    private var viewBinding= ViewTitleBarBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onClick(v: View?) {
        viewBinding.run {
            when(v?.id){
                barBack.id -> context.getActivity()?.finish()
                else ->{}
            }
        }
    }

    init {
        // 读取配置
        val array = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView)
        val title = array.getString(R.styleable.TitleBarView_bar_title) ?: ""
        val showBack = array.getBoolean(R.styleable.TitleBarView_bar_show_back, false)

        viewBinding.run {
            barBack.setOnClickListener(this@TitleBarView)
            barTitle.text = title
            barBack.toVisibility(showBack)
        }
        array.recycle()
    }



    fun setShowBack(showBack: Boolean): TitleBarView {
        viewBinding.run {
            barBack.toVisibility(showBack)
        }
        return this
    }


    fun setTitle(title: String): TitleBarView {
        viewBinding.run {
            barTitle.text = title
        }
        return this
    }

}