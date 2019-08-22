package Frontend.Recepcionista;
import Backend.Cliente;
import Backend.ManejadorBusqueda;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class PanelCliente extends javax.swing.JPanel {
    
   
    //Variables e instancias de la clase.
    private ManejadorDBSM manejadorDB;
    private ManejadorBusqueda manejadorBusqueda;
    private ManejadorHilos manejadorHilos;
    private List<Cliente> listadoClientes;
    private ObservableList<Cliente> observableListClientes;
    private Cliente cliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String DPI;
    private String NIT;
    private String mensaje;
    private String patronBusqueda;
   
    //Constructor de la clase
    public PanelCliente() {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda();
        this.manejadorHilos = new ManejadorHilos();
        this.listadoClientes = new ArrayList<>();
        this.observableListClientes = ObservableCollections.observableList(listadoClientes);
        initComponents();
    }

    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Cliente> getObservableList() {
        return observableListClientes;
    }

    public void setObservableList(ObservableList<Cliente> observableList) {
        this.observableListClientes = observableList;
    }
    
    public void llenarTabla(List listado){
        this.observableListClientes.clear();
        this.observableListClientes.addAll(listado);
    }
    
    public void obtenerClientes(int tipo){
        listadoClientes = manejadorDB.obtenerListadoClientes("SELECT* FROM Cliente ORDER BY DPI;",tipo);
        this.llenarTabla(listadoClientes);
    }
    //-------------------------------------------------------------------------------
    
    /*
    Metodo encargado de limpiar las areas de texto y reestablecer a los valores iniciales el 
    JDialog CrearModificarCliente
    */
    public void limpiarCreadorModificadorCliente(){
        textoNombres.setText("");
        textoApellidos.setText("");
        textoNIT.setText("");
        textoDPI.setText("");
        textoCiudad.setText("");
    }
    
    /*
    Metodo encargado inicializar el JDialog CrearModificarCliente en base al parametro entero que recibe.
    Si recibe un 0 se trata de una creacion de usuario, de lo contrario se trata de una modificacion de 
    usuario. Por ultimo centra y hace visible el JDialog.
    */
    public void mostrarCreadorModificadorCliente(int tipo){
        if(tipo == 0){
            textoDPI.setEditable(true);
            CrearModificarCliente.setTitle("Nuevo Cliente");
            botonCrearModificar.setText("CREAR");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/crear.png")));
        }
        else{
            textoDPI.setEditable(false);
            CrearModificarCliente.setTitle("Modificar Cliente");
            botonCrearModificar.setText("MODIFICAR");
            botonCrearModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png")));
        }
        CrearModificarCliente.setLocationRelativeTo(this);
        CrearModificarCliente.setVisible(true);
    }
        
    /*
    Metodo encargado de mostrar un mensaje en pantalla, limpiar las areas de alerta y
    refrescar el listado de usuarios.
    */
    public void finalizarAccion(){
        this.lanzarMensaje(mensaje);
        if(!radioBotonPrimerNombre.isSelected() && !radioBotonPrimerApellido.isSelected() && !radioBotonCiudad.isSelected() && !radioBotonNIT.isSelected() && !radioBotonDPI.isSelected()){
            if(!selectorMostrarTodosLosRegistros.isSelected()){
                this.obtenerClientes(0);
            }
            else{
                this.obtenerClientes(1);
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
            listadoClientes = this.manejadorBusqueda.busquedaClientePorNombres(patronBusqueda);
        }
        if(radioBotonPrimerApellido.isSelected()){
            listadoClientes = this.manejadorBusqueda.busquedaClientePorApellidos(patronBusqueda);
        }
        if(radioBotonCiudad.isSelected()){
            listadoClientes = this.manejadorBusqueda.busquedaClientePorCiudad(patronBusqueda);  
        }
        if(radioBotonNIT.isSelected()){
            listadoClientes = this.manejadorBusqueda.busquedaClientePorNIT(patronBusqueda);
        }
        if(radioBotonDPI.isSelected()){
            listadoClientes = this.manejadorBusqueda.busquedaClientePorDPI(patronBusqueda);
        }
        this.llenarTabla(listadoClientes);
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
        CrearModificarCliente = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textoNIT = new javax.swing.JTextField();
        textoNombres = new javax.swing.JTextField();
        textoApellidos = new javax.swing.JTextField();
        etiquetaAlertaCliente = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textoDPI = new javax.swing.JFormattedTextField();
        textoCiudad = new javax.swing.JTextField();
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
        tablaClientes = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        botonNuevoUsuario = new rojerusan.RSButtonIconI();
        botonModificar = new rojerusan.RSButtonIconI();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonPrimerApellido = new javax.swing.JRadioButton();
        radioBotonNIT = new javax.swing.JRadioButton();
        radioBotonDPI = new javax.swing.JRadioButton();
        radioBotonCiudad = new javax.swing.JRadioButton();
        radioBotonPrimerNombre = new javax.swing.JRadioButton();
        alertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textoBusqueda = new rojeru_san.RSMTextFull();

        CrearModificarCliente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        CrearModificarCliente.setTitle("Creacion De Usuario Administrador");
        CrearModificarCliente.setMinimumSize(new java.awt.Dimension(700, 620));
        CrearModificarCliente.setModal(true);
        CrearModificarCliente.setResizable(false);
        CrearModificarCliente.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(682, 593));
        jPanel3.setMinimumSize(new java.awt.Dimension(682, 593));
        jPanel3.setName(""); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N

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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel2.setText("Apellidos:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/destino.png"))); // NOI18N
        jLabel3.setText("Direccion:");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel4.setText("DPI:");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombre.png"))); // NOI18N
        jLabel5.setText("Nombres:");

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

        textoNIT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNIT.setForeground(new java.awt.Color(0, 102, 153));
        textoNIT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNITKeyTyped(evt);
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

        etiquetaAlertaCliente.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaCliente.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel11.setText("NIT:");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        textoDPI.setForeground(new java.awt.Color(0, 102, 153));
        try {
            textoDPI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoDPI.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N

        textoCiudad.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoCiudad.setForeground(new java.awt.Color(0, 102, 153));
        textoCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoCiudadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(textoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoCiudad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoDPI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNIT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                    .addComponent(textoCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(textoDPI, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiquetaAlertaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(textoNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        botonCancelar.setBackground(new java.awt.Color(0, 153, 255));
        botonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cancelar.png"))); // NOI18N
        botonCancelar.setText("CANCELAR");
        botonCancelar.setColorHover(new java.awt.Color(153, 153, 153));
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonCrearModificar.setBackground(new java.awt.Color(0, 153, 255));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        CrearModificarCliente.getContentPane().add(jPanel3, gridBagConstraints);

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

        tablaClientes.setBackground(new java.awt.Color(204, 204, 204));
        tablaClientes.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaClientes.setForeground(new java.awt.Color(0, 102, 153));
        tablaClientes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaClientes.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tablaClientes.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaClientes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${DPI}"));
        columnBinding.setColumnName("DPI");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${NIT}"));
        columnBinding.setColumnName("NIT");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${apellido}"));
        columnBinding.setColumnName("Apellido");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${direccion}"));
        columnBinding.setColumnName("Direccion");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tablaClientes);

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

        botonNuevoUsuario.setBackground(new java.awt.Color(0, 153, 255));
        botonNuevoUsuario.setForeground(new java.awt.Color(0, 102, 153));
        botonNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N
        botonNuevoUsuario.setText("Nuevo");
        botonNuevoUsuario.setColorHover(new java.awt.Color(153, 153, 153));
        botonNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoUsuarioActionPerformed(evt);
            }
        });

        botonModificar.setBackground(new java.awt.Color(0, 153, 255));
        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/editar.png"))); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.setColorHover(new java.awt.Color(153, 153, 153));
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(botonNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(botonNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
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

        radioBotonNIT.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonNIT);
        radioBotonNIT.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonNIT.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonNIT.setText("NIT");
        radioBotonNIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonNITActionPerformed(evt);
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

        radioBotonCiudad.setBackground(new java.awt.Color(204, 204, 204));
        selectorFiltrado.add(radioBotonCiudad);
        radioBotonCiudad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        radioBotonCiudad.setForeground(new java.awt.Color(0, 102, 153));
        radioBotonCiudad.setText("Ciudad");
        radioBotonCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotonCiudadActionPerformed(evt);
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
                .addComponent(radioBotonCiudad)
                .addGap(18, 18, 18)
                .addComponent(radioBotonNIT)
                .addGap(18, 18, 18)
                .addComponent(radioBotonDPI)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonPrimerApellido)
                    .addComponent(radioBotonPrimerNombre)
                    .addComponent(radioBotonCiudad)
                    .addComponent(radioBotonNIT)
                    .addComponent(radioBotonDPI))
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

        alertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        alertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 788;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        add(alertaTabla, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jSeparator1, gridBagConstraints);

        textoBusqueda.setForeground(new java.awt.Color(0, 102, 153));
        textoBusqueda.setBordeColorFocus(new java.awt.Color(0, 153, 255));
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
        this.mostrarCreadorModificadorCliente(0);
    }//GEN-LAST:event_botonNuevoUsuarioActionPerformed
    
  
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoNITKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNITKeyTyped
        if (textoNIT.getText().length() == 9) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 9 caracteres en el campo de NIT");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoNITKeyTyped
    
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombresKeyTyped
        if (textoNombres.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombres");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoNombresKeyTyped
    
    //Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    private void textoApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoApellidosKeyTyped
        if (textoApellidos.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 30 caracteres en el campo de apellidos");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoApellidosKeyTyped

    //Metodo encargado de cerrar el JDialog CrearModificarCliente
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        CrearModificarCliente.dispose();
        this.limpiarCreadorModificadorCliente();
    }//GEN-LAST:event_botonCancelarActionPerformed

    /*
    Metodo encargado de la creacion o modificacion de un cliente. Valida que los campos obligatorios se encuentren
    llenos, recupera la informacion ingresada y la asigna a las variables correspondientes, posteriormente realiza 
    validaciones y establece si se crea o se modifica un usuario. Por ultimo limpia todas las areas de texto y 
    cierra el JDialog
    */
    private void botonCrearModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearModificarActionPerformed
        if(textoNombres.getText().isEmpty() || textoApellidos.getText().isEmpty() || textoNIT.getText().isEmpty() || textoDPI.getText().equals("    -     -    ") || textoNIT.getText().isEmpty()){
            etiquetaAlertaCliente.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
        else{
            nombres = textoNombres.getText();
            apellidos = textoApellidos.getText();
            direccion = textoCiudad.getText();
            DPI = textoDPI.getText();
            NIT = textoNIT.getText();
            cliente = new Cliente(nombres, apellidos, direccion, DPI, NIT);
            if(botonCrearModificar.getText().equals("CREAR")){
                mensaje = manejadorDB.crearNuevoCliente(cliente); 
            } 
            else{
                mensaje = manejadorDB.modificarCliente(cliente);
            }
            this.finalizarAccion();
            this.limpiarCreadorModificadorCliente();
            CrearModificarCliente.setVisible(false);
        }
    }//GEN-LAST:event_botonCrearModificarActionPerformed

    /*
    Metodo encargado de inicializar, llenar los campos de texto y mostrar el JDialog CreadorModificadorCliente mediante el 
    llamado al metodo mostrarCreadorModificadorCliente. Primeramente valida que se encuentra un usuario seleccionado y recupera
    el objeto Cliente del cliente seleccionado para obtener sus atributos y llenar los campos de texto.
    */
    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        if(this.tablaClientes.getSelectedRow() == -1){
            alertaTabla.setText("Seleccione el cliente a modificar");
            manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
        }
        else{
            cliente = listadoClientes.get(this.tablaClientes.getSelectedRow());
            textoNombres.setText(cliente.getNombre());
            textoApellidos.setText(cliente.getApellido());
            textoCiudad.setText(cliente.getDireccion());
            textoDPI.setText(cliente.getDPI());
            textoNIT.setText(cliente.getNIT());
            this.mostrarCreadorModificadorCliente(1);
        }
    }//GEN-LAST:event_botonModificarActionPerformed
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
            this.obtenerClientes(1);
        }
        else{
            this.obtenerClientes(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaKeyReleased
        if(!radioBotonPrimerNombre.isSelected() && !radioBotonPrimerApellido.isSelected() && !radioBotonCiudad.isSelected() && !radioBotonNIT.isSelected() && !radioBotonDPI.isSelected()){
            alertaTabla.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
            textoBusqueda.setText("");
        }
        else{  
            patronBusqueda = textoBusqueda.getText();
            if(patronBusqueda.equals("")){
                this.obtenerClientes(0);
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
    private void radioBotonCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonCiudadActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonCiudadActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonNITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonNITActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonNITActionPerformed

    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonDPIActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonDPIActionPerformed

    private void textoCiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoCiudadKeyTyped
         if (textoCiudad.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaCliente.setText("Solo se permite el ingreso de 25 caracteres en el campo de direccion");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCliente);
        }
    }//GEN-LAST:event_textoCiudadKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CrearModificarCliente;
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JLabel alertaTabla;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonCancelar;
    private rojerusan.RSButtonIconI botonCrearModificar;
    private rojerusan.RSButtonIconI botonModificar;
    private rojerusan.RSButtonIconI botonNuevoUsuario;
    private javax.swing.JLabel etiquetaAlertaCliente;
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
    private javax.swing.JRadioButton radioBotonCiudad;
    private javax.swing.JRadioButton radioBotonDPI;
    private javax.swing.JRadioButton radioBotonNIT;
    private javax.swing.JRadioButton radioBotonPrimerApellido;
    private javax.swing.JRadioButton radioBotonPrimerNombre;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField textoApellidos;
    private rojeru_san.RSMTextFull textoBusqueda;
    private javax.swing.JTextField textoCiudad;
    private javax.swing.JFormattedTextField textoDPI;
    private javax.swing.JTextField textoNIT;
    private javax.swing.JTextField textoNombres;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
