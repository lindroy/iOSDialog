package com.lindroid.iosdialoglib;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lindroid.iosdialog.CustomDialog;
import com.lindroid.iosdialog.IAlertDialog;
import com.lindroid.iosdialog.IAlertListDialog;
import com.lindroid.iosdialog.IBottomListDialog;
import com.lindroid.iosdialog.viewholder.ViewHolder;

import java.util.Arrays;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/**
 * @author Lin
 * @date 2019/7/16
 * @function Java代码使用示例
 * @Description
 */
public class JavaActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotlin);
        mContext = this;
    }


    /**
     * IAlertDialog
     *
     * @param view
     */
    public void showIAlertDialog(View view) {
        IAlertDialog.build(this)
                .setTitle("提示")
                .setMessage("确定要退出登录吗？")
                .setPosButtonText(R.string.ios_dialog_positive_text)
                .setNegButtonText(R.string.ios_dialog_negative_text)
                .setPosClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.confirm), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setNegClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();

    }

    /**
     * 选项较少的IAlertListDialog
     */
    public void showIAlertListDialog(View view) {
        IAlertListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的书籍")
                .addItem("西游记")
                .addItem("红楼梦")
                .addItem("水浒传")
                .addItem("三国演义")
                .setCancelText(R.string.cancel)
                .setCancelClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setItemClickedDismissible(true)
                .setItemClickListener(new Function4<Integer, String, TextView, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(Integer pos, String s, TextView textView, DialogInterface dialogInterface) {
                        Toast.makeText(mContext, "你选择了" + s, Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();
    }

    /**
     * 选项较多的IAlertListDialog
     */
    public void showIAlertListMore(View view) {
        IAlertListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的城市")
                .addItems(Arrays.asList(getResources().getStringArray(R.array.cities)))
                .setCancelText(R.string.cancel)
                .setCancelClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setItemClickedDismissible(true)
                .setItemClickListener(new Function4<Integer, String, TextView, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(Integer pos, String s, TextView textView, DialogInterface dialogInterface) {
                        Toast.makeText(mContext, "你选择了" + s, Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();
    }

    /**
     * 选项较少的IBottomListDialog
     */
    public void showIBottomListDialog(View view) {
        IBottomListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的书籍")
                .addItem("西游记")
                .addItem("红楼梦")
                .addItem("水浒传")
                .addItem("三国演义")
                .setCancelText(R.string.cancel)
                .setCancelClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setItemClickedDismissible(true)
                .setItemClickListener(new Function4<Integer, String, TextView, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(Integer pos, String s, TextView textView, DialogInterface dialogInterface) {
                        Toast.makeText(mContext, "你选择了" + s, Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();
    }

    /**
     * 选项较少的IBottomListMore
     */
    public void showIBottomListMore(View view) {
        IBottomListDialog.build(this)
                .setTitle("提示")
                .setMessage("请选择你喜欢的城市")
                .addItems(Arrays.asList(getResources().getStringArray(R.array.cities)))
                .setCancelText(R.string.cancel)
                .setCancelClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext, getString(R.string.cancel), Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setItemClickedDismissible(true)
                .setItemClickListener(new Function4<Integer, String, TextView, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(Integer pos, String s, TextView textView, DialogInterface dialogInterface) {
                        Toast.makeText(mContext, "你选择了" + s, Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();

    }

    /**
     * 自定义内容布局的IAlertDialog
     */
    public void showIAlertCustomDialog(View view) {
        IAlertDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
//                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.dialog_content_view)
                .setPosButtonText(R.string.confirm)
                .setNegButtonText(R.string.cancel)
                .setViewHandler(new Function2<ViewHolder, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(ViewHolder holder, DialogInterface dialogInterface) {
                        holder.setOnClickListener(R.id.imageView, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show();
                            }
                        });
                        return null;
                    }
                })
                .show(); //显示对话框
    }

    /**
     * 自定义内容布局的IAlertListDialog
     */
    public void showIAlertListCustomDialog(View view) {
        IAlertListDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
//                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.dialog_content_view)
                .setCancelText(R.string.confirm)
                .setViewHandler(new Function2<ViewHolder, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(ViewHolder holder, DialogInterface dialogInterface) {
                        holder.setOnClickListener(R.id.imageView, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show();
                            }
                        });
                        return null;
                    }
                })
                .show(); //显示对话框
    }

    /**
     * 自定义内容布局的IBottomListDialog
     */
    public void showIBottomCustomDialog(View view) {
        IBottomListDialog
                .build(this) //创建和配置对话框的入口
                .setTitle("提示")
//                    .setMessage("确定要退出登录吗？")
                .setContentView(R.layout.dialog_content_view)
                .setCancelText(R.string.confirm)
                .setViewHandler(new Function2<ViewHolder, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(ViewHolder holder, DialogInterface dialogInterface) {
                        holder.setOnClickListener(R.id.imageView, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "点击图标", Toast.LENGTH_LONG).show();
                            }
                        });
                        return null;
                    }
                })
                .show(); //显示对话框
    }

    /**
     * 完全自定义布局对话框
     */
    public void showCustomDialog(View view) {
        CustomDialog.build(this)
                .setView(R.layout.dialog_custom_view)
                .setViewHandler(new Function2<ViewHolder, DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(ViewHolder holder, final DialogInterface dialog) {
                        holder.setOnClickListener(R.id.tvDismiss, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        return null;
                    }
                })
                .show();

    }
}
