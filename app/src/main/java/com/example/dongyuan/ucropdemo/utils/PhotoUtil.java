package com.example.dongyuan.ucropdemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.dongyuan.ucropdemo.constant.AppConstant;

import java.io.File;

/**
 * 头像工具类（拍照 、相册查看）
 * Created by dong.yuan on 2017/5/19.
 */

public class PhotoUtil {
    public final static int REQUEST_SELECT_PICTURE = 10001; //相册
    public final static int REQUEST_CAMERA = 10002;//拍照


    //拍照
    public static void toCamera(Activity activity) {
        //判断文件夹是否存在
        File file = new File(AppConstant.HEAD_PHOTO_PATH);
        if (!file.exists()) {
            try {
                //不存在则要创建
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //创建拍照生成的名字
        String imageName = String.valueOf(System.currentTimeMillis()) +".jpg";
        //加载图片路径
        String imagePath=AppConstant.HEAD_PHOTO_PATH+imageName;
        Uri uri = Uri.fromFile(new File(imagePath));
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent,REQUEST_CAMERA);

    }

    //相册
    public static void toAlbum() {

    }
}
