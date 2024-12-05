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
    import static form.Form_Sach.arrSach;
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
    import model.Model_Sach;
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
    public class Sach_Controller {

        public static Connection conn = ConnectionQLTV.getInstance().getConnection();
        public static Statement state;
        public static PreparedStatement pstate;
        public static String sql;
        public static ResultSet rs;

        // Lấy nguồn : Dùng để lấy nguồn dữ liệu từ CSDL và đưa vào mảng ArrayList
        public static List<Model_Sach> LayNguonSach(String ten) throws SQLException, IOException, JSONException {
            List<Model_Sach> arr = new ArrayList<>();
            try {
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php";
                if (!ten.equals("")) {
                    url = url + "?TenSach=" + ten;
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
                    Model_Sach temp = new Model_Sach();
                    temp.setMaSach(jsonObject.getString("MaSach"));
                    temp.setTenSach(jsonObject.getString("TenSach"));
                    temp.setMaTL(jsonObject.getString("MaTL"));
                    temp.setTacGia(jsonObject.getString("TacGia"));
                    temp.setMaNXB(jsonObject.getString("MaNXB"));
                    temp.setNamXB(jsonObject.getString("NamXB"));
                    temp.setTenTL(jsonObject.getString("TenTL"));
                    temp.setSoLuong(jsonObject.getInt("SoLuong"));
                    temp.setTenNXB(jsonObject.getString("TenNXB"));
                    arr.add(temp);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arr;
        }
       public static String automasach() throws SQLException, JSONException {
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
                        JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(responseString);
                } catch (JSONException ex) {
                    Logger.getLogger(Sach_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                        if (jsonArray.length()>0) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                        maht =jsonObject.getString("MAX(MaSach)");
                            System.out.println(maht);
                    }
                    if (maht.isEmpty()) {
                        mas = "TL001"; // Nếu không có mã thể loại hiện tại, bắt đầu từ mã TL001
                    } else {
                        // Tách phần số của mã thể loại hiện tại
                        String so = maht.substring(2);
                        // Chuyển phần số thành số nguyên và tăng giá trị lên 1
                        int soMoi = Integer.parseInt(so) + 1;
                        // Format số mới để có độ dài 3 chữ số (ví dụ: 001, 010, 100)
                        String soMoiFormatted = String.format("%03d", soMoi);
                        // Tạo mã thể loại mới bằng cách kết hợp "TL" với số mới đã định dạng
                        mas = "TL" + soMoiFormatted;
                    } }catch (IOException e) {
                        e.printStackTrace();
                    }

                    return mas;
        }
        // 1.insert : Thêm độc giả
        public static void ThemMoi(Model_Sach sach) throws JSONException {
            try {
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php";
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/json");

                // Tạo đối tượng JSON
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("MaSach", sach.getMaSach());
                jsonObject.put("TenSach", sach.getTenSach());
                jsonObject.put("MaTL", sach.getMaTL());
                jsonObject.put("TacGia", sach.getTacGia());
                jsonObject.put("MaNXB", sach.getMaNXB());
                jsonObject.put("NamXB", sach.getNamXB());
                jsonObject.put("SoLuong", sach.getSoLuong());


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
        public static void CapNhat(Model_Sach sach) throws JSONException {
            try {
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php";
                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpPut httpPut = new HttpPut(url);
                httpPut.setHeader("Content-Type", "application/json");

                // Tạo đối tượng JSON
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("MaSach", sach.getMaSach());
                jsonObject.put("TenSach", sach.getTenSach());
                jsonObject.put("MaTL", sach.getMaTL());
                jsonObject.put("TacGia", sach.getTacGia());
                jsonObject.put("MaNXB", sach.getMaNXB());
                jsonObject.put("NamXB", sach.getNamXB());
                jsonObject.put("SoLuong", sach.getSoLuong());


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
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php?MaSach=" + macu;
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
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php?MaSach=" + macu;

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

        public static List<Model_NhanVien> TimKiemTheoTen(String ten) throws JSONException, SQLException {
            List<Model_NhanVien> arr = new ArrayList<>();

            try {
                String tenurl = URLEncoder.encode(ten, StandardCharsets.UTF_8.toString());
                String url = "http://localhost/ApiNhom3UTT/LibaryAPI/Sach.php?TenSach=" + tenurl;

                HttpClient httpClient = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(url);

                HttpResponse response = httpClient.execute(request);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);

                    JSONArray jsonArray = new JSONArray(responseBody);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       Model_NhanVien temp = new Model_NhanVien();
                        temp.setMaNV(rs.getString("MaNV"));
                        temp.setHoTen(rs.getString("HoTen"));
                        temp.setGioiTinh(rs.getString("GioiTinh"));
                        temp.setNgaySinh(rs.getString("NgaySinh"));
                        temp.setDiaChi(rs.getString("DiaChi"));
                        temp.setEmail(rs.getString("Email"));
                        temp.setChucVu(rs.getString("ChucVu"));
                        temp.setNgayVaoLam(rs.getString("NgayVaoLam"));
                        arr.add(temp);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return arr;
        }

    }