package com.hhthien.luanvan.telehome.Models;

import java.io.Serializable;

/**
 * Created by HUUTHIEN on 6/7/2017.
 */

public class SanPham implements Serializable {
    private int id;
    private String tensp;
    private int gia;
    private int giagoc;
    private int phantramkm;
    private String mota;
    private int maloai;
    private int math;
    private int soluong;
    private int luotmua;
    private String hinhsp;
    private long ngaydang;

    public SanPham() {}

    public SanPham(int id, String tensp, int gia, String mota, int maloai, int math, int soluong, int luotmua, String hinhsp) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.mota = mota;
        this.maloai = maloai;
        this.math = math;
        this.soluong = soluong;
        this.luotmua = luotmua;
        this.hinhsp = hinhsp;
    }

    public SanPham(int id, String tensp, int gia, String mota, int maloai, int math, int soluong, int luotmua, String hinhsp, long ngaydang) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.mota = mota;
        this.maloai = maloai;
        this.math = math;
        this.soluong = soluong;
        this.luotmua = luotmua;
        this.hinhsp = hinhsp;
        this.ngaydang = ngaydang;
    }

    public SanPham(int id, String tensp, int gia, int giagoc, int phantramkm, String mota, int maloai, int math, int soluong, int luotmua, String hinhsp, long ngaydang) {
        this.id = id;
        this.tensp = tensp;
        this.gia = gia;
        this.giagoc = giagoc;
        this.phantramkm = phantramkm;
        this.mota = mota;
        this.maloai = maloai;
        this.math = math;
        this.soluong = soluong;
        this.luotmua = luotmua;
        this.hinhsp = hinhsp;
        this.ngaydang = ngaydang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getLuotmua() {
        return luotmua;
    }

    public void setLuotmua(int luotmua) {
        this.luotmua = luotmua;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public long getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(long ngaydang) {
        this.ngaydang = ngaydang;
    }

    public int getGiagoc() {
        return giagoc;
    }

    public void setGiagoc(int giagoc) {
        this.giagoc = giagoc;
    }

    public int getPhantramkm() {
        return phantramkm;
    }

    public void setPhantramkm(int phantramkm) {
        this.phantramkm = phantramkm;
    }
}
