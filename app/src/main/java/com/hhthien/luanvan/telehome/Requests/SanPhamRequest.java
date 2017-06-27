package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.hhthien.luanvan.telehome.Adapters.SanPhamAdapter;
import com.hhthien.luanvan.telehome.Adapters.SlideSanPhamAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by HUUTHIEN on 6/11/2017.
 */

public class SanPhamRequest {
    public static JsonArrayRequest DanhSachSanPhamMoi
            (final Context context, final SanPhamAdapter adapterSP, final List<SanPham> listSanPhamMoi) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_MOI,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPhamMoi.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getString("mota"),
                                        jsonObject.getInt("maloaisp"),
                                        jsonObject.getInt("math"),
                                        jsonObject.getInt("soluong"),
                                        jsonObject.getInt("luotmua"),
                                        Constant.DIACHI_MAYCHU + jsonObject.getString("hinhsp")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public static JsonArrayRequest DanhSachSanPhamTheoLoai
            (final Context context, final SanPhamAdapter adapterSP, final List<SanPham> listSanPham, int maloaisp) {
        JsonArrayRequest request = new JsonArrayRequest(
                Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_THEO_LOAI + maloaisp,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPham.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
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
                        adapterSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public static JsonArrayRequest DanhSachSanPhamTheoLoaiTheoThuongHieuTheoGia
            (final Context context, final SanPhamAdapter adapterSP, final List<SanPham> listSanPham, int maloaisp, int math,
             int giabd, int giakt) {
        JsonArrayRequest request = new JsonArrayRequest(
                Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_THEO_LOAI_THUONG_HIEU_GIA + maloaisp + "/" + math +
                        "/" + giabd + "/" + giakt,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listSanPham.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPham.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
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
                        adapterSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public static JsonArrayRequest DanhSachSanPhamTheoLoaiTheoThuongHieu
            (final Context context, final SanPhamAdapter adapterSP, final List<SanPham> listSanPham, int maloaisp, int math) {
        JsonArrayRequest request = new JsonArrayRequest(
                Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_THEO_LOAI_THUONG_HIEU + maloaisp + "/" + math,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listSanPham.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPham.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
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
                        adapterSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public static JsonArrayRequest DanhSachSanPhamTheoLoaiTheoGia
            (final Context context, final SanPhamAdapter adapterSP, final List<SanPham> listSanPham, int maloaisp,
             int giabd, int giakt) {
        JsonArrayRequest request = new JsonArrayRequest(
                Constant.DIACHI_MAYCHU + Constant.DANHSACH_SAN_PHAM_THEO_LOAI_GIA + maloaisp +
                        "/" + giabd + "/" + giakt,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listSanPham.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listSanPham.add(new SanPham(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
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
                        adapterSP.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }

    public static JsonArrayRequest DanhSachHinhSanPham
            (final Context context, final SlideSanPhamAdapter adapter, final List<String> listHinh, int masp) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANHSACH_HINH_SAN_PHAM + masp,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listHinh.add(Constant.DIACHI_MAYCHU + jsonObject.getString("duongdan"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return request;
    }
}
