package com.hhthien.luanvan.telehome.Models;

/**
 * Created by HUUTHIEN on 6/9/2017.
 */

public class KhuyenMai {
    private int id;
    private String tenkm;
    private String ngaybd;
    private String ngaykt;
    private String hinhkm;

    public KhuyenMai() {}

    public KhuyenMai(String hinhkm) {
        this.hinhkm = hinhkm;
    }

    public KhuyenMai(int id, String tenkm, String ngaybd, String ngaykt, String hinhkm) {
        this.id = id;
        this.tenkm = tenkm;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.hinhkm = hinhkm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public String getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(String ngaybd) {
        this.ngaybd = ngaybd;
    }

    public String getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(String ngaykt) {
        this.ngaykt = ngaykt;
    }

    public String getHinhkm() {
        return hinhkm;
    }

    public void setHinhkm(String hinhkm) {
        this.hinhkm = hinhkm;
    }
}
