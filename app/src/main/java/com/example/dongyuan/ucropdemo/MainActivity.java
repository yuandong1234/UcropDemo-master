package com.example.dongyuan.ucropdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dongyuan.ucropdemo.utils.PhotoUtil;
import com.example.dongyuan.ucropdemo.widget.BottomDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomDialog dialog;

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
                //Toast.makeText(this,"男",Toast.LENGTH_SHORT).show();
                PhotoUtil.toCamera(this);
                break;
            case R.id.ll_sex_woman:
                dialog.dismiss();
                Toast.makeText(this, "女", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PhotoUtil.REQUEST_CAMERA:
                //在指定Uri的情况下，data返回为null
                break;
        }
    }
}
