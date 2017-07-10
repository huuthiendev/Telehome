package com.hhthien.luanvan.telehome.Requests;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hhthien.luanvan.telehome.Activities.MainActivity;
import com.hhthien.luanvan.telehome.Adapters.GioHangAdapter;
import com.hhthien.luanvan.telehome.Common.Constant;
import com.hhthien.luanvan.telehome.Models.GioHang;
import com.hhthien.luanvan.telehome.Models.HoaDon;
import com.hhthien.luanvan.telehome.R;
import com.roughike.bottombar.BottomBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HUUTHIEN on 7/1/2017.
 */

public class GioHangRequest {
    private static String TAG = "GioHangRequest";

    public static JsonArrayRequest DanhSachGioHang
            (final Context context, final GioHangAdapter adapterGH, final List<GioHang> listGioHang, final TextView tvTongTienGH, int mand) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.DANH_SACH_GIO_HANG + mand,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Số lượng sản phẩm trong giỏ hàng: " + response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listGioHang.add(new GioHang(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("mand"),
                                        jsonObject.getInt("masp"),
                                        jsonObject.getInt("soluong"),
                                        jsonObject.getInt("tongtien"),
                                        jsonObject.getString("tensp"),
                                        jsonObject.getInt("gia"),
                                        Constant.DIACHI_MAYCHU + jsonObject.getString("hinhsp")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapterGH.notifyDataSetChanged();
                        CapNhatTongTien(tvTongTienGH, listGioHang);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Lỗi: DanhSachGioHang " + error.getMessage());
            }
        });
        return request;
    }

    public static JsonArrayRequest ThemSanPhamGioHang (int mand, int masp, int soluong) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.THEM_PHAM_GIO_HANG + mand + "/" + masp + "/" + soluong, null, null);
        return request;
    }

    public static JsonArrayRequest CapNhatSanPhamGioHang (int magh, int soluong) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.CAP_NHAT_SAN_PHAM_GIO_HANG + magh + "/" + soluong, null, null);
        return request;
    }

    public static JsonArrayRequest XoaSanPhamGioHang (int magh) {
        JsonArrayRequest request = new JsonArrayRequest(Constant.DIACHI_MAYCHU + Constant.XOA_SAN_PHAM_GIO_HANG + magh, null, null);
        return request;
    }

    public static StringRequest TongSoLuong(final BottomBar bottomBar, int mand) {
        StringRequest request = new StringRequest(Constant.DIACHI_MAYCHU + Constant.TONG_SO_LUONG_SAN_PHAM_GIO_HANG + mand,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Tổng số lượng sản phẩm trong giỏ hàng: " + response);
                        bottomBar.getTabWithId(R.id.tab_giohang).setBadgeCount(Integer.parseInt(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Lỗi: TongSoLuong " + error.getMessage());
            }
        });
        return request;
    }

    private static void CapNhatTongTien(TextView tvTongTienGH, List<GioHang> listGioHang) {
        int tongtien = 0;
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        for (GioHang item : listGioHang) {
            tongtien += item.getTongtien();
        }
        tvTongTienGH.setText("Tổng tiền: " + formatter.format(tongtien) + " đ");
    }

    public static void ThanhToan(final Context context, final HoaDon hoadon) {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.DIACHI_MAYCHU + Constant.POST_THANH_TOAN_HOA_DON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Hóa đơn của bạn đã được ghi nhận", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi: ThanhToan " + error.getMessage());
                Toast.makeText(context, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mand", String.valueOf(hoadon.getMand()));
                params.put("tennguoinhan", hoadon.getTennguoinhan());
                params.put("sodt", hoadon.getSdt());
                params.put("diachi", hoadon.getDiachi());
                params.put("phuongthucthanhtoan", String.valueOf(hoadon.getChuyenkhoan()));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
