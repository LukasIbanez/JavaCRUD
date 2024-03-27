package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class VehiculoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Vehiculo>datos=new ArrayList<>();
        String sql="select * from vehiculo";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setPatente(rs.getString(1));
                v.setNro_motor(rs.getInt(2));
                v.setNro_chasis(rs.getInt(3));
                v.setMarca(rs.getString(4));
                v.setModelo(rs.getString(5));
                v.setAnio(rs.getInt(6));
                v.setTpo_combustible(rs.getString(7));
                v.setTpo_vehiculo(rs.getString(8));
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
    
    public int agregar(Vehiculo v){
        try {
            String sql="insert into vehiculo(patente,nro_motor,nro_chasis,marca,modelo,año,tpo_combustible,tpo_vehiculo) values(?,?,?,?,?,?,?,?)";
            try {
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setString(1, v.getPatente());
                ps.setInt(2, v.getNro_motor());
                ps.setInt(3, v.getNro_chasis());
                ps.setString(4, v.getMarca());
                ps.setString(5, v.getModelo());
                ps.setInt(6, v.getAnio());
                ps.setString(7, v.getTpo_combustible());
                ps.setString(8, v.getTpo_vehiculo());

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
    public String Actualizar(Vehiculo v) {
        String sql = "update vehiculo set nro_motor=?, nro_chasis=?, marca=?, modelo=?, año=?, tpo_combustible=?, tpo_vehiculo=? where patente=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getNro_motor());
            ps.setInt(2, v.getNro_chasis());
            ps.setString(3, v.getMarca());
            ps.setString(4, v.getModelo());
            ps.setInt(5, v.getAnio());
            ps.setString(6, v.getTpo_combustible());
            ps.setString(7, v.getTpo_vehiculo());
            ps.setString(8, v.getPatente());

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
    public boolean delete(String patente){
        String sqlCheckArriendo = "SELECT COUNT(*) AS count FROM arriendo WHERE patente = ?";
        try {
            con = conectar.getConnection();
            PreparedStatement psCheck = con.prepareStatement(sqlCheckArriendo);
            psCheck.setString(1, patente);
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

        String sql = "DELETE FROM vehiculo WHERE patente = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, patente);
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
