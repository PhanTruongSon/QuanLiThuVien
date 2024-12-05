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
public class Model_Sach {
    private String MaSach;
    private String TenSach;
    private String MaTL,TenTL;
    private String TacGia;
    private String MaNXB,TenNXB;
    private String NamXB;
    private int SoLuong;

    public Model_Sach() {
    }

    public Model_Sach(String MaSach, String TenSach, String MaTL, String TenTL, String TacGia, String MaNXB, String TenNXB, String NamXB, int SoLuong) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.MaTL = MaTL;
        this.TenTL = TenTL;
        this.TacGia = TacGia;
        this.MaNXB = MaNXB;
        this.TenNXB = TenNXB;
        this.NamXB = NamXB;
        this.SoLuong = SoLuong;
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

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String MaTL) {
        this.MaTL = MaTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String TenTL) {
        this.TenTL = TenTL;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public String getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(String MaNXB) {
        this.MaNXB = MaNXB;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String TenNXB) {
        this.TenNXB = TenNXB;
    }

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String NamXB) {
        this.NamXB = NamXB;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
}
    

    
