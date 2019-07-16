
package com.lindroid.iosdialog.base

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

/**
 * @author Lin
 * @date 2019/7/16
 * @function
 * @Description
 */
@Suppress("UNCHECKED_CAST")
open class BaseBuildDialog<T : BaseBuildDialog<T>> : DialogFragment()  {

    protected lateinit var fm: FragmentManager


    companion object {
        @JvmStatic
        fun <T : BaseBuildDialog<T>> build(fm: FragmentManager): BaseBuildDialog<T> = BaseBuildDialog<T>().apply {
            this.fm = fm
        } as T

        @JvmStatic
        fun <T : BaseBuildDialog<T>> build(activity: FragmentActivity): BaseBuildDialog<T> =
                build(activity.supportFragmentManager)

        @JvmStatic
        fun <T : BaseBuildDialog<T>> build(fragment: Fragment): BaseBuildDialog<T> = build(fragment.childFragmentManager)
    }


}