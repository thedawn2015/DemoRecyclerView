package com.simon.demorecyclerview.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simon.demorecyclerview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button linearButton, gridButton, containerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
    }

    private void assignViews() {
        linearButton = (Button) findViewById(R.id.btn_linear);
        gridButton = (Button) findViewById(R.id.btn_grid);
        containerButton = (Button) findViewById(R.id.btn_container);

        linearButton.setOnClickListener(this);
        gridButton.setOnClickListener(this);
        containerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_linear:
                LinearLayoutManagerActivity.launcher(this);
                break;
            case R.id.btn_grid:
                call("15881173371");
                break;
            case R.id.btn_container:
                break;
            default:
                break;
        }
    }

    /**
     * 拨打电话
     *
     * @param phoneNum
     */
    private void call(String phoneNum) {
        if (!phoneNum.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
    }
}