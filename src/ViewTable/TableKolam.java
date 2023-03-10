/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewTable;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author REFKY
 */
public class TableKolam extends javax.swing.JPanel {

    /**
     * Creates new form TableKolam
     */
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    public TableKolam() {
        initComponents();
        Koneksi.Koneksi databaseConnection = new Koneksi.Koneksi();
        con = databaseConnection.getKoneksi();
        showKolam(false, "");
    }
    DefaultTableModel dtm;

    public void showKolam(boolean search, String tipeSearch) {
        String[] kolom = {"ID Tempat", "Nama Kolam"};
        dtm = new DefaultTableModel(null, kolom);
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM tempat ";
            if (search) {
                query += "WHERE nama_tempat LIKE '%" + tipeSearch + "%'";
            }

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id_tempat = rs.getString("id_tempat");
                String nama_tempat = rs.getString("nama_tempat");

                dtm.addRow(new String[]{id_tempat, nama_tempat});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        table_kolam.setModel(dtm);
    }

    private String getCellValue(int x, int y) {
        return dtm.getValueAt(x, y).toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_kolam = new javax.swing.JTable();
        Search = new javax.swing.JTextField();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btn_export = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jPanel5.setBackground(new java.awt.Color(36, 47, 65));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        table_kolam.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table_kolam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "NIS", "Nama Anggota", "Kelas", "Tanggal Dibuat", "Alamat"
            }
        ));
        table_kolam.setGridColor(new java.awt.Color(255, 255, 255));
        table_kolam.setSelectionBackground(new java.awt.Color(97, 212, 195));
        table_kolam.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                table_kolamAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        table_kolam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_kolamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(table_kolam);

        Search.setBackground(new java.awt.Color(36, 47, 65));
        Search.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        Search.setForeground(new java.awt.Color(255, 255, 255));
        Search.setBorder(null);
        Search.setPreferredSize(new java.awt.Dimension(450, 39));
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(36, 47, 65));
        btn_edit.setText("Edit");
        btn_edit.setPreferredSize(new java.awt.Dimension(100, 40));
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_editMouseExited(evt);
            }
        });
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        btn_hapus.setForeground(new java.awt.Color(36, 47, 65));
        btn_hapus.setText("Hapus");
        btn_hapus.setPreferredSize(new java.awt.Dimension(100, 40));
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_hapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_hapusMouseExited(evt);
            }
        });
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(255, 255, 255));
        btn_refresh.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(36, 47, 65));
        btn_refresh.setText("Tambah");
        btn_refresh.setPreferredSize(new java.awt.Dimension(100, 40));
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_refreshMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_refreshMouseExited(evt);
            }
        });
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cari");

        btn_export.setBackground(new java.awt.Color(255, 255, 255));
        btn_export.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        btn_export.setForeground(new java.awt.Color(36, 47, 65));
        btn_export.setText("Export Excel");
        btn_export.setPreferredSize(new java.awt.Dimension(100, 40));
        btn_export.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_exportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_exportMouseExited(evt);
            }
        });
        btn_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(605, 605, 605)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table_kolamAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_table_kolamAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_table_kolamAncestorAdded

    private void table_kolamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_kolamMouseClicked

    }//GEN-LAST:event_table_kolamMouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed

    }//GEN-LAST:event_SearchKeyPressed

    private void btn_editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseEntered
        // TODO add your handling code here:
        btn_edit.setBackground(new Color(97, 212, 195));
    }//GEN-LAST:event_btn_editMouseEntered

    private void btn_editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseExited
        // TODO add your handling code here:
        btn_edit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_editMouseExited

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        int baris = table_kolam.getSelectedRow();
        String selected = table_kolam.getValueAt(baris, 0).toString();
        TemplateAdmin.Tambah_Kolam edit_kolam = new TemplateAdmin.Tambah_Kolam(true, "Edit", selected);
        edit_kolam.setVisible(true);
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseEntered
        // TODO add your handling code here:
        btn_hapus.setBackground(new Color(97, 212, 195));
    }//GEN-LAST:event_btn_hapusMouseEntered

    private void btn_hapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseExited
        // TODO add your handling code here:
        btn_hapus.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_hapusMouseExited

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int baris = table_kolam.getSelectedRow();
        String idWhoWantToBeDelete = table_kolam.getValueAt(baris, 0).toString();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menghapus?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
                Statement stmt = con.createStatement();
                String query = "DELETE FROM tempat WHERE id_tempat = '" + idWhoWantToBeDelete + "';";
                int berhasil = stmt.executeUpdate(query);
                if (berhasil == 1) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                    dtm.getDataVector().removeAllElements();
                    showKolam(false, "");
                } else {
                    JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_refreshMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseEntered
        // TODO add your handling code here:
        btn_refresh.setBackground(new Color(97, 212, 195));
    }//GEN-LAST:event_btn_refreshMouseEntered

    private void btn_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseExited
        // TODO add your handling code here:
        btn_refresh.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_refreshMouseExited

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        TemplateAdmin.Tambah_Kolam edit_kolam = new TemplateAdmin.Tambah_Kolam(true, "Tambah", "");
        edit_kolam.setVisible(true);
        JFrame parent = (JFrame) this.getTopLevelAncestor();
        parent.dispose();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String isi = Search.getText();
        if (Character.isDigit(c)) {
            showKolam(true, isi);
        }
        if (!Character.isDigit(c)) {
            showKolam(true, isi);
        }
    }//GEN-LAST:event_SearchKeyReleased

    private void btn_exportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exportMouseEntered
        // TODO add your handling code here:
        btn_export.setBackground(new Color(97, 212, 195));
    }//GEN-LAST:event_btn_exportMouseEntered

    private void btn_exportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exportMouseExited
        // TODO add your handling code here:
        btn_export.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_exportMouseExited

    private void btn_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportActionPerformed
        // TODO add your handling code here:
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
        JFileChooser excelFileChooser = new JFileChooser();
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {

            try {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("Kolam");
                TreeMap<String, Object[]> data = new TreeMap<>();
                data.put("-1", new Object[]{dtm.getColumnName(0), dtm.getColumnName(1)});
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    data.put(Integer.toString(i), new Object[]{getCellValue(i, 0), getCellValue(i, 1)});
                    Set<String> ids = data.keySet();
                    XSSFRow row;
                    int rowID = 0;

                    for (String key : ids) {
                        row = excelSheet.createRow(rowID++);

                        Object[] values = data.get(key);

                        int cellID = 0;
                        for (Object o : values) {
                            XSSFCell cell = row.createCell(cellID++);
                            cell.setCellValue(o.toString());
                        }
                    }
                }
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Berhasil Ekspor");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btn_exportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Search;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_export;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table_kolam;
    // End of variables declaration//GEN-END:variables
}
