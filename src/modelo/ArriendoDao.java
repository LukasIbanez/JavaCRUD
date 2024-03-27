
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class ArriendoDao {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Arriendo>datos=new ArrayList<>();
        String sql="select * from arriendo";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Arriendo a = new Arriendo();
                a.setId(rs.getInt(1));
                a.setFecha_inicio(rs.getTimestamp(2));
                a.setRut_vendedor(rs.getString(3));
                a.setRut_cliente(rs.getString(4));
                a.setFecha_termino(rs.getTimestamp(5));
                a.setPatente(rs.getString(6));
                datos.add(a);
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
    public int agregar(Arriendo a){
        try {
            String sql="insert into arriendo(fecha_inico,rut_vendedor,rut_cliente,fecha_termino,patente) values(?,?,?,?,?)";
            try {
                con=conectar.getConnection();
                ps=con.prepareStatement(sql);
                ps.setTimestamp(1, a.getFecha_inicio());
                ps.setString(2, a.getRut_vendedor());
                ps.setString(3, a.getRut_cliente());
                ps.setTimestamp(4, a.getFecha_termino());
                ps.setString(5, a.getPatente());

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
        return 0;
    }
    
    public String Actualizar(Arriendo a) {
        String sql = "update arriendo set fecha_inico=?, rut_vendedor=?, rut_cliente=?, fecha_termino=?, Patente=? where id=?";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, a.getFecha_inicio());
            ps.setString(2, a.getRut_vendedor());
            ps.setString(3, a.getRut_cliente());
            ps.setTimestamp(4, a.getFecha_termino());
            ps.setString(5, a.getPatente());
            ps.setInt(6, a.getId());

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
    
    public void delete(int id){
        String sql="delete from arriendo where id="+id;
        try{
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
