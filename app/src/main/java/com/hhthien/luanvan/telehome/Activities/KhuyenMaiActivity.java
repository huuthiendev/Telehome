package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.SanPhamKhuyenMaiAdapter;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.KhuyenMaiRequest;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiActivity extends AppCompatActivity {
    private RecyclerView rvKhuyenMai;
    private Toolbar tbKhuyenMai;
    private int makm;
    private List<SanPham> listSanPhamKM;
    private SanPhamKhuyenMaiAdapter adapterSPKM;
    private RecyclerView.LayoutManager lmSanPhamKM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khuyen_mai);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        makm = intent.getIntExtra("makm", 0);
        anhXa();

        tbKhuyenMai.setTitle("Chi tiết khuyến mãi");
        setSupportActionBar(tbKhuyenMai);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);

        Volley.newRequestQueue(this).add(KhuyenMaiRequest.DanhSachSanPhamKhuyenMai(this, adapterSPKM, listSanPhamKM, makm));
    }

    private void anhXa() {
        rvKhuyenMai = (RecyclerView) findViewById(R.id.rvKhuyenMai);
        tbKhuyenMai = (Toolbar) findViewById(R.id.tbKhuyenMai);
        listSanPhamKM = new ArrayList<>();

        adapterSPKM = new SanPhamKhuyenMaiAdapter(KhuyenMaiActivity.this, listSanPhamKM);
        rvKhuyenMai.setAdapter(adapterSPKM);
        lmSanPhamKM = new LinearLayoutManager(KhuyenMaiActivity.this);
        rvKhuyenMai.setLayoutManager(lmSanPhamKM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(KhuyenMaiActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(KhuyenMaiActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
