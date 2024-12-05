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
public class Model_ChiTietMuonTra {
    private String SoPM;    
    private String MaSach;
    private int SoLuong;
    private int TinhTrang;
    private Date NgayTra;

    public Model_ChiTietMuonTra(String SoPM, String MaSach, int SoLuong, int TinhTrang, Date NgayTra) {
        this.SoPM = SoPM;
        this.MaSach = MaSach;
        this.SoLuong = SoLuong;
        this.TinhTrang = TinhTrang;
        this.NgayTra = NgayTra;
    }

    public String getSoPM() {
        return SoPM;
    }

    public void setSoPM(String SoPM) {
        this.SoPM = SoPM;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }
    

    
}
