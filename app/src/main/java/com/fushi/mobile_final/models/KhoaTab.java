package com.fushi.mobile_final.models;

public class KhoaTab {
    private String maKhoa;
    private String tenKhoa;
    private String moTa;

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        moTa = moTa;
    }

    @Override
    public String toString()  {
        return this.getTenKhoa();
    }
}
