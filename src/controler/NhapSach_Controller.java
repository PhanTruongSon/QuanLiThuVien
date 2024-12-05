/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Model_NhapSach;

/**
 *
 * @author Administrator
 */
public class NhapSach_Controller {

    public static Connection conn = ConnectionQLTV.getInstance().getConnection();
    public static Statement state;
    public static PreparedStatement pstate;
    public static String sql;
    public static ResultSet rs;

    public static List<Model_NhapSach> LayNguonNS(String ma, String start, String end) throws SQLException {
        List<Model_NhapSach> arr = new ArrayList<>();
        conn = null;
        state = null;
        try {
            conn = ConnectionQLTV.getInstance().getConnection();

            if (!ma.equals("") && !start.equals("") && !end.equals("")) {
                sql = "Select MaNhap, nhanvien.MaNV,nhanvien.HoTen,ngaynhap From nhapsach inner join nhanvien on nhapsach.MaNV= nhanvien.MaNV where MaNhap ='" + ma + "'and ngaynhap>='" + start + "' and ngaynhap<='" + end + "'";
            }
            if (ma.equals("") && !start.equals("") && !end.equals("")) {
                sql = "Select MaNhap, nhanvien.MaNV,nhanvien.HoTen, ngaynhap from nhapsach inner join nhanvien on nhapsach.MaNV=nhanvien.MaNV where ngaynhap>= '" + start + "'and ngaynhap <='" + end + "'";
            }
            if (!ma.equals("") && start.equals("") && end.equals("")) {
                sql = "Select MaNhap, nhanvien.MaNV,nhanvien.HoTen, ngaynhap from nhapsach inner join nhanvien on nhapsach.MaNV= nhanvien.MaNV where manhap ='" + ma + "'";
            }
            if (ma.equals("") && start.equals("") && end.equals("")) {
                sql = "Select MaNhap, nhanvien.MaNV,nhanvien.HoTen, ngaynhap from nhapsach inner join nhanvien on nhapsach.MaNV= nhanvien.MaNV";
            }

            state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Model_NhapSach temp = new Model_NhapSach();
                temp.setMaNhap(rs.getString("MaNhap"));
                temp.setMaNV(rs.getString("MaNV"));
                temp.setHoTen(rs.getString("HoTen"));
                temp.setNgayNhap(rs.getDate("NgayNhap").toString());
                arr.add(temp);
            }
            state.close();

        } catch (SQLException ex) {
            Logger.getLogger(TheLoai_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;
    }

    public static String automanhap() throws SQLException {
        String mas = "", maht = "";
        conn = ConnectionQLTV.getInstance().getConnection();
        sql = "SELECT MAX(MaNhap) FROM nhapsach";
        state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql);
        if (rs.next()) {
            maht = rs.getString(1);
        }
        if (maht.isEmpty()) {
            mas = "N001"; // Nếu không có mã thể loại hiện tại, bắt đầu từ mã TL001
        } else {
            // Tách phần số của mã thể loại hiện tại
            String so = maht.substring(2);
            // Chuyển phần số thành số nguyên và tăng giá trị lên 1
            int soMoi = Integer.parseInt(so) + 1;
            // Format số mới để có độ dài 3 chữ số (ví dụ: 001, 010, 100)
            String soMoiFormatted = String.format("%03d", soMoi);
            // Tạo mã thể loại mới bằng cách kết hợp "TL" với số mới đã định dạng
            mas = "N" + soMoiFormatted;
        }
        return mas;
    }

    public static void ThemMoi(String manhap, String manv, String ngaynhap) {
        pstate = null;
        try {

            conn = ConnectionQLTV.getInstance().getConnection();
            sql = "Insert into nhapsach values(?,?,?)";
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, manhap);
            pstate.setString(2, manv);
            pstate.setString(3, ngaynhap);
            pstate.executeUpdate();
            pstate.close();

        } catch (SQLException ex) {
            Logger.getLogger(NhapSach_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
