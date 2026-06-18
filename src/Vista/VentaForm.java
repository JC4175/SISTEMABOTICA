/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.VentaControlador;
import Modelo.DetalleVenta;
import Modelo.Medicamento;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zakkc
 */
public class VentaForm extends javax.swing.JInternalFrame {

    private VentaControlador controlador = new VentaControlador();
    private DefaultTableModel modelo;
    
    public VentaForm() {
        initComponents();
        configurarTabla();
        controlador.nuevaVenta();
        lblIdVenta.setText(controlador.getVentaActual().getIDVenta());
        lblFechaventa.setText(controlador.getVentaActual().getFechaVenta());
        lblTotal.setText("S/ 0.00");
        txtcodigomedicamento.setText("");
    }

    private void configurarTabla()
    {
        modelo = new DefaultTableModel(new String[] {"Código","Nombre","Cantidad","Precio Unit.","SubTotal"},0)
        {
            public boolean isCellEditable(int r,int c){return false;}
        };
        tbldetalleventa.setModel(modelo);
    }
    
    private void actualizaTotal()
    {
        lblTotal.setText(String.format("S/ %.2f", controlador.getTotalActual()));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigomedicamento = new javax.swing.JTextField();
        btnbuscarventa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblnombremedicamento = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        lblstockdisponible = new javax.swing.JLabel();
        lblpreciounitario = new javax.swing.JLabel();
        btnagregarventa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldetalleventa = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblIdVenta = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblFechaventa = new javax.swing.JLabel();
        btnconfirmarventa = new javax.swing.JButton();
        btncancelarventa = new javax.swing.JButton();

        setClosable(true);

        jPanel4.setBackground(new java.awt.Color(25, 135, 84));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 255, 153));
        jLabel6.setText("●");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ventas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Código de Medicamento");

        btnbuscarventa.setText("Buscar");
        btnbuscarventa.addActionListener(this::btnbuscarventaActionPerformed);

        jLabel3.setText("Nombre de Medicamento");

        jLabel4.setText("Stock Disponible");

        jLabel5.setText("Precio unitario");

        jLabel7.setText("Cantidad");

        btnagregarventa.setText("➕ Agregar");
        btnagregarventa.addActionListener(this::btnagregarventaActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblpreciounitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblstockdisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcodigomedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(lblnombremedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnbuscarventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnagregarventa, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcodigomedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarventa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblnombremedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblstockdisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblpreciounitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnagregarventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbldetalleventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Cantidad", "Precio Unit.", "Sub Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldetalleventa);

        jLabel10.setText("DETALLE DE LA VENTA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Total :       S/");

        jLabel13.setText("ID Venta :");

        jLabel15.setText("Fecha : ");

        btnconfirmarventa.setText("Confirmar Venta");
        btnconfirmarventa.addActionListener(this::btnconfirmarventaActionPerformed);

        btncancelarventa.setText("Cancelar Venta");
        btncancelarventa.addActionListener(this::btncancelarventaActionPerformed);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIdVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(lblFechaventa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnconfirmarventa)
                        .addGap(45, 45, 45)
                        .addComponent(btncancelarventa)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblFechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnconfirmarventa)
                    .addComponent(btncancelarventa))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarventaActionPerformed
        String codigo = txtcodigomedicamento.getText().trim();
        String cantidad = txtcantidad.getText().trim();
        
        String resultado = controlador.agregarDetalle(codigo, cantidad);
        
        if (!resultado.equals("ok"))
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        // Refresca la tabla desde los detalles actuales
        modelo.setRowCount(0);
        for (DetalleVenta d : controlador.getVentaActual().getDetalles()) {
            modelo.addRow(new Object[]{
                d.getCodigoMedicamento(),
                d.getNombreMedicamento(),
                d.getCantidad(),
                String.format("S/ %.2f", d.getPrecioUnitario()),
                String.format("S/ %.2f", d.getSubtotal())
            });
        }
        actualizaTotal();
        txtcantidad.setText("");
        txtcodigomedicamento.setText("");
        lblnombremedicamento.setText("-");
        lblstockdisponible.setText("-");
        lblpreciounitario.setText("-");
    }//GEN-LAST:event_btnagregarventaActionPerformed

    private void btncancelarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarventaActionPerformed
        int confirmar = javax.swing.JOptionPane.showConfirmDialog(this,"Desea Cancelar ? Perdera los productos agregados",
                "CAncelar Venta", javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirmar == javax.swing.JOptionPane.YES_OPTION)
        {
            controlador.cancelarVenta();
            modelo.setRowCount(0);
            controlador.nuevaVenta();
            lblIdVenta.setText(controlador.getVentaActual().getIDVenta());
            lblFechaventa.setText(controlador.getVentaActual().getFechaVenta());
            lblTotal.setText("S/ 0.00");
        }
    }//GEN-LAST:event_btncancelarventaActionPerformed

    private void btnbuscarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarventaActionPerformed
        String codigo = txtcodigomedicamento.getText().trim();
        if (codigo.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese un código.");
            return;
        }
        Medicamento m = controlador.buscarMedicamento(codigo);
        if (m == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Medicamento no encontrado.", "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            lblnombremedicamento.setText("-");
            lblstockdisponible.setText("-");
            lblpreciounitario.setText("-");
            return;
        }
        if (m.estaVencido()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "❌ Este medicamento está vencido y no puede venderse.",
                "Vencido", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        lblnombremedicamento.setText(m.getNombre());
        lblstockdisponible.setText(String.valueOf(m.getStock()));
        lblpreciounitario.setText(String.format("S/ %.2f", m.getPrecio()));
    }//GEN-LAST:event_btnbuscarventaActionPerformed

    private void btnconfirmarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarventaActionPerformed
        if (modelo.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Agregue al menos un producto antes de confirmar.");
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Confirmar la venta por " + lblTotal.getText() + "?",
            "Confirmar venta", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            String resultado = controlador.confirmarVenta();
            if (resultado.startsWith("ok")) {
                String id = resultado.split(":")[1];
                javax.swing.JOptionPane.showMessageDialog(this,
                    "✅ Venta " + id + " registrada correctamente.");
                modelo.setRowCount(0);
                controlador.nuevaVenta();
                lblIdVenta.setText(controlador.getVentaActual().getIDVenta());
                lblFechaventa.setText(controlador.getVentaActual().getFechaVenta());
                lblTotal.setText("S/ 0.00");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnconfirmarventaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregarventa;
    private javax.swing.JButton btnbuscarventa;
    private javax.swing.JButton btncancelarventa;
    private javax.swing.JButton btnconfirmarventa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaventa;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblnombremedicamento;
    private javax.swing.JLabel lblpreciounitario;
    private javax.swing.JLabel lblstockdisponible;
    private javax.swing.JTable tbldetalleventa;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcodigomedicamento;
    // End of variables declaration//GEN-END:variables
}
