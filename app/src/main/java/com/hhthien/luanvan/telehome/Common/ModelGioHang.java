package com.hhthien.luanvan.telehome.Common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hhthien.luanvan.telehome.Models.GioHang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo S410p on 8/26/2016.
 */
public class ModelGioHang {
    SQLiteDatabase database;

    public void MoKetNoiSQL(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();
    }

    public boolean XoaSanPhamTrongGioHang(int masp){

        int kiemtra = database.delete(DataSanPham.TB_GIOHANG,DataSanPham.TB_GIOHANG_MASP + " = " + masp, null);
        if(kiemtra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean CapNhatSoLuongSanPhamGioHang(int masp, int soluong){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG, soluong);

        int id = database.update(DataSanPham.TB_GIOHANG, contentValues, DataSanPham.TB_GIOHANG_MASP + " = " + masp, null);
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean ThemGioHang(GioHang gioHang){
        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG + " WHERE " + DataSanPham.TB_GIOHANG_MASP + " = " + gioHang.getMasp();
        Cursor cursor = database.rawQuery(truyvan, null);
        long id = 0;

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
            CapNhatSoLuongSanPhamGioHang(gioHang.getMasp(), soluong + 1);
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataSanPham.TB_GIOHANG_MASP,gioHang.getMasp());
            contentValues.put(DataSanPham.TB_GIOHANG_TENSP,gioHang.getTensp());
            contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN,gioHang.getGia());
            contentValues.put(DataSanPham.TB_GIOHANG_HINHANH,gioHang.getHinhsp());
            contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,gioHang.getSoluong());
            id = database.insert(DataSanPham.TB_GIOHANG, null, contentValues);
        }

        if(id > 0){
            return true;
        }else{
            return false;
        }

    }

    public List<GioHang> LayDanhSachSanPhamTrongGioHang(){
        List<GioHang> list = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            String hinhanh = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));

            GioHang gioHang = new GioHang();
            gioHang.setMasp(masp);
            gioHang.setTensp(tensp);
            gioHang.setGia(giatien);
            gioHang.setHinhsp(hinhanh);
            gioHang.setSoluong(soluong);
            gioHang.setTongtien(soluong * giatien);

            list.add(gioHang);
            cursor.moveToNext();
        }

        return list;
    }
}
