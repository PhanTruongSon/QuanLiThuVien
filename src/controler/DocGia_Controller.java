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
public class DocGia_Controller {

    public static Connection conn = ConnectionQLTV.getInstance().getConnection();
    public static Statement state;
    public static PreparedStatement pstate;
    public static String sql;
    public static ResultSet rs;

    // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
    public static List<Model_DocGia> LayNguonDG(String sMaDG) throws SQLException, IOException, JSONException {
        List<Model_DocGia> arr = new ArrayList<>();
        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php";
            if (!sMaDG.equals("")) {
                url = url + "?MaDG=" + sMaDG;
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
                Model_DocGia temp = new Model_DocGia();
                temp.setMaDG(jsonObject.getString("MaDG"));
                temp.setTenDG(jsonObject.getString("TenDG"));
                temp.setNgaySinh(jsonObject.getString("NgaySinh"));
                temp.setGioiTinh(jsonObject.getString("GioiTinh"));
                temp.setDienThoai(jsonObject.getString("DienThoai"));
                temp.setEmail(jsonObject.getString("Email"));
                temp.setHanDung(jsonObject.getString("HanDung"));
                arr.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // 1.insert : Thêm độc giả
    public static void ThemMoi(Model_DocGia dg) throws JSONException {
        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");

            // Tạo đối tượng JSON
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("TenDG", dg.getTenDG());
            jsonObject.put("NgaySinh", dg.getNgaySinh());
            jsonObject.put("GioiTinh", dg.getGioiTinh());
            jsonObject.put("DienThoai", dg.getDienThoai());
            jsonObject.put("Email", dg.getEmail());
            jsonObject.put("HanDung", dg.getHanDung());
            jsonObject.put("MaDG", dg.getMaDG());

            // Đặt dữ liệu JSON vào body của yêu cầu POST
            StringEntity requestEntity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // Gửi yêu cầu POST và nhận phản hồi từ máy chủ
            HttpResponse response = httpClient.execute(httpPost);

            // Đọc phản hồi từ máy chủ
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            System.out.println("Response: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2.update : Cập nhật Độc Gia
    public static void CapNhat(Model_DocGia dg) throws JSONException {
        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPut httpPut = new HttpPut(url);
            httpPut.setHeader("Content-Type", "application/json");

            // Tạo đối tượng JSON
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("MaDG", dg.getMaDG());
            jsonObject.put("TenDG", dg.getTenDG());
            jsonObject.put("NgaySinh", dg.getNgaySinh());
            jsonObject.put("GioiTinh", dg.getGioiTinh());
            jsonObject.put("DienThoai", dg.getDienThoai());
            jsonObject.put("Email", dg.getEmail());
            jsonObject.put("HanDung", dg.getHanDung());

            // Đặt dữ liệu JSON vào body của yêu cầu PUT
            StringEntity requestEntity = new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON);
            httpPut.setEntity(requestEntity);

            // Gửi yêu cầu PUT và nhận phản hồi từ máy chủ
            HttpResponse response = httpClient.execute(httpPut);

            // Đọc phản hồi từ máy chủ
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            System.out.println("Response: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4. delete : xóa tài khoản
    public static void Delete(String macu) {

        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php?MaDG=" + macu;
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setHeader("Content-Type", "application/json");

            // Gửi yêu cầu DELETE và nhận phản hồi từ máy chủ
            HttpResponse response = httpClient.execute(httpDelete);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) {
                System.out.println("Xóa thành công.");
            } else {
                // Đọc phản hồi từ máy chủ chỉ khi có thực thể (entity)
                if (response.getEntity() != null) {
                    String responseString = EntityUtils.toString(response.getEntity());
                    System.out.println("Response: " + responseString);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 5. check : Kiểm tra dữ liệu PK
    public static boolean KiemTraTrungMa(String manhap, boolean ktThem, String macu) throws JSONException {
        boolean kq = false;
        try {
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php?MaDG=" + macu;

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);

                JSONArray jsonArray = new JSONArray(responseBody);
                if (jsonArray.length() > 0) {
                    kq = true;
                } else {
                    kq = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
}
        return kq;
    }

    public static List<Model_DocGia> TimKiemTheoTen(String ten) throws JSONException {
        List<Model_DocGia> arr = new ArrayList<>();

        try {
            String tenurl = URLEncoder.encode(ten, StandardCharsets.UTF_8.toString());
            String url = "http://localhost/ApiNhom3UTT/LibaryAPI/DocGia.php?TenDG=" + tenurl;

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);

                JSONArray jsonArray = new JSONArray(responseBody);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Model_DocGia temp = new Model_DocGia();
                    temp.setMaDG(jsonObject.getString("MaDG"));
                    temp.setTenDG(jsonObject.getString("TenDG"));
                    temp.setNgaySinh(jsonObject.getString("NgaySinh"));
                    temp.setGioiTinh(jsonObject.getString("GioiTinh"));
                    temp.setDienThoai(jsonObject.getString("DienThoai"));
                    temp.setEmail(jsonObject.getString("Email"));
                    temp.setHanDung(jsonObject.getString("HanDung"));
                    arr.add(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

}