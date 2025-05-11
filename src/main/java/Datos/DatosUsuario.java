/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author oscar
 */
public class DatosUsuario {
    private String cedula;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private Date  fecha_ing;
    public DatosUsuario(){ }
    
    private DatosUsuario(String cedula, String nombre, String apellidos, String direccion, String telefono, String email, Date fecha_ing){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos =apellidos;
        this.direccion=direccion;
        this.telefono=telefono;
        this.email=email;
        this.fecha_ing=fecha_ing;
        
    }
    
    public String getCedula(){return cedula;}
    public String getNombre(){return nombre;}
    public String getApellidos(){return apellidos;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public Date getFecha(){return fecha_ing;}
    
    
    
}
