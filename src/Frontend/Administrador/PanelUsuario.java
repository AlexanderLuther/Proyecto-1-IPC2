package Frontend.Administrador;


import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author helmuthluther
 */
public class PanelUsuario extends javax.swing.JPanel {
    
    //Constantes de la clase.
    private final String ADMINISTRADOR = "Administrador";
    private final String OPERADOR = "Operador";
    
    //Variables e instancias de la clase.
    private ManejadorDBSM manejadorDB;
    private ManejadorHilos manejadorHilos;
    private ManejadorBusqueda manejadorBusqueda;
    private List<Usuario> listadoUsuarios;
    private ObservableList<Usuario> observableListUsuarios;
    private Usuario usuario;
    private boolean contrasenaOculta;
    private String nombres;
    private String apellidos;
    private String nombreUsuario;
    private String contrasena;
    private String tipo;
    private String mensaje;
    private String patronBusqueda;
   
    //Constructor de la clase
    public PanelUsuario() {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorHilos = new ManejadorHilos();
        this.listadoUsuarios = new ArrayList<>();
        this.observableListUsuarios = ObservableCollections.observableList(listadoUsuarios);
        initComponents();
    }

    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Usuario> getObservableList() {
        return observableListUsuarios;
    }

    public void setObservableList(ObservableList<Usuario> observableList) {
        this.observableListUsuarios = observableList;
    }
    
    public void llenarTabla(List listado){
        this.observableListUsuarios.clear();
        this.observableListUsuarios.addAll(listado);
    }
   
    public void obtenerUsuarios(int tipo){
        if(tipo == 0){
            listadoUsuarios = manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario ORDER BY Nombre LIMIT 45;");
        }
        else{
            listadoUsuarios = manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario ORDER BY Nombre;");
        }
        this.llenarTabla(listadoUsuarios);
    }
    //-------------------------------------------------------------------------------
    
    /*
    Metodo encargado de limpiar las areas de texto y reestablecer a los valores iniciales el 
    JDialog CrearModificarUsuario
    */
    public void limpiarCreadorModificadorUsuario(){
        textoContrasena.setEchoChar('*');
        botonVerContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png")));
        textoNombreUsuario.setEditable(true);
        selectorTipoUsuario.setEnabled(true);
        textoNombres.setText("");
        textoApellidos.setText("");
        textoNombreUsuario.setText("");
        textoContrasena.setText("");
        selectorTipoUsuario.setSelectedIndex(0);
    }
    
    /*
    Metodo encargado inicializar el JDialog CrearModificarUsuario en base al parametro entero que recibe.
    Si recibe un 0 se trata de una creacion de usuario, de lo contrario se trata de una modificacion de 
    usuario. Por ultimo centra y hace visible el JDialog.
    */
    public void mostrarCreadorModificadorUsuario(int tipo){
        contrasenaOculta = true;
        if(tipo == 0){
            CrearModificarUsuario.setTitle("Nuevo Usuario");
            botonCrearModificar.setText("CREAR");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/crear.png")));
        }
        else{
            CrearModificarUsuario.setTitle("Modificar Usuario");
            botonCrearModificar.setText("MODIFICAR");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png")));
            textoNombreUsuario.setEditable(false);
            selectorTipoUsuario.setEnabled(false);
        }
        CrearModificarUsuario.setLocationRelativeTo(this);
        CrearModificarUsuario.setVisible(true);
    }
    
    
    /*
    Metodo encargado de mostrar un mensaje en pantalla, limpiar las areas de alerta y
    refrescar el listado de usuarios.
    */
    public void finalizarAccion(){
        this.lanzarMensaje(mensaje);
        if(!radioBotonPrimerNombre.isSelected() && !radioBotonPrimerApellido.isSelected() && !radioBotonNombreUsuario.isSelected() && !radioBotonTipoUsuario.isSelected() && !radioBotonEstado.isSelected()){
            if(!selectorMostrarTodosLosRegistros.isSelected()){
                this.obtenerUsuarios(0);
            }
            else{
                this.obtenerUsuarios(1);
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
        if(radioBotonPrimerNombre.isSelected()){    
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorNombres(patronBusqueda, 0);
        }
        if(radioBotonPrimerApellido.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorApellidos(patronBusqueda, 0);
        }
        if(radioBotonNombreUsuario.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaPorNombreUsuario(patronBusqueda, 0);    
        }
        if(radioBotonTipoUsuario.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorTipo(patronBusqueda, 0);
        }
        if(radioBotonEstado.isSelected()){
            listadoUsuarios = this.manejadorBusqueda.busquedaUsuarioPorEstado(patronBusqueda, 0);
        }
        this.llenarTabla(listadoUsuarios);
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
        CrearModificarUsuario = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoContrasena = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textoNombreUsuario = new javax.swing.JTextField();
        textoNombres = new javax.swing.JTextField();
        textoApellidos = new javax.swing.JTextField();
        etiquetaAlertaUsuario = new javax.swing.JLabel();
        botonVerContrasena = new rojeru_san.RSButton();
        selectorTipoUsuario = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        botonCancelar = new rojerusan.RSButtonIconI();
        botonCrearModificar = new rojerusan.RSButtonIconI();
        MostradorMensajes = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        botonNuevoUsuario = new rojerusan.RSButtonIconI();
        botonModificar = new rojerusan.RSButtonIconI();
        botonEliminar = new rojerusan.RSButtonIconI();
        botonDesactivar = new rojerusan.RSButtonIconI();
        botonActivar = new rojerusan.RSButtonIconI();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonPrimerApellido = new javax.swing.JRadioButton();
        radioBotonTipoUsuario = new javax.swing.JRadioButton();
        radioBotonEstado = new javax.swing.JRadioButton();
        radioBotonNombreUsuario = new javax.swing.JRadioButton();
        radioBotonPrimerNombre = new javax.swing.JRadioButton();
        etiquetaAlertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textoBusqueda = new rojeru_san.RSMTextFull();

        CrearModificarUsuario.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        CrearModificarUsuario.setTitle("Creacion De Usuario Administrador");
        CrearModificarUsuario.setMinimumSize(new java.awt.Dimension(701, 630));
        CrearModificarUsuario.setModal(true);
        CrearModificarUsuario.setResizable(false);
        CrearModificarUsuario.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel3.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel3.setName(""); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBackground(new java.awt.Color(51, 153, 0));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 625;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel2.setText("Apellidos:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombreUsuario.png"))); // NOI18N
        jLabel3.setText("Nombre de Usuario:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel4.setText("Contraseña:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel5.setText("Nombres:");

        textoContrasena.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoContrasena.setForeground(new java.awt.Color(0, 102, 153));
        textoContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoContrasenaKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        textoNombreUsuario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombreUsuario.setForeground(new java.awt.Color(0, 102, 153));
        textoNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombreUsuarioKeyTyped(evt);
            }
        });

        textoNombres.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombres.setForeground(new java.awt.Color(0, 102, 153));
        textoNombres.setToolTipText("");
        textoNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombresKeyTyped(evt);
            }
        });

        textoApellidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoApellidos.setForeground(new java.awt.Color(0, 102, 153));
        textoApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoApellidosKeyTyped(evt);
            }
        });

        etiquetaAlertaUsuario.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaUsuario.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        botonVerContrasena.setBackground(new java.awt.Color(204, 204, 204));
        botonVerContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png"))); // NOI18N
        botonVerContrasena.setBorderPainted(false);
        botonVerContrasena.setColorHover(new java.awt.Color(153, 153, 153));
        botonVerContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerContrasenaActionPerformed(evt);
            }
        });

        selectorTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador", "Recepcionista" }));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/silueta-de-usuario.png"))); // NOI18N
        jLabel11.setText("Tipo de Usuario:");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNombreUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(selectorTipoUsuario, 0, 304, Short.MAX_VALUE)
                                    .addComponent(textoContrasena))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonVerContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(textoNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(textoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(textoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(textoContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(botonVerContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(selectorTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaAlertaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 50, 0, 0);
        jPanel3.add(jPanel5, gridBagConstraints);

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
        botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/crear.png"))); // NOI18N
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
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrearModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 50, 0, 0);
        jPanel3.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 37;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        CrearModificarUsuario.getContentPane().add(jPanel3, gridBagConstraints);

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(600, 200));
        MostradorMensajes.setModal(true);
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
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
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

        tablaUsuarios.setBackground(new java.awt.Color(204, 204, 204));
        tablaUsuarios.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaUsuarios.setForeground(new java.awt.Color(0, 102, 153));
        tablaUsuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaUsuarios.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaUsuarios);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombres}"));
        columnBinding.setColumnName("NOMBRES");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${apellidos}"));
        columnBinding.setColumnName("APELLIDOS");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombreUsuario}"));
        columnBinding.setColumnName("NOMBRE DE USUARIO");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${contrasena}"));
        columnBinding.setColumnName("CONTRASEÑA");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipo}"));
        columnBinding.setColumnName("TIPO");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${activo}"));
        columnBinding.setColumnName("ACTIVO");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaUsuarios);

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

        botonNuevoUsuario.setBackground(new java.awt.Color(51, 153, 0));
        botonNuevoUsuario.setForeground(new java.awt.Color(0, 102, 153));
        botonNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N
        botonNuevoUsuario.setText("Nuevo Usuario");
        botonNuevoUsuario.setColorHover(new java.awt.Color(153, 153, 153));
        botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoUsuarioActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonActivar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonDesactivar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(botonNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(botonActivar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(botonDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(botonNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(216, Short.MAX_VALUE)))
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

        radioBotonPrimerApellido.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonPrimerApellido);
        radioBotonPrimerApellido.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerApellido.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerApellido.setText("Primer Apellido");
        radioBotonPrimerApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerApellidoActionPerformed(evt);
            }
        });

        radioBotonTipoUsuario.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonTipoUsuario);
        radioBotonTipoUsuario.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonTipoUsuario.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonTipoUsuario.setText("Tipo de Usuario");
        radioBotonTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonTipoUsuarioActionPerformed(evt);
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

        radioBotonNombreUsuario.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonNombreUsuario);
        radioBotonNombreUsuario.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonNombreUsuario.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonNombreUsuario.setText("Nombre De Usuario");
        radioBotonNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonNombreUsuarioActionPerformed(evt);
            }
        });

        radioBotonPrimerNombre.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonPrimerNombre);
        radioBotonPrimerNombre.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonPrimerNombre.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonPrimerNombre.setText("Primer Nombre");
        radioBotonPrimerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonPrimerNombreActionPerformed(evt);
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
                .addComponent(radioBotonPrimerNombre)
                .addGap(18, 18, 18)
                .addComponent(radioBotonPrimerApellido)
                .addGap(18, 18, 18)
                .addComponent(radioBotonNombreUsuario)
                .addGap(18, 18, 18)
                .addComponent(radioBotonTipoUsuario)
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
                    .addComponent(radioBotonPrimerApellido)
                    .addComponent(radioBotonPrimerNombre)
                    .addComponent(radioBotonNombreUsuario)
                    .addComponent(radioBotonTipoUsuario)
                    .addComponent(radioBotonEstado))
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
        textoBusqueda.setBordeColorFocus(new java.awt.Color(51, 153, 0));
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
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 340;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        add(textoBusqueda, gridBagConstraints);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents
   
    /*
    Metodo encargado de inicializar y mostrar el JDialog CreadorModificadorUsuario mediante el llamado al metodo mostrarCreadorModificadorUsuario
    */
    private void botonNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoUsuarioActionPerformed
        this.mostrarCreadorModificadorUsuario(0);
    }//GEN-LAST:event_botonNuevoUsuarioActionPerformed
    
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoContrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoContrasenaKeyTyped
        if (textoContrasena.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaUsuario.setText("Solo se permite el ingreso de 25 caracteres en el campo de contraseña");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
        }
    }//GEN-LAST:event_textoContrasenaKeyTyped
   
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoNombreUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreUsuarioKeyTyped
        if (textoNombreUsuario.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaUsuario.setText("Solo se permite el ingreso de 25 caracteres en el campo de nombre de usuario");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
        }
    }//GEN-LAST:event_textoNombreUsuarioKeyTyped
    
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombresKeyTyped
        if (textoNombres.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaUsuario.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombres");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
        }
    }//GEN-LAST:event_textoNombresKeyTyped
    
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoApellidosKeyTyped
        if (textoApellidos.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaUsuario.setText("Solo se permite el ingreso de 30 caracteres en el campo de apellidos");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
        }
    }//GEN-LAST:event_textoApellidosKeyTyped

    /*
    Metodo encargado de mostrar y ocultar la contrasena. Modifica la imagen del boton correspondiente y cambia
    el valor de la variable booleana contraenaOculta en base a una comparacion que detecta si la contrasena 
    actualmente se encuentra oculta o no.
    */
    private void botonVerContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerContrasenaActionPerformed
        if(contrasenaOculta){
            botonVerContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ocultar.png")));
            textoContrasena.setEchoChar((char)0);
            contrasenaOculta = false;
        }
        else{
            botonVerContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png")));
            textoContrasena.setEchoChar('*');
            contrasenaOculta = true;
        }
    }//GEN-LAST:event_botonVerContrasenaActionPerformed

    //Metodo encargado de cerrar el JDialog CrearModificarUsuario.
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        CrearModificarUsuario.dispose();
        this.limpiarCreadorModificadorUsuario();
    }//GEN-LAST:event_botonCancelarActionPerformed

    /*
    Metodo encargado de la creacion o modificacion de un usuario. Valida que los campos obligatorios se encuentren
    llenos, recupera la informacion ingresada y la asigna a las variables correspondientes, posteriormente realiza 
    validaciones y establece si se crea o se modifica un usuario. Por ultimo limpia todas las areas de texto y 
    cierra el JDialog
    */
    private void botonCrearModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearModificarActionPerformed
        if(textoNombres.getText().isEmpty() || textoApellidos.getText().isEmpty() || textoNombreUsuario.getText().isEmpty() || textoContrasena.getText().isEmpty()){
            etiquetaAlertaUsuario.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
        }
        else{
            nombres = textoNombres.getText();
            apellidos = textoApellidos.getText();
            nombreUsuario = textoNombreUsuario.getText();
            contrasena = textoContrasena.getText();
            tipo = selectorTipoUsuario.getSelectedItem().toString();
            usuario = new Usuario(nombres, apellidos, nombreUsuario, contrasena, tipo, true);
            if(botonCrearModificar.getText().equals("CREAR")){
                mensaje = manejadorDB.crearNuevoUsuario(usuario); 
            } 
            else{
                mensaje = manejadorDB.modificarUsuario(usuario);
            }
            if(mensaje.equals("El nombre de usuario ya se encuentra registrado en el sistema")){
                    etiquetaAlertaUsuario.setText(mensaje);
                    manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaUsuario);
            }
            else{
                this.finalizarAccion();
                this.limpiarCreadorModificadorUsuario();
                CrearModificarUsuario.setVisible(false);
            }
        }
    }//GEN-LAST:event_botonCrearModificarActionPerformed

    /*
    Metodo encargado de inicializar, llenar los campos de texto y mostrar el JDialog CreadorModificadorUsuario mediante el 
    llamado al metodo mostrarCreadorModificadorUsuario. Primeramente valida que se encuentra un usuario seleccionado y recupera
    el objeto Usuario del usuario seleccionado para obtener sus atributos y llenar los campos de texto.
    */
    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if(this.tablaUsuarios.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione el usuario a modificar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
        else{
            usuario = listadoUsuarios.get(this.tablaUsuarios.getSelectedRow());
            textoNombres.setText(usuario.getNombres());
            textoApellidos.setText(usuario.getApellidos());
            textoNombreUsuario.setText(usuario.getNombreUsuario());
            textoContrasena.setText(usuario.getContrasena());
            switch (usuario.getTipo()){
                case ADMINISTRADOR:
                    selectorTipoUsuario.setSelectedIndex(0);
                break;
                case OPERADOR:
                    selectorTipoUsuario.setSelectedIndex(1);
                break;
                default:
                    selectorTipoUsuario.setSelectedIndex(2);
                break;
            }
            this.mostrarCreadorModificadorUsuario(1);
        }
    }//GEN-LAST:event_botonModificarActionPerformed
    /*
    Metodo encargado de cerrar el JDialog MostradorMensajes.
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed
    /*
    Metodo encargado de activar un usuario seleccionado. Valida que exista un usuario seleccionado y valida que el 
    usuario no se encuentre activado, si alguna validacion no se cumple se lanza un mensaje de error, de lo contrario
    se llama al metodo modificarUsuario para proceder con la activacion.
    */
    private void botonActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActivarActionPerformed
        if(this.tablaUsuarios.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione el usuario a activar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
        else{
            usuario = listadoUsuarios.get(this.tablaUsuarios.getSelectedRow());
            if(usuario.isActivo()){
                etiquetaAlertaTabla.setText("El usuario ya se encuentra activado");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
            }
            else{
                usuario.setActivo(true);
                mensaje = manejadorDB.modificarUsuario(usuario);
                this.finalizarAccion();
            }
        }
    }//GEN-LAST:event_botonActivarActionPerformed
    /*
    Metodo encargado de eliminar un usuario seleccionado. Valida que exista un usuario seleccionado si la
    validacion no se cumple se lanza un mensaje de error, de lo contrario se llama al metodo eliminarUsuario
    para proceder con la eliminacion.
    */
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
         if(this.tablaUsuarios.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione el usuario a eliminar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
        else{
             usuario = listadoUsuarios.get(this.tablaUsuarios.getSelectedRow());
             mensaje = manejadorDB.eliminarUsuario(usuario);
             this.finalizarAccion();
        }
    }//GEN-LAST:event_botonEliminarActionPerformed
    /*
    Metodo encargado de desactivar un usuario seleccionado. Valida que exista un usuario seleccionado y valida que el 
    usuario se encuentre activado, si alguna validacion no se cumple se lanza un mensaje de error, de lo contrario
    se llama al metodo modificarUsuario para proceder con la desactivacion.
    */
    private void botonDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDesactivarActionPerformed
        if(this.tablaUsuarios.getSelectedRow() == -1){
            etiquetaAlertaTabla.setText("Seleccione el usuario a desactivar");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
        }
         else{
            usuario = listadoUsuarios.get(this.tablaUsuarios.getSelectedRow());
            if(!usuario.isActivo()){
                etiquetaAlertaTabla.setText("El usuario ya se encuentra desactivado");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
            }
            else{
                usuario.setActivo(false);
                mensaje = manejadorDB.modificarUsuario(usuario);
                this.finalizarAccion();
            }
        }
    }//GEN-LAST:event_botonDesactivarActionPerformed

    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosLosRegistros.
    */
    private void selectorMostrarTodosLosRegistrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosLosRegistrosItemStateChanged
        if(selectorMostrarTodosLosRegistros.isSelected()){
            selectorFiltrado.clearSelection();
            textoBusqueda.setText("");
            this.obtenerUsuarios(1);
        }
        else{
            this.obtenerUsuarios(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaKeyReleased
        if(!radioBotonPrimerNombre.isSelected() && !radioBotonPrimerApellido.isSelected() && !radioBotonNombreUsuario.isSelected() && !radioBotonTipoUsuario.isSelected() && !radioBotonEstado.isSelected()){
            etiquetaAlertaTabla.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTabla);
            textoBusqueda.setText("");
        }
        else{  
            patronBusqueda = textoBusqueda.getText();
            if(patronBusqueda.equals("")){
                this.obtenerUsuarios(0);
            }
            else{
                this.establecerFiltroDeBusqueda();
            }
        }
    }//GEN-LAST:event_textoBusquedaKeyReleased
    
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonPrimerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerNombreActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonPrimerNombreActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonPrimerApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerApellidoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonPrimerApellidoActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonNombreUsuarioActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonNombreUsuarioActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonTipoUsuarioActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonTipoUsuarioActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonEstadoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonEstadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CrearModificarUsuario;
    private javax.swing.JDialog MostradorMensajes;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonActivar;
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonCancelar;
    private rojerusan.RSButtonIconI botonCrearModificar;
    private rojerusan.RSButtonIconI botonDesactivar;
    private rojerusan.RSButtonIconI botonEliminar;
    private rojerusan.RSButtonIconI botonModificar;
    private rojerusan.RSButtonIconI botonNuevoUsuario;
    private rojeru_san.RSButton botonVerContrasena;
    private javax.swing.JLabel etiquetaAlertaTabla;
    private javax.swing.JLabel etiquetaAlertaUsuario;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonEstado;
    private javax.swing.JRadioButton radioBotonNombreUsuario;
    private javax.swing.JRadioButton radioBotonPrimerApellido;
    private javax.swing.JRadioButton radioBotonPrimerNombre;
    private javax.swing.JRadioButton radioBotonTipoUsuario;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JComboBox<String> selectorTipoUsuario;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField textoApellidos;
    private rojeru_san.RSMTextFull textoBusqueda;
    private javax.swing.JPasswordField textoContrasena;
    private javax.swing.JTextField textoNombreUsuario;
    private javax.swing.JTextField textoNombres;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
