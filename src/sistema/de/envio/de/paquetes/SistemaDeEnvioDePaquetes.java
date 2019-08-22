package sistema.de.envio.de.paquetes;
import Backend.ManejadorBodega;
import Backend.ManejadorDBSM;
import Frontend.VentanaPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author helmuthluther
 */
public class SistemaDeEnvioDePaquetes {

    //Objetos e instancias utilizados por la clase main.
    private static ManejadorDBSM manejadorDB = new ManejadorDBSM();
    private static ManejadorBodega manejadorBodega = new ManejadorBodega();
    private static VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
    private static ResultSet resultado;
    
    /*
    Metodo encargado de llamar al metodo conectarDB de la clase ManejadorDBSM, recibe un booleano como respuesta.
    Si es false, se lanza un mensaje de error, de lo contario se realiza una consulta a la base de datos llamando 
    al metodo realizarConsulta. Si la tabla esta vacia se inicia el proceso de registro del primer usuario 
    administrador y se inicia la aplicacion, de lo contrario se solicita el inicio de sesion.
    */
    public static void main(String[] args) {
       /* String nombre;
        String apellido;
        String nombreUsuario;
        String contrasena = "123";
        String tipo = "Administrador";
        Usuario usuario;
        for(int i = 0; i < 101; i++){
            nombre = "Administrador " +i;
            apellido = "Usuario " + i;
            nombreUsuario = "Admin " +i;
            usuario = new Usuario(nombre, apellido, nombreUsuario, contrasena, tipo, true);
            manejadorDB.crearNuevoUsuario(usuario);
        }*/
       /*
       String nombre;
        String apellido;
        String nombreUsuario;
        String contrasena = "123";
        String tipo = "Operador";
        Usuario usuario;
        for(int i = 0; i < 101; i++){
            nombre = "Operador " +i;
            apellido = "Usuario " + i;
            nombreUsuario = "Operador " +i;
            usuario = new Usuario(nombre, apellido, nombreUsuario, contrasena, tipo, true);
            manejadorDB.crearNuevoUsuario(usuario);
        }*/
       manejadorBodega.setCerrarAplicacion(false);
       manejadorBodega.start();
       
        if(manejadorDB.conectarDB()){
            try {
                resultado = manejadorDB.realizarConsulta("SELECT* FROM Usuario");
                if(!resultado.next()){
                    ventanaPrincipal.setManejadorBodega(manejadorBodega);
                    ventanaPrincipal.crearPrimerUsuarioAdministrador();
                    manejadorDB.crearCodigosIniciales();
                    
                }
                else{
                    ventanaPrincipal.setManejadorBodega(manejadorBodega);
                    ventanaPrincipal.iniciarSesion(0);         
                }
            } 
            catch (SQLException ex) {
                ventanaPrincipal.lanzarMensaje("Error al conectar con la Base de Datos");
                manejadorBodega.setCerrarAplicacion(true);
                System.exit(0);
            }
        }
        else{
            ventanaPrincipal.lanzarMensaje("Error al conectar con la Base de Datos");
            manejadorBodega.setCerrarAplicacion(true);
            System.exit(0);
        }
    }
}
