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
public class Tambah_Pemijahan extends javax.swing.JFrame {

    /**
     * Creates new form Tambah_Pemijahan
     */
    Connection koneksi;
    String action;
    ResultSet rs;
  
    public Tambah_Pemijahan(boolean modal, String act, String id_pemijahan) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        showPetugas();
        showTempat();
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_pemijahan);
        }
    }

    private Tambah_Pemijahan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            select_tempat.addItem("--Pilih Tempat--");
            while (rs.next()){
                select_tempat.addItem(rs.getString("nama_tempat"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void simpanData() {
        String id_pemijahan = txt_kode.getText();
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        int petugass = select_petugas.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        String jumlah_indukan = txt_indukan.getText();
        String jumlah_jenis = txt_jenis.getText();
        String hasil_telur = txt_hasil_telur.getText();
        String hasil_larva = txt_hasil_larva.getText();
        String ukuran = txt_ukuran.getText();
        int tempatt = select_tempat.getSelectedIndex();
        String id_tempat = String.valueOf(tempatt);
        String keterangan = txt_keterangan.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO pemijahan(id_pemijahan,tanggal,id_petugas,jumlah_indukan,jumlah_jenis,hasil_telur,hasil_larva,ukuran,id_tempat,keterangan) "
                    + "VALUES ('"+id_pemijahan+"', '"+tanggal+"', '"+id_petugas+"', '"+jumlah_indukan+"', '"+jumlah_jenis+"', '"+hasil_telur+"', '"+hasil_larva+"', '"+ukuran+"', '"+id_tempat+"', '"+keterangan+"')";
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
        public void showData(String id_pemijahan){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM pemijahan WHERE id_pemijahan ='"+id_pemijahan+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_pemijahan"));
            String tanggal = rs.getString("tanggal");
            String[] revertDate = revertDate(tanggal);
            cmbTahun.setSelectedItem(revertDate[0]);
            cmbBulan.setSelectedItem(revertDate[1]);
            cmbHari.setSelectedItem(revertDate[2]);
            int indexPetugas = Integer.parseInt(rs.getString("id_petugas"));
            select_petugas.setSelectedIndex(indexPetugas);
            txt_indukan.setText(rs.getString("jumlah_indukan"));
            txt_jenis.setText(rs.getString("jumlah_jenis"));
            txt_hasil_telur.setText(rs.getString("hasil_telur"));
            txt_hasil_larva.setText(rs.getString("hasil_larva"));
            txt_ukuran.setText(rs.getString("ukuran"));
            int indexTempat = Integer.parseInt(rs.getString("id_tempat"));
            select_tempat.setSelectedIndex(indexTempat);
            txt_keterangan.setText(rs.getString("keterangan"));
            
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Pada Query");
        }
    }
        
    public void EditData(){
        String id_pemijahan = txt_kode.getText();
        String tahun  = cmbTahun.getSelectedItem().toString();
        String bulan  = cmbBulan.getSelectedItem().toString();
        String hari  = cmbHari.getSelectedItem().toString();
        String tanggal = tahun + "-" + bulan + "-" + hari;
        int petugass = select_petugas.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        String jumlah_indukan = txt_indukan.getText();
        String jumlah_jenis = txt_jenis.getText();
        String hasil_telur = txt_hasil_telur.getText();
        String hasil_larva = txt_hasil_larva.getText();
        String ukuran = txt_ukuran.getText();
        int ttempat = select_tempat.getSelectedIndex();
        String id_tempat = String.valueOf(ttempat);;
        String keterangan = txt_keterangan.getText();

        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE pemijahan SET tanggal = '"+tanggal+"',"
            + "id_petugas = '"+id_petugas+"',"
            + "jumlah_indukan = '"+jumlah_indukan+"',"
            + "jumlah_jenis = '"+jumlah_jenis+"',"
            + "hasil_telur = '"+hasil_telur+"',"
            + "hasil_larva = '"+hasil_larva+"',"
            + "ukuran = '"+ukuran+"',"
            + "id_tempat = '"+id_tempat+"',"
            + "keterangan = '"+keterangan+"' WHERE id_pemijahan = '"+id_pemijahan+"'";
            
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
        jLabel5 = new javax.swing.JLabel();
        txt_kode = new javax.swing.JTextField();
        txt_indukan = new javax.swing.JTextField();
        txt_jenis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_hasil_telur = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        select_tempat = new javax.swing.JComboBox();
        btn_simpan = new javax.swing.JButton();
        btn_kembali = new javax.swing.JButton();
        select_petugas = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txt_keterangan = new javax.swing.JTextField();
        cmbHari = new javax.swing.JComboBox();
        cmbBulan = new javax.swing.JComboBox();
        cmbTahun = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txt_hasil_larva = new javax.swing.JTextField();
        txt_ukuran = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(36, 47, 65));
        panel1.setName(""); // NOI18N
        panel1.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel2.setText("Petugas");

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel3.setText("Kode");

        jLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel4.setText("Tanggal");

        jLabel5.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel5.setText("Jumlah Jenis");

        txt_kode.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kode.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeActionPerformed(evt);
            }
        });

        txt_indukan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_indukan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_indukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_indukanActionPerformed(evt);
            }
        });

        txt_jenis.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_jenis.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_jenisActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel7.setText("Jumlah Indukan");

        jLabel6.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel6.setText("Hasil Telur");

        txt_hasil_telur.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_hasil_telur.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_hasil_telur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hasil_telurActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel9.setText("Tempat");

        select_tempat.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        select_tempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_tempatActionPerformed(evt);
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

        select_petugas.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel10.setText("Keterangan");

        txt_keterangan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_keterangan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_keteranganActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel8.setText("Hasil Larva");

        txt_hasil_larva.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_hasil_larva.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_hasil_larva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hasil_larvaActionPerformed(evt);
            }
        });

        txt_ukuran.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_ukuran.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_ukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ukuranActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel11.setText("Ukuran");

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
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(select_tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(187, 187, 187)
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_indukan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(select_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_hasil_telur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_hasil_larva, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(select_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_indukan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hasil_telur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hasil_larva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(select_tempat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
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

    private void txt_indukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_indukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_indukanActionPerformed

    private void txt_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_jenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jenisActionPerformed

    private void txt_hasil_telurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hasil_telurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hasil_telurActionPerformed

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

    private void txt_keteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_keteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_keteranganActionPerformed

    private void txt_hasil_larvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hasil_larvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hasil_larvaActionPerformed

    private void txt_ukuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ukuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ukuranActionPerformed

    private void select_tempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_tempatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_tempatActionPerformed

    private void cmbTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTahunActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_Pemijahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Pemijahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Pemijahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Pemijahan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tambah_Pemijahan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox cmbBulan;
    private javax.swing.JComboBox cmbHari;
    private javax.swing.JComboBox cmbTahun;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JComboBox select_petugas;
    private javax.swing.JComboBox select_tempat;
    private javax.swing.JTextField txt_hasil_larva;
    private javax.swing.JTextField txt_hasil_telur;
    private javax.swing.JTextField txt_indukan;
    private javax.swing.JTextField txt_jenis;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_ukuran;
    // End of variables declaration//GEN-END:variables
}
