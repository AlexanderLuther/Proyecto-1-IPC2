package Frontend.Administrador;
import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
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
public class PanelPuntoDeControl extends javax.swing.JPanel {
    
    //Variables e instancias de la clase.
    private ManejadorBusqueda manejadorBusqueda;
    private PanelRuta panelRuta;
    private ManejadorDBSM manejadorDB;
    private PuntoDeControl puntoDeControl;
    private List<PuntoDeControl> listadoPuntosDeControl;
    private ObservableList<PuntoDeControl> observableListPuntosDeControl;
    private List<Usuario> listadoUsuarios;
    private ObservableList<Usuario> observableListUsuarios;
    private Ruta ruta;
    private String mensaje;
    private String patronBusqueda;
    private String operadorAsignado;
    private String nombrePuntoDeControl;
    private int cantidadPaquetesCola;
    private double tarifaOperacion;
    private boolean tarifaOperacionPropia;
    private int codigoUltimoPuntoControl;
    
    //Constructor de la clase.
    public PanelPuntoDeControl(PanelRuta panelRuta) {
        this.panelRuta = panelRuta;
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.listadoPuntosDeControl = new ArrayList<>();
        this.observableListPuntosDeControl = ObservableCollections.observableList(listadoPuntosDeControl);
        this.listadoUsuarios = new ArrayList<>();
        this.observableListUsuarios= ObservableCollections.observableList(listadoUsuarios);
        
        initComponents();
    }

    /*
    Metodos utilizados para la implementacion de Beans Beanding.
    */
    public ObservableList<PuntoDeControl> getObservableListPuntosDeControl() {
        return observableListPuntosDeControl;
    }

    public void setObservableListPuntosDeControl(ObservableList<PuntoDeControl> observableListPuntosDeControl) {
        this.observableListPuntosDeControl = observableListPuntosDeControl;
    }
    
    public void llenarTabla(List listado){
        this.observableListPuntosDeControl.clear();
        this.observableListPuntosDeControl.addAll(listado);
    }
    
    public void obtenerPuntosDeControl(String codigoRuta){
        listadoPuntosDeControl = manejadorDB.obtenerListadoPuntosDeControl("SELECT* FROM PuntoDeControl WHERE CodigoRuta = '"+codigoRuta+"' ORDER BY Codigo;");
        this.llenarTabla(listadoPuntosDeControl);
    }
    
    public void establecerInformacionRuta(Ruta ruta){
        this.ruta = ruta;
        etiquetaRutaActual.setText(ruta.getNombre());
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
    Metodo encargado de limpiar la etiqueta de alerta, lanzar un mensaje informativo y
    actualizar los datos de la tabla de puntos de control.
    */
    public void finalizarAccion(String mensaje){
        alertaTabla.setText("");
        this.lanzarMensaje(mensaje);
        this.obtenerPuntosDeControl(ruta.getCodigo());
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
        this.limpiarEtiquetasDeAlerta();
    }
    
     /*
    Metodo encargado de limpiar las etiquetas de alerta
    */
    public void limpiarEtiquetasDeAlerta(){
        etiquetaAlertaPuntoDeControl.setText("");
        etiquetaAlertaTablaOperadores.setText("");
        alertaTabla.setText("");
    }
    
    /*
    Metodo encargado de inicializar y mostrar el JDialog CrearPuntoDeControl
    */
    public void inicializarCreadorModificadorPuntoDeControl(int tipo){
        if(tipo == 0){
            textoNombrePuntoControl.setText("");
            textoCantidadPaquetesCola.setText("");
            textoTarifaOperacion.setText("");
            textoOperadorAsignado.setText("");
            etiquetaAlertaPuntoDeControl.setText("");
            CreardorPuntoDeControl.setTitle("Nuevo Punto de Control");
            botonAceptar.setText("CREAR");
            botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/rutaColor.png")));
        }
        else{
            CreardorPuntoDeControl.setTitle("Modificar Punto de Control");
            botonAceptar.setText("MODIFICAR");
            botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png")));
        }
        CreardorPuntoDeControl.setLocationRelativeTo(this);
        CreardorPuntoDeControl.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        MostradorMensajes = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        CreardorPuntoDeControl = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
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
        jPanel12 = new javax.swing.JPanel();
        botonCancelar = new rojerusan.RSButtonIconI();
        botonAceptar = new rojerusan.RSButtonIconI();
        etiquetaInformacionPuestoControl = new javax.swing.JLabel();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPuntosDeControl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botonAgregarPuntoDeControl = new rojerusan.RSButtonIconI();
        botonModificar = new rojerusan.RSButtonIconI();
        botonEliminar = new rojerusan.RSButtonIconI();
        botonRegresar = new rojerusan.RSButtonIconI();
        alertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        etiquetaRutaActual = new javax.swing.JLabel();

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(660, 200));
        MostradorMensajes.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(51, 153, 0));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
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
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MostradorMensajesLayout.setVerticalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorMensajesLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel9.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel9.setName(""); // NOI18N

        jPanel10.setBackground(new java.awt.Color(51, 153, 0));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/punto-de-control.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaPuntoDeControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                                .addComponent(textoCantidadPaquetesCola, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(etiquetaNombrePuntoControl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textoNombrePuntoControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
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
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombrePuntoControl)
                    .addComponent(jLabel14)
                    .addComponent(textoNombrePuntoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15)
                    .addComponent(textoCantidadPaquetesCola, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonSeleccionarOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoOperadorAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(etiquetaAlertaPuntoDeControl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        botonCancelar.setBackground(new java.awt.Color(51, 153, 0));
        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cancelar.png"))); // NOI18N
        botonCancelar.setText("CANCELAR");
        botonCancelar.setColorHover(new java.awt.Color(153, 153, 153));
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setBackground(new java.awt.Color(51, 153, 0));
        botonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonAceptar.setText("ACEPTAR");
        botonAceptar.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        etiquetaInformacionPuestoControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaInformacionPuestoControl.setForeground(new java.awt.Color(0, 153, 0));
        etiquetaInformacionPuestoControl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaInformacionPuestoControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaInformacionPuestoControl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        CreardorPuntoDeControl.getContentPane().add(jPanel9, gridBagConstraints);

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
        columnBinding.setColumnName("Nombre  De Usuario");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombres}"));
        columnBinding.setColumnName("Nombres");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${apellidos}"));
        columnBinding.setColumnName("Apellidos");
        columnBinding.setColumnClass(String.class);
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
        radioBotonPrimerNombreOperador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerNombreOperador.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerNombreOperador.setText("Primer Nombre");
        radioBotonPrimerNombreOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerNombreOperadorActionPerformed(evt);
            }
        });

        radioBotonPrimerApellidoOperador.setBackground(new java.awt.Color(255, 255, 255));
        radioBotonPrimerApellidoOperador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerApellidoOperador.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerApellidoOperador.setText("Primer Apellido");
        radioBotonPrimerApellidoOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerApellidoOperadorActionPerformed(evt);
            }
        });

        radioBotonNombreOperador.setBackground(new java.awt.Color(255, 255, 255));
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

        setMaximumSize(new java.awt.Dimension(1240, 642));
        setMinimumSize(new java.awt.Dimension(1240, 642));
        setPreferredSize(new java.awt.Dimension(1240, 642));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(1244, 642));
        jPanel1.setMinimumSize(new java.awt.Dimension(1244, 642));

        tablaPuntosDeControl.setBackground(new java.awt.Color(204, 204, 204));
        tablaPuntosDeControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaPuntosDeControl.setForeground(new java.awt.Color(0, 102, 153));
        tablaPuntosDeControl.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPuntosDeControl.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaPuntosDeControl.setSelectionForeground(new java.awt.Color(204, 204, 204));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListPuntosDeControl}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPuntosDeControl);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${operadorAsignado}"));
        columnBinding.setColumnName("Operador Asignado");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cantidadPaquetesCola}"));
        columnBinding.setColumnName("Cantidad Paquetes Cola");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tarifaOperacion}"));
        columnBinding.setColumnName("Tarifa Operacion");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tarifaOperacionPropia}"));
        columnBinding.setColumnName("Tarifa Operacion Propia");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaPuntosDeControl);
        if (tablaPuntosDeControl.getColumnModel().getColumnCount() > 0) {
            tablaPuntosDeControl.getColumnModel().getColumn(0).setPreferredWidth(150);
            tablaPuntosDeControl.getColumnModel().getColumn(1).setPreferredWidth(150);
            tablaPuntosDeControl.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        botonAgregarPuntoDeControl.setBackground(new java.awt.Color(51, 153, 0));
        botonAgregarPuntoDeControl.setForeground(new java.awt.Color(0, 102, 153));
        botonAgregarPuntoDeControl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/rutaColor.png"))); // NOI18N
        botonAgregarPuntoDeControl.setText("Agregar");
        botonAgregarPuntoDeControl.setColorHover(new java.awt.Color(153, 153, 153));
        botonAgregarPuntoDeControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarPuntoDeControlActionPerformed(evt);
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

        botonEliminar.setBackground(new java.awt.Color(51, 153, 0));
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/eliminar.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.setColorHover(new java.awt.Color(153, 153, 153));
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonRegresar.setBackground(new java.awt.Color(51, 153, 0));
        botonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salir.png"))); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.setColorHover(new java.awt.Color(153, 153, 153));
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(botonAgregarPuntoDeControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(botonAgregarPuntoDeControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(167, Short.MAX_VALUE)))
        );

        alertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        alertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        titulo1.setBackground(new java.awt.Color(0, 153, 153));
        titulo1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("RUTA ACTUAL");

        etiquetaRutaActual.setBackground(new java.awt.Color(0, 153, 153));
        etiquetaRutaActual.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        etiquetaRutaActual.setForeground(new java.awt.Color(0, 153, 0));
        etiquetaRutaActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 921, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(etiquetaRutaActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(titulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(titulo1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(etiquetaRutaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alertaTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(alertaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
    /*
    Metodo encargado realizar validaciones, si se recibe un true se llama al metodo inicializarCreadorModificador
    PuntoDeControl enviando como parametro un 0, indicando que se trata de un nuevo punto de control.
    */
    private void botonAgregarPuntoDeControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarPuntoDeControlActionPerformed
        if(manejadorDB.consultarModificacionPuntoDeControl(ruta, puntoDeControl, 0)){
            this.inicializarCreadorModificadorPuntoDeControl(0);
        }
        else{
            alertaTabla.setText("No se puede agregar un nuevo punto de control. Hay paquetes actualmente en la ruta");
        }
    }//GEN-LAST:event_botonAgregarPuntoDeControlActionPerformed
    /*
    Metodo encargado realizar validaciones, si se recibe un true se llama al metodo inicializarCreadorModificador
    PuntoDeControl enviando como parametro un 1, indicando que se trata de una modificacion de un punto de control.
    Ademas se recuperan los datos del punto de control seleccionado y se imprimen en los campos de texto.
    */
    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if(this.tablaPuntosDeControl.getSelectedRow() == -1){
            alertaTabla.setText("Seleccione el punto de control a modificar");
        }
        else{
            puntoDeControl = listadoPuntosDeControl.get(tablaPuntosDeControl.getSelectedRow());
            if(manejadorDB.consultarModificacionPuntoDeControl(ruta, puntoDeControl, 1)){
                textoNombrePuntoControl.setText(puntoDeControl.getNombre());
                textoCantidadPaquetesCola.setText(String.valueOf(puntoDeControl.getCantidadPaquetesCola()));
                textoOperadorAsignado.setText(puntoDeControl.getOperadorAsignado());
                if(puntoDeControl.isTarifaOperacionPropia()){
                    textoTarifaOperacion.setText(String.valueOf(puntoDeControl.getTarifaOperacion()));
                }
                else{
                    textoTarifaOperacion.setText("");
                }
                this.inicializarCreadorModificadorPuntoDeControl(1);
            }
            else{
                alertaTabla.setText("No se puede modificar. Hay paquetes actualmente en punto de control");
            } 
        }
    }//GEN-LAST:event_botonModificarActionPerformed
    /*
    Metodo encargado de eliminar un punto de control. Valida que se tenga seleccionado un punto de control,
    posteriormente llama al metodo consultarModificacionPuntoDeControl, si recibe un booleano con un valor 
    true procede al borrado del punto de control y lanza un mensaje informativo. De lo contrario muestra 
    un mensaje de alerta.
    */ 
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        if(this.tablaPuntosDeControl.getSelectedRow() == -1){
            alertaTabla.setText("Seleccione el punto de control a eliminar");
        }
        else{
            if(manejadorDB.consultarModificacionPuntoDeControl(ruta, puntoDeControl, 0)){
                puntoDeControl = listadoPuntosDeControl.get(tablaPuntosDeControl.getSelectedRow());
                mensaje = manejadorDB.eliminarPuntoDeControl(puntoDeControl);
                this.finalizarAccion(mensaje);
            }
            else{
                alertaTabla.setText("No se puede eliminar un punto de control. Hay paquetes actualmente en la ruta");
            }
        }
    }//GEN-LAST:event_botonEliminarActionPerformed
    /*
    Motodo encargado de ocultar el panel actual y llamar al metodo reestructurarPanelRuta
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        alertaTabla.setText("");
        this.setVisible(false);
        panelRuta.reestructurarPanelRuta();
        panelRuta.limpiarEtiquetasDeAlerta();
    }//GEN-LAST:event_botonRegresarActionPerformed
    /*
    Metodo encargado de cerrar el JDialog de mensajes
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNombrePuntoControlKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombrePuntoControlKeyTyped
        if (textoNombrePuntoControl.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaPuntoDeControl.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombre");
        }
    }//GEN-LAST:event_textoNombrePuntoControlKeyTyped
   /*
    Metodo encargado de inicializar la tabla de operadores y mostrar el JDialog MostrarOperadores
    */
    private void botonSeleccionarOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeleccionarOperadorActionPerformed
        this.limpiarEtiquetasDeAlerta();
        selectorFiltradoOperadores.clearSelection();
        this.selectorMostrarTodosOperadores.setSelected(false);
        textoBusquedaOperador.setText("");
        this.obtenerUsuarios(0);
        MostrarOperadores.setLocationRelativeTo(this);
        MostrarOperadores.setVisible(true);
    }//GEN-LAST:event_botonSeleccionarOperadorActionPerformed
    /*
    Metodo encargado de cerrar el JDialod CreadorPuntoDeControl, ademas limipia las etiquetas de alerta
    */
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        CreardorPuntoDeControl.setVisible(false);
        alertaTabla.setText("");
        etiquetaAlertaPuntoDeControl.setText("");
    }//GEN-LAST:event_botonCancelarActionPerformed
    /*
    Metodo encargado de crear o modificar un punto de control. Valida que todos los campos obligatorios se encuentren
    llenos. Si se trata de un nuevo punto de control, obtiene el codigo del ultimo punto de control registrado para la 
    ruta en turno y establece su atributo ultimoPuntoDeControl en false. Crea una nueva instancia del tipo PuntoDeControl
    y le agrega sus atributos segun la informacion recuperada. Por ultimo llama al metodo crearNUevoPuntoDeControl y lanza 
    un mensaje informativo. Si se trata de una modificacion, recupera la informacion ingresada y llama al metodo modificarPunto
    DeControl, por ultimo lanza un mensaje informativo.
    */    
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        if(textoNombrePuntoControl.getText().isEmpty() || textoCantidadPaquetesCola.getText().isEmpty() || textoOperadorAsignado.getText().isEmpty()){
            etiquetaAlertaPuntoDeControl.setText("Se deben llenar todos los campos obligatorios");
        }
        else{
            try{
                nombrePuntoDeControl = textoNombrePuntoControl.getText();
                cantidadPaquetesCola = Integer.parseInt(textoCantidadPaquetesCola.getText());
                operadorAsignado = textoOperadorAsignado.getText();
                if(!textoTarifaOperacion.getText().equals("")){
                    tarifaOperacion = Double.parseDouble(textoTarifaOperacion.getText());
                    tarifaOperacionPropia = true;
                }
                else{
                    tarifaOperacion = 0.00;
                    tarifaOperacionPropia = false;
                }    
                if(botonAceptar.getText().equals("CREAR")){
                //Se obtiene el codigo del ultimo punto de control y se establece el false el valor de su atributo ultimoPuntoDeControl.
                try{
                    codigoUltimoPuntoControl = listadoPuntosDeControl.get(listadoPuntosDeControl.size()-1).getCodigo();
                    puntoDeControl = listadoPuntosDeControl.get(listadoPuntosDeControl.size()-1);
                    puntoDeControl.setUltimoPuntoDeControl(false);
                    manejadorDB.modificarPuntoDeControl(puntoDeControl);
                }
                catch(ArrayIndexOutOfBoundsException e){
                    codigoUltimoPuntoControl = 0;
                }
                //Crear el nuevo punto de control
                puntoDeControl = new PuntoDeControl(codigoUltimoPuntoControl + 1 , ruta.getCodigo(), nombrePuntoDeControl, tarifaOperacion, cantidadPaquetesCola, operadorAsignado, true, tarifaOperacionPropia);
                System.out.println(manejadorDB.crearNuevoPuntoDeControl(puntoDeControl));
                mensaje = "Punto de control creado exitosamente";
                }    
                else{
                    puntoDeControl = listadoPuntosDeControl.get(tablaPuntosDeControl.getSelectedRow());
                    puntoDeControl.setNombre(nombrePuntoDeControl);
                    puntoDeControl.setTarifaOperacion(tarifaOperacion);
                    puntoDeControl.setOperadorAsignado(operadorAsignado);
                    puntoDeControl.setCantidadPaquetesCola(cantidadPaquetesCola);
                    puntoDeControl.setTarifaOperacionPropia(tarifaOperacionPropia);
                    mensaje = manejadorDB.modificarPuntoDeControl(puntoDeControl);
                }
                this.finalizarAccion(mensaje);
                CreardorPuntoDeControl.dispose();
            }        
            catch(NumberFormatException e){
                etiquetaAlertaPuntoDeControl.setText("Tarifa de operacion no valida");
            } 
        }
    }//GEN-LAST:event_botonAceptarActionPerformed
    /*
    Metodo encargado de obtener el nombre de usuario del operador que se encuente seleccionado. Valida que se 
    encuentre seleccionado un operador de la tabla, posteriomente obtiene el nombre de usuario y lo almacena 
    en la variable operadorAsignado. Imprime en la etiqueta textoOperadorAsignado el valor obtenido y cierra
    el JDialog
    */
    private void aceptarOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarOperadorActionPerformed
        if(tablaOperadores.getSelectedRow() == -1){
            etiquetaAlertaTablaOperadores.setText("Seleccione un operador");
        }
        else{
            operadorAsignado = listadoUsuarios.get(tablaOperadores.getSelectedRow()).getNombreUsuario();
            textoOperadorAsignado.setText(operadorAsignado);
            MostrarOperadores.setVisible(false);
        }
    }//GEN-LAST:event_aceptarOperadorActionPerformed
    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosOperadores.
    */
    private void selectorMostrarTodosOperadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosOperadoresItemStateChanged
        if(selectorMostrarTodosOperadores.isSelected()){
            selectorFiltradoOperadores.clearSelection();
            textoBusquedaOperador.setText("");
            this.obtenerUsuarios(1);
            this.limpiarEtiquetasDeAlerta();
        }
        else{
            this.obtenerUsuarios(0);
            this.limpiarEtiquetasDeAlerta();
        }
    }//GEN-LAST:event_selectorMostrarTodosOperadoresItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusquedaOperador. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusquedaOperadores.
    */
    private void textoBusquedaOperadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaOperadorKeyReleased
        if(!radioBotonPrimerNombreOperador.isSelected() && !radioBotonPrimerApellidoOperador.isSelected() && !radioBotonNombreOperador.isSelected()){
            etiquetaAlertaTablaOperadores.setText("No se ha seleccionado un criterio de filtrado");
            textoBusquedaOperador.setText("");
        }
        else{
            patronBusqueda = textoBusquedaOperador.getText();
            if(patronBusqueda.equals("")){
                this.obtenerUsuarios(0);
                this.limpiarEtiquetasDeAlerta();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CreardorPuntoDeControl;
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog MostrarOperadores;
    private rojerusan.RSButtonIconI aceptarOperador;
    private javax.swing.JLabel alertaTabla;
    private rojerusan.RSButtonIconI botonAceptar;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonAgregarPuntoDeControl;
    private rojerusan.RSButtonIconI botonBuscar1;
    private rojerusan.RSButtonIconI botonCancelar;
    private rojerusan.RSButtonIconI botonEliminar;
    private rojerusan.RSButtonIconI botonModificar;
    private rojerusan.RSButtonIconI botonRegresar;
    private rojerusan.RSButtonIconI botonSeleccionarOperador;
    private javax.swing.JLabel etiquetaAlertaPuntoDeControl;
    private javax.swing.JLabel etiquetaAlertaTablaOperadores;
    private javax.swing.JLabel etiquetaInformacionPuestoControl;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaNombrePuntoControl;
    private javax.swing.JLabel etiquetaRutaActual;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonNombreOperador;
    private javax.swing.JRadioButton radioBotonPrimerApellidoOperador;
    private javax.swing.JRadioButton radioBotonPrimerNombreOperador;
    private javax.swing.ButtonGroup selectorFiltradoOperadores;
    private javax.swing.JCheckBox selectorMostrarTodosOperadores;
    private javax.swing.JTable tablaOperadores;
    private javax.swing.JTable tablaPuntosDeControl;
    private rojeru_san.RSMTextFull textoBusquedaOperador;
    private rojeru_san.RSMTextFull textoCantidadPaquetesCola;
    private javax.swing.JTextField textoNombrePuntoControl;
    private javax.swing.JTextField textoOperadorAsignado;
    private javax.swing.JTextField textoTarifaOperacion;
    private javax.swing.JLabel titulo1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
