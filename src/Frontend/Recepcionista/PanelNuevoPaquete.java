package Frontend.Recepcionista;
import Backend.Cliente;
import Backend.ManejadorBusqueda;
import Backend.ManejadorCalculos;
import Backend.ManejadorCodigo;
import Backend.ManejadorDBSM;
import Backend.ManejadorFechas;
import Backend.ManejadorHilos;
import Backend.Paquete;
import Backend.Ruta;
import Backend.Tarifa;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class PanelNuevoPaquete extends javax.swing.JPanel {
    
    //Variables e instancias de la clase.
    private List<Ruta> listadoDestinos;
    private ObservableList<Ruta> observableListDestinos;
    private List<Paquete> listadoPaquetes;
    private ManejadorDBSM manejadorDB;
    private ManejadorBusqueda manejadorBusqueda;
    private ManejadorCodigo manejadorCodigo;
    private ManejadorCalculos manejadorCalculos;
    private ManejadorFechas manejadorFechas;
    private ManejadorHilos manejadorHilos;
    private PanelInformacionFacturacion panelInformacionFacturacion;
    //Paquete
    private Paquete paquete;
    private double peso;
    private String destino;
    private boolean priorizacion;
    private double cuotaPriorizacion;
    private double cuotaDestino;
    private String codigo;
    private double precioLibra;
    private double total;
    private Timestamp fechaEntrada;
    //Factuacion
    private String NITFacturacion;
    private String DPIFacturacion;
    private String codigoInicial;
    //Cliente
    private Cliente cliente;
    private String nombre;
    private String apellido;
    private String NIT;
    private String DPI;
    private String direccion;
            
    //Varios
    private Tarifa tarifa;
    private String patronBusqueda;
    private String mensaje;
    
    public PanelNuevoPaquete() {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorHilos = new ManejadorHilos();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorCodigo = new ManejadorCodigo();
        this.manejadorFechas = new ManejadorFechas();
        this.manejadorCalculos = new ManejadorCalculos();
        this.panelInformacionFacturacion = new PanelInformacionFacturacion(null, true);
        this.listadoDestinos = new ArrayList<>();
        this.observableListDestinos = ObservableCollections.observableList(listadoDestinos);
        this.listadoPaquetes = new ArrayList<>();
        initComponents();
    }
    
    /*
    Metodos utilizados para la implementacion de Beans Beanding en la tabla de rutas
    */
    public ObservableList<Ruta> getObservableListRutas() {
        return observableListDestinos;
    }

    public void setObservableListRutas(ObservableList<Ruta> observableList) {
        this.observableListDestinos = observableList;
    }
    
    public void llenarTablaDestinos(List listado){
        this.observableListDestinos.clear();
        this.observableListDestinos.addAll(listado);
    }
    
    public void obtenerDestinos(int tipo){
        if(tipo == 0){
            listadoDestinos = manejadorDB.obtenerListadoRutas("SELECT DISTINCT Destino FROM Ruta WHERE Activa = TRUE LIMIT 45;");
        }
        else{
            listadoDestinos = manejadorDB.obtenerListadoRutas("SELECT DISTINCT Destino FROM Ruta WHERE Activa = TRUE;");
        }
        this.llenarTablaDestinos(listadoDestinos);
    }
    
    /*
    Metodo encargado de crear una nueva instancia de la clase paquete y la almacena dentro de una lista.
    Obtiene la informacion almacenada en los campos de texto y la guarda en sus variables determinadas, 
    ademas obtiene los atributos restantes del objeto (codigo, total, fecha).
    */
    public boolean crearPaquete(){
        try{
            peso = Double.parseDouble(textoPeso.getText());
            destino = textoDestino.getText();
            //Obtener codigo
            codigo = manejadorDB.obtenerCodigoPaquete();
            if(listadoPaquetes.isEmpty()){
                codigoInicial = codigo;
            }
            codigo = manejadorCodigo.obtenerNuevoCodigo(codigo);
            manejadorDB.actualizarCodigoPaquete(codigo);
            //Obtener precio del paquete
            total = manejadorCalculos.calcularPrecioPaquete(peso, precioLibra, cuotaDestino, cuotaPriorizacion, priorizacion);
            //Obtener hora y fecha de entrada
            fechaEntrada = manejadorFechas.obtenerFechaActual();
            //Crear el nuevo paquete
            paquete = new Paquete(codigo, peso, priorizacion, destino, cuotaDestino, cuotaPriorizacion, precioLibra, total, "", fechaEntrada);
            manejadorDB.actualizarCodigoPaquete(paquete.getCodigo());
            listadoPaquetes.add(paquete);
        }
        catch(NumberFormatException e){
            etiquetaAlertaNuevoPaquete.setText("Peso no valido");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaNuevoPaquete);
            return false;
        }
        return true;
    }
    
    /*
    Metodo encargado de inicializar el panel en blanco
    */
    public void inicializar(){
        textoPeso.setText("");
        textoDestino.setText("");
        priorizar.setSelected(false);
        tarifa = manejadorDB.recuperarTarifas();
        cuotaPriorizacion = tarifa.getCuotaPriorizacionGlobal();
        cuotaDestino = tarifa.getCuotaDestinoGlobal();
        precioLibra = tarifa.getPrecioLibraGlobal();
        etiquetaPrecioDestino.setText("Precio de destino: Q0.00");
    }

    /*
    Metodo encargado de limpiar el listado de paquetes
    */
    public void limpiarListadoPaquetes(){
        listadoPaquetes.clear();
    }
    
    /*
    Metodo encargado de llenar la informacion del JDialog y lo hace visible
    */
    public void llenarInformacionFacturacion(){
        panelInformacionFacturacion.llenarInformacionFacturacion(listadoPaquetes);
        panelInformacionFacturacion.llenarInformacionEncabezado(cliente.getNombre(), cliente.getApellido(), cliente.getNIT(), cliente.getDireccion());
        panelInformacionFacturacion.setLocationRelativeTo(this);
        panelInformacionFacturacion.setVisible(true);
    }
    
    /*
    Metodo encargado de hacer visible el JDialog encargado de recibir el NIT 
    */
    public void mostrarReceptorNIT(){
        textoNITFacturacion.setText("");
        ReceptorNIT.setLocationRelativeTo(this);
        ReceptorNIT.setVisible(true);
    }
    
    /*
    Metodo encargado de hacer visible el JDialog encargado de recibir el DPI
    */
    public void mostrarReceptorDPI(){
        textoDPIFacturacion.setText("");
        ReceptorDPI.setLocationRelativeTo(this);
        ReceptorDPI.setVisible(true);
    }
    
    /*
    Metodo encargado de asignar el DPI del cliente a todos los paquetes que se van a procesar
    */
    public void asignarDPI(){
        for(int i = 0; i < listadoPaquetes.size(); i++){
            listadoPaquetes.get(i).setDPI(cliente.getDPI());
        }
    }
    
    /*
    Metodo encargado de guardar en la base de datos los paquetes ingresados
    */
    public void ingresarPaquetes(){
        areaResumenPaquetesIngresados.setText("");
        for(int i = 0; i < listadoPaquetes.size(); i++){
        //Ingresar a tabla Paquetes
        mensaje = manejadorDB.crearNuevoPaquete(listadoPaquetes.get(i));
        //Ingreasar a bodega
        manejadorDB.guardarPaqueteEnBodega(listadoPaquetes.get(i));
        areaResumenPaquetesIngresados.setText(areaResumenPaquetesIngresados.getText() + mensaje +"\n");
        }
    }
    
    /*
    Metodo encargado de lanzar un mensaje informativo, limpiar el listado de paquetes e inicializa
    el panel de paquetes.
    */
    public void finaizarAccion(int tipo){
        if(tipo == 0){
            this.lanzarMensajePaquetesIngresados(mensaje);
        }else{
            //lanzar mensaje informativo
        }
        this.limpiarListadoPaquetes();
        this.inicializar();
    }
    
    /*
    Metodo encargado de mostrar el JDialog encargado de mostrar los paquetes ingresados
    */
    public void lanzarMensajePaquetesIngresados(String mensaje){
        MostradorPaquetesIngresados.setLocationRelativeTo(this);
        MostradorPaquetesIngresados.setVisible(true);
    }
    
    /*
    Metodo encargado de mostrar el JDialog MostradorMensajes.
    */
    public void lanzarMensaje(String mensaje){
        etiquetaMensaje.setText(mensaje);
        MostradorMensajes.setLocationRelativeTo(this);
        MostradorMensajes.setVisible(true);
    }
    
    /*
    Metodo encargado de asignar el DPI del cliente a cada uno de los paquetes llamando al metodo asignarDPI, 
    posteriormente ingresa los paquetes a la base de datos llamando al metodo ingresarPaquetes y llama al 
    metodo finalizarAccion para terminar con el proceso.
    */
    public void realizarFacturacion(){
        this.llenarInformacionFacturacion();
        //Validar que se acepte el ingreso de los paquetes
        if(panelInformacionFacturacion.devolverRespuesta()){
            this.asignarDPI(); 
            this.ingresarPaquetes();
            this.finaizarAccion(0);
        }
        else{
            this.limpiarListadoPaquetes();
            this.inicializar();
            manejadorDB.actualizarCodigoPaquete(codigoInicial);
        }
    } 
    
    /*
    Metodo encargado de limpiar las areas de texto y reestablecer a los valores iniciales el 
    JDialog CrearCliente
    */
    public void limpiarCreadorCliente(){
        textoNombresCliente.setText("");
        textoApellidosCliente.setText("");
        textoNITCliente.setText("");
        textoDPICliente.setText("");
        textoDireccionCliente.setText("");
    }
    
    /*
    Metodo encargado de hacer visible el JDialog CrearCliente y establecer sus
    valores iniciales.
    */
    public void inicializarCreadorCliente(int tipo){
        if(tipo == 0){
            textoNITCliente.setText(NITFacturacion);
        }
        else{
            textoNITCliente.setText("CF");
            textoDPICliente.setText(DPIFacturacion);
        }
        CrearCliente.setLocationRelativeTo(this);
        CrearCliente.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        MostrarDestinos = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDestinos = new javax.swing.JTable();
        aceptarDestino = new rojerusan.RSButtonIconI();
        etiquetaAlertaTablaDestinos = new javax.swing.JLabel();
        selectorMostrarTodosDestinos = new javax.swing.JCheckBox();
        textoBusquedaDestino = new rojeru_san.RSMTextFull();
        botonBuscar1 = new rojerusan.RSButtonIconI();
        ReceptorNIT = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        botonAceptarNITFacturacion = new rojerusan.RSButtonIconI();
        textoNITFacturacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        etiquetaAlertaNIT = new javax.swing.JLabel();
        MostradorPaquetesIngresados = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        botonAceptarMensajePaquetes = new rojerusan.RSButtonIconI();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaResumenPaquetesIngresados = new javax.swing.JTextArea();
        ReceptorDPI = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        botonAceptarDPIFacturacion = new rojerusan.RSButtonIconI();
        jLabel2 = new javax.swing.JLabel();
        etiquetaAlertaDPI = new javax.swing.JLabel();
        textoDPIFacturacion = new javax.swing.JFormattedTextField();
        CrearCliente = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textoNITCliente = new javax.swing.JTextField();
        textoNombresCliente = new javax.swing.JTextField();
        textoApellidosCliente = new javax.swing.JTextField();
        etiquetaAlertaCliente = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textoDPICliente = new javax.swing.JFormattedTextField();
        textoDireccionCliente = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        botonCrearModificar = new rojerusan.RSButtonIconI();
        MostradorMensajes = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textoPeso = new javax.swing.JTextField();
        etiquetaAlertaNuevoPaquete = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        botonSeleccionarOperador = new rojerusan.RSButtonIconI();
        textoDestino = new rojeru_san.RSMTextFull();
        priorizar = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        etiquetaPrecioDestino = new javax.swing.JLabel();
        etiquetaPriorizacion = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        botonFacturar = new rojerusan.RSButtonIconI();
        botonSiguiente = new rojerusan.RSButtonIconI();
        etiquetaInformacionPaquete = new javax.swing.JLabel();

        MostrarDestinos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        MostrarDestinos.setTitle("Seleccione un Destino");
        MostrarDestinos.setBackground(new java.awt.Color(204, 204, 204));
        MostrarDestinos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        MostrarDestinos.setForeground(new java.awt.Color(0, 153, 255));
        MostrarDestinos.setMinimumSize(new java.awt.Dimension(515, 600));
        MostrarDestinos.setModal(true);
        MostrarDestinos.setResizable(false);

        tablaDestinos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaDestinos.setForeground(new java.awt.Color(0, 153, 255));
        tablaDestinos.setSelectionBackground(new java.awt.Color(0, 153, 255));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListRutas}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaDestinos);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${destino}"));
        columnBinding.setColumnName("Destino");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(tablaDestinos);

        aceptarDestino.setBackground(new java.awt.Color(0, 153, 255));
        aceptarDestino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        aceptarDestino.setText("ACEPTAR");
        aceptarDestino.setColorHover(new java.awt.Color(153, 153, 153));
        aceptarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarDestinoActionPerformed(evt);
            }
        });

        etiquetaAlertaTablaDestinos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTablaDestinos.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaTablaDestinos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        selectorMostrarTodosDestinos.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodosDestinos.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodosDestinos.setText("Mostrar todos los Destinos");
        selectorMostrarTodosDestinos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodosDestinosItemStateChanged(evt);
            }
        });

        textoBusquedaDestino.setForeground(new java.awt.Color(0, 102, 153));
        textoBusquedaDestino.setBordeColorFocus(new java.awt.Color(0, 153, 255));
        textoBusquedaDestino.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusquedaDestino.setPlaceholder("Realizar Busqueda");
        textoBusquedaDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBusquedaDestinoKeyReleased(evt);
            }
        });

        botonBuscar1.setBackground(new java.awt.Color(204, 204, 204));
        botonBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/buscar.png"))); // NOI18N
        botonBuscar1.setBorderPainted(false);
        botonBuscar1.setColorHover(new java.awt.Color(153, 153, 153));
        botonBuscar1.setEnabled(false);

        javax.swing.GroupLayout MostrarDestinosLayout = new javax.swing.GroupLayout(MostrarDestinos.getContentPane());
        MostrarDestinos.getContentPane().setLayout(MostrarDestinosLayout);
        MostrarDestinosLayout.setHorizontalGroup(
            MostrarDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostrarDestinosLayout.createSequentialGroup()
                .addGroup(MostrarDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MostrarDestinosLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(MostrarDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectorMostrarTodosDestinos)
                            .addGroup(MostrarDestinosLayout.createSequentialGroup()
                                .addComponent(botonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoBusquedaDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(etiquetaAlertaTablaDestinos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(MostrarDestinosLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(aceptarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MostrarDestinosLayout.setVerticalGroup(
            MostrarDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostrarDestinosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(MostrarDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoBusquedaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectorMostrarTodosDestinos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaAlertaTablaDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        ReceptorNIT.setTitle("Ingresar NIT");
        ReceptorNIT.setBackground(new java.awt.Color(204, 204, 204));
        ReceptorNIT.setMinimumSize(new java.awt.Dimension(699, 260));
        ReceptorNIT.setModal(true);
        ReceptorNIT.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));
        jPanel14.setMaximumSize(new java.awt.Dimension(587, 151));
        jPanel14.setMinimumSize(new java.awt.Dimension(587, 151));

        botonAceptarNITFacturacion.setBackground(new java.awt.Color(0, 153, 255));
        botonAceptarNITFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarNITFacturacion.setText("ACEPTAR");
        botonAceptarNITFacturacion.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarNITFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarNITFacturacionActionPerformed(evt);
            }
        });

        textoNITFacturacion.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        textoNITFacturacion.setForeground(new java.awt.Color(0, 102, 153));
        textoNITFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNITFacturacionKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel1.setText("Ingrese numero de NIT:");

        etiquetaAlertaNIT.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaNIT.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaNIT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 112, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNITFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(botonAceptarNITFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaAlertaNIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNITFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptarNITFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlertaNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout ReceptorNITLayout = new javax.swing.GroupLayout(ReceptorNIT.getContentPane());
        ReceptorNIT.getContentPane().setLayout(ReceptorNITLayout);
        ReceptorNITLayout.setHorizontalGroup(
            ReceptorNITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ReceptorNITLayout.setVerticalGroup(
            ReceptorNITLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceptorNITLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MostradorPaquetesIngresados.setTitle("Mensaje");
        MostradorPaquetesIngresados.setBackground(new java.awt.Color(204, 204, 204));
        MostradorPaquetesIngresados.setMinimumSize(new java.awt.Dimension(620, 250));
        MostradorPaquetesIngresados.setModal(true);
        MostradorPaquetesIngresados.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setPreferredSize(new java.awt.Dimension(605, 168));

        botonAceptarMensajePaquetes.setBackground(new java.awt.Color(0, 153, 255));
        botonAceptarMensajePaquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarMensajePaquetes.setText("ACEPTAR");
        botonAceptarMensajePaquetes.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarMensajePaquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarMensajePaquetesActionPerformed(evt);
            }
        });

        areaResumenPaquetesIngresados.setEditable(false);
        areaResumenPaquetesIngresados.setColumns(20);
        areaResumenPaquetesIngresados.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        areaResumenPaquetesIngresados.setForeground(new java.awt.Color(0, 153, 255));
        areaResumenPaquetesIngresados.setRows(5);
        jScrollPane1.setViewportView(areaResumenPaquetesIngresados);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(botonAceptarMensajePaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptarMensajePaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout MostradorPaquetesIngresadosLayout = new javax.swing.GroupLayout(MostradorPaquetesIngresados.getContentPane());
        MostradorPaquetesIngresados.getContentPane().setLayout(MostradorPaquetesIngresadosLayout);
        MostradorPaquetesIngresadosLayout.setHorizontalGroup(
            MostradorPaquetesIngresadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        MostradorPaquetesIngresadosLayout.setVerticalGroup(
            MostradorPaquetesIngresadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorPaquetesIngresadosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );

        ReceptorDPI.setTitle("Ingresar DPI");
        ReceptorDPI.setBackground(new java.awt.Color(204, 204, 204));
        ReceptorDPI.setMinimumSize(new java.awt.Dimension(699, 260));
        ReceptorDPI.setModal(true);
        ReceptorDPI.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));
        jPanel16.setMaximumSize(new java.awt.Dimension(587, 151));
        jPanel16.setMinimumSize(new java.awt.Dimension(587, 151));

        botonAceptarDPIFacturacion.setBackground(new java.awt.Color(0, 153, 255));
        botonAceptarDPIFacturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarDPIFacturacion.setText("ACEPTAR");
        botonAceptarDPIFacturacion.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarDPIFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarDPIFacturacionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel2.setText("Ingrese numero de DPI:");

        etiquetaAlertaDPI.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaDPI.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaDPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textoDPIFacturacion.setForeground(new java.awt.Color(0, 102, 153));
        try {
            textoDPIFacturacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDPIFacturacion.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 113, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoDPIFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(botonAceptarDPIFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaAlertaDPI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoDPIFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptarDPIFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlertaDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout ReceptorDPILayout = new javax.swing.GroupLayout(ReceptorDPI.getContentPane());
        ReceptorDPI.getContentPane().setLayout(ReceptorDPILayout);
        ReceptorDPILayout.setHorizontalGroup(
            ReceptorDPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ReceptorDPILayout.setVerticalGroup(
            ReceptorDPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceptorDPILayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CrearCliente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CrearCliente.setTitle("Crear nuevo Cliente");
        CrearCliente.setMinimumSize(new java.awt.Dimension(700, 620));
        CrearCliente.setModal(true);
        CrearCliente.setResizable(false);
        CrearCliente.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setMaximumSize(new java.awt.Dimension(682, 593));
        jPanel5.setMinimumSize(new java.awt.Dimension(682, 593));
        jPanel5.setName(""); // NOI18N

        jPanel6.setBackground(new java.awt.Color(0, 153, 255));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/destino.png"))); // NOI18N
        jLabel4.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel5.setText("DPI:");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel6.setText("Nombres:");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        textoNITCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNITCliente.setForeground(new java.awt.Color(0, 102, 153));
        textoNITCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNITClienteKeyTyped(evt);
            }
        });

        textoNombresCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombresCliente.setForeground(new java.awt.Color(0, 102, 153));
        textoNombresCliente.setToolTipText("");
        textoNombresCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombresClienteKeyTyped(evt);
            }
        });

        textoApellidosCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoApellidosCliente.setForeground(new java.awt.Color(0, 102, 153));
        textoApellidosCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoApellidosClienteKeyTyped(evt);
            }
        });

        etiquetaAlertaCliente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaCliente.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel13.setText("NIT:");

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        textoDPICliente.setForeground(new java.awt.Color(0, 102, 153));
        try {
            textoDPICliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDPICliente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N

        textoDireccionCliente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoDireccionCliente.setForeground(new java.awt.Color(0, 102, 153));
        textoDireccionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoDireccionClienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(textoApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNombresCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoDireccionCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoDPICliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNITCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(textoNombresCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(textoApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(textoDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(textoDPICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaAlertaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(textoNITCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        botonCrearModificar.setBackground(new java.awt.Color(0, 153, 255));
        botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/crear.png"))); // NOI18N
        botonCrearModificar.setText("CREAR");
        botonCrearModificar.setColorHover(new java.awt.Color(153, 153, 153));
        botonCrearModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(botonCrearModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonCrearModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        CrearCliente.getContentPane().add(jPanel5, gridBagConstraints);

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(600, 200));
        MostradorMensajes.setModal(true);
        MostradorMensajes.setResizable(false);

        jPanel12.setBackground(new java.awt.Color(0, 153, 255));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(202, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout MostradorMensajesLayout = new javax.swing.GroupLayout(MostradorMensajes.getContentPane());
        MostradorMensajes.getContentPane().setLayout(MostradorMensajesLayout);
        MostradorMensajesLayout.setHorizontalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MostradorMensajesLayout.setVerticalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorMensajesLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setMaximumSize(new java.awt.Dimension(701, 562));
        setMinimumSize(new java.awt.Dimension(701, 562));
        setPreferredSize(new java.awt.Dimension(701, 562));
        setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel8.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel8.setName(""); // NOI18N
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/pin-ubicacion.png"))); // NOI18N
        jLabel11.setText("Destino:");

        etiquetaNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaNombre.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/peso.png"))); // NOI18N
        etiquetaNombre.setText("Peso:");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        textoPeso.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoPeso.setForeground(new java.awt.Color(0, 102, 153));
        textoPeso.setToolTipText("");

        etiquetaAlertaNuevoPaquete.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaNuevoPaquete.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaNuevoPaquete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaNuevoPaquete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/prioridad.png"))); // NOI18N
        jLabel18.setText("Priorizacion:");

        botonSeleccionarOperador.setBackground(new java.awt.Color(204, 204, 204));
        botonSeleccionarOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/seleccionar.png"))); // NOI18N
        botonSeleccionarOperador.setBorderPainted(false);
        botonSeleccionarOperador.setColorHover(new java.awt.Color(0, 153, 255));
        botonSeleccionarOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeleccionarOperadorActionPerformed(evt);
            }
        });

        textoDestino.setEditable(false);
        textoDestino.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        textoDestino.setBotonColor(new java.awt.Color(255, 255, 255));
        textoDestino.setMaximumSize(new java.awt.Dimension(12, 31));
        textoDestino.setMinimumSize(new java.awt.Dimension(12, 31));
        textoDestino.setPlaceholder("");
        textoDestino.setPreferredSize(new java.awt.Dimension(12, 31));
        textoDestino.setSelectedTextColor(new java.awt.Color(0, 102, 153));
        textoDestino.setSoloNumeros(true);

        priorizar.setBackground(new java.awt.Color(204, 204, 204));
        priorizar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        priorizar.setForeground(new java.awt.Color(0, 102, 153));
        priorizar.setText("Priorizar");
        priorizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                priorizarItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 153));
        jLabel19.setText("lb(s)");

        etiquetaPrecioDestino.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaPrecioDestino.setForeground(new java.awt.Color(102, 102, 102));
        etiquetaPrecioDestino.setText("Precio de destino: Q0.00");

        etiquetaPriorizacion.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaPriorizacion.setForeground(new java.awt.Color(102, 102, 102));
        etiquetaPriorizacion.setText("Precio de priorizacion: Q0.00");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(etiquetaNombre)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(textoDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                            .addComponent(textoPeso, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(botonSeleccionarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel19))))
                                    .addComponent(etiquetaPrecioDestino)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priorizar)
                                    .addComponent(etiquetaPriorizacion))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(etiquetaAlertaNuevoPaquete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre)
                    .addComponent(jLabel14)
                    .addComponent(textoPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonSeleccionarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaPrecioDestino))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(priorizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaPriorizacion))
                    .addComponent(jLabel18))
                .addGap(18, 37, Short.MAX_VALUE)
                .addComponent(etiquetaAlertaNuevoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(105, 62, 0, 0);
        jPanel8.add(jPanel10, gridBagConstraints);

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        botonFacturar.setBackground(new java.awt.Color(0, 153, 255));
        botonFacturar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonFacturar.setText("FACTURAR");
        botonFacturar.setColorHover(new java.awt.Color(153, 153, 153));
        botonFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacturarActionPerformed(evt);
            }
        });

        botonSiguiente.setBackground(new java.awt.Color(0, 153, 255));
        botonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/Siguiente.png"))); // NOI18N
        botonSiguiente.setText("MAS PAQUETES");
        botonSiguiente.setColorHover(new java.awt.Color(153, 153, 153));
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        etiquetaInformacionPaquete.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaInformacionPaquete.setForeground(new java.awt.Color(0, 153, 0));
        etiquetaInformacionPaquete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaInformacionPaquete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonFacturar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaInformacionPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 93;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 106, 6);
        jPanel8.add(jPanel11, gridBagConstraints);

        add(jPanel8, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    /*
    Metodo encargado del proceso de facturacion. Solicita el NIT del cliente y realiza la facturacion. 
    Si el NIT no se encuentra enlazado a ningun cliente se crea un nuevo cliente y se realiza la facturacion.
    Si el NIT es consumidor final se procede a solicitar el numero de DPI, si el DPI no se encuentra
    enlazado a ningun cliente se crea un nuevo cliente y se realiza la facturacion.
    */
    private void botonFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacturarActionPerformed
        if(textoPeso.getText().isEmpty() || textoDestino.getText().isEmpty()){
            etiquetaAlertaNuevoPaquete.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaNuevoPaquete);
        }
        else{
            if(this.crearPaquete()){
                //Solicitar NIT, si es cf se procede a solicitar DPI 
                this.mostrarReceptorNIT();
                if(!NITFacturacion.equalsIgnoreCase("cf")){
                    //Buscar cliente con el numero de NIT dado y proceder a facturacion. De lo contrario crear un nuevo cliente.
                    if(manejadorDB.buscarClientePorNIT(NITFacturacion)){
                        cliente = manejadorDB.obtenerClientePorNIT(NITFacturacion);
                        this.realizarFacturacion();
                    }
                    else{
                        this.inicializarCreadorCliente(0);
                        cliente = manejadorDB.obtenerClientePorNIT(NITFacturacion);
                        this.realizarFacturacion();
                    }
                }
                else{
                    this.mostrarReceptorDPI();
                    //Buscar cliente con el numero de DPI dado y proceder a facturacion. De lo contrario se registra un nuevo cliente.
                    if(manejadorDB.buscarClientePorDPI(DPIFacturacion)){
                        cliente = manejadorDB.obtenerClientePorDPI(DPIFacturacion);
                        this.realizarFacturacion();
                    }
                    else{
                        this.inicializarCreadorCliente(1);
                        cliente = manejadorDB.obtenerClientePorDPI(DPIFacturacion);
                        this.realizarFacturacion();
                    } 
                }
            }
        }
    }//GEN-LAST:event_botonFacturarActionPerformed
    /*
    Metodo encargado de validad que todos los campos se encuentren llenos. Posteriormente llama a los metodos
    crearPaquete y inicializar.
    */
    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        if(textoPeso.getText().isEmpty() || textoDestino.getText().isEmpty()){
            etiquetaAlertaNuevoPaquete.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaNuevoPaquete);
        }
        else{
            if(this.crearPaquete()){
                this.inicializar();
                etiquetaInformacionPaquete.setText("Paquete registrado exitosamente");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaInformacionPaquete);
            }
        }
    }//GEN-LAST:event_botonSiguienteActionPerformed
     /*
    Metodo encargado de abrir un JDialog que muestra los destinos de envio
    */
    private void botonSeleccionarOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeleccionarOperadorActionPerformed
        this.obtenerDestinos(0);
        MostrarDestinos.setLocationRelativeTo(this);
        MostrarDestinos.setVisible(true);
    }//GEN-LAST:event_botonSeleccionarOperadorActionPerformed
    /*
    Metodo encargdo de establecer si se aplica o no priorizacion al paquete. Si se aplica se imprime en una 
    etiqueta el precio que tendra la priorizaion.
    */
    private void priorizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_priorizarItemStateChanged
        priorizacion = priorizar.isSelected();
        if(priorizacion){ 
            etiquetaPriorizacion.setText("Precio de priorizacion: Q"+ String.valueOf(cuotaPriorizacion));
        }
        else{
            etiquetaPriorizacion.setText(String.valueOf("Precio de priorizacion: Q0.00"));
        }
    }//GEN-LAST:event_priorizarItemStateChanged
    /*
    Metodo encargado de obtener el destino del paquete. Valida que se encuente seleccionado un destino y lo 
    almacena en la variable destino, imprime el valor en un campo de texto y cierra el JDialog.
    */
    private void aceptarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarDestinoActionPerformed
        if(tablaDestinos.getSelectedRow() == -1){
            etiquetaAlertaTablaDestinos.setText("Seleccione un destino");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaDestinos);
        }
        else{
            destino = listadoDestinos.get(tablaDestinos.getSelectedRow()).getDestino();
            textoDestino.setText(destino);
            etiquetaPrecioDestino.setText("Precio de destino: Q"+ String.valueOf(cuotaDestino));
            MostrarDestinos.setVisible(false);
        }
    }//GEN-LAST:event_aceptarDestinoActionPerformed
    /*
    Metodo encagado de mostrar segun la seleccion que se tenga los primeros 45 destinos 
    o el listado completo de destinos.
    */
    private void selectorMostrarTodosDestinosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosDestinosItemStateChanged
        if(selectorMostrarTodosDestinos.isSelected()){
            this.obtenerDestinos(1); 
        }
        else{
            this.obtenerDestinos(0);
            
        }
        textoBusquedaDestino.setText("");
    }//GEN-LAST:event_selectorMostrarTodosDestinosItemStateChanged
    /*
    Metodo encargado de realizar una busqueda basado en el patron ingresado y almacenado en la variable
    patonBusqueda.
    */
    private void textoBusquedaDestinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaDestinoKeyReleased
        patronBusqueda = textoBusquedaDestino.getText();
        if(patronBusqueda.equals("")){
            this.obtenerDestinos(0);
        }
        else{
            listadoDestinos= this.manejadorBusqueda.busquedaRutaPorDestino(patronBusqueda, 1);
            this.llenarTablaDestinos(listadoDestinos);
        }
    }//GEN-LAST:event_textoBusquedaDestinoKeyReleased
    /*
    Metodo encargado de obtener el NIT para realizar la facturacion
    */
    private void botonAceptarNITFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarNITFacturacionActionPerformed
        if(textoNITFacturacion.getText().isEmpty()){
            etiquetaAlertaNIT.setText("Ingrese el numero de NIT");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaNIT);
        }
        else{
            NITFacturacion = textoNITFacturacion.getText();
            ReceptorNIT.setVisible(false);
        }
    }//GEN-LAST:event_botonAceptarNITFacturacionActionPerformed
    /*
    Metodo encargado de cerrar el JDialog encargado de mostrar los paquetes ingresados exitosamente.
    */
    private void botonAceptarMensajePaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajePaquetesActionPerformed
        MostradorPaquetesIngresados.dispose();
    }//GEN-LAST:event_botonAceptarMensajePaquetesActionPerformed
    /*
    Metodo encargado de limitar la cantidad de caracteres que se ingresan en un area de texto.
    */
    private void textoNITFacturacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNITFacturacionKeyTyped
        if (textoNITFacturacion.getText().length() == 9) {
            evt.consume();
            etiquetaAlertaNIT.setText("Solo se permite el ingreso de 9 caracteres.");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaNIT);
        }
    }//GEN-LAST:event_textoNITFacturacionKeyTyped
    /*
    Metodo encargado de obtener el DPI para realizar la facturacion
    */
    private void botonAceptarDPIFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarDPIFacturacionActionPerformed
       if(textoDPIFacturacion.getText().equals("    -     -    ")){
            etiquetaAlertaDPI.setText("Ingrese el numero de DPI");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaDPI);
        }
        else{
            DPIFacturacion = textoDPIFacturacion.getText();
            ReceptorDPI.setVisible(false);
        }
    }//GEN-LAST:event_botonAceptarDPIFacturacionActionPerformed
     /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNITClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNITClienteKeyTyped
        if (textoNITCliente.getText().length() == 9) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 9 caracteres en el campo de NIT");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoNITClienteKeyTyped
     /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNombresClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombresClienteKeyTyped
        if (textoNombresCliente.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombres");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoNombresClienteKeyTyped
     /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoApellidosClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoApellidosClienteKeyTyped
        if (textoApellidosCliente.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 30 caracteres en el campo de apellidos");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoApellidosClienteKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoDireccionClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDireccionClienteKeyTyped
        if (textoDireccionCliente.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 25 caracteres en el campo de direccion");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoDireccionClienteKeyTyped
    /*
    Metodo encargado de crear un nuevo cliente. Se valida que todos los campos obligatorios se encuentren
    llenos, de lo contrario se muestra una alerta. Posteriormente se obtienen los datos almacenados en 
    los campos de texto y se almacenand en su respectiva variable. Se crea una instancia de la clase 
    cliente y se le agregan sus atributos. Se llama al metodo crearNuevoCliente y se lanza un mensaje 
    informativo. Por ultimo se cierra el JDialog y se limpian sus areas de texto.
    */
    private void botonCrearModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearModificarActionPerformed
        if(textoNombresCliente.getText().isEmpty() || textoApellidosCliente.getText().isEmpty() || textoNITCliente.getText().isEmpty() || textoDPICliente.getText().equals("    -     -    ") || textoNITCliente.getText().isEmpty()){
            etiquetaAlertaCliente.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
        else{
            nombre = textoNombresCliente.getText();
            apellido = textoApellidosCliente.getText();
            direccion = textoDireccionCliente.getText();
            DPI = textoDPICliente.getText();
            NIT = textoNITCliente.getText();
            cliente = new Cliente(nombre, apellido, direccion, DPI, NIT);
            mensaje = manejadorDB.crearNuevoCliente(cliente);
            this.lanzarMensaje(mensaje);
            CrearCliente.setVisible(false);
            this.limpiarCreadorCliente();
        }
    }//GEN-LAST:event_botonCrearModificarActionPerformed
    /*
    Metodo encargado de cerra el JDialog MostradorMensajes.
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CrearCliente;
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog MostradorPaquetesIngresados;
    private javax.swing.JDialog MostrarDestinos;
    private javax.swing.JDialog ReceptorDPI;
    private javax.swing.JDialog ReceptorNIT;
    private rojerusan.RSButtonIconI aceptarDestino;
    private javax.swing.JTextArea areaResumenPaquetesIngresados;
    private rojerusan.RSButtonIconI botonAceptarDPIFacturacion;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonAceptarMensajePaquetes;
    private rojerusan.RSButtonIconI botonAceptarNITFacturacion;
    private rojerusan.RSButtonIconI botonBuscar1;
    private rojerusan.RSButtonIconI botonCrearModificar;
    private rojerusan.RSButtonIconI botonFacturar;
    private rojerusan.RSButtonIconI botonSeleccionarOperador;
    private rojerusan.RSButtonIconI botonSiguiente;
    private javax.swing.JLabel etiquetaAlertaCliente;
    private javax.swing.JLabel etiquetaAlertaDPI;
    private javax.swing.JLabel etiquetaAlertaNIT;
    private javax.swing.JLabel etiquetaAlertaNuevoPaquete;
    private javax.swing.JLabel etiquetaAlertaTablaDestinos;
    private javax.swing.JLabel etiquetaInformacionPaquete;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaPrecioDestino;
    private javax.swing.JLabel etiquetaPriorizacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox priorizar;
    private javax.swing.JCheckBox selectorMostrarTodosDestinos;
    private javax.swing.JTable tablaDestinos;
    private javax.swing.JTextField textoApellidosCliente;
    private rojeru_san.RSMTextFull textoBusquedaDestino;
    private javax.swing.JFormattedTextField textoDPICliente;
    private javax.swing.JFormattedTextField textoDPIFacturacion;
    private rojeru_san.RSMTextFull textoDestino;
    private javax.swing.JTextField textoDireccionCliente;
    private javax.swing.JTextField textoNITCliente;
    private javax.swing.JTextField textoNITFacturacion;
    private javax.swing.JTextField textoNombresCliente;
    private javax.swing.JTextField textoPeso;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
