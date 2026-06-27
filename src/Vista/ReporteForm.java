/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controlador.MedicamentoControlador;
import Controlador.VentaControlador;
import Modelo.Venta;
import Modelo.DetalleVenta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zakkc
 */
public class ReporteForm extends javax.swing.JInternalFrame {

    
    private VentaControlador ventaControlador = new VentaControlador();
    private MedicamentoControlador medControlador = new MedicamentoControlador();
    private DefaultTableModel modelo;
    private ArrayList<Venta> ventasFiltradas = new ArrayList<>();
    
    
    
    
    
    public ReporteForm() {
        initComponents();
        
        configurarTabla();
        cargarResumenGeneral();
        btnVerVencidos.addActionListener(e -> btnVerVencidosActionPerformed());
    }

    private void configurarTabla() 
    {
        modelo = new DefaultTableModel( new String[]{"Fecha", "Medicamento", "Cantidad", "Precio Unit.", "Subtotal"}, 0) 
        {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblreportes.setModel(modelo);
    }

    private void cargarResumenGeneral() {
        ArrayList<Venta> todas = ventaControlador.listarTodas();
        double total = 0;
        for (Venta v : todas) total += v.getTotal();

        lbltotal.setText(String.format("S/ %.2f", total));
        lblvendido.setText(String.valueOf(todas.size()));
        lblvencidos.setText(String.valueOf(medControlador.contarProximosVencer()));
        lblventas.setText(String.valueOf(todas.size()));
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateDesde = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dateHasta = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        lblvendido = new javax.swing.JLabel();
        lblvencidos = new javax.swing.JLabel();
        lblventas = new javax.swing.JLabel();
        lblTopMedicamento = new javax.swing.JLabel();
        lblTicketPromedio = new javax.swing.JLabel();
        lblTopPago = new javax.swing.JLabel();
        btnVerVencidos = new javax.swing.JButton();
        btngenerar = new javax.swing.JButton();
        btnexportar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblreportes = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(112, 48, 140));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 153, 255));
        jLabel1.setText("●");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Reportes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel5.setText("Desde");

        dateDesde.setBackground(new java.awt.Color(255, 255, 255));
        dateDesde.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel6.setText("Hasta");

        dateHasta.setBackground(new java.awt.Color(255, 255, 255));
        dateHasta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(243, 235, 241));

        jLabel3.setText("💰 Total vendido: ");

        jLabel4.setText("📦 Unidades vendidas:");

        jLabel7.setText("⚠️ Medicamentos por vencer:");

        jLabel8.setText("🛒 Ventas registradas:");

        lbltotal.setBackground(new java.awt.Color(255, 255, 255));

        btnVerVencidos.setText("Ver Detalles");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblvendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblvencidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblventas, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnVerVencidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTopMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTicketPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTopPago, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTopMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblvendido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVerVencidos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblvencidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTicketPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTopPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        btngenerar.setBackground(new java.awt.Color(25, 135, 84));
        btngenerar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btngenerar.setForeground(new java.awt.Color(255, 255, 255));
        btngenerar.setText("Generar");
        btngenerar.addActionListener(this::btngenerarActionPerformed);

        btnexportar.setBackground(new java.awt.Color(26, 82, 118));
        btnexportar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnexportar.setForeground(new java.awt.Color(255, 255, 255));
        btnexportar.setText("Exportar TXT");
        btnexportar.addActionListener(this::btnexportarActionPerformed);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblreportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Medicamento", "Cantidad", "Precio Unit", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblreportes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(dateDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnexportar))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btngenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarActionPerformed
        java.util.Date desde = dateDesde.getDate();
        java.util.Date hasta = dateHasta.getDate();
        
        if (desde == null || hasta == null) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione el rango de fechas.");
            return;
        }
        
        if (desde.after(hasta)) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "La fecha 'Desde' no puede ser mayor a 'Hasta'.");
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ventasFiltradas.clear();
        modelo.setRowCount(0);

        double totalPeriodo = 0;
        int unidades = 0;
        
        for (Venta v : ventaControlador.listarTodas()) {
            try {
                java.util.Date fechaVenta = sdf.parse(v.getFechaVenta());
                if (!fechaVenta.before(desde) && !fechaVenta.after(hasta)) {
                    ventasFiltradas.add(v);
                    totalPeriodo += v.getTotal();
                    for(DetalleVenta d : v.getDetalles())
                    {
                        unidades += d.getCantidad();
                        modelo.addRow(new Object[]{
                            v.getFechaVenta(),
                            d.getNombreMedicamento(),
                            d.getCantidad(),
                            String.format("S/ %.2f", d.getPrecioUnitario()),
                            String.format("S/ %.2f", d.getSubtotal())
                        });
                        
                    }
                }
            } catch (Exception e) {}
        }
        
        lbltotal.setText(String.format("S/ %.2f", totalPeriodo));
        lblvendido.setText(String.valueOf(unidades));
        lblventas.setText(String.valueOf(ventasFiltradas.size()));
        lblvencidos.setText(String.valueOf(medControlador.contarProximosVencer()));

        // --- Calcular Métricas Adicionales ---
        // 1. Ticket Promedio
        double ticketPromedio = 0;
        if (!ventasFiltradas.isEmpty()) {
            ticketPromedio = totalPeriodo / ventasFiltradas.size();
        }
        if (lblTicketPromedio != null) {
            lblTicketPromedio.setText(String.format("Promedio: S/ %.2f", ticketPromedio));
        }

        // 2. Medicamento Estrella
        java.util.HashMap<String, Integer> conteoMed = new java.util.HashMap<>();
        for (Venta v : ventasFiltradas) {
            for (DetalleVenta d : v.getDetalles()) {
                conteoMed.put(d.getNombreMedicamento(), conteoMed.getOrDefault(d.getNombreMedicamento(), 0) + d.getCantidad());
            }
        }
        String topMed = "-";
        int maxCant = 0;
        for (java.util.Map.Entry<String, Integer> entry : conteoMed.entrySet()) {
            if (entry.getValue() > maxCant) {
                maxCant = entry.getValue();
                topMed = entry.getKey() + " (" + maxCant + " uds)";
            }
        }
        if (lblTopMedicamento != null) {
            lblTopMedicamento.setText("Estrella: " + topMed);
        }

        // 3. Método de Pago más Popular
        java.util.HashMap<String, Integer> conteoPago = new java.util.HashMap<>();
        for (Venta v : ventasFiltradas) {
            String mp = v.getMetodoPago();
            if (mp != null && !mp.isEmpty()) {
                conteoPago.put(mp, conteoPago.getOrDefault(mp, 0) + 1);
            }
        }
        String topPago = "-";
        int maxPago = 0;
        for (java.util.Map.Entry<String, Integer> entry : conteoPago.entrySet()) {
            if (entry.getValue() > maxPago) {
                maxPago = entry.getValue();
                topPago = entry.getKey() + " (" + maxPago + " vtas)";
            }
        }
        if (lblTopPago != null) {
            lblTopPago.setText("Pago: " + topPago);
        }

    }//GEN-LAST:event_btngenerarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        if (ventasFiltradas.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Primero genere un reporte antes de exportar.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File("reporte_botica.txt"));
        int opcion = chooser.showSaveDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) 
        {
            String ruta = chooser.getSelectedFile().getAbsolutePath();
            ventaControlador.exportarReporte(ventasFiltradas, ruta);
            javax.swing.JOptionPane.showMessageDialog(this,"✅ Reporte exportado en:\n" + ruta);
        }
    }

    private void btnVerVencidosActionPerformed() {
        ArrayList<Modelo.Medicamento> lista = medControlador.listarProximosVencer();
        if (lista == null || lista.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "✅ No hay medicamentos próximos a vencer (30 días).", "Medicamentos por Vencer", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("⚠️ MEDICAMENTOS PRÓXIMOS A VENCER (30 días):\n\n");
        for (Modelo.Medicamento m : lista) {
            sb.append("• ").append(m.getNombre())
              .append(" (Código: ").append(m.getCodigo()).append(")")
              .append(" - Vence: ").append(m.getFechaVencimiento())
              .append(" - Stock: ").append(m.getStock()).append(" uds\n");
        }
        javax.swing.JOptionPane.showMessageDialog(this, sb.toString(), "Alerta de Vencimiento", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerVencidos;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton btngenerar;
    private com.toedter.calendar.JDateChooser dateDesde;
    private com.toedter.calendar.JDateChooser dateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTicketPromedio;
    private javax.swing.JLabel lblTopMedicamento;
    private javax.swing.JLabel lblTopPago;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JLabel lblvencidos;
    private javax.swing.JLabel lblvendido;
    private javax.swing.JLabel lblventas;
    private javax.swing.JTable tblreportes;
    // End of variables declaration//GEN-END:variables
}
