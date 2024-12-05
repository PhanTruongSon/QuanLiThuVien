/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Think Pad
 */
public class Model_NhapSach {
    private String MaNhap;
    private String MaNV,HoTen;
    private String NgayNhap;
    public Model_NhapSach() {
    }

    public Model_NhapSach(String MaNhap, String MaNV, String HoTen, String NgayNhap) {
        this.MaNhap = MaNhap;
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.NgayNhap = NgayNhap;
    }

    public String getMaNhap() {
        return MaNhap;
    }

    public void setMaNhap(String MaNhap) {
        this.MaNhap = MaNhap;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    
    
    
    

    
   
    
    
}
