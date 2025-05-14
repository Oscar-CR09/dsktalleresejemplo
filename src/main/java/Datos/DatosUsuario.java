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
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private Date  fecha;
    public DatosUsuario(){ }
    
    private DatosUsuario(String cedula, String nombre, String apellidos, String direccion, String telefono, String email, Date fecha){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido =apellido;
        this.direccion=direccion;
        this.telefono=telefono;
        this.email=email;
        this.fecha=fecha;
        
    }
    
    public String getCedula(){return cedula;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public Date getFecha(){return fecha;}
    
    // --- MÉTODOS SETTER (ESTOS SON LOS QUE TE FALTAN Y CAUSAN EL ERROR) ---
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
