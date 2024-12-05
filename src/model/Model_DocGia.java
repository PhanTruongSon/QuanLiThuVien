
package model;



public class Model_DocGia {
    private String MaDG;
    private String TenDG;
    private String NgaySinh;
    private String GioiTinh;
    private String DienThoai;
    private String Email;
    private String HanDung;
    
    public Model_DocGia(){
        
    }

    public Model_DocGia(String MaDG, String TenDG, String NgaySinh, String GioiTinh, String DienThoai, String Email, String HanDung) {
        this.MaDG = MaDG;
        this.TenDG = TenDG;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DienThoai = DienThoai;
        this.Email = Email;
        this.HanDung = HanDung;
    }

    public String getMaDG() {
        return MaDG;
    }

    public void setMaDG(String MaDG) {
        this.MaDG = MaDG;
    }

    public String getTenDG() {
        return TenDG;
    }

    public void setTenDG(String TenDG) {
        this.TenDG = TenDG;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getHanDung() {
        return HanDung;
    }

    public void setHanDung(String HanDung) {
        this.HanDung = HanDung;
    }

    
    
}
    
   