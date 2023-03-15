package com.app.mcu.ext

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity


/**
 * Created by qisan 2022/6/20
 * com.qisan.baselib.ext
 */

inline fun <reified T> Any.saveAs() : T{
    return this as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.saveAsUnChecked() : T{
    return this as T
}

inline fun <reified T> Any.isEqualType() : Boolean{
    return this is T
}

/**
 * Boolean转Visibility
 */
fun Boolean.toVisibility() = if (this) View.VISIBLE else View.GONE
fun View.toVisibility(show:Boolean) {
    if (this.isVisible != show) {
        this.isVisible = show
    }
}

/**
 * Context转Activity
 */
fun Context.getActivity(): FragmentActivity? {
    return when (this) {
        is FragmentActivity -> {
            this
        }
        is ContextWrapper -> {
            this.baseContext.getActivity()
        }
        else -> null
    }
}

