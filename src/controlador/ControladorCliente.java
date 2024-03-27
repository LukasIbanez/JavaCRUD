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
import modelo.Cliente;
import modelo.ClienteDao;
import vistas.Menu_cliente;
import java.sql.Date;

public class ControladorCliente implements ActionListener {
    
    ClienteDao dao = new ClienteDao();
    Cliente c = new Cliente();
    Menu_cliente menu = new Menu_cliente();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorCliente(Menu_cliente m) {
        this.menu = m;
        this.menu.btnListar.addActionListener(this);
        this.menu.btnGuardar.addActionListener(this);
        this.menu.btnEditar.addActionListener(this);
        this.menu.btnActualizar.addActionListener(this);
        this.menu.btnEliminar.addActionListener(this);
        listar(menu.tabla);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu.btnListar){
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnGuardar){
            agregar();
            
        }
        if(e.getSource()==menu.btnEditar){
            int fila=menu.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un cliente de la Fila");
            }else{
                String rut=(String)menu.tabla.getValueAt(fila, 0);
                String nom=(String)menu.tabla.getValueAt(fila, 1);
                Date fecha=(Date)menu.tabla.getValueAt(fila, 2);
                String direccion=(String)menu.tabla.getValueAt(fila, 3);
                String correo=(String)menu.tabla.getValueAt(fila, 4);
                String telefono=(String)menu.tabla.getValueAt(fila, 5);
                menu.txtRut.setText(rut);
                menu.txtNombre.setText(nom);
                menu.jDateFechaNacimiento.setDate(new java.util.Date(fecha.getTime()));
                menu.txtTelefono.setText(telefono);
                menu.txtCorreo.setText(correo);
                menu.txtDireccion.setText(direccion);
                menu.txtRut.setEnabled(false);
            }
        }
        if(e.getSource()==menu.btnActualizar){
            Actualizar();
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnEliminar){
            int fila = menu.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un Cliente de la fila");
            } else {
                String rut = (String) menu.tabla.getValueAt(fila, 0);
                boolean eliminado = dao.delete(rut);
                if (eliminado) {
                    JOptionPane.showMessageDialog(menu, "Cliente eliminado correctamente");
                    limpiarTabla();
                    listar(menu.tabla);
                } else {
                    JOptionPane.showMessageDialog(menu, "El Cliente está asociado a un arriendo y no puede ser eliminado");
                }
            }
        }
    }
    public void Actualizar() {
        String rut = menu.txtRut.getText();
        String nombre = menu.txtNombre.getText();
        Date fecha = new Date(menu.jDateFechaNacimiento.getDate().getTime());
        String direccion = menu.txtDireccion.getText();
        String correo = menu.txtCorreo.getText();
        String telefono = menu.txtTelefono.getText();

        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "Rut es un campo obligatorio.");
            return;
        }

        c.setRut(rut);
        c.setNombre(nombre);
        c.setFecha(fecha);
        c.setCorreo(correo);
        c.setTelefono(telefono);
        c.setDireccion(direccion);

        try {
            String resultado = dao.Actualizar(c);
            JOptionPane.showMessageDialog(menu, resultado);
            menu.txtRut.setText("");
            menu.txtNombre.setText("");
            menu.jDateFechaNacimiento.setDate(new java.util.Date());
            menu.txtTelefono.setText("");
            menu.txtCorreo.setText("");
            menu.txtDireccion.setText("");

            // También puedes volver a habilitar el campo de texto txtRut si es necesario
            menu.txtRut.setEnabled(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al actualizar el usuario.");
        }
    }

    public void listar(JTable tabla){      
        
        modelo=(DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        List<Cliente>lista=dao.listar();
        Object[]object=new Object[6];
        for(int i = 0; i <lista.size(); i++) {
            object[0]=lista.get(i).getRut();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getFecha();
            object[3]=lista.get(i).getDireccion();
            object[4]=lista.get(i).getCorreo();
            object[5]=lista.get(i).getTelefono();
            modelo.addRow(object);
        }
        menu.txtRut.setEnabled(true);
        menu.tabla.setModel(modelo);
        menu.txtRut.setText("");
        menu.txtNombre.setText("");
        menu.jDateFechaNacimiento.setDate(new java.util.Date());
        menu.txtTelefono.setText("");
        menu.txtCorreo.setText("");
        menu.txtDireccion.setText("");
    }  
    
    public void agregar() {
        String rut = menu.txtRut.getText();
        String nombre = menu.txtNombre.getText();
        Date fecha = new Date(menu.jDateFechaNacimiento.getDate().getTime());
        String direccion = menu.txtDireccion.getText();
        String correo = menu.txtCorreo.getText();
        String telefono = menu.txtTelefono.getText();

        // Validar que el campo txtRut no esté vacío
        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "El campo Rut no puede estar vacío.");
            return;
        }
        
        if (!isValidEmail(correo)) {
            JOptionPane.showMessageDialog(menu, "El correo electrónico ingresado no es válido.");
            return;
        }
        
        String regexCorreo = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern patternCorreo = Pattern.compile(regexCorreo);
        Matcher matcherCorreo = patternCorreo.matcher(correo);
        if (!matcherCorreo.matches()) {
            JOptionPane.showMessageDialog(menu, "El correo electrónico ingresado no es válido.");
            return;
        }

        c.setRut(rut);
        c.setNombre(nombre);
        c.setFecha(fecha);
        c.setDireccion(direccion);
        c.setCorreo(correo);
        c.setTelefono(telefono);

        try {
            int r = dao.agregar(c);
            if (r == 1) {
                JOptionPane.showMessageDialog(menu, "Cliente agregado con éxito.");
                limpiarTabla();
                listar(menu.tabla);
            } else {
                JOptionPane.showMessageDialog(menu, "Error al agregar cliente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al agregar cliente.");
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
