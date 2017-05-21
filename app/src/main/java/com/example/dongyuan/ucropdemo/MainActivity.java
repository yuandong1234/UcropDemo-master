package com.example.dongyuan.ucropdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dongyuan.ucropdemo.utils.PhotoUtil;
import com.example.dongyuan.ucropdemo.widget.BottomDialog;
import com.yalantis.ucrop.UCrop;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private BottomDialog dialog;
    private Uri originalUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        initDialog();
    }

    private void initDialog() {
        View dialogView = View.inflate(this, R.layout.layout_bottom_dialog, null);
        dialog = new BottomDialog.Builder(this)
                .setContentView(dialogView)
                .setListener(this)
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;
            case R.id.ll_sex_man:
                dialog.dismiss();
                originalUri = PhotoUtil.toCamera(this);
                break;
            case R.id.ll_sex_woman:
                dialog.dismiss();
                PhotoUtil.toAlbum(this);
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PhotoUtil.CAMERA_REQUEST_CODE:
                    //在指定Uri的情况下，data返回为null
                    Log.e(TAG, "照相成功");
                    PhotoUtil.toCrop(originalUri, this);
                    break;
                case PhotoUtil.ALBUM_REQUEST_CODE:
                    if (data != null) {
                        try {
                            Uri uri = data.getData();
                            Log.e(TAG, "从相册获取成功 :"+uri.getPath());
                            PhotoUtil.toCrop(uri, this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    if (data != null) {
                        Uri resultUri = UCrop.getOutput(data);
                        Log.e(TAG, "图片剪切成功 : " + resultUri.getPath());
                        ResultActivity.startWithUri(this, resultUri);
                    }
                    break;
                case UCrop.RESULT_ERROR:
                    Throwable cropError = UCrop.getError(data);
                    Log.e(TAG, "图片剪切失败 :" + cropError.toString());
                    break;
            }
        }

    }
}
