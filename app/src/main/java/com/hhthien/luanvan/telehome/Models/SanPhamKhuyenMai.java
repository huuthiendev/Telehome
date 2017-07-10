package com.hhthien.luanvan.telehome.Models;

import java.io.Serializable;

/**
 * Created by HUUTHIEN on 7/7/2017.
 */

public class SanPhamKhuyenMai implements Serializable {
    private int makm;
    private int masp;
    private String tensp;
    private int giasp;
    private int giagoc;
    private int phantramkm;
    private String hinhsp;

    public SanPhamKhuyenMai(int makm, int masp, String tensp, int giasp, int giagoc, int phantramkm, String hinhsp) {
        this.makm = makm;
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.giagoc = giagoc;
        this.phantramkm = phantramkm;
        this.hinhsp = hinhsp;
    }

    public int getMakm() {
        return makm;
    }

    public void setMakm(int makm) {
        this.makm = makm;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
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

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }
}
