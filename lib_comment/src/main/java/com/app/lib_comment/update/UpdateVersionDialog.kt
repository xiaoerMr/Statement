package com.app.lib_comment.update

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.app.lib_comment.R
import com.app.lib_comment.ext.e
import com.kongzue.dialogx.dialogs.CustomDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import com.kongzue.dialogx.interfaces.OnBindView

/**
 * 版本升级相关的弹窗
 */

class UpdateVersionDialog {
    private val maskColor by lazy { Color.parseColor("#5D000000") }

    fun showUpdateDialog(updateListener: () -> Unit, closeListener: () -> Unit) {
        CustomDialog.build()
            .setCustomView(object : OnBindView<CustomDialog>(R.layout.dialog_update_version) {
                override fun onBind(dialog: CustomDialog, v: View) {

                    v.findViewById<AppCompatButton>(R.id.vUpdate).setOnClickListener {
                        updateListener.invoke()
                        dialog.dismiss()
                    }
                    v.findViewById<AppCompatButton>(R.id.vClose).setOnClickListener {
                        closeListener.invoke()
                        dialog.dismiss()
                    }

                }
            })
            .setMaskColor(maskColor) // 背景遮罩
            .setAlign(CustomDialog.ALIGN.CENTER)
            .setCancelable(false)
            .show()
    }

    private var oldProgress =-1
    fun showProgressDialog(progress: Int) {
        if (oldProgress != progress) {
            oldProgress = progress
            WaitDialog.show("正在下载 ${progress}%").refreshUI()
            if (progress == 100) {
                WaitDialog.dismiss()
            }
        }

    }
}
