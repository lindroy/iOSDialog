package com.lindroid.iosdialog.bean

import android.os.Parcelable
import android.support.annotation.ColorInt
import android.view.Gravity
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/5/18
 * @function Dialog中的Text样式
 * @Description
 */
@Parcelize
data class TextParams(
        var textSize: Float,
        @ColorInt var textColor: Int,
        var gravity: Int = Gravity.CENTER,
        var text: String = "",
        var isBold: Boolean = false,
        var height: Int = 0,
        var paddingLeft: Int = 0,
        var paddingTop: Int = 0,
        var paddingRight: Int = 0,
        var paddingBottom: Int = 0
) : Parcelable