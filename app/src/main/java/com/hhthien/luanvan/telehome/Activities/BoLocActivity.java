package com.hhthien.luanvan.telehome.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.ThuongHieuAdapter;
import com.hhthien.luanvan.telehome.Common.NumberTextWatcher;
import com.hhthien.luanvan.telehome.Common.PassDataThuongHieu;
import com.hhthien.luanvan.telehome.Models.ThuongHieu;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.ThuongHieuRequest;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BoLocActivity extends AppCompatActivity implements PassDataThuongHieu, View.OnClickListener {
    private Toolbar tbBoLoc;
    private TextView tvThuongHieu;
    private Button btnApDung;
    private EditText edtGiaBD, edtGiaKT;
    private RecyclerView rvThuongHieu;
    private ThuongHieuAdapter adapterTH;
    private RecyclerView.LayoutManager lmThuongHieu;
    private List<ThuongHieu> listThuongHieu;
    private int maLoai, maTH;
    private String TAG = "BoLocActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_loc);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();

        Intent intent = getIntent();
        maLoai = intent.getIntExtra("maloaisp", 0);
        Log.d(TAG, "Mã loại sản phẩm: " + maLoai);

        tbBoLoc.setTitle("Bộ lọc");
        setSupportActionBar(tbBoLoc);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapterTH = new ThuongHieuAdapter(this, listThuongHieu, this);
        rvThuongHieu.setAdapter(adapterTH);
        lmThuongHieu = new GridLayoutManager(BoLocActivity.this, 3);
        rvThuongHieu.setLayoutManager(lmThuongHieu);

        Volley.newRequestQueue(this).add(ThuongHieuRequest.DanhSachThuongHieuTheoLoaiSanPham(this, adapterTH, listThuongHieu, maLoai));
    }

    private void anhXa() {
        btnApDung = (Button) findViewById(R.id.btnApDung);
        rvThuongHieu = (RecyclerView) findViewById(R.id.rvThuongHieu);
        tbBoLoc = (Toolbar) findViewById(R.id.tbBoLoc);
        tvThuongHieu = (TextView) findViewById(R.id.tvThuongHieu);
        btnApDung = (Button) findViewById(R.id.btnApDung);
        btnApDung.setOnClickListener(this);
        listThuongHieu = new ArrayList<>();
        edtGiaBD = (EditText) findViewById(R.id.edtGiaBD);
        edtGiaKT = (EditText) findViewById(R.id.edtGiaKT);
        edtGiaBD.addTextChangedListener(new NumberTextWatcher(edtGiaBD));
        edtGiaKT.addTextChangedListener(new NumberTextWatcher(edtGiaKT));
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
    public void sendTH(int math, String tenth) {
        maTH = math;
        tvThuongHieu.setText(tenth);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnApDung:
                Intent returnIntent = new Intent();
                returnIntent.putExtra("math", maTH);
                DecimalFormat df = new DecimalFormat("#,###");
                int giabd = 0, giakt = 0;
                String chuoibd = edtGiaBD.getText().toString(),
                        chuoikt = edtGiaKT.getText().toString();
                try {
                    if (!chuoibd.equals("") && !chuoikt.equals("")) {
                        giabd = df.parse(chuoibd).intValue();
                        giakt = df.parse(chuoikt).intValue();
                    }
                    else if (!chuoibd.equals("")) {
                        giabd = df.parse(chuoibd).intValue();
                    }
                    else if (!chuoikt.equals("")) {
                        giakt = df.parse(chuoikt).intValue();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                returnIntent.putExtra("giabd", giabd);
                returnIntent.putExtra("giakt", giakt);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
        }
    }
}