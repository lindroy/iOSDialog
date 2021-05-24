package com.lindroy.iosdialog.bean

import android.content.DialogInterface
import android.os.Parcelable
import androidx.annotation.FloatRange
import androidx.annotation.LayoutRes
import android.view.Gravity
import android.view.View
import com.lindroy.iosdialog.viewholder.ViewHolder
import kotlinx.android.parcel.Parcelize

/**
 * @author Lin
 * @date 2019/8/2
 * @function BaseDialog参数
 * @Description
 */
@Parcelize
open class BaseParams(
   /* @LayoutRes
    var layoutId: Int = 0,
    var tag: String = "AndDialog",
    var animStyle: Int = android.R.style.Animation_Dialog,
    var widthScale: Float = 0.8F,
    var heightScale: Float = 0.0F,
    @FloatRange(from = 0.0, to = 1.0)
    var dimAmount: Float = 0.6F,
    var gravity: Int = Gravity.CENTER,
    var dismissible:Boolean = true,
    var cancelableOutside: Boolean = true*/
) : Parcelable, UnParcelableParams(){
    val comParams = ComParams().copy()
}


abstract class UnParcelableParams(
    var layout: View? = null,
    var viewHandler: ((ViewHolder, DialogInterface) -> Unit)? = null
)

data class ComParams(
    @LayoutRes
    var layoutId: Int = 0,
    var tag: String = "AndDialog",
    var animStyle: Int = android.R.style.Animation_Dialog,
    var widthScale: Float = 0.8F,
    var heightScale: Float = 0.0F,
    @FloatRange(from = 0.0, to = 1.0)
    var dimAmount: Float = 0.6F,
    var gravity: Int = Gravity.CENTER,
    var dismissible:Boolean = true,
    var cancelableOutside: Boolean = true
)