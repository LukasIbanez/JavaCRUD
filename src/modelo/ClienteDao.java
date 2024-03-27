package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

public class ClienteDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar(){
        List<Cliente>datos=new ArrayList<>();
        String sql="select * from cliente";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setRut(rs.getString(1));
                c.setNombre(rs.getString(2));
                c.setFecha(rs.getDate(3));
                c.setDireccion(rs.getString(4));
                c.setCorreo(rs.getString(5));
                c.setTelefono(rs.getString(6));
                datos.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return datos;
    }
    
    public int agregar(Cliente c){
        try {
            String sql="insert into cliente(rut,nombre,fecha,direccion,correo,telefono) values(?,?,?,?,?,?)";
            try {
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setString(1, c.getRut());
                ps.setString(2, c.getNombre());
                ps.setDate(3, c.getFecha());
                ps.setString(4, c.getDireccion());
                ps.setString(5, c.getCorreo());
                ps.setString(6, c.getTelefono());

                // Ejecutar la actualización y retornar el resultado
                return ps.executeUpdate();
            } catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0; // Retornar 0 en caso de error
    }
    public String Actualizar(Cliente c) {
        String sql = "update cliente set nombre=?, fecha=?, direccion=?, correo=?, telefono=? where rut=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setDate(2, c.getFecha());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getCorreo());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getRut());

            // Ejecutar la actualización y retornar el resultado
            int rowsAffected = ps.executeUpdate();

            return "Actualización exitosa. Filas afectadas: " + rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar: " + e.getMessage();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean delete(String rut){
        String sqlCheckArriendo = "SELECT COUNT(*) AS count FROM arriendo WHERE rut_cliente = ?";
        try {
            con = conectar.getConnection();
            PreparedStatement psCheck = con.prepareStatement(sqlCheckArriendo);
            psCheck.setString(1, rut);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return false; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "DELETE FROM cliente WHERE rut = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, rut);
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


}