package com.lindroid.iosdialoglib

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.lindroid.iosdialog.IAlertDialog
import com.lindroid.iosdialog.IAlertListDialog
import com.lindroid.iosdialog.IBottomListDialog
import java.util.*

/**
 * @author Lin
 * @date 2019/7/16
 * @function Kotlin代码编写的示例
 * @Description
 */
class KotlinActivity : AppCompatActivity() {
    private lateinit var mContext:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        mContext = this
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

    /**
     * 选项较少的IAlertListDialog
     */
    fun showIAlertListDialog(view: View) {
        IAlertListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的书籍")
                .addItem("西游记")
                .addItem("红楼梦")
                .addItem("水浒传")
                .addItem("三国演义")
                .setCancelText(R.string.cancel)
                .setCancelClickListener {
                    Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show()
                    null
                }
                .setItemClickedDismissible(true)
                .setItemClickListener { pos, s, textView, dialogInterface ->
                    Toast.makeText(mContext, "你选择了$s", Toast.LENGTH_LONG).show()
                    null
                }
                .show()
    }

    /**
     * 选项较多的IAlertListDialog
     */
    fun showIAlertListMore(view: View) {
        IAlertListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的城市")
                .addItems(Arrays.asList(*resources.getStringArray(R.array.cities)))
                .setCancelText(R.string.cancel)
                .setCancelClickListener {
                    Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show()
                }
                .setItemClickedDismissible(true)
                .setItemClickListener { pos, s, textView, dialogInterface ->
                    Toast.makeText(mContext, "你选择了$s", Toast.LENGTH_LONG).show()
                }
                .show()
    }
    /**
     * 选项较少的IBottomListDialog
     */
    fun showIBottomListDialog(view: View) {
        IBottomListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的书籍")
                .addItem("西游记")
                .addItem("红楼梦")
                .addItem("水浒传")
                .addItem("三国演义")
                .setCancelText(R.string.cancel)
                .setCancelClickListener {
                    Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show()
                }
                .setItemClickedDismissible(true)
                .setItemClickListener { pos, s, textView, dialogInterface ->
                    Toast.makeText(mContext, "你选择了$s", Toast.LENGTH_LONG).show()
                }
                .show()
    }

    /**
     * 选项较少的IBottomListMore
     */
    fun showIBottomListMore(view: View) {
        IBottomListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的城市")
                .addItems(Arrays.asList(*resources.getStringArray(R.array.cities)))
                .setCancelText(R.string.cancel)
                .setCancelClickListener {
                    Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show()
                }
                .setItemClickedDismissible(true)
                .setItemClickListener { pos, s, textView, dialogInterface ->
                    Toast.makeText(mContext, "你选择了$s", Toast.LENGTH_LONG).show()
                }
                .show()

    }

    /**
     * 自定义内容布局的IAlertDialog
     */
    fun showIAlertCustomDialog(view: View) {
        IAlertDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
                //                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.custom_view)
                .setPosButtonText(R.string.confirm)
                .setNegButtonText(R.string.cancel)
                .setViewHandler { holder, dialogInterface ->
                    holder.setOnClickListener(R.id.imageView, View.OnClickListener { Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show() })
                    null
                }
                .show() //显示对话框
    }

    /**
     * 自定义内容布局的IAlertListDialog
     */
    fun showIAlertListCustomDialog(view: View) {
        IAlertListDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
                //                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.custom_view)
                .setCancelText(R.string.confirm)
                .setViewHandler { holder, dialogInterface ->
                    holder.setOnClickListener(R.id.imageView, View.OnClickListener { Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show() })
                    null
                }
                .show() //显示对话框
    }

    /**
     * 自定义内容布局的IBottomListDialog
     */
    fun showIBottomCustomDialog(view: View) {
        IBottomListDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
                //                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.custom_view)
                .setCancelText(R.string.confirm)
                .setViewHandler { holder, dialogInterface ->
                    holder.setOnClickListener(R.id.imageView, View.OnClickListener { Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show() })
                    null
                }
                .show() //显示对话框
    }
}
