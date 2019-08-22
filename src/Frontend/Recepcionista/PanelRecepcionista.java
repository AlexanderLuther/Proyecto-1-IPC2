package Frontend.Recepcionista;
import Backend.CambiadorPaneles;
import Backend.ManejadorBodega;
import Backend.ManejadorDBSM;
import Frontend.PanelInicio;
import Frontend.VentanaPrincipal;
import java.sql.ResultSet;
/**
 *
 * @author helmuthluther
 */
public class PanelRecepcionista extends javax.swing.JPanel {

    //Variables e instancias de la clase
    private ManejadorDBSM manejadorDB;
    private CambiadorPaneles cambiarPanel;
    private VentanaPrincipal ventanaPrincipal;
    private PanelInicio panelInicio;
    private PanelNuevoPaquete panelNuevoPaquete;
    private PanelCliente panelCliente;
    private ResultSet resultado;
    private ManejadorBodega manejadorBodega;
    
    //Constructor de la clase
    public PanelRecepcionista(VentanaPrincipal ventanaPrincipal) {
        this.manejadorDB = new ManejadorDBSM();
        this.cambiarPanel = new CambiadorPaneles();
        this.ventanaPrincipal = ventanaPrincipal;
        this.panelCliente = new PanelCliente();
        this.panelInicio = new PanelInicio();
        this.panelNuevoPaquete = new PanelNuevoPaquete();
        initComponents();
    }
    
    //Metodo encargado de mostrar el usuario en turno
    public void estableceUsuario(String usuario){
        etiquetaUsuario.setText("USUARIO: " + usuario);
    }
  
   //Metodo encargado de mostrar en pantalla el mensaje de error que recibe como parametro. 
   public void lanzarMensaje(String mensaje){
        etiquetaMensaje.setText(mensaje);
        MostradorMensajes.setLocationRelativeTo(null);
        MostradorMensajes.setVisible(true);
   }
   
   //Metodo encargado de establecer el panel de fondo.
   public void establecerFondo(){
       cambiarPanel.cambiarPanel(panelPrincipal, panelInicio);
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

        MostradorMensajes = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        etiquetaMensaje = new javax.swing.JLabel();
        botonAceptarMensaje = new rojerusan.RSButtonIconI();
        panelPrincipalAdministrador = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        panelNuevo = new javax.swing.JPanel();
        botonNuevoPaquete = new rojerusan.RSButtonIconI();
        botonRetirarPaquete = new rojerusan.RSButtonIconI();
        botonLocalizacion = new rojerusan.RSButtonIconI();
        botonCliente = new rojerusan.RSButtonIconI();
        etiquetaNuevo = new javax.swing.JLabel();
        panelMas = new javax.swing.JPanel();
        botonHome = new rojerusan.RSButtonIconI();
        botonCambiarUsuario = new rojerusan.RSButtonIconI();
        botonAcecaDe = new rojerusan.RSButtonIconI();
        botonSalir = new rojerusan.RSButtonIconI();
        etiquetaMas = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        logoIngenieria = new rojerusan.RSButtonIconI();
        jPanel7 = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        etiquetaUsuario = new javax.swing.JLabel();

        MostradorMensajes.setTitle("Mensaje");
        MostradorMensajes.setBackground(new java.awt.Color(204, 204, 204));
        MostradorMensajes.setMinimumSize(new java.awt.Dimension(600, 200));
        MostradorMensajes.setModal(true);
        MostradorMensajes.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
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
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(199, 199, 199)
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
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MostradorMensajesLayout.setVerticalGroup(
            MostradorMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MostradorMensajesLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setLayout(new java.awt.BorderLayout());

        panelMenu.setBackground(new java.awt.Color(204, 204, 204));
        panelMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

        panelNuevo.setBackground(new java.awt.Color(204, 204, 204));
        panelNuevo.setPreferredSize(new java.awt.Dimension(260, 113));

        botonNuevoPaquete.setBackground(new java.awt.Color(204, 204, 204));
        botonNuevoPaquete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cajaBlancoNegro.png"))); // NOI18N
        botonNuevoPaquete.setText("  NUEVO PAQUETE");
        botonNuevoPaquete.setBorderPainted(false);
        botonNuevoPaquete.setColorHover(new java.awt.Color(0, 153, 255));
        botonNuevoPaquete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonNuevoPaquete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevoPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoPaqueteActionPerformed(evt);
            }
        });

        botonRetirarPaquete.setBackground(new java.awt.Color(204, 204, 204));
        botonRetirarPaquete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/retirar.png"))); // NOI18N
        botonRetirarPaquete.setText("---  RETIRAR PAQUETE");
        botonRetirarPaquete.setBorderPainted(false);
        botonRetirarPaquete.setColorHover(new java.awt.Color(0, 153, 255));
        botonRetirarPaquete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRetirarPaquete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRetirarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRetirarPaqueteActionPerformed(evt);
            }
        });

        botonLocalizacion.setBackground(new java.awt.Color(204, 204, 204));
        botonLocalizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mapa.png"))); // NOI18N
        botonLocalizacion.setText("LOCALIZACION");
        botonLocalizacion.setBorderPainted(false);
        botonLocalizacion.setColorHover(new java.awt.Color(0, 153, 255));
        botonLocalizacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonLocalizacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLocalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLocalizacionActionPerformed(evt);
            }
        });

        botonCliente.setBackground(new java.awt.Color(204, 204, 204));
        botonCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/silueta-de-usuario.png"))); // NOI18N
        botonCliente.setText("CLIENTE");
        botonCliente.setBorderPainted(false);
        botonCliente.setColorHover(new java.awt.Color(0, 153, 255));
        botonCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonNuevoPaquete, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(botonRetirarPaquete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonLocalizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonNuevoPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRetirarPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botonLocalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(botonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        etiquetaNuevo.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        etiquetaNuevo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaNuevo.setText("MENU");

        panelMas.setBackground(new java.awt.Color(204, 204, 204));

        botonHome.setBackground(new java.awt.Color(204, 204, 204));
        botonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/home.png"))); // NOI18N
        botonHome.setText("INICIO");
        botonHome.setBorderPainted(false);
        botonHome.setColorHover(new java.awt.Color(0, 153, 255));
        botonHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHomeActionPerformed(evt);
            }
        });

        botonCambiarUsuario.setBackground(new java.awt.Color(204, 204, 204));
        botonCambiarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/cambiar-de-usuario.png"))); // NOI18N
        botonCambiarUsuario.setText("   --     CAMBIAR USUARIO      ");
        botonCambiarUsuario.setBorderPainted(false);
        botonCambiarUsuario.setColorHover(new java.awt.Color(0, 153, 255));
        botonCambiarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCambiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarUsuarioActionPerformed(evt);
            }
        });

        botonAcecaDe.setBackground(new java.awt.Color(204, 204, 204));
        botonAcecaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/informacion.png"))); // NOI18N
        botonAcecaDe.setText("ACERCA DE");
        botonAcecaDe.setBorderPainted(false);
        botonAcecaDe.setColorHover(new java.awt.Color(0, 153, 255));
        botonAcecaDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonAcecaDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAcecaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAcecaDeActionPerformed(evt);
            }
        });

        botonSalir.setBackground(new java.awt.Color(204, 204, 204));
        botonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salirBlancoyNegro.png"))); // NOI18N
        botonSalir.setText("SALIR");
        botonSalir.setBorderPainted(false);
        botonSalir.setColorHover(new java.awt.Color(255, 0, 0));
        botonSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMasLayout = new javax.swing.GroupLayout(panelMas);
        panelMas.setLayout(panelMasLayout);
        panelMasLayout.setHorizontalGroup(
            panelMasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAcecaDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonCambiarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMasLayout.setVerticalGroup(
            panelMasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMasLayout.createSequentialGroup()
                .addComponent(botonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCambiarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAcecaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        etiquetaMas.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        etiquetaMas.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaMas.setText("MAS");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNuevo)
                    .addComponent(etiquetaMas))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelMas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(etiquetaNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(etiquetaMas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new java.awt.BorderLayout());

        panelEncabezado.setBackground(new java.awt.Color(0, 153, 255));
        panelEncabezado.setLayout(new java.awt.GridBagLayout());

        logoIngenieria.setBackground(new java.awt.Color(0, 153, 255));
        logoIngenieria.setBorder(null);
        logoIngenieria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/recepcion.png"))); // NOI18N
        logoIngenieria.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 117;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 56, 0, 126);
        panelEncabezado.add(logoIngenieria, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(0, 153, 255));

        titulo1.setBackground(new java.awt.Color(0, 153, 153));
        titulo1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("RECEPCIONISTA");

        etiquetaUsuario.setBackground(new java.awt.Color(0, 153, 153));
        etiquetaUsuario.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        etiquetaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaUsuario.setText("Usuario:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo1)
                        .addComponent(etiquetaUsuario))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(titulo1)
                    .addGap(7, 7, 7)
                    .addComponent(etiquetaUsuario)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 71;
        gridBagConstraints.ipady = 40;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 257, 0, 0);
        panelEncabezado.add(jPanel7, gridBagConstraints);

        javax.swing.GroupLayout panelPrincipalAdministradorLayout = new javax.swing.GroupLayout(panelPrincipalAdministrador);
        panelPrincipalAdministrador.setLayout(panelPrincipalAdministradorLayout);
        panelPrincipalAdministradorLayout.setHorizontalGroup(
            panelPrincipalAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelPrincipalAdministradorLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalAdministradorLayout.setVerticalGroup(
            panelPrincipalAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalAdministradorLayout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPrincipalAdministradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(panelPrincipalAdministrador, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    /*
    Metodo encargado de inicializar el panel Nuevo paquete y hacerlo visible.
    Limpia el listado encargado de almacenar temporalmente los paquetes a ingresar
    */
    private void botonNuevoPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoPaqueteActionPerformed
        panelNuevoPaquete.inicializar();
        panelNuevoPaquete.limpiarListadoPaquetes();
        cambiarPanel.cambiarPanel(panelPrincipal, panelNuevoPaquete);
    }//GEN-LAST:event_botonNuevoPaqueteActionPerformed

  
    private void botonRetirarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRetirarPaqueteActionPerformed
    
    }//GEN-LAST:event_botonRetirarPaqueteActionPerformed

    //Metodo encargado de mostrar la informacion del desarrollador
    private void botonAcecaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAcecaDeActionPerformed
        this.lanzarMensaje("<html> <center> Desarollador por: <br> Helmuth Alexander Luther Montejo </center> </html>");
    }//GEN-LAST:event_botonAcecaDeActionPerformed

    //Metodo encargado de mostrar la ventana de inicio de sesion.
    private void botonCambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarUsuarioActionPerformed
        ventanaPrincipal.iniciarSesion(1);
    }//GEN-LAST:event_botonCambiarUsuarioActionPerformed

    //Metodo encargado de detener el hilo de bodega y salir de la aplicacion
    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        manejadorBodega.setCerrarAplicacion(true);
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed
    
    //Metodo encargado de cerrar el JDialog encargado de mostrar mensajes
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed

    //Metodo encargado de mostrar el panel de inicio
    private void botonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHomeActionPerformed
        cambiarPanel.cambiarPanel(panelPrincipal, panelInicio);
    }//GEN-LAST:event_botonHomeActionPerformed
  
    private void botonLocalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLocalizacionActionPerformed
    }//GEN-LAST:event_botonLocalizacionActionPerformed
    /*
    Metodo encargado de inicializar el panel Cliente y hacerlo visible
    */
    private void botonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonClienteActionPerformed
        panelCliente.obtenerClientes(0);
        panelCliente.inicializarCasillasSeleccion();
        cambiarPanel.cambiarPanel(panelPrincipal, panelCliente);
    }//GEN-LAST:event_botonClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MostradorMensajes;
    private rojerusan.RSButtonIconI botonAcecaDe;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonCambiarUsuario;
    private rojerusan.RSButtonIconI botonCliente;
    private rojerusan.RSButtonIconI botonHome;
    private rojerusan.RSButtonIconI botonLocalizacion;
    private rojerusan.RSButtonIconI botonNuevoPaquete;
    private rojerusan.RSButtonIconI botonRetirarPaquete;
    private rojerusan.RSButtonIconI botonSalir;
    private javax.swing.JLabel etiquetaMas;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaNuevo;
    private javax.swing.JLabel etiquetaUsuario;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private rojerusan.RSButtonIconI logoIngenieria;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelMas;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelPrincipalAdministrador;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
