/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package StudentPayment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author acer_user
 */
public class Pengeluaran extends javax.swing.JFrame {

    Connection conn;
    Statement stmt;
    PreparedStatement pstmt = null;
    String driver = "org.postgresql.Driver";
    String koneksi = "jdbc:postgresql://localhost:5432/StudentPaymentFix";
    String user = "postgres";
    String password = "rini123tok";
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(inputStreamReader);
    private String saldoTerupdate;

    /**
     * Creates new form Pengeluaran
     */
    public final void tampilInfoSantri() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
            String sql = "select nama,saldo from santri where nis = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNis.getText());
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                String nama = res.getString("nama");
                int saldo = res.getInt("saldo");
                Namas.setText(nama);
                saldoA.setText(String.valueOf(saldo));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NIS tidak ditemukan.");
        }
    }
    
    public final void tampilNIS() {
    Connection conn = null; // Pastikan koneksi dideklarasikan
    try {
        Class.forName(driver);
        conn = DriverManager.getConnection(koneksi, user, password);
        String sql = "SELECT nama, saldo FROM santri WHERE nis = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, txtNis.getText());
        ResultSet res = ps.executeQuery();

        // Cek apakah NIS ditemukan atau tidak
        if (res.next()) {
            String nama = res.getString("nama");
            int saldo = res.getInt("saldo");
            Namas.setText(nama);
            saldoA.setText(String.valueOf(saldo));
        } else {
            // Jika NIS tidak ditemukan, tampilkan pop-up JOptionPane
            JOptionPane.showMessageDialog(this, "NIS tidak ditemukan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

        // Tutup ResultSet dan PreparedStatement
        res.close();
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Tutup koneksi database jika tidak null
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



    public Pengeluaran() {
        initComponents();
        tampilInfoSantri();

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(koneksi, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String generateNewId(Connection conn) throws SQLException {
        String newId = "SPC0001";  // ID awal jika belum ada data

        // Query untuk mendapatkan ID pemesanan terakhir
        String sql = "SELECT id_pengeluaran FROM Pengeluaran ORDER BY id_pengeluaran DESC LIMIT 1";
        try (PreparedStatement statement = conn.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                String lastId = resultSet.getString("id_pengeluaran");
                System.out.println("ID terakhir dari database: '" + lastId + "'");  // Logging ID terakhir

                if (lastId != null && lastId.length() >= 4) {
                    // Hapus spasi sebelum parsing
                    lastId = lastId.trim(); // Menghapus spasi di awal dan akhir
                    try {
                        int lastIdNumber = Integer.parseInt(lastId.substring(3));  // Ambil angka dari ID terakhir
                        lastIdNumber++;  // Increment ID
                        newId = "SPC" + String.format("%04d", lastIdNumber); // Format ke ID baru
                    } catch (NumberFormatException e) {
                        System.out.println("Format ID tidak valid: " + lastId);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("ID terakhir tidak valid: " + lastId);
                }
            } else {
                System.out.println("Tidak ada ID pengeluaran yang ditemukan, menggunakan ID default.");
            }

        }

        return newId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIdStore = new javax.swing.JTextField();
        txtNis = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        txtJenis = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        btnCari = new java.awt.Button();
        Namas = new javax.swing.JLabel();
        btnInsert = new java.awt.Button();
        btnTgl = new javax.swing.JRadioButton();
        saldoA = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPengeluaran = new javax.swing.JTable();

        jTextField5.setText("jTextField5");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PENGELUARAN");

        jLabel2.setText("ID Pengeluaran");

        jLabel3.setText("NIS");

        jLabel4.setText("Nama");

        jLabel5.setText("Tanggal ");

        jLabel6.setText("Jenis");

        jLabel7.setText("Saldo Awal");

        jLabel9.setText("Total");

        txtIdStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdStoreActionPerformed(evt);
            }
        });

        txtNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisActionPerformed(evt);
            }
        });

        txtTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglActionPerformed(evt);
            }
        });

        txtJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisActionPerformed(evt);
            }
        });

        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        btnCari.setLabel("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        Namas.setText("-");

        btnInsert.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnInsert.setLabel("OK");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTglActionPerformed(evt);
            }
        });

        tblPengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPengeluaran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saldoA, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtNis, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                    .addComponent(txtIdStore)))
                                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTgl)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(57, 57, 57)
                                        .addComponent(Namas, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdStore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Namas)
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(btnTgl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saldoA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdStoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdStoreActionPerformed

    private void txtJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisActionPerformed

    private void txtNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNisActionPerformed

    private void txtTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        tampilNIS();
        tampilInfoSantri();

    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTglActionPerformed
        tanggal();
    }//GEN-LAST:event_btnTglActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed

        try {
            int response = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin melakukan transaksi?", "Konfirmasi transaksi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            conn = DriverManager.getConnection(koneksi, user, password);
            conn.setAutoCommit(false);

          
            if (response == JOptionPane.YES_OPTION) {
                String nis = txtNis.getText();
                int jumlah = Integer.parseInt(txtJumlah.getText());

                
                String querySaldo = "SELECT saldo FROM santri WHERE nis = ?"; // Ambil saldo santri 
                PreparedStatement psSaldo = conn.prepareStatement(querySaldo);
                psSaldo.setString(1, nis);
                ResultSet rs = psSaldo.executeQuery();

                // Jika saldo ditemukan, periksa apakah saldo mencukupi
                if (rs.next()) {
                    int saldoSaatIni = rs.getInt("saldo");
                    
                    if (saldoSaatIni >= jumlah) {                    
//                        String id_pengeluaran = txtIdStore.getText();  //melanjutkan transaksi
                        String dateString = txtTgl.getText();
                        java.sql.Date dateValue = java.sql.Date.valueOf(dateString);
                        String jenis_pengeluaran = txtJenis.getText();
                        
                        String newID = generateNewId(conn);
                        
                        String sql = "INSERT INTO pengeluaran (id_pengeluaran, nis, tanggal, jenis_pengeluaran, jumlah) VALUES ( ?,?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, newID);
                        ps.setString(2, nis);
                        ps.setDate(3, dateValue);
                        ps.setString(4, jenis_pengeluaran);
                        ps.setInt(5, jumlah);

                        String update = "UPDATE santri SET saldo = saldo - ?";
                        PreparedStatement psi = conn.prepareStatement(update);
                        psi.setInt(1, jumlah); // Update saldo berdasarkan jumlah pengeluaran

                       // Ambil saldo santri 
                        ps.executeUpdate();
                        psi.executeUpdate();
                        conn.commit();

                        JOptionPane.showMessageDialog(this, "Transaksi berhasil!\nSisa saldo: " + (saldoSaatIni - jumlah));
                    } else {
                        // Saldo tidak mencukupi
                        JOptionPane.showMessageDialog(this, "Transaksi gagal ! \n Saldo tidak mencukupi.");
                        conn.rollback();
                    }
                
                }
            } else {
                conn.rollback();
            }

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        showTable();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

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
            java.util.logging.Logger.getLogger(Pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pengeluaran().setVisible(true);
            }
        });
    }

    public void showTable() {
        try {           
            Class.forName(driver);
            String sql = "SELECT * FROM pengeluaran order by id_pengeluaran";
            conn = DriverManager.getConnection(koneksi, user, password);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {

                ResultSet rs = stmt.executeQuery(sql);
                this.tblPengeluaran.setModel(DbUtils.resultSetToTableModel(rs));
                while (rs.next()) {
                    System.out.println(String.valueOf(rs.getObject(1)) + " " + String.valueOf(rs.getObject(2)) + " " + String.valueOf(rs.getObject(3)) + " " + String.valueOf(rs.getObject(4)) + " " + String.valueOf(rs.getObject(5)));
                }
                conn.close();
            }

            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {

        }
    }

    private void tanggal() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());
        txtTgl.setText(currentDate);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Namas;
    private java.awt.Button btnCari;
    private java.awt.Button btnInsert;
    private javax.swing.JRadioButton btnTgl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel saldoA;
    private javax.swing.JTable tblPengeluaran;
    private javax.swing.JTextField txtIdStore;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNis;
    private javax.swing.JTextField txtTgl;
    // End of variables declaration//GEN-END:variables
}
