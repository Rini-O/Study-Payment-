/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package StudentPayment;

import java.io.BufferedReader;
import java.io.*;
import java.lang.System.Logger;
import java.sql.PreparedStatement;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer_user
 */
public class Pembayaran extends javax.swing.JFrame {

    Connection conn;
    Statement stmt;
    PreparedStatement pstmt = null;

    private DefaultTableModel tableModel;
    private final String[] columns = {"id Pembayaran", "NIS", "Nama", "Tanggal", "Jenis ", "Bulan", "Jumlah"};
    private final ArrayList<String[]> dataList = new ArrayList<>();

    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/StudentPaymentFix";
    String user = "postgres";
    String password = "rini123tok";
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);
    
    public void harga (){
         String pembayaran = cbJenis.getSelectedItem().toString();
        int jumlah = 0;
        if (pembayaran.equals("SPP")) {
            jumlah = 500000;
            jumlahLbl.setText(String.valueOf(jumlah));
        } else if (pembayaran.equals("Uang Bangunan")) {
            jumlah = 200000;
            jumlahLbl.setText(String.valueOf(jumlah));
        } else if (pembayaran.equals("Uang Makan")) {
            jumlah = 400000;
            jumlahLbl.setText(String.valueOf(jumlah));
        }
    }

    public void clear() {
        txtIdPembayaran.setText("");
        txtNis.setText("");
        txtTgl.setText("");
        txtCari.setText("");
    }

    public final void tampilNamaSantri() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
            String sql = "select nama from santri where nis = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNis.getText());
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                String nama = res.getString("nama");
                Namas.setText(nama);
             } else {
            // Jika NIS tidak ditemukan, tampilkan pop-up JOptionPane
            JOptionPane.showMessageDialog(this, "NIS tidak ditemukan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

        // Tutup ResultSet dan PreparedStatement
        res.close();
        ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public final void getAllData() {
        try {
            Connection conn;
            Class.forName(driver);
            dataList.clear();
            String sql = "SELECT P.id_pembayaran, P.nis, S.nama, P.tanggal, P.jenis_pembayaran, P.bulan, P.jumlah "
                    + "FROM pembayaran P "
                    + "JOIN santri S ON P.nis = S.nis";
            
            conn = DriverManager.getConnection(koneksi, user, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
            final ResultSet data = ps.getResultSet();
            while (data.next()) {
                final String[] row = new String[]{
                    data.getString("id_pembayaran"),
                    data.getString("nis"),
                    data.getString("nama"),
                    data.getString("tanggal"),
                    data.getString("jenis_pembayaran"),
                    data.getString("bulan"),
                    data.getString("jumlah")};
                dataList.add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void search() {
        String sql = "SELECT nis FROM pembayaran ORDER BY nis"; // Mengambil semua ID pembeli
        try ( PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            List<Integer> ids = new ArrayList<>(); // Untuk menyimpan semua ID yang ada
            while (rs.next()) {
                String No_Urut = rs.getString("nis");
                int idNumber = Integer.parseInt(No_Urut.replaceAll("\\D", "")); // Ambil angka dari ID
                ids.add(idNumber);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Format ID tidak valid.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void searchData(String keyword) {
        try {
            String sql = "SELECT * FROM pembayaran WHERE id_pembayaran LIKE ? OR nis LIKE ? ";


            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");


            // Menjalankan query dan mendapatkan hasil
            ResultSet rs = pstmt.executeQuery();

            // Menggunakan DbUtils untuk menampilkan hasil di tabel
            tblPembayaran.setModel(DbUtils.resultSetToTableModel(rs));
            clear();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat mencari data: " + ex.getMessage());
        }
    }

    public final void refreshModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        for (String[] data : dataList) {
            model.addRow(data);
        }
        tblPembayaran.setModel(model);
    }

    /**
     * Creates new form Pembaran
     */
    public Pembayaran() {
        initComponents();
        this.getAllData();
        this.refreshModel();
        harga();
        tampilNamaSantri();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdPembayaran = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNis = new javax.swing.JTextField();
        btnCari1 = new java.awt.Button();
        jLabel4 = new javax.swing.JLabel();
        Namas = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTgl = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbJenis = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnTgl = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPembayaran = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari2 = new java.awt.Button();
        btnInsert = new java.awt.Button();
        btnUpdate = new java.awt.Button();
        btnDelete = new java.awt.Button();
        cbBulan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jumlahLbl = new javax.swing.JLabel();
        btnBack = new java.awt.Button();

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("PEMBAYARAN");

        jLabel2.setText("ID Pembayaran");

        txtIdPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPembayaranActionPerformed(evt);
            }
        });

        jLabel3.setText("NIS");

        txtNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisActionPerformed(evt);
            }
        });

        btnCari1.setLabel("Cari");
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nama");

        Namas.setText("-");

        jLabel7.setText("Tanggal");

        txtTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglActionPerformed(evt);
            }
        });

        jLabel8.setText("Jenis Pembayaran");

        cbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SPP", "Uang Bangunan", "Uang Makan", " " }));
        cbJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJenisActionPerformed(evt);
            }
        });

        jLabel10.setText("Jumlah");

        btnTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTglActionPerformed(evt);
            }
        });

        tblPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPembayaranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblPembayaranMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblPembayaran);

        jLabel11.setText("NIS");

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });

        btnCari2.setLabel("Cari");
        btnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari2ActionPerformed(evt);
            }
        });

        btnInsert.setLabel("INSERT");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setLabel("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setLabel("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei ", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember", " " }));

        jLabel5.setText("Bulan");

        jumlahLbl.setText("-");

        btnBack.setLabel("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Namas, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jumlahLbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnInsert, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIdPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCari2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(Namas)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTgl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jumlahLbl))
                        .addGap(66, 66, 66)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        btnInsert.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPembayaranActionPerformed

    private void txtTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari2ActionPerformed
        String keyword = txtCari.getText();  // Mengambil kata kunci dari txtSearch
        searchData(keyword);

    }//GEN-LAST:event_btnCari2ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (txtIdPembayaran.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID Pembayaran harus terisi untuk update data");

        } else {
            try {
                String sql = "UPDATE pembayaran SET bulan = ?, jumlah = ?, jenis_pembayaran = ? WHERE id_pembayaran = ?";
                pstmt = conn.prepareStatement(sql);

                String idPembayaran = txtIdPembayaran.getText();
                String bulan = cbBulan.getSelectedItem().toString();
                int jumlah = Integer.parseInt(jumlahLbl.getText());
                String jenis = cbJenis.getSelectedItem().toString();

                pstmt.setString(1, bulan);    
                pstmt.setInt(2, jumlah);        
                pstmt.setString(3, jenis);      
                pstmt.setString(4, idPembayaran);
                // Eksekusi query
                pstmt.executeUpdate();
                this.getAllData();
                this.refreshModel();
//                conn.commit();

                // Tutup koneksi
//                pstmt.close();
//                conn.close();
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        bersih();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        tampilNamaSantri();
    }//GEN-LAST:event_btnCari1ActionPerformed

    private void btnTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTglActionPerformed
        tanggal();
    }//GEN-LAST:event_btnTglActionPerformed

    private void cbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJenisActionPerformed
        // TODO add your handling code here:
        String pembayaran = cbJenis.getSelectedItem().toString();
        int jumlah = 0;
        if (pembayaran.equals("SPP")) {
            jumlah = 500000;
            jumlahLbl.setText(String.valueOf(jumlah));
        } else if (pembayaran.equals("Uang Bangunan")) {
            jumlah = 200000;
            jumlahLbl.setText(String.valueOf(jumlah));
        } else if (pembayaran.equals("Uang Makan")) {
            jumlah = 400000;
            jumlahLbl.setText(String.valueOf(jumlah));
        }
    }//GEN-LAST:event_cbJenisActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if (txtIdPembayaran.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data harus terisi");

        } else {
            try {
                conn.setAutoCommit(false);

                String sql = "INSERT INTO pembayaran(id_pembayaran, nis, tanggal, bulan, jumlah, jenis_pembayaran) VALUES(?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);

                String idPembayaran = txtIdPembayaran.getText();
                String nis = txtNis.getText();

                String dateString = txtTgl.getText();
                java.sql.Date dateValue = java.sql.Date.valueOf(dateString);

                String Bulan = cbBulan.getSelectedItem().toString();
                int jumlah = Integer.parseInt(jumlahLbl.getText());
                String Jenis = cbJenis.getSelectedItem().toString();

                // Set parameter untuk query SQL
                pstmt.setString(1, idPembayaran);
                pstmt.setString(2, nis);

                pstmt.setDate(3, dateValue);

                pstmt.setString(4, cbBulan.getSelectedItem().toString());
                pstmt.setInt(5, jumlah);
                pstmt.setString(6, cbJenis.getSelectedItem().toString());
                // Eksekusi query
                pstmt.executeUpdate();
                conn.commit();
                this.getAllData();
                this.refreshModel();

                // Tutup koneksi
                pstmt.close();
                conn.close();
                JOptionPane.showMessageDialog(null, "Success to insert");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        bersih();

    }//GEN-LAST:event_btnInsertActionPerformed

    private void txtNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNisActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtIdPembayaran.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "ID Pembayaran harus terisi untuk menghapus data");

        } else {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(koneksi, user, password);
                conn.setAutoCommit(false);

                String sql = "DELETE FROM pembayaran WHERE id_pembayaran = ?";
                pstmt = conn.prepareStatement(sql);

                String idPembayaran = txtIdPembayaran.getText();

                // Set parameter untuk query SQL
                pstmt.setString(1, idPembayaran);

                // Eksekusi query
                int rowsDeleted = pstmt.executeUpdate();
                conn.commit();

                // Cek apakah data berhasil dihapus
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                }

                // Tutup koneksi
                pstmt.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        bersih();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembayaranMouseClicked
        int row = tblPembayaran.getSelectedRow();
        txtIdPembayaran.setText(tblPembayaran.getValueAt(row, 0).toString());
        txtNis.setText(tblPembayaran.getValueAt(row, 1).toString());
        Namas.setText(tblPembayaran.getValueAt(row, 2).toString());
        txtTgl.setText(tblPembayaran.getValueAt(row, 3).toString());
        cbBulan.setSelectedItem(tblPembayaran.getValueAt(row, 5).toString());
        cbJenis.setSelectedItem(tblPembayaran.getValueAt(row, 4).toString());
        jumlahLbl.setText(tblPembayaran.getValueAt(row, 7).toString());

    }//GEN-LAST:event_tblPembayaranMouseClicked

    private void tblPembayaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembayaranMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPembayaranMouseEntered

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
       this.dispose();
       Menu2 a = new Menu2();
       a.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void tanggal() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());
        txtTgl.setText(currentDate);
    }

    private void bersih() {
        txtIdPembayaran.setText("");
        txtNis.setText("");
        txtTgl.setText("");
        cbJenis.setSelectedIndex(0);
        cbBulan.setSelectedIndex(0);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Namas;
    private java.awt.Button btnBack;
    private java.awt.Button btnCari1;
    private java.awt.Button btnCari2;
    private java.awt.Button btnDelete;
    private java.awt.Button btnInsert;
    private javax.swing.JRadioButton btnTgl;
    private java.awt.Button btnUpdate;
    private javax.swing.JComboBox<String> cbBulan;
    private javax.swing.JComboBox<String> cbJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jumlahLbl;
    private javax.swing.JTable tblPembayaran;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdPembayaran;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtTgl;
    // End of variables declaration//GEN-END:variables
}
