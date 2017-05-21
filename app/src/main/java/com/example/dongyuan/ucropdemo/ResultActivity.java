package com.example.dongyuan.ucropdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.dongyuan.ucropdemo.utils.BitmapUtil;

public class ResultActivity extends AppCompatActivity {
    private static String TAG=ResultActivity.class.getSimpleName();

    private ImageView ucropImage;

    public static void startWithUri(@NonNull Context context, @NonNull Uri uri) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();

    }

    private void initView() {
        ucropImage = (ImageView) findViewById(R.id.ucropImage);
        String imagePath=getIntent().getData().getPath();
        Bitmap bitmap= BitmapUtil.decodeBitmap(imagePath);
        ucropImage.setImageBitmap(bitmap);
    }
}
