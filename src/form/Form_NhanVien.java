/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import controler.NhanVien_Controller;
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
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Model_NhanVien;
import notification.Notification;
import org.json.JSONException;
import swing.button;

/**
 *
 * @author Think Pad
 */
public class Form_NhanVien extends javax.swing.JPanel {

    public static List<Model_NhanVien> arrNV = new ArrayList<>();
    public static String maNV, tenNV, sgioiTinh, ngaySinh, diaChi, dienThoai, email, schucVu, ngayVaoLam;
    public static Date date;
    public static String macu;
    public static boolean ktThem, chucvu, gioitinh;
    public static SimpleDateFormat sDF = new SimpleDateFormat("yyyy-mm-dd");
    String[] columnNamesNhanVien = {"Mã Nhân Viên", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Điện thoại", "Email", "Chức vụ", "Ngày vào làm"};
    DefaultTableModel tblDanhSach = new DefaultTableModel(columnNamesNhanVien, 0);

    /**
     * Creates new form Form_NhanVien
     */
    public void KhoaMo(boolean b) {
        btThem.setEnabled(b);
        btSua.setEnabled(b);
        btXoa.setEnabled(b);

        btLuu.setEnabled(!b);
        btThem.setStyle(button.ButtonStyle.PRIMARY);
        btSua.setStyle(button.ButtonStyle.PRIMARY);
        btXoa.setStyle(button.ButtonStyle.PRIMARY);

        btLuu.setStyle(button.ButtonStyle.SECONDARY);

    }
    // Khi ấn vào nút thêm đổi màu các button khác

    public void ColorButtonAdd() {
        btThem.setStyle(button.ButtonStyle.SECONDARY);
        btSua.setStyle(button.ButtonStyle.SECONDARY);
        btXoa.setStyle(button.ButtonStyle.SECONDARY);
        btReload.setStyle(button.ButtonStyle.DESTRUCTIVE);
        btLuu.setStyle(button.ButtonStyle.DESTRUCTIVE);

    }// Khi ấn vào button reload đổi màu các button khác

    public void ColorButtonReload() {
        btThem.setStyle(button.ButtonStyle.PRIMARY);
        btSua.setStyle(button.ButtonStyle.PRIMARY);
        btXoa.setStyle(button.ButtonStyle.PRIMARY);

    }

    public Form_NhanVien() throws JSONException, IOException {
        initComponents();
        tblDanhSach = (DefaultTableModel) tbNhanVien.getModel();
        LayNguon();
        KhoaMo(true);

    }

    public void XoaTrang() {
        txtManhanvien.setText("");
        txtHoTen.setText("");
        txtDienthoai.setText("");
        txtEmail.setText("");

    }

    public void LayNguon() throws JSONException, IOException {

        try {
            if (txtTimKiem.getText().equals("")) {
                arrNV = NhanVien_Controller.LayNguonNV("");
            } else {
                arrNV = NhanVien_Controller.LayNguonNV(txtTimKiem.getText());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblDanhSach.setRowCount(0); // Xóa trắng
        arrNV.forEach(p -> {
            tblDanhSach.addRow(new Object[]{
                p.getMaNV(), p.getHoTen(), p.getGioiTinh(), p.getNgaySinh(), p.getDiaChi(),
                p.getDienThoai(), p.getEmail(), p.getChucVu(), p.getNgayVaoLam()

            });
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new swing.Table();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbGioiTinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtEmail = new javax.swing.JTextField();
        txtManhanvien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        txtDienthoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtHoTen = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        btSua = new swing.button();
        btLuu = new swing.button();
        btXoa = new swing.button();
        txtTimKiem = new javax.swing.JTextField();
        btThem = new swing.button();
        btTimKiem = new swing.button();
        jDateChooserNgayVaoLam = new com.toedter.calendar.JDateChooser();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        btReload = new swing.button();
        jcbChuaVu = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Địa Chỉ", "Điện thoại", "Email", "Chức vụ", "Ngày vào làm"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Giới tính");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Nhân Viên");

        jcbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jcbGioiTinh.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Email");

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmail.setBorder(null);

        txtManhanvien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtManhanvien.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Điện thoại");

        txtDienthoai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDienthoai.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Chức vụ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtHoTen.setBorder(null);
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Ngày sinh");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDiaChi.setBorder(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Ngày vào làm");

        btSua.setText("Sửa");
        btSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btLuu.setText("Lưu thông tin");
        btLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLuu.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLuuActionPerformed(evt);
            }
        });

        btXoa.setText("Xóa");
        btXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        btThem.setText("Thêm");
        btThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btTimKiem.setText("Tìm kiếm");
        btTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btTimKiem.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });

        btReload.setText("RELOAD");
        btReload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btReload.setStyle(swing.button.ButtonStyle.DESTRUCTIVE);
        btReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReloadActionPerformed(evt);
            }
        });

        jcbChuaVu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcbChuaVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Thủ Thư" }));
        jcbChuaVu.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(btLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btReload, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(txtHoTen)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(txtManhanvien)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                    .addComponent(jLabel3)
                    .addComponent(jcbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jcbChuaVu, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDienthoai, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEmail)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 245, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jDateChooserNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(133, 133, 133))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtManhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbChuaVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btReload, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        // TODO add your handling code here:
        if (txtManhanvien.getText().length() <= 0) {
            return;
        }
        macu = txtManhanvien.getText();
        ktThem = false;
        KhoaMo(false);
        txtManhanvien.requestFocus();
        ColorButtonAdd();

    }//GEN-LAST:event_btSuaActionPerformed

    private void btLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLuuActionPerformed
        // TODO add your handling code here:
        if (txtManhanvien.getText().length() <= 0 || txtHoTen.getText().length() <= 0 || txtEmail.getText().length() <= 0
                || txtDienthoai.getText().length() <= 0 || txtDiaChi.getText().length() <= 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Bạn chưa nhập đầy dử thông tin");
            panel.showNotification();
            txtManhanvien.requestFocus();
            return;
        }
        if (!txtDienthoai.getText().matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là một số có 10 ký tự", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtDienthoai.requestFocus();
            return;
        }

        if (!txtEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Email phải chứa ký tự @", "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return;
        }

        macu = txtManhanvien.getText();

        // thực hiện ghi
        maNV = txtManhanvien.getText();
        tenNV = txtHoTen.getText();
        sgioiTinh = jcbGioiTinh.getSelectedItem().toString();
        ngaySinh = sDF.format(jDateChooserNgaySinh.getDate());
        dienThoai = txtDienthoai.getText();
        email = txtEmail.getText();
        schucVu = jcbChuaVu.getSelectedItem().toString();
        ngayVaoLam = sDF.format(jDateChooserNgayVaoLam.getDate());
        Model_NhanVien temp = new Model_NhanVien(maNV, tenNV, sgioiTinh, ngaySinh, diaChi, dienThoai, email, schucVu, ngayVaoLam);
        if (ktThem == true) {
            try {
                if (NhanVien_Controller.KiemTraTrungMa(txtManhanvien.getText(), ktThem, txtManhanvien.getText()) == true) {
                    Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Bạn nhập trùng mã Nhân Viên");
                    panel.showNotification();
                    txtManhanvien.requestFocus();
                    return;
                    
                }
                NhanVien_Controller.ThemMoi(temp);
                Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm mới thành công");
                panel.showNotification();
            } catch (JSONException ex) {
                Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (ktThem == false) {

                try {
                    if (NhanVien_Controller.KiemTraTrungMa(txtManhanvien.getText(), ktThem, txtManhanvien.getText()) == true) {
                        txtManhanvien.requestFocus();
                        NhanVien_Controller.CapNhat(temp);
                        Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Sửa  thành công");
                        panel.showNotification();
                        
                    } else {
                        Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Sửa  không thành công");
                        panel.showNotification();
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            LayNguon();
        } catch (JSONException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        XoaTrang();
        KhoaMo(true);
        ColorButtonReload();
    }//GEN-LAST:event_btLuuActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        if (txtManhanvien.getText().length() <= 0) {
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên không[" + txtManhanvien.getText() + "]",
                "Thông báo ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kq == JOptionPane.YES_NO_OPTION) {
            macu = txtManhanvien.getText();
            NhanVien_Controller.Delete(macu);
            XoaTrang();
            try {
                LayNguon();
            } catch (JSONException ex) {
                Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btXoaActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:
        ktThem = true;
        KhoaMo(false);
        txtManhanvien.requestFocus();
        ColorButtonAdd();
    }//GEN-LAST:event_btThemActionPerformed

    private void btReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReloadActionPerformed
        // TODO add your handling code here:
        XoaTrang();
        txtTimKiem.setText("");
        try {
            LayNguon();
        } catch (JSONException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        KhoaMo(true);
        ColorButtonReload();

    }//GEN-LAST:event_btReloadActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:

        int index = tbNhanVien.getSelectedRow();
        TableModel model = tbNhanVien.getModel();
        maNV = model.getValueAt(index, 0).toString();
        tenNV = model.getValueAt(index, 1).toString();
        sgioiTinh = model.getValueAt(index, 2).toString();
        ngaySinh = model.getValueAt(index, 3).toString();
        diaChi = model.getValueAt(index, 4).toString();
        dienThoai = model.getValueAt(index, 5).toString();
        email = model.getValueAt(index, 6).toString();
        schucVu = model.getValueAt(index, 7).toString();
        ngayVaoLam = model.getValueAt(index, 8).toString();

        txtManhanvien.setText(maNV);
        txtHoTen.setText(tenNV);
        try {
            date = sDF.parse(ngaySinh);
            jDateChooserNgaySinh.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jcbGioiTinh.setSelectedIndex(sgioiTinh.equalsIgnoreCase("Nam") ? 0 : 1);
        txtDienthoai.setText(dienThoai);
        txtDiaChi.setText(diaChi);
        txtEmail.setText(email);
        jcbChuaVu.setSelectedIndex(schucVu.equalsIgnoreCase("Quản Lý") ? 0 : 1);
        try {
            date = sDF.parse(ngayVaoLam);
            jDateChooserNgayVaoLam.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        try {
            // TODO add your handling code here:
            LayNguon();
        } catch (JSONException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Form_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.button btLuu;
    private swing.button btReload;
    private swing.button btSua;
    private swing.button btThem;
    private swing.button btTimKiem;
    private swing.button btXoa;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private com.toedter.calendar.JDateChooser jDateChooserNgayVaoLam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JComboBox<String> jcbChuaVu;
    private javax.swing.JComboBox<String> jcbGioiTinh;
    private swing.Table tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienthoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtManhanvien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
