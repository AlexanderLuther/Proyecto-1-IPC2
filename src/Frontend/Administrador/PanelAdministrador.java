package Frontend.Administrador;
import Backend.CambiadorPaneles;
import Backend.ManejadorBodega;
import Backend.ManejadorDBSM;
import Backend.ManejadorHilos;
import Backend.Tarifa;
import Frontend.PanelInicio;
import Frontend.VentanaPrincipal;
import java.sql.ResultSet;
/**
 *
 * @author helmuthluther
 */
public class PanelAdministrador extends javax.swing.JPanel {

    //Variables e instancias de la clase
    private ManejadorHilos manejadorHilos;
    private ManejadorDBSM manejadorDB;
    private CambiadorPaneles cambiarPanel;
    private VentanaPrincipal ventanaPrincipal;
    private PanelUsuario panelUsuario;
    private PanelInicio panelInicio;
    private PanelReportes panelReportes;
    private PanelRuta panelRuta;
    private double tarifaOperacion;
    private double precioPorLibra;
    private double cuotaPriorizacion;
    private double cuotaDestino;
    private Tarifa tarifa;
    private ResultSet resultado;
    private ManejadorBodega manejadorBodega;
    
    //Constructor de la clase
    public PanelAdministrador(VentanaPrincipal ventanaPrincipal) {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorHilos = new ManejadorHilos();
        this.cambiarPanel = new CambiadorPaneles();
        this.ventanaPrincipal = ventanaPrincipal;
        this.panelUsuario = new PanelUsuario();
        this.panelInicio = new PanelInicio();
        this.panelRuta = new PanelRuta();
        this.panelReportes = new PanelReportes();
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
        ReceptorTarifas = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
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
        jLabel15 = new javax.swing.JLabel();
        textoCuotaDestino = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        botonCancelarTarifa = new rojerusan.RSButtonIconI();
        botonAceptarTarifa = new rojerusan.RSButtonIconI();
        panelPrincipalAdministrador = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        panelNuevo = new javax.swing.JPanel();
        botonUsuario = new rojerusan.RSButtonIconI();
        botonReportes = new rojerusan.RSButtonIconI();
        botonRuta = new rojerusan.RSButtonIconI();
        botonTarifa = new rojerusan.RSButtonIconI();
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

        jPanel2.setBackground(new java.awt.Color(51, 153, 0));

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
                .addGap(200, 200, 200)
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
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(botonAceptarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
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

        ReceptorTarifas.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        ReceptorTarifas.setTitle("Establecer Tarifas Globales");
        ReceptorTarifas.setMaximumSize(new java.awt.Dimension(637, 598));
        ReceptorTarifas.setMinimumSize(new java.awt.Dimension(637, 598));
        ReceptorTarifas.setModal(true);
        ReceptorTarifas.setPreferredSize(new java.awt.Dimension(637, 598));
        ReceptorTarifas.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setMaximumSize(new java.awt.Dimension(628, 516));
        jPanel6.setMinimumSize(new java.awt.Dimension(628, 516));
        jPanel6.setName(""); // NOI18N

        jPanel8.setBackground(new java.awt.Color(51, 153, 0));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billetera.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel11.setText("Precio por Libra:");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel12.setText("Cuota de Priorizacion");

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

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 153));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/billete.png"))); // NOI18N
        jLabel15.setText("Cuota de Destino");

        textoCuotaDestino.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        textoCuotaDestino.setForeground(new java.awt.Color(0, 102, 153));

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setText("Q");

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("*");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoCuotaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textoPrecioPorLiba, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoCuotaPriorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(etiquetaAlertaTarifa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(textoTarifaOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel20)
                    .addComponent(textoPrecioPorLiba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(textoCuotaPriorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoCuotaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        setLayout(new java.awt.BorderLayout());

        panelMenu.setBackground(new java.awt.Color(204, 204, 204));
        panelMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));

        panelNuevo.setBackground(new java.awt.Color(204, 204, 204));
        panelNuevo.setPreferredSize(new java.awt.Dimension(260, 113));

        botonUsuario.setBackground(new java.awt.Color(204, 204, 204));
        botonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/silueta-de-usuario.png"))); // NOI18N
        botonUsuario.setText("USUARIO");
        botonUsuario.setBorderPainted(false);
        botonUsuario.setColorHover(new java.awt.Color(51, 153, 0));
        botonUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonUsuarioActionPerformed(evt);
            }
        });

        botonReportes.setBackground(new java.awt.Color(204, 204, 204));
        botonReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reporte.png"))); // NOI18N
        botonReportes.setText("REPORTES");
        botonReportes.setBorderPainted(false);
        botonReportes.setColorHover(new java.awt.Color(51, 153, 0));
        botonReportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReportesActionPerformed(evt);
            }
        });

        botonRuta.setBackground(new java.awt.Color(204, 204, 204));
        botonRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ruta.png"))); // NOI18N
        botonRuta.setText("RUTA");
        botonRuta.setBorderPainted(false);
        botonRuta.setColorHover(new java.awt.Color(51, 153, 0));
        botonRuta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRuta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRutaActionPerformed(evt);
            }
        });

        botonTarifa.setBackground(new java.awt.Color(204, 204, 204));
        botonTarifa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/tarifa.png"))); // NOI18N
        botonTarifa.setText("TARIFA");
        botonTarifa.setBorderPainted(false);
        botonTarifa.setColorHover(new java.awt.Color(51, 153, 0));
        botonTarifa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonTarifa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTarifaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
            .addComponent(botonRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(botonTarifa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botonTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(botonReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        etiquetaNuevo.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        etiquetaNuevo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaNuevo.setText("MENU");

        panelMas.setBackground(new java.awt.Color(204, 204, 204));

        botonHome.setBackground(new java.awt.Color(204, 204, 204));
        botonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/home.png"))); // NOI18N
        botonHome.setText("INICIO");
        botonHome.setBorderPainted(false);
        botonHome.setColorHover(new java.awt.Color(51, 153, 0));
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
        botonCambiarUsuario.setColorHover(new java.awt.Color(51, 153, 0));
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
        botonAcecaDe.setColorHover(new java.awt.Color(51, 153, 0));
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

        panelEncabezado.setBackground(new java.awt.Color(51, 153, 0));
        panelEncabezado.setLayout(new java.awt.GridBagLayout());

        logoIngenieria.setBackground(new java.awt.Color(51, 153, 0));
        logoIngenieria.setBorder(null);
        logoIngenieria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/adminisrador.png"))); // NOI18N
        logoIngenieria.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 117;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 18);
        panelEncabezado.add(logoIngenieria, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(51, 153, 0));

        titulo1.setBackground(new java.awt.Color(0, 153, 153));
        titulo1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("ADMINISTRADOR");

        etiquetaUsuario.setBackground(new java.awt.Color(0, 153, 153));
        etiquetaUsuario.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        etiquetaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaUsuario.setText("Usuario:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
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
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 150, 0, 0);
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
    Metodo encargado de mostrar el panel de Usuarios. Inicializa la tabla de usuarios con
    los primeros 45 usuarios.
    */
    private void botonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonUsuarioActionPerformed
        panelUsuario.obtenerUsuarios(0);
        panelUsuario.inicializarCasillasSeleccion();
        cambiarPanel.cambiarPanel(panelPrincipal, panelUsuario);
    }//GEN-LAST:event_botonUsuarioActionPerformed
    /*
    Metodo encargado de mostrar el panel de reportes
    */
    private void botonReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReportesActionPerformed
        cambiarPanel.cambiarPanel(panelPrincipal, panelReportes);
    }//GEN-LAST:event_botonReportesActionPerformed

    /*
    Metodo encargado de mostrar el panel de Rutas. Inicializa la tabla de rutas con
    las primeras 45 rutas.
    */
    private void botonRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRutaActionPerformed
        panelRuta.reestructurarPanelRuta();
        panelRuta.obtenerRutas(0);
        panelRuta.inicializarCasillasSeleccion();
        cambiarPanel.cambiarPanel(panelPrincipal, panelRuta);
    }//GEN-LAST:event_botonRutaActionPerformed

    /*
    Metodo encargado de recuperar la informacion correspondiente a tarifa de la base de datos,
    inicializa el JDialog ReceptorTarifas con la informacion obtenida y lo hace visible
    */
    private void botonTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTarifaActionPerformed
        ReceptorTarifas.setLocationRelativeTo(this);
        tarifa = manejadorDB.recuperarTarifas();
        textoTarifaOperacion.setText(String.valueOf(tarifa.getTarifaOperacionGlobal()));
        textoPrecioPorLiba.setText(String.valueOf(tarifa.getPrecioLibraGlobal()));
        textoCuotaPriorizacion.setText(String.valueOf(tarifa.getCuotaPriorizacionGlobal()));
        textoCuotaDestino.setText(String.valueOf(tarifa.getCuotaDestinoGlobal()));
        ReceptorTarifas.setVisible(true);
    }//GEN-LAST:event_botonTarifaActionPerformed

    //Metodo encargado de mostrar la informacion del desarrollador
    private void botonAcecaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAcecaDeActionPerformed
        this.lanzarMensaje("<html> <center> Desarollado por: <br> Helmuth Alexander Luther Montejo </center> </html>");
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

    //Metodo encargado de cerrar el JDialog ReceptorTarifas
    private void botonCancelarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarTarifaActionPerformed
        ReceptorTarifas.dispose();
        this.ReceptorTarifas.setVisible(false);
    }//GEN-LAST:event_botonCancelarTarifaActionPerformed
    
    /*
    Metodo encargado de validar que los campos obligatorios se encuentren llenos. Posteriormente recupera
    la informacion ingresada y la almacena en las variables determinanas. Por ultimo manda a actualizar 
    la informacion en la base de datos.
    */
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
                manejadorDB.establecerTarifas(tarifa, false);
                this.lanzarMensaje("Tarifas establecidas exitosamente");
                this.ReceptorTarifas.setVisible(false);
            }
            catch(NumberFormatException e){
                etiquetaAlertaTarifa.setText("Monto ingresado no valido");
                manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTarifa);
            }
        }
    }//GEN-LAST:event_botonAceptarTarifaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog ReceptorTarifas;
    private rojerusan.RSButtonIconI botonAcecaDe;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonAceptarTarifa;
    private rojerusan.RSButtonIconI botonCambiarUsuario;
    private rojerusan.RSButtonIconI botonCancelarTarifa;
    private rojerusan.RSButtonIconI botonHome;
    private rojerusan.RSButtonIconI botonReportes;
    private rojerusan.RSButtonIconI botonRuta;
    private rojerusan.RSButtonIconI botonSalir;
    private rojerusan.RSButtonIconI botonTarifa;
    private rojerusan.RSButtonIconI botonUsuario;
    private javax.swing.JLabel etiquetaAlertaTarifa;
    private javax.swing.JLabel etiquetaMas;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaNuevo;
    private javax.swing.JLabel etiquetaUsuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private rojerusan.RSButtonIconI logoIngenieria;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelMas;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelPrincipalAdministrador;
    private javax.swing.JTextField textoCuotaDestino;
    private javax.swing.JTextField textoCuotaPriorizacion;
    private javax.swing.JTextField textoPrecioPorLiba;
    private javax.swing.JTextField textoTarifaOperacion;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
