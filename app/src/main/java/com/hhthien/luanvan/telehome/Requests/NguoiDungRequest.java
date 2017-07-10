package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Activities.MainActivity;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.NguoiDung;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HUUTHIEN on 6/27/2017.
 */

public class NguoiDungRequest {
    private static String TAG = "NguoiDungRequest";

    public static void KiemTraDangNhap(final Context context, final String email, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.DIACHI_MAYCHU + Constant.POST_NGUOI_DUNG_DANG_NHAP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Kết quả: " + response);
                        try {
                            if (response.equalsIgnoreCase("thatbai")){
                                Log.d(TAG, "ĐĂNG NHẬP THẤT BẠI");
                            } else {
                                JSONObject object = new JSONObject(response);
                                SharedPreferences sharedPreferences = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("id", object.getInt("id"));
                                editor.putString("tennd", object.getString("tennd"));
                                editor.commit();

                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);

                                Log.d(TAG, "ĐĂNG NHẬP THÀNH CÔNG");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi: NguoiDungDangNhap " + error.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public static StringRequest ThongTinNguoiDung
            (final NguoiDung nguoidung, int mand) {
        StringRequest request = new StringRequest(Constant.DIACHI_MAYCHU + Constant.THONG_TIN_NGUOI_DUNG + mand,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            nguoidung.setTennd(jsonObject.getString("tennd"));
                            nguoidung.setEmail(jsonObject.getString("email"));
                            nguoidung.setDiachi(jsonObject.getString("diachi"));
                            nguoidung.setNgaysinh(jsonObject.getString("ngaysinh"));
                            nguoidung.setSodt(jsonObject.getString("sdt"));
                            nguoidung.setGioitinh(jsonObject.getInt("gioitinh"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi: ThongTinNguoiDung " + error.getMessage());
            }
        });
        return request;
    }

    public static void ThongTinNguoiDungThanhToan(Context context, final EditText edtTenND, final EditText edtSoDT, final EditText edtDiaChiND, int mand) {
        StringRequest request = new StringRequest(Constant.DIACHI_MAYCHU + Constant.THONG_TIN_NGUOI_DUNG + mand,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String tennd = jsonObject.getString("tennd");
                            String sodt = jsonObject.getString("sdt");
                            String diachi = jsonObject.getString("diachi");
                            if (tennd != null) {
                                edtTenND.setText(tennd);
                                if (sodt != "null") {
                                    edtSoDT.setText(sodt);
                                }
                                if (diachi != "null") {
                                    edtDiaChiND.setText(diachi);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi: ThongTinNguoiDung " + error.getMessage());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}
