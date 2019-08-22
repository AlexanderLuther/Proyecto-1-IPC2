package Frontend;
import Frontend.Recepcionista.PanelRecepcionista;
import Frontend.Administrador.PanelAdministrador;
import Backend.CambiadorPaneles;
import Backend.ManejadorBodega;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.Tarifa;
import Backend.Usuario;
/**
 *
 * @author helmuthluther
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    //Constantes de la clase
    private final String ADMINISTRADOR = "Administrador";
    private final String OPERADOR = "Operador";
    private final String RECEPCIONISTA = "Recepcionista";
    
    //Variables e instancias utilizadas por la clase
    private Usuario usuario;
    private Tarifa tarifa;
    private CambiadorPaneles cambiarPanel;
    private ManejadorDBSM manejadorDB;
    private ManejadorHilos manejadorHilos;
    private PanelAdministrador panelAdministrador;
    private PanelRecepcionista panelRecepcionista;
    private PanelOperador panelOperador;

    private String nombres;
    private String apellidos;
    private String nombreUsuario;
    private String contrasena;
    private boolean contrasenaOculta;
    private String mensaje;
    private String tipoUsuarioActual;
    private double tarifaOperacion;
    private double precioPorLibra;
    private double cuotaPriorizacion;
    private double cuotaDestino;
    private ManejadorBodega manejadorBodega;
    
    public VentanaPrincipal() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorHilos = new  ManejadorHilos();
        this.cambiarPanel = new CambiadorPaneles();
        this.panelAdministrador = new PanelAdministrador(this);
        this.panelOperador = new PanelOperador();
        this.panelRecepcionista = new PanelRecepcionista(this);
        initComponents();
    }
   
   /*
   Metodo encargado de mostrar un JDialog que controla el inicio de sesion.
   */
   public void iniciarSesion(int tipo){
       contrasenaOculta = true;
       if(tipo == 0){
           etiquetaInformacionLogin.setText("Bienvenido al sistema");      
       }
       else{
           etiquetaInformacionLogin.setText("Cambio de Usuario");
           textoNombreUsuarioLogin.setText("");
           textoContrasenaLogin.setText("");
           botonSalirLogin.setText("Cancelar");
       }
       IniciarSesion.setLocationRelativeTo(this);
       IniciarSesion.setVisible(true);
       this.setVisible(true);
       
   }
   
   /*
   Metodo encargado de mostrar un JDialog correspondiente a la creacion de un usuario Administrador durante el primer inicio del sistema.
   */
   public void crearPrimerUsuarioAdministrador(){
       contrasenaOculta = true;
       etiquetaInformacionCreacionUsuarioAdmin.setText("Bienvenido al sistema, se necesita la creacion de un usuario Administrador");
       CreadorUsuarioAdministrador.setLocationRelativeTo(this);
       CreadorUsuarioAdministrador.setVisible(true);
       this.setVisible(true);
       etiquetaInformacionTarifa.setText("<html> <center> Si selecciona cancelar todas las tarifas seran establecidas en Q0.00"
                                        + "<br> Puede modificar las tarifas ingresadas en cualquier momento </center> </html>");
       this.ReceptorTarifas.setLocationRelativeTo(this);
       this.ReceptorTarifas.setVisible(true);
       
   }
   
    /*
   Metodo encargado de mostrar en pantalla el mensaje de error que recibe como parametro. 
   */
   public void lanzarMensaje(String mensaje){
        etiquetaMensaje.setText(mensaje);
        MostradorMensajes.setLocationRelativeTo(null);
        MostradorMensajes.setVisible(true);
   }
   
   public void mostrarAreaTrabajo(){
        switch (tipoUsuarioActual) {
            case ADMINISTRADOR:
                panelAdministrador.setManejadorBodega(manejadorBodega);
                cambiarPanel.cambiarPanel(panelPrincipal, panelAdministrador);
                panelAdministrador.establecerFondo();
                panelAdministrador.estableceUsuario(nombreUsuario);
            break;
            case RECEPCIONISTA:
                panelRecepcionista.setManejadorBodega(manejadorBodega);
                cambiarPanel.cambiarPanel(panelPrincipal, panelRecepcionista);
                panelRecepcionista.establecerFondo();
                panelRecepcionista.estableceUsuario(nombreUsuario);
            break;
            default:
                cambiarPanel.cambiarPanel(panelPrincipal, panelOperador);
            break;
        }
   }
  
   /*
   Metodo encargado de asignar un valor a la instancia manejadorBodega
   */
   public void setManejadorBodega(ManejadorBodega manejadorBodega){
       this.manejadorBodega = manejadorBodega;
   }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        CreadorUsuarioAdministrador = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        etiquetaInformacionCreacionUsuarioAdmin = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoContrasenaCreacionUsuarioAdmin = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textoNombreUsuarioCreacionUsuarioAdmin = new javax.swing.JTextField();
        textoNombresCreacionUsuarioAdmin = new javax.swing.JTextField();
        textoApellidosCreacionUsuarioAdmin = new javax.swing.JTextField();
        etiquetaAlertaCrearUsuarioAdministrador = new javax.swing.JLabel();
        botonVerContrasenaCreacionUsuarioAdmin = new rojeru_san.RSButton();
        jPanel5 = new javax.swing.JPanel();
        botonSalirUsuarioAdmin = new rojerusan.RSButtonIconI();
        botonCrearUsuarioAdmin = new rojerusan.RSButtonIconI();
        IniciarSesion = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        etiquetaInformacionLogin = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        textoContrasenaLogin = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        textoNombreUsuarioLogin = new javax.swing.JTextField();
        etiquetaAlertaLogin = new javax.swing.JLabel();
        botonVerContrasenaLogin = new rojeru_san.RSButton();
        jPanel12 = new javax.swing.JPanel();
        botonSalirLogin = new rojerusan.RSButtonIconI();
        botonIngresarLogin = new rojerusan.RSButtonIconI();
        MostradorMensajes = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        ReceptorTarifas = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        etiquetaInformacionTarifa = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        textoCuotaPriorizacion = new javax.swing.JTextField();
        textoTarifaOperacion = new javax.swing.JTextField();
        textoPrecioPorLiba = new javax.swing.JTextField();
        etiquetaAlertaTarifa = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        textoCuotaDestino = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        botonCancelarTarifa = new rojerusan.RSButtonIconI();
        botonAceptarTarifa = new rojerusan.RSButtonIconI();
        panelPrincipal = new javax.swing.JPanel();

        CreadorUsuarioAdministrador.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        CreadorUsuarioAdministrador.setTitle("Creacion De Usuario Administrador");
        CreadorUsuarioAdministrador.setMinimumSize(new java.awt.Dimension(701, 562));
        CreadorUsuarioAdministrador.setModal(true);
        CreadorUsuarioAdministrador.setResizable(false);
        CreadorUsuarioAdministrador.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setMaximumSize(new java.awt.Dimension(701, 562));
        jPanel2.setMinimumSize(new java.awt.Dimension(701, 562));
        jPanel2.setName(""); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N

        etiquetaInformacionCreacionUsuarioAdmin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaInformacionCreacionUsuarioAdmin.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaInformacionCreacionUsuarioAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaInformacionCreacionUsuarioAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaInformacionCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

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

        textoContrasenaCreacionUsuarioAdmin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoContrasenaCreacionUsuarioAdmin.setForeground(new java.awt.Color(0, 102, 153));
        textoContrasenaCreacionUsuarioAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoContrasenaCreacionUsuarioAdminKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        textoNombreUsuarioCreacionUsuarioAdmin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombreUsuarioCreacionUsuarioAdmin.setForeground(new java.awt.Color(0, 102, 153));
        textoNombreUsuarioCreacionUsuarioAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombreUsuarioCreacionUsuarioAdminKeyTyped(evt);
            }
        });

        textoNombresCreacionUsuarioAdmin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombresCreacionUsuarioAdmin.setForeground(new java.awt.Color(0, 102, 153));
        textoNombresCreacionUsuarioAdmin.setToolTipText("");
        textoNombresCreacionUsuarioAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombresCreacionUsuarioAdminKeyTyped(evt);
            }
        });

        textoApellidosCreacionUsuarioAdmin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoApellidosCreacionUsuarioAdmin.setForeground(new java.awt.Color(0, 102, 153));
        textoApellidosCreacionUsuarioAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoApellidosCreacionUsuarioAdminKeyTyped(evt);
            }
        });

        etiquetaAlertaCrearUsuarioAdministrador.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaCrearUsuarioAdministrador.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaCrearUsuarioAdministrador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaCrearUsuarioAdministrador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        botonVerContrasenaCreacionUsuarioAdmin.setBackground(new java.awt.Color(204, 204, 204));
        botonVerContrasenaCreacionUsuarioAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png"))); // NOI18N
        botonVerContrasenaCreacionUsuarioAdmin.setBorderPainted(false);
        botonVerContrasenaCreacionUsuarioAdmin.setColorHover(new java.awt.Color(153, 153, 153));
        botonVerContrasenaCreacionUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerContrasenaCreacionUsuarioAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaCrearUsuarioAdministrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoApellidosCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoNombreUsuarioCreacionUsuarioAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNombresCreacionUsuarioAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoContrasenaCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonVerContrasenaCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(textoNombresCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(textoApellidosCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textoNombreUsuarioCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(textoContrasenaCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addComponent(botonVerContrasenaCreacionUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(etiquetaAlertaCrearUsuarioAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        botonSalirUsuarioAdmin.setBackground(new java.awt.Color(255, 0, 0));
        botonSalirUsuarioAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salir.png"))); // NOI18N
        botonSalirUsuarioAdmin.setText("SALIR");
        botonSalirUsuarioAdmin.setColorHover(new java.awt.Color(153, 153, 153));
        botonSalirUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirUsuarioAdminActionPerformed(evt);
            }
        });

        botonCrearUsuarioAdmin.setBackground(new java.awt.Color(255, 0, 0));
        botonCrearUsuarioAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/crear.png"))); // NOI18N
        botonCrearUsuarioAdmin.setText("CREAR");
        botonCrearUsuarioAdmin.setColorHover(new java.awt.Color(153, 153, 153));
        botonCrearUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearUsuarioAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonCrearUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonSalirUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSalirUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrearUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        CreadorUsuarioAdministrador.getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

        IniciarSesion.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        IniciarSesion.setTitle("Iniciar Sesion");
        IniciarSesion.setMinimumSize(new java.awt.Dimension(701, 415));
        IniciarSesion.setModal(true);
        IniciarSesion.setResizable(false);
        IniciarSesion.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setMaximumSize(new java.awt.Dimension(701, 365));
        jPanel9.setMinimumSize(new java.awt.Dimension(701, 365));
        jPanel9.setName(""); // NOI18N

        jPanel10.setBackground(new java.awt.Color(204, 0, 0));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/usuario.png"))); // NOI18N

        etiquetaInformacionLogin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaInformacionLogin.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaInformacionLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaInformacionLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaInformacionLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/nombreUsuario.png"))); // NOI18N
        jLabel17.setText("Nombre de Usuario:");

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/contrasena.png"))); // NOI18N
        jLabel18.setText("Contraseña:");

        textoContrasenaLogin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoContrasenaLogin.setForeground(new java.awt.Color(0, 102, 153));
        textoContrasenaLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoContrasenaLoginKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("*");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("*");

        textoNombreUsuarioLogin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoNombreUsuarioLogin.setForeground(new java.awt.Color(0, 102, 153));
        textoNombreUsuarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombreUsuarioLoginKeyTyped(evt);
            }
        });

        etiquetaAlertaLogin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaLogin.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        botonVerContrasenaLogin.setBackground(new java.awt.Color(204, 204, 204));
        botonVerContrasenaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png"))); // NOI18N
        botonVerContrasenaLogin.setBorderPainted(false);
        botonVerContrasenaLogin.setColorHover(new java.awt.Color(153, 153, 153));
        botonVerContrasenaLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerContrasenaLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(textoNombreUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoContrasenaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonVerContrasenaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(textoNombreUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonVerContrasenaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoContrasenaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(etiquetaAlertaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        botonSalirLogin.setBackground(new java.awt.Color(255, 0, 0));
        botonSalirLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salir.png"))); // NOI18N
        botonSalirLogin.setText("SALIR");
        botonSalirLogin.setColorHover(new java.awt.Color(153, 153, 153));
        botonSalirLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirLoginActionPerformed(evt);
            }
        });

        botonIngresarLogin.setBackground(new java.awt.Color(255, 0, 0));
        botonIngresarLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonIngresarLogin.setText("INGRESAR");
        botonIngresarLogin.setColorHover(new java.awt.Color(153, 153, 153));
        botonIngresarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonIngresarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonSalirLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSalirLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonIngresarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        IniciarSesion.getContentPane().add(jPanel9, gridBagConstraints);

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(406, 200));
        MostradorMensajes.setModal(true);
        MostradorMensajes.setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 153, 0));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));

        etiquetaMensaje.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        etiquetaMensaje.setForeground(new java.awt.Color(0, 153, 255));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botonAceptarMensaje.setBackground(new java.awt.Color(51, 153, 0));
        botonAceptarMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarMensaje.setText("ACEPTAR");
        botonAceptarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarMensajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(etiquetaMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addGap(0, 107, Short.MAX_VALUE)
                            .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(93, 93, 93)))
                    .addGap(3, 3, 3)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(etiquetaMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout MostradorMensajesLayout = new javax.swing.GroupLayout(MostradorMensajes.getContentPane());
        MostradorMensajes.getContentPane().setLayout(MostradorMensajesLayout);
        MostradorMensajesLayout.setHorizontalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MostradorMensajesLayout.setVerticalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorMensajesLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ReceptorTarifas.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ReceptorTarifas.setTitle("Establecer Tarifas Globales");
        ReceptorTarifas.setMinimumSize(new java.awt.Dimension(628, 596));
        ReceptorTarifas.setModal(true);
        ReceptorTarifas.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setMaximumSize(new java.awt.Dimension(628, 516));
        jPanel6.setMinimumSize(new java.awt.Dimension(628, 516));
        jPanel6.setName(""); // NOI18N

        jPanel7.setBackground(new java.awt.Color(51, 153, 0));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billetera.png"))); // NOI18N

        etiquetaInformacionTarifa.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        etiquetaInformacionTarifa.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaInformacionTarifa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaInformacionTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaInformacionTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel11.setText("Precio por Libra:");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel12.setText("Cuota de Priorizacion:");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 153));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel14.setText("Tarifa de Operacion:");

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("*");

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("*");

        textoCuotaPriorizacion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoCuotaPriorizacion.setForeground(new java.awt.Color(0, 102, 153));

        textoTarifaOperacion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoTarifaOperacion.setForeground(new java.awt.Color(0, 102, 153));
        textoTarifaOperacion.setToolTipText("");

        textoPrecioPorLiba.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoPrecioPorLiba.setForeground(new java.awt.Color(0, 102, 153));

        etiquetaAlertaTarifa.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTarifa.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaTarifa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaAlertaTarifa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("Q");

        jLabel24.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 153));
        jLabel24.setText("Q");

        jLabel25.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 153));
        jLabel25.setText("Q");

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 153));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel26.setText("Cuota de Destino:");

        jLabel27.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 153));
        jLabel27.setText("Q");

        textoCuotaDestino.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoCuotaDestino.setForeground(new java.awt.Color(0, 102, 153));

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("*");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlertaTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoCuotaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoPrecioPorLiba, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoCuotaPriorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel20)
                    .addComponent(textoPrecioPorLiba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(textoCuotaPriorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCuotaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiquetaAlertaTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        botonCancelarTarifa.setBackground(new java.awt.Color(51, 153, 0));
        botonCancelarTarifa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cancelar.png"))); // NOI18N
        botonCancelarTarifa.setText("CANCELAR");
        botonCancelarTarifa.setColorHover(new java.awt.Color(153, 153, 153));
        botonCancelarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarTarifaActionPerformed(evt);
            }
        });

        botonAceptarTarifa.setBackground(new java.awt.Color(51, 153, 0));
        botonAceptarTarifa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/aceptar.png"))); // NOI18N
        botonAceptarTarifa.setText("ACEPTAR");
        botonAceptarTarifa.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarTarifaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(botonAceptarTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCancelarTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelarTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAceptarTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout ReceptorTarifasLayout = new javax.swing.GroupLayout(ReceptorTarifas.getContentPane());
        ReceptorTarifas.getContentPane().setLayout(ReceptorTarifasLayout);
        ReceptorTarifasLayout.setHorizontalGroup(
            ReceptorTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ReceptorTarifasLayout.setVerticalGroup(
            ReceptorTarifasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema De Envio De Paquetes");

        panelPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        panelPrincipal.setMaximumSize(new java.awt.Dimension(500, 500));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(500, 500));
        panelPrincipal.setLayout(new javax.swing.BoxLayout(panelPrincipal, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
    Metodo encargado de la creacion del primer usuario administrador en el sistema. Valida que los campos obligatorios no 
    se encuentren vacios y posteriormente obtiene la informacion almacenada en los campos de texto. Por ultimo registra en
    la base de datos al nuevo usuario, lanza un mensaje e inicia el panel correspondiente al usuario de tipo administrador.
    */
    private void botonCrearUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearUsuarioAdminActionPerformed
       if(textoNombresCreacionUsuarioAdmin.getText().isEmpty() || textoApellidosCreacionUsuarioAdmin.getText().isEmpty() || textoNombreUsuarioCreacionUsuarioAdmin.getText().isEmpty() || textoContrasenaCreacionUsuarioAdmin.getText().isEmpty()){
           etiquetaAlertaCrearUsuarioAdministrador.setText("Se deben llenar todos los campos obligatorios");
           manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCrearUsuarioAdministrador);
       }
       else{
           nombres = textoNombresCreacionUsuarioAdmin.getText();
           apellidos = textoApellidosCreacionUsuarioAdmin.getText();
           nombreUsuario = textoNombreUsuarioCreacionUsuarioAdmin.getText();
           contrasena = textoContrasenaCreacionUsuarioAdmin.getText(); 
           usuario = new Usuario(nombres, apellidos, nombreUsuario, contrasena, this.ADMINISTRADOR, true);   
           mensaje = manejadorDB.crearNuevoUsuario(usuario);
           this.lanzarMensaje(mensaje);
           CreadorUsuarioAdministrador.setVisible(false);
           tipoUsuarioActual = "Administrador";
           this.mostrarAreaTrabajo();
       }
    }//GEN-LAST:event_botonCrearUsuarioAdminActionPerformed
    /*
    Metodo encargado de terminar la ejecucion del programa
    */
    private void botonSalirUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirUsuarioAdminActionPerformed
        manejadorBodega.setCerrarAplicacion(true);
        System.exit(0);
    }//GEN-LAST:event_botonSalirUsuarioAdminActionPerformed
    /*
    Metodo encargado de mostrar y ocultar la contrasena. Modifica la imagen del boton correspondiente y cambia
    el valor de la variable booleana contraenaOculta en base a una comparacion que detecta si la contrasena 
    actualmente se encuentra oculta o no.
    */
    private void botonVerContrasenaCreacionUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerContrasenaCreacionUsuarioAdminActionPerformed
        if(contrasenaOculta){
            botonVerContrasenaCreacionUsuarioAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ocultar.png")));
            textoContrasenaCreacionUsuarioAdmin.setEchoChar((char)0);
            contrasenaOculta = false;
        }
        else{
            botonVerContrasenaCreacionUsuarioAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png")));
            textoContrasenaCreacionUsuarioAdmin.setEchoChar('*');
            contrasenaOculta = true;
        }
    }//GEN-LAST:event_botonVerContrasenaCreacionUsuarioAdminActionPerformed
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNombresCreacionUsuarioAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombresCreacionUsuarioAdminKeyTyped
        if (textoNombresCreacionUsuarioAdmin.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCrearUsuarioAdministrador.setText("Solo se permite el ingreso de 30 caracteres en el campo de nombres");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCrearUsuarioAdministrador);
        }
    }//GEN-LAST:event_textoNombresCreacionUsuarioAdminKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoApellidosCreacionUsuarioAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoApellidosCreacionUsuarioAdminKeyTyped
        if (textoApellidosCreacionUsuarioAdmin.getText().length() == 30) {
            evt.consume();
            etiquetaAlertaCrearUsuarioAdministrador.setText("Solo se permite el ingreso de 30 caracteres en el campo de apellidos");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCrearUsuarioAdministrador);
        }
    }//GEN-LAST:event_textoApellidosCreacionUsuarioAdminKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNombreUsuarioCreacionUsuarioAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreUsuarioCreacionUsuarioAdminKeyTyped
        if (textoNombreUsuarioCreacionUsuarioAdmin.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaCrearUsuarioAdministrador.setText("Solo se permite el ingreso de 25 caracteres en el campo de nombre de usuario");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCrearUsuarioAdministrador);
        }
    }//GEN-LAST:event_textoNombreUsuarioCreacionUsuarioAdminKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoContrasenaCreacionUsuarioAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoContrasenaCreacionUsuarioAdminKeyTyped
        if (textoContrasenaCreacionUsuarioAdmin.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaCrearUsuarioAdministrador.setText("Solo se permite el ingreso de 25 caracteres en el campo de contraseña");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaCrearUsuarioAdministrador);
        }
    }//GEN-LAST:event_textoContrasenaCreacionUsuarioAdminKeyTyped
    /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoContrasenaLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoContrasenaLoginKeyTyped
         if (textoContrasenaLogin.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaLogin.setText("Solo se permite el ingreso de 25 caracteres en el campo de contraseña");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaLogin);
        }
    }//GEN-LAST:event_textoContrasenaLoginKeyTyped
     /*
    Metodo encargado de limitar la cantidad de caracteres que se pueden ingresar en un campo de texto
    */
    private void textoNombreUsuarioLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreUsuarioLoginKeyTyped
        if (textoNombreUsuarioLogin.getText().length() == 25) {
            evt.consume();
            etiquetaAlertaLogin.setText("Solo se permite el ingreso de 25 caracteres en el campo de nombre de usuario");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaLogin);
        }
    }//GEN-LAST:event_textoNombreUsuarioLoginKeyTyped
     /*
    Metodo encargado de mostrar y ocultar la contrasena. Modifica la imagen del boton correspondiente y cambia
    el valor de la variable booleana contraenaOculta en base a una comparacion que detecta si la contrasena 
    actualmente se encuentra oculta o no.
    */
    private void botonVerContrasenaLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerContrasenaLoginActionPerformed
        if(contrasenaOculta){
            botonVerContrasenaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ocultar.png")));
            textoContrasenaLogin.setEchoChar((char)0);
            contrasenaOculta = false;
        }
        else{
            botonVerContrasenaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png")));
            textoContrasenaLogin.setEchoChar('*');
            contrasenaOculta = true;
        }
    }//GEN-LAST:event_botonVerContrasenaLoginActionPerformed
     /*
    Metodo encargado de terminar la ejecucion del programa o de cerrar el JDialog, dependiendo de la validacion correspondiente
    */
    private void botonSalirLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirLoginActionPerformed
        if(botonSalirLogin.getText().equals("Cancelar")){
            IniciarSesion.dispose();
        }
        else{
            manejadorBodega.setCerrarAplicacion(true);
            System.exit(0);
        }
    }//GEN-LAST:event_botonSalirLoginActionPerformed
    /*
    Metodo encargado de validar el login de un usuario. Valida que los campos obligatorios se encuentres llenos,
    posteriormente obtiene los valores contenidos dentro de los campos de texto y llama al meteodo validarLogin
    que devuelve un String mediante al cual se sentencia la accion a realizar.  Si el inicio de sesion es valido 
    se obtiene el tipo de usuario que ha iniciado sesion y se muestra el panel correspondiente.
    */
    private void botonIngresarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarLoginActionPerformed
        if(textoNombreUsuarioLogin.getText().isEmpty() || textoContrasenaLogin.getText().isEmpty()){
            etiquetaAlertaLogin.setText("Se deben llenar todos los campos obligatorios");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaLogin);
        }
        else{
            nombreUsuario = textoNombreUsuarioLogin.getText();
            contrasena = textoContrasenaLogin.getText();
            mensaje = manejadorDB.validarLogin(nombreUsuario, contrasena);
            if(mensaje.equals("Iniciar Sesion")){
                tipoUsuarioActual = manejadorDB.obtenerTipoUsuario();
                IniciarSesion.setVisible(false);
                textoContrasenaLogin.setEchoChar('*');
                botonVerContrasenaLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ver.png")));
                this.mostrarAreaTrabajo();
            }
            else{
                etiquetaAlertaLogin.setText(mensaje);
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaLogin);
                if(mensaje.equals("Contraseña incorrecta")){
                    textoContrasenaLogin.setText("");
                }
                else if(mensaje.equals("Nombre de usuario no registrado en el sistema")){
                    textoNombreUsuarioLogin.setText("");
                }
            }
        }
    }//GEN-LAST:event_botonIngresarLoginActionPerformed
    /*
    Metodo encargado de cerrar la ventana de mensaje
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed
    /*
    Metodo encargado de establecer las tarifas globales en Q0.00
    */
    private void botonCancelarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarTarifaActionPerformed
        tarifa = new Tarifa(0, 0, 0, 0);
        manejadorDB.establecerTarifas(tarifa, true);
        this.lanzarMensaje("Tarifas Establecidas en Q0.00");
        this.ReceptorTarifas.setVisible(false);   
    }//GEN-LAST:event_botonCancelarTarifaActionPerformed

    private void botonAceptarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarTarifaActionPerformed
    if(textoTarifaOperacion.getText().isEmpty() || textoPrecioPorLiba.getText().isEmpty() || textoCuotaPriorizacion.getText().isEmpty()){
        etiquetaAlertaTarifa.setText("Se deben llenar todos los campos obligatorios");
        manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTarifa);
    }
    else{
        try{
            tarifaOperacion = Double.parseDouble(textoTarifaOperacion.getText());
            precioPorLibra = Double.parseDouble(textoPrecioPorLiba.getText());
            cuotaPriorizacion = Double.parseDouble(textoCuotaPriorizacion.getText());
            cuotaDestino = Double.parseDouble(textoCuotaDestino.getText());
            tarifa = new Tarifa(tarifaOperacion, precioPorLibra, cuotaPriorizacion, cuotaDestino);
            manejadorDB.establecerTarifas(tarifa, true);
            this.lanzarMensaje("Tarifas establecidas exitosamente");
            this.ReceptorTarifas.setVisible(false);
        }   
        catch(NumberFormatException e){
            etiquetaAlertaTarifa.setText("Monto ingresado no valido");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTarifa);
        }
    }
    }//GEN-LAST:event_botonAceptarTarifaActionPerformed
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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CreadorUsuarioAdministrador;
    private javax.swing.JDialog IniciarSesion;
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog ReceptorTarifas;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonAceptarTarifa;
    private rojerusan.RSButtonIconI botonCancelarTarifa;
    private rojerusan.RSButtonIconI botonCrearUsuarioAdmin;
    private rojerusan.RSButtonIconI botonIngresarLogin;
    private rojerusan.RSButtonIconI botonSalirLogin;
    private rojerusan.RSButtonIconI botonSalirUsuarioAdmin;
    private rojeru_san.RSButton botonVerContrasenaCreacionUsuarioAdmin;
    private rojeru_san.RSButton botonVerContrasenaLogin;
    private javax.swing.JLabel etiquetaAlertaCrearUsuarioAdministrador;
    private javax.swing.JLabel etiquetaAlertaLogin;
    private javax.swing.JLabel etiquetaAlertaTarifa;
    private javax.swing.JLabel etiquetaInformacionCreacionUsuarioAdmin;
    private javax.swing.JLabel etiquetaInformacionLogin;
    private javax.swing.JLabel etiquetaInformacionTarifa;
    private javax.swing.JLabel etiquetaMensaje;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField textoApellidosCreacionUsuarioAdmin;
    private javax.swing.JPasswordField textoContrasenaCreacionUsuarioAdmin;
    private javax.swing.JPasswordField textoContrasenaLogin;
    private javax.swing.JTextField textoCuotaDestino;
    private javax.swing.JTextField textoCuotaPriorizacion;
    private javax.swing.JTextField textoNombreUsuarioCreacionUsuarioAdmin;
    private javax.swing.JTextField textoNombreUsuarioLogin;
    private javax.swing.JTextField textoNombresCreacionUsuarioAdmin;
    private javax.swing.JTextField textoPrecioPorLiba;
    private javax.swing.JTextField textoTarifaOperacion;
    // End of variables declaration//GEN-END:variables
}
