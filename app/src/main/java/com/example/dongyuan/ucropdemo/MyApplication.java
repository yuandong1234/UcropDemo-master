package com.example.dongyuan.ucropdemo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

/**
 * Created by dong.yuan on 2017/5/19.
 */

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        //7.0 调用系统相机需要
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
