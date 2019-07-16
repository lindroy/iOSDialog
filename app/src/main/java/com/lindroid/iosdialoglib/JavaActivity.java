package com.lindroid.iosdialoglib;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.lindroid.iosdialog.IAlertDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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
                        Toast.makeText(mContext,"确定",Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .setNegClickListener(new Function1<DialogInterface, Unit>() {
                    @Override
                    public Unit invoke(DialogInterface dialogInterface) {
                        Toast.makeText(mContext,"取消",Toast.LENGTH_LONG).show();
                        return null;
                    }
                })
                .show();

    }
}
