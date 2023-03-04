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
 * @author Afif
 */
public class Tambah_Opname extends javax.swing.JFrame {

    /**
     * Creates new form Tambah_Opname
     */
    String action;
    ResultSet rs;
    Connection koneksi;
    
    
    public Tambah_Opname(boolean modal, String act, String id_opname) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_opname);
        }
    }

    private Tambah_Opname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void simpanData() {
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        String id_opname = txt_kode.getText();
        String nama_barang = txt_NamaBarang.getText();
        String jenis = txt_Jenis.getText();
        String satuan = cmb_satuan.getSelectedItem().toString();
        String stok = txt_Stok.getText();
        String jumlah_pemakaian = txt_pemakaian.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO stok_opname(id_opname,tanggal,nama_barang,jenis,satuan,stok,jumlah_pemakaian) "
                    + "VALUES ('"+id_opname+"', '"+tanggal+"', '"+nama_barang+"', '"+jenis+"', '"+satuan+"', '"+stok+"', '"+jumlah_pemakaian+"')";
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
        public void showData(String id_opname){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM stok_opname WHERE id_opname ='"+id_opname+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_opname"));
            String tanggal = rs.getString("tanggal");
            String[] revertDate = revertDate(tanggal);
            cmbTahun.setSelectedItem(revertDate[0]);
            cmbBulan.setSelectedItem(revertDate[1]);
            cmbHari.setSelectedItem(revertDate[2]);
            txt_Jenis.setText(rs.getString("jenis"));
            txt_NamaBarang.setText(rs.getString("nama_barang"));
            txt_Stok.setText(rs.getString("stok"));
            txt_pemakaian.setText(rs.getString("jumlah_pemakaian"));            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Pada Query");
        }
    }
        
    public void EditData(){
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        String id_opname = txt_kode.getText();
        String nama_barang = txt_NamaBarang.getText();
        String jenis = txt_Jenis.getText();
        String satuan = cmb_satuan.getSelectedItem().toString();
        String stok = txt_Stok.getText();
        String jumlah_pemakaian = txt_pemakaian.getText();

        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE stok_opname SET tanggal = '"+tanggal+"',"
            + "nama_barang = '"+nama_barang+"',"
            + "jenis = '"+jenis+"',"
            + "satuan = '"+satuan+"',"
            + "stok = '"+stok+"',"
            + "jumlah_pemakaian = '"+jumlah_pemakaian+"' WHERE id_opname = '"+id_opname+"'";
            
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

        jCheckBox1 = new javax.swing.JCheckBox();
        panel6 = new java.awt.Panel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        btn_simpan5 = new javax.swing.JButton();
        btn_kembali5 = new javax.swing.JButton();
        txt_NamaBarang = new javax.swing.JTextField();
        cmbTahun = new javax.swing.JComboBox();
        cmbBulan = new javax.swing.JComboBox();
        cmbHari = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_Jenis = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_Stok = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_pemakaian = new javax.swing.JTextField();
        cmb_satuan = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel6.setBackground(new java.awt.Color(36, 47, 65));
        panel6.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel13.setText("Kode");

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel14.setText("Nama Barang");

        txt_kode.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kode.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        btn_simpan5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btn_simpan5.setText("Simpan");
        btn_simpan5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_simpan5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_simpan5MouseExited(evt);
            }
        });
        btn_simpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan5ActionPerformed(evt);
            }
        });

        btn_kembali5.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btn_kembali5.setText("Kembali");
        btn_kembali5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_kembali5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_kembali5MouseExited(evt);
            }
        });
        btn_kembali5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kembali5ActionPerformed(evt);
            }
        });

        txt_NamaBarang.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_NamaBarang.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_NamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NamaBarangActionPerformed(evt);
            }
        });

        cmbTahun.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbTahun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047" }));
        cmbTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTahunActionPerformed(evt);
            }
        });

        cmbBulan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbHari.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbHari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel15.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel15.setText("Tanggal");

        jLabel16.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel16.setText("Jenis");

        txt_Jenis.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_Jenis.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_Jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_JenisActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel21.setText("Stok");

        txt_Stok.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_Stok.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_Stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_StokActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel22.setText("Jumlah Pemakaian");

        txt_pemakaian.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_pemakaian.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_pemakaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pemakaianActionPerformed(evt);
            }
        });

        cmb_satuan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmb_satuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "Liter" }));

        jLabel23.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel23.setText("Satuan");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pemakaian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_satuan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(238, 238, 238)))
                .addGap(373, 373, 373))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_Stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_pemakaian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_satuan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel17.setBackground(new java.awt.Color(97, 212, 195));

        jLabel27.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Data Opname");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(607, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(530, 530, 530))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kodeActionPerformed

    private void btn_simpan5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan5MouseEntered
        // TODO add your handling code here:
        btn_simpan5.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_simpan5MouseEntered

    private void btn_simpan5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan5MouseExited
        // TODO add your handling code here:
        btn_simpan5.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_simpan5MouseExited

    private void btn_simpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan5ActionPerformed
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
    }//GEN-LAST:event_btn_simpan5ActionPerformed

    private void btn_kembali5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembali5MouseEntered
        // TODO add your handling code here:
        btn_kembali5.setBackground(new Color(97,212,195));
    }//GEN-LAST:event_btn_kembali5MouseEntered

    private void btn_kembali5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembali5MouseExited
        // TODO add your handling code here:
        btn_kembali5.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_btn_kembali5MouseExited

    private void btn_kembali5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kembali5ActionPerformed
        // TODO add your handling code here:
        TemplateAdmin.MainFrameAdmin mfa = new TemplateAdmin.MainFrameAdmin();
        mfa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_kembali5ActionPerformed

    private void txt_NamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NamaBarangActionPerformed

    private void cmbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTahunActionPerformed

    private void txt_JenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_JenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_JenisActionPerformed

    private void txt_StokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_StokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_StokActionPerformed

    private void txt_pemakaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pemakaianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pemakaianActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_Opname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Opname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Opname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Opname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tambah_Opname().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali5;
    private javax.swing.JButton btn_simpan5;
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JComboBox cmbHari;
    private javax.swing.JComboBox cmbTahun;
    private javax.swing.JComboBox<String> cmb_satuan;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private java.awt.Panel panel6;
    private javax.swing.JTextField txt_Jenis;
    private javax.swing.JTextField txt_NamaBarang;
    private javax.swing.JTextField txt_Stok;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_pemakaian;
    // End of variables declaration//GEN-END:variables
}
