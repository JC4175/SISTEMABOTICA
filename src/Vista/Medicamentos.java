/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.CategoriaControlador;
import Controlador.MedicamentoControlador;
import Modelo.Medicamento;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zakkc
 */
public class Medicamentos extends javax.swing.JInternalFrame {

    private MedicamentoControlador controlador = new MedicamentoControlador();
    private CategoriaControlador catControlador = new CategoriaControlador();
    private DefaultTableModel modelo;
    private boolean modoEdicion = false;
    
    
    public Medicamentos() {
        initComponents();
        //javax.swing.plaf.basic.BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI();
        //ui.setNorthPane(null);
        //this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Quita el borde exterior
        configurarTabla();
        cargarCategorias();
        cargarTabla();
        verificarAlertas();
        // Deshabilitar campos hasta presionar Nuevo
        setFormularioHabilitado(false);        
        restringirCaracteresVisuales();
    }

    private void configurarTabla() 
    {
        modelo = new DefaultTableModel(
            new String[]{"Código", "Nombre", "Stock", "Vence", "Receta", "Presentación"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblmedicamentos.setModel(modelo);
        tblmedicamentos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccion();
        });
    }
    
    private void cargarTabla() {
        modelo.setRowCount(0);
        for (Medicamento m : controlador.listarTodos()) {
            modelo.addRow(new Object[]{
                m.getCodigo(), 
                m.getNombre(), 
                m.getStock(), 
                m.getFechaVencimiento(),
                m.isRequiereReceta() ? "Sí" : "No",
                m.getPresentacion()
            });
        }
    }
    
    private void cargarCategorias() {
        cmbcategoria.removeAllItems();
        for (String nombre : catControlador.listarNombres()) {
            cmbcategoria.addItem(nombre);
        }
    }
    
    private void verificarAlertas() {
        int proxVencer = controlador.contarProximosVencer();
        int stockBajo  = controlador.listarStockBajo().size();

        if (proxVencer > 0 || stockBajo > 0) {
            String msg = "";
            if (proxVencer > 0) msg += "⚠ " + proxVencer + " medicamentos vencen pronto. ";
            if (stockBajo > 0)  msg += "🔴 " + stockBajo + " con stock bajo.";
            lblaviso.setText(msg);
            jPanel3.setVisible(true);
        } else {
            jPanel3.setVisible(false);
        }
    }
    
    private void cargarSeleccion() {
        int fila = tblmedicamentos.getSelectedRow();
        if (fila < 0) return;
        String codigo = (String) modelo.getValueAt(fila, 0);
        Medicamento m = controlador.buscarPorCodigo(codigo);
        if (m == null) return;

        txtcodigomedicamento.setText(m.getCodigo());
        txtnombremed.setText(m.getNombre());
        txtlab.setText(m.getLaboratorio());
        txtpreciomed.setText(String.valueOf(m.getPrecio()));
        txtstockmed.setText(String.valueOf(m.getStock()));
        txtstockmin.setText(String.valueOf(m.getStockMinimo()));
        txtidproveedor.setText(m.getIdProveedor());
        cmbcategoria.setSelectedItem(m.getIdCategoria());
        
        if (chkRequiereReceta != null) {
            chkRequiereReceta.setSelected(m.isRequiereReceta());
        }
        if (txtPresentacion != null) {
            txtPresentacion.setText(m.getPresentacion());
        }

        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            fechavencimiento.setDate(sdf.parse(m.getFechaVencimiento()));
        } catch (Exception e) {}
    }

    private void setFormularioHabilitado(boolean estado) {
        txtcodigomedicamento.setEnabled(estado);
        txtnombremed.setEnabled(estado);
        txtlab.setEnabled(estado);
        txtpreciomed.setEnabled(estado);
        txtstockmed.setEnabled(estado);
        txtstockmin.setEnabled(estado);
        txtidproveedor.setEnabled(estado);
        fechavencimiento.setEnabled(estado);
        cmbcategoria.setEnabled(estado);
        if (chkRequiereReceta != null) {
            chkRequiereReceta.setEnabled(estado);
        }
        if (txtPresentacion != null) {
            txtPresentacion.setEnabled(estado);
        }
    }

    private void limpiarFormulario() {
        txtcodigomedicamento.setText("");
        txtnombremed.setText("");
        txtlab.setText("");
        txtpreciomed.setText("");
        txtstockmed.setText("");
        txtstockmin.setText("");
        txtidproveedor.setText("");
        fechavencimiento.setDate(null);
        if (cmbcategoria.getItemCount() > 0) cmbcategoria.setSelectedIndex(0);
        if (chkRequiereReceta != null) {
            chkRequiereReceta.setSelected(false);
        }
        if (txtPresentacion != null) {
            txtPresentacion.setText("");
        }
        tblmedicamentos.clearSelection();
        modoEdicion = false;
    }

    private String getFechaFormateada() {
        java.util.Date fecha = fechavencimiento.getDate();
        if (fecha == null) return "";
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }
    
    // ──----------------------- EVENTOS DE BOTONCITOS ──────────────────────────────────────────
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblaviso = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmedicamentos = new javax.swing.JTable();
        btnguardarmed = new javax.swing.JButton();
        btneditarmed = new javax.swing.JButton();
        btneliminarmed = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtcodigomedicamento = new javax.swing.JTextField();
        txtnombremed = new javax.swing.JTextField();
        txtlab = new javax.swing.JTextField();
        txtpreciomed = new javax.swing.JTextField();
        txtstockmed = new javax.swing.JTextField();
        txtstockmin = new javax.swing.JTextField();
        txtidproveedor = new javax.swing.JTextField();
        fechavencimiento = new com.toedter.calendar.JDateChooser();
        cmbcategoria = new javax.swing.JComboBox<>();
        btnNuevaCat = new javax.swing.JButton();
        chkRequiereReceta = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        txtPresentacion = new javax.swing.JTextField();
        btnnuevomed = new javax.swing.JButton();
        btnLimpiarmed = new javax.swing.JButton();
        txtbusquedmed = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel2.setBackground(new java.awt.Color(26, 42, 58));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestión de Medicamentos ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(93, 176, 226));
        jLabel2.setText("●");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 243, 205));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 102)));

        lblaviso.setForeground(new java.awt.Color(255, 102, 0));
        lblaviso.setText("⚠ 3 medicamentos vencen en menos de 30 días");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(lblaviso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblaviso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("🔍 Busqueda ");

        tblmedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Nombre", "Stock", "Vence"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblmedicamentos);

        btnguardarmed.setBackground(new java.awt.Color(25, 135, 84));
        btnguardarmed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnguardarmed.setForeground(new java.awt.Color(255, 255, 255));
        btnguardarmed.setText("Guardar");
        btnguardarmed.addActionListener(this::btnguardarmedActionPerformed);

        btneditarmed.setBackground(new java.awt.Color(13, 110, 253));
        btneditarmed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneditarmed.setForeground(new java.awt.Color(255, 255, 255));
        btneditarmed.setText("Editar");
        btneditarmed.addActionListener(this::btneditarmedActionPerformed);

        btneliminarmed.setBackground(new java.awt.Color(220, 53, 69));
        btneliminarmed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneliminarmed.setForeground(new java.awt.Color(255, 255, 255));
        btneliminarmed.setText("Eliminar");
        btneliminarmed.addActionListener(this::btneliminarmedActionPerformed);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Código");

        jLabel5.setText("Nombre");

        jLabel6.setText("Laboratorio");

        jLabel7.setText("Precio");

        jLabel8.setText("Stock");

        jLabel9.setText("Stock Mínimo");

        jLabel10.setText("Fecha Vencimineto");

        jLabel11.setText("Categoría");

        jLabel12.setText("ID Proveedor");

        txtlab.addActionListener(this::txtlabActionPerformed);

        txtstockmin.addActionListener(this::txtstockminActionPerformed);

        fechavencimiento.setBackground(new java.awt.Color(255, 255, 255));

        cmbcategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnNuevaCat.setText("➕");
        btnNuevaCat.addActionListener(this::btnNuevaCatActionPerformed);

        chkRequiereReceta.setText("Requiere Receta");

        jLabel13.setText("Presentación");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkRequiereReceta)
                    .addComponent(txtcodigomedicamento)
                    .addComponent(txtnombremed)
                    .addComponent(txtlab)
                    .addComponent(txtpreciomed)
                    .addComponent(txtstockmed)
                    .addComponent(txtstockmin)
                    .addComponent(txtidproveedor)
                    .addComponent(fechavencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cmbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNuevaCat))
                    .addComponent(txtPresentacion))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcodigomedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(txtnombremed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtlab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtpreciomed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtstockmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtstockmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevaCat)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtidproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chkRequiereReceta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnnuevomed.setText("Nuevo");
        btnnuevomed.addActionListener(this::btnnuevomedActionPerformed);

        btnLimpiarmed.setText("Limpiar");
        btnLimpiarmed.addActionListener(this::btnLimpiarmedActionPerformed);

        txtbusquedmed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedmedKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(55, 55, 55)
                                        .addComponent(txtbusquedmed, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnguardarmed)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btneditarmed)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btneliminarmed)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnnuevomed)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLimpiarmed)))
                                .addGap(0, 106, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtbusquedmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardarmed, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditarmed, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminarmed, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevomed)
                    .addComponent(btnLimpiarmed))
                .addGap(61, 61, 61))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtlabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtlabActionPerformed

    private void txtstockminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockminActionPerformed

    private void btnnuevomedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevomedActionPerformed
        limpiarFormulario();
        setFormularioHabilitado(true);
        txtcodigomedicamento.setEnabled(true);
        txtcodigomedicamento.requestFocus();
    }//GEN-LAST:event_btnnuevomedActionPerformed

    private void btnguardarmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarmedActionPerformed
        String resultado;
        String fecha = getFechaFormateada();

        boolean reqReceta = false;
        String pres = "General";
        if (chkRequiereReceta != null) {
            reqReceta = chkRequiereReceta.isSelected();
        }
        if (txtPresentacion != null) {
            pres = txtPresentacion.getText().trim();
        }

        if (!modoEdicion) {
            resultado = controlador.registrar(
                txtcodigomedicamento.getText().trim(),
                txtnombremed.getText().trim(),
                txtlab.getText().trim(),
                txtpreciomed.getText().trim(),
                txtstockmed.getText().trim(),
                txtstockmin.getText().trim(),
                fecha,
                (String) cmbcategoria.getSelectedItem(),
                txtidproveedor.getText().trim(),
                reqReceta,
                pres
            );
        } else {
            resultado = controlador.actualizar(
                txtcodigomedicamento.getText().trim(),
                txtnombremed.getText().trim(),
                txtlab.getText().trim(),
                txtpreciomed.getText().trim(),
                txtstockmed.getText().trim(),
                txtstockmin.getText().trim(),
                fecha,
                (String) cmbcategoria.getSelectedItem(),
                txtidproveedor.getText().trim(),
                reqReceta,
                pres
            );
        }

        if (resultado.equals("ok")) {
            javax.swing.JOptionPane.showMessageDialog(this,
                modoEdicion ? "✅ Medicamento actualizado." : "✅ Medicamento registrado.");
            limpiarFormulario();
            setFormularioHabilitado(false);
            cargarTabla();
            verificarAlertas();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnguardarmedActionPerformed

    private void btneditarmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarmedActionPerformed
        if (tblmedicamentos.getSelectedRow() < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Seleccione un medicamento de la tabla.");
            return;
        }
        modoEdicion = true;
        setFormularioHabilitado(true);
        txtcodigomedicamento.setEnabled(false); // No se cambia el código
    }//GEN-LAST:event_btneditarmedActionPerformed

    private void btneliminarmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarmedActionPerformed
        if (tblmedicamentos.getSelectedRow() < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Seleccione un medicamento de la tabla.");
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar este medicamento?",
            "Confirmar", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            String codigo = txtcodigomedicamento.getText().trim();
            String resultado = controlador.eliminar(codigo);
            if (resultado.equals("ok")) {
                javax.swing.JOptionPane.showMessageDialog(this, "✅ Medicamento eliminado.");
                limpiarFormulario();
                setFormularioHabilitado(false);
                cargarTabla();
                verificarAlertas();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btneliminarmedActionPerformed

    private void btnLimpiarmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarmedActionPerformed
        limpiarFormulario();
        setFormularioHabilitado(false);
    }//GEN-LAST:event_btnLimpiarmedActionPerformed
    
    // txtbusqueda — búsqueda en tiempo real:
    /*public void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt)
    {
        String busqueda = txtbusqueda.getText().trim().toLowerCase();
        modelo.setRowCount(0);
        for (Medicamento m : controlador.listarTodos()) {
            if (m.getNombre().toLowerCase().contains(busqueda) ||
                m.getCodigo().toLowerCase().contains(busqueda)) {
                modelo.addRow(new Object[]{
                    m.getCodigo(), m.getNombre(), m.getStock(), m.getFechaVencimiento()
                });
            }
        }
    }*/
    private void btnNuevaCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaCatActionPerformed
    CategoriaDialog categ = new CategoriaDialog((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this),true);
    categ.setVisible(true);

    // Si se guardó, recargar el JComboBox
    if (categ.isGuardado())
    {
        cargarCategorias();
    }
    }//GEN-LAST:event_btnNuevaCatActionPerformed

    private void txtbusquedmedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedmedKeyReleased
    
    String busqueda = txtbusquedmed.getText().trim().toLowerCase();
    modelo.setRowCount(0);
    for (Modelo.Medicamento m : controlador.listarTodos()) 
    {
        if (m.getNombre().toLowerCase().contains(busqueda) || m.getCodigo().toLowerCase().contains(busqueda)) 
        {
            modelo.addRow(new Object[]{m.getCodigo(), m.getNombre(), m.getStock(), m.getFechaVencimiento()});
        }
    }        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusquedmedKeyReleased
    

    private void restringirCaracteresVisuales() {
        if (txtpreciomed != null) {
            txtpreciomed.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    char c = evt.getKeyChar();
                    String text = txtpreciomed.getText();
                    if (!Character.isDigit(c) && c != '.') {
                        evt.consume();
                    }
                    if (c == '.' && text.contains(".")) {
                        evt.consume();
                    }
                }
            });
        }

        java.awt.event.KeyAdapter soloDigitos = new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        };

        if (txtstockmed != null) txtstockmed.addKeyListener(soloDigitos);
        if (txtstockmin != null) txtstockmin.addKeyListener(soloDigitos);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiarmed;
    private javax.swing.JButton btnNuevaCat;
    private javax.swing.JButton btneditarmed;
    private javax.swing.JButton btneliminarmed;
    private javax.swing.JButton btnguardarmed;
    private javax.swing.JButton btnnuevomed;
    private javax.swing.JCheckBox chkRequiereReceta;
    private javax.swing.JComboBox<String> cmbcategoria;
    private com.toedter.calendar.JDateChooser fechavencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblaviso;
    private javax.swing.JTable tblmedicamentos;
    private javax.swing.JTextField txtPresentacion;
    private javax.swing.JTextField txtbusquedmed;
    private javax.swing.JTextField txtcodigomedicamento;
    private javax.swing.JTextField txtidproveedor;
    private javax.swing.JTextField txtlab;
    private javax.swing.JTextField txtnombremed;
    private javax.swing.JTextField txtpreciomed;
    private javax.swing.JTextField txtstockmed;
    private javax.swing.JTextField txtstockmin;
    // End of variables declaration//GEN-END:variables
}
