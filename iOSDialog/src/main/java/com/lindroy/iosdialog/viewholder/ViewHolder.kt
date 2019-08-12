package com.lindroy.iosdialog.viewholder

import android.content.Context
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.util.SparseArray
import android.view.View
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView

/**
 * @author Lin
 * @date 2019/1/22
 * @function 配合自定义布局对话框使用的ViewHolder类，集成了一些常用的控件的方法
 * @Description
 */
@Suppress("UNCHECKED_CAST")
class ViewHolder constructor(private val convertView: View) {
    private val views: SparseArray<View> = SparseArray()
    private var mContext: Context = convertView.context

    /**
     * 通过Id获取某个View的实例
     */
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }
    /**
     * 通过Id获取某个TextView的实例
     */
    fun getTextView(@IdRes viewId: Int) = getView<TextView>(viewId)
    /**
     * 通过Id获取某个ImageView的实例
     */
    fun getImageView(@IdRes viewId: Int) = getView<ImageView>(viewId)
    /**
     * 通过Id获取某个Button的实例
     */
    fun getButton(@IdRes viewId: Int) = getView<ImageView>(viewId)

    /**
     * 设置TextView文字
     * @param stringId:字符资源ID
     */
    fun setText(@IdRes viewId: Int, @StringRes stringId: Int) =
            this.apply { getTextView(viewId).setText(stringId) }
    /**
     * 设置TextView文字
     */
    fun setText(@IdRes viewId: Int, string: String) = this.apply { getTextView(viewId).text = string }
    /**
     * 设置TextView文字颜色
     */
    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int) =
            this.apply { getTextView(viewId).setTextColor(color) }
    /**
     * 设置TextView文字颜色
     * @param colorId:文字资源Id
     */
    fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorId: Int) =
            this.apply { setTextColor(viewId, ContextCompat.getColor(mContext, colorId)) }

    /**
     * 设置可见性
     */
    fun setVisibility(@IdRes viewId: Int, visibility: Int) = this.apply { getView<View>(viewId).visibility = visibility }


    /**
     * 设置是否隐藏
     */
    fun setGone(@IdRes viewId: Int, isGone: Boolean) = this.apply { getView<View>(viewId).visibility = if (isGone) View.GONE else View.VISIBLE }

    /**
     * 设置背景
     * @param backgroundResId : Drawable资源Id
     */
    fun setBackgroundRes(@IdRes viewId: Int, @DrawableRes backgroundResId: Int) =
            this.apply { getView<View>(viewId).setBackgroundResource(backgroundResId) }
    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) =
            this.apply { getView<View>(viewId).setBackgroundColor(color) }
    /**
     * 设置背景颜色
     * @param colorId : Drawable资源Id
     */
    fun setBackgroundColorRes(@IdRes viewId: Int, @ColorRes colorId: Int) =
            this.apply { getView<View>(viewId).setBackgroundColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * 设置选中
     */
    fun setChecked(@IdRes viewId: Int, checked: Boolean) = this.apply {
        val view: View = getView(viewId)
        if (view is Checkable) {
            view.isChecked = checked
        }
    }

    /**
     * 点击监听
     */
    fun setOnClickListener(@IdRes viewId: Int, clickListener: View.OnClickListener) =
            this.apply { getView<View>(viewId).setOnClickListener(clickListener) }

    /**
     * 点击监听
     * @param viewIds:点击事件相同的View的Id
     */
    fun setOnClickListener(@IdRes vararg viewIds: Int, clickListener: View.OnClickListener) = this.apply {
        viewIds.forEach {
            getView<View>(it).setOnClickListener(clickListener)
        }
    }

    /**
     * 柯里化setOnClickListener方法
     */
    fun setOnClickListener(@IdRes vararg viewIds: Int) =
            fun(clickListener: View.OnClickListener) =
                    this.apply {
                        viewIds.forEach {
                            getView<View>(it).setOnClickListener(clickListener)
                        }
                    }

    /**
     * 选择监听
     */
    fun setOnCheckedChangeListener(@IdRes viewId: Int, listener: CompoundButton.OnCheckedChangeListener) = this.apply {
        val view: View = getView(viewId)
        if (view is CompoundButton) {
            view.setOnCheckedChangeListener(listener)
        }
    }


//    fun <P1,P2,R> Function2<P1,P2,R>.curried() = fun(p1:P1) = fun(p2:P2)=this(p1,p2)
}