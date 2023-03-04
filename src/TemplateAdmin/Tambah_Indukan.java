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
 * @author Afif
 */
public class Tambah_Indukan extends javax.swing.JFrame {

    Connection koneksi;
    String action;
    ResultSet rs;
    /**
     * Creates new form Tambah_Indukan
     */
    public Tambah_Indukan(boolean modal, String act, String id_stok_indukan) {
        initComponents();
        setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        koneksi = (Connection) databaseConnection.getKoneksi();
        action = act;
        showPetugas();
        showIkan();
        if  (action.equals("Edit")){
            txt_kode.setEnabled(false);
            showData(id_stok_indukan);
        }
    }

    private Tambah_Indukan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void showPetugas(){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * from petugas ";
            ResultSet rs = stmt.executeQuery(query);
            
            cmb_petugas.removeAllItems();
            cmb_petugas.addItem("--Pilih Petugas--");
            while (rs.next()){
                cmb_petugas.addItem(rs.getString("nama_petugas"));
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
            
            cmb_ikan.removeAllItems();
            cmb_ikan.addItem("--Pilih Ikan--");
            while (rs.next()){
                cmb_ikan.addItem(rs.getString("nama_ikan"));
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
        int petugass = cmb_petugas.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        String id_stok_ikan = txt_kode.getText();
        String banyak_induk_betina = txt_induk_betina.getText();
        String banyak_induk_jantan = txt_induk_jantan.getText();
        String calon_indukan_betina = txt_calon_betina.getText();
        String calon_indukan_jantan = txt_calon_jantan.getText();
        String jumlah_kematian = txt_kematian.getText();
        int ikann = cmb_ikan.getSelectedIndex();
        String id_ikan = String.valueOf(ikann);
        String keterangan = txt_keterangan.getText();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO stok_indukan(id_stok_indukan,id_ikan,tanggal,banyak_induk_betina,banyak_induk_jantan,jumlah_indukan,calon_indukan_betina,calon_indukan_jantan,jumlah_calon_indukan,jumlah_kematian,total_keseluruhan,id_petugas,keterangan) "
                    + "VALUES ('"+id_stok_ikan+"', '"+id_ikan+"', '"+tanggal+"', '"+banyak_induk_betina+"', '"+banyak_induk_jantan+"', '"+0+"', '"+calon_indukan_betina+"', '"+calon_indukan_jantan+"', '"+0+"', '"+jumlah_kematian+"', '"+0+"', '"+id_petugas+"', '"+keterangan+"')";
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
        public void showData(String id_stok_indukan){
        try {
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM stok_indukan WHERE id_stok_indukan ='"+id_stok_indukan+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            txt_kode.setText(rs.getString("id_stok_indukan"));
            int indexIkan = Integer.parseInt(rs.getString("id_ikan"));
            String tanggal = rs.getString("tanggal");
            String[] revertDate = revertDate(tanggal);
            cmbTahun.setSelectedItem(revertDate[0]);
            cmbBulan.setSelectedItem(revertDate[1]);
            cmbHari.setSelectedItem(revertDate[2]);
            cmb_ikan.setSelectedIndex(indexIkan);
            txt_induk_betina.setText(rs.getString("banyak_induk_betina"));
            txt_induk_jantan.setText(rs.getString("banyak_induk_jantan"));
            txt_calon_betina.setText(rs.getString("calon_indukan_betina"));
            txt_calon_jantan.setText(rs.getString("calon_indukan_jantan"));
            txt_kematian.setText(rs.getString("jumlah_kematian"));
            int indexPetugas = Integer.parseInt(rs.getString("id_petugas"));
            cmb_petugas.setSelectedIndex(indexPetugas);
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
        int petugass = cmb_ikan.getSelectedIndex();
        String id_petugas = String.valueOf(petugass);
        String id_stok_indukan = txt_kode.getText();
        String banyak_induk_betina = txt_induk_betina.getText();
        String banyak_induk_jantan = txt_induk_jantan.getText();
        String calon_indukan_betina = txt_calon_betina.getText();
        String calon_indukan_jantan = txt_calon_jantan.getText();
        String jumlah_kematian = txt_kematian.getText();
        int ikann = cmb_ikan.getSelectedIndex();
        String id_ikan = String.valueOf(ikann);
        String keterangan = txt_keterangan.getText();

        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE stok_indukan SET id_ikan = '"+id_ikan+"',"
            + "tanggal = '"+tanggal+"',"
            + "banyak_induk_betina = '"+banyak_induk_betina+"',"
            + "banyak_induk_jantan = '"+banyak_induk_jantan+"',"
            + "calon_indukan_betina = '"+calon_indukan_betina+"',"
            + "calon_indukan_jantan = '"+calon_indukan_jantan+"',"
            + "jumlah_kematian = '"+jumlah_kematian+"',"
            + "id_petugas = '"+id_petugas+"',"
            + "keterangan = '"+keterangan+"' WHERE id_stok_indukan = '"+id_stok_indukan+"'";
            
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
        txt_induk_betina = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_induk_jantan = new javax.swing.JTextField();
        cmb_petugas = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cmbHari = new javax.swing.JComboBox<>();
        cmbBulan = new javax.swing.JComboBox<>();
        cmbTahun = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cmb_ikan = new javax.swing.JComboBox<>();
        txt_keterangan = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_calon_betina = new javax.swing.JTextField();
        txt_calon_jantan = new javax.swing.JTextField();
        txt_kematian = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel6.setBackground(new java.awt.Color(36, 47, 65));
        panel6.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        panel6.setPreferredSize(new java.awt.Dimension(1280, 800));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(1153, 528));

        jLabel13.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel13.setText("Kode");

        jLabel14.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel14.setText("Banyak Induk Betina");

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

        txt_induk_betina.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_induk_betina.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_induk_betina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_induk_betinaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel15.setText("Tanggal");

        jLabel16.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel16.setText("Banyak Induk Jantan");

        txt_induk_jantan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_induk_jantan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_induk_jantan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_induk_jantanActionPerformed(evt);
            }
        });

        cmb_petugas.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmb_petugas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "Liter" }));

        jLabel23.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel23.setText("Petugas");

        cmbHari.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmbHari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbBulan.setFont(cmb_petugas.getFont());
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cmbTahun.setFont(cmb_petugas.getFont());
        cmbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047" }));

        jLabel17.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel17.setText("Ikan");

        cmb_ikan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        cmb_ikan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "Liter" }));
        cmb_ikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ikanActionPerformed(evt);
            }
        });

        txt_keterangan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_keterangan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_keterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_keteranganActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel24.setText("Keterangan");

        txt_calon_betina.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_calon_betina.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_calon_betina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_calon_betinaActionPerformed(evt);
            }
        });

        txt_calon_jantan.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_calon_jantan.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_calon_jantan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_calon_jantanActionPerformed(evt);
            }
        });

        txt_kematian.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txt_kematian.setPreferredSize(new java.awt.Dimension(6, 35));
        txt_kematian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kematianActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel18.setText("Calon Induk Betina");

        jLabel19.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel19.setText("Calon Induk Jantan");

        jLabel20.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        jLabel20.setText("Jumlah Kematian");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel16)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_induk_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_induk_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_calon_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_calon_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_kematian, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmb_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel15)
                                .addComponent(jLabel17))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(247, 247, 247)
                                    .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(142, 142, 142)
                                    .addComponent(cmb_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGap(337, 337, 337))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmb_ikan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_induk_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_induk_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_calon_betina, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_calon_jantan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kematian, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(16, 16, 16)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_kembali5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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

    private void txt_induk_betinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_induk_betinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_induk_betinaActionPerformed

    private void txt_induk_jantanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_induk_jantanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_induk_jantanActionPerformed

    private void cmb_ikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_ikanActionPerformed

    private void txt_keteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_keteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_keteranganActionPerformed

    private void txt_calon_betinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_calon_betinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_calon_betinaActionPerformed

    private void txt_calon_jantanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_calon_jantanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_calon_jantanActionPerformed

    private void txt_kematianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kematianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kematianActionPerformed

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
            java.util.logging.Logger.getLogger(Tambah_Indukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tambah_Indukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tambah_Indukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tambah_Indukan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tambah_Indukan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_kembali5;
    private javax.swing.JButton btn_simpan5;
    private javax.swing.JComboBox<String> cmbBulan;
    private javax.swing.JComboBox<String> cmbHari;
    private javax.swing.JComboBox<String> cmbTahun;
    private javax.swing.JComboBox<String> cmb_ikan;
    private javax.swing.JComboBox<String> cmb_petugas;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private java.awt.Panel panel6;
    private javax.swing.JTextField txt_calon_betina;
    private javax.swing.JTextField txt_calon_jantan;
    private javax.swing.JTextField txt_induk_betina;
    private javax.swing.JTextField txt_induk_jantan;
    private javax.swing.JTextField txt_kematian;
    private javax.swing.JTextField txt_keterangan;
    private javax.swing.JTextField txt_kode;
    // End of variables declaration//GEN-END:variables
}
