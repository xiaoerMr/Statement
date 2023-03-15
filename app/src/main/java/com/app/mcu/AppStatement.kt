package com.app.mcu

import android.app.Application
import com.kongzue.dialogx.DialogX

class AppStatement : Application() {

    override fun onCreate() {
        super.onCreate()

        DialogX.init(this)
        DialogX.globalTheme = DialogX.THEME.DARK
    }
}