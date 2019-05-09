/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dal;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author $$$
 */




public class ModuloConexao {
    
    /**
     * Metode de conexão com o banco de dados, primeiro declara as variaves de
     * de conexão(URL,DRIVER,USUARIO,ROOT,LINK)
     *
     */
    public static Connection con = null;
     
     //MODULO DE CONEXÃO FAZER UM METODO PARA CONECTAR
     
     public static Connection conector(){
       
          
     try {
       Class.forName("com.mysql.cj.jdbc.Driver");
       con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/dbinfox?useTimezone=true&serverTimezone=UTC&useSSL=false","root","root");

       
       return con;
       } catch (ClassNotFoundException ex) {
        System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
        
      
    }    catch(SQLException e) {
         System.out.println(e);
                JOptionPane.showMessageDialog(null,"ERRO AO CONECTAR AO BANCO,VERIFICAR SERVIÇO" + e);
          
    }
             return null;
     
         
         
     
     }
    
    
    
    
    
}
