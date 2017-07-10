package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.NguoiDungRequest;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "DangNhapActivity";
    private Button btnTrangDangKy, btnDangNhap;
    private EditText edtEmail, edtMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
    }

    private void anhXa() {
        btnTrangDangKy = (Button) findViewById(R.id.btnTrangDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnTrangDangKy.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTrangDangKy:
                Intent intent = new Intent(this, DangKyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btnDangNhap:
                NguoiDungRequest.KiemTraDangNhap(this, edtEmail.getText().toString(), edtMatKhau.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
