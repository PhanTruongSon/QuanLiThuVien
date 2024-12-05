/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Think Pad
 */
public class Model_TaiKhoan {
    private String TaiKhoan;
    private String MatKhau;
    private String MaNV;

    public Model_TaiKhoan(String TaiKhoan, String MatKhau, String MaNV) {
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.MaNV = MaNV;
    }

    public Model_TaiKhoan(String string, String string0, String string1, String string2, Date date, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Model_TaiKhoan() {
        
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
    
    
}

