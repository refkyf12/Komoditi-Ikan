/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Afif
 */
public class Koneksi {
    
    public static Connection getKoneksi() {
        
        String[] dataKoneksi = Koneksi.readSettings();
        
        String host = dataKoneksi[0];
        String port = dataKoneksi[1];
        String username = dataKoneksi[2];
        String password = dataKoneksi[3];
        String db = dataKoneksi[4];
        
        String string_connection = "jdbc:mysql://" + host + ":" + port + "/" + db;
        
        Connection connection = null;
        
        try {
        
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(string_connection, username, password);
//            System.out.println("Koneksi Berhasil");
            
        } catch(Exception e) {
            
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Error");
            connection = null;
            
        }
        
        return connection;
    }
    
     public static String[] readSettings() {
        String[] data = new String[5];
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("Data//setting.env");
            br = new BufferedReader(fr);

            String sCurrentLine;
            
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                 data[i] = sCurrentLine;  
                 i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Setting Tidak ditemukan");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }
    
}
