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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author REFKY
 */
public class Tambah_Populasi extends javax.swing.JFrame {

    /**
     * Creates new form Tambah_Populasi
     */
    Connection koneksi;
    String action;
    public Tambah_Populasi(boolean modal, String act, String id_populasi) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        showIkan();
        showTempat();
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_populasi);
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
    public void showTempat(){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * from tempat ";
            ResultSet rs = stmt.executeQuery(query);
            
            select_tempat.removeAllItems();
            select_tempat.addItem("--Pilih Tempat--");
            while (rs.next()){
                select_tempat.addItem(rs.getString("nama_tempat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public void simpanData(){
        String id_populasi = txt_kode.getText();
        int iikan = select_ikan.getSelectedIndex();
        String ikan = String.valueOf(iikan);
        int ttempat = select_tempat.getSelectedIndex();
        String tempat = String.valueOf(ttempat);
        String ukuran = txt_ukuran.getText();
        String jumlah_jantan = txt_jumlah_jantan.getText();
        String jumlah_betina = txt_jumlah_betina.getText();
         
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO populasi_ikan(id_populasi,id_tempat,id_ikan,ukuran,jumlah_jantan,jumlah_betina) "
                    + "VALUES('"+id_populasi+"', '"+tempat+"', '"+ikan+"', '"+ukuran+"', '"+jumlah_jantan+"', '"+jumlah_betina+"')";
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
      public void showData(String id_populasi){
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM populasi_ikan WHERE id_populasi ='"+id_populasi+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_populasi"));
            int indexTempat = Integer.parseInt(rs.getString("id_tempat"));
            select_tempat.setSelectedIndex(indexTempat);
            int indexIkan = Integer.parseInt(rs.getString("id_ikan"));
            select_ikan.setSelectedIndex(indexIkan);
            txt_ukuran.setText(rs.getString("ukuran"));
            txt_jumlah_jantan.setText(rs.getString("jumlah_jantan"));
            txt_jumlah_betina.setText(rs.getString("jumlah_betina"));
            System.out.println(query);
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Pada Query");
        }
    }
      public void EditData(){
        String id_populasi = txt_kode.getText();
        int iikan = select_ikan.getSelectedIndex();
        String ikan = String.valueOf(iikan);
        int ttempat = select_tempat.getSelectedIndex();
        String tempat = String.valueOf(ttempat);
        String ukuran = txt_ukuran.getText();
        String jumlah_jantan = txt_jumlah_jantan.getText();
        String jumlah_betina = txt_jumlah_betina.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE populasi_ikan SET id_tempat = '"+tempat+"',"
                    + "id_ikan ='"+ikan+"',"
                    + "ukuran ='"+ukuran+"',"
                    + "jumlah_jantan ='"+jumlah_jantan+"',"
                    + "jumlah_betina ='"+jumlah_betina+"' WHERE id_populasi = '"+id_populasi+"'";
            
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
        txt_jumlah_jantan = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_jumlah_betina = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        select_tempat = new javax.swing.JComboBox();
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
        jLabel2.setText("Nama Ikan");

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel3.setText("Kode");

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel4.setText("Nama Tempat");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel5.setText("Jumlah Jantan");

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

        txt_jumlah_jantan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_jumlah_jantan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_jumlah_jantan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah_jantanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel7.setText("Ukuran");

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel6.setText("Jumlah Betina");

        txt_jumlah_betina.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_jumlah_betina.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_jumlah_betina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jumlah_betinaActionPerformed(evt);
            }
        });

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

        select_tempat.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_tempat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersedia", "Tidak Tersedia" }));

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
                            .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jumlah_jantan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(select_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(393, 393, 393))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt_jumlah_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(391, 391, 391))))
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
                    .addComponent(select_tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(select_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlah_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jumlah_betina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(101, 101, 101)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(164, 164, 164))
        );

        jPanel12.setBackground(new java.awt.Color(97, 212, 195));

        jLabel27.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Data Populasi");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(528, 528, 528)
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
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void txt_jumlah_jantanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah_jantanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah_jantanActionPerformed

    private void txt_jumlah_betinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jumlah_betinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jumlah_betinaActionPerformed

    private void btn_simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseEntered
        // TODO add your handling code here:
        btn_simpan.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_simpanMouseEntered

    private void btn_simpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseExited
        // TODO add your handling code here:
        btn_simpan.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_simpanMouseExited

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

    private void btn_kembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembaliMouseEntered
        // TODO add your handling code here:
        btn_kembali.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_kembaliMouseEntered

    private void btn_kembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembaliMouseExited
        // TODO add your handling code here:
        btn_kembali.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_kembaliMouseExited

    private void btn_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembaliActionPerformed
        // TODO add your handling code here:
        TemplateAdmin.MainFrameAdmin mfa = new TemplateAdmin.MainFrameAdmin();
        mfa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_kembaliActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_Populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private java.awt.Panel panel1;
    private javax.swing.JComboBox select_ikan;
    private javax.swing.JComboBox select_tempat;
    private javax.swing.JTextField txt_jumlah_betina;
    private javax.swing.JTextField txt_jumlah_jantan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_ukuran;
    // End of variables declaration//GEN-END:variables
}
