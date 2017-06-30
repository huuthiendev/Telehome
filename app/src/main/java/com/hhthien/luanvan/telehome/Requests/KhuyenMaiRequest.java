package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hhthien.luanvan.telehome.Adapters.SlideKhuyenMaiAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.KhuyenMai;

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
                                listKhuyenMai.add(new KhuyenMai(Constant.DIACHI_MAYCHU + jsonObject.getString("hinhkm")));
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
}
