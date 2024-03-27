package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Vendedor;
import modelo.VendedorDao;
import vistas.Menu_vendedor;
import java.sql.Date;

public class ControladorVendedor implements ActionListener{
    VendedorDao dao = new VendedorDao();
    Vendedor c = new Vendedor();
    Menu_vendedor menu = new Menu_vendedor();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorVendedor (Menu_vendedor m) {
        this.menu = m;
        this.menu.btnListarVendedor.addActionListener(this);
        this.menu.btnGuardarVendedor.addActionListener(this);
        this.menu.btnEditarVendedor.addActionListener(this);
        this.menu.btnActualizarVendedor.addActionListener(this);
        this.menu.btnEliminarVendedor.addActionListener(this);
        listar(menu.tabla);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu.btnListarVendedor){
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnGuardarVendedor){
            agregar();
        }
        if(e.getSource()==menu.btnEditarVendedor){
            int fila=menu.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un Vendedor de la Fila");
            }else{
                String rut=(String)menu.tabla.getValueAt(fila, 0);
                String nom=(String)menu.tabla.getValueAt(fila, 1);
                Date fecha=(Date)menu.tabla.getValueAt(fila, 2);
                String telefono=(String)menu.tabla.getValueAt(fila, 3);
                String correo=(String)menu.tabla.getValueAt(fila, 4);
                String direccion=(String)menu.tabla.getValueAt(fila, 5);
                menu.txtRutVendedor.setText(rut);
                menu.txtNombreVendedor.setText(nom);
                menu.jDateFechaNacimiento.setDate(new java.util.Date(fecha.getTime()));
                menu.txtTelefonoVendedor.setText(telefono);
                menu.txtCorreoVendedor.setText(correo);
                menu.txtDireccionVendedor.setText(direccion);
                menu.txtRutVendedor.setEnabled(false);
            }
        }
        if(e.getSource()==menu.btnActualizarVendedor){
            Actualizar();
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnEliminarVendedor){
            int fila = menu.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un Vendedor de la fila");
            } else {
                String rut = (String) menu.tabla.getValueAt(fila, 0);
                boolean eliminado = dao.delete(rut);
                if (eliminado) {
                    JOptionPane.showMessageDialog(menu, "Vendedor eliminado correctamente");
                    limpiarTabla();
                    listar(menu.tabla);
                } else {
                    JOptionPane.showMessageDialog(menu, "El Vendedor está asociado a un arriendo y no puede ser eliminado");
                }
            }
        }
    }
    public void Actualizar() {
        String rut = menu.txtRutVendedor.getText();
        String nombre = menu.txtNombreVendedor.getText();
        Date fecha = new Date(menu.jDateFechaNacimiento.getDate().getTime());
        String direccion = menu.txtDireccionVendedor.getText();
        String correo = menu.txtCorreoVendedor.getText();
        String telefono = menu.txtTelefonoVendedor.getText();

        // Validación de entrada (puedes personalizar según tus necesidades)
        if (rut.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "Rut es un campo obligatorios.");
            return;
        }

        c.setRut(rut);
        c.setNombre(nombre);
        c.setFechanac(fecha);
        c.setCorreo(correo);
        c.setTelefono(telefono);
        c.setDireccion(direccion);

        try {
            String resultado = dao.Actualizar(c);
            JOptionPane.showMessageDialog(menu, resultado);
            menu.txtRutVendedor.setText("");
            menu.txtNombreVendedor.setText("");
            menu.jDateFechaNacimiento.setDate(new java.util.Date());
            menu.txtTelefonoVendedor.setText("");
            menu.txtCorreoVendedor.setText("");
            menu.txtDireccionVendedor.setText("");

            // También puedes volver a habilitar el campo de texto txtRut si es necesario
            menu.txtRutVendedor.setEnabled(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al actualizar el usuario.");
        }
    }

    public void listar(JTable tabla){      
        
        modelo=(DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        List<Vendedor>lista=dao.listar();
        Object[]object=new Object[6];
        for(int i = 0; i <lista.size(); i++) {
            object[0]=lista.get(i).getRut();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getFechanac();
            object[3]=lista.get(i).getDireccion();
            object[4]=lista.get(i).getCorreo();
            object[5]=lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        menu.txtRutVendedor.setEnabled(true);
        menu.tabla.setModel(modelo);
        menu.txtRutVendedor.setText("");
        menu.txtNombreVendedor.setText("");
        menu.jDateFechaNacimiento.setDate(new java.util.Date());
        menu.txtTelefonoVendedor.setText("");
        menu.txtCorreoVendedor.setText("");
        menu.txtDireccionVendedor.setText("");
    }  
    
    public void agregar() {
        String rut = menu.txtRutVendedor.getText();
        String nombre = menu.txtNombreVendedor.getText();
        Date fecha = new Date(menu.jDateFechaNacimiento.getDate().getTime());
        String direccion = menu.txtDireccionVendedor.getText();
        String correo = menu.txtCorreoVendedor.getText();
        String telefono = menu.txtTelefonoVendedor.getText();

        // Validar que el campo txtRut no esté vacío
        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "El campo Rut no puede estar vacío.");
            return;
        }
        if (!isValidEmail(correo)) {
            JOptionPane.showMessageDialog(menu, "El correo electrónico ingresado no es válido.");
            return;
        }

        c.setRut(rut);
        c.setNombre(nombre);
        c.setFechanac(fecha);
        c.setDireccion(direccion);
        c.setCorreo(correo);
        c.setTelefono(telefono);

        try {
            int r = dao.agregar(c);
            if (r == 1) {
                JOptionPane.showMessageDialog(menu, "Vendedor agregado con éxito.");
                limpiarTabla();
                listar(menu.tabla);
            } else {
                JOptionPane.showMessageDialog(menu, "Error al agregar usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al agregar usuario.");
        }
    }
    
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    void limpiarTabla(){
        for(int i=0; i<menu.tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i=-1;
        }
    }
}
