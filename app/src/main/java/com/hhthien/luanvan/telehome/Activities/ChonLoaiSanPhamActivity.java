package com.hhthien.luanvan.telehome.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.ChonLoaiSanPhamAdapter;
import com.hhthien.luanvan.telehome.Common.PassDataChonLoaiSanPham;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.LoaiSanPhamRequest;

import java.util.ArrayList;
import java.util.List;

public class ChonLoaiSanPhamActivity extends AppCompatActivity implements PassDataChonLoaiSanPham {
    private RecyclerView rvChonLoaiSanPham;
    private Toolbar tbChonLoaiSanPham;
    private ChonLoaiSanPhamAdapter adapterLoaiSP;
    private List<LoaiSanPham> listLoaiSP;
    private RecyclerView.LayoutManager lmLoaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_san_pham);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rvChonLoaiSanPham = (RecyclerView) findViewById(R.id.rvChonLoaiSanPham);
        tbChonLoaiSanPham = (Toolbar) findViewById(R.id.tbChonLoaiSanPham);

        tbChonLoaiSanPham.setTitle("Chọn loại sản phẩm");
        setSupportActionBar(tbChonLoaiSanPham);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listLoaiSP = new ArrayList<>();
        lmLoaiSanPham = new LinearLayoutManager(ChonLoaiSanPhamActivity.this);
        adapterLoaiSP = new ChonLoaiSanPhamAdapter(this, listLoaiSP, this);

        rvChonLoaiSanPham.setLayoutManager(lmLoaiSanPham);
        rvChonLoaiSanPham.setAdapter(adapterLoaiSP);

        Volley.newRequestQueue(this).add(LoaiSanPhamRequest.DanhSachChonLoaiSanPham(this, adapterLoaiSP, listLoaiSP));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void sendLoaiSanPham(int maloaisp, String tenloaisp) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("maloaisp", maloaisp);
        returnIntent.putExtra("tenloaisp", tenloaisp);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
