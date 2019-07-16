package com.lindroid.iosdialoglib.app

import android.app.Application
import com.lindroid.iosdialog.IDialog

/**
 * @author Lin
 * @date 2019/7/16
 * @function
 * @Description
 */
class App :Application(){
    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
      /*  if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)*/
//        LinDialog.init(instance)
        IDialog.init(instance)
    }
}