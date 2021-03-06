package com.lindroy.iosdialog.util

import android.content.Context
import android.graphics.Point
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import android.view.WindowManager
import com.lindroy.iosdialog.IDialog
import com.lindroy.iosdialog.IDialog.context

/**
 * @author Lin
 * @date 2019/5/18
 * @function 工具类
 * @Description
 */

internal fun dp2px(dpValue: Float): Float {
    val scale = IDialog.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}

internal fun px2sp(pxValue: Float): Float {
    val fontScale = IDialog.context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F)
}

internal fun getSpSize(dimenId: Int) = px2sp(IDialog.context.resources.getDimensionPixelSize(dimenId).toFloat())

internal fun getPxSize(dimenId: Int) = IDialog.context.resources.getDimensionPixelSize(dimenId)

internal fun getResColor(@ColorRes colorId: Int) = ContextCompat.getColor(IDialog.context, colorId)

internal fun getResDrawable(@DrawableRes resId:Int) = ContextCompat.getDrawable(context,resId)

internal fun getResString(@StringRes stringId: Int) = IDialog.context.getString(stringId)

internal val screenWidth: Int
    get() {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            true -> wm.defaultDisplay.getRealSize(point)
            false -> wm.defaultDisplay.getSize(point)
        }
        return point.x
    }

