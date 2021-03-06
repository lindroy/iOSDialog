package com.lindroy.iosdialog.base

import com.lindroy.iosdialog.IDialog

/**
 * @author Lin
 * @date 2019/7/16
 * @function iOS风格提示对话框基类
 * @Description
 */
abstract class BaseIAlertDialog<T : BaseIAlertDialog<T>>:BaseIOSDialog<T>(){

    init {
        titleConfig = IDialog.alertTitleConfigs.copy()
        msgConfig = IDialog.alertMsgConfigs.copy()
        paddingTitleMsg = IDialog.alertPaddingTitleMsg
        paddingTop = IDialog.alertPaddingTop
        paddingSides = IDialog.alertPaddingSides
        paddingBottom = IDialog.alertPaddingBottom
        setWidthScale(IDialog.alertWidthScale)
        setAnimStyle(IDialog.alertAnimStyle)
    }
}