package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hhthien.luanvan.telehome.Adapters.SanPhamKhuyenMaiAdapter;
import com.hhthien.luanvan.telehome.Adapters.SlideKhuyenMaiAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.KhuyenMai;
import com.hhthien.luanvan.telehome.Models.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/10/2017.
 */

public class KhuyenMaiRequest {
    private static String TAG = "KhuyenMaiRequest";

    public static JsonArrayRequest DanhSachKhuyenMai(final Context context, final SlideKhuyenMaiAdapter adapterKM, final List<KhuyenMai> listKhuyenMai) {
        JsonArrayRequest requestKM = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_KHUYEN_MAI,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d(TAG, "Hình khuy?n mãi: " + Constant.DIACHI_MAYCHU + jsonObject.getString("hinhkm"));
                                listKhuyenMai.add(new KhuyenMai(jsonObject.getInt("id"),
                                                                jsonObject.getString("tenkm"),
                                                                jsonObject.getString("ngaybd"),
                                                                jsonObject.getString("ngaykt"),
                                                                Constant.DIACHI_MAYCHU + jsonObject.getString("hinhkm")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterKM.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "L?i: DanhSachKhuyenMai " + error.getMessage());
            }
        });
        return requestKM;
    }

    public static JsonArrayRequest DanhSachSanPhamKhuyenMai
            (final Context context, final SanPhamKhuyenMaiAdapter adapterSPKM, final List<SanPham> listSanPhamKM, int makm) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_KHUYEN_MAI + makm,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "S? lu?ng s?n ph?m khuy?n mãi: " + response.length());
                        listSanPhamKM.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPhamKM.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getInt("giagoc"),
                                        jsonObject.getInt("phantramkm"),
                                        jsonObject.getString("mota"),
                                        jsonObject.getInt("maloaisp"),
                                        jsonObject.getInt("math"),
                                        jsonObject.getInt("soluong"),
                                        jsonObject.getInt("luotmua"),
                                        Constant.DIACHI_MAYCHU + jsonObject.getString("hinhsp"),
                                        jsonObject.getLong("ngaydang")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterSPKM.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "L?i: DanhSachSanPhamKhuyenMai " + error.getMessage());
            }
        });
        return request;
    }
}
