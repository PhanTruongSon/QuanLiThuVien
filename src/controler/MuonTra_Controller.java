package controler;

import Connection.ConnectionQLTV;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model_ChiTietSachTra;
import model.Model_Sach;

public class MuonTra_Controller {

    public static Connection conn = ConnectionQLTV.getInstance().getConnection();
    public static Statement state;
    public static PreparedStatement pstate;
    public static String sql;
    public static ResultSet rs;

    // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
    public static List<Model_Sach> LayNguonSach(String masach) throws SQLException {
        state = null;
        List<Model_Sach> arrSach = new ArrayList<>();
        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            if (masach.equals("Sách")) {
                sql = "Select * from (sach inner join nhaxuatban on sach.MaNXB=nhaxuatban.MaNXB) inner join theloai on sach.MaTL=theloai.MaTL";
            } else {
                sql = "Select * from (sach inner join nhaxuatban on sach.MaNXB=nhaxuatban.MaNXB) inner join theloai on sach.MaTL=theloai.MaTL where MaSach = '" + masach + "'";
            }
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Model_Sach temp = new Model_Sach();
                temp.setMaSach(rs.getString("MaSach"));
                temp.setTenSach(rs.getString("TenSach"));
                temp.setMaTL(rs.getString("MaTL"));
                temp.setTenTL(rs.getString("TenTL"));
                temp.setTacGia(rs.getString("TacGia"));
                temp.setMaNXB(rs.getString("MaNXB"));
                temp.setTenNXB(rs.getString("TenNXB"));
                temp.setNamXB(rs.getString("NamXB"));
                temp.setSoLuong(rs.getInt("SoLuong"));
                arrSach.add(temp);
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(MuonTra_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSach;
    }

    public static List<Model_ChiTietSachTra> LayNguonTinhTrangSach(String madg) throws SQLException {
        state = null;
        List<Model_ChiTietSachTra> arrSach = new ArrayList<>();

        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "SELECT phieumuon.SoPM, sach.MaSach, TenSach, chitietmuontra.SoLuong, TinhTrang, HanTra FROM phieumuon inner join chitietmuontra on phieumuon.SoPM"
                    + " = chitietmuontra.SoPM inner join sach on chitietmuontra.MaSach = sach.MaSach WHERE MaDG = '" + madg + "' order by TinhTrang";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Model_ChiTietSachTra temp = new Model_ChiTietSachTra();
                temp.setSoPM(rs.getString("SoPM"));
                temp.setMaSach(rs.getString("MaSach"));
                temp.setTenSach(rs.getString("TenSach"));
                temp.setSoLuong(rs.getString("SoLuong"));
                temp.setTinhTrang(rs.getString("TinhTrang"));
                temp.setHanTra(rs.getString("HanTra"));
                arrSach.add(temp);
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(MuonTra_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSach;
    }

    public static int getslSachMuon(String madg) {
        int sl = 0;
        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "select sum(soluong) as tong from phieumuon inner join chitietmuontra on phieumuon.SoPM = chitietmuontra.SoPM where madg = '" + madg + "' and tinhtrang = 0";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                sl = rs.getInt("tong");
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(MuonTra_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }

    public static int getslSachMuonQuaHan(String madg) {
        int sl = 0;
        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngay = dateFormat.format(currentDate);
            sql = "select sum(soluong) as tong from phieumuon inner join chitietmuontra on phieumuon.SoPM = chitietmuontra.SoPM where madg = '" + madg + "' and tinhtrang = 0 and HanTra <'" + ngay + "'";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                sl = rs.getInt("tong");
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(MuonTra_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }

    public static boolean KiemTraDocGia(String madg) {
        boolean kq = false;
        List<Model_Sach> arrSach = new ArrayList<>();

        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "Select MaSach from docgia where MaDG='" + madg + "'";
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if (rs.next()) {
                Model_Sach temp = new Model_Sach();
                temp.setMaSach(rs.getString("MaSach"));
                temp.setTenSach(rs.getString("TenSach"));
                temp.setMaTL(rs.getString("MaTL"));
                temp.setTenNXB(rs.getString("TenTL"));
                temp.setTacGia(rs.getString("TacGia"));
                temp.setMaNXB(rs.getString("MaNXB"));
                temp.setTenNXB(rs.getString("TenNXB"));
                temp.setNamXB(rs.getString("NamXB"));
                temp.setSoLuong(rs.getInt("SoLuong"));
                arrSach.add(temp);

            } else {
                kq = false;
            }
//        
        } catch (SQLException ex) {
            Logger.getLogger(MuonTra_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String autosophieumuon() throws SQLException {
        String mas = "", maht = "";
        conn = ConnectionQLTV.getInstance().getConnection();
        sql = "SELECT MAX(SoPM) FROM PhieuMuon";
        state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql);
        if (rs.next()) {
            maht = rs.getString(1);
        }
        if (maht.isEmpty()) {
            mas = "PM001"; // Nếu không có mã thể loại hiện tại, bắt đầu từ mã TL001
        } else {
            // Tách phần số của mã thể loại hiện tại
            String so = maht.substring(2);
            // Chuyển phần số thành số nguyên và tăng giá trị lên 1
            int soMoi = Integer.parseInt(so) + 1;
            // Format số mới để có độ dài 3 chữ số (ví dụ: 001, 010, 100)
            String soMoiFormatted = String.format("%03d", soMoi);
            // Tạo mã thể loại mới bằng cách kết hợp "TL" với số mới đã định dạng
            mas = "PM" + soMoiFormatted;
        }
        return mas;
    }

    public static void ThemMoiPhieuMuon(String sopm, String madg, String manv, String ngaymuon, String hantra) {
        pstate = null;
        try {

            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "Insert into phieumuon values(?,?,?,?,?)";
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, sopm);
            pstate.setString(2, madg);
            pstate.setString(3, manv);
            pstate.setString(4, ngaymuon);
            pstate.setString(5, hantra);
            pstate.executeUpdate();
            pstate.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ThemMoiChiTietNhapSach(String sql) {
        pstate = null;
        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.executeUpdate();
            pstate.close();

        } catch (SQLException ex) {
            Logger.getLogger(ChiTietNhapSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void UpdateChiTietMuonTra(String sopm, String masach, String tinhtrang, int soluongtra, String ngaytra) {
        pstate = null;
        try {

            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "update chitietmuontra set tinhtrang = '"+ tinhtrang +"', ngaytra = '"+ ngaytra +"' where SoPM = '"+sopm+"' and masach = '"+masach+"'";
            pstate = conn.prepareStatement(sql);
            pstate.executeUpdate();
            sql = "update sach set soluong = soluong + "+soluongtra+" where masach = '"+masach+"'";
            pstate = conn.prepareStatement(sql);
            pstate.executeUpdate();
            pstate.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhapSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
