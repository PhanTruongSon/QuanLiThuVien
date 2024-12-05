
package model;

public class Model_ChiTietSachTra {
    private String SoPM, MaSach, TenSach, SoLuong, TinhTrang, HanTra;

    public Model_ChiTietSachTra() {
    }

    public Model_ChiTietSachTra(String SoPM, String MaSach, String TenSach, String SoLuong, String TinhTrang, String HanTra) {
        this.SoPM = SoPM;
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.SoLuong = SoLuong;
        this.TinhTrang = TinhTrang;
        this.HanTra = HanTra;
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

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public String getHanTra() {
        return HanTra;
    }

    public void setHanTra(String HanTra) {
        this.HanTra = HanTra;
    }
    
}
