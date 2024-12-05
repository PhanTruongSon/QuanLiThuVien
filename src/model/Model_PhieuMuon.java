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
public class Model_PhieuMuon {
    private String SoPM;    
    private String MaDG;
    private String MaNV;
    private Date NgayMuon;    
    private Date HanTra;

    public Model_PhieuMuon(String SoPM, String MaDG, String MaNV, Date NgayMuon, Date HanTra) {
        this.SoPM = SoPM;
        this.MaDG = MaDG;
        this.MaNV = MaNV;
        this.NgayMuon = NgayMuon;
        this.HanTra = HanTra;
    }

    public String getSoPM() {
        return SoPM;
    }

    public void setSoPM(String SoPM) {
        this.SoPM = SoPM;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    public Date getHanTra() {
        return HanTra;
    }

    public void setHanTra(Date HanTra) {
        this.HanTra = HanTra;
    }
    


    
}
