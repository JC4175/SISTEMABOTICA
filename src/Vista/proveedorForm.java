/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author zakkc
 */

import Controlador.ProveedorControlador;
import Modelo.Proveedor;


import javax.swing.table.DefaultTableModel;

public class proveedorForm extends javax.swing.JInternalFrame {

    private ProveedorControlador controlador = new ProveedorControlador();
    private DefaultTableModel modelo;
    private boolean modoEdicion = false;
    
    public proveedorForm() {
        initComponents();
        
        configurarTabla();
        cargarTabla();
        setFormularioHabilitado(false);
    }

    private void configurarTabla()
    {
        modelo = new DefaultTableModel( new String[]{"ID", "Razón Social", "Teléfono"}, 0) 
        {public boolean isCellEditable(int r, int c) { return false; }};
        
        tblProveeedor.setModel(modelo);
        tblProveeedor.getSelectionModel().addListSelectionListener(e -> {if (!e.getValueIsAdjusting()) cargarSeleccion();});
    }
    
    private void cargarTabla() 
    {
        modelo.setRowCount(0);
        for (Proveedor p : controlador.listarTodos()) 
        {
            modelo.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getTelefono()});
        }
    }
    
    private void cargarSeleccion() 
    {
        int fila = tblProveeedor.getSelectedRow();
        if (fila < 0) return;
        txtIdprov.setText((String) modelo.getValueAt(fila, 0));
        txtNombreProv.setText((String) modelo.getValueAt(fila, 1));
        txtTelefonoProv.setText((String) modelo.getValueAt(fila, 2));
    }

    private void limpiarFormulario() 
    {
        txtIdprov.setText("");
        txtNombreProv.setText("");
        txtTelefonoProv.setText("");
        txtbuscarproveedor.setText("");
        tblProveeedor.clearSelection();
        modoEdicion = false;
    }
    
    private void setFormularioHabilitado(boolean estado)
    {
        txtIdprov.setEnabled(estado);
        txtNombreProv.setEnabled(estado);
        txtTelefonoProv.setEnabled(estado);
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtbuscarproveedor = new javax.swing.JTextField();
        btnbuscarprov = new javax.swing.JButton();
        btnNuevoProv = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProveeedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdprov = new javax.swing.JTextField();
        txtNombreProv = new javax.swing.JTextField();
        txtTelefonoProv = new javax.swing.JTextField();
        btnguardarprov = new javax.swing.JButton();
        btneditarprov = new javax.swing.JButton();
        btnEliminarProv = new javax.swing.JButton();
        btnLimpiarProv = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 204, 255));
        jLabel1.setText("●");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proveedores");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(17, 17, 17))
        );

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Buscar");

        btnbuscarprov.setText("Buscar");
        btnbuscarprov.addActionListener(this::btnbuscarprovActionPerformed);

        btnNuevoProv.setText("+ Nuevo Proveedor");
        btnNuevoProv.addActionListener(this::btnNuevoProvActionPerformed);

        tblProveeedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Razón Social", "Teléfono"
            }
        ));
        tblProveeedor.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblProveeedor);
        tblProveeedor.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("ID");

        jLabel5.setText("Razon Social");

        jLabel6.setText("Telefono");

        btnguardarprov.setText("Guardar");
        btnguardarprov.addActionListener(this::btnguardarprovActionPerformed);

        btneditarprov.setText("Editar");
        btneditarprov.addActionListener(this::btneditarprovActionPerformed);

        btnEliminarProv.setText("Eliminar");
        btnEliminarProv.addActionListener(this::btnEliminarProvActionPerformed);

        btnLimpiarProv.setText("Limpiar");
        btnLimpiarProv.addActionListener(this::btnLimpiarProvActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdprov, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(txtNombreProv)
                            .addComponent(txtTelefonoProv)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnguardarprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneditarprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpiarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdprov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombreProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnguardarprov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneditarprov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarProv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiarProv)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnbuscarprov)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevoProv)
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtbuscarproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarprov)
                    .addComponent(btnNuevoProv))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProvActionPerformed
        limpiarFormulario();
        setFormularioHabilitado(false);
        cargarTabla();
    }//GEN-LAST:event_btnLimpiarProvActionPerformed

    private void btnNuevoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProvActionPerformed
        limpiarFormulario();
        setFormularioHabilitado(true);
        txtIdprov.requestFocus();
    }//GEN-LAST:event_btnNuevoProvActionPerformed

    private void btnguardarprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarprovActionPerformed
        String resultado;
        if (!modoEdicion) 
        {
            resultado = controlador.registrar( txtIdprov.getText().trim(), txtNombreProv.getText().trim(),
                    txtTelefonoProv.getText().trim());
        } 
        else 
        {
            resultado = controlador.actualizar(txtIdprov.getText().trim(), txtNombreProv.getText().trim(),
                txtTelefonoProv.getText().trim());
        }

        if (resultado.equals("ok")) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, modoEdicion ? "✅ Proveedor actualizado." : "✅ Proveedor registrado.");
            limpiarFormulario();
            setFormularioHabilitado(false);
            cargarTabla();
        } 
        else {
            javax.swing.JOptionPane.showMessageDialog(this, resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnguardarprovActionPerformed

    private void btneditarprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarprovActionPerformed
        // TODO add your handling code here:
        
        if (tblProveeedor.getSelectedRow() < 0) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Seleccione un proveedor de la tabla.");
            return;
        }
        modoEdicion = true;
        setFormularioHabilitado(true);
        txtIdprov.setEnabled(false);
    }//GEN-LAST:event_btneditarprovActionPerformed

    private void btnEliminarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProvActionPerformed
        // TODO add your handling code here:
        
        if (tblProveeedor.getSelectedRow() < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un proveedor de la tabla.");
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "¿Eliminar este proveedor?", "Confirmar",
            javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) 
        {
            String resultado = controlador.eliminar(txtIdprov.getText().trim());
            if (resultado.equals("ok"))
            {
                javax.swing.JOptionPane.showMessageDialog(this, "✅ Proveedor eliminado.");
                limpiarFormulario();
                setFormularioHabilitado(false);
                cargarTabla();
            }
        }
    }//GEN-LAST:event_btnEliminarProvActionPerformed

    private void btnbuscarprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarprovActionPerformed
        String busqueda = txtbuscarproveedor.getText().trim().toLowerCase();
        modelo.setRowCount(0);
        
        for (Proveedor p : controlador.listarTodos()) 
        {
            if (p.getNombre().toLowerCase().contains(busqueda) || p.getCodigo().toLowerCase().contains(busqueda))
            {
                modelo.addRow(new Object[]{  p.getCodigo(), p.getNombre(), p.getTelefono()});
            }
        }
    }//GEN-LAST:event_btnbuscarprovActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarProv;
    private javax.swing.JButton btnLimpiarProv;
    private javax.swing.JButton btnNuevoProv;
    private javax.swing.JButton btnbuscarprov;
    private javax.swing.JButton btneditarprov;
    private javax.swing.JButton btnguardarprov;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProveeedor;
    private javax.swing.JTextField txtIdprov;
    private javax.swing.JTextField txtNombreProv;
    private javax.swing.JTextField txtTelefonoProv;
    private javax.swing.JTextField txtbuscarproveedor;
    // End of variables declaration//GEN-END:variables
}
