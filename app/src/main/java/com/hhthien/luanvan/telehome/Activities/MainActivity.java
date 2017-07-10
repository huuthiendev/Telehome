package com.hhthien.luanvan.telehome.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hhthien.luanvan.telehome.Common.ModelGioHang;
import com.hhthien.luanvan.telehome.Fragments.QuanTamFragment;
import com.hhthien.luanvan.telehome.Fragments.TaiKhoanFragment;
import com.hhthien.luanvan.telehome.Fragments.TrangChuFragment;
import com.hhthien.luanvan.telehome.Models.GioHang;
import com.hhthien.luanvan.telehome.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    private int mand;
    private ModelGioHang modelGioHang;
    private List<GioHang> listGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        mand = sharedPreferences.getInt("id", 0);
        listGioHang = new ArrayList<>();

        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(this);
        listGioHang = modelGioHang.LayDanhSachSanPhamTrongGioHang();

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setBadgeBackgroundColor(getResources().getColor(R.color.colorTelehome));
        bottomBar.setOnTabSelectListener(tabSelect);
        bottomBar.getTabWithId(R.id.tab_giohang).setBadgeCount(LaySoLuongTrongGioHang(listGioHang));
    }

    OnTabSelectListener tabSelect = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            if (tabId == R.id.tab_trangchu) {
                TrangChuFragment fTrangChu = new TrangChuFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fTrangChu).commit();
            }
            else if (tabId == R.id.tab_timkiem) {
                Intent intentTimKiem = new Intent(MainActivity.this, TimKiemActivity.class);
                startActivity(intentTimKiem);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            else if (tabId == R.id.tab_giohang) {
                Intent intentGioHang = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intentGioHang);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            else if (tabId == R.id.tab_quantam) {
                QuanTamFragment fQuanTam = new QuanTamFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fQuanTam).commit();
            }
            else if (tabId == R.id.tab_taikhoan) {
                if (mand == 0) {
                    Intent intentDangNhap = new Intent(MainActivity.this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    TaiKhoanFragment fTaiKhoan = new TaiKhoanFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fTaiKhoan).commit();
                }
            }
        }
    };

    public int LaySoLuongTrongGioHang(List<GioHang> list) {
        int soluong = 0;
        for (GioHang gioHang : list) {
            soluong += gioHang.getSoluong();
        }
        return soluong;
    }
}
