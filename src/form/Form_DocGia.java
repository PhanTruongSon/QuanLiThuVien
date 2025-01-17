/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import controler.DocGia_Controller;
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
import model.Model_DocGia;
import notification.Notification;
import org.json.JSONException;
import swing.button;

/**
 *
 * @author Think Pad
 */
public class Form_DocGia extends javax.swing.JPanel {

    public static DefaultTableModel tblDanhSach;
    public static List<Model_DocGia> arr = new ArrayList<>();
    public static String MaDG, TenDG, NgaySinh, sGioiTinh, SDT, Email, HanDung;
    public static Date date;
    public static boolean ktThem, gioitinh;
    public static String macu, sMaDG;

    public static SimpleDateFormat sDF = new SimpleDateFormat("yyyy-mm-dd");

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

    public Form_DocGia() throws SQLException, IOException, JSONException {
        initComponents();
        tblDanhSach = (DefaultTableModel) tbDocGia.getModel();
        sMaDG = "";
        LayNguon();
        KhoaMo(true);
    }

    public void XoaTrang() {
        txtMadocgia.setText("");
        txtTendocgia.setText("");
        txtDienthoai.setText("");
        txtEmail.setText("");
        jDateChooserHanDung.setDate(null);
        jDateChooserNgaySinh.setDate(null);

    }

    // hiển thị kết quả tìm kiếm
    private void hienThiKetQua(List<Model_DocGia> ketQua) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã độc giả");
        model.addColumn("Tên độc giả");
        model.addColumn("Ngày sinh");
        model.addColumn("Giới tính");
        model.addColumn("Điện thoại");
        model.addColumn("Email");
        model.addColumn("Hạn dùng");
        model.setRowCount(0);

        for (Model_DocGia docGia : ketQua) {
            Object[] row = new Object[7];
            row[0] = docGia.getMaDG();
            row[1] = docGia.getTenDG();
            row[2] = docGia.getNgaySinh();
            row[3] = docGia.getGioiTinh();
            row[4] = docGia.getDienThoai();
            row[5] = docGia.getEmail();
            row[6] = docGia.getHanDung();
            model.addRow(row);
        }

        tbDocGia.setModel(model);
    }

    public void LayNguon() throws IOException, JSONException {
        try {
            arr = DocGia_Controller.LayNguonDG(sMaDG);
        } catch (SQLException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDanhSach.setRowCount(0); // Xóa trắng
        arr.forEach(p -> {
            tblDanhSach.addRow(new Object[]{
                p.getMaDG(), p.getTenDG(), p.getNgaySinh(), p.getGioiTinh(),
                p.getDienThoai(), p.getEmail(), p.getHanDung()

            });
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        txtMadocgia = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtTendocgia = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtDienthoai = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDocGia = new swing.Table();
        jcbGioiTinh = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btSua = new swing.button();
        btXoa = new swing.button();
        btLuu = new swing.button();
        btThem = new swing.button();
        btTimKiem = new swing.button();
        txtTimKiem = new javax.swing.JTextField();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        jDateChooserHanDung = new com.toedter.calendar.JDateChooser();
        btReload = new swing.button();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtMadocgia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMadocgia.setBorder(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên Độc giả");

        txtTendocgia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTendocgia.setBorder(null);
        txtTendocgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTendocgiaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Ngày sinh");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Giới tính");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Điện thoại");

        txtDienthoai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDienthoai.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Email");

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEmail.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Hạn dùng");

        tbDocGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Độc giả", "Tên Độc giả", "Ngày sinh", "Giới tính", "Điện thoại", "Email", "Hạn dùng"
            }
        ));
        tbDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDocGiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDocGia);

        jcbGioiTinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jcbGioiTinh.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Độc giả");

        btSua.setText("Sửa");
        btSua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setText("Xóa");
        btXoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
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

        btThem.setText("Thêm");
        btThem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btTimKiem.setText("Tìm kiếm");
        btTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(txtTendocgia)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1)
                                            .addComponent(txtMadocgia, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                            .addComponent(jSeparator1)))
                                    .addComponent(jLabel3)
                                    .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                .addGap(298, 298, 298)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDienthoai)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtEmail)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                    .addComponent(jDateChooserHanDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jcbGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(btReload, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMadocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTendocgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(19, 19, 19)
                        .addComponent(jDateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserHanDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(14, 14, 14)
                .addComponent(jcbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem)
                    .addComponent(btTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btReload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1034, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTendocgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTendocgiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTendocgiaActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        // TODO add your handling code here:
        if (txtMadocgia.getText().length() <= 0) {
            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Bạn hãy chọn độc giả cần sửa!");
            panel.showNotification();
            txtMadocgia.requestFocus();
            return;
        }
        macu = txtMadocgia.getText();
        ktThem = false;
        KhoaMo(false);
        txtMadocgia.requestFocus();
        ColorButtonAdd();


    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        // TODO add your handling code here:
        if (txtMadocgia.getText().length() <= 0) {
            return;
        }
        int kq = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa độc giả không[" + txtMadocgia.getText() + "]",
                "Thông báo ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (kq == JOptionPane.YES_NO_OPTION) {
            try {
                macu = txtMadocgia.getText();
                DocGia_Controller.Delete(macu);
                XoaTrang();
                LayNguon();
            } catch (IOException ex) {
                Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btXoaActionPerformed

    private void btLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLuuActionPerformed
        try {
            // TODO add your handling code here:
            // Kiem tra du lieu rong hay khong
            
            if (txtMadocgia.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã độc giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtMadocgia.requestFocus();
                return;
            }
            if (txtTendocgia.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên độc giả", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtTendocgia.requestFocus();
                return;
            }
            if (txtDienthoai.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtDienthoai.requestFocus();
                return;
            }
            if (txtEmail.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập email", "Thông báo", JOptionPane.WARNING_MESSAGE);
                txtEmail.requestFocus();
                return;
            }
            // Kiểm tra tính hợp lệ của dữ liệu
            // Kiểm tra trùng mã
            macu = txtMadocgia.getText();
            
            // thực hiện ghi
            MaDG = txtMadocgia.getText();
            TenDG = txtTendocgia.getText();
            NgaySinh = sDF.format(jDateChooserNgaySinh.getDate());
            sGioiTinh = jcbGioiTinh.getSelectedItem().toString();
            SDT = txtDienthoai.getText();
            Email = txtEmail.getText();
            HanDung = sDF.format(jDateChooserHanDung.getDate());
            Model_DocGia temp = new Model_DocGia(MaDG, TenDG, NgaySinh, sGioiTinh, SDT, Email, HanDung);
            System.out.println(ktThem);
            if (ktThem == true) {
                try {
                    if (DocGia_Controller.KiemTraTrungMa(txtMadocgia.getText(), ktThem, txtMadocgia.getText()) == true) {
                        
                        Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Bạn nhập trùng mã Độc Giả");
                        panel.showNotification();
                        txtMadocgia.requestFocus();
                        return;
                        
                    }
                    DocGia_Controller.ThemMoi(temp);
                    Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Thêm mới thành công");
                    panel.showNotification();
                } catch (JSONException ex) {
                    Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                if (ktThem == false) {
                    
                    try {
                        if (DocGia_Controller.KiemTraTrungMa(txtMadocgia.getText(), ktThem, txtMadocgia.getText()) == true) {
                            txtMadocgia.requestFocus();
                            DocGia_Controller.CapNhat(temp);
                            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.SUCCESS, Notification.Location.BOTTOM_RIGHT, "Sửa  thành công");
                            panel.showNotification();
                            
                        } else {
                            Notification panel = new Notification((JFrame) SwingUtilities.getWindowAncestor(this), Notification.Type.WARNING, Notification.Location.BOTTOM_RIGHT, "Sửa  không thành công");
                            panel.showNotification();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            LayNguon();
            KhoaMo(false);
            ColorButtonReload();
        } catch (IOException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btLuuActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        // TODO add your handling code here:

        ktThem = true;
        KhoaMo(false);
        txtMadocgia.requestFocus();
        ColorButtonAdd();


    }//GEN-LAST:event_btThemActionPerformed

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        try {
            String tenDocGia = txtTimKiem.getText();
            List<Model_DocGia> ketQua = DocGia_Controller.TimKiemTheoTen(tenDocGia);
            hienThiKetQua(ketQua);
        } catch (JSONException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btTimKiemActionPerformed

    private void btReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReloadActionPerformed
        try {
            // TODO add your handling code here:
            
            XoaTrang();
            txtTimKiem.setText("");
            LayNguon();
            KhoaMo(true);
            ColorButtonReload();
        } catch (IOException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btReloadActionPerformed

    private void tbDocGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDocGiaMouseClicked
        // TODO add your handling code here:
        int index = tbDocGia.getSelectedRow();
        TableModel model = tbDocGia.getModel();
        MaDG = model.getValueAt(index, 0).toString();
        TenDG = model.getValueAt(index, 1).toString();
        NgaySinh = model.getValueAt(index, 2).toString();
        sGioiTinh = model.getValueAt(index, 3).toString();
        SDT = model.getValueAt(index, 4).toString();
        Email = model.getValueAt(index, 5).toString();
        HanDung = model.getValueAt(index, 6).toString();

        txtMadocgia.setText(MaDG);
        txtTendocgia.setText(TenDG);
        try {
            date = sDF.parse(NgaySinh);
            jDateChooserNgaySinh.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }
        jcbGioiTinh.setSelectedIndex(sGioiTinh.equalsIgnoreCase("Nam") ? 0 : 1);
        txtDienthoai.setText(SDT);
        txtEmail.setText(Email);
        try {
            date = sDF.parse(HanDung);
            jDateChooserHanDung.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_tbDocGiaMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        try {
            // TODO add your handling code here:
            String tenDocGia = txtTimKiem.getText();
            List<Model_DocGia> ketQua = DocGia_Controller.TimKiemTheoTen(tenDocGia);
            hienThiKetQua(ketQua);
        } catch (JSONException ex) {
            Logger.getLogger(Form_DocGia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.button btLuu;
    private swing.button btReload;
    private swing.button btSua;
    private swing.button btThem;
    private swing.button btTimKiem;
    private swing.button btXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooserHanDung;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JComboBox<String> jcbGioiTinh;
    private swing.Table tbDocGia;
    private javax.swing.JTextField txtDienthoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMadocgia;
    private javax.swing.JTextField txtTendocgia;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
