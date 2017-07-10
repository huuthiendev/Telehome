package com.hhthien.luanvan.telehome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhthien.luanvan.telehome.Activities.MainActivity;
import com.hhthien.luanvan.telehome.R;

/**
 * Created by HUUTHIEN on 6/4/2017.
 */

public class TaiKhoanFragment extends Fragment implements View.OnClickListener {
    TextView tvTenNguoiDung;
    Toolbar tbTaiKhoan;
    LinearLayout linearDangXuat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tai_khoan, container, false);
        anhXa(view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennd = sharedPreferences.getString("tennd", "");
        tvTenNguoiDung.setText(tennd);
        tbTaiKhoan.setTitle("Qu?n lý tài kho?n");

        return view;
    }

    private void anhXa(View view) {
        tvTenNguoiDung = (TextView) view.findViewById(R.id.tvTenNguoiDung);
        tbTaiKhoan = (Toolbar) view.findViewById(R.id.tbTaiKhoan);
        linearDangXuat = (LinearLayout) view.findViewById(R.id.linearDangXuat);
        linearDangXuat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearDangXuat:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
        }
    }
}
