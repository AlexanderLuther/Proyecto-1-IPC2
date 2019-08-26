package Frontend.Operador;
import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.PuntoDeControl;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author helmuthluther
 */
public class PanelProcesarPaquete extends javax.swing.JPanel {
    
    //Variables e instancias de la clase.
    private PanelPaqueteEnPuntoDeControl panelPaqueteEnPuntoDeControl;
    private ManejadorDBSM manejadorDB;
    private ManejadorHilos manejadorHilos;
    private ManejadorBusqueda manejadorBusqueda;
    private List<PuntoDeControl> listadoPuntosDeControl;
    private ObservableList<PuntoDeControl> observableListPuntosDeControl;
    private PuntoDeControl puntoDeControl;
    private String patronBusqueda;
    private String usuarioActual;
    private String nombreRuta;
    
    //Constructor de la clase
    public PanelProcesarPaquete() {
        this.panelPaqueteEnPuntoDeControl = new PanelPaqueteEnPuntoDeControl(this);
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorHilos = new ManejadorHilos();
        this.listadoPuntosDeControl = new ArrayList<>();
        this.observableListPuntosDeControl = ObservableCollections.observableList(listadoPuntosDeControl);
        initComponents();
    }

    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<PuntoDeControl> getObservableList() {
        return observableListPuntosDeControl;
    }

    public void setObservableList(ObservableList<PuntoDeControl> observableList) {
        this.observableListPuntosDeControl = observableList;
    }
    
    public void llenarTablaPuntosDeControl(List listado){
        this.observableListPuntosDeControl.clear();
        this.observableListPuntosDeControl.addAll(listado);
    }
   
    public void obtenerPuntosDeControl(int tipo){
        if(tipo == 0){
            listadoPuntosDeControl = manejadorDB.obtenerListadoPuntosDeControl("SELECT* FROM PuntoDeControl WHERE OperadorAsignado = '"+usuarioActual+"' ORDER BY CodigoRuta ASC, Codigo ASC  LIMIT 45;");
        }
        else{
           listadoPuntosDeControl = manejadorDB.obtenerListadoPuntosDeControl("SELECT* FROM PuntoDeControl WHERE OperadorAsignado = '"+usuarioActual+"' ORDER BY CodigoRuta ASC, Codigo ASC;");
        }
        this.llenarTablaPuntosDeControl(listadoPuntosDeControl);
    }
    //-------------------------------------------------------------------------------

    
    /*
    Metodo encargado de validar el RadioBoton que se encuentra seleccionado y en base a esa validacion establecer
    el metodo de busqueda a realizar.
    */
    public void establecerFiltroDeBusqueda(){
        selectorMostrarTodosLosRegistros.setSelected(false);
        if(radioBotonNombre.isSelected()){    
            listadoPuntosDeControl = this.manejadorBusqueda.busquedaPuntoDeControlPorNombre(patronBusqueda, usuarioActual);
        }
        if(radioBotonCodigoRuta.isSelected()){
            listadoPuntosDeControl = this.manejadorBusqueda.busquedaPuntoDeControlPorCodigoRuta(patronBusqueda, usuarioActual);
        }
        if(radioBotonCodigo.isSelected()){
            listadoPuntosDeControl = this.manejadorBusqueda.busquedaPuntoDeControlPorCodigo(patronBusqueda, usuarioActual);
        }
        this.llenarTablaPuntosDeControl(listadoPuntosDeControl);
    }
   
   //Metodo encargado de remover la seleccion de todas las casillas de seleccion
   public void inicializarCasillasSeleccion(){
       textoBusqueda.setText("");
       selectorMostrarTodosLosRegistros.setSelected(false);
       selectorFiltrado.clearSelection();
   }
   
   /*
   Metodo encargado de establecer el valor de la variable usuarioActual.
   */
   public void establecerUsuarioActual(String usuarioActual){
       this.usuarioActual = usuarioActual;
   }
   
    /*
    Metodo encargado de remover el panelPuntoDeControl y hacer visibles todos los componenes del panelRuta
    */
    public void reestructurarPanelProcesarPaquete(){
        this.remove(panelPaqueteEnPuntoDeControl);
        for(int i = 0; i < this.getComponentCount(); i++){
            this.getComponent(i).setVisible(true);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectorFiltrado = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPuntosDeControl = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        botonVerPaquetes = new rojerusan.RSButtonIconI();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonCodigoRuta = new javax.swing.JRadioButton();
        radioBotonNombre = new javax.swing.JRadioButton();
        radioBotonCodigo = new javax.swing.JRadioButton();
        etiquetaAlertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textoBusqueda = new rojeru_san.RSMTextFull();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridBagLayout());

        tablaPuntosDeControl.setBackground(new java.awt.Color(204, 204, 204));
        tablaPuntosDeControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaPuntosDeControl.setForeground(new java.awt.Color(0, 102, 153));
        tablaPuntosDeControl.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPuntosDeControl.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tablaPuntosDeControl.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPuntosDeControl);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Codigo ");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigoRuta}"));
        columnBinding.setColumnName("Codigo Ruta");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cantidadPaquetesCola}"));
        columnBinding.setColumnName("Cantidad de Paquetes en Cola");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tarifaOperacion}"));
        columnBinding.setColumnName("Tarifa de Operacion");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaPuntosDeControl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1177;
        gridBagConstraints.ipady = 570;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(jScrollPane1, gridBagConstraints);

        botonBuscar.setBackground(new java.awt.Color(204, 204, 204));
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/buscar.png"))); // NOI18N
        botonBuscar.setBorderPainted(false);
        botonBuscar.setColorHover(new java.awt.Color(153, 153, 153));
        botonBuscar.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(botonBuscar, gridBagConstraints);

        selectorMostrarTodosLosRegistros.setBackground(new java.awt.Color(204, 204, 204));
        selectorMostrarTodosLosRegistros.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodosLosRegistros.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodosLosRegistros.setText("Mostrar todos los registros");
        selectorMostrarTodosLosRegistros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodosLosRegistrosItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(selectorMostrarTodosLosRegistros, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        botonVerPaquetes.setBackground(new java.awt.Color(51, 102, 255));
        botonVerPaquetes.setForeground(new java.awt.Color(0, 102, 153));
        botonVerPaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/caja.png"))); // NOI18N
        botonVerPaquetes.setText("Ver Paquetes");
        botonVerPaquetes.setColorHover(new java.awt.Color(153, 153, 153));
        botonVerPaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerPaquetesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(botonVerPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(botonVerPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(153, 6, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Filtrado por:");

        radioBotonCodigoRuta.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonCodigoRuta);
        radioBotonCodigoRuta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonCodigoRuta.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonCodigoRuta.setText("Codigo de Ruta");
        radioBotonCodigoRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonCodigoRutaActionPerformed(evt);
            }
        });

        radioBotonNombre.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonNombre);
        radioBotonNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonNombre.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonNombre.setText("Nombre");
        radioBotonNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonNombreActionPerformed(evt);
            }
        });

        radioBotonCodigo.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonCodigo);
        radioBotonCodigo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonCodigo.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonCodigo.setText("Codigo ");
        radioBotonCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonCodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(radioBotonCodigo)
                .addGap(18, 18, 18)
                .addComponent(radioBotonNombre)
                .addGap(18, 18, 18)
                .addComponent(radioBotonCodigoRuta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonCodigoRuta)
                    .addComponent(radioBotonNombre)
                    .addComponent(radioBotonCodigo))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 6, 0, 0);
        add(jPanel2, gridBagConstraints);

        etiquetaAlertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        etiquetaAlertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 788;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        add(etiquetaAlertaTabla, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jSeparator1, gridBagConstraints);

        textoBusqueda.setForeground(new java.awt.Color(0, 102, 153));
        textoBusqueda.setBordeColorFocus(new java.awt.Color(51, 102, 255));
        textoBusqueda.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusqueda.setPlaceholder("Realizar Busqueda");
        textoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBusquedaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 340;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(textoBusqueda, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
   
  
    /*
    Metodo encargado de mostrar todos los paquetes asignados al punto de control y al operador actual.
    Obtiene el punto de control seleccionado y lo almacena en la variable puntoDeControl posteriormente
    inicializa el panel PaquetesEnPuntoDeControl y lo hace visible.
    */
    private void botonVerPaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerPaquetesActionPerformed
        if(tablaPuntosDeControl.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione un punto de control");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
        else{
            puntoDeControl = listadoPuntosDeControl.get(tablaPuntosDeControl.getSelectedRow());
            panelPaqueteEnPuntoDeControl.establecerInformacionPuntoDeControl(puntoDeControl);
            panelPaqueteEnPuntoDeControl.obtenerPaquetes(puntoDeControl);
            for(int i = 0; i < this.getComponentCount(); i++){
               this.getComponent(i).setVisible(false);
            }
            this.add(panelPaqueteEnPuntoDeControl);
            panelPaqueteEnPuntoDeControl.setVisible(true);
        }
    }//GEN-LAST:event_botonVerPaquetesActionPerformed
   /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosLosRegistros.
    */
    private void selectorMostrarTodosLosRegistrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosLosRegistrosItemStateChanged
        if(selectorMostrarTodosLosRegistros.isSelected()){
            selectorFiltrado.clearSelection();
            textoBusqueda.setText("");
            this.obtenerPuntosDeControl(1);
        }
        else{
            this.obtenerPuntosDeControl(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaKeyReleased
        if(!radioBotonNombre.isSelected() && !radioBotonCodigoRuta.isSelected() && !radioBotonCodigo.isSelected()){
            etiquetaAlertaTabla.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
            textoBusqueda.setText("");
        }
        else{  
            patronBusqueda = textoBusqueda.getText();
            if(patronBusqueda.equals("")){
                this.obtenerPuntosDeControl(0);
            }
            else{
                this.establecerFiltroDeBusqueda();
            }
        }
    }//GEN-LAST:event_textoBusquedaKeyReleased
    
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonNombreActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonNombreActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonCodigoRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonCodigoRutaActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonCodigoRutaActionPerformed
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonCodigoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonCodigoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonVerPaquetes;
    private javax.swing.JLabel etiquetaAlertaTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonCodigo;
    private javax.swing.JRadioButton radioBotonCodigoRuta;
    private javax.swing.JRadioButton radioBotonNombre;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JTable tablaPuntosDeControl;
    private rojeru_san.RSMTextFull textoBusqueda;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
