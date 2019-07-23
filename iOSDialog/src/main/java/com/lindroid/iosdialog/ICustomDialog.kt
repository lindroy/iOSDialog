package com.lindroid.iosdialog

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import com.lindroid.iosdialog.base.BaseIOSDialog

/**
 * @author Lin
 * @date 2019/1/30
 * @function 自定义布局对话框
 * @Description
 */
class ICustomDialog : BaseIOSDialog<ICustomDialog>() {
    /**
     * 子类继承需要创建的对话框布局Id
     */
    override fun dialogViewId() = 0

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(dialogView: View): Boolean = false

    companion object {
        @JvmStatic
        fun build(activity: FragmentActivity) =
                ICustomDialog().apply {
                    this.fm = activity.supportFragmentManager
                }

        @JvmStatic
        fun build(fragment: Fragment) =
                ICustomDialog().apply {
                    this.fm = fragment.childFragmentManager
                }
    }

    /**
     * 设置自定义的布局
     */
    fun setView(dialogView: View) = this.apply { this.dialogView = dialogView }

    /**
     * 设置自定义的布局Id
     */
    fun setView(@LayoutRes layoutId: Int) = this.apply { customViewId = layoutId }
}