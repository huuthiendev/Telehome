package com.hhthien.luanvan.telehome.Models;

/**
 * Created by HUUTHIEN on 7/4/2017.
 */

public class HoaDon {
    private int id;
    private int mand;
    private String tennguoinhan;
    private String sdt;
    private String diachi;
    private int trangthai;
    private int tongtien;
    private String ngaygiao;
    private int chuyenkhoan;
    private int machuyenkhoan;
    private String ngaymua;

    public HoaDon(int mand, String tennguoinhan, String sdt, String diachi, int chuyenkhoan) {
        this.mand = mand;
        this.tennguoinhan = tennguoinhan;
        this.sdt = sdt;
        this.diachi = diachi;
        this.chuyenkhoan = chuyenkhoan;
    }

    public HoaDon(int id, int mand, String tennguoinhan, String sdt, String diachi, int trangthai,
                  int tongtien, String ngaygiao, int chuyenkhoan, int machuyenkhoan, String ngaymua) {
        this.id = id;
        this.mand = mand;
        this.tennguoinhan = tennguoinhan;
        this.sdt = sdt;
        this.diachi = diachi;
        this.trangthai = trangthai;
        this.tongtien = tongtien;
        this.ngaygiao = ngaygiao;
        this.chuyenkhoan = chuyenkhoan;
        this.machuyenkhoan = machuyenkhoan;
        this.ngaymua = ngaymua;
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

    public String getTennguoinhan() {
        return tennguoinhan;
    }

    public void setTennguoinhan(String tennguoinhan) {
        this.tennguoinhan = tennguoinhan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgaygiao() {
        return ngaygiao;
    }

    public void setNgaygiao(String ngaygiao) {
        this.ngaygiao = ngaygiao;
    }

    public int getChuyenkhoan() {return chuyenkhoan;}

    public void setChuyenkhoan(int chuyenkhoan) {
        this.chuyenkhoan = chuyenkhoan;
    }

    public int getMachuyenkhoan() {
        return machuyenkhoan;
    }

    public void setMachuyenkhoan(int machuyenkhoan) {
        this.machuyenkhoan = machuyenkhoan;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }
}
