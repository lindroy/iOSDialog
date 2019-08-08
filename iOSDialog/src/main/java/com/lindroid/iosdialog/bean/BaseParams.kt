package com.lindroid.iosdialog.bean

import android.content.DialogInterface
import android.os.Parcelable
import android.support.annotation.FloatRange
import android.support.annotation.LayoutRes
import android.view.Gravity
import android.view.View
import com.lindroid.iosdialog.viewholder.ViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/2
 * @function BaseDialog参数
 * @Description
 */
@Parcelize
data class BaseParams(
    @LayoutRes
    var dialogViewId: Int = 0,
    var dialogTag: String = "AndDialog",
    var animStyle: Int = android.R.style.Animation_Dialog,
    var widthScale: Float = 0.8F,
    var heightScale: Float = 0.0F,
    @FloatRange(from = 0.0, to = 1.0)
    var dimAmount: Float = 0.6F,
    var gravity: Int = Gravity.CENTER,
    var cancelableOutside: Boolean = true
) : Parcelable, UnParcelableParams()

abstract class UnParcelableParams(
    var dialogView: View? = null,
    var viewHandler: ((ViewHolder, DialogInterface) -> Unit)? = null
)