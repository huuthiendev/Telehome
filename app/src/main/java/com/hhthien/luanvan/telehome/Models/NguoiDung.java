package com.hhthien.luanvan.telehome.Models;

/**
 * Created by HUUTHIEN on 7/5/2017.
 */

public class NguoiDung {
    private int id;
    private String tennd;
    private String email;
    private String diachi;
    private String sodt;
    private String ngaysinh;
    private int gioitinh;

    public NguoiDung() {}

    public NguoiDung(int id, String tennd, String email, String diachi, String sodt, String ngaysinh, int gioitinh) {
        this.id = id;
        this.tennd = tennd;
        this.email = email;
        this.diachi = diachi;
        this.sodt = sodt;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennd() {
        return tennd;
    }

    public void setTennd(String tennd) {
        this.tennd = tennd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
