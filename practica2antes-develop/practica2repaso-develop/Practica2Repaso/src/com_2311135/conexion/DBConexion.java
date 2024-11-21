/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.conexion;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author liahu
 */
public class DBConexion {
    private static String DRIVER="com.mysql.cj.jdbc.Driver";
    private static String USUARIO="root";
    private static String PASSWORD="armybt21";
    private static String URL= "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";

    
    static{
        try{
            Class.forName(DRIVER);
            
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error en el Driver" +e);
        }
    }
    
    public Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL,USUARIO,PASSWORD);
           JOptionPane.showMessageDialog(null, "Conexión exitosa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la conexión "+e);

        }
        return con;
    }
    
    
}
