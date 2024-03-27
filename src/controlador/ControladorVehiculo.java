package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Vehiculo;
import modelo.VehiculoDao;
import vistas.Menu_vehiculo;

public class ControladorVehiculo implements ActionListener{
    
    VehiculoDao vei = new VehiculoDao();
    Vehiculo v = new Vehiculo();
    Menu_vehiculo menu = new Menu_vehiculo();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorVehiculo(Menu_vehiculo m) {
        this.menu = m;
        this.menu.btnListarVehiculo.addActionListener(this);
        this.menu.btnGuardarVehiculo.addActionListener(this);
        this.menu.btnEditarVehiculo.addActionListener(this);
        this.menu.btnActualizarVehiculo.addActionListener(this);
        this.menu.btnEliminarVehiculo.addActionListener(this);
        listar(menu.tabla);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu.btnListarVehiculo){
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnGuardarVehiculo){
            agregar();
        }
        if(e.getSource()==menu.btnEditarVehiculo){
            int fila=menu.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un vehiculo de la Fila");
            }else{
                String patente=(String)menu.tabla.getValueAt(fila, 0);
                int nro_motor=(int)menu.tabla.getValueAt(fila, 1);
                int nro_chasis=(int)menu.tabla.getValueAt(fila, 2);
                String marca=(String)menu.tabla.getValueAt(fila, 3);
                String modelo=(String)menu.tabla.getValueAt(fila, 4);
                int año=(int)menu.tabla.getValueAt(fila, 5);
                String tpo_combustible=(String)menu.tabla.getValueAt(fila, 6);
                String tpo_vehiculo=(String)menu.tabla.getValueAt(fila, 7);
                menu.txtPatente.setText(patente);
                menu.txtNro_motor.setText(Integer.toString(nro_motor));
                menu.txtNro_chasis.setText(Integer.toString(nro_chasis));
                menu.txtMarca.setText(marca);
                menu.txtModelo.setText(modelo);
                menu.txtAnio.setText(Integer.toString(año));
                menu.jComboBoxTipoCombustible.setSelectedItem(tpo_combustible);
                menu.jComboBoxTipoVehiculo.setSelectedItem(tpo_vehiculo);
                menu.txtPatente.setEnabled(false);
            }
        }
        if(e.getSource()==menu.btnActualizarVehiculo){
            Actualizar();
            limpiarTabla();
            listar(menu.tabla);
        }
        if (e.getSource() == menu.btnEliminarVehiculo) {
            int fila = menu.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un vehículo de la fila");
            } else {
                String patente = (String) menu.tabla.getValueAt(fila, 0);
                boolean eliminado = vei.delete(patente);
                if (eliminado) {
                    JOptionPane.showMessageDialog(menu, "Vehículo eliminado correctamente");
                    limpiarTabla();
                    listar(menu.tabla);
                } else {
                    JOptionPane.showMessageDialog(menu, "La patente está asociada a arriendos y no puede ser eliminada");
                }
            }
        }
    }
    public void Actualizar() {
        String patente = menu.txtPatente.getText();
        int nro_motor = Integer.parseInt(menu.txtNro_motor.getText());
        int nro_chasis = Integer.parseInt(menu.txtNro_chasis.getText());
        String marca = menu.txtMarca.getText();
        String modelo_auto = menu.txtModelo.getText();
        int año = Integer.parseInt(menu.txtAnio.getText());
        String tipo_combustible = menu.jComboBoxTipoCombustible.getSelectedItem().toString();
        String tipo_vehiculo = menu.jComboBoxTipoVehiculo.getSelectedItem().toString();

        // Validación de entrada (puedes personalizar según tus necesidades)
        if (patente.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "Patente es un campo obligatorio.");
            return;
        }

        v.setPatente(patente);
        v.setNro_motor(nro_motor);
        v.setNro_chasis(nro_chasis);
        v.setMarca(marca);
        v.setModelo(modelo_auto);
        v.setAnio(año);
        v.setTpo_combustible(tipo_combustible);
        v.setTpo_vehiculo(tipo_vehiculo);
        

        try {
            String resultado = vei.Actualizar(v);
            JOptionPane.showMessageDialog(menu, resultado);
            menu.txtPatente.setText("");
            menu.txtNro_motor.setText("");
            menu.txtNro_chasis.setText("");
            menu.txtMarca.setText("");
            menu.txtModelo.setText("");
            menu.txtAnio.setText("");
            menu.jComboBoxTipoCombustible.setSelectedIndex(0);
            menu.jComboBoxTipoVehiculo.setSelectedIndex(0);
            

            // También puedes volver a habilitar el campo de texto txtRut si es necesario
            menu.txtPatente.setEnabled(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al actualizar el vehiculo.");
        }
    }

    public void listar(JTable tabla){      
        
        modelo=(DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        List<Vehiculo>lista=vei.listar();
        Object[]object=new Object[8];
        for(int i = 0; i <lista.size(); i++) {
            object[0]=lista.get(i).getPatente();
            object[1]=lista.get(i).getNro_motor();
            object[2]=lista.get(i).getNro_chasis();
            object[3]=lista.get(i).getMarca();
            object[4]=lista.get(i).getModelo();
            object[5]=lista.get(i).getAnio();
            object[6]=lista.get(i).getTpo_combustible();
            object[7]=lista.get(i).getTpo_vehiculo();
            modelo.addRow(object);
        }
        menu.txtPatente.setEnabled(true);
        menu.tabla.setModel(modelo);
        menu.txtPatente.setText("");
        menu.txtNro_motor.setText("");
        menu.txtNro_chasis.setText("");
        menu.txtMarca.setText("");
        menu.txtModelo.setText("");
        menu.txtAnio.setText("");
        menu.jComboBoxTipoCombustible.setSelectedIndex(0);
        menu.jComboBoxTipoVehiculo.setSelectedIndex(0);
    }  
    
    public void agregar() {
        String patente = menu.txtPatente.getText();
        String Nro_motor = menu.txtNro_motor.getText();
        String Nro_chasis = menu.txtNro_chasis.getText();
        String Marca = menu.txtMarca.getText();
        String Modelo = menu.txtModelo.getText();
        String Anio = menu.txtAnio.getText();
        String tpo_combustible = menu.jComboBoxTipoCombustible.getSelectedItem().toString();
        String tpo_vehiculo = menu.jComboBoxTipoVehiculo.getSelectedItem().toString();
        
        // Validar que el campo txtRut no esté vacío
        if (patente.isEmpty()) {
            JOptionPane.showMessageDialog(menu, "El campo Patente no puede estar vacío.");
            return;
        }

        if (patente.length() != 6) {
            JOptionPane.showMessageDialog(menu, "La patente debe tener exactamente 6 caracteres.");
            return;
        }

        if (Nro_motor.length() < 1) {
            JOptionPane.showMessageDialog(menu, "El número de motor debe tener al menos 1 caracter.");
            return;
        }

        if (Nro_chasis.length() < 1) {
            JOptionPane.showMessageDialog(menu, "El número de chasis debe tener al menos 1 caracter.");
            return;
        }

        if (Anio.length() != 4) {
            JOptionPane.showMessageDialog(menu, "Ingrese un año correcto.");
            return;
        }

        v.setPatente(patente);
        v.setNro_motor(Integer.parseInt(Nro_motor));
        v.setNro_chasis(Integer.parseInt(Nro_chasis));
        v.setMarca(Marca);
        v.setModelo(Modelo);
        v.setAnio(Integer.parseInt(Anio));
        v.setTpo_combustible(tpo_combustible);
        v.setTpo_vehiculo(tpo_vehiculo);

        try {
            int r = vei.agregar(v);
            if (r == 1) {
                JOptionPane.showMessageDialog(menu, "Vehiculo agregado con éxito.");
                limpiarTabla();
                listar(menu.tabla);
            } else {
                JOptionPane.showMessageDialog(menu, "Error al agregar vehiculo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al agregar vehiculo.");
        }
    }

    void limpiarTabla(){
        for(int i=0; i<menu.tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i=-1;
        }
    }
}
