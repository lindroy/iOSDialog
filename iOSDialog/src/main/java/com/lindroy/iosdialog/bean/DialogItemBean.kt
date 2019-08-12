package com.lindroy.iosdialog.bean

import android.support.annotation.ColorInt

/**
 * @author Lin
 * @date 2019/5/18
 * @function 含有列表对话框的item实体类
 * @Description
 */

data class DialogItemBean(
        val text: String,
        @ColorInt val textColor: Int,
        val textSize: Float = 0F

)

