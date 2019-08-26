package Frontend.Recepcionista;
import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.Paquete;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class PanelLocalizarPaquete extends javax.swing.JPanel {

    //Variables e instancias de la clase.
    private ManejadorDBSM manejadorDB;
    private ManejadorHilos manejadorHilos;
    private ManejadorBusqueda manejadorBusqueda;
    private List<Paquete> listadoPaquetes;
    private ObservableList<Paquete> observableListPaquetes;
    private String mensaje;
    private String patronBusqueda;
    private Paquete paquete;
    private int puntoDeControlActual;
    private int puntosDeControlTotales;
    private double cantidadHoras;
    
    //Constructor de la clase
    public PanelLocalizarPaquete() {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorHilos = new ManejadorHilos();
        this.listadoPaquetes = new ArrayList<>();
        this.observableListPaquetes = ObservableCollections.observableList(listadoPaquetes);
        initComponents();
    }

    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Paquete> getObservableList() {
        return observableListPaquetes;
    }

    public void setObservableList(ObservableList<Paquete> observableList) {
        this.observableListPaquetes = observableList;
    }
    
    public void llenarTabla(List listado){
        this.observableListPaquetes.clear();
        this.observableListPaquetes.addAll(listado);
    }
   
    public void obtenerPaquetes(int tipo){
        if(tipo == 0){
            listadoPaquetes = manejadorDB.obtenerListadoPaquetes("SELECT A.Codigo, A.Peso, A.Destino, A.PrecioTotal, A.DPICliente FROM Paquete A, PaqueteAsignadoRuta B WHERE B.EnDestino = FALSE && A.Codigo = B.CodigoPaquete LIMIT 45;");
        }
        else{
            listadoPaquetes = manejadorDB.obtenerListadoPaquetes("SELECT A.Codigo, A.Peso, A.Destino, A.PrecioTotal, A.DPICliente FROM Paquete A, PaqueteAsignadoRuta B WHERE B.EnDestino = FALSE && A.Codigo = B.CodigoPaquete");
        }
        this.llenarTabla(listadoPaquetes);
    }
    //-------------------------------------------------------------------------------
    
    
    /*
    Metodo encargado de mostrar un mensaje en pantalla, limpiar las areas de alerta y
    refrescar el listado de usuarios.
    */
    public void finalizarAccion(){
        this.lanzarMensaje(mensaje);
        if(!radioBotonDPI.isSelected() && !radioBotonCodigo.isSelected() && !radioBotonDestino.isSelected()){
            if(!selectorMostrarTodosLosRegistros.isSelected()){
                this.obtenerPaquetes(0);
            }
            else{
                this.obtenerPaquetes(1);
            }   
        }
        else{
            this.establecerFiltroDeBusqueda();
        }
    }
    
    /*
    Metodo encargado de validar el RadioBoton que se encuentra seleccionado y en base a esa validacion establecer
    el metodo de busqueda a realizar.
    */
    public void establecerFiltroDeBusqueda(){
        selectorMostrarTodosLosRegistros.setSelected(false);
        if(radioBotonDPI.isSelected()){    
            listadoPaquetes = this.manejadorBusqueda.busquedaPaquetePorDPI(patronBusqueda,1);
        }
        if(radioBotonCodigo.isSelected()){
            listadoPaquetes = this.manejadorBusqueda.busquedaPaquetePorCodigo(patronBusqueda,1);
        }
        if(radioBotonDestino.isSelected()){
            listadoPaquetes = this.manejadorBusqueda.busquedaPaquetePorDestino(patronBusqueda,1);
        }
        this.llenarTabla(listadoPaquetes);
    }
    
   //Metodo encargado de mostrar en pantalla el mensaje de error que recibe como parametro. 
   public void lanzarMensaje(String mensaje){
        etiquetaMensaje.setText(mensaje);
        MostradorMensajes.setLocationRelativeTo(this);
        MostradorMensajes.setVisible(true);
   }
   
   //Metodo encargado de remover la seleccion de todas las casillas de seleccion
   public void inicializarCasillasSeleccion(){
       textoBusqueda.setText("");
       selectorMostrarTodosLosRegistros.setSelected(false);
       selectorFiltrado.clearSelection();
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectorFiltrado = new javax.swing.ButtonGroup();
        MostradorMensajes = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPaquete = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        botonLocalizar = new rojerusan.RSButtonIconI();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonCodigo = new javax.swing.JRadioButton();
        radioBotonDestino = new javax.swing.JRadioButton();
        radioBotonDPI = new javax.swing.JRadioButton();
        etiquetaAlertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textoBusqueda = new rojeru_san.RSMTextFull();

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(600, 200));
        MostradorMensajes.setModal(true);
        MostradorMensajes.setResizable(false);

        jPanel7.setBackground(new java.awt.Color(0, 153, 255));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));

        etiquetaMensaje.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(0, 153, 255));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botonAceptarMensaje.setBackground(new java.awt.Color(0, 153, 255));
        botonAceptarMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarMensaje.setText("ACEPTAR");
        botonAceptarMensaje.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarMensajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout MostradorMensajesLayout = new javax.swing.GroupLayout(MostradorMensajes.getContentPane());
        MostradorMensajes.getContentPane().setLayout(MostradorMensajesLayout);
        MostradorMensajesLayout.setHorizontalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MostradorMensajesLayout.setVerticalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorMensajesLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(new java.awt.GridBagLayout());

        tablaPaquete.setBackground(new java.awt.Color(204, 204, 204));
        tablaPaquete.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaPaquete.setForeground(new java.awt.Color(0, 102, 153));
        tablaPaquete.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPaquete.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tablaPaquete.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPaquete);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${DPI}"));
        columnBinding.setColumnName("DPI de Cliente");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Codigo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${destino}"));
        columnBinding.setColumnName("Destino");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${peso}"));
        columnBinding.setColumnName("Peso");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaPaquete);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1175;
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
        gridBagConstraints.gridheight = 3;
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(selectorMostrarTodosLosRegistros, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        botonLocalizar.setBackground(new java.awt.Color(0, 153, 255));
        botonLocalizar.setForeground(new java.awt.Color(0, 102, 153));
        botonLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/destino.png"))); // NOI18N
        botonLocalizar.setText("Localizar");
        botonLocalizar.setColorHover(new java.awt.Color(153, 153, 153));
        botonLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLocalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(botonLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(botonLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(153, 6, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Filtrado por:");

        radioBotonCodigo.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonCodigo);
        radioBotonCodigo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonCodigo.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonCodigo.setText("Codigo");
        radioBotonCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonCodigoActionPerformed(evt);
            }
        });

        radioBotonDestino.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonDestino);
        radioBotonDestino.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonDestino.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonDestino.setText("Destino");
        radioBotonDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonDestinoActionPerformed(evt);
            }
        });

        radioBotonDPI.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonDPI);
        radioBotonDPI.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonDPI.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonDPI.setText("DPI");
        radioBotonDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonDPIActionPerformed(evt);
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
                .addComponent(radioBotonDPI)
                .addGap(18, 18, 18)
                .addComponent(radioBotonCodigo)
                .addGap(18, 18, 18)
                .addComponent(radioBotonDestino)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonCodigo)
                    .addComponent(radioBotonDPI)
                    .addComponent(radioBotonDestino))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel2, gridBagConstraints);

        etiquetaAlertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        etiquetaAlertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 788;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 6, 0);
        add(etiquetaAlertaTabla, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jSeparator1, gridBagConstraints);

        textoBusqueda.setForeground(new java.awt.Color(0, 102, 153));
        textoBusqueda.setBordeColorFocus(new java.awt.Color(0, 153, 255));
        textoBusqueda.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusqueda.setDoubleBuffered(true);
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
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 340;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(textoBusqueda, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
   
    /*
    Metodo encargado de obtener la ubicacion actual del paquete y la cantidad total de puntos de control
    por los que debe de pasar. por ultimo lanza un mensaje indicando los datos obtenidos.
    */
    private void botonLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLocalizarActionPerformed
        if(tablaPaquete.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione un paquete");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
        else{
            paquete = listadoPaquetes.get(tablaPaquete.getSelectedRow());
            puntoDeControlActual = manejadorDB.obtenerPuntoDeControlActual(paquete);
            puntosDeControlTotales = manejadorDB.obtenerTotalPuntosDeControlAsignados(paquete);
            cantidadHoras = manejadorDB.obtenerTotalHorasEnPuntoDeControl(paquete);
            mensaje = "Punto de control Actual: "+puntoDeControlActual + "/" + puntosDeControlTotales + " Horas trasncurridas: " + cantidadHoras;  
            this.finalizarAccion();
        }
    }//GEN-LAST:event_botonLocalizarActionPerformed
   /*
    Metodo encargado de cerrar el JDialog MostradorMensajes.
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed

    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosLosRegistros.
    */
    private void selectorMostrarTodosLosRegistrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosLosRegistrosItemStateChanged
        if(selectorMostrarTodosLosRegistros.isSelected()){
            selectorFiltrado.clearSelection();
            textoBusqueda.setText("");
            this.obtenerPaquetes(1);
        }
        else{
            this.obtenerPaquetes(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaKeyReleased
        if(!radioBotonDPI.isSelected() && !radioBotonCodigo.isSelected() && !radioBotonDestino.isSelected()){
            etiquetaAlertaTabla.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
            textoBusqueda.setText("");
        }
        else{  
            patronBusqueda = textoBusqueda.getText();
            if(patronBusqueda.equals("")){
                this.obtenerPaquetes(0);
            }
            else{
                this.establecerFiltroDeBusqueda();
            }
        }
    }//GEN-LAST:event_textoBusquedaKeyReleased
    
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonDPIActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonDPIActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonCodigoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonCodigoActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonDestinoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonDestinoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MostradorMensajes;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonLocalizar;
    private javax.swing.JLabel etiquetaAlertaTabla;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonCodigo;
    private javax.swing.JRadioButton radioBotonDPI;
    private javax.swing.JRadioButton radioBotonDestino;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JTable tablaPaquete;
    private rojeru_san.RSMTextFull textoBusqueda;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
