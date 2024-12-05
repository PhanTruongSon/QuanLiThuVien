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
public class Model_TheLoai {
    private String MaTL;
    private String TenTL;

    public Model_TheLoai() {
    }
    

    public Model_TheLoai(String MaTL, String TenTL) {
        this.MaTL = MaTL;
        this.TenTL = TenTL;
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
    
    
}
