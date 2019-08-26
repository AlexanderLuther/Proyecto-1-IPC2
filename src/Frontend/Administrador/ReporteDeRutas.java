package Frontend.Administrador;
import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.Ruta;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class ReporteDeRutas extends javax.swing.JDialog {

    private List<Ruta> listadoRutas;
    private ObservableList<Ruta> observableListRutas;
    private ManejadorDBSM manejadorDB;
    private ManejadorBusqueda manejadorBusqueda;
    private ManejadorHilos manejadorHilos;
    private Ruta ruta;
    private int cantidadPaquetesEnRuta;
    private int cantidadPaquetesEnDestino;
    
    public ReporteDeRutas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.listadoRutas = new ArrayList<>();
        this.observableListRutas = ObservableCollections.observableList(listadoRutas);
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda(); 
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
    
    public void obtenerRutas(int tipo){
        if(tipo == 0){
            listadoRutas = manejadorDB.obtenerListadoRutas("SELECT* FROM Ruta ORDER BY Codigo LIMIT 45;");
        }
        else{
            listadoRutas = manejadorDB.obtenerListadoRutas("SELECT* FROM Ruta ORDER BY Codigo;");
        }
        this.llenarTablaRutas(listadoRutas);
    }

    /*
    Metodo encargado de validar el RadioBoton que se encuentra seleccionado y en base a esa validacion establecer
    el metodo de busqueda a realizar.
    */
    public void establecerFiltroDeBusqueda(){
        selectorMostrarTodosLosRegistros.setSelected(false);
        if(radioBotonActiva.isSelected()){    
            listadoRutas = this.manejadorBusqueda.busquedaRutaPorEstado("Activada");
        }
        if(radioBotonInactiva.isSelected()){
            listadoRutas = this.manejadorBusqueda.busquedaRutaPorEstado("Desactivada");
        }
        this.llenarTablaRutas(listadoRutas);
    }
    
    //Metodo encargado de remover la seleccion de todas las casillas de seleccion
    public void inicializarCasillasSeleccion(){
       selectorMostrarTodosLosRegistros.setSelected(false);
       selectorFiltrado.clearSelection();
    }
    
    //Metodo encargado de establecer el texto por default de las etiquetas costo, ganancia e ingreso.
    public void inicializarEtiquetas(){
        etiquetaPaquetesEnDestino.setText("");
        etiquetaPaquetesEnRuta.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectorFiltrado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonInactiva = new javax.swing.JRadioButton();
        radioBotonActiva = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        botonRegresar = new rojerusan.RSButtonIconI();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        etiquetaPaquetesEnRuta = new javax.swing.JLabel();
        etiquetaPaquetesEnDestino = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonImprimir = new rojerusan.RSButtonIconI();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Rutas");
        getContentPane().setLayout(new java.awt.GridBagLayout());

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

        selectorMostrarTodosLosRegistros.setBackground(new java.awt.Color(204, 204, 204));
        selectorMostrarTodosLosRegistros.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodosLosRegistros.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodosLosRegistros.setText("Mostrar todos los registros");
        selectorMostrarTodosLosRegistros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodosLosRegistrosItemStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Filtrar Rutas por:");

        radioBotonInactiva.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonInactiva);
        radioBotonInactiva.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonInactiva.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonInactiva.setText("Inactiva");
        radioBotonInactiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonInactivaActionPerformed(evt);
            }
        });

        radioBotonActiva.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonActiva);
        radioBotonActiva.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonActiva.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonActiva.setText("Activa");
        radioBotonActiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonActivaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(radioBotonActiva)
                .addGap(18, 18, 18)
                .addComponent(radioBotonInactiva)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonInactiva)
                    .addComponent(radioBotonActiva))
                .addContainerGap())
        );

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
        jLabel3.setText("LISTADO DE RUTAS");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("En Ruta:");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("En Destino:");

        etiquetaPaquetesEnRuta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaPaquetesEnRuta.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaPaquetesEnRuta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        etiquetaPaquetesEnDestino.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaPaquetesEnDestino.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaPaquetesEnDestino.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaPaquetesEnDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiquetaPaquetesEnRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaPaquetesEnRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(etiquetaPaquetesEnDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(selectorMostrarTodosLosRegistros)
                        .addGap(18, 18, 18)
                        .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(154, 154, 154))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(selectorMostrarTodosLosRegistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosLosRegistros.
    */
    private void selectorMostrarTodosLosRegistrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosLosRegistrosItemStateChanged
        this.inicializarEtiquetas();
        if(selectorMostrarTodosLosRegistros.isSelected()){
            selectorFiltrado.clearSelection();
            this.obtenerRutas(1);
        }
        else{
            this.obtenerRutas(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    //Metodo encargado de establecer el filtro de busqueda
    private void radioBotonInactivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonInactivaActionPerformed
       this.inicializarEtiquetas();
       this.establecerFiltroDeBusqueda();
    }//GEN-LAST:event_radioBotonInactivaActionPerformed

   //Metodo encargado de establecer el filtro de busqueda
    private void radioBotonActivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonActivaActionPerformed
        this.inicializarEtiquetas();
        this.establecerFiltroDeBusqueda();
    }//GEN-LAST:event_radioBotonActivaActionPerformed
   /*
    Metodo encargado de ocultar el JDialog
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed
    /*
    Metodo encargado de obtener la ruta seleccionada y establecer el valor de la cantidad de paquetes
    en ruta y la cantidada de paquetes en destino.
    */
    private void tablaRutasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRutasMouseReleased
        this.inicializarEtiquetas();
        ruta = listadoRutas.get(tablaRutas.getSelectedRow());
        cantidadPaquetesEnRuta = manejadorDB.obtenerCantidad("SELECT COUNT(*) FROM PaqueteAsignadoRuta WHERE EnDestino = FALSE && CodigoRuta = '"+ruta.getCodigo()+"';");
        cantidadPaquetesEnDestino = manejadorDB.obtenerCantidad("SELECT COUNT(*) FROM PaqueteAsignadoRuta WHERE EnDestino = TRUE && CodigoRuta = '"+ruta.getCodigo()+"';");
        etiquetaPaquetesEnRuta.setText(String.valueOf(cantidadPaquetesEnRuta));
        etiquetaPaquetesEnDestino.setText(String.valueOf(cantidadPaquetesEnDestino));
    }//GEN-LAST:event_tablaRutasMouseReleased

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        
    }//GEN-LAST:event_botonImprimirActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ReporteDeRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteDeRutas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteDeRutas dialog = new ReporteDeRutas(new javax.swing.JFrame(), true);
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
    private rojerusan.RSButtonIconI botonImprimir;
    private rojerusan.RSButtonIconI botonRegresar;
    private javax.swing.JLabel etiquetaPaquetesEnDestino;
    private javax.swing.JLabel etiquetaPaquetesEnRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonActiva;
    private javax.swing.JRadioButton radioBotonInactiva;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JTable tablaRutas;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
