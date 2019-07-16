package com.lindroid.iosdialog

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
internal class CustomDialog : BaseIOSDialog<CustomDialog>() {
    /**
     * 子类继承需要创建的对话框布局Id
     */
    override fun dialogViewId() = 0

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean = false

    companion object {
        @JvmStatic
        fun build(activity: FragmentActivity) =
                CustomDialog().apply {
                    this.fm = activity.supportFragmentManager
                }

        @JvmStatic
        fun build(fragment: Fragment) =
                CustomDialog().apply {
                    this.fm = fragment.childFragmentManager
                }
    }
}