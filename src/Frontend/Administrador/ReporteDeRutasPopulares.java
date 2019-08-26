package Frontend.Administrador;

import Backend.ManejadorDBSM;
import Backend.ManejadorFechas;
import Backend.ManejadorHilos;

import Backend.Ruta;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class ReporteDeRutasPopulares extends javax.swing.JDialog {

    private List<Ruta> listadoRutas;
    private ObservableList<Ruta> observableListRutas;
    private ManejadorDBSM manejadorDB;
    private ManejadorFechas manejadorFechas;
    private ManejadorHilos manejadorHilos;
    private Ruta ruta;
    private Timestamp fechaInicial;
    private Timestamp fechaFinal;
    private int cantidadPaquetes;
    
    
    public ReporteDeRutasPopulares(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.listadoRutas = new ArrayList<>();
        this.observableListRutas = ObservableCollections.observableList(listadoRutas);
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorFechas = new ManejadorFechas();
        this.manejadorHilos = new ManejadorHilos();
        initComponents();
    }
    
    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Ruta> getObservableList() {
        return observableListRutas;
    }

    public void setObservableList(ObservableList<Ruta> observableList) {
        this.observableListRutas = observableList;
    }
    
    public void llenarTablaRutas(List listado){
        this.observableListRutas.clear();
        this.observableListRutas.addAll(listado);
    }

    /*
    Metodo encargado de inicializar y mostrar el JDialog selectorFechas
    */
    public void obtenerFechas(){
        intervaloFinal.setDatoFecha(null);
        intervaloInicial.setDatoFecha(null);
        selectorFechas.setLocationRelativeTo(this);
        selectorFechas.setVisible(true);
    }
    
    /*
    Metodo encargado de llenar las tablas y hacer visible el JDialos
    */
    public void obtenerRutasPopulares(boolean tiempoCompleto){
        selectorFechas.setVisible(false);
        listadoRutas = manejadorDB.obtenerRutasPopulares(tiempoCompleto, fechaInicial, fechaFinal);
        this.llenarTablaRutas(listadoRutas);
        this.setLocationRelativeTo(selectorFechas);
        this.setVisible(true);
        etiquetaCantidadPaquetes.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectorFiltrado = new javax.swing.ButtonGroup();
        selectorFechas = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        etiquetaFecha = new javax.swing.JLabel();
        etiquetaFecha1 = new javax.swing.JLabel();
        intervaloInicial = new rojeru_san.componentes.RSDateChooser();
        intervaloFinal = new rojeru_san.componentes.RSDateChooser();
        botonSinIntervalo = new rojerusan.RSButtonIconI();
        botonAceptarIntervalo = new rojerusan.RSButtonIconI();
        alertaFechas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        botonRegresar = new rojerusan.RSButtonIconI();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        etiquetaCantidadPaquetes = new javax.swing.JLabel();
        etiquetaPaquetesEnRuta = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonImprimir = new rojerusan.RSButtonIconI();

        selectorFechas.setTitle("Establecer Intervalo de Tiempo");
        selectorFechas.setMinimumSize(new java.awt.Dimension(450, 250));
        selectorFechas.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 200));
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 200));
        jPanel2.setName(""); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 200));

        etiquetaFecha.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaFecha.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaFecha.setText("Fecha Inicial:");
        etiquetaFecha.setToolTipText("");

        etiquetaFecha1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaFecha1.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaFecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaFecha1.setText("Fecha Final:");
        etiquetaFecha1.setToolTipText("");

        intervaloInicial.setColorBackground(new java.awt.Color(51, 153, 0));
        intervaloInicial.setColorDiaActual(new java.awt.Color(255, 0, 0));

        intervaloFinal.setColorBackground(new java.awt.Color(51, 153, 0));
        intervaloFinal.setColorDiaActual(new java.awt.Color(255, 0, 0));

        botonSinIntervalo.setBackground(new java.awt.Color(51, 153, 0));
        botonSinIntervalo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cancelar.png"))); // NOI18N
        botonSinIntervalo.setText("SIN INTERVALO");
        botonSinIntervalo.setColorHover(new java.awt.Color(153, 153, 153));
        botonSinIntervalo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        botonSinIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSinIntervaloActionPerformed(evt);
            }
        });

        botonAceptarIntervalo.setBackground(new java.awt.Color(51, 153, 0));
        botonAceptarIntervalo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonAceptarIntervalo.setText("ACEPTAR");
        botonAceptarIntervalo.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarIntervalo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonAceptarIntervalo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAceptarIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarIntervaloActionPerformed(evt);
            }
        });

        alertaFechas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertaFechas.setForeground(new java.awt.Color(255, 0, 0));
        alertaFechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptarIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonSinIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alertaFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaFecha)
                            .addComponent(etiquetaFecha1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(intervaloFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(intervaloInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intervaloInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intervaloFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alertaFechas, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSinIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAceptarIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        selectorFechas.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Rutas Populares");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tablaRutas.setBackground(new java.awt.Color(204, 204, 204));
        tablaRutas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaRutas.setForeground(new java.awt.Color(0, 102, 153));
        tablaRutas.setGridColor(new java.awt.Color(255, 255, 255));
        tablaRutas.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaRutas.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaRutas);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Codigo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${destino}"));
        columnBinding.setColumnName("Destino");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${activa}"));
        columnBinding.setColumnName("Activa");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tablaRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaRutasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRutas);

        botonRegresar.setBackground(new java.awt.Color(51, 153, 0));
        botonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salir.png"))); // NOI18N
        botonRegresar.setText("REGRESAR");
        botonRegresar.setColorHover(new java.awt.Color(153, 153, 153));
        botonRegresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LISTADO DE RUTAS POPULARES");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        etiquetaCantidadPaquetes.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaCantidadPaquetes.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaCantidadPaquetes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        etiquetaPaquetesEnRuta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaPaquetesEnRuta.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaPaquetesEnRuta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("CANTIDAD DE PAQUETES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(etiquetaCantidadPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaPaquetesEnRuta)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaCantidadPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaPaquetesEnRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonImprimir.setBackground(new java.awt.Color(51, 153, 0));
        botonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/impresora.png"))); // NOI18N
        botonImprimir.setText("IMPRIMIR");
        botonImprimir.setColorHover(new java.awt.Color(153, 153, 153));
        botonImprimir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

  /*
    Metodo encargado de ocultar el JDialog
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed
    /*
    Metodo encargado de obtener la ruta seleccionada y establecer el valor de la cantidad de paquetes.
    */
    private void tablaRutasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRutasMouseReleased
        ruta = listadoRutas.get(tablaRutas.getSelectedRow());
        cantidadPaquetes = manejadorDB.obtenerCantidad("SELECT COUNT(*) FROM PaqueteAsignadoRuta WHERE CodigoRuta = '"+ruta.getCodigo()+"';");
        etiquetaCantidadPaquetes.setText(String.valueOf(cantidadPaquetes));
  
    }//GEN-LAST:event_tablaRutasMouseReleased

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        
    }//GEN-LAST:event_botonImprimirActionPerformed
    /*
    Metodo encargado de llamar al metodo obtenerRutasPopulares enviando false como parametro.
    */
    private void botonSinIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSinIntervaloActionPerformed
        this.obtenerRutasPopulares(true);
    }//GEN-LAST:event_botonSinIntervaloActionPerformed
    /*
    Metodo encargado de obtener las fechas ingresadas y almacenarlas en sus variables respectivas.
    Por ultimo llama al metodo obtenerRutasPopulares enviando true como parametro.
    */
    private void botonAceptarIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarIntervaloActionPerformed
        try{
            fechaInicial = manejadorFechas.convertirFecha(intervaloInicial.getDatoFecha());
            fechaFinal = manejadorFechas.convertirFecha(intervaloFinal.getDatoFecha());
            this.obtenerRutasPopulares(false);
        }
        catch(NullPointerException e){
            alertaFechas.setText("Se deben seleccionar los dos intervalos");
            manejadorHilos.limpiarEtiquetaAlerta(alertaFechas);
        }
    }//GEN-LAST:event_botonAceptarIntervaloActionPerformed

  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutasPopulares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutasPopulares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutasPopulares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutasPopulares.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteDeRutasPopulares dialog = new ReporteDeRutasPopulares(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaFechas;
    private rojerusan.RSButtonIconI botonAceptarIntervalo;
    private rojerusan.RSButtonIconI botonImprimir;
    private rojerusan.RSButtonIconI botonRegresar;
    private rojerusan.RSButtonIconI botonSinIntervalo;
    private javax.swing.JLabel etiquetaCantidadPaquetes;
    private javax.swing.JLabel etiquetaFecha;
    private javax.swing.JLabel etiquetaFecha1;
    private javax.swing.JLabel etiquetaPaquetesEnRuta;
    private rojeru_san.componentes.RSDateChooser intervaloFinal;
    private rojeru_san.componentes.RSDateChooser intervaloInicial;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JDialog selectorFechas;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JTable tablaRutas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
