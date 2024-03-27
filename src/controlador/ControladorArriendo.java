package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Arriendo;
import modelo.ArriendoDao;
import vistas.Menu_arriendo;
import java.sql.Timestamp;

/**
 *
 * @author GorillaSetups
 */
public class ControladorArriendo implements ActionListener{
    ArriendoDao vei = new ArriendoDao();
    Arriendo a = new Arriendo();
    Menu_arriendo menu = new Menu_arriendo();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorArriendo(Menu_arriendo m) {
        this.menu = m;
        this.menu.btnListarArriendo.addActionListener(this);
        this.menu.btnGuardarArriendo.addActionListener(this);
        this.menu.btnEditarArriendo.addActionListener(this);
        this.menu.btnActualizarArriendo.addActionListener(this);
        this.menu.btnEliminarArriendo.addActionListener(this);
        listar(menu.tabla);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menu.btnListarArriendo){
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnGuardarArriendo){
            agregar();
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnEditarArriendo){
            int fila=menu.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un arriendo de la Fila");
            }else{
                Timestamp fecha_inicio=(Timestamp)menu.tabla.getValueAt(fila, 1);
                String rut_vendedor=(String)menu.tabla.getValueAt(fila, 2);
                String rut_cliente=(String)menu.tabla.getValueAt(fila, 3);
                Timestamp fecha_termino=(Timestamp)menu.tabla.getValueAt(fila, 4);
                String patente=(String)menu.tabla.getValueAt(fila, 5);
                menu.jDateFechaArriendo.setDate(new java.util.Date(fecha_inicio.getTime()));
                menu.comboBoxVendedor.setSelectedItem(rut_vendedor);
                menu.comboBoxCliente.setSelectedItem(rut_cliente);
                menu.jDateFechaTerminoArriendo.setDate(new java.util.Date(fecha_termino.getTime()));
                menu.comboBoxPatente.setSelectedItem(patente);
            }
        }
        if(e.getSource()==menu.btnActualizarArriendo){
            Actualizar();
            limpiarTabla();
            listar(menu.tabla);
        }
        if(e.getSource()==menu.btnEliminarArriendo){
            int fila=menu.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(menu, "Debe seleccionar un arriendo de la Fila");
            }else{
                int id=(int)menu.tabla.getValueAt(fila, 0);
                vei.delete(id);
                JOptionPane.showMessageDialog(menu, "Arriendo Eliminado");
                limpiarTabla();
                listar(menu.tabla);
            }
        }
    }
    public void Actualizar() {
        Timestamp fecha_inicio = new Timestamp(menu.jDateFechaArriendo.getDate().getTime());
        String rut_vendedor = menu.comboBoxVendedor.getSelectedItem().toString();
        String rut_cliente = menu.comboBoxCliente.getSelectedItem().toString();
        Timestamp fecha_termino = new Timestamp(menu.jDateFechaTerminoArriendo.getDate().getTime());
        String patente = menu.comboBoxPatente.getSelectedItem().toString();
        
        if (fecha_termino == null) {
            JOptionPane.showMessageDialog(menu, "La fecha de termino es obligatoria.");
            return;
        }
        if (fecha_termino.before(fecha_inicio)) {
            JOptionPane.showMessageDialog(menu, "La fecha de termino no puede ser anterior a la fecha de inicio.");
            return;
        }

        a.setFecha_inicio(fecha_inicio);
        a.setRut_vendedor(rut_vendedor);
        a.setRut_cliente(rut_cliente);
        a.setFecha_termino(fecha_termino);
        a.setPatente(patente);
        

        try {
            String resultado = vei.Actualizar(a);
            JOptionPane.showMessageDialog(menu, resultado);
            menu.jDateFechaArriendo.setDate(new java.util.Date());
            menu.comboBoxVendedor.setSelectedIndex(0);
            menu.comboBoxCliente.setSelectedIndex(0);
            menu.jDateFechaTerminoArriendo.setDate(new java.util.Date());
            menu.comboBoxPatente.setSelectedIndex(0);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al actualizar el arriendo.");
        }
    }

    public void listar(JTable tabla){      
        
        modelo=(DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        List<Arriendo>lista=vei.listar();
        Object[]object=new Object[6];
        for(int i = 0; i <lista.size(); i++) {
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getFecha_inicio();
            object[2]=lista.get(i).getRut_vendedor();
            object[3]=lista.get(i).getRut_cliente();
            object[4]=lista.get(i).getFecha_termino();
            object[5]=lista.get(i).getPatente();
            modelo.addRow(object);
        }
        menu.jDateFechaArriendo.setDate(new java.util.Date());
        menu.comboBoxVendedor.setSelectedIndex(0);
        menu.comboBoxCliente.setSelectedIndex(0);
        menu.jDateFechaTerminoArriendo.setDate(new java.util.Date());
        menu.comboBoxPatente.setSelectedIndex(0);
    }  
    
    public void agregar() {
        Timestamp fecha_inicio = new Timestamp(menu.jDateFechaArriendo.getDate().getTime());
        String rut_vendedor = menu.comboBoxVendedor.getSelectedItem().toString();
        String rut_cliente = menu.comboBoxCliente.getSelectedItem().toString();
        Timestamp fecha_termino = new Timestamp(menu.jDateFechaTerminoArriendo.getDate().getTime());
        String patente = menu.comboBoxPatente.getSelectedItem().toString();
        
        if (fecha_termino == null) {
            JOptionPane.showMessageDialog(menu, "La fecha de termino es obligatoria.");
            return;
        }
        if (fecha_termino.before(fecha_inicio)) {
            JOptionPane.showMessageDialog(menu, "La fecha de termino no puede ser anterior a la fecha de inicio.");
            return;
        }

        a.setFecha_inicio(fecha_inicio);
        a.setRut_vendedor(rut_vendedor);
        a.setRut_cliente(rut_cliente);
        a.setFecha_termino(fecha_termino);
        a.setPatente(patente);

        try {
            int r = vei.agregar(a);
            if (r == 1) {
                JOptionPane.showMessageDialog(menu, "Arriendo agregado con éxito.");
            } else {
                JOptionPane.showMessageDialog(menu, "Error al agregar el arriendo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado al agregar el arriendo.");
        }
    }

    void limpiarTabla(){
        for(int i=0; i<menu.tabla.getRowCount(); i++){
            modelo.removeRow(i);
            i=-1;
        }
    }
}
