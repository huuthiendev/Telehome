package com.hhthien.luanvan.telehome.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hhthien.luanvan.telehome.Models.HoaDon;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.GioHangRequest;
import com.hhthien.luanvan.telehome.Requests.NguoiDungRequest;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar tbThanhToan;
    EditText edtTenNguoiNhan, edtDiaChi, edtSoDT;
    TextView tvNhanTienKhiGiaoHang, tvChuyenKhoan;
    ImageButton btnNhanTienKhiGiaoHang, btnChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoaThuan;
    int chonHinhThuc = 0;
    private int mand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();

        SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        mand = sharedPreferences.getInt("id", 0);
        NguoiDungRequest.ThongTinNguoiDungThanhToan(this, edtTenNguoiNhan, edtSoDT, edtDiaChi, mand);
    }

    private void anhXa() {
        tbThanhToan = (Toolbar) findViewById(R.id.tbThanhToan);
        tbThanhToan.setTitle("Thanh toán");
        setSupportActionBar(tbThanhToan);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);
        edtTenNguoiNhan = (EditText) findViewById(R.id.edtTenNguoiNhan);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtSoDT = (EditText) findViewById(R.id.edtSoDT);
        btnNhanTienKhiGiaoHang = (ImageButton) findViewById(R.id.btnNhanTienKhiGiaoHang);
        btnChuyenKhoan = (ImageButton) findViewById(R.id.btnChuyenKhoan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        tvNhanTienKhiGiaoHang = (TextView) findViewById(R.id.tvNhanTienKhiGiaoHang);
        tvChuyenKhoan = (TextView) findViewById(R.id.tvChuyenKhoan);
        cbThoaThuan = (CheckBox) findViewById(R.id.cbThoaThuan);
        btnNhanTienKhiGiaoHang.setOnClickListener(this);
        btnChuyenKhoan.setOnClickListener(this);
        btnThanhToan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThanhToan:
                String tennguoinhan = edtTenNguoiNhan.getText().toString();
                String sodt = edtSoDT.getText().toString();
                String diachi = edtDiaChi.getText().toString();

                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0 ){
                    if(cbThoaThuan.isChecked()) {
                        if (chonHinhThuc == 0) {
                            Toast.makeText(this, "Vui lòng chọn hình thức thanh toán", Toast.LENGTH_SHORT).show();
                        } else {
                            HoaDon hoadon = new HoaDon(mand, tennguoinhan, sodt, diachi, chonHinhThuc);
                            GioHangRequest.ThanhToan(this, hoadon);
                        }
                    } else {
                        Toast.makeText(this,"Bạn chưa nhấn chọn vào ô thỏa thuận", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,"Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnNhanTienKhiGiaoHang:
                ChonHinhThucGiaoHang(tvNhanTienKhiGiaoHang, tvChuyenKhoan);
                chonHinhThuc = 1;
                break;
            case R.id.btnChuyenKhoan:
                ChonHinhThucGiaoHang(tvChuyenKhoan, tvNhanTienKhiGiaoHang);
                chonHinhThuc = 2;
                break;
        }
    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon){
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorXamNhat));
    }

    private int getIdColor(int idcolor){
        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }
        return color;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ThanhToanActivity.this, GioHangActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ThanhToanActivity.this, GioHangActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
