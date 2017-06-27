package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hhthien.luanvan.telehome.R;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnTrangDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
    }

    private void anhXa() {
        btnTrangDangKy = (Button) findViewById(R.id.btnTrangDangKy);
        btnTrangDangKy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTrangDangKy:
                Intent intent = new Intent(this, DangKyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
