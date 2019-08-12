@file:Suppress("UNCHECKED_CAST")

package com.lindroy.iosdialog.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.FloatRange
import android.support.annotation.LayoutRes
import android.support.annotation.Px
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*
import com.lindroy.iosdialog.util.dp2px
import com.lindroy.iosdialog.util.screenWidth
import com.lindroy.iosdialog.viewholder.ViewHolder

/**
 * @author Lin
 * @date 2019/7/16
 * @function 对话框基类
 * @Description
 */
abstract class BaseDialog<T : BaseDialog<T>> : DialogFragment() {

    private var dialogTag = "iOSDialog"
    protected lateinit var mContext: Context
    /**
     * 自定义对话框布局Id
     */
    @LayoutRes
    protected var customViewId: Int = 0
    private var viewHandler: ((ViewHolder, DialogInterface) -> Unit)? = null
    private var dismissListener: ((dialog:DialogInterface?) -> Unit)? = null
    protected var dialogLayout: View? = null
    private var animStyle = 0
    private var widthScale = 0F
    private var widthPx = 0
    private var gravity: Int = Gravity.CENTER
    protected lateinit var fm: FragmentManager

    /**
     * 子类继承需要创建的对话框布局Id
     */
    @LayoutRes
    protected abstract fun layoutId(): Int

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    protected abstract fun onHandleView(dialogLayout: View): Boolean

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //去除4.4以下系统中出现的标题栏
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return when {
            layoutId() != 0 -> inflater.inflate(layoutId(), container, false)
            customViewId != 0 -> inflater.inflate(customViewId, container, false)
            dialogLayout != null -> dialogLayout
            else -> {
                throw IllegalStateException("请为对话框设置布局!!!")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!onHandleView(view)) {
            viewHandler?.invoke(ViewHolder(view), dialog)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog.window?.apply {
            val params = attributes
            params.gravity = gravity
            //去除白色的背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗体动画
            setWindowAnimations(animStyle)
            if (widthPx > 0) {
                params.width = widthPx
            } else if (widthScale > 0) {
                params.width = (screenWidth * widthScale).toInt()
            }
            attributes = params
        }
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        dismissListener?.invoke(dialog)
    }

    /**
     * 显示对话框
     * @param tag:DialogFragment的Tag，默认为“iOSDialog”
     */
    @JvmOverloads
    fun show(tag: String = dialogTag) {
        this.show(fm, tag)
    }

    /**
     * 设置动画
     */
    fun setAnimStyle(@StyleRes style: Int) = this.apply { this.animStyle = style } as T

    /**
     * 设置宽度与屏幕宽度比例
     * @param scale : 范围为0~1.0，为1时占满宽度
     */
    fun setWidthScale(@FloatRange(from = 0.0, to = 1.0) scale: Float) =
        this.apply { widthScale = scale } as T

    /**
     * 设置对话框宽度
     * @param width:宽度值，单位为px
     */
    fun setWidthPx(@Px width: Int) = this.apply { widthPx = width } as T

    /**
     * 设置对话框宽度
     * @param width:宽度值，单位为dp
     */
    fun setWidthDp(width: Int) = setWidthPx(dp2px(width.toFloat()).toInt())

    /**
     * 设置对话框中屏幕中的位置
     */
    fun setGravity(gravity: Int) = this.apply { this.gravity = gravity } as T

    /**
     * 处理对话框中的View
     */
    fun setOnViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
        this.apply { this.viewHandler = viewHandler } as T

    /**
     * 对话框消失监听
     */
    fun setOnDismissListener(listener: (dialog:DialogInterface?) -> Unit) = this.apply { dismissListener = listener } as T

    /**
     * 点击对话框外部是否关闭对话框
     */
    fun setCancelOutside(isCancelable: Boolean) =
        this.apply { this.isCancelable = isCancelable } as T

    override fun onDestroy() {
        viewHandler = null
        dismissListener = null
        super.onDestroy()
    }

}