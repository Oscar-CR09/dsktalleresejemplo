/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Conexion;
import Datos.DatosUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oscar
 */
public class Usuario {
        private final Conexion cnx = new Conexion();
        private String cSQL="";
        int totReg;
        String[ ] titulos={"Cedula","Nombre","Apellido","Direccion","Telefono","Email","Fecha de Ingreso"};
        Object[ ] registro= new Object[7];
        
        public DefaultTableModel Filtrado(String apellidos){
           /* DefaultTableModel modelo;
            Connection cn = cnx.conectar( );
            modelo = new DefaultTableModel(null,titulos);
            cSQL="Select *from usuarios Where apellidos LIKE '%"+ apellidos.trim()+"%'";*/
            DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            // cSQL ya está declarado como miembro de la clase, así que no necesitas redeclararlo aquí.
            // Sin embargo, es mejor mantener las variables lo más local posible si no se usan en otros métodos.
            String sql = "SELECT * FROM usuarios WHERE apellidos LIKE ?"; // Usar PreparedStatement
            // Usar try-with-resources para Connection, Statement, ResultSet
    try (Connection cn = cnx.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) { // Cambiar a PreparedStatement

        if (cn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return modelo; // Devuelve un modelo vacío o null según tu lógica
        }

        pst.setString(1, "%" + apellidos.trim() + "%"); // Establecer el parámetro del PreparedStatement
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Object[] registroLocal = new Object[7]; // Crear un nuevo array para cada fila
                registroLocal[0] = rs.getString("Cedula");
                registroLocal[1] = rs.getString("Nombre");
                registroLocal[2] = rs.getString("Apellido"); // Asegúrate que la columna se llame "Apellido" y no "Apellidos" como en la consulta
                registroLocal[3] = rs.getString("Direccion");
                registroLocal[4] = rs.getString("Telefono");
                registroLocal[5] = rs.getString("Email");
                registroLocal[6] = rs.getString("Fecha_Ingreso");
                modelo.addRow(registroLocal);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al filtrar usuarios: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muy útil para depuración en la consola
        // Considera devolver un modelo vacío en lugar de null para evitar NullPointerExceptions más adelante
        // return new DefaultTableModel(null, titulos);
        return null;
    } catch (Exception e) { // Captura otras posibles excepciones generales
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null;
    }
    return modelo;
}

            /* ****************************************************************************
            try {
                //private final Conexion cnx= new Conexion();
                 Statement st = cn.createStatement();
                ResultSet rs ;
                        rs=st.executeQuery(cSQL);
                
                while(rs.next()){
                    registro[0]=rs.getString("Cedula");
                    registro[1]=rs.getString("Nombre");
                    registro[2]=rs.getString("Apellido");
                    registro[3]=rs.getString("Direccion");
                    registro[4]=rs.getString("Telefono");
                    registro[5]=rs.getString("Email");
                    registro[6]=rs.getString("Fecha_Ingreso");
                    //totReg +=1;
                    modelo.addRow(registro);
                    
                }
                /*
                rs.close();
                st.close();
                cn.close();
                return modelo;
                */
          /*      
            }catch (SQLException e) { // Captura SQLException específicamente
        JOptionPane.showMessageDialog(null, "Error al filtrar usuarios: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Para depuración
        return null; // O un modelo vacío: return new DefaultTableModel(null, titulos);
    } catch (Exception e) { // Captura otras posibles excepciones
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null;
    }
    return modelo; /*catch (SQLException e) { 
                JOptionPane.showConfirmDialog(null,e+"Error mostrar"); return null; 
            }   */        
        //}
        
        public DefaultTableModel BuscarCedula(String cedula){
           // DefaultTableModel modelo;
            //totReg=0;
            //modelo=new DefaultTableModel(null,titulos);
            //cSQL = "Select * from where cedula LIKE '%"+cedula.trim()+"%'";
             DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            totReg = 0; // Si totReg es solo para este método, decláralo localmente.
            // String sql = "SELECT * FROM usuarios WHERE cedula LIKE ?"; // Corregido: nombre de tabla "usuarios"
            String sql = "SELECT * FROM usuarios WHERE cedula = ?"; // Si buscas una cédula exacta, usa '='. Si es parcial, LIKE está bien.

             try (Connection cn = cnx.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) {

        if (cn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return modelo; // o null
        }

        // pst.setString(1, "%" + cedula.trim() + "%"); // Para LIKE
        pst.setString(1, cedula.trim()); // Para '='

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Object[] registroLocal = new Object[7]; // Es importante crear un nuevo array por cada fila
                registroLocal[0] = rs.getString("Cedula");
                registroLocal[1] = rs.getString("Nombre");
                registroLocal[2] = rs.getString("Apellido");
                registroLocal[3] = rs.getString("Direccion");
                registroLocal[4] = rs.getString("Telefono");
                registroLocal[5] = rs.getString("Email");
                registroLocal[6] = rs.getString("Fecha_Ingreso");
                totReg += 1;
                modelo.addRow(registroLocal);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar por cédula: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null; // o un modelo vacío
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return null;
    }
    return modelo;
        }
           /* try {
                Connection cn =cnx.conectar();
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(cSQL);
                while (rs.next()) {                    
                    registro[0]=rs.getString("Cedula");
                    registro[1]=rs.getString("Nombre");
                    registro[2]=rs.getString("Apellido");
                    registro[3]=rs.getString("Direccion");
                    registro[4]=rs.getString("Telefono");
                    registro[5]=rs.getString("Email");
                    registro[6]=rs.getString("Fecha_Ingreso");
                    totReg +=1;
                    modelo.addRow(registro);
                    
                }
                
                return modelo;
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null,e+"Error mostrar"); 
                return null; 
            }
        }*/
           
        public DefaultTableModel listarUsuarios(){
            DefaultTableModel modelo;
            totReg=0;
            modelo=new DefaultTableModel (null,titulos);
            cSQL="Select * From Usuarios";
            try {
                Connection cn =cnx.conectar();
                Statement st =cn.createStatement();
                ResultSet rs=st.executeQuery(cSQL);
                while (rs.next()) {                    
                     registro[0]=rs.getString("Cedula");
                    registro[1]=rs.getString("Nombre");
                    registro[2]=rs.getString("Apellido");
                    registro[3]=rs.getString("Direccion");
                    registro[4]=rs.getString("Telefono");
                    registro[5]=rs.getString("Email");
                    registro[6]=rs.getString("Fecha_Ingreso");
                    totReg +=1;
                    modelo.addRow(registro);
                }
                rs.close();
                st.close();
                cn.close();
                return modelo;
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null,e+"Error mostrar"); 
                return null; 
            }
            
        }
        public boolean insertar_Usuario(DatosUsuario usuario) {
    // Define cSQL localmente, es más limpio.
    String sql = "INSERT INTO usuarios(Cedula, Nombre, Apellidos, Direccion, Telefono, Email, Fecha_Ingreso) VALUES(?,?,?,?,?,?,?)";
    Connection cn = null; // Declara fuera para poder verificarla si no usas try-with-resources anidado profundamente

    try {
        cn = cnx.conectar(); // Llama a conectar primero

        // ***** DIAGNÓSTICO CRUCIAL *****
        if (cn == null) {
            // Esto sucede si tu clase Conexion.conectar() devuelve null en lugar de lanzar una excepción.
            // El error original probablemente ocurrió DENTRO de conectar() y se mostró un JOptionPane allí.
            JOptionPane.showMessageDialog(null,
                    "Error Crítico: No se pudo obtener la conexión de la base de datos desde cnx.conectar(). Verifique la consola o los diálogos anteriores.",
                    "Falla de Conexión Interna", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Si la conexión NO es null, procede con el PreparedStatement usando try-with-resources para el PreparedStatement
        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, usuario.getCedula());
            pst.setString(2, usuario.getNombre());
            pst.setString(3, usuario.getApellido());
            pst.setString(4, usuario.getDireccion());
            pst.setString(5, usuario.getTelefono());
            pst.setString(6, usuario.getEmail());

            // MUY IMPORTANTE: Asegúrate de que usuario.getFecha() devuelve java.sql.Date
            // Si devuelve java.util.Date, necesitas convertirlo:
            if (usuario.getFecha() instanceof java.util.Date && !(usuario.getFecha() instanceof java.sql.Date)) {
                java.util.Date utilDate = (java.util.Date) usuario.getFecha();
                pst.setDate(7, new java.sql.Date(utilDate.getTime()));
            } else if (usuario.getFecha() instanceof java.sql.Date) {
                pst.setDate(7, (java.sql.Date) usuario.getFecha());
            } else if (usuario.getFecha() == null) {
                pst.setNull(7, java.sql.Types.DATE); // O manejar como error si no puede ser nulo
            } else {
                // Tipo de fecha desconocido, podría ser un problema.
                JOptionPane.showMessageDialog(null, "Tipo de fecha no compatible para Fecha_Ingreso.", "Error de Tipo de Dato", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            int n = pst.executeUpdate();
            return n != 0;
        } // PreparedStatement se cierra aquí

    } catch (ClassNotFoundException e) { // Si cnx.conectar() PUEDE lanzar esto
        JOptionPane.showMessageDialog(null, "Error de Driver: No se encontró la clase del driver de base de datos.\n" + e.getMessage(), "Error de Driver", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muestra el stack trace en la consola
        return false;
    } catch (SQLException e) { // Captura SQLException de conectar() o prepareStatement() o executeUpdate()
        JOptionPane.showMessageDialog(null, "Error de SQL: " + e.getMessage() +
                                      "\nSQLState: " + e.getSQLState() +
                                      "\nErrorCode: " + e.getErrorCode(),
                                      "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muestra el stack trace en la consola
        return false;
    } catch (NullPointerException e) { // Para atrapar explícitamente si cn fue null
        JOptionPane.showMessageDialog(null, "Error Interno (NullPointerException): Posiblemente la conexión no se estableció correctamente.\n" + e.getMessage(), "Error Crítico del Programa", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muestra el stack trace en la consola
        return false;
    } catch (Exception e) { // Captura cualquier otra cosa inesperada
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Muestra el stack trace en la consola
        return false;
    } finally {
        // Si cn se obtuvo y no se usó en un try-with-resources para Connection, ciérralo.
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                // Opcional: registrar error al cerrar conexión
                System.err.println("Error al cerrar conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
        /*public boolean inserta_Usuario(DatosUsuario usuario){
            
           cSQL="Insert into usuarios(Cedula, Nombre, Apellidos, Direccion, Telefono, Email, Fecha_Ingreso) values(?,?,?,?,?,?,?)";
            try(Connection cn = cnx.conectar();
         PreparedStatement pst = cn.prepareStatement(cSQL)) {
               // Connection cn = cnx.conectar();
               // PreparedStatement pst =cn.prepareStatement(cSQL);
                pst.setString(1, usuario.getCedula());
                pst.setString(2, usuario.getNombre());
                pst.setString(3, usuario.getApellidos());
                pst.setString(4, usuario.getDireccion());
                pst.setString(5, usuario.getTelefono());
                pst.setString(6, usuario.getEmail());
                pst.setDate(7, usuario.getFecha());
                int n = pst.executeUpdate();
                return n !=0;
                                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                return false;
            }
            
        }*/
        public boolean editar_Usuario(DatosUsuario usuario){
            cSQL = "Update usuario set Nombre="+"?,"
                    +"Apellidos="+"?,"
                    +"Direccion="+"?,"
                    +"Telefono="+"?,"
                    +"Email="+"?,"
                    +"Fecha_Ingreso="+"?,"
                    +"Where Cedula="+"?";
            try (Connection cn = cnx.conectar();
         PreparedStatement pst = cn.prepareStatement(cSQL)){
                
                  if (cn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
                /*Connection cn = cnx.conectar();
                PreparedStatement pst =cn.prepareStatement(cSQL);*/
                pst.setString(7, usuario.getCedula());
                pst.setString(1, usuario.getNombre());
                pst.setString(2, usuario.getApellido());
                pst.setString(3, usuario.getDireccion());
                pst.setString(4, usuario.getTelefono());
                pst.setString(5, usuario.getEmail());
                pst.setDate(6, usuario.getFecha());
                
                int n = pst.executeUpdate();
                return n !=0;
            }catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al editar usuario: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado al editar usuario: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return false; /*catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
                 return false;
            }*/
        }
        }
        
        // Corregido para eliminar_Usuario (asumiendo que la tabla es 'usuarios')
public boolean eliminar_Usuario(String cedula) {
    cSQL = "DELETE FROM usuarios WHERE Cedula=?"; // Usar nombre de tabla correcto y PreparedStatement
    try (Connection cn = cnx.conectar();
         PreparedStatement pst = cn.prepareStatement(cSQL)) {

        if (cn == null) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        pst.setString(1, cedula);
        int n = pst.executeUpdate();
        // No es necesario cerrar pst y cn aquí si usas try-with-resources
        return n != 0; // Devuelve true si se eliminó al menos una fila

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return false;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado al eliminar usuario: " + e.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        return false;
    }
        /*public boolean eliminar_Usuario(String Cedula){
            cSQL="Delete From Usuario where Cedula="+"?";
            try {
                Connection cn = cnx.conectar();
                PreparedStatement pstEdicion= cn.prepareStatement(cSQL);
                pstEdicion.setString(1, Cedula);
                int n = pstEdicion.executeUpdate();
                pstEdicion.close();
                cn.close();
                return true;
                
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e);
                 return false;
            }
        }       */         
}

}
