package com.hhthien.luanvan.telehome.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.SanPhamAdapter;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.SanPhamRequest;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar tbTimKiem;
    private RecyclerView rvTimKiem;
    private List<SanPham> listSanPham;
    private SanPhamAdapter adapterSP;
    private RecyclerView.LayoutManager lmSanPham;
    private String TAG = "TimKiemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tbTimKiem = (Toolbar) findViewById(R.id.tbTimKiem);
        rvTimKiem = (RecyclerView) findViewById(R.id.rvTimKiem);
        listSanPham = new ArrayList<>();

        setSupportActionBar(tbTimKiem);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);

        adapterSP = new SanPhamAdapter(TimKiemActivity.this, listSanPham, R.layout.item_loai_san_pham_sanpham_list);
        rvTimKiem.setAdapter(adapterSP);
        lmSanPham = new LinearLayoutManager(TimKiemActivity.this);
        rvTimKiem.setLayoutManager(lmSanPham);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(TimKiemActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TimKiemActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tim_kiem, menu);

        MenuItem itTimKiem = menu.findItem(R.id.itTimKiem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itTimKiem);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(TAG, "Text submit: " + query);
        Volley.newRequestQueue(this).add(SanPhamRequest.DanhSachSanPhamTimKiem(this, adapterSP, listSanPham, query));
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d(TAG, "Text change: " + newText);
        Volley.newRequestQueue(this).add(SanPhamRequest.DanhSachSanPhamTimKiem(this, adapterSP, listSanPham, newText));
        return false;
    }
}
