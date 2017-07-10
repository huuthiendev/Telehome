package com.hhthien.luanvan.telehome.Models;

/**
 * Created by HUUTHIEN on 7/1/2017.
 */

public class GioHang {
    private int id;
    private int mand;
    private int masp;
    private int soluong;
    private int tongtien;
    private String tensp;
    private int gia;
    private String hinhsp;

    public GioHang() {}

    public GioHang(int id, int mand, int masp, int soluong, int tongtien, String tensp, int gia, String hinhsp) {
        this.id = id;
        this.mand = mand;
        this.masp = masp;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.tensp = tensp;
        this.gia = gia;
        this.hinhsp = hinhsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
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

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }
}
