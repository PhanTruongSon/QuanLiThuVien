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
        import model.Model_NhanVien;
        import model.Model_TheLoai;
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
        public class TheLoai_Controller {

            public static Connection conn = ConnectionQLTV.getInstance().getConnection();
            public static Statement state;
            public static PreparedStatement pstate;
            public static String sql;
            public static ResultSet rs;

            // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
            public static List<Model_TheLoai> LayNguonTL() throws SQLException, IOException, JSONException {
                List<Model_TheLoai> arr = new ArrayList<>();
                try {
                    String url = "http://localhost/ApiNhom3UTT/LibaryAPI/TheLoai.php";

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
                        Model_TheLoai temp = new Model_TheLoai();
                        temp.setMaTL(jsonObject.getString("MaTL"));
                        temp.setTenTL(jsonObject.getString("TenTL"));
                        arr.add(temp);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return arr;
            }

             public static String automatl() throws SQLException, IOException, JSONException {
                String mas = "", maht = "";
               try {
                    String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php?Max=";

                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpGet httpGet = new HttpGet(url);
                    httpGet.setHeader("Content-Type", "application/json");
                    HttpResponse response = httpClient.execute(httpGet);
                    HttpEntity entity = response.getEntity();
                    String responseString = EntityUtils.toString(entity);

                    // Xử lý dữ liệu JSON từ phản hồi
                    JSONArray jsonArray = new JSONArray(responseString);
                    if (jsonArray.length()>0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                    maht =jsonObject.getString("MAX(MaSach)");
                        System.out.println(maht);
                }
                if (maht.isEmpty()) {
                    mas = "S001"; // Nếu không có mã thể loại hiện tại, bắt đầu từ mã TL001
                } else {
                    // Tách phần số của mã thể loại hiện tại
                    String so = maht.substring(2);
                    // Chuyển phần số thành số nguyên và tăng giá trị lên 1
                    int soMoi = Integer.parseInt(so) + 1;
                    // Format số mới để có độ dài 3 chữ số (ví dụ: 001, 010, 100)
                    String soMoiFormatted = String.format("%03d", soMoi);
                    // Tạo mã thể loại mới bằng cách kết hợp "TL" với số mới đã định dạng
                    mas = "S" + soMoiFormatted;
                } }catch (IOException e) {
                    e.printStackTrace();
                }

                return mas;
            }

            // 1.insert : Thêm độc giả
            public static void ThemMoi(String matl, String tentl) throws JSONException {
                try {
                    String url = "http://localhost/ApiNhom3UTT/LibaryAPI/TheLoai.php";
                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/json");

                    // Tạo đối tượng JSON
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("MaTL", matl);
                    jsonObject.put("TenTL", tentl);


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
            // 5. check : Kiểm tra dữ liệu PK
            public static boolean KiemTraTrungMaTen(String tennhap) throws JSONException {
                boolean kq = false;
                try {
                    String url = "http://localhost/ApiNhom3UTT/LibaryAPI/TheLoai.php?TenTL=" + tennhap;

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











        }