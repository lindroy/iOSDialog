package com.lindroy.iosdialog

import android.content.DialogInterface
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.core.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.lindroy.iosdialog.adapter.IDialogListAdapter
import com.lindroy.iosdialog.base.BaseIOSDialog
import com.lindroy.iosdialog.bean.DialogItemBean
import com.lindroy.iosdialog.bean.TextParams
import com.lindroy.iosdialog.constants.DIALOG_BOTTOM_LIST
import com.lindroy.iosdialog.util.getPxSize
import com.lindroy.iosdialog.util.getResColor
import com.lindroy.iosdialog.util.getResString
import com.lindroy.iosdialog.util.getSpSize
import kotlinx.android.synthetic.main.dialog_bottom_sheet_ios.*

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS风格的底部菜单对话框
 * @Description
 */
class IBottomListDialog : BaseIOSDialog<IBottomListDialog>() {

    private val items: MutableList<DialogItemBean> = ArrayList()
    private val bottomBtnParams: TextParams = IDialog.bottomBtnConfigs.copy()
    private val bottomItemParams: TextParams = IDialog.bottomListItemConfigs.copy()
    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null
    private var cancelClickListener: ((DialogInterface) -> Unit)? = null
    private var dismissible = true
    private var itemDismissible = true
    /**
     * 子类继承需要创建的对话框布局Id
     */
    override fun layoutId() = R.layout.dialog_bottom_sheet_ios

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(dialogLayout: View): Boolean {
        super.onHandleView(dialogLayout)
        setGravity(Gravity.BOTTOM)
//        ShapeDrawable的宽高会跟随第一个设置background的View
        llContent.background = initBackgroundDrawable()

        btnCancel.apply {
            text = bottomBtnParams.text
            textSize = bottomBtnParams.textSize
            setTextColor(bottomBtnParams.textColor)
            if (bottomBtnParams.height > 0) {
                height = bottomBtnParams.height
            }
            background = initBackgroundDrawable()
            setOnClickListener {
                cancelClickListener?.invoke(dialog)
                if (dismissible) {
                    dismiss()
                }
            }
        }
        initListView()
        return false
    }

    private fun initListView() {
        if (items.isNotEmpty()) {
            lvChoices.apply {
                visibility = View.VISIBLE
                divider =
                    ContextCompat.getDrawable(mContext, R.drawable.dialog_ios_divider_vertical)
                dividerHeight = resources.getDimensionPixelSize(R.dimen.ios_dialog_divider_size)
                adapter = IDialogListAdapter(
                    mContext,
                    DIALOG_BOTTOM_LIST,
                    R.layout.item_dialog_list,
                    items
                )
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
        @ColorInt textColor: Int = bottomItemParams.textColor,
        textSize: Float = bottomItemParams.textSize
    ) =
        this.apply {
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
    fun setItemHeight(height: Int) = this.apply { bottomBtnParams.height = height }

    /**
     * 设置列表Item高度
     * @param resId: dimen资源Id
     */
    fun setItemHeightRes(@DimenRes resId: Int) = this.apply { setItemHeight(getPxSize(resId)) }

    /**
     * 设置取消按钮文字
     */
    fun setCancelText(
        text: String
    ) = this.apply { bottomBtnParams.text = text }

    /**
     * 设置取消按钮文字Id
     */
    fun setCancelText(@StringRes stringId: Int) =
        this.apply { setCancelText(getResString(stringId)) }

    /**
     * 设置取消按钮文字颜色
     */
    fun setCancelTextColor(@ColorInt color: Int) = this.apply { bottomBtnParams.textColor = color }

    /**
     * 设置取消按钮文字颜色Id
     */
    fun setCancelTextColorRes(@ColorRes colorId: Int) =
        this.apply { setCancelTextColor(getResColor(colorId)) }

    /**
     * 设置取消按钮文字大小，单位为sp
     */
    fun setCancelTextSize(textSize: Float) = this.apply { bottomBtnParams.textSize = textSize }

    /**
     * 设置取消按钮文字大小
     * @param dimens资源
     */
    fun setCancelTextSizeRes(@DimenRes textSizeId: Int) =
        this.apply { setCancelTextSize(getSpSize(textSizeId)) }

    /**
     * 设置取消按钮高度
     */
    fun setCancelButtonHeight(height: Int) = this.apply { bottomBtnParams.height = height }

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
    fun setCancelButton(
        text: String = bottomBtnParams.text,
        textColor: Int = bottomBtnParams.textColor,
        textSize: Float = bottomBtnParams.textSize,
        height: Int = bottomBtnParams.height,
        listener: (dialog: DialogInterface) -> Unit
    ) = this.apply {
        bottomBtnParams.let {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
            it.height = height
        }
        cancelClickListener = listener
    }

    /**
     * 点击取消按钮是否关闭对话框
     */
    fun setCanCelClickedDismissible(dismissible: Boolean) =
        this.apply { this.dismissible = dismissible }

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
        super.onDestroy()
        cancelClickListener = null
        itemClickListener = null
    }

    companion object {
        private fun IBottomListDialog.initConfig() {
            titleConfig = IDialog.bottomTitleConfigs.copy()
            msgConfig = IDialog.bottomMsgConfigs.copy()
            paddingTitleMsg = IDialog.bottomPaddingTitleMsg
            paddingTop = IDialog.bottomPaddingTop
            paddingSides = IDialog.bottomPaddingSides
            paddingBottom = IDialog.bottomPaddingBottom
            setWidthScale(IDialog.bottomWidthScale)
            setAnimStyle(IDialog.bottomAnimStyle)
        }

        @JvmStatic
        fun build(activity: androidx.fragment.app.FragmentActivity) =
            IBottomListDialog().apply {
                this.fm = activity.supportFragmentManager
                initConfig()
            }

        @JvmStatic
        fun build(fragment: androidx.fragment.app.Fragment) =
            IBottomListDialog().apply {
                this.fm = fragment.childFragmentManager
                initConfig()
            }
    }

}

