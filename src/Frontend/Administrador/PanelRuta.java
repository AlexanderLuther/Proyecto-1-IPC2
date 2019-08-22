package Frontend.Administrador;
import Backend.CambiadorPaneles;
import Backend.ManejadorBusqueda;
import Backend.ManejadorCodigo;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.PuntoDeControl;
import Backend.Ruta;
import Backend.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class PanelRuta extends javax.swing.JPanel {
    
    //Variables e instancias de la clase.
    private ManejadorDBSM manejadorDB;
    private ManejadorBusqueda manejadorBusqueda;
    private ManejadorHilos manejadorHilos;
    private ManejadorCodigo manejadorCodigo;
    private PanelPuntoDeControl panelPuntoDeControl;
    private CambiadorPaneles cambiarPanel;
    private List<Ruta> listadoRutas;
    private ObservableList<Ruta> observableListRutas;
    private List<Usuario> listadoUsuarios;
    private ObservableList<Usuario> observableListUsuarios;
    private Ruta ruta;
    private String nombreRuta;
    private String nombreDestino;
    private String codigoRuta;
    private String codigoActualRuta;
    private PuntoDeControl puntoDeControl;
    private int contadorPuntosDeControl = 1;
    private String nombrePuntoControl;
    private String operadorAsignado;
    private int cantidadPaquetesCola;
    private Double tarifaOperacion;
    private String patronBusqueda;
    private String mensaje;
    
    
    //Constructor de la clase
    public PanelRuta() {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorHilos = new ManejadorHilos();
        this.manejadorCodigo = new ManejadorCodigo();
        this.panelPuntoDeControl = new PanelPuntoDeControl(this);
        this.cambiarPanel = new CambiadorPaneles();
        this.listadoRutas = new ArrayList<>();
        this.observableListRutas = ObservableCollections.observableList(listadoRutas);
        this.listadoUsuarios = new ArrayList<>();
        this.observableListUsuarios= ObservableCollections.observableList(listadoUsuarios);
        initComponents();
    } 
    
    /*
    Metodos utilizados para la implementacion de Beans Beanding en la tabla de Operadores.
    */
    public ObservableList<Usuario> getObservableListUsuarios() {
        return observableListUsuarios;
    }
    
    public void setObservableListUsuarios(ObservableList<Usuario> observableList) {
        this.observableListUsuarios = observableList;
    }
    
    public void llenarTablaUsuarios(List listado){
        this.observableListUsuarios.clear();
        this.observableListUsuarios.addAll(listado);
    }
    
    public void obtenerUsuarios(int tipo){
        listadoUsuarios = manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario WHERE Activo = TRUE && Tipo = 'Operador' ORDER BY NombreUsuario;",tipo);
        this.llenarTablaUsuarios(listadoUsuarios);
    }

    /*
    Metodos utilizados para la implementacion de Beans Beanding en la tabla de rutas
    */
    public ObservableList<Ruta> getObservableListRutas() {
        return observableListRutas;
    }

    public void setObservableListRutas(ObservableList<Ruta> observableList) {
        this.observableListRutas = observableList;
    }
    
    public void llenarTablaRutas(List listado){
        this.observableListRutas.clear();
        this.observableListRutas.addAll(listado);
    }
    
    public void obtenerRutas(int tipo){
        listadoRutas = manejadorDB.obtenerListadoRutas("SELECT* FROM Ruta ORDER BY Codigo;",tipo);
        this.llenarTablaRutas(listadoRutas);
    }
   
    /*
    Metodo encargado inicializar el JDialog CrearModificarRuta en base al parametro entero que recibe.
    Si recibe un 0 se trata de una creacion de una ruta, de lo contrario se trata de una modificacion de 
    una ruta. Por ultimo centra y hace visible el JDialog.
    */
    public void mostrarCreadorModificadorRuta(int tipo){
        if(tipo == 0){
            CrearModificarRuta.setTitle("Nueva Ruta");
            botonCrearModificar.setText("SIGUIENTE");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/Siguiente.png")));
        }
        else{
            CrearModificarRuta.setTitle("Modificar Ruta");
            botonCrearModificar.setText("MODIFICAR");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png")));
            textoDestino.setEditable(false);
        }
        CrearModificarRuta.setLocationRelativeTo(this);
        CrearModificarRuta.setVisible(true);
    }
    
    /*
    Metodo encargado de limpiar las areas de texto y reestablecer a los valores iniciales el 
    JDialog CrearModificarRuta
    */
    public void cerrarCreadorModificadorRuta(){
        CrearModificarRuta.setVisible(false);
        textoDestino.setEditable(true);
        textoNombreRuta.setText("");
        textoDestino.setText("");
    }
    
    /*
    Metodo encargado de limpiar todas las areas de texto y mostrar el JDialog CrearPuntoDeControl
    */
    public void mostrarCreadorPuntoDeControl(){
       textoNombrePuntoControl.setText("");
       textoCantidadPaquetesCola.setText("");
       textoTarifaOperacion.setText("");
       textoOperadorAsignado.setText("");
       CreardorPuntoDeControl.setLocationRelativeTo(this);
       etiquetaNombrePuntoControl.setText("Nombre del Punto de Control " +contadorPuntosDeControl +" :");
       CreardorPuntoDeControl.setVisible(true);
   }
    
    /*
    Metodo encargado de validar el RadioBoton que se encuentra seleccionado y en base a esa validacion establecer
    el metodo de busqueda a realizar para la tabla de operadores.
    */
    public void establecerFiltroDeBusquedaOperadores(){
        selectorMostrarTodosOperadores.setSelected(false);
        if(radioBotonPrimerNombreOperador.isSelected()){    
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorNombres(patronBusqueda, 1);
        }
        if(radioBotonPrimerApellidoOperador.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorApellidos(patronBusqueda, 1);
        }
        if(radioBotonNombreOperador.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaPorNombreUsuario(patronBusqueda, 1);    
        }
        this.llenarTablaUsuarios(listadoUsuarios);
    }
  
    /*
    Metodo encargado de validar el RadioBoton que se encuentra seleccionado y en base a esa validacion establecer
    el metodo de busqueda a realizar para la tabla de rutas.
    */
    public void establecerFiltroDeBusquedaRutas(){
        selectorMostrarTodasRutas.setSelected(false);
        if(radioBotonNombre.isSelected()){    
            listadoRutas = this.manejadorBusqueda.busquedaRutaPorNombre(patronBusqueda);
        }
        if(radioBotonDestino.isSelected()){
            listadoRutas = this.manejadorBusqueda.busquedaRutaPorDestino(patronBusqueda, 0);
        }
        if(radioBotonEstado.isSelected()){
            listadoRutas = this.manejadorBusqueda.busquedaRutaPorEstado(patronBusqueda);    
        }
        this.llenarTablaRutas(listadoRutas);
    }
    
    /*
    Metodo encargado de mostrar en pantalla el mensaje de error que recibe como parametro.
    */
    public void lanzarMensaje(String mensaje){
        etiquetaMensaje.setText(mensaje);
        MostradorMensajes.setLocationRelativeTo(this);
        MostradorMensajes.setVisible(true);
   }
    
    /*
    Metodo encargado de remover la seleccion de todas las casillas de seleccion
    */
    public void inicializarCasillasSeleccion(){
       textoBusquedaRutas.setText("");
       selectorMostrarTodasRutas.setSelected(false);
       selectorFiltrado.clearSelection();
   }

    /*
    Metodo encargado de crear un nuevo punto de control. Recupera las informacion ingresada en los campos de texto y
    la almacena en las variables correspondientes. Posteriormente crea una instancia del objeto PuntoDeControl y llama
    al metodo crearNuevoPuntoDeControl enviando como parametro la instancia creada anteriormente. Por ultimo realiza una
    comparacion, si es el ultimo punto de control establece la variable contadorPuntosDeControl en 1, lanza un mensaje
    informativo y cierra el JDialog, de lo contrario le suma 1 contadorPuntosDeControl, manda a limpiar las etiquetas de 
    alerta, a reestablecer el JDialog y muestra un mensaje informativo en el JDialog.
    */
    public void crearPuntoDeControl(boolean ultimoPuntoDeControl){
        try{
            nombrePuntoControl = textoNombrePuntoControl.getText();
            cantidadPaquetesCola = Integer.parseInt(textoCantidadPaquetesCola.getText());
            operadorAsignado = textoOperadorAsignado.getText();
            if(!textoTarifaOperacion.getText().equals("")){
                tarifaOperacion = Double.parseDouble(textoTarifaOperacion.getText());
                puntoDeControl = new PuntoDeControl(contadorPuntosDeControl, ruta.getCodigo(), nombrePuntoControl, tarifaOperacion, cantidadPaquetesCola, operadorAsignado, ultimoPuntoDeControl, true);
            }
            else{
                tarifaOperacion = 0.00;
                puntoDeControl = new PuntoDeControl(contadorPuntosDeControl, ruta.getCodigo(), nombrePuntoControl, tarifaOperacion, cantidadPaquetesCola, operadorAsignado, ultimoPuntoDeControl, false);
            }
            mensaje = manejadorDB.crearNuevoPuntoDeControl(puntoDeControl);
            if(ultimoPuntoDeControl){
                contadorPuntosDeControl = 1;
                mensaje = "Ruta " + ruta.getNombre() + " creada con " + mensaje;
                this.finalizarAccion();
                CreardorPuntoDeControl.dispose();
            }
            else{
                contadorPuntosDeControl++;
                etiquetaInformacionPuntoControl.setText("Punto de control " + puntoDeControl.getNombre() + " creado exitosamente");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaInformacionPuntoControl);
                this.mostrarCreadorPuntoDeControl();
            }
        } 
        catch(NumberFormatException e){
            etiquetaAlertaPuntoDeControl.setText("Tarifa de operacion no valida");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaPuntoDeControl);
        } 
    }
    
    /*
    Metodo encargado de mostrar un mensaje en pantalla, limpiar las areas de alerta y
    refrescar el listado de usuarios.
    */
    public void finalizarAccion(){
        this.lanzarMensaje(mensaje);
        if(!radioBotonNombre.isSelected() && !radioBotonDestino.isSelected() && !radioBotonEstado.isSelected()){
            if(selectorMostrarTodasRutas.isSelected()){
                this.obtenerRutas(1);
            }
            else{
                this.obtenerRutas(0);
            }   
        }
        else{
            this.establecerFiltroDeBusquedaRutas();
        }
    }
    
    /*
    Metodo encargado de remover el panelPuntoDeControl y hacer visibles todos los componenes del panelRuta
    */
    public void reestructurarPanelRuta(){
        this.remove(panelPuntoDeControl);
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
        CrearModificarRuta = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textoNombreRuta = new javax.swing.JTextField();
        textoDestino = new javax.swing.JTextField();
        etiquetaAlertaRuta = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        botonCancelar = new rojerusan.RSButtonIconI();
        botonCrearModificar = new rojerusan.RSButtonIconI();
        MostradorMensajes = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        CreardorPuntoDeControl = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        etiquetaNombrePuntoControl = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textoTarifaOperacion = new javax.swing.JTextField();
        textoNombrePuntoControl = new javax.swing.JTextField();
        etiquetaAlertaPuntoDeControl = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        textoOperadorAsignado = new javax.swing.JTextField();
        botonSeleccionarOperador = new rojerusan.RSButtonIconI();
        textoCantidadPaquetesCola = new rojeru_san.RSMTextFull();
        jPanel11 = new javax.swing.JPanel();
        botonFinalizar = new rojerusan.RSButtonIconI();
        botonSiguiente = new rojerusan.RSButtonIconI();
        etiquetaInformacionPuntoControl = new javax.swing.JLabel();
        MostrarOperadores = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOperadores = new javax.swing.JTable();
        aceptarOperador = new rojerusan.RSButtonIconI();
        etiquetaAlertaTablaOperadores = new javax.swing.JLabel();
        selectorMostrarTodosOperadores = new javax.swing.JCheckBox();
        textoBusquedaOperador = new rojeru_san.RSMTextFull();
        botonBuscar1 = new rojerusan.RSButtonIconI();
        jLabel13 = new javax.swing.JLabel();
        radioBotonPrimerNombreOperador = new javax.swing.JRadioButton();
        radioBotonPrimerApellidoOperador = new javax.swing.JRadioButton();
        radioBotonNombreOperador = new javax.swing.JRadioButton();
        selectorFiltradoOperadores = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodasRutas = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        botonVerPuntosDeControl = new rojerusan.RSButtonIconI();
        botonModificar = new rojerusan.RSButtonIconI();
        botonDesactivar = new rojerusan.RSButtonIconI();
        botonActivar = new rojerusan.RSButtonIconI();
        botonNuevaRuta = new rojerusan.RSButtonIconI();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonDestino = new javax.swing.JRadioButton();
        radioBotonEstado = new javax.swing.JRadioButton();
        radioBotonNombre = new javax.swing.JRadioButton();
        etiquetaAlertaTablaRutas = new javax.swing.JLabel();
        textoBusquedaRutas = new rojeru_san.RSMTextFull();

        CrearModificarRuta.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CrearModificarRuta.setTitle("Creacion De Usuario Administrador");
        CrearModificarRuta.setMaximumSize(new java.awt.Dimension(701, 400));
        CrearModificarRuta.setMinimumSize(new java.awt.Dimension(701, 400));
        CrearModificarRuta.setModal(true);
        CrearModificarRuta.setPreferredSize(new java.awt.Dimension(701, 400));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel3.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel3.setName(""); // NOI18N

        jPanel4.setBackground(new java.awt.Color(51, 153, 0));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ruta.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/destino.png"))); // NOI18N
        jLabel2.setText("Destino:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/rutaColor.png"))); // NOI18N
        jLabel5.setText("Nombre de la Ruta:");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        textoNombreRuta.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombreRuta.setForeground(new java.awt.Color(0, 102, 153));
        textoNombreRuta.setToolTipText("");
        textoNombreRuta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombreRutaKeyTyped(evt);
            }
        });

        textoDestino.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoDestino.setForeground(new java.awt.Color(0, 102, 153));
        textoDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoDestinoKeyTyped(evt);
            }
        });

        etiquetaAlertaRuta.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaRuta.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaRuta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaRuta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(etiquetaAlertaRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(textoNombreRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(textoNombreRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(etiquetaAlertaRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        botonCancelar.setBackground(new java.awt.Color(51, 153, 0));
        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cancelar.png"))); // NOI18N
        botonCancelar.setText("CANCELAR");
        botonCancelar.setColorHover(new java.awt.Color(153, 153, 153));
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonCrearModificar.setBackground(new java.awt.Color(51, 153, 0));
        botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/Siguiente.png"))); // NOI18N
        botonCrearModificar.setText("CREAR");
        botonCrearModificar.setColorHover(new java.awt.Color(153, 153, 153));
        botonCrearModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonCrearModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrearModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CrearModificarRutaLayout = new javax.swing.GroupLayout(CrearModificarRuta.getContentPane());
        CrearModificarRuta.getContentPane().setLayout(CrearModificarRutaLayout);
        CrearModificarRutaLayout.setHorizontalGroup(
            CrearModificarRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CrearModificarRutaLayout.setVerticalGroup(
            CrearModificarRutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(660, 200));
        MostradorMensajes.setResizable(false);

        jPanel7.setBackground(new java.awt.Color(51, 153, 0));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
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
        etiquetaMensaje.setForeground(new java.awt.Color(51, 153, 0));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botonAceptarMensaje.setBackground(new java.awt.Color(51, 153, 0));
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
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
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

        CreardorPuntoDeControl.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        CreardorPuntoDeControl.setTitle("Creacion De Punto de Control");
        CreardorPuntoDeControl.setMinimumSize(new java.awt.Dimension(730, 570));
        CreardorPuntoDeControl.setModal(true);
        CreardorPuntoDeControl.setResizable(false);
        CreardorPuntoDeControl.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel8.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel8.setName(""); // NOI18N

        jPanel9.setBackground(new java.awt.Color(51, 153, 0));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/punto-de-control.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/caja.png"))); // NOI18N
        jLabel11.setText("Cantidad de Paquetes en cola:");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cuota.png"))); // NOI18N
        jLabel12.setText("Tarifa de Operacion:");

        etiquetaNombrePuntoControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaNombrePuntoControl.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaNombrePuntoControl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombreIdentifiacion.png"))); // NOI18N
        etiquetaNombrePuntoControl.setText("Nombre del Punto de Control:");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        textoTarifaOperacion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoTarifaOperacion.setForeground(new java.awt.Color(0, 102, 153));

        textoNombrePuntoControl.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombrePuntoControl.setForeground(new java.awt.Color(0, 102, 153));
        textoNombrePuntoControl.setToolTipText("");
        textoNombrePuntoControl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombrePuntoControlKeyTyped(evt);
            }
        });

        etiquetaAlertaPuntoDeControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaPuntoDeControl.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaPuntoDeControl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaPuntoDeControl.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setText("Q");

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N
        jLabel18.setText("Operador Asignado:");

        textoOperadorAsignado.setEditable(false);
        textoOperadorAsignado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoOperadorAsignado.setForeground(new java.awt.Color(0, 102, 153));

        botonSeleccionarOperador.setBackground(new java.awt.Color(204, 204, 204));
        botonSeleccionarOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/seleccionar.png"))); // NOI18N
        botonSeleccionarOperador.setBorderPainted(false);
        botonSeleccionarOperador.setColorHover(new java.awt.Color(51, 153, 0));
        botonSeleccionarOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeleccionarOperadorActionPerformed(evt);
            }
        });

        textoCantidadPaquetesCola.setBordeColorFocus(new java.awt.Color(255, 255, 255));
        textoCantidadPaquetesCola.setBotonColor(new java.awt.Color(255, 255, 255));
        textoCantidadPaquetesCola.setMaximumSize(new java.awt.Dimension(12, 31));
        textoCantidadPaquetesCola.setMinimumSize(new java.awt.Dimension(12, 31));
        textoCantidadPaquetesCola.setPlaceholder("");
        textoCantidadPaquetesCola.setPreferredSize(new java.awt.Dimension(12, 31));
        textoCantidadPaquetesCola.setSelectedTextColor(new java.awt.Color(0, 102, 153));
        textoCantidadPaquetesCola.setSoloNumeros(true);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaPuntoDeControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addComponent(textoCantidadPaquetesCola, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(etiquetaNombrePuntoControl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textoNombrePuntoControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoOperadorAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonSeleccionarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombrePuntoControl)
                    .addComponent(jLabel14)
                    .addComponent(textoNombrePuntoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(textoCantidadPaquetesCola, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonSeleccionarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoOperadorAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(etiquetaAlertaPuntoDeControl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        botonFinalizar.setBackground(new java.awt.Color(51, 153, 0));
        botonFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonFinalizar.setText("FINALIZAR");
        botonFinalizar.setColorHover(new java.awt.Color(153, 153, 153));
        botonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFinalizarActionPerformed(evt);
            }
        });

        botonSiguiente.setBackground(new java.awt.Color(51, 153, 0));
        botonSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonSiguiente.setText("SIGUIENTE");
        botonSiguiente.setColorHover(new java.awt.Color(153, 153, 153));
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        etiquetaInformacionPuntoControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaInformacionPuntoControl.setForeground(new java.awt.Color(0, 153, 0));
        etiquetaInformacionPuntoControl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaInformacionPuntoControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaInformacionPuntoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        CreardorPuntoDeControl.getContentPane().add(jPanel8, gridBagConstraints);

        MostrarOperadores.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        MostrarOperadores.setTitle("Seleccione un Operador");
        MostrarOperadores.setBackground(new java.awt.Color(204, 204, 204));
        MostrarOperadores.setMinimumSize(new java.awt.Dimension(955, 620));
        MostrarOperadores.setModal(true);
        MostrarOperadores.setResizable(false);

        tablaOperadores.setSelectionBackground(new java.awt.Color(0, 153, 0));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListUsuarios}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaOperadores);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombreUsuario}"));
        columnBinding.setColumnName("Nombre de Usuario");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombres}"));
        columnBinding.setColumnName("Nombres");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${apellidos}"));
        columnBinding.setColumnName("Apellidos");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(tablaOperadores);

        aceptarOperador.setBackground(new java.awt.Color(51, 153, 0));
        aceptarOperador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        aceptarOperador.setText("ACEPTAR");
        aceptarOperador.setColorHover(new java.awt.Color(153, 153, 153));
        aceptarOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarOperadorActionPerformed(evt);
            }
        });

        etiquetaAlertaTablaOperadores.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTablaOperadores.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaTablaOperadores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        selectorMostrarTodosOperadores.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodosOperadores.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodosOperadores.setText("Mostrar todos los Operadores");
        selectorMostrarTodosOperadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodosOperadoresItemStateChanged(evt);
            }
        });

        textoBusquedaOperador.setForeground(new java.awt.Color(0, 102, 153));
        textoBusquedaOperador.setBordeColorFocus(new java.awt.Color(51, 153, 0));
        textoBusquedaOperador.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusquedaOperador.setPlaceholder("Realizar Busqueda");
        textoBusquedaOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBusquedaOperadorKeyReleased(evt);
            }
        });

        botonBuscar1.setBackground(new java.awt.Color(204, 204, 204));
        botonBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/buscar.png"))); // NOI18N
        botonBuscar1.setBorderPainted(false);
        botonBuscar1.setColorHover(new java.awt.Color(153, 153, 153));
        botonBuscar1.setEnabled(false);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("Filtrado por:");

        radioBotonPrimerNombreOperador.setBackground(new java.awt.Color(255, 255, 255));
        selectorFiltradoOperadores.add(radioBotonPrimerNombreOperador);
        radioBotonPrimerNombreOperador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerNombreOperador.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerNombreOperador.setText("Primer Nombre");
        radioBotonPrimerNombreOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerNombreOperadorActionPerformed(evt);
            }
        });

        radioBotonPrimerApellidoOperador.setBackground(new java.awt.Color(255, 255, 255));
        selectorFiltradoOperadores.add(radioBotonPrimerApellidoOperador);
        radioBotonPrimerApellidoOperador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerApellidoOperador.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerApellidoOperador.setText("Primer Apellido");
        radioBotonPrimerApellidoOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerApellidoOperadorActionPerformed(evt);
            }
        });

        radioBotonNombreOperador.setBackground(new java.awt.Color(255, 255, 255));
        selectorFiltradoOperadores.add(radioBotonNombreOperador);
        radioBotonNombreOperador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonNombreOperador.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonNombreOperador.setText("Nombre De Usuario");
        radioBotonNombreOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonNombreOperadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MostrarOperadoresLayout = new javax.swing.GroupLayout(MostrarOperadores.getContentPane());
        MostrarOperadores.getContentPane().setLayout(MostrarOperadoresLayout);
        MostrarOperadoresLayout.setHorizontalGroup(
            MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                .addGroup(MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaTablaOperadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                        .addGroup(MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectorMostrarTodosOperadores)
                                    .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                                        .addComponent(botonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoBusquedaOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioBotonNombreOperador)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioBotonPrimerNombreOperador)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioBotonPrimerApellidoOperador))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                                .addGap(383, 383, 383)
                                .addComponent(aceptarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addContainerGap())
        );
        MostrarOperadoresLayout.setVerticalGroup(
            MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostrarOperadoresLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MostrarOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioBotonNombreOperador)
                        .addComponent(radioBotonPrimerApellidoOperador)
                        .addComponent(radioBotonPrimerNombreOperador)
                        .addComponent(jLabel13)
                        .addComponent(textoBusquedaOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectorMostrarTodosOperadores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaAlertaTablaOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aceptarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1465, 738));
        setMinimumSize(new java.awt.Dimension(1465, 738));
        setLayout(new java.awt.GridBagLayout());

        tablaRutas.setBackground(new java.awt.Color(204, 204, 204));
        tablaRutas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaRutas.setForeground(new java.awt.Color(0, 102, 153));
        tablaRutas.setGridColor(new java.awt.Color(255, 255, 255));
        tablaRutas.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaRutas.setSelectionForeground(new java.awt.Color(204, 204, 204));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListRutas}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaRutas);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cuotaDestino}"));
        columnBinding.setColumnName("Cuota Destino");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${activa}"));
        columnBinding.setColumnName("Activa");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaRutas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
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
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(botonBuscar, gridBagConstraints);

        selectorMostrarTodasRutas.setBackground(new java.awt.Color(204, 204, 204));
        selectorMostrarTodasRutas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodasRutas.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodasRutas.setText("Mostrar todos los registros");
        selectorMostrarTodasRutas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodasRutasItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(selectorMostrarTodasRutas, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        botonVerPuntosDeControl.setBackground(new java.awt.Color(51, 153, 0));
        botonVerPuntosDeControl.setForeground(new java.awt.Color(0, 102, 153));
        botonVerPuntosDeControl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png"))); // NOI18N
        botonVerPuntosDeControl.setText("Ver Puntos de Control");
        botonVerPuntosDeControl.setColorHover(new java.awt.Color(153, 153, 153));
        botonVerPuntosDeControl.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        botonVerPuntosDeControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerPuntosDeControlActionPerformed(evt);
            }
        });

        botonModificar.setBackground(new java.awt.Color(51, 153, 0));
        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png"))); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.setColorHover(new java.awt.Color(153, 153, 153));
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonDesactivar.setBackground(new java.awt.Color(51, 153, 0));
        botonDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/desactivar.png"))); // NOI18N
        botonDesactivar.setText("Desactivar");
        botonDesactivar.setColorHover(new java.awt.Color(153, 153, 153));
        botonDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDesactivarActionPerformed(evt);
            }
        });

        botonActivar.setBackground(new java.awt.Color(51, 153, 0));
        botonActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonActivar.setText("Activar");
        botonActivar.setColorHover(new java.awt.Color(153, 153, 153));
        botonActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActivarActionPerformed(evt);
            }
        });

        botonNuevaRuta.setBackground(new java.awt.Color(51, 153, 0));
        botonNuevaRuta.setForeground(new java.awt.Color(0, 102, 153));
        botonNuevaRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ruta.png"))); // NOI18N
        botonNuevaRuta.setText("Nueva Ruta");
        botonNuevaRuta.setColorHover(new java.awt.Color(153, 153, 153));
        botonNuevaRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonActivar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDesactivar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonVerPuntosDeControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonNuevaRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(botonNuevaRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(botonActivar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(botonDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonVerPuntosDeControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(153, 6, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Filtrado por:");

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

        radioBotonEstado.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonEstado);
        radioBotonEstado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonEstado.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonEstado.setText("Estado");
        radioBotonEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonEstadoActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(radioBotonNombre)
                .addGap(18, 18, 18)
                .addComponent(radioBotonDestino)
                .addGap(18, 18, 18)
                .addComponent(radioBotonEstado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonDestino)
                    .addComponent(radioBotonNombre)
                    .addComponent(radioBotonEstado))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel2, gridBagConstraints);

        etiquetaAlertaTablaRutas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTablaRutas.setForeground(new java.awt.Color(204, 0, 0));
        etiquetaAlertaTablaRutas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 1202;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 38, 0, 0);
        add(etiquetaAlertaTablaRutas, gridBagConstraints);

        textoBusquedaRutas.setForeground(new java.awt.Color(0, 102, 153));
        textoBusquedaRutas.setBordeColorFocus(new java.awt.Color(51, 153, 0));
        textoBusquedaRutas.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusquedaRutas.setPlaceholder("Realizar Busqueda");
        textoBusquedaRutas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBusquedaRutasKeyReleased(evt);
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
        add(textoBusquedaRutas, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    
    /*
    Metodo encargado de ocultar los elementos que se encuentran actualmente en el PanelRuta. Posteriormente
    agrega al PanelRuta el panel panelPuntoDeControl y lo hace visible.
    */
    private void botonVerPuntosDeControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerPuntosDeControlActionPerformed
        if(this.tablaRutas.getSelectedRow() == -1){
            etiquetaAlertaTablaRutas.setText("Seleccione una ruta");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
        }
        else{
            for(int i = 0; i < this.getComponentCount(); i++){
               this.getComponent(i).setVisible(false);
            }
            this.add(panelPuntoDeControl);
            ruta = listadoRutas.get(this.tablaRutas.getSelectedRow());
            panelPuntoDeControl.obtenerPuntosDeControl(ruta.getCodigo());
            panelPuntoDeControl.establecerInformacionRuta(ruta);
            panelPuntoDeControl.setVisible(true);
        }
    }//GEN-LAST:event_botonVerPuntosDeControlActionPerformed
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto    
    */
    private void textoNombreRutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreRutaKeyTyped
        if (textoNombreRuta.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaRuta.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombre");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaRuta);
        }
    }//GEN-LAST:event_textoNombreRutaKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto    
    */
    private void textoDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDestinoKeyTyped
        if (textoDestino.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaRuta.setText("Solo se permite el ingreso de 30 caracteres en el campo de destino");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaRuta);
        }
    }//GEN-LAST:event_textoDestinoKeyTyped
    /*
    Metodo encargado de cerrar el JDialog CrearModificarRuta.
    */
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.cerrarCreadorModificadorRuta();
    }//GEN-LAST:event_botonCancelarActionPerformed
    /*
    Metodo encargado de la creacion o modificacion de una ruta. Valida que los campos obligatorios se encuentren
    llenos, recupera la informacion ingresada y la asigna a las variables correspondientes, posteriormente realiza 
    validaciones y establece si se crea o se modifica una ruta. Si se crea una nueva ruta se llama al metodo 
    CrearPuntosDeControl. Por ultimo limpia todas las areas de texto y cierra el JDialog
    */
    private void botonCrearModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearModificarActionPerformed
        if(textoNombreRuta.getText().isEmpty() || textoDestino.getText().isEmpty()){
            etiquetaAlertaRuta.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaRuta);
        }
        else{
            try{
                nombreRuta = textoNombreRuta.getText();
                nombreDestino = textoDestino.getText();

                if(botonCrearModificar.getText().equals("SIGUIENTE")){
                    codigoRuta = manejadorDB.obtenerCodigoRuta();
                    codigoRuta = manejadorCodigo.obtenerNuevoCodigo(codigoRuta);
                    ruta = new Ruta(codigoRuta, nombreRuta, nombreDestino, true);
                    manejadorDB.crearNuevaRuta(ruta);
                    manejadorDB.actualizarCodigoRuta(ruta.getCodigo());
                    this.cerrarCreadorModificadorRuta();
                    this.mostrarCreadorPuntoDeControl();     
                } 
                else{  
                    ruta = new Ruta(codigoActualRuta, nombreRuta, nombreDestino, true);
                    mensaje = manejadorDB.modificarRuta(ruta);
                    this.finalizarAccion();
                    this.cerrarCreadorModificadorRuta();
                }
            }
            catch(NumberFormatException e){
                etiquetaAlertaRuta.setText("Cuota de destino no valida");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaRuta);
            }
        }
    }//GEN-LAST:event_botonCrearModificarActionPerformed
    /*
    Metodo encargado de inicializar, llenar los campos de texto y mostrar el JDialog CreadorModificadorRuta mediante el 
    llamado al metodo mostrarCreadorModificadorRuta. Primeramente valida que se encuentra una ruta seleccionada y recupera
    el objeto Ruta de la ruta seleccionada para obtener sus atributos y llenar los campos de texto.
    */
    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if(this.tablaRutas.getSelectedRow() == -1){
            etiquetaAlertaTablaRutas.setText("Seleccione la ruta a modificar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
            
        }
        else{
            ruta = listadoRutas.get(this.tablaRutas.getSelectedRow());
            textoNombreRuta.setText(ruta.getNombre());
            textoDestino.setText(ruta.getDestino());
            codigoActualRuta = ruta.getCodigo();
            this.mostrarCreadorModificadorRuta(1);
        }
    }//GEN-LAST:event_botonModificarActionPerformed
    /*
    Metodo encargado de cerrar el JDialog MostradorMensajes.
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed
    /*
    Metodo encargado de activar una ruta seleccionado. Valida que exista una ruta seleccionada y valida que la 
    ruta no se encuentre activada, si alguna validacion no se cumple se lanza un mensaje de error, de lo contrario
    se llama al metodo modificarRuta para proceder con la activacion.
    */
    private void botonActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActivarActionPerformed
        if(this.tablaRutas.getSelectedRow() == -1){
            etiquetaAlertaTablaRutas.setText("Seleccione la ruta a activar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
        }
        else{
            ruta = listadoRutas.get(this.tablaRutas.getSelectedRow());
            if(ruta.isActiva()){
                etiquetaAlertaTablaRutas.setText("La ruta ya se encuentra activada");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
            }
            else{
                ruta.setActiva(true);
                mensaje = manejadorDB.modificarRuta(ruta);
                this.finalizarAccion();
            }
        }
    }//GEN-LAST:event_botonActivarActionPerformed
   /*
    Metodo encargado de desactivar una ruta seleccionada. Valida que exista una ruta seleccionada y valida que la
    ruta se encuentre activada, si alguna validacion no se cumple se lanza un mensaje de error, de lo contrario
    se llama al metodo modificarRuta para proceder con la desactivacion.
    */
    private void botonDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDesactivarActionPerformed
        if(this.tablaRutas.getSelectedRow() == -1){
            etiquetaAlertaTablaRutas.setText("Seleccione la ruta a desactivar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
        }
         else{
            ruta = listadoRutas.get(this.tablaRutas.getSelectedRow());
            if(!ruta.isActiva()){
                etiquetaAlertaTablaRutas.setText("La ruta ya se encuentra desactivada");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
            }
            else{
                ruta.setActiva(false);
                mensaje = manejadorDB.modificarRuta(ruta);
                if(mensaje.equals("No se puede desactivar. Hay paquetes actualmente en la ruta")){
                    etiquetaAlertaTablaRutas.setText(mensaje);
                    manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
                    ruta.setActiva(true);
                }
                else{
                    this.finalizarAccion();
                }
            }
        }
    }//GEN-LAST:event_botonDesactivarActionPerformed
        /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodasRuta.
    */
    private void selectorMostrarTodasRutasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodasRutasItemStateChanged
        if(selectorMostrarTodasRutas.isSelected()){
            selectorFiltrado.clearSelection();
            textoBusquedaRutas.setText("");
            this.obtenerRutas(1);
        }
        else{
            this.obtenerRutas(0);
        }
    }//GEN-LAST:event_selectorMostrarTodasRutasItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaRutasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaRutasKeyReleased
        if(!radioBotonNombre.isSelected() && !radioBotonDestino.isSelected() && !radioBotonEstado.isSelected()){
            etiquetaAlertaTablaRutas.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaRutas);
            textoBusquedaRutas.setText("");
        }
        else{  
            patronBusqueda = textoBusquedaRutas.getText();
            if(patronBusqueda.equals("")){
                this.obtenerRutas(0);
            }
            else{
                this.establecerFiltroDeBusquedaRutas();
            }
        }
    }//GEN-LAST:event_textoBusquedaRutasKeyReleased
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonNombreActionPerformed
        textoBusquedaRutas.setText("");
    }//GEN-LAST:event_radioBotonNombreActionPerformed
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonDestinoActionPerformed
        textoBusquedaRutas.setText("");
    }//GEN-LAST:event_radioBotonDestinoActionPerformed
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonEstadoActionPerformed
        textoBusquedaRutas.setText("");
    }//GEN-LAST:event_radioBotonEstadoActionPerformed
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto    
    */   
    private void textoNombrePuntoControlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombrePuntoControlKeyTyped
        if (textoNombrePuntoControl.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaPuntoDeControl.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombre");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaPuntoDeControl);
        }
    }//GEN-LAST:event_textoNombrePuntoControlKeyTyped
    /*
    Metodo encargado de crear un nuevo punto de control. Valida que todos los campos obligatorios se encuentren
    llenos. Llama al metodo crearPuntoDeControl enviando un valor booleano TRUE como parametro
    */
    private void botonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFinalizarActionPerformed
        if(textoNombrePuntoControl.getText().isEmpty() || textoCantidadPaquetesCola.getText().isEmpty() || textoOperadorAsignado.getText().isEmpty()){
            etiquetaAlertaPuntoDeControl.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaPuntoDeControl);
        }
        else{
            this.crearPuntoDeControl(true);
        }
    }//GEN-LAST:event_botonFinalizarActionPerformed
    /*
    Metodo encargado de crear un nuevo punto de control. Valida que todos los campos obligatorios se encuentren
    llenos. Llama al metodo crearPuntoDeControl enviando un valor booleano FALSE como parametro
    */
    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        if(textoNombrePuntoControl.getText().isEmpty() || textoCantidadPaquetesCola.getText().isEmpty() || textoOperadorAsignado.getText().isEmpty()){
            etiquetaAlertaPuntoDeControl.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaPuntoDeControl);
        }
         else{
            this.crearPuntoDeControl(false);
        }
    }//GEN-LAST:event_botonSiguienteActionPerformed
     /*
    Metodo encargado de inicializar la tabla de operadores y mostrar el JDialog MostrarOperadores
    */
    private void botonSeleccionarOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeleccionarOperadorActionPerformed
        this.selectorFiltradoOperadores.clearSelection();
        this.selectorMostrarTodosOperadores.setSelected(false);
        textoBusquedaOperador.setText("");
        this.obtenerUsuarios(0);
        MostrarOperadores.setLocationRelativeTo(this);
        MostrarOperadores.setVisible(true);
    }//GEN-LAST:event_botonSeleccionarOperadorActionPerformed
    /*
    Metodo encargado de obtener el nombre de usuario del operador que se encuente seleccionado. Valida que se 
    encuentre seleccionado un operador de la tabla, posteriomente obtiene el nombre de usuario y lo almacena 
    en la variable operadorAsignado. Imprime en la etiqueta textoOperadorAsignado el valor obtenido y cierra
    el JDialog
    */
    private void aceptarOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarOperadorActionPerformed
        if(tablaOperadores.getSelectedRow() == -1){
            etiquetaAlertaTablaOperadores.setText("Seleccione un operador");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaOperadores);
        }
        else{
            operadorAsignado = listadoUsuarios.get(tablaOperadores.getSelectedRow()).getNombreUsuario();
            textoOperadorAsignado.setText(operadorAsignado);
            MostrarOperadores.setVisible(false); 
        }
    }//GEN-LAST:event_aceptarOperadorActionPerformed
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusquedaOperador. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusquedaOperadores.
    */
    private void textoBusquedaOperadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaOperadorKeyReleased
        if(!radioBotonPrimerNombreOperador.isSelected() && !radioBotonPrimerApellidoOperador.isSelected() && !radioBotonNombreOperador.isSelected()){
            etiquetaAlertaTablaOperadores.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTablaOperadores);
            textoBusquedaOperador.setText("");
        }
        else{
            patronBusqueda = textoBusquedaOperador.getText();
            if(patronBusqueda.equals("")){
                this.obtenerUsuarios(0);
            }
            else{
                this.establecerFiltroDeBusquedaOperadores();
            }
        }
    }//GEN-LAST:event_textoBusquedaOperadorKeyReleased
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonPrimerNombreOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerNombreOperadorActionPerformed
        textoBusquedaOperador.setText("");
    }//GEN-LAST:event_radioBotonPrimerNombreOperadorActionPerformed
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonPrimerApellidoOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerApellidoOperadorActionPerformed
        textoBusquedaOperador.setText("");
    }//GEN-LAST:event_radioBotonPrimerApellidoOperadorActionPerformed
    /*
    Metodo encargado de limpiar de area de busqueda
    */
    private void radioBotonNombreOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonNombreOperadorActionPerformed
        textoBusquedaOperador.setText("");
    }//GEN-LAST:event_radioBotonNombreOperadorActionPerformed
    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosOperadores.
    */
    private void selectorMostrarTodosOperadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosOperadoresItemStateChanged
    if(selectorMostrarTodosOperadores.isSelected()){
            selectorFiltradoOperadores.clearSelection();
            textoBusquedaOperador.setText("");
            this.obtenerUsuarios(1);
        }
        else{
            this.obtenerUsuarios(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosOperadoresItemStateChanged
    /*
    Metodo encargado de inicializar y mostrar el JDialog CreadorModificadorUsuario mediante el llamado al metodo mostrarCreadorModificadorUsuario
    */
    private void botonNuevaRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaRutaActionPerformed
        this.mostrarCreadorModificadorRuta(0);
    }//GEN-LAST:event_botonNuevaRutaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CrearModificarRuta;
    private javax.swing.JDialog CreardorPuntoDeControl;
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog MostrarOperadores;
    private rojerusan.RSButtonIconI aceptarOperador;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonActivar;
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonBuscar1;
    private rojerusan.RSButtonIconI botonCancelar;
    private rojerusan.RSButtonIconI botonCrearModificar;
    private rojerusan.RSButtonIconI botonDesactivar;
    private rojerusan.RSButtonIconI botonFinalizar;
    private rojerusan.RSButtonIconI botonModificar;
    private rojerusan.RSButtonIconI botonNuevaRuta;
    private rojerusan.RSButtonIconI botonSeleccionarOperador;
    private rojerusan.RSButtonIconI botonSiguiente;
    private rojerusan.RSButtonIconI botonVerPuntosDeControl;
    private javax.swing.JLabel etiquetaAlertaPuntoDeControl;
    private javax.swing.JLabel etiquetaAlertaRuta;
    private javax.swing.JLabel etiquetaAlertaTablaOperadores;
    private javax.swing.JLabel etiquetaAlertaTablaRutas;
    private javax.swing.JLabel etiquetaInformacionPuntoControl;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaNombrePuntoControl;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JRadioButton radioBotonDestino;
    private javax.swing.JRadioButton radioBotonEstado;
    private javax.swing.JRadioButton radioBotonNombre;
    private javax.swing.JRadioButton radioBotonNombreOperador;
    private javax.swing.JRadioButton radioBotonPrimerApellidoOperador;
    private javax.swing.JRadioButton radioBotonPrimerNombreOperador;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.ButtonGroup selectorFiltradoOperadores;
    private javax.swing.JCheckBox selectorMostrarTodasRutas;
    private javax.swing.JCheckBox selectorMostrarTodosOperadores;
    private javax.swing.JTable tablaOperadores;
    private javax.swing.JTable tablaRutas;
    private rojeru_san.RSMTextFull textoBusquedaOperador;
    private rojeru_san.RSMTextFull textoBusquedaRutas;
    private rojeru_san.RSMTextFull textoCantidadPaquetesCola;
    private javax.swing.JTextField textoDestino;
    private javax.swing.JTextField textoNombrePuntoControl;
    private javax.swing.JTextField textoNombreRuta;
    private javax.swing.JTextField textoOperadorAsignado;
    private javax.swing.JTextField textoTarifaOperacion;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
