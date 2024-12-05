/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Login.login;
import controler.ChiTietNhapSach_Controller;
import controler.NhaXuatBan_Controller;
import controler.NhapSach_Controller;
import controler.Sach_Controller;
import controler.TheLoai_Controller;
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Model_ChiTietNhapSach;
import model.Model_NhaXuatBan;
import model.Model_NhapSach;
import model.Model_Sach;
import model.Model_TheLoai;
import notification.Notification;
import org.json.JSONException;
import swing.button;

/**
 *
 * @author Think Pad
 */
public class Form_Sach extends javax.swing.JPanel {

    public static List<Model_TheLoai> arrTL = new ArrayList<>();
    public static List<Model_Sach> arrSach = new ArrayList<>();
    public static List<Model_NhaXuatBan> arrNXB = new ArrayList<>();
    public static List<Model_NhapSach> arrNhapSach = new ArrayList<>();
    public static String maNhap, manhanvien, hoten, ngaynhap, ngaydefault = "2000-01-12";
    public static List<Model_ChiTietNhapSach> arrCTNS = new ArrayList<>();
    public static String masachNhap, TenSachNhap, soluongSachNhap;
    public static String maNXB, tenNXB, diachiNXB, emailNXB, dienthoaiNXB;
    public static String masach, tensach, maTLsach, tacgia, manxbsach, namxuatban, soluong;
    public static String maTL, tenTL;
    public static boolean ktThemNXB, ktThemSach;
    public static String macuNXB;
    public static Date date;
    String[] columnNamesNXB = {"Mã Nhà Xuất Bản", "Tên Nhà Xuất bản", "Địa chỉ", "Emaul", "Điện thoại"};
    DefaultTableModel tblNXB = new DefaultTableModel(columnNamesNXB, 0);
    String[] columnNamesChiTietNhapSach = {"Mã Nhập", "Mã Sách", "Tên Sách", "Số Lượng"};
    DefaultTableModel tblChiTietNhapSach = new DefaultTableModel(columnNamesChiTietNhapSach, 0);
    String[] columnNamesNhapSach = {"Mã Nhập", "Mã Nhân Viên", "Tên Nhân Viên", "Ngày Nhập"};
    DefaultTableModel tblDanhNhapSach = new DefaultTableModel(columnNamesNhapSach, 0);
    String[] columnNames = {"", "Mã Sách", "Tên Sách", "Thể loại", "Tác Giả", "Nhà Xuất Bản", "Năm XB", "Số Lượng"};
    DefaultTableModel tblDanhSach = new DefaultTableModel(columnNames, 0) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) { // chỉ định cột đầu tiên là kiểu Boolean (JCheckBox)
                return Boolean.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 0; // cho phép chỉnh sửa ô kiểm (checkbox) trong cột đầu tiên
        }
    };
    public static SimpleDateFormat sDF = new SimpleDateFormat("yyyy-mm-dd");

    public Form_Sach() throws IOException, JSONException {
        initComponents();
        KhoaMoSach(true);
        LayNguonNXB();
        LayNguonCBO();
        LayNguonSach();
        txtMaSach.setEditable(false);
        txtSoLuong.setEditable(false);
        txtSoLuong.setForeground(Color.red);

        try {
            date = sDF.parse(ngaydefault);
            DateStart.setDate(date);
            DateEnd.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        LayNguonNhapSach();
        LayNguonChiTietNhapSach("");
        DesginTable();

    }

    public void DesginTable() {
        tbSach.getColumnModel().getColumn(0).setPreferredWidth(10);

    }

    public void KhoaMoNXB(boolean b) {
        btThemNXB.setEnabled(b);
        btSuaNXB.setEnabled(b);
        btXoaNXB.setEnabled(b);
        btLuuNXB.setEnabled(!b);
        btThemNXB.setStyle(button.ButtonStyle.PRIMARY);
        btSuaNXB.setStyle(button.ButtonStyle.PRIMARY);
        btXoaNXB.setStyle(button.ButtonStyle.PRIMARY);

        btLuuNXB.setStyle(button.ButtonStyle.SECONDARY);

    }

    // Khi ấn vào nút thêm đổi màu các button khác
    public void ColorButtonAdd() {
        btThemNXB.setStyle(button.ButtonStyle.SECONDARY);
        btSuaNXB.setStyle(button.ButtonStyle.SECONDARY);
        btXoaNXB.setStyle(button.ButtonStyle.SECONDARY);
        btReloadNXB.setStyle(button.ButtonStyle.DESTRUCTIVE);
        btLuuNXB.setStyle(button.ButtonStyle.DESTRUCTIVE);

        // Sách
        btThemSach.setStyle(button.ButtonStyle.SECONDARY);
        btSuaSach.setStyle(button.ButtonStyle.SECONDARY);
        btReloadSach.setStyle(button.ButtonStyle.DESTRUCTIVE);
        btLuuSach.setStyle(button.ButtonStyle.DESTRUCTIVE);

    }// Khi ấn vào button reload đổi màu các button khác

    public void ColorButtonReload() {
        btThemNXB.setStyle(button.ButtonStyle.PRIMARY);
        btSuaNXB.setStyle(button.ButtonStyle.PRIMARY);
        btXoaNXB.setStyle(button.ButtonStyle.PRIMARY);

        // SÁch
        btThemSach.setStyle(button.ButtonStyle.PRIMARY);
        btSuaSach.setStyle(button.ButtonStyle.PRIMARY);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void XoaTrangNXB() {
        txtMaNXB.setText("");
        txtTenNXB.setText("");
        txtDienThoaiNXB.setText("");
        txtEmailNXB.setText("");
        txtDiaChiNXB.setText("");

    }

    public void LayNguonNXB() throws IOException, JSONException {

        try {
            if (txtTimKiemNXB.getText().equals("")) {
                arrNXB = NhaXuatBan_Controller.LayNguonNXB("");
            } else {
                arrNXB = NhaXuatBan_Controller.LayNguonNXB(txtTimKiemNXB.getText());

            }

        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblNXB = (DefaultTableModel) tbNXB.getModel();
        tblNXB.setRowCount(0); // Xóa trắng
        arrNXB.forEach(p -> {
            tblNXB.addRow(new Object[]{
                p.getMaNXB(), p.getTenNXB(), p.getDiaChi(), p.getEmail(),
                p.getDienThoai()

            });
        });

    }
    // Xóa trắng from Sách

    public void XoaTrangSach() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtNamXuatBan.setText("");
        txtSoLuong.setText("");
        txtTacGia.setText("");
    }

    public void KhoaMoSach(boolean b) {
        btThemSach.setEnabled(b);
        btSuaSach.setEnabled(b);
        btLuuSach.setEnabled(!b);
        btThemSach.setStyle(button.ButtonStyle.PRIMARY);
        btSuaSach.setStyle(button.ButtonStyle.PRIMARY);
        btLuuSach.setStyle(button.ButtonStyle.SECONDARY);
//        txtMaSach.setEditable(b);
//        txtTenSach.setEditable(b);
//        txtSoLuong.setEditable(b);
//        txtNamXuatBan.setEditable(b);
//        jcbNXB.setEnabled(b);
//        jcbTheLoai.setEnabled(b);

    }// Lay Nguon Nhap sach

    public void LayNguonNhapSach() {

        String key = "";
        boolean a = txtTimKiemNhapSach.getText().equals("");
        boolean b = sDF.format(DateStart.getDate()).equals("2000-01-12");
        boolean c = sDF.format(DateEnd.getDate()).equals("2000-01-12");
        if (a) {
            key += "0";
        } else {
            key += "1";
        }

        if (b) {
            key += "0";
        } else {
            key += "1";
        }
        if (c) {
            key += "0";
        } else {
            key += "1";
        }
        if (key.equals("101") || key.equals("001") || key.equals("010") || key.equals("110")) {

        } else {
            switch (key) {
                case "111": {
                    try {
                        arrNhapSach = NhapSach_Controller.LayNguonNS(txtTimKiemNhapSach.getText(), sDF.format(DateStart.getDate()), sDF.format(DateEnd.getDate()));
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "011": {
                    try {
                        arrNhapSach = NhapSach_Controller.LayNguonNS("", sDF.format(DateStart.getDate()), sDF.format(DateEnd.getDate()));
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "100": {
                    try {
                        arrNhapSach = NhapSach_Controller.LayNguonNS(txtTimKiemNhapSach.getText(), "", "");
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "000": {
                    try {
                        arrNhapSach = NhapSach_Controller.LayNguonNS("", "", "");
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
            tblDanhNhapSach.setRowCount(0);
            arrNhapSach.forEach(p -> {
                tblDanhNhapSach.addRow(new Object[]{
                    p.getMaNhap(), p.getMaNV(), p.getHoTen(), p.getNgayNhap()

                });
            });
            tbNhapSach.setModel(tblDanhNhapSach);
        }

    }

    public void LayNguonChiTietNhapSach(String manhap) throws IOException, JSONException {
        try {
            if (manhap.equals("")) {
                arrCTNS = ChiTietNhapSach_Controller.LayNguonCTNS("");
            } else {
                arrCTNS = ChiTietNhapSach_Controller.LayNguonCTNS(manhap);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblChiTietNhapSach.setRowCount(0);
        arrCTNS.forEach(p -> {
            tblChiTietNhapSach.addRow(new Object[]{
                p.getMaNhap(), p.getMaSach(), p.getTenSach(), p.getSoLuong()

            });
        });
        tbChiTietNhapSach.setModel(tblChiTietNhapSach);
    }
    // LayNguonSach

    public void LayNguonSach() throws IOException, JSONException {

        try {
            if (txtTimKiemSach.getText().equals("")) {
                arrSach = Sach_Controller.LayNguonSach("");
            } else {
                arrSach = Sach_Controller.LayNguonSach(txtTimKiemSach.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDanhSach.setRowCount(0);
        arrSach.forEach(p -> {
            tblDanhSach.addRow(new Object[]{
                false, p.getMaSach(), p.getTenSach(), p.getTenTL(), p.getTacGia(),
                p.getTenNXB(), p.getNamXB(), p.getSoLuong()
                   
            });
            System.out.println(p);
        });
        tbSach.setModel(tblDanhSach);

    }
//     private void hienThiKetQua(List<Model_DocGia> ketQua) {
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Mã độc giả");
//        model.addColumn("Tên độc giả");
//        model.addColumn("Ngày sinh");
//        model.addColumn("Giới tính");
//        model.addColumn("Điện thoại");
//        model.addColumn("Email");
//        model.addColumn("Hạn dùng");
//        model.setRowCount(0);
//
//
//        for (Model_DocGia docGia : ketQua) {
//            Object[] row = new Object[7];
//            row[0] = docGia.getMaDG();
//            row[1] = docGia.getTenDG();
//            row[2] = docGia.getNgaySinh();
//            row[3] = docGia.getGioiTinh();
//            row[4] = docGia.getDienThoai();
//            row[5] = docGia.getEmail();
//            row[6] = docGia.getHanDung();
//            model.addRow(row);
//        }
//
//        tbSach.setModel(model);
//    }

    // Lấy Nguồn Thể loại
    //Lấy nguồn thể loại
    public void LayNguonCBO() throws IOException, JSONException {
        try {
            arrTL = TheLoai_Controller.LayNguonTL();
            for (int i = 0; i < arrTL.size(); i++) {

                jcbTheLoai.addItem(arrTL.get(i).getTenTL());

            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            arrNXB = NhaXuatBan_Controller.LayNguonNXB("");
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < arrNXB.size(); i++) {
            jcbNXB.addItem(arrNXB.get(i).getTenNXB());
        }

    }
    // Vi tri the loia vs nhaxuat ban

    private int vtNXB(String sMaNXB) {
        for (int i = 0; i < arrNXB.size(); i++) {
            if (arrNXB.get(i).getTenNXB().equals(sMaNXB) == true) {
                return i;
            }
        }
        return 0;
    }

    private int vtTheLoai(String sMaTL) {
        for (int i = 0; i < arrTL.size(); i++) {
            if (arrTL.get(i).getTenTL().equals(sMaTL) == true) {
                return i;
            }
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogTheLoai = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txtDilogMaTL = new javax.swing.JTextField();
        txtdilogTenTL = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btThemTL = new swing.button();
        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        txtDialogtennv = new javax.swing.JTextField();
        txtDialogmanv = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btDialogluunhapsach = new swing.button();
        jLabel22 = new javax.swing.JLabel();
        btDialogthoatnhapsach = new swing.button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDialognhapsach = new swing.Table();
        jLabel23 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPQuanLyNhapSach = new javax.swing.JPanel();
        button12 = new swing.button();
        txtTimKiemNhapSach = new javax.swing.JTextField();
        DateStart = new com.toedter.calendar.JDateChooser();
        DateEnd = new com.toedter.calendar.JDateChooser();
        label1 = new java.awt.Label();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNhapSach = new swing.Table();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbChiTietNhapSach = new swing.Table();
        jPNXB = new javax.swing.JPanel();
        txtMaNXB = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtTenNXB = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChiNXB = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txtEmailNXB = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtDienThoaiNXB = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNXB = new swing.Table();
        jLabel1 = new javax.swing.JLabel();
        btSuaNXB = new swing.button();
        btXoaNXB = new swing.button();
        btLuuNXB = new swing.button();
        btThemNXB = new swing.button();
        txtTimKiemNXB = new javax.swing.JTextField();
        button5 = new swing.button();
        btReloadNXB = new swing.button();
        jPNhapSach = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSach = new swing.Table();
        btNhapSach = new swing.button();
        btSuaSach = new swing.button();
        btLuuSach = new swing.button();
        txtTimKiemSach = new javax.swing.JTextField();
        button3 = new swing.button();
        jLabel4 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        txtNamXuatBan = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        btThemSach = new swing.button();
        jcbNXB = new javax.swing.JComboBox<>();
        jcbTheLoai = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btReloadSach = new swing.button();
        jLabel16 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();

        jDialogTheLoai.setBackground(new java.awt.Color(255, 255, 255));
        jDialogTheLoai.setMinimumSize(new java.awt.Dimension(700, 300));
        jDialogTheLoai.setModal(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));

        jLabel17.setText("MãThể Loại");

        jLabel19.setText("Tên Thể Loại");

        btThemTL.setText("Thêm");
        btThemTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemTLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDilogMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btThemTL, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdilogTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(181, 181, 181))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDilogMaTL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdilogTenTL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btThemTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogTheLoaiLayout = new javax.swing.GroupLayout(jDialogTheLoai.getContentPane());
        jDialogTheLoai.getContentPane().setLayout(jDialogTheLoaiLayout);
        jDialogTheLoaiLayout.setHorizontalGroup(
            jDialogTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        jDialogTheLoaiLayout.setVerticalGroup(
            jDialogTheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog1.setMinimumSize(new java.awt.Dimension(786, 405));
        jDialog1.setModal(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtDialogmanv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDialogmanvActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Mã:");

        btDialogluunhapsach.setText("Lưu");
        btDialogluunhapsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDialogluunhapsachActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Mã Nhân Viên :");

        btDialogthoatnhapsach.setText("Thoát");
        btDialogthoatnhapsach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDialogthoatnhapsachActionPerformed(evt);
            }
        });

        tbDialognhapsach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbDialognhapsach);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Nhập Sách");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(btDialogthoatnhapsach, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDialogluunhapsach, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDialogmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDialogtennv, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDialogmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtDialogtennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDialogluunhapsach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDialogthoatnhapsach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(0, 102, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPQuanLyNhapSach.setBackground(new java.awt.Color(255, 255, 255));

        button12.setText("Tìm kiếm");
        button12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });

        txtTimKiemNhapSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTimKiemNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemNhapSachActionPerformed(evt);
            }
        });

        DateStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DateStart.setMaxSelectableDate(new java.util.Date(253370743283000L));
        DateStart.setMinSelectableDate(new java.util.Date(-62135791117000L));

        DateEnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setText("đến");

        tbNhapSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Nhập", "Mã Nhân Viên", "Nhân viên", "Ngày Nhập"
            }
        ));
        tbNhapSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhapSachMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbNhapSach);

        tbChiTietNhapSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Nhập", "Mã Sâch", "Tên Sách", "Số lượng"
            }
        ));
        jScrollPane5.setViewportView(tbChiTietNhapSach);

        javax.swing.GroupLayout jPQuanLyNhapSachLayout = new javax.swing.GroupLayout(jPQuanLyNhapSach);
        jPQuanLyNhapSach.setLayout(jPQuanLyNhapSachLayout);
        jPQuanLyNhapSachLayout.setHorizontalGroup(
            jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPQuanLyNhapSachLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPQuanLyNhapSachLayout.createSequentialGroup()
                        .addComponent(DateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPQuanLyNhapSachLayout.createSequentialGroup()
                        .addComponent(txtTimKiemNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(button12, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPQuanLyNhapSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );
        jPQuanLyNhapSachLayout.setVerticalGroup(
            jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPQuanLyNhapSachLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button12, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtTimKiemNhapSach))
                .addGap(40, 40, 40)
                .addGroup(jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPQuanLyNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(268, 268, 268))
        );

        jTabbedPane1.addTab("Quản lý nhập sách", jPQuanLyNhapSach);

        jPNXB.setBackground(new java.awt.Color(255, 255, 255));

        txtMaNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMaNXB.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên Nhà Xuất Bản");

        txtTenNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenNXB.setBorder(null);
        txtTenNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNXBActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Đia chỉ");

        txtDiaChiNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiaChiNXB.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        txtEmailNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmailNXB.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Điện thoại");

        txtDienThoaiNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDienThoaiNXB.setBorder(null);
        txtDienThoaiNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDienThoaiNXBActionPerformed(evt);
            }
        });

        tbNXB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Nhà Xuất Bản", "Tên Nhà Xuất Bản", "Địa Chỉ", "Email", "Điện thoại"
            }
        ));
        tbNXB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNXBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNXB);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Nhà Xuất Bản");

        btSuaNXB.setText("Sửa");
        btSuaNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSuaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaNXBActionPerformed(evt);
            }
        });

        btXoaNXB.setText("Xóa");
        btXoaNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btXoaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaNXBActionPerformed(evt);
            }
        });

        btLuuNXB.setText("Lưu thông tin");
        btLuuNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLuuNXB.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btLuuNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLuuNXBActionPerformed(evt);
            }
        });

        btThemNXB.setText("Thêm");
        btThemNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btThemNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemNXBActionPerformed(evt);
            }
        });

        txtTimKiemNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTimKiemNXB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemNXBCaretUpdate(evt);
            }
        });

        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        button5.setText("Tìm kiếm");
        button5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        button5.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);

        btReloadNXB.setText("RELOAD");
        btReloadNXB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReloadNXB.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btReloadNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReloadNXBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPNXBLayout = new javax.swing.GroupLayout(jPNXB);
        jPNXB.setLayout(jPNXBLayout);
        jPNXBLayout.setHorizontalGroup(
            jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNXBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPNXBLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtTenNXB)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(txtMaNXB, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addComponent(jLabel3))
                .addGap(102, 102, 102)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addComponent(txtDienThoaiNXB)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(txtDiaChiNXB)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtEmailNXB)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPNXBLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(btSuaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(btXoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(btLuuNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(btReloadNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPNXBLayout.setVerticalGroup(
            jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNXBLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPNXBLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNXBLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChiNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNXBLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNXBLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmailNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPNXBLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDienThoaiNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPNXBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThemNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoaNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLuuNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btReloadNXB, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );

        jTabbedPane1.addTab("Nhà Xuất bản", jPNXB);

        jPNhapSach.setBackground(new java.awt.Color(255, 255, 255));

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbSach);

        btNhapSach.setText("Nhập Sách");
        btNhapSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btNhapSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhapSachActionPerformed(evt);
            }
        });

        btSuaSach.setText("Sửa");
        btSuaSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSuaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaSachActionPerformed(evt);
            }
        });

        btLuuSach.setText("Lưu thông tin");
        btLuuSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLuuSach.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btLuuSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLuuSachActionPerformed(evt);
            }
        });

        txtTimKiemSach.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemSachCaretUpdate(evt);
            }
        });

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        button3.setText("Tìm kiếm");
        button3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        button3.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã Sách");

        jLabel6.setText("Tên Sách");

        jLabel10.setText("Mã TL- Tên Thể Loại");

        jLabel11.setText("Mã NXB- Tên Nhà Xuất Bản");

        jLabel12.setText("Năm Xuất Bản");

        jLabel13.setText("Số lượng");

        btThemSach.setText("Thêm");
        btThemSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemSachActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Plus_40px.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Plus_40px.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        btReloadSach.setText("RELOAD");
        btReloadSach.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReloadSach.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btReloadSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReloadSachActionPerformed(evt);
            }
        });

        jLabel16.setText("Tác Giả");

        javax.swing.GroupLayout jPNhapSachLayout = new javax.swing.GroupLayout(jPNhapSach);
        jPNhapSach.setLayout(jPNhapSachLayout);
        jPNhapSachLayout.setHorizontalGroup(
            jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPNhapSachLayout.createSequentialGroup()
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(226, 355, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator12)
                                .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPNhapSachLayout.createSequentialGroup()
                                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSach, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSach, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(jcbTheLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong)
                    .addComponent(jSeparator11)
                    .addComponent(jcbNXB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addContainerGap(372, Short.MAX_VALUE))
            .addGroup(jPNhapSachLayout.createSequentialGroup()
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btSuaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btLuuSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btReloadSach, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPNhapSachLayout.setVerticalGroup(
            jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNhapSachLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPNhapSachLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPNhapSachLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jcbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)))
                    .addGroup(jPNhapSachLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPNhapSachLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jcbNXB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPNhapSachLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPNhapSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhapSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btThemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSuaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLuuSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btReloadSach, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhập Sách", jPNhapSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btThemNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemNXBActionPerformed
        // TODO add your handling code here:
        ktThemNXB = true;
        KhoaMoNXB(false);
        txtMaNXB.requestFocus();
        ColorButtonAdd();

    }//GEN-LAST:event_btThemNXBActionPerformed

    private void btLuuNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLuuNXBActionPerformed
        // TODO add your handling code here:
        if (txtMaNXB.getText().length() <= 0 || txtTenNXB.getText().length() <= 0 || txtDiaChiNXB.getText().length() <= 0
                || txtDienThoaiNXB.getText().length() <= 0 || txtEmailNXB.getText().length() <= 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Bạn chưa nhập đầy dử thông tin");
            panel.showNotification();
            txtMaNXB.requestFocus();
            return;
        }
        macuNXB = txtMaNXB.getText();

        // thuc hien ghi
        maNXB = txtMaNXB.getText();
        tenNXB = txtTenNXB.getText();
        diachiNXB = txtDiaChiNXB.getText();
        emailNXB = txtEmailNXB.getText();
        dienthoaiNXB = txtDienThoaiNXB.getText();

        Model_NhaXuatBan temp = new Model_NhaXuatBan(maNXB, tenNXB, diachiNXB, emailNXB, dienthoaiNXB);
        if (ktThemNXB == true) {
            try {
                if (NhaXuatBan_Controller.KiemTraTrungMa(txtMaNXB.getText(), ktThemNXB, txtMaNXB.getText()) == true) {
                    Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Nhập trùng mã nhà xuất bản");
                    panel.showNotification();
                    txtMaNXB.requestFocus();
                    return;
                }
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                NhaXuatBan_Controller.ThemMoi(temp);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm mới thành công");
            panel.showNotification();

        } else if (ktThemNXB == false) {
            try {
                if (NhaXuatBan_Controller.KiemTraTrungMa(txtMaNXB.getText(), ktThemNXB, txtMaNXB.getText()) == true) {
                    txtMaNXB.requestFocus();
                    NhaXuatBan_Controller.CapNhat(temp);
                    Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Sửa thành công thành công");
                    
                }
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Sửa  không thành công");
            panel.showNotification();
        }

        try {
            LayNguonNXB();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        KhoaMoNXB(false);
        ColorButtonReload();
        try {
            LayNguonCBO();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btLuuNXBActionPerformed

    private void btXoaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaNXBActionPerformed
        // TODO add your handling code here:
        if (txtMaNXB.getText().length() <= 0) {
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa độc giả không[" + txtMaNXB.getText() + "]",
                "Thông báo ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kq == JOptionPane.YES_NO_OPTION) {
            macuNXB = txtMaNXB.getText();
            NhaXuatBan_Controller.Delete(macuNXB);
            XoaTrangNXB();
            try {
                LayNguonNXB();
            } catch (IOException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                LayNguonCBO();
            } catch (IOException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btXoaNXBActionPerformed

    private void btSuaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaNXBActionPerformed
        // TODO add your handling code here:
        if (txtMaNXB.getText().length() <= 0) {
            return;
        }
        macuNXB = txtMaNXB.getText();
        ktThemNXB = false;
        KhoaMoNXB(false);
        txtMaNXB.requestFocus();
        ColorButtonAdd();
    }//GEN-LAST:event_btSuaNXBActionPerformed

    private void txtTenNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNXBActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        // TODO add your handling code here:
        LayNguonNhapSach();
    }//GEN-LAST:event_button12ActionPerformed

    private void btSuaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaSachActionPerformed
        // TODO add your handling code here:
        ktThemSach = false;
        KhoaMoSach(false);

        ColorButtonAdd();

//        if (txtMaSach.getText().length() <= 0 || txtTenSach.getText().length() <= 0 || txtTacGia.getText().length() <= 0
//                || txtNamXuatBan.getText().length() <= 0) {
//            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Bạn chưa nhập đầy dử thông tin");
//            panel.showNotification();
//            txtTenSach.requestFocus();
//            return;
//        } else {
//            masach = txtMaSach.getText();
//            tensach = txtTenSach.getText();
//
//            tenTL = jcbTheLoai.getSelectedItem().toString();
//            for (int i = 0; i < arrTL.size(); i++) {
//                if (arrTL.get(i).getTenTL().equals(tenTL) == true) {
//                    maTL = arrTL.get(i).getMaTL();
//                }
//            }
//
//            tacgia = txtTacGia.getText();
//            tenNXB = jcbNXB.getSelectedItem().toString();
//            for (int i = 0; i < arrNXB.size(); i++) {
//                if (arrNXB.get(i).getTenNXB().equals(tenNXB) == true) {
//                    maNXB = arrNXB.get(i).getMaNXB();
//                }
//            }
//            namxuatban = txtNamXuatBan.getText();
//
//            Model_Sach temp = new Model_Sach(masach, tensach, maTL, tenTL, tacgia, maNXB, tenNXB, namxuatban, 0);
////            if(ktThemSach==true){
////                Sach_Controller.ThemMoi(temp);
////                Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm thành công");
////                panel.showNotification();
////                LayNguonSach();
////                
////            }else 
//                if(ktThemSach==false){
//                Sach_Controller.CapNhat(temp);
//                Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Sửa thành công");
//                panel.showNotification();
//                LayNguonSach();
//            }
//            
//        }
    }//GEN-LAST:event_btSuaSachActionPerformed

    private void btLuuSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLuuSachActionPerformed
        // TODO add your handling code here:
        if (txtMaSach.getText().length() <= 0 || txtTenSach.getText().length() <= 0 || txtTacGia.getText().length() <= 0
                || txtNamXuatBan.getText().length() <= 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Bạn chưa nhập đầy dử thông tin");
            panel.showNotification();
            txtTenSach.requestFocus();
            return;
        }
        // thuc hien ghi

        masach = txtMaSach.getText();
        tensach = txtTenSach.getText();

        tenTL = jcbTheLoai.getSelectedItem().toString();
        for (int i = 0; i < arrTL.size(); i++) {
            if (arrTL.get(i).getTenTL().equals(tenTL) == true) {
                maTL = arrTL.get(i).getMaTL();
            }
        }

        tacgia = txtTacGia.getText();
        tenNXB = jcbNXB.getSelectedItem().toString();
        for (int i = 0; i < arrNXB.size(); i++) {
            if (arrNXB.get(i).getTenNXB().equals(tenNXB) == true) {
                maNXB = arrNXB.get(i).getMaNXB();
            }
        }
        namxuatban = txtNamXuatBan.getText();

        Model_Sach temp = new Model_Sach(masach, tensach, maTL, tenTL, tacgia, maNXB, tenNXB, namxuatban, 0);
        if (ktThemSach == true) {
            try {
                Sach_Controller.ThemMoi(temp);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm mới thành công");
            panel.showNotification();
        } else if (ktThemSach == false) {
            System.out.println("Sửa");
            try {
                Sach_Controller.CapNhat(temp);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Sửa thành công");
            panel.showNotification();

        }
        XoaTrangSach();
        try {
            LayNguonSach();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        KhoaMoSach(true);
        ColorButtonReload();
    }//GEN-LAST:event_btLuuSachActionPerformed

    private void btNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhapSachActionPerformed
        txtDialogmanv.setText(login.manv);
        txtDialogtennv.setText(login.tennv);
        String[] colum = {"Mã Sách", "Tên Sách", "Số Lượng"};
        DefaultTableModel tblnhapsach = new DefaultTableModel(colum, 0);
        tbDialognhapsach.setModel(tblnhapsach);
        int checkBoxColumnIndex = 0; // Chỉ mục của cột checkbox trong JTable
        for (int row = 0; row < tbSach.getRowCount(); row++) {
            boolean isChecked = (boolean) tbSach.getValueAt(row, checkBoxColumnIndex);

            if (isChecked) {
                tblnhapsach.addRow(new Object[]{tbSach.getValueAt(row, 1).toString(), tbSach.getValueAt(row, 2).toString(), 0
                });
            }
        }
        if (tbDialognhapsach.getRowCount() == 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Chưa chọn sách");
            panel.showNotification();
        } else {
            jDialog1.setLocationRelativeTo(null);
            jDialog1.setVisible(true);
        }


    }//GEN-LAST:event_btNhapSachActionPerformed

    private void btThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemSachActionPerformed
        try {
            try {
                txtMaSach.setText(Sach_Controller.automasach());
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        ktThemSach = true;
        KhoaMoSach(false);
        txtTenSach.requestFocus();
        ColorButtonAdd();


    }//GEN-LAST:event_btThemSachActionPerformed

    private void btReloadNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReloadNXBActionPerformed
        // TODO add your handling code here:
        XoaTrangNXB();
        try {
            LayNguonNXB();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        KhoaMoNXB(true);
        ColorButtonReload();
    }//GEN-LAST:event_btReloadNXBActionPerformed

    private void txtDienThoaiNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDienThoaiNXBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDienThoaiNXBActionPerformed

    private void tbNXBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNXBMouseClicked
        // TODO add your handling code here:
        int index = tbNXB.getSelectedRow();
        TableModel model = tbNXB.getModel();
        maNXB = model.getValueAt(index, 0).toString();
        tenNXB = model.getValueAt(index, 1).toString();
        diachiNXB = model.getValueAt(index, 2).toString();
        emailNXB = model.getValueAt(index, 3).toString();
        dienthoaiNXB = model.getValueAt(index, 4).toString();

        txtMaNXB.setText(maNXB);
        txtTenNXB.setText(tenNXB);
        txtDiaChiNXB.setText(diachiNXB);
        txtEmailNXB.setText(emailNXB);
        txtDienThoaiNXB.setText(dienthoaiNXB);
    }//GEN-LAST:event_tbNXBMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btReloadSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReloadSachActionPerformed
        // TODO add your handling code here:
        XoaTrangSach();
        try {
            LayNguonSach();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        KhoaMoSach(true);
        ColorButtonReload();
    }//GEN-LAST:event_btReloadSachActionPerformed

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        // TODO add your handling code here:
        int index = tbSach.getSelectedRow();
        TableModel model = tbSach.getModel();
        masach = model.getValueAt(index, 1).toString();
        tensach = model.getValueAt(index, 2).toString();
        maTLsach = model.getValueAt(index, 3).toString();
        tacgia = model.getValueAt(index, 4).toString();
        manxbsach = model.getValueAt(index, 5).toString();
        namxuatban = model.getValueAt(index, 6).toString();
        soluong = model.getValueAt(index, 7).toString();
        txtMaSach.setText(masach);
        txtTenSach.setText(tensach);
        txtNamXuatBan.setText(namxuatban);
        txtSoLuong.setText(soluong);
        txtTacGia.setText(tacgia);
        jcbNXB.setSelectedIndex(vtNXB(manxbsach));
        jcbTheLoai.setSelectedIndex(vtTheLoai(maTLsach));


    }//GEN-LAST:event_tbSachMouseClicked

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_button3ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:

        try {
            try {
                txtDilogMaTL.setText(TheLoai_Controller.automatl());
            } catch (IOException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtDilogMaTL.setEditable(false);
        jDialogTheLoai.setLocationRelativeTo(null);
        jDialogTheLoai.setVisible(true);


    }//GEN-LAST:event_jLabel14MouseClicked

    private void btThemTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemTLActionPerformed
        // TODO add your handling code here:

        if (txtdilogTenTL.getText().length() <= 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Nhập đầy đủ thông tin");
            panel.showNotification();
            txtdilogTenTL.requestFocus();
            return;

        } else {
            try {
                if (TheLoai_Controller.KiemTraTrungMaTen(txtdilogTenTL.getText()) == true) {
                    Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Trùng tên");
                    panel.showNotification();
                    
                } else {
                    try {
                        TheLoai_Controller.ThemMoi(txtDilogMaTL.getText(), txtdilogTenTL.getText());
                        try {
                            LayNguonCBO();
                        } catch (IOException ex) {
                            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JSONException ex) {
                            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm thành công");
                        panel.showNotification();
                        jDialogTheLoai.setVisible(false);
                        
                    } catch (JSONException ex) {
                        Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            } catch (JSONException ex) {
                Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_btThemTLActionPerformed

    private void txtTimKiemSachCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemSachCaretUpdate
        try {
            // TODO add your handling code here:
            LayNguonSach();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemSachCaretUpdate

    private void txtDialogmanvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDialogmanvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDialogmanvActionPerformed

    private void btDialogluunhapsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDialogluunhapsachActionPerformed
        int rowcount = tbDialognhapsach.getRowCount();
        for (int row = 0; row < rowcount; row++) {
            if (tbDialognhapsach.getValueAt(row, 2).toString().equals("0")) {
                Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Nhập thiếu số lượng sách " + tbDialognhapsach.getValueAt(row, 1).toString());
                panel.showNotification();
                return;
            }
        }
        String manhap = "";
        try {
            manhap += NhapSach_Controller.automanhap();
            String sql = "insert into chitietnhapsach values";
            for (int row = 0; row < rowcount; row++) {
                if (row < rowcount - 1) {
                    sql += "('" + manhap + "', '" + tbDialognhapsach.getValueAt(row, 0).toString() + "', '" + tbDialognhapsach.getValueAt(row, 2).toString() + "'),";
                } else {
                    sql += "('" + manhap + "', '" + tbDialognhapsach.getValueAt(row, 0).toString() + "', '" + tbDialognhapsach.getValueAt(row, 2).toString() + "')";
                }
            }
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngay = dateFormat.format(currentDate);
            NhapSach_Controller.ThemMoi(manhap, txtDialogmanv.getText(), ngay);
            ChiTietNhapSach_Controller.ThemMoi(sql);
            jDialog1.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            LayNguonSach();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btDialogluunhapsachActionPerformed

    private void btDialogthoatnhapsachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDialogthoatnhapsachActionPerformed
        jDialog1.setVisible(false);
    }//GEN-LAST:event_btDialogthoatnhapsachActionPerformed

    private void txtTimKiemNhapSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemNhapSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemNhapSachActionPerformed

    private void tbNhapSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhapSachMouseClicked
        // TODO add your handling code here:
        int index = tbNhapSach.getSelectedRow();
        System.out.println("sàhih");
        String manhap = tbNhapSach.getValueAt(index, 0).toString();
        System.out.println(manhap);
        try {
            LayNguonChiTietNhapSach(manhap);
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbNhapSachMouseClicked

    private void txtTimKiemNXBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemNXBCaretUpdate
        try {
            // TODO add your handling code here:
            LayNguonNXB();
        } catch (IOException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemNXBCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateEnd;
    private com.toedter.calendar.JDateChooser DateStart;
    private swing.button btDialogluunhapsach;
    private swing.button btDialogthoatnhapsach;
    private swing.button btLuuNXB;
    private swing.button btLuuSach;
    private swing.button btNhapSach;
    private swing.button btReloadNXB;
    private swing.button btReloadSach;
    private swing.button btSuaNXB;
    private swing.button btSuaSach;
    private swing.button btThemNXB;
    private swing.button btThemSach;
    private swing.button btThemTL;
    private swing.button btXoaNXB;
    private swing.button button12;
    private swing.button button3;
    private swing.button button5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialogTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPNXB;
    private javax.swing.JPanel jPNhapSach;
    private javax.swing.JPanel jPQuanLyNhapSach;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbNXB;
    private javax.swing.JComboBox<String> jcbTheLoai;
    private java.awt.Label label1;
    private swing.Table tbChiTietNhapSach;
    private swing.Table tbDialognhapsach;
    private swing.Table tbNXB;
    private swing.Table tbNhapSach;
    private swing.Table tbSach;
    private javax.swing.JTextField txtDiaChiNXB;
    private javax.swing.JTextField txtDialogmanv;
    private javax.swing.JTextField txtDialogtennv;
    private javax.swing.JTextField txtDienThoaiNXB;
    private javax.swing.JTextField txtDilogMaTL;
    private javax.swing.JTextField txtEmailNXB;
    private javax.swing.JTextField txtMaNXB;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNamXuatBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenNXB;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTimKiemNXB;
    private javax.swing.JTextField txtTimKiemNhapSach;
    private javax.swing.JTextField txtTimKiemSach;
    private javax.swing.JTextField txtdilogTenTL;
    // End of variables declaration//GEN-END:variables
}
