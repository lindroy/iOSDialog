package com.lindroy.iosdialog

import android.content.DialogInterface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.lindroy.iosdialog.adapter.IDialogListAdapter
import com.lindroy.iosdialog.base.BaseIAlertDialog
import com.lindroy.iosdialog.bean.DialogItemBean
import com.lindroy.iosdialog.constants.DIALOG_ALERT_LIST
import com.lindroy.iosdialog.util.getPxSize
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResString
import com.lindroy.iosdialog.util.getSpSize
import kotlinx.android.synthetic.main.dialog_alert_list_ios.*
import java.util.*

/**
 * @author Lin
 * @date 2019/5/19
 * @function 多选项的iOS风格提示对话框
 * @description
 */
class IAlertListDialog : BaseIAlertDialog<IAlertListDialog>() {

    private val items: MutableList<DialogItemBean> = ArrayList()
    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null
    private val alertListBtnConfigs = IDialog.alertNegBtnParams.copy()
    private val alertListItemConfigs = IDialog.alertListItemConfigs.copy()
    private var dismissible = true
    private var itemDismissible = true
    private var cancelClickListener: ((DialogInterface) -> Unit)? = null
    /**
     * 子类继承需要创建的对话框布局Id
     */
    override fun layoutId() = R.layout.dialog_alert_list_ios

    override fun onHandleView(dialogLayout: View): Boolean {
        super.onHandleView(dialogLayout)
        setAnimStyle(IDialog.alertAnimStyle)
        llRoot.background = initBackgroundDrawable()
        initListView()
        btnAlertList.apply {
            alertListBtnConfigs.let {
                text = it.text
                textSize = it.textSize
                setTextColor(it.textColor)
            }
            if (IDialog.alertBtnHeight > 0) {
                height = IDialog.alertBtnHeight
            }
            setOnClickListener {
                cancelClickListener?.invoke(dialog)
                if (dismissible) {
                    dismiss()
                }
            }
        }
        return false
    }

    private fun initListView() {
        if (items.isNotEmpty()) {
            lvChoices.apply {
                visibility = View.VISIBLE
                divider =
                    ContextCompat.getDrawable(mContext, R.drawable.dialog_ios_divider_vertical)
                dividerHeight = resources.getDimensionPixelSize(R.dimen.ios_dialog_divider_size)
                adapter =
                    IDialogListAdapter(mContext, DIALOG_ALERT_LIST, R.layout.item_dialog_list, items)
                setOnItemClickListener { parent, view, position, id ->
                    itemClickListener?.invoke(
                        position,
                        items[position].text,
                        view as TextView,
                        dialog
                    )
                    if (itemDismissible) {
                        dismiss()
                    }
                }
            }
        } else {
            lvChoices.visibility = View.GONE
        }

    }

    /**
     * 添加一个选项
     */
    @JvmOverloads
    fun addItem(
        text: String,
        @ColorInt textColor: Int = alertListItemConfigs.textColor,
        textSize: Float = alertListItemConfigs.textSize
    ) = this.apply {
        items.add(DialogItemBean(text, textColor, textSize))
    }

    /**
     * 添加一组选项
     * @param items:字符集合
     */
    fun addItems(items: List<String>) = this.apply {
        items.forEach { addItem(it) }
    }

    /**
     * 设置列表Item高度
     */
    fun setItemHeight(height: Int) = this.apply { alertListItemConfigs.height = height }

    /**
     * 设置列表Item高度
     * @param dimen资源Id
     */
    fun setItemHeightRes(@DimenRes resId: Int) = this.apply { setItemHeight(getPxSize(resId)) }

    /**
     * 点击取消按钮后是否自动关闭对话框
     * @return 默认为true
     */
    fun setCanCelClickedDismissible(dismissible: Boolean) =
        this.apply { this.dismissible = dismissible }

    /**
     * 设置取消按钮文字
     */
    fun setCancelText(text: String) = this.apply { alertListBtnConfigs.text = text }

    /**
     * 设置取消按钮文字Id
     * @param stringId:文字资源Id
     */
    fun setCancelText(@StringRes stringId: Int) =
        this.apply { setCancelText(getResString(stringId)) }

    /**
     * 设置取消按钮文字颜色
     */
    fun setCancelTextColor(@ColorInt color: Int) =
        this.apply { alertListBtnConfigs.textColor = color }

    /**
     * 设置取消按钮文字颜色Id
     * @param colorId:颜色资源Id
     */
    fun setCancelTextColorRes(@ColorRes colorId: Int) =
        this.apply { setCancelTextColor(getResColor(colorId)) }

    /**
     * 设置取消按钮文字大小，单位为sp
     */
    fun setCancelTextSize(textSize: Float) = this.apply { alertListBtnConfigs.textSize = textSize }

    /**
     * 设置取消按钮文字大小
     * @param dimens资源
     */
    fun setCancelTextSizeRes(@DimenRes textSizeId: Int) =
        this.apply { setCancelTextSize(getSpSize(textSizeId)) }

    /**
     * 设置取消按钮高度
     */
    fun setCancelButtonHeight(height: Int) = this.apply { alertListBtnConfigs.height = height }

    /**
     * 设置取消按钮高度
     * @param resId:dimens资源Id
     * @see setCancelButtonHeight
     */
    fun setCancelButtonHeightRes(@DimenRes resId: Int) =
        this.apply { setCancelButtonHeight(getPxSize(resId)) }

    /**
     * 设置取消按钮的样式和点击事件
     */
    @JvmOverloads
    fun setCancelButton(
        text: String = alertListBtnConfigs.text,
        textColor: Int = alertListBtnConfigs.textColor,
        textSize: Float = alertListBtnConfigs.textSize,
        height: Int = alertListBtnConfigs.height,
        listener: (dialog: DialogInterface) -> Unit
    ) = this.apply {
        alertListBtnConfigs.let {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
            it.height = height
        }
        cancelClickListener = listener
    }

    /**
     * 点击列表选项后是否关闭对话框
     */
    fun setItemClickedDismissible(itemDismissible: Boolean) =
        this.apply { this.itemDismissible = itemDismissible }

    /**
     * item的点击事件
     */
    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
        this.apply { itemClickListener = listener }

    /**
     * 取消按钮点击事件
     */
    fun setOnCancelClickListener(listener: (dialog: DialogInterface) -> Unit) =
        this.apply { cancelClickListener = listener }

    override fun onDestroy() {
        itemClickListener = null
        cancelClickListener = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun build(activity: androidx.fragment.app.FragmentActivity) = IAlertListDialog().apply {
            this.fm = activity.supportFragmentManager
        }

        @JvmStatic
        fun build(fragment: androidx.fragment.app.Fragment) = IAlertListDialog().apply {
            this.fm = fragment.childFragmentManager
        }
    }

}