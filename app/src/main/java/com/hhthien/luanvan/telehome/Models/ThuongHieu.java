package com.hhthien.luanvan.telehome.Models;

/**
 * Created by HUUTHIEN on 6/18/2017.
 */

public class ThuongHieu {
    private int id;
    private String tenth;
    private String hinhth;

    public ThuongHieu(int id, String tenth, String hinhth) {
        this.id = id;
        this.tenth = tenth;
        this.hinhth = hinhth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public String getHinhth() {
        return hinhth;
    }

    public void setHinhth(String hinhth) {
        this.hinhth = hinhth;
    }
}
