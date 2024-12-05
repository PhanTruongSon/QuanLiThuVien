package controler;

import Connection.ConnectionQLTV;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model_Sach;

public class TraSach_Controller {

    public static Connection conn = ConnectionQLTV.getInstance().getConnection();
    public static Statement state;
    public static PreparedStatement pstate;
    public static String sql;
    public static ResultSet rs;

    // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
    public static List<Model_Sach> LayNguonSach() throws SQLException {
        state = null;
        List<Model_Sach> arrSach = new ArrayList<>();

        try {
            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "Select * from (sach inner join nhaxuatban on sach.MaNXB=nhaxuatban.MaNXB) inner join theloai on sach.MaTL=theloai.MaTL";
            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(TraSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSach;
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
            Logger.getLogger(TraSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
