package com.lindroid.iosdialog

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import com.lindroid.iosdialog.bean.TextParams
import com.lindroid.iosdialog.util.getPxSize
import com.lindroid.iosdialog.util.getResColor
import com.lindroid.iosdialog.util.getResString
import com.lindroid.iosdialog.util.getSpSize

/**
 * @author Lin
 * @date 2019/5/18
 * @function IDialog配置类
 * @Description
 */
object IDialog {

    internal val context: Context
        get() = application.applicationContext

    internal var alertWidthScale = 0.7F

    internal var alertWidthPx = 0

    internal var bottomWidthScale = 0.95F

    internal var bottomWidthPx = 0

    internal var cornerRadius = 0F

    internal var alpha = 0.85F

    internal var bgColor = Color.WHITE

    internal var alertBtnHeight = 0

    internal val alertTitleConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_alert_title_size), textColorBlack)
    }

    internal val alertMsgConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_alert_message_size), textColorBlack)
    }

    /**
     * 提示类对话框动画
     */
    internal var alertAnimStyle: Int = R.style.IOSAlertDialogStyle

    internal val alertPosBtnConfigs by lazy {
        TextParams(
            getSpSize(R.dimen.ios_alert_button_text_size),
            textColorBlue,
            text = getResString(R.string.ios_dialog_positive_text)
        )
    }

    internal val alertNegBtnConfigs by lazy {
        TextParams(
            getSpSize(R.dimen.ios_alert_button_text_size),
            textColorRed,
            text = getResString(R.string.ios_dialog_negative_text)
        )
    }

    internal val alertListItemConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_alert_list_item_text_size), textColorBlue)
    }

    internal var alertPaddingTop = 0

    internal var alertPaddingBottom = 0

    internal var alertPaddingSides = 0

    /**
     * 提示对话框标题与消息文字文字的间距
     */
    internal var alertPaddingTitleMsg = 0

    internal var bottomPaddingTop = 0

    internal var bottomPaddingBottom = 0

    internal var bottomPaddingSides = 0

    internal var bottomPaddingTitleMsg = 0

    internal var bottomAnimStyle: Int = R.style.IOSBottomDialogStyle

    internal val bottomTitleConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_bottom_title_size), textColorBlack)
    }

    internal val bottomMsgConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_bottom_message_size), textColorBlack)
    }

    internal val bottomBtnConfigs by lazy {
        TextParams(
            getSpSize(R.dimen.ios_bottom_button_text_size), textColorBlue,
            text = context.getString(R.string.ios_dialog_negative_text)
        )
    }

    internal val bottomListItemConfigs by lazy {
        TextParams(getSpSize(R.dimen.ios_bottom_list_item_text_size), textColorBlue)
    }

    private lateinit var application: Application

    private val textColorBlue by lazy {
        getResColor(R.color.ios_dialog_text_color_blue)
    }

    private val textColorRed by lazy {
        getResColor(R.color.ios_dialog_text_color_red)
    }

    private val textColorBlack by lazy {
        getResColor(R.color.ios_dialog_text_color_black)
    }

    /**
     * 初始化方法，调起所有
     */
    fun init(application: Application): Config {
        this.application = application
        cornerRadius = getPxSize(R.dimen.ios_dialog_corner_radius).toFloat()
        alertPaddingTop = getPxSize(R.dimen.ios_alert_padding_top)
        alertPaddingBottom = getPxSize(R.dimen.ios_alert_padding_bottom)
        alertPaddingSides = getPxSize(R.dimen.ios_alert_padding_sides)
        alertPaddingTitleMsg = getPxSize(R.dimen.ios_alert_padding_title_msg)
        bottomPaddingTop = getPxSize(R.dimen.ios_bottom_padding_top)
        bottomPaddingBottom = getPxSize(R.dimen.ios_bottom_padding_bottom)
        bottomPaddingSides = getPxSize(R.dimen.ios_bottom_padding_sides)
        bottomPaddingTitleMsg = getPxSize(R.dimen.ios_bottom_padding_title_msg)
        return Config.build()
    }


    class Config {

        /**
         * 设置背景颜色
         * 默认为纯白色（Color.WHITE）
         */
        fun setBackgroudColor(@ColorInt color: Int) = this.apply { bgColor = color }

        /**
         * 设置背景圆角半径
         * 单位为px；默认为12dp（R.dimen.ios_dialog_corner_radius）
         */
        fun setCornerRadius(cornerRadius: Float) = this.apply {
            IDialog.cornerRadius = cornerRadius
        }

        /**
         * 设置背景透明度
         * 范围为0~1.0,0为全透明，1为全不透明；默认为0.85
         */
        fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) =
            this.apply { IDialog.alpha = alpha }

        /**
         * 设置提示对话框与屏幕宽度比
         * 默认为0.7
         */
        fun setAlertWidthScale(widthScale: Float) = this.apply { alertWidthScale = widthScale }

        /**
         * 设置提示对话框宽度，默认为0，优先于setAlertWidthScale
         * @param width:宽度值，单位为px
         */
        fun setAlertWidthPx(width: Int) = this.apply { alertWidthPx = width }

        /**
         * 设置提示对话框动画
         */
        fun setAlertAnimStyle(@StyleRes style: Int) = this.apply { alertAnimStyle = style }

        /**
         * 设置提示对话框顶部内边距
         * 单位为px；默认为R.dimen.ios_alert_padding_top
         */
        fun setAlertPaddingTop(top: Int) = this.apply { alertPaddingTop = top }

        /**
         * 设置提示对话框标题与消息文字布局的底部内边距
         * 单位为px；默认为R.dimen.ios_alert_padding_bottom
         */
        fun setAlertPaddingBottom(bottom: Int) = this.apply { alertPaddingBottom = bottom }

        /**
         * 设置提示对话框标题与消息文字布局的两侧内边距
         * 单位为px；默认为R.dimen.ios_alert_padding_sides
         */
        fun setAlertPaddingSides(sides: Int) = this.apply { alertPaddingSides = sides }

        /**
         * 设置提示对话框标题与消息文字之间的间距
         * 单位为px，默认为R.dimen.ios_alert_padding_title_msg
         */
        fun setAlertPaddingTitleMsg(padding: Int) = this.apply { alertPaddingTitleMsg = padding }

        /**
         * 设置提示对话框标题样式
         */
        @JvmOverloads
        fun setAlertTitleView(
            textSize: Float = alertTitleConfigs.textSize,
            @ColorInt textColor: Int = alertTitleConfigs.textColor,
            text: String = "",
            gravity: Int = alertTitleConfigs.gravity
        ) = this.apply {
            alertTitleConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.text = text
                it.gravity = gravity
            }
        }

        /**
         * 设置提示对话框消息文字样式
         */
        @JvmOverloads
        fun setAlertMsgView(
            textSize: Float = alertMsgConfigs.textSize,
            @ColorInt textColor: Int = alertMsgConfigs.textColor,
            gravity: Int = alertMsgConfigs.gravity
        ) = this.apply {
            alertMsgConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        /**
         * 设置提示对话框按钮高度
         * 单位为px，默认为R.dimen.ios_alert_bottom_height
         */
        fun setAlertButtonHeight(height: Int) = this.apply { alertBtnHeight = height }

        /**
         * 设置提示对话框确认按钮样式
         */
        @JvmOverloads
        fun setAlertPosButton(
            text: String = alertPosBtnConfigs.text,
            textSize: Float = alertPosBtnConfigs.textSize,
            @ColorInt textColor: Int = alertPosBtnConfigs.textColor,
            gravity: Int = alertPosBtnConfigs.gravity
        ) = this.apply {
            alertPosBtnConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        /**
         * 设置提示对话框取消按钮样式
         */
        @JvmOverloads
        fun setAlertNegButton(
            text: String = alertNegBtnConfigs.text,
            textSize: Float = alertNegBtnConfigs.textSize,
            @ColorInt textColor: Int = alertNegBtnConfigs.textColor,
            gravity: Int = alertNegBtnConfigs.gravity
        ) = this.apply {
            alertNegBtnConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        /**
         * 设置IAlertListDialog的选项样式
         * @param textSize:字体大小，单位sp
         * @param paddingSides:左右的padding值
         */
        @JvmOverloads
        fun setAlertListItem(
            textSize: Float = alertListItemConfigs.textSize,
            @ColorInt textColor: Int = alertListItemConfigs.textColor,
            height: Int = alertListItemConfigs.height,
            paddingSides: Int = 0
        ) = this.apply {
            alertListItemConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
                it.paddingLeft = paddingSides
                it.paddingRight = paddingSides
            }
        }

        /**
         * 设置底部对话框与屏幕宽度之比，默认为0.95
         *
         */
        fun setBottomWidthScale(widthScale: Float) = this.apply { bottomWidthScale = widthScale }

        /**
         * 设置底部对话框的宽度，默认为0，优先于setBottomWidthScale
         * @param width:宽度值，单位为px
         */
        fun setBottomWidthPx(width:Int) = this.apply { bottomWidthPx = width }

        /**
         * 底部对话框的动画样式
         */
        fun setBottomAnimStyle(@StyleRes style: Int) = this.apply { bottomAnimStyle = style }

        /**
         * 设置底部对话框顶部内边距
         * 单位为px；默认为R.dimen.ios_bottom_padding_top
         */
        fun setBottomPaddingTop(top: Int) = this.apply { bottomPaddingTop = top }

        /**
         * 设置底部对话框标题与消息文字部分的底部内边距
         * 单位为px；默认为R.dimen.ios_bottom_padding_bottom
         */
        fun setBottomPaddingBottom(bottom: Int) = this.apply { bottomPaddingBottom = bottom }

        /**
         * 设置提示对话框标题与消息文字部分的两侧内边距
         * 单位为px；默认为R.dimen.ios_bottom_padding_sides
         */
        fun setBottomPaddingSides(sides: Int) = this.apply { bottomPaddingSides = sides }

        /**
         * 设置底部对话框标题与消息文字部分的两侧内边距
         * 单位为px；默认为R.dimen.ios_bottom_padding_title_msg
         */
        fun setBottomPaddingTitleMsg(padding: Int) = this.apply { bottomPaddingTitleMsg = padding }

        /**
         * 设置IBottomListDialog的标题样式
         */
        @JvmOverloads
        fun setBottomListTitleView(
            textSize: Float = bottomTitleConfigs.textSize,
            @ColorInt textColor: Int = bottomTitleConfigs.textColor,
            text: String = "",
            gravity: Int = bottomTitleConfigs.gravity
        ) = this.apply {
            bottomTitleConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        /**
         * 设置IBottomListDialog的消息文字文字样式
         */
        @JvmOverloads
        fun setBottomListMsgView(
            textSize: Float = bottomMsgConfigs.textSize,
            @ColorInt textColor: Int = bottomMsgConfigs.textColor,
            gravity: Int = bottomMsgConfigs.gravity
        ) = this.apply {
            bottomMsgConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        /**
         * 设置IBottomListDialog的取消按钮样式
         */
        @JvmOverloads
        fun setBottomListButton(
            textSize: Float = bottomBtnConfigs.textSize,
            @ColorInt textColor: Int = bottomBtnConfigs.textColor,
            text: String = bottomBtnConfigs.text,
            height: Int = bottomBtnConfigs.height
        ) = this.apply {
            bottomBtnConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
            }
        }

        /**
         * 设置IBottomListDialog的选项样式
         * @param paddingSides:左右的padding值
         */
        fun setBottomListItem(
            textSize: Float = bottomListItemConfigs.textSize,
            @ColorInt textColor: Int = bottomListItemConfigs.textColor,
            height: Int = bottomListItemConfigs.height,
            paddingSides: Int = 0
        ) = this.apply {
            bottomListItemConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
                it.paddingLeft = paddingSides
                it.paddingRight = paddingSides
            }
        }

        companion object {
            @JvmStatic
            fun build() = Config()
        }

    }
}