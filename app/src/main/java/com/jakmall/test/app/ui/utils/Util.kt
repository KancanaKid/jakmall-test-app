package com.jakmall.test.app.ui.utils

import android.content.res.Resources

/**
 * @author basyi
 * Created 3/3/2023 at 3:20 PM
 * Purpose to provide utilities functions
 */
object Util {
    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}