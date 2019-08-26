package Frontend.Administrador;
import Backend.Cliente;
import Backend.ManejadorBusqueda;
import Backend.ManejadorCalculos;
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
public class ReporteDeClientes extends javax.swing.JDialog {

    private List<Cliente> listadoClientes;
    private ObservableList<Cliente> observableListClientes;
    private List<Paquete> listadoPaquetes;
    private ObservableList<Paquete> observableListPaquetes;
    private ManejadorDBSM manejadorDB;
    private ManejadorBusqueda manejadorBusqueda;
    private ManejadorHilos manejadorHilos;
    private ManejadorCalculos manejadorCalculos;
    private String patronBusqueda;
    private Cliente cliente;
    private Paquete paquete;
    private double costo;
    private double ganancia;
    private double ingreso;
    
    public ReporteDeClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.listadoClientes = new ArrayList<>();
        this.observableListClientes = ObservableCollections.observableList(listadoClientes);
        this.listadoPaquetes = new ArrayList<>();
        this.observableListPaquetes = ObservableCollections.observableList(listadoPaquetes);
        this.manejadorDB = new ManejadorDBSM();
        this.manejadorBusqueda = new ManejadorBusqueda(); 
        this.manejadorHilos = new ManejadorHilos();
        this.manejadorCalculos = new ManejadorCalculos();
        initComponents();
    }
    
    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Cliente> getObservableList() {
        return observableListClientes;
    }

    public void setObservableList(ObservableList<Cliente> observableList) {
        this.observableListClientes = observableList;
    }
    
    public void llenarTablaClientes(List listado){
        this.observableListClientes.clear();
        this.observableListClientes.addAll(listado);
    }
    
    public void obtenerClientes(int tipo){
        if(tipo == 0){
            listadoClientes = manejadorDB.obtenerListadoClientes("SELECT* FROM Cliente ORDER BY DPI LIMIT 45;");
        }
        else{
            listadoClientes = manejadorDB.obtenerListadoClientes("SELECT* FROM Cliente ORDER BY DPI;");
        }
        this.llenarTablaClientes(listadoClientes);
    }

    //Metodos utilizados para la implementacion de Beans Beanding. ------------------
    public ObservableList<Paquete> getObservableListPaquetes() {
        return observableListPaquetes;
    }
   
    public void setObservableListPaquetes(ObservableList<Paquete> observableListPaquetes) {
        this.observableListPaquetes = observableListPaquetes;
    }
    
     public void llenarTablaPaquetes(List listado){
        this.observableListPaquetes.clear();
        this.observableListPaquetes.addAll(listado);
    }
    
    public void obtenerPaquetes(){
        listadoPaquetes = manejadorDB.obtenerListadoPaquetes("SELECT A.Codigo, A.Peso, A.Destino, A.DPICliente, A.PrecioTotal FROM Paquete A, PaqueteAsignadoRuta B WHERE A.DPICliente = '"+cliente.getDPI()+"' && B.Entregado = TRUE "
                                                                        + "&& A.Codigo = B.CodigoPaquete;");
        if(listadoPaquetes.isEmpty()){
            alertaTabla.setText("El cliente aun no tiene paquetes entregados");
            manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
        }
        this.llenarTablaPaquetes(listadoPaquetes);
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
        if(radioBotonDPI.isSelected()){
            listadoClientes = this.manejadorBusqueda.busquedaClientePorDPI(patronBusqueda);
        }
        this.llenarTablaClientes(listadoClientes);
    }
    
    //Metodo encargado de remover la seleccion de todas las casillas de seleccion
    public void inicializarCasillasSeleccion(){
       textoBusqueda.setText("");
       selectorMostrarTodosLosRegistros.setSelected(false);
       selectorFiltrado.clearSelection();
    }
    
    //Metodo encargado de establecer el texto por default de las etiquetas costo, ganancia e ingreso.
    public void inicializarEtiquetas(){
        etiquetaCosto.setText("Q");
        etiquetaGanancia.setText("Q");
        etiquetaIngreso.setText("Q");
    }
    
    /*
    Metodo encargado de inicializar el blanco la tabla
    */
    public void inicializarTablaPaquetes(){
        listadoPaquetes.clear();
        this.llenarTablaPaquetes(listadoPaquetes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        selectorFiltrado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        botonBuscar = new rojerusan.RSButtonIconI();
        selectorMostrarTodosLosRegistros = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBotonPrimerApellido = new javax.swing.JRadioButton();
        radioBotonDPI = new javax.swing.JRadioButton();
        radioBotonPrimerNombre = new javax.swing.JRadioButton();
        alertaTabla = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textoBusqueda = new rojeru_san.RSMTextFull();
        botonRegresar = new rojerusan.RSButtonIconI();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPaquetes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        etiquetaIngreso = new javax.swing.JLabel();
        etiquetaCosto = new javax.swing.JLabel();
        etiquetaGanancia = new javax.swing.JLabel();
        botonImprimir = new rojerusan.RSButtonIconI();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Clientes");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        tablaClientes.setBackground(new java.awt.Color(204, 204, 204));
        tablaClientes.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaClientes.setForeground(new java.awt.Color(0, 102, 153));
        tablaClientes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaClientes.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaClientes.setSelectionForeground(new java.awt.Color(204, 204, 204));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaClientes);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${DPI}"));
        columnBinding.setColumnName("DPI");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombres");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${apellido}"));
        columnBinding.setColumnName("Apellidos");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaClientesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        botonBuscar.setBackground(new java.awt.Color(204, 204, 204));
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/buscar.png"))); // NOI18N
        botonBuscar.setBorderPainted(false);
        botonBuscar.setColorHover(new java.awt.Color(153, 153, 153));
        botonBuscar.setEnabled(false);

        selectorMostrarTodosLosRegistros.setBackground(new java.awt.Color(204, 204, 204));
        selectorMostrarTodosLosRegistros.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        selectorMostrarTodosLosRegistros.setForeground(new java.awt.Color(0, 102, 153));
        selectorMostrarTodosLosRegistros.setText("Mostrar todos los registros");
        selectorMostrarTodosLosRegistros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectorMostrarTodosLosRegistrosItemStateChanged(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Filtrar Clientes por:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(radioBotonPrimerNombre)
                .addGap(18, 18, 18)
                .addComponent(radioBotonPrimerApellido)
                .addGap(18, 18, 18)
                .addComponent(radioBotonDPI)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioBotonPrimerApellido)
                    .addComponent(radioBotonPrimerNombre)
                    .addComponent(radioBotonDPI))
                .addContainerGap())
        );

        alertaTabla.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        alertaTabla.setForeground(new java.awt.Color(204, 0, 0));
        alertaTabla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        textoBusqueda.setForeground(new java.awt.Color(0, 102, 153));
        textoBusqueda.setBordeColorFocus(new java.awt.Color(51, 153, 0));
        textoBusqueda.setBotonColor(new java.awt.Color(255, 255, 255));
        textoBusqueda.setPlaceholder("Realizar Busqueda");
        textoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBusquedaKeyReleased(evt);
            }
        });

        botonRegresar.setBackground(new java.awt.Color(51, 153, 0));
        botonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/salir.png"))); // NOI18N
        botonRegresar.setText("REGRESAR");
        botonRegresar.setColorHover(new java.awt.Color(153, 153, 153));
        botonRegresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        tablaPaquetes.setBackground(new java.awt.Color(204, 204, 204));
        tablaPaquetes.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        tablaPaquetes.setForeground(new java.awt.Color(0, 102, 153));
        tablaPaquetes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaPaquetes.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tablaPaquetes.setSelectionForeground(new java.awt.Color(204, 204, 204));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListPaquetes}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPaquetes);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigo}"));
        columnBinding.setColumnName("Codigo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${destino}"));
        columnBinding.setColumnName("Destino");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${peso}"));
        columnBinding.setColumnName("Peso (lbs)");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tablaPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPaquetesMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPaquetes);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LISTADO DE CLIENTES");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LISTADO DE PAQUETES");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Ingreso:");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Costo:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Ganancia:");

        etiquetaIngreso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaIngreso.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaIngreso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaIngreso.setText("Q");

        etiquetaCosto.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaCosto.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaCosto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaCosto.setText("Q");

        etiquetaGanancia.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        etiquetaGanancia.setForeground(new java.awt.Color(0, 102, 153));
        etiquetaGanancia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaGanancia.setText("Q");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(etiquetaCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(etiquetaGanancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonImprimir.setBackground(new java.awt.Color(51, 153, 0));
        botonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Imagenes/impresora.png"))); // NOI18N
        botonImprimir.setText("IMPRIMIR");
        botonImprimir.setColorHover(new java.awt.Color(153, 153, 153));
        botonImprimir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(textoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(selectorMostrarTodosLosRegistros)
                        .addGap(12, 12, 12)
                        .addComponent(alertaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectorMostrarTodosLosRegistros)
                    .addComponent(alertaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /*
    Metodo encargado de mostrar solo 45 registros o todos los registros de la DB basado en el estado del boton
    de seleccion selectorMostrarTodosLosRegistros.
    */
    private void selectorMostrarTodosLosRegistrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selectorMostrarTodosLosRegistrosItemStateChanged
        this.inicializarEtiquetas();
        this.inicializarTablaPaquetes();
        if(selectorMostrarTodosLosRegistros.isSelected()){
            selectorFiltrado.clearSelection();
            textoBusqueda.setText("");
            this.obtenerClientes(1);
        }
        else{
            this.obtenerClientes(0);
        }
    }//GEN-LAST:event_selectorMostrarTodosLosRegistrosItemStateChanged
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonPrimerApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerApellidoActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonPrimerApellidoActionPerformed
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonDPIActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonDPIActionPerformed
    //Metodo encargado de limpiar de area de busqueda
    private void radioBotonPrimerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotonPrimerNombreActionPerformed
        textoBusqueda.setText("");
    }//GEN-LAST:event_radioBotonPrimerNombreActionPerformed
    /*
    Metodo encargado de obtener el texto ingresado en el area de texto textoBusqueda. Valida que se encuentre
    seleccionado un criterio de busqueda y posteriormente llama al metdodo establecerFiltroBusqueda.
    */
    private void textoBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBusquedaKeyReleased
        if(!radioBotonPrimerNombre.isSelected() && !radioBotonPrimerApellido.isSelected() && !radioBotonDPI.isSelected()){
            alertaTabla.setText("No se ha seleccionado un criterio de filtrado");
            manejadorHilos.limpiarEtiquetaAlerta(alertaTabla);
            textoBusqueda.setText("");
        }
        else{
            listadoPaquetes.clear();
            this.llenarTablaPaquetes(listadoPaquetes);
            this.inicializarEtiquetas();
            patronBusqueda = textoBusqueda.getText();
            if(patronBusqueda.equals("")){
                this.obtenerClientes(0);
            }
            else{
                this.establecerFiltroDeBusqueda();
            }
        }
    }//GEN-LAST:event_textoBusquedaKeyReleased
    /*
    Metodo encargado de ocultar el JDialog
    */
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed
    /*
    Metodo encargado de obtener el cliente seleccionado y llenar la tabla de paquetes 
    segun los paquetes entregados correspondientes al cliente en turno
    */
    private void tablaClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseReleased
        this.inicializarEtiquetas();
        cliente = listadoClientes.get(tablaClientes.getSelectedRow());
        this.obtenerPaquetes();
    }//GEN-LAST:event_tablaClientesMouseReleased

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        
    }//GEN-LAST:event_botonImprimirActionPerformed
    /*
    Metodo encargado de imprimir en las etiquetas de valores financieros el valor de economico 
    obtenido del paquete seleccionado. Obtiene el paquete seleccionado en la tabla.
    */
    private void tablaPaquetesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPaquetesMouseReleased
        paquete = listadoPaquetes.get(tablaPaquetes.getSelectedRow());
        ingreso = paquete.getPrecioTotal();
        costo = this.manejadorDB.obtenerCostoTotal("SELECT* FROM PaquetePasaPuntoDeControl WHERE CodigoPaquete = '"+paquete.getCodigo()+"';");
        ganancia = this.manejadorCalculos.obtenerGanancia(costo, ingreso);
        etiquetaIngreso.setText("Q" + ingreso);
        etiquetaCosto.setText("Q" + costo );
        etiquetaGanancia.setText("Q" + ganancia);
        
    }//GEN-LAST:event_tablaPaquetesMouseReleased

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ReporteDeClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteDeClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteDeClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteDeClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteDeClientes dialog = new ReporteDeClientes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaTabla;
    private rojerusan.RSButtonIconI botonBuscar;
    private rojerusan.RSButtonIconI botonImprimir;
    private rojerusan.RSButtonIconI botonRegresar;
    private javax.swing.JLabel etiquetaCosto;
    private javax.swing.JLabel etiquetaGanancia;
    private javax.swing.JLabel etiquetaIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton radioBotonDPI;
    private javax.swing.JRadioButton radioBotonPrimerApellido;
    private javax.swing.JRadioButton radioBotonPrimerNombre;
    private javax.swing.ButtonGroup selectorFiltrado;
    private javax.swing.JCheckBox selectorMostrarTodosLosRegistros;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaPaquetes;
    private rojeru_san.RSMTextFull textoBusqueda;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
