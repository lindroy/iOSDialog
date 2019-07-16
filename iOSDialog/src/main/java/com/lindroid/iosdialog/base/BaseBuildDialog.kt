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
open class BaseBuildDialog<R : BaseBuildDialog<R>> : DialogFragment() {

    lateinit var fm: FragmentManager

    companion object {
        @JvmStatic
        inline fun <reified T : BaseBuildDialog<T>> build(fm: FragmentManager)= BaseBuildDialog<T>().apply {
            this.fm = fm
        } as T

        @JvmStatic
        inline fun <reified T : BaseBuildDialog<T>> build(activity: FragmentActivity):T=  build(activity.supportFragmentManager)


        @JvmStatic
        inline fun <reified T : BaseBuildDialog<T>> build(fragment: Fragment):T= build(fragment.childFragmentManager)
    }


}