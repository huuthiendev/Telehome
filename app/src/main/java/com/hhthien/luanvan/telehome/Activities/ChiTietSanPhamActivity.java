package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.SlideSanPhamAdapter;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.SanPhamRequest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar tbChiTietSanPham;
    private TextView tvTenSanPham, tvGia;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private SlideSanPhamAdapter adapterSP;
    private List<String> listHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();
        setSupportActionBar(tbChiTietSanPham);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        SanPham sanpham = (SanPham) intent.getSerializableExtra("sanpham");
        adapterSP = new SlideSanPhamAdapter(this, listHinh);

        tvTenSanPham.setText(sanpham.getTensp());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        tvGia.setText(formatter.format(sanpham.getGia()) + " VNĐ");

        mPager.setAdapter(adapterSP);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Tự động chạy viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == listHinh.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        Volley.newRequestQueue(this).add(SanPhamRequest.DanhSachHinhSanPham(this, adapterSP, listHinh, sanpham.getId()));
    }

    private void anhXa() {
        tbChiTietSanPham = (Toolbar) findViewById(R.id.tbChiTietSanPham);
        mPager = (ViewPager) findViewById(R.id.pager);
        listHinh = new ArrayList<>();
        tvTenSanPham = (TextView) findViewById(R.id.tvTenSanPham);
        tvGia = (TextView) findViewById(R.id.tvGia);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {

    }
}
