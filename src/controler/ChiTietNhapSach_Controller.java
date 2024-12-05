/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import Connection.ConnectionQLTV;
import static Connection.ConnectionQLTV.DB_URL;
import static Connection.ConnectionQLTV.PASSWORD;
import static Connection.ConnectionQLTV.USER_NAME;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model_DocGia;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Think Pad
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model_ChiTietNhapSach;
import model.Model_NhaXuatBan;
import model.Model_NhanVien;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Think Pad
 */
public class ChiTietNhapSach_Controller {

    public static Connection conn = ConnectionQLTV.getInstance().getConnection();
    public static Statement state;
    public static PreparedStatement pstate;
    public static String sql;
    public static ResultSet rs;

    // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
    public static List<Model_ChiTietNhapSach> LayNguonCTNS(String ma) throws SQLException, IOException, JSONException {
        List<Model_ChiTietNhapSach> arr = new ArrayList<>();
        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/ChiTietNhapSach.php";
            if (!ma.equals("")) {
                url = url + "?MaNhap=" + ma;
            }
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);

            // Xử lý dữ liệu JSON từ phản hồi
            JSONArray jsonArray = new JSONArray(responseString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Model_ChiTietNhapSach temp = new Model_ChiTietNhapSach();
                temp.setMaNhap(jsonObject.getString("MaNhap"));
                temp.setMaSach(jsonObject.getString("MaSach"));
                temp.setTenSach(jsonObject.getString("TenSach"));
                temp.setSoLuong(jsonObject.getString("SoLuong"));
               
          
           
                arr.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

//     1.insert : Thêm độc giả
//    public static void ThemMoi(String sql) throws JSONException {
//        try {
//            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/ChiTietNhapSach.php";
//            HttpClient httpClient = HttpClientBuilder.create().build();
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Content-Type", "application/json");
//
//            // Tạo đối tượng JSON
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("TenNXB", nxb.getTenNXB());
//            jsonObject.put("DiaChi", nxb.getDiaChi());
//            jsonObject.put("Email", nxb.getEmail());
//            jsonObject.put("DiaChi", nxb.getDiaChi());
//            jsonObject.put("DienThoai", nxb.getDienThoai());
//            jsonObject.put("Email", nxb.getEmail());
//           
//
//            // Đặt dữ liệu JSON vào body của yêu cầu POST
//            StringEntity requestEntity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
//            httpPost.setEntity(requestEntity);
//
//            // Gửi yêu cầu POST và nhận phản hồi từ máy chủ
//            HttpResponse response = httpClient.execute(httpPost);
//
//            // Đọc phản hồi từ máy chủ
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString(entity);
//            System.out.println("Response: " + responseString);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////    }
public static void ThemMoi(String sql) {
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
 
}