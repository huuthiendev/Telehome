package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hhthien.luanvan.telehome.Adapters.ChonLoaiSanPhamAdapter;
import com.hhthien.luanvan.telehome.Adapters.LoaiSanPhamAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/10/2017.
 */

public class LoaiSanPhamRequest {
    private static String TAG = "LoaiSanPhamRequest";

    public static JsonArrayRequest DanhSachLoaiSanPham(final Context context, final LoaiSanPhamAdapter adapterLoaiSP, final List<LoaiSanPham> listLoaiSP) {
        JsonArrayRequest requestLoaiSP = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_LOAI_SAN_PHAM,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "S? lu?ng lo?i s?n ph?m: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listLoaiSP.add(new LoaiSanPham(jsonObject.getInt("id"),
                                        jsonObject.getString("tenloaisp"),
                                        Constant.DIACHI_MAYCHU + jsonObject.getString("hinhloaisp")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterLoaiSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "L?i: DanhSachLoaiSanPham " + error.getMessage());
            }
        });
        return requestLoaiSP;
    }

    public static JsonArrayRequest DanhSachChonLoaiSanPham(final Context context, final ChonLoaiSanPhamAdapter adapterLoaiSP, final List<LoaiSanPham> listLoaiSP) {
        JsonArrayRequest requestLoaiSP = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_LOAI_SAN_PHAM,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "S? lu?ng lo?i s?n ph?m: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listLoaiSP.add(new LoaiSanPham(jsonObject.getInt("id"),
                                        jsonObject.getString("tenloaisp"),
                                        Constant.DIACHI_MAYCHU + jsonObject.getString("hinhloaisp")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterLoaiSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "L?i: DanhSachLoaiSanPham " + error.getMessage());
            }
        });
        return requestLoaiSP;
    }
}
