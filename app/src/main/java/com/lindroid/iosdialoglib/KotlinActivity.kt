package com.lindroid.iosdialoglib

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.lindroid.iosdialog.IAlertDialog

/**
 * @author Lin
 * @date 2019/7/16
 * @function Kotlin代码编写的示例
 * @Description
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

   /* private fun setViewClickListener(vararg viewIds: Int, listener: (view: View) -> Unit) {
        viewIds.forEach { viewId ->
            findViewById<View>(viewId).setOnClickListener {
                listener.invoke(it)
            }
        }
    }*/

    /**
     * IAlertDialog
     */
    fun showIAlertDialog(view: View) {
        IAlertDialog.build(this)
            .setTitle("提示")
            .setMessage("确定要退出登录吗？")
            .setPosButtonText(R.string.ios_dialog_positive_text)
            .setNegButtonText(R.string.ios_dialog_negative_text)
            .setPosClickListener {
                Toast.makeText(this, "确定", Toast.LENGTH_LONG).show()
            }
            .setNegClickListener {
                Toast.makeText(this, "取消", Toast.LENGTH_LONG).show()
            }
            .show()

    }
}
