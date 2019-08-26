package Frontend.Operador;
import Backend.EntidadAsociativaPaquetePasaPuntoDeControl;
import Backend.ManejadorCalculos;
import Backend.ManejadorDBSM;
import Backend.ManejadorFechas;
import Backend.ManejadorHilos;
import Backend.PuntoDeControl;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
/**
 *
 * @author helmuthluther
 */
public class PanelPaqueteEnPuntoDeControl extends javax.swing.JPanel {
    
    //Variables e instancias de la clase.
    private PanelProcesarPaquete panelProcesarPaquete;
    private ManejadorDBSM manejadorDB;
    private ManejadorHilos manejadorHilos;
    private ManejadorCalculos manejadorCalculos;
    private ManejadorFechas manejadorFechas;
    private List<EntidadAsociativaPaquetePasaPuntoDeControl> listadoPaquetes;
    private ObservableList<EntidadAsociativaPaquetePasaPuntoDeControl> observableListPaquetes;
    private EntidadAsociativaPaquetePasaPuntoDeControl paquetePasaPuntoDeControl;
    private PuntoDeControl puntoDeControl;
    private String mensaje;
    private String tiempo;
    private double costo;
    private Timestamp fechaIngreso;

    
    //Constructor de la clase.
    public PanelPaqueteEnPuntoDeControl(PanelProcesarPaquete panelProcesarPaquete) {
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorHilos = new ManejadorHilos();
        this.manejadorCalculos = new ManejadorCalculos();
        this.manejadorFechas = new ManejadorFechas();
        this.panelProcesarPaquete = panelProcesarPaquete;
        this.listadoPaquetes = new ArrayList<>();
        this.observableListPaquetes = ObservableCollections.observableList(listadoPaquetes);
        initComponents();
    }

    /*
    Metodos utilizados para la implementacion de Beans Beanding.
    */

    public ObservableList<EntidadAsociativaPaquetePasaPuntoDeControl> getObservableListPaquetes() {
        return observableListPaquetes;
    }

    public void setObservableListPaquetes(ObservableList<EntidadAsociativaPaquetePasaPuntoDeControl> observableListPaquetes) {
        this.observableListPaquetes = observableListPaquetes;
    }
    
    
    public void llenarTabla(List listado){
        this.observableListPaquetes.clear();
        this.observableListPaquetes.addAll(listado);
    }
    
    public void obtenerPaquetes(PuntoDeControl puntoDeControl){
        listadoPaquetes = manejadorDB.obtenerListadoPaquetesEnPuntoDeControl("SELECT* FROM PaquetePasaPuntoDeControl WHERE CodigoPuntoDeControl = '"+puntoDeControl.getCodigo()+"' && CodigoRuta = '"+puntoDeControl.getCodigoRuta()+"'"
                                                                            + " && EnTurno = TRUE ORDER BY Prioridad DESC, FechaIngreso ASC;");
        this.llenarTabla(listadoPaquetes);
    }
    
    
    public void establecerInformacionPuntoDeControl(PuntoDeControl puntoDeControl){
        this.puntoDeControl = puntoDeControl;
        etiquetaRutaActual.setText(puntoDeControl.getNombre());
    }
    
  
    /*
    Metodo encargado de limpiar la etiqueta de alerta, lanzar un mensaje informativo y
    actualizar los datos de la tabla de puntos de control.
    */
    public void finalizarAccion(String mensaje){
        this.lanzarMensaje(mensaje);
        this.obtenerPaquetes(puntoDeControl);
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
    Metodo encargado de inicializar y hacer visible el JDialog ReceptorDeTiempo
    */
    public void mostrarReceptorTiempo(){
        textoTiempo.setText("");
        ReceptorTiempo.setLocationRelativeTo(this);
        ReceptorTiempo.setVisible(true);
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
        ReceptorTiempo = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        botonAceptarTiempo = new rojerusan.RSButtonIconI();
        jLabel1 = new javax.swing.JLabel();
        etiquetaAlertaTiempo = new javax.swing.JLabel();
        textoTiempo = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPaquetesEnPuntoDeControl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botonProcesar = new rojerusan.RSButtonIconI();
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

        jPanel8.setBackground(new java.awt.Color(51, 102, 255));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        etiquetaMensaje.setForeground(new java.awt.Color(51, 102, 255));
        etiquetaMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        botonAceptarMensaje.setBackground(new java.awt.Color(51, 102, 255));
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
                .addContainerGap(230, Short.MAX_VALUE))
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

        ReceptorTiempo.setTitle("Ingresar TIempo en Punto de Control");
        ReceptorTiempo.setBackground(new java.awt.Color(204, 204, 204));
        ReceptorTiempo.setMinimumSize(new java.awt.Dimension(587, 260));
        ReceptorTiempo.setModal(true);
        ReceptorTiempo.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/mensaje.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setMaximumSize(new java.awt.Dimension(587, 151));
        jPanel15.setMinimumSize(new java.awt.Dimension(587, 151));

        botonAceptarTiempo.setBackground(new java.awt.Color(51, 102, 255));
        botonAceptarTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/ok.png"))); // NOI18N
        botonAceptarTiempo.setText("ACEPTAR");
        botonAceptarTiempo.setColorHover(new java.awt.Color(153, 153, 153));
        botonAceptarTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarTiempoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/reloj.png"))); // NOI18N
        jLabel1.setText("Tiempo en punto de control :");

        etiquetaAlertaTiempo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaAlertaTiempo.setForeground(new java.awt.Color(255, 0, 0));
        etiquetaAlertaTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textoTiempo.setForeground(new java.awt.Color(0, 102, 153));
        try {
            textoTiempo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoTiempo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("HH:mm");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(textoTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(33, 33, 33))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaAlertaTiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(botonAceptarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addComponent(botonAceptarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlertaTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout ReceptorTiempoLayout = new javax.swing.GroupLayout(ReceptorTiempo.getContentPane());
        ReceptorTiempo.getContentPane().setLayout(ReceptorTiempoLayout);
        ReceptorTiempoLayout.setHorizontalGroup(
            ReceptorTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ReceptorTiempoLayout.setVerticalGroup(
            ReceptorTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReceptorTiempoLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setMaximumSize(new java.awt.Dimension(1240, 642));
        setMinimumSize(new java.awt.Dimension(1240, 642));
        setPreferredSize(new java.awt.Dimension(1240, 642));
        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(1244, 642));
        jPanel1.setMinimumSize(new java.awt.Dimension(1244, 642));

        tablaPaquetesEnPuntoDeControl.setBackground(new java.awt.Color(204, 204, 204));
        tablaPaquetesEnPuntoDeControl.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaPaquetesEnPuntoDeControl.setForeground(new java.awt.Color(51, 102, 255));
        tablaPaquetesEnPuntoDeControl.setEnabled(false);
        tablaPaquetesEnPuntoDeControl.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPaquetesEnPuntoDeControl.setSelectionBackground(new java.awt.Color(51, 102, 255));
        tablaPaquetesEnPuntoDeControl.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListPaquetes}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPaquetesEnPuntoDeControl);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigoPaquete}"));
        columnBinding.setColumnName("Codigo Paquete");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tablaPaquetesEnPuntoDeControl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaPaquetesEnPuntoDeControlMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPaquetesEnPuntoDeControl);
        if (tablaPaquetesEnPuntoDeControl.getColumnModel().getColumnCount() > 0) {
            tablaPaquetesEnPuntoDeControl.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        botonProcesar.setBackground(new java.awt.Color(51, 102, 255));
        botonProcesar.setForeground(new java.awt.Color(0, 102, 153));
        botonProcesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/procesar.png"))); // NOI18N
        botonProcesar.setText("Procesar");
        botonProcesar.setColorHover(new java.awt.Color(153, 153, 153));
        botonProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonProcesarActionPerformed(evt);
            }
        });

        botonRegresar.setBackground(new java.awt.Color(51, 102, 255));
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
                .addGap(14, 14, 14)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(botonProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(botonProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE)))
        );

        alertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        alertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        titulo1.setBackground(new java.awt.Color(0, 153, 153));
        titulo1.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("PUNTO DE CONTROL ACTUAL");

        etiquetaRutaActual.setBackground(new java.awt.Color(0, 153, 153));
        etiquetaRutaActual.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        etiquetaRutaActual.setForeground(new java.awt.Color(51, 102, 255));
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
                .addContainerGap(26, Short.MAX_VALUE))
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
    Motodo encargado de ocultar el panel actual y llamar al metodo reestructurarPanelProcesarPaquete
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
        panelProcesarPaquete.reestructurarPanelProcesarPaquete();
    }//GEN-LAST:event_botonRegresarActionPerformed
    /*
    Metodo encargado de cerrar el JDialog de mensajes
    */
    private void botonAceptarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarMensajeActionPerformed
        MostradorMensajes.dispose();
    }//GEN-LAST:event_botonAceptarMensajeActionPerformed
    /*
    Metodo encargado de procesar paquete en el punto de control actual. Valida que el siguiente punto de
    control tenga espacio en su cola, de lo contrario lanza un mensaje de alerta. Primero obtiene el objeto
    sobre el cual se modificaran los valores posteriormente se  obtiene la cantidad de tiempo que el paquete
    estuvo en el punto de control. Se calcula el costo que este tiempo implica. Se aplican las  respectivas
    modificaciones(costo, enTurno, finalizado). Si el punto de control actual es el ultimo punto de control
    se modifica la tabla PaqueteAsignadoRuta y se estabelce en TRUE el campo EnDestino. De lo contrario se
    busca el siguiente punto de control del paquete y se le asigna (fechaIngreso, enTurno) y se actualiza,
    ademas se modifica el punto de control actual del paquete de la tabla PaqueteAsignadoRuta.
    */
    private void botonProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonProcesarActionPerformed
        try{
            paquetePasaPuntoDeControl = listadoPaquetes.get(0);
            if(manejadorDB.verificarColaPuntoDeControlSiguiente(paquetePasaPuntoDeControl)){
                this.mostrarReceptorTiempo();
                costo = manejadorCalculos.calcularCostoPaqueteEnPuntoDeControl(tiempo, paquetePasaPuntoDeControl.getTarifaOperacion());
                paquetePasaPuntoDeControl.setEnTurno(false);
                paquetePasaPuntoDeControl.setFinalizado(true);
                paquetePasaPuntoDeControl.setCosto(costo);
                paquetePasaPuntoDeControl.setCantidadHoras(manejadorCalculos.getHorasTotales());
                mensaje = manejadorDB.actualizarPuntoDeControlPaquete(paquetePasaPuntoDeControl);
                if(puntoDeControl.isUltimoPuntoDeControl()){
                    manejadorDB.modificarPaqueteAsignadoRuta(paquetePasaPuntoDeControl, 0);
                }
                else{
                    paquetePasaPuntoDeControl = manejadorDB.obtenerPuntoDeControlPaqueteSiguiente(paquetePasaPuntoDeControl);
                    fechaIngreso = manejadorFechas.obtenerFechaActual();
                    paquetePasaPuntoDeControl.setEnTurno(true);
                    paquetePasaPuntoDeControl.setFechaIngreso(fechaIngreso);
                    manejadorDB.actualizarPuntoDeControlPaquete(paquetePasaPuntoDeControl);
                    manejadorDB.modificarPaqueteAsignadoRuta(paquetePasaPuntoDeControl, 1);
                }
                this.finalizarAccion(mensaje);
            }
            else{
                alertaTabla.setText("No se puede procesar. La cola del siguiente punto de control se encuentra llena");
                manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
            }
        }
        catch(IndexOutOfBoundsException e){
            alertaTabla.setText("No hay ningun paquete para procesar actualmente");
            manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
        }
    }//GEN-LAST:event_botonProcesarActionPerformed

    /*
    Metodo encargado de lanzar un mensaje de alerta al presionar algun item de la tabla.
    */
    private void tablaPaquetesEnPuntoDeControlMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPaquetesEnPuntoDeControlMousePressed
        alertaTabla.setText("La seleccion del paquete es automatica. Presione PROCESAR");
        manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
    }//GEN-LAST:event_tablaPaquetesEnPuntoDeControlMousePressed
    /*
    Metodo encargado de obtener la cantidad de tiempo que tardo un paquete en determinado punto de
    control. Valida que el campo de texto no se encuentre vacio y que la hora ingresada sea valida.
    Almacena el valor obtenido en la variable tiempo.
    */
    private void botonAceptarTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarTiempoActionPerformed
        if(textoTiempo.getText().equals("  :  :  ")){
            etiquetaAlertaTiempo.setText("Ingrese la cantidad de horas");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTiempo);
        }
        if(!textoTiempo.isEditValid()){
            etiquetaAlertaTiempo.setText("Tiempo ingresado no valido");
            manejadorHilos.limpiarEtiquetaAlerta(etiquetaAlertaTiempo);
        }
        else{
            tiempo = textoTiempo.getText();
            ReceptorTiempo.setVisible(false);
        }
    }//GEN-LAST:event_botonAceptarTiempoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MostradorMensajes;
    private javax.swing.JDialog ReceptorTiempo;
    private javax.swing.JLabel alertaTabla;
    private rojerusan.RSButtonIconI botonAceptarMensaje;
    private rojerusan.RSButtonIconI botonAceptarTiempo;
    private rojerusan.RSButtonIconI botonProcesar;
    private rojerusan.RSButtonIconI botonRegresar;
    private javax.swing.JLabel etiquetaAlertaTiempo;
    private javax.swing.JLabel etiquetaMensaje;
    private javax.swing.JLabel etiquetaRutaActual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaPaquetesEnPuntoDeControl;
    private javax.swing.JFormattedTextField textoTiempo;
    private javax.swing.JLabel titulo1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
