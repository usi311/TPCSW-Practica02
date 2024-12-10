/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcsw.practica02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iker
 */
public class ConexionDB {
    
    private static ConexionDB cx = null;

    public static ConexionDB getInstance() {
        if (cx == null) {
            cx = new ConexionDB();
        }
        return cx;
    }

    private Connection con = null;

    public ConexionDB() {
        try {
            String url = "jdbc:postgresql://localhost:5432/ejemplo";
            con = DriverManager.getConnection(url, "postgres", "l");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean execute(TransactionDB transaction){
    return transaction.execute(con);
    }
    
     public ResultSet select(String sql){
        try {
            Statement stm = con.createStatement();
            ResultSet reg=stm.executeQuery(sql);
            return reg;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
     }
    
    public boolean execute(String sql) {
        Statement stm = null;
        try {
            stm=con.createStatement();
            return stm.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ResultSet executeQuery(String sql) {
        try {
            Statement stm = con.createStatement();
            return stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    // Asegúrate de cerrar el ResultSet y el Statement
    public void closeResources(ResultSet rs, Statement stm) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
