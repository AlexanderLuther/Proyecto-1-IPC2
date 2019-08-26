package Backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
/**
 *
 * @author helmuthluther
 */
public class ManejadorBodega extends Thread{
    
    //Constantes de la clase.
    private final String USUARIO = "root";
    private final String CONTRASENA = "Xela0806.";
    private final String URL = "jdbc:mysql://localhost:3306/SistemaEnvioDePaquetes";
    //Variables de la clase
    private Connection conexion = null;
    private Statement declaracion;
    private ResultSet resultadoDestino;
    private ResultSet resultadoPaquetesPorDestino;
    private ResultSet resultadoRutasActivasPorDestino;
    private ResultSet resultadoPrimerPuntoDeControl;
    private ResultSet resultadoPaquetePasaPuntoDeControl;
    private ResultSet resultadoPuntoDeControl;
    private ResultSet resultadoPaquete;
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private ManejadorDBSM manejadorDB = new ManejadorDBSM();
    private boolean cerrarAplicacion;
    private String destino;
    private String codigoPaquete;
    private String codigoRuta;
    private int codigoPuntoDeControl;
    private int cantidadPaquetesCola;
    private int cantidadPaquetesColaActualmente;
    private int contador;
    private PuntoDeControl puntoDeControl;
    private Tarifa tarifa;
    private boolean puntoDeControlEnTurno;
    private double tarifaOperacion;
    private Timestamp fechaActual;
    private boolean prioridad;
    
    
    /*
    Metodo encargado de establecer la conexion con la Base de Datos.
    */
    private boolean conectarDB(){
        try{
            conexion =  DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conectado " + conexion.getCatalog());
        }
        catch(SQLException e){
            return false;
        }
        return true;
    } 
    
    /*
    Metodo encargado de establecer el valor de la variable cerrarAplicacion
    */
    public void setCerrarAplicacion(boolean cerrarAplicacion){
        this.cerrarAplicacion = cerrarAplicacion;      
    }
    
    /*
    Metodo encargado de la asignacion de ruta a un paquete ingresado.
        1. Se llama al metodo conectarDB para crear una conexion con la base de datos. Se obtiene un valor booleano,
           true significa que se establecio la conexion exitosamrente y se procede a ejecutar el bucle de asignacion
           de paquetes a rutas y puntos de control, de lo contrario no se realiza ninguna accion.
        2. Se obtienen todos los destinos a los cuales se deben enviar los paquetes
        3. Por cada destino, se obtiene la lista de paquetes a asignar ruta ordenada segun dos criterios:
                A. Prioridad (1 o 0) de forma descendente para ingresar primero los paquetes priorizados.
                B. Fecha de ingreso de forma ascendente para seguir el concepto de cola. El primer paquete 
                   que entra es el primero que sale.
        4. Se buscan todas las rutas que tengan un destino con valor igual al contenido dentro de la variable
           destino.  
        5. Se busca el primer punto de control de la ruta que tenga un valor igual al contenido dentro de la
           variable codigoRuta y se obtiene la cantidad de paquetes que pueden almacenar en cola. El valor 
           obtenido se almacena en la variable cantidadPaquetesCola.
           Se obtiene la cantidad de paquetes que hay actualmente en la cola del punto de control actual
           y se almacena en la variable cantidadPaquetesColaActualmente.
        6. Si la cantidad de paquetes actual en la cola es menor a la cantidad maxima de paquetes en cola
           del punto de control correspondiente a la ruta en turno, se asigna esta ruta y todos sus 
           puntos de control al paquete en turno. Por ultimo se remueve de bodega el paquete en turno.
    */
    @Override
    public void run(){
        //-------------------------------------------PASO 1-------------------------------------------
        if(this.conectarDB()){
            while(!cerrarAplicacion){
                try {
        //-------------------------------------------PASO 2-------------------------------------------                        
                    declaracion = conexion.createStatement();
                    resultadoDestino = declaracion.executeQuery("SELECT DISTINCT Destino FROM Bodega;");
                    while(resultadoDestino.next()){
                        destino = resultadoDestino.getString("Destino");
                        System.out.println("\n"+destino);                   
        //-------------------------------------------PASO 3-------------------------------------------        
                        declaracion = conexion.createStatement();
                        resultadoPaquetesPorDestino = declaracion.executeQuery("SELECT* FROM Bodega WHERE Destino = '"+destino+"' ORDER BY Prioridad DESC, FechaIngreso ASC;");
                        while(resultadoPaquetesPorDestino.next()){
                            System.out.println(resultadoPaquetesPorDestino.getString("Prioridad") + " Fecha de Ingreso: " + resultadoPaquetesPorDestino.getString("FechaIngreso"));
                            codigoPaquete = resultadoPaquetesPorDestino.getString("CodigoPaquete");
        //-------------------------------------------PASO 4-------------------------------------------        
                            declaracion = conexion.createStatement();
                            resultadoRutasActivasPorDestino = declaracion.executeQuery("SELECT* FROM Ruta WHERE Destino = '"+destino+"' && Activa = TRUE");
                            while(resultadoRutasActivasPorDestino.next()){
                                System.out.println(resultadoRutasActivasPorDestino.getString("Nombre"));
                                codigoRuta = resultadoRutasActivasPorDestino.getString("Codigo");
        //-------------------------------------------PASO 5-------------------------------------------        
                                declaracion = conexion.createStatement();
                                resultadoPrimerPuntoDeControl = declaracion.executeQuery("SELECT* FROM PuntoDeControl WHERE CodigoRuta = '"+codigoRuta+"' ORDER BY Codigo ASC LIMIT 1;");
                                while(resultadoPrimerPuntoDeControl.next()){
                                    System.out.println("Numero de punto de control: " + resultadoPrimerPuntoDeControl.getInt("Codigo"));
                                    cantidadPaquetesCola = resultadoPrimerPuntoDeControl.getInt("CantidadPaquetesCola");
                                    codigoPuntoDeControl = resultadoPrimerPuntoDeControl.getInt("Codigo");
                                    System.out.println("Cantidad de paquetes en cola: " + cantidadPaquetesCola + "\n");
                                }
                                declaracion = conexion.createStatement();
                                resultadoPaquetePasaPuntoDeControl = declaracion.executeQuery("SELECT COUNT(*) FROM PaquetePasaPuntoDeControl WHERE CodigoRuta = '"+codigoRuta+"'  && CodigoPuntoDeControl = '"+codigoPuntoDeControl+"' && EnTurno = TRUE;");
                                while(resultadoPaquetePasaPuntoDeControl.next()){
                                     cantidadPaquetesColaActualmente = resultadoPaquetePasaPuntoDeControl.getInt("COUNT(*)");
                                }
        //-------------------------------------------PASO 6-------------------------------------------                          
                                if(cantidadPaquetesColaActualmente < cantidadPaquetesCola){
                                    this.registrarRutaPaquete();
                                    this.registrarPuntosdeControlPaquete();
                                    declaracion = conexion.createStatement();
                                    declaracion.executeUpdate("DELETE FROM Bodega WHERE CodigoPaquete = '"+codigoPaquete+"'");
                                    //GUARDAR EN ESTA RUTA
                                    break;
                                }
                                else{
                                    System.out.println("Punto de control lleno");
                                    //NO SE PUEDE ASIGNAR A ESTA RUTA, EL PUNTO DE CONTROL ESTA LLENO.                                   
                                }
                            }
                        }
                    }
                    
                }
                catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }  
    
    /*
    Metodo encargado de ingresar en la base de datos una nueva asignacion de ruta a un paquete.
    */
    private void registrarRutaPaquete() throws SQLException{
        fechaActual = manejadorFechas.obtenerFechaActual();
        declaracion = conexion.createStatement();
        declaracion.executeUpdate("INSERT INTO PaqueteAsignadoRuta (CodigoPaquete, CodigoRuta, PuntoDeControlActual, EnDestino, Entregado, FechaAsignacion) VALUES('"+codigoPaquete+"', '"+codigoRuta+"', '1', FALSE, FALSE, '"+fechaActual+"');");   
    }
    
    /*
    Metodo encargado de ingresar en la base de datos una nueva asignacion de puntos de control a un paquete.
    Recupera todos los puntos de control de la ruta indicada y crear una nueva instancia de la clase PuntoDeControl,
    por cada tupla obtenida, posteriormente realiza la asignacion de puntos de control al paquete en turno
    */
    private void registrarPuntosdeControlPaquete() throws SQLException{
        contador = 1;
        declaracion = conexion.createStatement();
        resultadoPaquete = declaracion.executeQuery("SELECT* FROM Paquete WHERE Codigo = '"+codigoPaquete+"';");
        while(resultadoPaquete.next()){
            prioridad = resultadoPaquete.getBoolean("Prioridad");
        }    
        declaracion = conexion.createStatement();
        resultadoPuntoDeControl = declaracion.executeQuery("SELECT* FROM PuntoDeControl WHERE CodigoRuta = '"+codigoRuta+"' ORDER BY Codigo ASC;");
        while(resultadoPuntoDeControl.next()){
            puntoDeControl = new PuntoDeControl(resultadoPuntoDeControl.getInt("Codigo"), resultadoPuntoDeControl.getString("CodigoRuta"), resultadoPuntoDeControl.getString("Nombre"),
                            resultadoPuntoDeControl.getDouble("TarifaOperacion"), resultadoPuntoDeControl.getInt("CantidadPaquetesCola"), resultadoPuntoDeControl.getString("OperadorAsignado"),
                            resultadoPuntoDeControl.getBoolean("UltimoPuntoDeControl"), resultadoPuntoDeControl.getBoolean("TarifaOperacionPropia"));
     
            if(contador == 1){
                puntoDeControlEnTurno = true;
                contador++;
            }
            else{
                puntoDeControlEnTurno = false;
            }
            
            if(puntoDeControl.isTarifaOperacionPropia()){
                tarifaOperacion = puntoDeControl.getTarifaOperacion();
            }
            else{
                tarifa = manejadorDB.recuperarTarifas();
                tarifaOperacion = tarifa.getTarifaOperacionGlobal();
            }
            fechaActual = manejadorFechas.obtenerFechaActual();
            System.out.println(fechaActual);
            
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("INSERT INTO PaquetePasaPuntoDeControl (CodigoPaquete, CodigoRuta, CodigoPuntoDeControl, TarifaOperacion, CantidadHoras, Costo, Finalizado, EnTurno, FechaIngreso, Prioridad)"
                    + " VALUES('"+codigoPaquete+"', '"+puntoDeControl.getCodigoRuta()+"', '"+puntoDeControl.getCodigo()+"', '"+tarifaOperacion+"', '0', '0', FALSE, "+puntoDeControlEnTurno+", '"+fechaActual+"', "+prioridad+");");
        }
    }
}
