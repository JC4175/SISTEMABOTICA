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
        
        // Configurar opciones de ComboBoxes
        if (cmbTipoComprobante != null) {
            cmbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleta", "Factura" }));
        }
        if (cmbMetodoPago != null) {
            cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta", "Yape", "Plin" }));
        }
        
        if (lblNumComprobante != null) {
            lblNumComprobante.setText(controlador.getVentaActual().getNumComprobante());
        }
        agregarMenuContextual(); //nuevo metodo para cambiar los productos de ventas
        configurarEventosCliente();
        restringirCaracteresVisuales();
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
        jLabel23 = new javax.swing.JLabel();
        lblNumComprobante = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        chkAlergiaCliente = new javax.swing.JCheckBox();
        txtSeguroCliente = new javax.swing.JTextField();
        cmbTipoComprobante = new javax.swing.JComboBox<>();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        lblNombreCliente = new javax.swing.JLabel();
        lblAlergiasCliente = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtCorreoCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDetalleAlergiaCliente = new javax.swing.JTextField();

        setClosable(true);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jPanel4.setBackground(new java.awt.Color(25, 135, 84));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VENTAS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(389, 389, 389))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Código de Medicamento");

        txtcodigomedicamento.addActionListener(this::txtcodigomedicamentoActionPerformed);

        btnbuscarventa.setBackground(new java.awt.Color(168, 218, 220));
        btnbuscarventa.setText("Buscar");
        btnbuscarventa.addActionListener(this::btnbuscarventaActionPerformed);

        jLabel3.setText("Nombre de Medicamento");

        jLabel4.setText("Stock Disponible");

        jLabel5.setText("Precio unitario");

        jLabel7.setText("Cantidad");

        btnagregarventa.setBackground(new java.awt.Color(168, 218, 220));
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
                    .addComponent(lblnombremedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcodigomedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(lblstockdisponible, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(lblpreciounitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcantidad))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnagregarventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscarventa, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnagregarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblstockdisponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(lblpreciounitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel10.setText("DETALLE DE LA VENTA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel10)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Total :       S/");

        jLabel13.setText("ID Venta :");

        jLabel15.setText("Fecha : ");

        btnconfirmarventa.setBackground(new java.awt.Color(168, 218, 220));
        btnconfirmarventa.setText("Confirmar Venta");
        btnconfirmarventa.addActionListener(this::btnconfirmarventaActionPerformed);

        btncancelarventa.setBackground(new java.awt.Color(168, 218, 220));
        btncancelarventa.setText("Cancelar Venta");
        btncancelarventa.addActionListener(this::btncancelarventaActionPerformed);

        jLabel23.setText("N° Comprobante");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel23))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnconfirmarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblNumComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btncancelarventa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addComponent(btnconfirmarventa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btncancelarventa))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 18)); // NOI18N
        jLabel8.setText("CLIENTE");

        jLabel9.setText("DNI");

        jLabel12.setText("Apellido Paterno");

        jLabel14.setText("Apellido Materno");

        jLabel16.setText("Nombres");

        jLabel17.setText("Alergias");

        jLabel18.setText("Seguro");

        jLabel19.setText("Comprobante");

        jLabel20.setText("Metodo de Pago");

        txtApellidoPaterno.addActionListener(this::txtApellidoPaternoActionPerformed);

        chkAlergiaCliente.setText("Si");

        cmbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setText("Correo");

        jLabel22.setText("Detalle de Alergias");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAlergiasCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkAlergiaCliente)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSeguroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDetalleAlergiaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(15, 15, 15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(chkAlergiaCliente))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSeguroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtCorreoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtDetalleAlergiaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAlergiasCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarventaActionPerformed
        String codigo = txtcodigomedicamento.getText().trim();
        String cantidad = txtcantidad.getText().trim();
        
        // Buscar medicamento para realizar validaciones adicionales de negocio
        Medicamento m = controlador.buscarMedicamento(codigo);
        if (m != null) {
            // 1. Validar receta médica obligatoria
            if (m.isRequiereReceta()) {
                int confirmReceta = javax.swing.JOptionPane.showConfirmDialog(this,
                    "⚠ Este medicamento requiere receta médica.\n¿El cliente ha presentado la receta de forma física/virtual?",
                    "Medicamento Controlado", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
                if (confirmReceta != javax.swing.JOptionPane.YES_OPTION) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "No se puede agregar el medicamento al detalle sin receta médica.", "Venta Denegada", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // 2. Validar alergias del cliente asociadas al medicamento
            if (txtCodigoCliente != null && !txtCodigoCliente.getText().trim().isEmpty()) {
                String dniCli = txtCodigoCliente.getText().trim();
                Modelo.Cliente cliente = new Datos.ClienteDatos().buscarPorCodigo(dniCli);
                if (cliente != null && cliente.verificarAlergia()) {
                    String alergia = cliente.getDetalleAlergia().toLowerCase();
                    String medNombre = m.getNombre().toLowerCase();
                    // Buscar si hay coincidencia parcial entre el nombre del medicamento y la alergia
                    if (medNombre.contains(alergia) || alergia.contains(medNombre)) {
                        int confirmAlergia = javax.swing.JOptionPane.showConfirmDialog(this,
                            "❌ ¡ALERTA CLÍNICA! El cliente presenta alergia a: " + cliente.getDetalleAlergia().toUpperCase() +
                            "\n¿Está seguro de dispensar el medicamento " + m.getNombre() + " bajo su responsabilidad?",
                            "Alerta de Alergia", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.ERROR_MESSAGE);
                        if (confirmAlergia != javax.swing.JOptionPane.YES_OPTION) {
                            return;
                        }
                    }
                }
            }
        }
        
        String resultado = controlador.agregarDetalle(codigo, cantidad);
        
        if (!resultado.equals("ok"))
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        // Refresca la tabla desde los detalles actuales
        refrescarTabla();
        txtcantidad.setText("");
        txtcodigomedicamento.setText("");
        lblnombremedicamento.setText("-");
        lblstockdisponible.setText("-");
        lblpreciounitario.setText("-");
    }//GEN-LAST:event_btnagregarventaActionPerformed
    private void refrescarTabla()
    {
        modelo.setRowCount(0);
        if (controlador.getVentaActual() != null) {
            for (DetalleVenta d : controlador.getVentaActual().getDetalles()) {
                modelo.addRow(new Object[]{
                    d.getCodigoMedicamento(),
                    d.getNombreMedicamento(),
                    d.getCantidad(),
                    String.format("S/ %.2f", d.getPrecioUnitario()),
                    String.format("S/ %.2f", d.getSubtotal())
                });
            }
        }
        actualizaTotal();
    }
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
            if (lblNumComprobante != null) {
                lblNumComprobante.setText(controlador.getVentaActual().getNumComprobante());
            }
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

    private void txtApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoPaternoActionPerformed

    private void txtcodigomedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigomedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigomedicamentoActionPerformed

    private void btnconfirmarventaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (modelo.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Agregue al menos un producto antes de confirmar.");
            return;
        }
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "¿Confirmar la venta por " + lblTotal.getText() + "?",
            "Confirmar venta", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            
            // 1. Configurar datos del comprobante y método de pago en el controlador
            String tipoComp = (cmbTipoComprobante != null) ? (String) cmbTipoComprobante.getSelectedItem() : "Boleta";
            String nroComp = (lblNumComprobante != null) ? lblNumComprobante.getText().trim() : "";
            String metodoPago = (cmbMetodoPago != null) ? (String) cmbMetodoPago.getSelectedItem() : "Efectivo";
            controlador.configurarComprobante(tipoComp, nroComp, metodoPago);
            
            // 2. Registrar cliente nuevo si fue ingresado al vuelo en la venta
            if (txtCodigoCliente != null && !txtCodigoCliente.getText().trim().isEmpty()) {
                String dni = txtCodigoCliente.getText().trim();
                String nombre = (txtNombreCliente != null) ? txtNombreCliente.getText().trim() : "";
                String apellidoPaterno = (txtApellidoPaterno != null) ? txtApellidoPaterno.getText().trim() : "";
                String apellidoMaterno = (txtApellidoMaterno != null) ? txtApellidoMaterno.getText().trim() : "";
                String correo = (txtCorreoCliente != null) ? txtCorreoCliente.getText().trim() : "";
                boolean tieneAlergia = (chkAlergiaCliente != null) && chkAlergiaCliente.isSelected();
                String detalleAlergia = (txtDetalleAlergiaCliente != null) ? txtDetalleAlergiaCliente.getText().trim() : "";
                String seguro = (txtSeguroCliente != null) ? txtSeguroCliente.getText().trim() : "";
                
                String regCliRes = controlador.registrarClienteNuevoEnVenta(dni, nombre, apellidoPaterno, apellidoMaterno, correo, tieneAlergia, detalleAlergia, seguro);
                if (!regCliRes.equals("ok")) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Error al registrar cliente: " + regCliRes, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            String resultado = controlador.confirmarVenta();
            if (resultado.startsWith("ok")) {
                String id = resultado.split(":")[1];
                javax.swing.JOptionPane.showMessageDialog(this,
                    "✅ Venta " + id + " registrada correctamente.");
                modelo.setRowCount(0);
                
                // Limpiar campos de cliente y comprobante
                if (txtCodigoCliente != null) txtCodigoCliente.setText("");
                if (txtNombreCliente != null) {
                    txtNombreCliente.setText("");
                    txtNombreCliente.setEnabled(false);
                }
                if (txtApellidoPaterno != null) {
                    txtApellidoPaterno.setText("");
                    txtApellidoPaterno.setEnabled(false);
                }
                if (txtApellidoMaterno != null) {
                    txtApellidoMaterno.setText("");
                    txtApellidoMaterno.setEnabled(false);
                }
                if (txtCorreoCliente != null) {
                    txtCorreoCliente.setText("");
                    txtCorreoCliente.setEnabled(false);
                }
                if (chkAlergiaCliente != null) {
                    chkAlergiaCliente.setSelected(false);
                    chkAlergiaCliente.setEnabled(false);
                }
                if (txtDetalleAlergiaCliente != null) {
                    txtDetalleAlergiaCliente.setText("");
                    txtDetalleAlergiaCliente.setEnabled(false);
                }
                if (txtSeguroCliente != null) {
                    txtSeguroCliente.setText("");
                    txtSeguroCliente.setEnabled(false);
                }
                if (lblNombreCliente != null) lblNombreCliente.setText("Público General");
                if (lblAlergiasCliente != null) lblAlergiasCliente.setText("");
                
                controlador.nuevaVenta();
                lblIdVenta.setText(controlador.getVentaActual().getIDVenta());
                lblFechaventa.setText(controlador.getVentaActual().getFechaVenta());
                lblTotal.setText("S/ 0.00");
                if (lblNumComprobante != null) {
                    lblNumComprobante.setText(controlador.getVentaActual().getNumComprobante());
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void agregarMenuContextual() {
        javax.swing.JPopupMenu popupMenu = new javax.swing.JPopupMenu();
        javax.swing.JMenuItem menuQuitar = new javax.swing.JMenuItem("❌ Quitar de la lista");
        javax.swing.JMenuItem menuModificar = new javax.swing.JMenuItem("✏️ Modificar cantidad");
        
        popupMenu.add(menuQuitar);
        popupMenu.add(menuModificar);
        
        tbldetalleventa.setComponentPopupMenu(popupMenu);
        
        // Hace que al dar clic derecho se seleccione la fila bajo el mouse automáticamente
        tbldetalleventa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                int r = tbldetalleventa.rowAtPoint(e.getPoint());
                if (r >= 0 && r < tbldetalleventa.getRowCount()) {
                    tbldetalleventa.setRowSelectionInterval(r, r);
                } else {
                    tbldetalleventa.clearSelection();
                }
            }
        });
        
        menuQuitar.addActionListener(e -> quitarProductoSeleccionado());
        menuModificar.addActionListener(e -> modificarCantidadSeleccionada());
    }
    private void quitarProductoSeleccionado() {
        int fila = tbldetalleventa.getSelectedRow();
        if (fila < 0) return;
        
        String resultado = controlador.eliminarDetalle(fila);
        if (resultado.equals("ok")) {
            refrescarTabla();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    private void modificarCantidadSeleccionada()
    {
        int fila = tbldetalleventa.getSelectedRow();
        if (fila < 0) return;
        
        String codigo = (String) modelo.getValueAt(fila, 0);
        int cantidadActual = (int) modelo.getValueAt(fila, 2);
        
        String nuevaCantidadStr = javax.swing.JOptionPane.showInputDialog(this, 
            "Ingrese la nueva cantidad para el producto:", cantidadActual);
            
        if (nuevaCantidadStr == null || nuevaCantidadStr.trim().isEmpty()) {
            return; // El usuario canceló la entrada
        }
        // 1. Quitamos temporalmente el producto para no alterar la validación de stock
        controlador.eliminarDetalle(fila);
        
        // 2. Intentamos volver a agregarlo con la nueva cantidad
        String resultado = controlador.agregarDetalle(codigo, nuevaCantidadStr.trim());
        
        if (resultado.equals("ok")) {
            refrescarTabla();
        } else {
            // Si falla (por ejemplo, no hay suficiente stock), restauramos el original
            controlador.agregarDetalle(codigo, String.valueOf(cantidadActual));
            refrescarTabla();
            javax.swing.JOptionPane.showMessageDialog(this, 
                "No se pudo cambiar la cantidad: " + resultado, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarEventosCliente() {
        if (txtCodigoCliente == null) return;

        // Listener al presionar Enter en el campo de texto del cliente
        txtCodigoCliente.addActionListener(e -> validarYAsociarCliente());

        // Listener cuando el campo pierde el foco
        txtCodigoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                validarYAsociarCliente();
            }
        });
    }

    private void validarYAsociarCliente() {
        if (txtCodigoCliente == null) return;
        String DNI = txtCodigoCliente.getText().trim();
        if (DNI.isEmpty()) {
            if (lblNombreCliente != null) lblNombreCliente.setText("Público General");
            if (lblAlergiasCliente != null) lblAlergiasCliente.setText("");
            deshabilitarCamposCliente();
            return;
        }

        // Buscar el cliente en la base de datos
        Modelo.Cliente c = new Datos.ClienteDatos().buscarPorCodigo(DNI);
        if (c != null) {
            // Cliente ya existe en el sistema
            if (lblNombreCliente != null) {
                lblNombreCliente.setText(c.getNombre() + " " + c.getApellido());
            }
            if (lblAlergiasCliente != null) {
                if (c.verificarAlergia()) {
                    lblAlergiasCliente.setText("⚠️ ALERGIAS: " + c.getDetalleAlergia().toUpperCase());
                    lblAlergiasCliente.setForeground(java.awt.Color.RED);
                } else {
                    lblAlergiasCliente.setText("✅ Sin alergias conocidas");
                    lblAlergiasCliente.setForeground(new java.awt.Color(0, 150, 0));
                }
            }
            
            // Llenar campos rápidos de consulta y bloquear
            if (txtNombreCliente != null) txtNombreCliente.setText(c.getNombre());
            
            // Separar apellidos
            String[] apellidos = c.getApellido().split(" ", 2);
            String paterno = apellidos.length > 0 ? apellidos[0] : "";
            String materno = apellidos.length > 1 ? apellidos[1] : "";
            if (txtApellidoPaterno != null) txtApellidoPaterno.setText(paterno);
            if (txtApellidoMaterno != null) txtApellidoMaterno.setText(materno);
            
            if (txtCorreoCliente != null) txtCorreoCliente.setText(c.getCorreo());
            if (chkAlergiaCliente != null) chkAlergiaCliente.setSelected(c.verificarAlergia());
            if (txtDetalleAlergiaCliente != null) txtDetalleAlergiaCliente.setText(c.getDetalleAlergia());
            if (txtSeguroCliente != null) txtSeguroCliente.setText(c.getSeguroMedico());
            
            deshabilitarCamposCliente();
        } else {
            // Cliente nuevo: habilitar campos para ingreso inmediato
            if (lblNombreCliente != null) {
                lblNombreCliente.setText("🆕 Cliente nuevo. Complete los datos:");
            }
            if (lblAlergiasCliente != null) lblAlergiasCliente.setText("");
            habilitarCamposCliente();
        }
    }

    private void habilitarCamposCliente() {
        if (txtNombreCliente != null) txtNombreCliente.setEnabled(true);
        if (txtApellidoPaterno != null) txtApellidoPaterno.setEnabled(true);
        if (txtApellidoMaterno != null) txtApellidoMaterno.setEnabled(true);
        if (txtCorreoCliente != null) txtCorreoCliente.setEnabled(true);
        if (chkAlergiaCliente != null) {
            chkAlergiaCliente.setEnabled(true);
            // Configurar comportamiento dinámico del campo de detalle de alergias
            for (java.awt.event.ActionListener al : chkAlergiaCliente.getActionListeners()) {
                chkAlergiaCliente.removeActionListener(al);
            }
            chkAlergiaCliente.addActionListener(e -> {
                if (txtDetalleAlergiaCliente != null) {
                    txtDetalleAlergiaCliente.setEnabled(chkAlergiaCliente.isSelected());
                }
            });
        }
        if (txtDetalleAlergiaCliente != null) txtDetalleAlergiaCliente.setEnabled(chkAlergiaCliente != null && chkAlergiaCliente.isSelected());
        if (txtSeguroCliente != null) txtSeguroCliente.setEnabled(true);
    }

    private void deshabilitarCamposCliente() {
        if (txtNombreCliente != null) txtNombreCliente.setEnabled(false);
        if (txtApellidoPaterno != null) txtApellidoPaterno.setEnabled(false);
        if (txtApellidoMaterno != null) txtApellidoMaterno.setEnabled(false);
        if (txtCorreoCliente != null) txtCorreoCliente.setEnabled(false);
        if (chkAlergiaCliente != null) chkAlergiaCliente.setEnabled(false);
        if (txtDetalleAlergiaCliente != null) txtDetalleAlergiaCliente.setEnabled(false);
        if (txtSeguroCliente != null) txtSeguroCliente.setEnabled(false);
    }

    private void restringirCaracteresVisuales() {
        if (txtcantidad != null) {
            txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    char c = evt.getKeyChar();
                    if (!Character.isDigit(c)) {
                        evt.consume();
                    }
                }
            });
        }

        if (txtCodigoCliente != null) {
            txtCodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    char c = evt.getKeyChar();
                    String text = txtCodigoCliente.getText();
                    if (!Character.isLetterOrDigit(c) || text.length() >= 11) {
                        evt.consume();
                    }
                }
            });
        }

        java.awt.event.KeyAdapter soloLetras = new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c) && c != ' ') {
                    evt.consume();
                }
            }
        };

        if (txtNombreCliente != null) txtNombreCliente.addKeyListener(soloLetras);
        if (txtApellidoPaterno != null) txtApellidoPaterno.addKeyListener(soloLetras);
        if (txtApellidoMaterno != null) txtApellidoMaterno.addKeyListener(soloLetras);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregarventa;
    private javax.swing.JButton btnbuscarventa;
    private javax.swing.JButton btncancelarventa;
    private javax.swing.JButton btnconfirmarventa;
    private javax.swing.JCheckBox chkAlergiaCliente;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<String> cmbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlergiasCliente;
    private javax.swing.JLabel lblFechaventa;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNumComprobante;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblnombremedicamento;
    private javax.swing.JLabel lblpreciounitario;
    private javax.swing.JLabel lblstockdisponible;
    private javax.swing.JTable tbldetalleventa;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtDetalleAlergiaCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtSeguroCliente;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcodigomedicamento;
    // End of variables declaration//GEN-END:variables
}
