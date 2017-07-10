package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Adapters.GioHangAdapter;
import com.hhthien.luanvan.telehome.Common.ModelGioHang;
import com.hhthien.luanvan.telehome.Models.GioHang;
import com.hhthien.luanvan.telehome.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    Toolbar tbGioHang;
    RecyclerView rvGioHang;
    TextView tvTongTienGH;
    Button btnThanhToanGH;
    private List<GioHang> listGioHang;
    private GioHangAdapter adapterGH;
    private RecyclerView.LayoutManager lmGioHang;
    private ModelGioHang modelGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gio_hang);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();
        setSupportActionBar(tbGioHang);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);
    }

    private void anhXa() {
        tvTongTienGH = (TextView) findViewById(R.id.tvTongTienGH);
        tbGioHang = (Toolbar) findViewById(R.id.tbGioHang);
        rvGioHang = (RecyclerView) findViewById(R.id.rvGioHang);
        btnThanhToanGH = (Button) findViewById(R.id.btnThanhToanGH);
        tbGioHang.setTitle("Giỏ hàng");

        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(this);
        listGioHang = new ArrayList<>();
        listGioHang = modelGioHang.LayDanhSachSanPhamTrongGioHang();

        lmGioHang = new LinearLayoutManager(GioHangActivity.this);
        adapterGH = new GioHangAdapter(this, listGioHang, tvTongTienGH);

        rvGioHang.setAdapter(adapterGH);
        rvGioHang.setLayoutManager(lmGioHang);

        btnThanhToanGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
