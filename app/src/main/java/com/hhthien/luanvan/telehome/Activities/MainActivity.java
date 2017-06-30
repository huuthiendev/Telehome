package com.hhthien.luanvan.telehome.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.hhthien.luanvan.telehome.Fragments.QuanTamFragment;
import com.hhthien.luanvan.telehome.Fragments.TaiKhoanFragment;
import com.hhthien.luanvan.telehome.Fragments.TrangChuFragment;
import com.hhthien.luanvan.telehome.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setBadgeBackgroundColor(getResources().getColor(R.color.colorXam));
        bottomBar.getTabWithId(R.id.tab_giohang).setBadgeCount(5);
        bottomBar.setOnTabSelectListener(tabSelect);
    }

    OnTabSelectListener tabSelect = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            if (tabId == R.id.tab_trangchu) {
                TrangChuFragment fTrangChu = new TrangChuFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fTrangChu).commit();
            }
            else if (tabId == R.id.tab_timkiem) {
                Toast.makeText(MainActivity.this, "Tìm kiếm", Toast.LENGTH_SHORT).show();
            }
            else if (tabId == R.id.tab_giohang) {
                Toast.makeText(MainActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
            }
            else if (tabId == R.id.tab_quantam) {
                QuanTamFragment fQuanTam = new QuanTamFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fQuanTam).commit();
            }
            else if (tabId == R.id.tab_taikhoan) {
                SharedPreferences sharedPreferences = getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                String tennd = sharedPreferences.getString("tennd", "");
                if (tennd.isEmpty()) {
                    Intent intentDangNhap = new Intent(MainActivity.this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                } else {
                    TaiKhoanFragment fTaiKhoan = new TaiKhoanFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameContent, fTaiKhoan).commit();
                }
            }
        }
    };
}
