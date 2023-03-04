/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemplateAdmin;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author REFKY
 */
public class Tambah_PemberianPakan extends javax.swing.JFrame {

    /**
     * Creates new form Tambah_StokIndukan
     */
    String action;
    ResultSet rs;
    Connection koneksi;
    public Tambah_PemberianPakan(boolean modal, String act, String id_pemberianpakan) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (com.mysql.jdbc.Connection) databaseConnection.getKoneksi();
        action = act;
        showPetugas();
        showTempat();
        showIkan();
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showPemberianPakan(id_pemberianpakan);
        }
    }
    public void showPetugas(){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * from petugas ";
            ResultSet rs = stmt.executeQuery(query);
            
            select_petugas.removeAllItems();
            select_petugas.addItem("--Pilih Petugas--");
            while (rs.next()){
                select_petugas.addItem(rs.getString("nama_petugas"));
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
            select_tempat.addItem("--Pilih Petugas--");
            while (rs.next()){
                select_tempat.addItem(rs.getString("nama_tempat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
    public void simpanData() {
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        String id_pemberianpakan = txt_kode.getText();
        int petugass = select_petugas.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        int tempatt = select_tempat.getSelectedIndex();
        String id_tempat = String.valueOf(tempatt);
        int ikann = select_ikan.getSelectedIndex();
        String id_ikan = String.valueOf(ikann);
        String pakan_telor = txt_telor.getText();
        String pakan_cacing = txt_cacing.getText();
        String pakan_tepung = txt_tepung.getText();
        String pakan_f99 = txt_f99.getText();
        String keterangan = txt_keterangan.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO pemberian_pakan(id_pemberian_pakan,tanggal,id_petugas,id_tempat,id_ikan,pakan_telor,pakan_cacing,pakan_tepung,pakan_f99,keterangan) "
                    + "VALUES ('"+id_pemberianpakan+"', '"+tanggal+"', '"+id_petugas+"', '"+id_tempat+"', '"+id_ikan+"', '"+pakan_telor+"', '"+pakan_cacing+"', '"+pakan_tepung+"', '"+pakan_f99+"', '"+keterangan+"')";
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
        public void showPemberianPakan(String id_pemberianpakan){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pemberian_pakan WHERE id_pemberian_pakan ='"+id_pemberianpakan+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_pemberian_pakan"));
            String tanggal = rs.getString("tanggal");
            String[] revertDate = revertDate(tanggal);
            cmbTahun.setSelectedItem(revertDate[0]);
            cmbBulan.setSelectedItem(revertDate[1]);
            cmbHari.setSelectedItem(revertDate[2]);
            int indexPetugas = Integer.parseInt(rs.getString("id_petugas"));
            select_petugas.setSelectedIndex(indexPetugas);
            int indexTempat = Integer.parseInt(rs.getString("id_tempat"));
            select_tempat.setSelectedIndex(indexTempat);
            int indexIkan = Integer.parseInt(rs.getString("id_ikan"));
            select_ikan.setSelectedIndex(indexIkan);
            txt_telor.setText(rs.getString("pakan_telor"));
            txt_cacing.setText(rs.getString("pakan_cacing"));
            txt_tepung.setText(rs.getString("pakan_tepung"));
            txt_f99.setText(rs.getString("pakan_f99"));
            txt_keterangan.setText(rs.getString("keterangan"));            
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
        String id_pemberianpakan = txt_kode.getText();
        int petugass = select_petugas.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        int tempatt = select_tempat.getSelectedIndex();
        String id_tempat = String.valueOf(tempatt);
        int ikann = select_ikan.getSelectedIndex();
        String id_ikan = String.valueOf(ikann);
        String pakan_telor = txt_telor.getText();
        String pakan_cacing = txt_cacing.getText();
        String pakan_tepung = txt_tepung.getText();
        String pakan_f99 = txt_f99.getText();
        String keterangan = txt_keterangan.getText();

        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE pemberian_pakan SET tanggal = '"+tanggal+"',"
            + "id_petugas = '"+id_petugas+"',"
            + "id_tempat = '"+id_tempat+"',"
            + "id_ikan = '"+id_ikan+"',"
            + "pakan_telor = '"+pakan_telor+"',"
            + "pakan_cacing = '"+pakan_cacing+"',"
            + "pakan_tepung = '"+pakan_tepung+"',"
            + "pakan_f99 = '"+pakan_f99+"',"        
            + "keterangan = '"+keterangan+"' WHERE id_pemberian_pakan = '"+id_pemberianpakan+"'";
            
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

        panel6 = new java.awt.Panel();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        btn_simpan5 = new javax.swing.JButton();
        btn_kembali5 = new javax.swing.JButton();
        cmbTahun = new javax.swing.JComboBox();
        cmbBulan = new javax.swing.JComboBox();
        cmbHari = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        select_petugas = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        select_tempat = new javax.swing.JComboBox();
        select_ikan = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_telor = new javax.swing.JTextField();
        txt_cacing = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_tepung = new javax.swing.JTextField();
        txt_f99 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_keterangan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel6.setBackground(new java.awt.Color(36, 47, 65));
        panel6.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel13.setText("Kode");

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel14.setText("Nama Petugas\n");

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

        select_petugas.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_petugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel17.setText("Nama Tempat ");

        select_tempat.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_tempat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        select_ikan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_ikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel18.setText("Nama Ikan");

        jLabel19.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel19.setText("Pakan");

        txt_telor.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_telor.setPreferredSize(new java.awt.Dimension(69, 35));
        txt_telor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telorActionPerformed(evt);
            }
        });

        txt_cacing.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_cacing.setPreferredSize(new java.awt.Dimension(69, 35));

        jLabel21.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel21.setText("Telor");
        jLabel21.setToolTipText("");

        txt_tepung.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_tepung.setPreferredSize(new java.awt.Dimension(69, 35));

        txt_f99.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_f99.setPreferredSize(new java.awt.Dimension(69, 35));

        jLabel22.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel22.setText("Cacing");

        txt_keterangan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_keterangan.setPreferredSize(new java.awt.Dimension(69, 35));

        jLabel23.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel23.setText("Keterangan");

        jLabel24.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel24.setText("Tepung");

        jLabel25.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel25.setText("F99");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(279, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_kode, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(select_petugas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(select_tempat, 0, 393, Short.MAX_VALUE)
                            .addComponent(select_ikan, 0, 393, Short.MAX_VALUE)
                            .addComponent(txt_keterangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel24)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel25))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(txt_telor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cacing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tepung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_f99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addGap(373, 373, 373))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
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
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(select_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel17))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(select_tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel18))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(select_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cacing, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tepung, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_f99, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(97, 212, 195));

        jLabel27.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Data Pemberian Pakan");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addGap(486, 486, 486))
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
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 64, Short.MAX_VALUE))
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

    private void cmbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTahunActionPerformed

    private void txt_telorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telorActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_PemberianPakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_PemberianPakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_PemberianPakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_PemberianPakan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali5;
    private javax.swing.JButton btn_simpan5;
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JComboBox cmbHari;
    private javax.swing.JComboBox cmbTahun;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private java.awt.Panel panel6;
    private javax.swing.JComboBox select_ikan;
    private javax.swing.JComboBox select_petugas;
    private javax.swing.JComboBox select_tempat;
    private javax.swing.JTextField txt_cacing;
    private javax.swing.JTextField txt_f99;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_telor;
    private javax.swing.JTextField txt_tepung;
    // End of variables declaration//GEN-END:variables
}
