package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hhthien.luanvan.telehome.Adapters.ThuongHieuAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.ThuongHieu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/18/2017.
 */

public class ThuongHieuRequest {
    private static String TAG = "ThuongHieuRequest";

    public static JsonArrayRequest DanhSachThuongHieuTheoLoaiSanPham (final Context context, final ThuongHieuAdapter adapterTH,
                                                                      final List<ThuongHieu> listThuongHieu, int maloaisp) {
        JsonArrayRequest requestLoaiSP = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_THUONG_HIEU_THEO_LOAI + maloaisp,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "S? lu?ng thuong hi?u theo lo?i s?n ph?m: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listThuongHieu.add(new ThuongHieu(jsonObject.getInt("id"),
                                                                  jsonObject.getString("tenth"),
                                                                  Constant.DIACHI_MAYCHU + jsonObject.getString("hinhth")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterTH.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "L?i: DanhSachThuongHieuTheoLoaiSanPham " + error.getMessage());
            }
        });
        return requestLoaiSP;
    }
}
