package Backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private boolean cerrarAplicacion;
    
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
    */
    @Override
    public void run(){
        //-------------------------------------------PASO 1-------------------------------------------
        if(this.conectarDB()){
            while(!cerrarAplicacion){
                System.out.println("Bodega");
            }
        }
    }    
    
    
}
