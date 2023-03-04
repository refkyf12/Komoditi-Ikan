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
public class TambahRiwayatPengadaan extends javax.swing.JFrame {

    /**
     * Creates new form TambahRiwayatPengadaan
     */
    Connection koneksi;
    String action;
    ResultSet rs;
    public TambahRiwayatPengadaan(boolean modal, String act, String id_pengadaan) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_pengadaan);
        }
    }
     public void simpanData() {
        String id_pengadaan = txt_kode.getText();
        String pengadaan_ikan = txt_ikan.getText();
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        String jumlah_jantan = txt_jantan.getText();
        String jumlah_betina = txt_betina.getText();
        String keterangan = txt_keterangan.getText();
        String nama_kpa = txt_kpa.getText();
        String nama_pptk = txt_pptk.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO riwayat_pengadaan(id_pengadaan,pengadaan_ikan,jumlah_jantan,jumlah_betina,tanggal,keterangan,nama_kpa,nama_pptk) "
                    + "VALUES ('"+id_pengadaan+"', '"+pengadaan_ikan+"', '"+jumlah_jantan+"', '"+jumlah_betina+"', '"+tanggal+"', '"+keterangan+"', '"+nama_kpa+"', '"+nama_pptk+"')";
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
    
        public String[] revertDate(String tanggal) {
        String[] data = tanggal.split("-");
        return data;
    }
        public void showData(String id_pengadaan){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM riwayat_pengadaan WHERE id_pengadaan ='"+id_pengadaan+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_pengadaan"));
            txt_ikan.setText(rs.getString("pengadaan_ikan"));
            String tanggal = rs.getString("tanggal");
            String[] revertDate = revertDate(tanggal);
            cmbTahun.setSelectedItem(revertDate[0]);
            cmbBulan.setSelectedItem(revertDate[1]);
            cmbHari.setSelectedItem(revertDate[2]);
            txt_jantan.setText(rs.getString("jumlah_jantan"));
            txt_betina.setText(rs.getString("jumlah_betina"));
            txt_keterangan.setText(rs.getString("keterangan"));
            txt_kpa.setText(rs.getString("nama_kpa"));
            txt_pptk.setText(rs.getString("nama_pptk"));
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Pada Query");
        }
    }
        public void EditData(){
        String id_pengadaan = txt_kode.getText();
        String pengadaan_ikan = txt_ikan.getText();
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        String jumlah_jantan = txt_jantan.getText();
        String jumlah_betina = txt_betina.getText();
        String keterangan = txt_keterangan.getText();
        String nama_kpa = txt_kpa.getText();
        String nama_pptk = txt_pptk.getText();

        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE riwayat_pengadaan SET tanggal = '"+tanggal+"',"
            + "pengadaan_ikan = '"+pengadaan_ikan+"',"
            + "jumlah_jantan = '"+jumlah_jantan+"',"
            + "jumlah_betina = '"+jumlah_betina+"',"
            + "keterangan = '"+keterangan+"',"
            + "nama_kpa = '"+nama_kpa+"',"
            + "nama_pptk = '"+nama_pptk+"' WHERE id_pengadaan = '"+id_pengadaan+"'";
            
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
                System.out.println(query);
            }else{
                JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
                System.out.println(query);
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
        txt_kode = new javax.swing.JTextField();
        txt_ikan = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        cmbHari = new javax.swing.JComboBox();
        cmbBulan = new javax.swing.JComboBox();
        cmbTahun = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_jantan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_betina = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_keterangan = new javax.swing.JTextField();
        txt_kpa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_pptk = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(36, 47, 65));
        panel1.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        panel1.setName(""); // NOI18N
        panel1.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel2.setText("Pengadaan Ikan");

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel3.setText("Kode");

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel4.setText("Tanggal");

        txt_kode.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kode.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        txt_ikan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_ikan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_ikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ikanActionPerformed(evt);
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

        cmbHari.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbHari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbBulan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbTahun.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbTahun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047" }));
        cmbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTahunActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel5.setText("Jumlah Jantan");

        txt_jantan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_jantan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_jantan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jantanActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel6.setText("Jumlah Betina");

        txt_betina.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_betina.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_betina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_betinaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel7.setText("Keterangan");

        txt_keterangan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_keterangan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_keteranganActionPerformed(evt);
            }
        });

        txt_kpa.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kpa.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kpaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel8.setText("Nama KPA");

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel9.setText("Nama PPTK");

        txt_pptk.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_pptk.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_pptk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pptkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kpa, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pptk, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(393, 393, 393))))
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
                    .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_betina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_kpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_pptk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel12.setBackground(new java.awt.Color(97, 212, 195));

        jLabel27.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Data Pemijahan");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(541, 541, 541)
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
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 1372, Short.MAX_VALUE)
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

    private void txt_ikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ikanActionPerformed

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

    private void cmbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTahunActionPerformed

    private void txt_jantanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jantanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jantanActionPerformed

    private void txt_betinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_betinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_betinaActionPerformed

    private void txt_keteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_keteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_keteranganActionPerformed

    private void txt_kpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kpaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kpaActionPerformed

    private void txt_pptkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pptkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pptkActionPerformed

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
            java.util.logging.Logger.getLogger(TambahRiwayatPengadaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahRiwayatPengadaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahRiwayatPengadaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahRiwayatPengadaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JComboBox cmbHari;
    private javax.swing.JComboBox cmbTahun;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private java.awt.Panel panel1;
    private javax.swing.JTextField txt_betina;
    private javax.swing.JTextField txt_ikan;
    private javax.swing.JTextField txt_jantan;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_kpa;
    private javax.swing.JTextField txt_pptk;
    // End of variables declaration//GEN-END:variables
}
