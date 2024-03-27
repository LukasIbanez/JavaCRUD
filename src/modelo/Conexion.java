package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url= "jdbc:mysql://btqpdwqex3orqlldpdry-mysql.services.clever-cloud.com:3306/btqpdwqex3orqlldpdry";
        String user="uyomcb5joceqwlqq";
        String pass="FPE5A8y5B8PRx9fRH8yd";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);         
        } catch (Exception e) {
        }
        return con;
    }
}
