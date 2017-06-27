package com.hhthien.luanvan.telehome.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhthien.luanvan.telehome.R;

/**
 * Created by HUUTHIEN on 6/4/2017.
 */

public class QuanTamFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_yeu_thich, container, false);
        return view;
    }
}
