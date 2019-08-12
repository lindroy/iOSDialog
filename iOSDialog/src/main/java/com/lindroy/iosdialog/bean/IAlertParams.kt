package com.lindroy.iosdialog.bean

import android.content.DialogInterface
import com.lindroy.iosdialog.IDialog

/**
 * @author Lin
 * @date 2019/8/9
 * @function IAlertDialog配置参数
 * @Description
 */


class IAlertParams : BaseParams() {
    var buttonHeight = IDialog.alertBtnHeight
    var posListener: ((DialogInterface) -> Unit)? = null
    var negListener: ((DialogInterface) -> Unit)? = null
}