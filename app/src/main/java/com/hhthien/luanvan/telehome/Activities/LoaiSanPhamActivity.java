package com.hhthien.luanvan.telehome.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.SanPhamAdapter;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.SanPhamRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LoaiSanPhamActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvSanPhamTheoLoai;
    private List<SanPham> listSanPham;
    private SanPhamAdapter adapterSP;
    private RecyclerView.LayoutManager lmLoaiSanPham;
    private Toolbar tbSanPhamTheoLoai;
    private Button btnCheDoXem, btnSapXep, btnBoLoc;
    private Boolean flagCheDoXem = false;
    private int maLoai;
    private int KET_QUA = 3777;
    private String TAG = "LoaiSanPhamActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();

        Intent intent = getIntent();
        maLoai = intent.getIntExtra("maloaisp", 0);
        Log.d(TAG, "Mã loại sản phẩm: " + maLoai);

        String tenloaisp = intent.getStringExtra("tenloaisp");
        tbSanPhamTheoLoai.setTitle(tenloaisp);
        setSupportActionBar(tbSanPhamTheoLoai);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapterSP = new SanPhamAdapter(this, listSanPham, R.layout.item_loai_san_pham_sanpham_grid);
        rvSanPhamTheoLoai.setAdapter(adapterSP);
        lmLoaiSanPham = new GridLayoutManager(LoaiSanPhamActivity.this, 2);
        rvSanPhamTheoLoai.setLayoutManager(lmLoaiSanPham);

        Volley.newRequestQueue(this).add(SanPhamRequest.DanhSachSanPhamTheoLoai(this, adapterSP, listSanPham, maLoai));
    }

    private void anhXa() {
        rvSanPhamTheoLoai = (RecyclerView) findViewById(R.id.rvSanPhamTheoLoai);
        tbSanPhamTheoLoai = (Toolbar) findViewById(R.id.tbSanPhamTheoLoai);
        btnCheDoXem = (Button) findViewById(R.id.btnCheDoXem);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnBoLoc = (Button) findViewById(R.id.btnBoLoc);
        btnCheDoXem.setOnClickListener(this);
        btnSapXep.setOnClickListener(this);
        btnBoLoc.setOnClickListener(this);
        listSanPham = new ArrayList<>();
    }

    private void showPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnSapXep);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.giacaodenthap:
                        Collections.sort(listSanPham, sapXepCaoDenThap);
                        adapterSP.notifyDataSetChanged();
                        break;
                    case R.id.giathapdencao:
                        Collections.sort(listSanPham, sapXepThapDenCao);
                        adapterSP.notifyDataSetChanged();
                        break;
                    case R.id.banchaynhat:
                        Collections.sort(listSanPham, sapXepBanChayNhat);
                        adapterSP.notifyDataSetChanged();
                        break;
                    case R.id.moinhat:
                        Collections.sort(listSanPham, sapXepMoiNhat);
                        adapterSP.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(LoaiSanPhamActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoaiSanPhamActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public static Comparator<SanPham> sapXepThapDenCao = new Comparator<SanPham>() {

        @Override
        public int compare(SanPham s1, SanPham s2) {
            return s1.getGia() - s2.getGia();
        }
    };

    public static Comparator<SanPham> sapXepCaoDenThap= new Comparator<SanPham>() {

        @Override
        public int compare(SanPham s1, SanPham s2) {
            return s2.getGia() - s1.getGia();
        }
    };

    public static Comparator<SanPham> sapXepBanChayNhat = new Comparator<SanPham>() {
        @Override
        public int compare(SanPham s1, SanPham s2) {
            return s2.getLuotmua() - s1.getLuotmua();
        }
    };

    public static Comparator<SanPham> sapXepMoiNhat = new Comparator<SanPham>() {
        @Override
        public int compare(SanPham s1, SanPham s2) {
            return (int) (s2.getNgaydang() - s1.getNgaydang());
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheDoXem:
                if (flagCheDoXem) {
                    Log.d(TAG, "Chế độ xem: lưới");
                    adapterSP = new SanPhamAdapter(LoaiSanPhamActivity.this, listSanPham, R.layout.item_loai_san_pham_sanpham_grid);
                    rvSanPhamTheoLoai.setAdapter(adapterSP);
                    lmLoaiSanPham = new GridLayoutManager(LoaiSanPhamActivity.this, 2);
                    rvSanPhamTheoLoai.setLayoutManager(lmLoaiSanPham);
                    flagCheDoXem = false;
                }
                else {
                    Log.d(TAG, "Chế độ xem: danh sách");
                    adapterSP = new SanPhamAdapter(LoaiSanPhamActivity.this, listSanPham, R.layout.item_loai_san_pham_sanpham_list);
                    rvSanPhamTheoLoai.setAdapter(adapterSP);
                    lmLoaiSanPham = new LinearLayoutManager(LoaiSanPhamActivity.this);
                    rvSanPhamTheoLoai.setLayoutManager(lmLoaiSanPham);
                    flagCheDoXem = true;
                }
                break;
            case R.id.btnSapXep:
                showPopupMenu();
                break;
            case R.id.btnBoLoc:
                Intent intent = new Intent(LoaiSanPhamActivity.this, BoLocActivity.class);
                intent.putExtra("maloaisp", maLoai);
                startActivityForResult(intent, KET_QUA);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KET_QUA) {
            if(resultCode == Activity.RESULT_OK){
                int math = data.getIntExtra("math", 0);
                int giabd = data.getIntExtra("giabd", 0);
                int giakt = data.getIntExtra("giakt", 0);
                if (math != 0 && giabd != 0 && giakt != 0) {
                    Volley.newRequestQueue(this).add(
                            SanPhamRequest.DanhSachSanPhamTheoLoaiTheoThuongHieuTheoGia(
                                    this, adapterSP, listSanPham, maLoai, math, giabd, giakt));
                } else if (math != 0 && giabd == 0 && giakt == 0) {
                    Volley.newRequestQueue(this).add(
                            SanPhamRequest.DanhSachSanPhamTheoLoaiTheoThuongHieu(
                                    this, adapterSP, listSanPham, maLoai, math));
                } else if (math == 0 && giabd != 0 && giakt != 0) {
                    Volley.newRequestQueue(this).add(
                            SanPhamRequest.DanhSachSanPhamTheoLoaiTheoGia(
                                    this, adapterSP, listSanPham, maLoai, giabd, giakt));
                }
                Log.d(TAG, math + " " + giabd + " " + giakt);
            }
        }
    }
}
