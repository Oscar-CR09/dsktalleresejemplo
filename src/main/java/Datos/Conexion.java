/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author oscar
 */
public class Conexion {
    private final String db="dbtalleres";//nombre de la tabla
    private final String url="jdbc:mysql://localhost:3306/"+db;
    private final String user="root";//usuario de la base de datos
    public String pass="1989cero";//contraseña para conectarse a la base de datos
    
    public Conexion(){}
    
    public Connection conector() throws ClassNotFoundException{
        Connection dbConn=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConn = DriverManager.getConnection(this.url, this.user, this.pass);
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            
        }catch(ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    return dbConn;
    }
    
}


