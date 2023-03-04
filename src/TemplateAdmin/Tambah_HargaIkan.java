/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateAdmin;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author REFKY
 */
public class Tambah_HargaIkan extends javax.swing.JFrame {

    /**
     * Creates new form Tambah_HargaIkan
     */
    Connection koneksi;
    String action;
    
    public Tambah_HargaIkan(boolean modal, String act, String id_daftar_harga) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        showIkan();
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_daftar_harga);
        }
    }
    public void showIkan(){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * from data_ikan ";
            ResultSet rs = stmt.executeQuery(query);
            
            select_ikan.removeAllItems();
            select_ikan.addItem("--Pilih Ikan--");
            while (rs.next()){
                select_ikan.addItem(rs.getString("nama_ikan"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void simpanData(){
        String id_daftarharga = txt_kode.getText();
        int iikan = select_ikan.getSelectedIndex();
        String ikan = String.valueOf(iikan);
        String ukuran = txt_ukuran.getText();
        String harga = txt_harga.getText();
        String satuan = txt_satuan.getText();
        String ketersediaan  = select_ketersediaan.getSelectedItem().toString();
        String jumlah = txt_jumlah.getText();
         
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO daftar_harga(id_daftar_harga,id_ikan,ukuran,harga,satuan,ketersediaan,jumlah_stok) "
                    + "VALUES('"+id_daftarharga+"', '"+ikan+"', '"+ukuran+"', '"+harga+"', '"+satuan+"', '"+ketersediaan+"', '"+jumlah+"')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data berhasil dimasukkan");
            }else{
                JOptionPane.showMessageDialog(null, "Data gagal dimasukkan");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada database");
        }
    }
    
    public void showData(String id_daftar_harga){
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM daftar_harga WHERE id_daftar_harga ='"+id_daftar_harga+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_daftar_harga"));
            int indexIkan = Integer.parseInt(rs.getString("id_ikan"));
            select_ikan.setSelectedIndex(indexIkan);
            txt_ukuran.setText(rs.getString("ukuran"));
            txt_harga.setText(rs.getString("harga"));
            txt_satuan.setText(rs.getString("satuan"));
            select_ketersediaan.setSelectedItem(rs.getString("ketersediaan"));
            txt_jumlah.setText(rs.getString("jumlah_stok"));
            System.out.println(query);
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Pada Query");
        }
    }
    
    public void EditData(){
        String id_daftar_harga = txt_kode.getText();
        int iikan = select_ikan.getSelectedIndex();
        String ikan = String.valueOf(iikan);
        String ukuran = txt_ukuran.getText();
        String harga = txt_harga.getText();
        String satuan = txt_satuan.getText();
        String ketersediaan  = select_ketersediaan.getSelectedItem().toString();
        String jumlah = txt_jumlah.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE daftar_harga SET id_ikan = '"+ikan+"',"
                    + "ukuran ='"+ukuran+"',"
                    + "harga ='"+harga+"',"
                    + "satuan ='"+satuan+"',"
                    + "ketersediaan ='"+ketersediaan+"',"
                    + "jumlah_stok ='"+jumlah+"' WHERE id_daftar_harga = '"+id_daftar_harga+"'";
            
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            }else{
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
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

        panel1 = new java.awt.Panel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        txt_ukuran = new javax.swing.JTextField();
        txt_harga = new javax.swing.JTextField();
        txt_satuan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        select_ketersediaan = new javax.swing.JComboBox();
        btn_simpan = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        select_ikan = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(36, 47, 65));
        panel1.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel2.setText("Ukuran");

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel3.setText("Kode");

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel4.setText("Nama Ikan");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel5.setText("Satuan");

        txt_kode.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kode.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        txt_ukuran.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_ukuran.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_ukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ukuranActionPerformed(evt);
            }
        });

        txt_harga.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_harga.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });

        txt_satuan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_satuan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_satuanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel7.setText("Harga");

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel6.setText("Ketersediaan");

        txt_jumlah.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_jumlah.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlahActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel9.setText("Jumlah");

        select_ketersediaan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_ketersediaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersedia", "Tidak Tersedia" }));

        btn_simpan.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_simpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_simpanMouseExited(evt);
            }
        });
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_kembali.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btn_kembali.setText("Kembali");
        btn_kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kembaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kembaliMouseExited(evt);
            }
        });
        btn_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembaliActionPerformed(evt);
            }
        });

        select_ikan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_ikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersedia", "Tidak Tersedia" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_satuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_jumlah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(select_ketersediaan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(393, 393, 393))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(select_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select_ketersediaan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(164, 164, 164))
        );

        jPanel12.setBackground(new java.awt.Color(97, 212, 195));

        jLabel27.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Data Harga Ikan Dan Pakan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(424, 424, 424)
                .addComponent(jLabel26)
                .addGap(34, 34, 34))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 1342, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeActionPerformed

    private void txt_ukuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ukuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ukuranActionPerformed

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void txt_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_satuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_satuanActionPerformed

    private void txt_jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlahActionPerformed

    private void btn_kembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembaliMouseEntered
        // TODO add your handling code here:
        btn_kembali.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_kembaliMouseEntered

    private void btn_kembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembaliMouseExited
        // TODO add your handling code here:
        btn_kembali.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_kembaliMouseExited

    private void btn_simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseEntered
        // TODO add your handling code here:
        btn_simpan.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_simpanMouseEntered

    private void btn_simpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseExited
        // TODO add your handling code here:
         btn_simpan.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_simpanMouseExited

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        TemplateAdmin.MainFrameAdmin mfa = new TemplateAdmin.MainFrameAdmin();
        mfa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_kembaliActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(action.equals("Edit")) {
            EditData();
            new TemplateAdmin.MainFrameAdmin().setVisible(true);
            this.dispose();
        } else { 
            simpanData();
        new TemplateAdmin.MainFrameAdmin().setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_HargaIkan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_HargaIkan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_HargaIkan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_HargaIkan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private java.awt.Panel panel1;
    private javax.swing.JComboBox select_ikan;
    private javax.swing.JComboBox select_ketersediaan;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_satuan;
    private javax.swing.JTextField txt_ukuran;
    // End of variables declaration//GEN-END:variables
}
