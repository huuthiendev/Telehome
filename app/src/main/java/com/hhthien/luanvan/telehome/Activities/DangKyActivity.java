package com.hhthien.luanvan.telehome.Activities;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hhthien.luanvan.telehome.R;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDieuKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhXa();
    }

    private void anhXa() {
        btnDieuKhoan = (Button) findViewById(R.id.btnDieuKhoan);
        btnDieuKhoan.setPaintFlags(btnDieuKhoan.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnDieuKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDieuKhoan:
                break;
        }
    }
}
