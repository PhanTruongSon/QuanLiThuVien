/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Think Pad
 */
public class Model_ChiTietNhapSach {
    private String MaNhap;
    private String MaSach,TenSach;
    private String SoLuong;

    public Model_ChiTietNhapSach() {
    }

    public Model_ChiTietNhapSach(String MaNhap, String MaSach, String TenSach, String SoLuong) {
        this.MaNhap = MaNhap;
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.SoLuong = SoLuong;
    }

    public String getMaNhap() {
        return MaNhap;
    }

    public void setMaNhap(String MaNhap) {
        this.MaNhap = MaNhap;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

   
}
