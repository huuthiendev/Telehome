package com.hhthien.luanvan.telehome.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Adapters.LoaiSanPhamAdapter;
import com.hhthien.luanvan.telehome.Adapters.SanPhamAdapter;
import com.hhthien.luanvan.telehome.Adapters.SlideKhuyenMaiAdapter;
import com.hhthien.luanvan.telehome.Models.KhuyenMai;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;
import com.hhthien.luanvan.telehome.Models.SanPham;
import com.hhthien.luanvan.telehome.R;
import com.hhthien.luanvan.telehome.Requests.KhuyenMaiRequest;
import com.hhthien.luanvan.telehome.Requests.LoaiSanPhamRequest;
import com.hhthien.luanvan.telehome.Requests.SanPhamRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by HUUTHIEN on 6/4/2017.
 */

public class TrangChuFragment extends Fragment {

    private List<LoaiSanPham> listLoaiSP;
    private List<KhuyenMai> listKhuyenMai;
    private List<SanPham> listSanPhamMoi;
    private LoaiSanPhamAdapter adapterLoaiSP;
    private SlideKhuyenMaiAdapter adapterKM;
    private SanPhamAdapter adapterSP;
    private RecyclerView rvLoaiSanPham;
    private RecyclerView.LayoutManager lmLoaiSanPham;
    private RecyclerView rvSanPhamMoi;
    private RecyclerView.LayoutManager lmSanPhamMoi;
    private static ViewPager mPager;
    private static int currentPage = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listKhuyenMai = new ArrayList<>();
        adapterKM = new SlideKhuyenMaiAdapter(getActivity(), listKhuyenMai);

        listLoaiSP = new ArrayList<>();
        lmLoaiSanPham = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adapterLoaiSP = new LoaiSanPhamAdapter(getActivity(), listLoaiSP);

        listSanPhamMoi = new ArrayList<>();
        lmSanPhamMoi = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adapterSP = new SanPhamAdapter(getActivity(), listSanPhamMoi, R.layout.item_san_pham);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_trang_chu, container, false);
        rvLoaiSanPham = (RecyclerView) view.findViewById(R.id.rvLoaiSanPham);
        rvSanPhamMoi = (RecyclerView) view.findViewById(R.id.rvSanPhamMoi);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(adapterKM);

        rvLoaiSanPham.setLayoutManager(lmLoaiSanPham);
        rvLoaiSanPham.setAdapter(adapterLoaiSP);

        rvSanPhamMoi.setLayoutManager(lmSanPhamMoi);
        rvSanPhamMoi.setAdapter(adapterSP);

        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Tự động chạy viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == listKhuyenMai.size()) {
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

        // Request Volley
        Volley.newRequestQueue(getActivity()).add(KhuyenMaiRequest.DanhSachKhuyenMai(getActivity(), adapterKM, listKhuyenMai));
        Volley.newRequestQueue(getActivity()).add(LoaiSanPhamRequest.DanhSachLoaiSanPham(getActivity(), adapterLoaiSP, listLoaiSP));
        Volley.newRequestQueue(getActivity()).add(SanPhamRequest.DanhSachSanPhamMoi(getActivity(), adapterSP, listSanPhamMoi));

        return view;
    }
}
