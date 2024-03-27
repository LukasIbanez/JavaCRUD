
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.SQLException;

public class VendedorDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Vendedor>datos=new ArrayList<>();
        String sql="select * from vendedor";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setRut(rs.getString(1));
                v.setNombre(rs.getString(2));
                v.setFechanac(rs.getDate(3));
                v.setDireccion(rs.getString(4));
                v.setCorreo(rs.getString(5));
                v.setTelefono(rs.getString(6));
                datos.add(v);
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
    public int agregar(Vendedor v){
        try {
            String sql="insert into vendedor(rut,nombre,fechanac,telefono,correo,direccion) values(?,?,?,?,?,?)";
            try {
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setString(1, v.getRut());
                ps.setString(2, v.getNombre());
                ps.setDate(3, v.getFechanac());
                ps.setString(4, v.getTelefono());
                ps.setString(5, v.getCorreo());
                ps.setString(6, v.getDireccion());

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
    public String Actualizar(Vendedor v) {
        String sql = "update vendedor set nombre=?, fechanac=?, telefono=?, correo=?, direccion=? where rut=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getNombre());
            ps.setDate(2, v.getFechanac());
            ps.setString(3, v.getTelefono());
            ps.setString(4, v.getCorreo());
            ps.setString(5, v.getDireccion());
            ps.setString(6, v.getRut());

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
        String sqlCheckArriendo = "SELECT COUNT(*) AS count FROM arriendo WHERE rut_vendedor = ?";
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

        String sql = "DELETE FROM vendedor WHERE rut = ?";
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
