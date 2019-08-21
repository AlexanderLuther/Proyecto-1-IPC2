package Backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author helmuthluther
 */
public class ManejadorDBSM {

    //Constantes de la clase.
    private final String USUARIO = "root";
    private final String CONTRASENA = "Xela0806.";
    private final String URL = "jdbc:mysql://localhost:3306/SistemaEnvioDePaquetes";
    private final String ADMINISTRADOR = "Administrador";
    private final String OPERADOR = "Operador";
    private final String RECEPCIONISTA = "Recepcionista";
    
    
//Variables e instancias de la clase
    private Usuario usuario;
    private Connection conexion = null;
    private Statement declaracion;
    private PreparedStatement declaracionSegura;
    private ResultSet resultado;
    private String tipoUsuarioActual;
    private List listado;
    private List listadoAcotado;
    private Tarifa tarifa;
    private String codigoRuta;  
    private String codigoPaquete;
    private Ruta ruta;
    private PuntoDeControl puntoDeControl;
    private Cliente cliente;
    /*
    Metodo encargado de establecer la conexion con la Base de Datos.
    */
    public boolean conectarDB(){
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
    Metodo encargado de realizar consultas en la base de datos. Ejecuta el codigoSQL que recibe como parametro
    y devuelve la respuesta de la base de datos.
    */
    public ResultSet realizarConsulta(String codigoSQL){
        try {
          
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(codigoSQL);
        } catch (SQLException ex) {
            System.out.println("Error de conexion con la base de datos");
        }
        return resultado;
    }
    
        
    public List obtenerListadoUsuarios(String codigoSQL, int tipo){
        try {
            this.conectarDB();
            listado = new ArrayList<>();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(codigoSQL);
            while(resultado.next()){
                usuario = new Usuario(resultado.getString("Nombre"), resultado.getString("Apellido"), resultado.getString("NombreUsuario"), resultado.getString("Contrasena"), resultado.getString("Tipo"), resultado.getBoolean("Activo"));
                listado.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con la base de datos");
        }
        if(tipo == 0){
            if (listado.size() > 50){
                listadoAcotado = new ArrayList<>();
                for(int i = 0; i < 45; i++){
                    listadoAcotado.add(listado.get(i));
                }
                return listadoAcotado;
            }
        }
        return listado;
    }
    
    
    public List obtenerListadoRutas(String codigoSQL, int tipo){
        try {
            this.conectarDB();
            listado = new ArrayList<>();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(codigoSQL);
            while(resultado.next()){
                if(codigoSQL.contains("DISTINCT")){
                    ruta = new Ruta("", "", resultado.getString("Destino"), true);
                }
                else{
                    ruta = new Ruta(resultado.getString("Codigo"), resultado.getString("Nombre"), resultado.getString("Destino"), resultado.getBoolean("Activa"));
                }          
                listado.add(ruta);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con la base de datos");
        }
        if(tipo == 0){
            if (listado.size() > 50){
                listadoAcotado = new ArrayList<>();
                for(int i = 0; i < 45; i++){
                    listadoAcotado.add(listado.get(i));
                }
                return listadoAcotado;
            }
        }
        return listado;
    }
    
    
    public List obtenerListadoPuntosDeControl(String codigoSQL){
        try {
            this.conectarDB();
            listado = new ArrayList<>();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(codigoSQL);
            while(resultado.next()){
                puntoDeControl = new PuntoDeControl(resultado.getInt("Codigo"), resultado.getString("CodigoRuta"), resultado.getString("Nombre"), resultado.getDouble("TarifaOperacion"),
                resultado.getInt("CantidadPaquetesCola"), resultado.getString("OperadorAsignado"), resultado.getBoolean("UltimoPuntoDeControl"), resultado.getBoolean("TarifaOperacionPropia"));
                listado.add(puntoDeControl);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con la base de datos");
        }
        return listado;
    }
    
    public List obtenerListadoClientes(String codigoSQL, int tipo){
        try {
            this.conectarDB();
            listado = new ArrayList<>();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery(codigoSQL);
            while(resultado.next()){
                cliente = new Cliente(resultado.getString("Nombre"), resultado.getString("Apellido"), resultado.getString("Direccion"), resultado.getString("DPI"), resultado.getString("NIT"));
                listado.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con la base de datos");
        }
        if(tipo == 0){
            if (listado.size() > 50){
                listadoAcotado = new ArrayList<>();
                for(int i = 0; i < 45; i++){
                    listadoAcotado.add(listado.get(i));
                }
                return listadoAcotado;
            }
        }
        return listado;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//-------------------------------------------------------------USUARIO----------------------------------------------------------------------------------------------------------    
    /*
    Metodo encargado de crear un nuevo registro en la base de datos correspondiente a un nuevo usuario.
    */
    public String crearNuevoUsuario(Usuario usuario){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO Usuario (Nombre, Apellido, NombreUsuario, Contrasena, Tipo, Activo) VALUES(?, ?, ?, ?, ?, ?);");
            declaracionSegura.setString(1, usuario.getNombres());
            declaracionSegura.setString(2, usuario.getApellidos());
            declaracionSegura.setString(3, usuario.getNombreUsuario());
            declaracionSegura.setString(4, usuario.getContrasena());
            declaracionSegura.setString(5, usuario.getTipo());
            declaracionSegura.setBoolean(6, usuario.isActivo());
            declaracionSegura.executeUpdate();
            
            declaracionSegura = conexion.prepareStatement("INSERT INTO "+usuario.getTipo()+" (Nombre, Apellido, NombreUsuario) VALUES(?,?,?);");
            declaracionSegura.setString(1, usuario.getNombres());
            declaracionSegura.setString(2, usuario.getApellidos());
            declaracionSegura.setString(3, usuario.getNombreUsuario());
            declaracionSegura.executeUpdate();
        }   
        catch (SQLIntegrityConstraintViolationException e){
            return "El nombre de usuario ya se encuentra registrado en el sistema";
        }
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Usuario " + usuario.getNombreUsuario() + " registrado exitosamente";
    }
    
    /*
    Metodo encargado de actualizar un registro en la base de datos correspondiente a un usuario.
    */
      public String modificarUsuario(Usuario usuario){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("UPDATE Usuario SET Nombre = ?, Apellido = ?, Contrasena = ?, Activo = ? WHERE NombreUsuario = '"+usuario.getNombreUsuario()+"';");
            declaracionSegura.setString(1, usuario.getNombres());
            declaracionSegura.setString(2, usuario.getApellidos());
            declaracionSegura.setString(3, usuario.getContrasena());
            declaracionSegura.setBoolean(4, usuario.isActivo());
            declaracionSegura.executeUpdate();
            
            declaracionSegura = conexion.prepareStatement("UPDATE "+usuario.getTipo()+" SET Nombre = ?, Apellido = ? WHERE NombreUsuario = '"+usuario.getNombreUsuario()+"';");
            declaracionSegura.setString(1, usuario.getNombres());
            declaracionSegura.setString(2, usuario.getApellidos());
            declaracionSegura.executeUpdate();
        }   
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Usuario " + usuario.getNombreUsuario() + " modificado exitosamente";
    }
    
     /*
    Metodo encargado de eliminar un registro en la base de datos correspondiente a un usuario.
    */
      public String eliminarUsuario(Usuario usuario){
        try {
            conectarDB();
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("DELETE FROM "+usuario.getTipo() +" WHERE NombreUsuario = '"+usuario.getNombreUsuario() +"';");
            
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("DELETE FROM Usuario WHERE NombreUsuario = '"+usuario.getNombreUsuario() +"';");
            }
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Usuario " + usuario.getNombreUsuario() + " eliminado exitosamente";
    }  
      
    /*
    Metodo encargado de validar el inicio de sesion de algun usuario dentro del sistema. Recibe como parametros
    el nombre de usuario y la contrasena. Valida que el nombre de usuario este registrado en el sistema y luego
    valida que la contrasena sea correcta. Devuelve un String con un mensaje correspondiente a las validaciones 
    aceptadas.
    */
    public String validarLogin(String nombreUsuario, String contrasena){
        this.conectarDB();
        try {
            declaracionSegura = conexion.prepareStatement("SELECT* FROM Usuario WHERE NombreUsuario = ?;");
            declaracionSegura.setString(1, nombreUsuario);
            resultado = declaracionSegura.executeQuery();
            if(!resultado.next()){
                    return "Nombre de usuario no registrado en el sistema";
            }
            else{
                usuario = new Usuario(resultado.getString("Nombre"), resultado.getString("Apellido"), resultado.getString("NombreUsuario"), resultado.getString("Contrasena"), resultado.getString("Tipo"), resultado.getBoolean("Activo"));
                if(usuario.getContrasena().equals(contrasena)){
                    if(usuario.isActivo()){
                        tipoUsuarioActual = usuario.getTipo();
                        return "Iniciar Sesion";
                    }
                    else{
                        return "El usuario se encuentra desactivado";
                    }
                }
                else{
                    return "Contraseña incorrecta";
                }
            }   
        }
        catch (SQLException ex) {
            return "Error de Comunicacion con la base de Datos";
        }
    }  
      
    /*
    Metodo encargado de devolver el valor contenido dentro de la variable tipoUsuarioActual.
    */
    public String obtenerTipoUsuario(){
        return this.tipoUsuarioActual;
    }  
    
    
    
//-------------------------------------------------------------TARIFA----------------------------------------------------------------------------------------------------------       
    /*
    Metodo encargado de crear o actualizar la tabla Tarifa     
    */
    public void establecerTarifas(Tarifa tarifa, boolean primerInicio){
        try {
            this.conectarDB();            
            if(primerInicio){
                declaracionSegura = conexion.prepareStatement("INSERT INTO Tarifa (TarifaOperacionGlobal, PrecioLibraGlobal, CuotaPriorizacionGlobal, CuotaDestinoGlobal) VALUES(?,?,?, ?);");
                declaracionSegura.setDouble(1, tarifa.getTarifaOperacionGlobal());
                declaracionSegura.setDouble(2, tarifa.getPrecioLibraGlobal());
                declaracionSegura.setDouble(3, tarifa.getCuotaPriorizacionGlobal());
                declaracionSegura.setDouble(4, tarifa.getCuotaDestinoGlobal());
                declaracionSegura.executeUpdate();
            }
            else{
                declaracionSegura = conexion.prepareStatement("UPDATE Tarifa SET TarifaOperacionGlobal = ?, PrecioLibraGlobal = ?, CuotaPriorizacionGlobal = ?, CuotaDestinoGlobal =?;");
                declaracionSegura.setDouble(1, tarifa.getTarifaOperacionGlobal());
                declaracionSegura.setDouble(2, tarifa.getPrecioLibraGlobal());
                declaracionSegura.setDouble(3, tarifa.getCuotaPriorizacionGlobal());
                declaracionSegura.setDouble(4, tarifa.getCuotaDestinoGlobal());
                declaracionSegura.executeUpdate();
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
    } 
    
    /*
    Metodo encargado de realizar una consulta en la tabla Tarifa y almacenar los datos recuperados en una 
    instancia del tipo tarifa. Se devuelve la tarifa creada.
    */
    public Tarifa recuperarTarifas(){
        try {
            this.conectarDB();            
            declaracionSegura = conexion.prepareStatement("SELECT* FROM Tarifa;");
            resultado = declaracionSegura.executeQuery();
            while(resultado.next()){
                tarifa = new Tarifa(resultado.getDouble("TarifaOperacionGlobal"), resultado.getDouble("PrecioLibraGlobal"), resultado.getDouble("CuotaPriorizacionGlobal"), resultado.getDouble("CuotaDestinoGlobal"));
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex);
        }
        return tarifa;
    } 
    
    //-------------------------------------------------------------RUTA----------------------------------------------------------------------------------------------------------  
    /*
    Metodo encargado de crear un nuevo registro en la base de datos correspondiente a una nueva ruta.
    */
    public String crearNuevaRuta(Ruta ruta){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO Ruta (Codigo, Nombre, Destino, Activa) VALUES(?, ?, ?, ?);");
            declaracionSegura.setString(1, ruta.getCodigo());
            declaracionSegura.setString(2, ruta.getNombre());
            declaracionSegura.setString(3, ruta.getDestino());
            declaracionSegura.setBoolean(4, ruta.isActiva());
            declaracionSegura.executeUpdate();
            this.actualizarCodigoRuta(ruta.getCodigo());
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Ruta con destino a " + ruta.getDestino()+ " registrada exitosamente";
    }
    
    /*
    Metodo encargado de actualizar un registro en la base de datos correspondiente a una ruta.
    */
    public String modificarRuta(Ruta ruta){
        try {
            this.conectarDB();
            if(ruta.isActiva()){
                declaracionSegura = conexion.prepareStatement("UPDATE Ruta SET Nombre = ?, Activa = ? WHERE Codigo = '"+ruta.getCodigo()+"';");
                declaracionSegura.setString(1, ruta.getNombre());
                declaracionSegura.setBoolean(2, ruta.isActiva());
                declaracionSegura.executeUpdate();
            }
            else{
                declaracion = conexion.createStatement();
                resultado = declaracion.executeQuery("SELECT COUNT(*) FROM PaqueteAsignadoRuta WHERE CodigoRuta = '"+ruta.getCodigo()+"' && EnDestino = FALSE;");
                while(resultado.next()){
                    if(resultado.getInt("COUNT(*)") == 0){
                        declaracionSegura = conexion.prepareStatement("UPDATE Ruta SET Nombre = ?, Activa = ? WHERE Codigo = '"+ruta.getCodigo()+"';");
                        declaracionSegura.setString(1, ruta.getNombre());
                        declaracionSegura.setBoolean(2, ruta.isActiva());
                        declaracionSegura.executeUpdate();
                    }
                    else{
                        return "No se puede desactivar. Hay paquetes actualmente en la ruta";
                    }
                }
            }
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Ruta " + ruta.getNombre()+ " modificada exitosamente";
    }
    
    
    //-------------------------------------------------------------CODIGO----------------------------------------------------------------------------------------------------------
    public void crearCodigosIniciales(){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("INSERT INTO Codigo (CodigoPaquete, CodigoRuta) VALUES ('AAA-000','AAA-000');");
        } 
        catch (SQLException ex) {
            System.out.println("Error");
        }
    }
    
    
    public String obtenerCodigoRuta(){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery("SELECT* FROM Codigo;");
             while(resultado.next()){
                 codigoRuta = resultado.getString("CodigoRuta");
             }
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return codigoRuta;
    }
    
    public void actualizarCodigoRuta(String codigo){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("UPDATE Codigo SET CodigoRuta = '"+codigo+"';");
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
    }
    
    public String obtenerCodigoPaquete(){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            resultado = declaracion.executeQuery("SELECT* FROM Codigo;");
             while(resultado.next()){
                 codigoPaquete = resultado.getString("CodigoPaquete");
             }
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return codigoPaquete;
    }
    
     public void actualizarCodigoPaquete(String codigo){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("UPDATE Codigo SET CodigoPaquete = '"+codigo+"';");
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
    }
      //-------------------------------------------------------------PUNTO DE CONTROL----------------------------------------------------------------------------------------------------------
    
    /*
    Metodo encargado de crear un nuevo registro en la base de datos correspondiente a una nueva ruta.
    */
    public String crearNuevoPuntoDeControl(PuntoDeControl puntoDeControl){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO PuntoDeControl (Codigo, CodigoRuta, Nombre, TarifaOperacion, CantidadPaquetesCola, OperadorAsignado, UltimoPuntoDeControl, TarifaOperacionPropia) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            declaracionSegura.setInt(1, puntoDeControl.getCodigo());
            declaracionSegura.setString(2, puntoDeControl.getCodigoRuta());
            declaracionSegura.setString(3, puntoDeControl.getNombre());
            declaracionSegura.setDouble(4, puntoDeControl.getTarifaOperacion());
            declaracionSegura.setInt(5, puntoDeControl.getCantidadPaquetesCola());
            declaracionSegura.setString(6, puntoDeControl.getOperadorAsignado());
            declaracionSegura.setBoolean(7, puntoDeControl.isUltimoPuntoDeControl());
            declaracionSegura.setBoolean(8, puntoDeControl.isTarifaOperacionPropia());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        if(puntoDeControl.getCodigo() == 1){
            return puntoDeControl.getCodigo() + " Punto de control exitosamente";
        }
        
        else{
            return puntoDeControl.getCodigo() + " Puntos de control exitosamente";
        }
    }
    
     /*
    Metodo encargado de actualizar un registro en la base de datos correspondiente a una ruta.
    */
    public boolean consultarModificacionPuntoDeControl(Ruta ruta, PuntoDeControl puntodeControl , int tipo){
        try {
            this.conectarDB();
            if(tipo == 0){
                declaracion = conexion.createStatement();
                resultado = declaracion.executeQuery("SELECT COUNT(*) FROM PaqueteAsignadoRuta WHERE CodigoRuta = '"+ruta.getCodigo()+"' && EnDestino = FALSE;");
                while(resultado.next()){
                    return resultado.getInt("COUNT(*)") == 0;
                }
            }
            else{
                declaracion = conexion.createStatement();
                resultado = declaracion.executeQuery("SELECT COUNT(*) FROM PaquetePasaPuntoDeControl WHERE CodigoRuta = '"+ruta.getCodigo()+"' && CodigoPuntoDeControl = '"+puntodeControl.getCodigo()+"' && EnTurno = TRUE;");
                while(resultado.next()){
                    return resultado.getInt("COUNT(*)") == 0;
                }
            }
        }    
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return true;
    }
    
    public String eliminarPuntoDeControl(PuntoDeControl puntodeControl){
        try {
            this.conectarDB();
            declaracion = conexion.createStatement();
            declaracion.executeUpdate("DELETE FROM PuntoDeControl WHERE CodigoRuta = '"+puntodeControl.getCodigoRuta()+"' && Codigo = '"+puntodeControl.getCodigo()+"';");
        }    
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return "Punto de control eliminado exitosamente";
    }
    
    public String modificarPuntoDeControl(PuntoDeControl puntoDeControl){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("UPDATE PuntoDeControl SET Nombre = ?, TarifaOperacion = ?, CantidadPaquetesCola = ?, OperadorAsignado = ?, UltimoPuntoDeControl = ?, TarifaOperacionPropia = ?"
                                                        + " WHERE Codigo = '"+puntoDeControl.getCodigo()+"' && CodigoRuta = '"+puntoDeControl.getCodigoRuta()+"';");
            declaracionSegura.setString(1, puntoDeControl.getNombre());
            declaracionSegura.setDouble(2, puntoDeControl.getTarifaOperacion());
            declaracionSegura.setInt(3, puntoDeControl.getCantidadPaquetesCola());
            declaracionSegura.setString(4, puntoDeControl.getOperadorAsignado());
            declaracionSegura.setBoolean(5, puntoDeControl.isUltimoPuntoDeControl());
            declaracionSegura.setBoolean(6, puntoDeControl.isTarifaOperacionPropia());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Punto de control " + puntoDeControl.getNombre() + " modificado exitosamente";
    }
    
    //-------------------------------------------------------------CLIENTE----------------------------------------------------------------------------------------------------------
 
    public String crearNuevoCliente(Cliente cliente){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO Cliente (NIT, DPI, Nombre, Apellido, Direccion) VALUES(?, ?, ?, ?, ?);");
            declaracionSegura.setString(1, cliente.getNIT());
            declaracionSegura.setString(2, cliente.getDPI());
            declaracionSegura.setString(3, cliente.getNombre());
            declaracionSegura.setString(4, cliente.getApellido());
            declaracionSegura.setString(5, cliente.getDireccion());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Cliente " + cliente.getNombre()+ " registrada exitosamente";
    }
    
    public String modificarCliente(Cliente cliente){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("UPDATE Cliente SET NIT = ?, Nombre = ?, Apellido = ?, Direccion = ? WHERE DPI = '"+cliente.getDPI()+"';");
            declaracionSegura.setString(1, cliente.getNIT());
            declaracionSegura.setString(2, cliente.getNombre());
            declaracionSegura.setString(3, cliente.getApellido());
            declaracionSegura.setString(4, cliente.getDireccion());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Cliente " + cliente.getNombre()+ " modificado exitosamente";
    }
    
    public boolean buscarClientePorNIT(String NIT){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("SELECT COUNT(*) FROM Cliente WHERE  NIT = ?;");
            declaracionSegura.setString(1, NIT);
            resultado = declaracionSegura.executeQuery();
            while(resultado.next()){
                return resultado.getInt("COUNT(*)") == 1;
            }
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
        return false;
    }
    
     public boolean buscarClientePorDPI(String DPI){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("SELECT COUNT(*) FROM Cliente WHERE  DPI = ?;");
            declaracionSegura.setString(1, DPI);
            resultado = declaracionSegura.executeQuery();
            while(resultado.next()){
                return resultado.getInt("COUNT(*)") == 1;
            }
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
        return false;
    }
    
    public Cliente obtenerClientePorNIT(String NIT){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("SELECT* FROM Cliente WHERE  NIT = ?;");
            declaracionSegura.setString(1, NIT);
            resultado = declaracionSegura.executeQuery();
            while(resultado.next()){
                cliente = new Cliente(resultado.getString("Nombre"), resultado.getString("Apellido") , resultado.getString("Direccion"), resultado.getString("DPI"), resultado.getString("NIT"));
            }
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
        return cliente;
    }
    
    public Cliente obtenerClientePorDPI(String DPI){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("SELECT* FROM Cliente WHERE  DPI = ?;");
            declaracionSegura.setString(1, DPI);
            resultado = declaracionSegura.executeQuery();
            while(resultado.next()){
                cliente = new Cliente(resultado.getString("Nombre"), resultado.getString("Apellido") , resultado.getString("Direccion"), resultado.getString("DPI"), resultado.getString("NIT"));
            }
        }    
        catch (SQLException ex) {
            System.out.println("Error");
        }
        return cliente;
    }
    
    //-------------------------------------------------------------PAQUETE----------------------------------------------------------------------------------------------------------
     public String crearNuevoPaquete(Paquete paquete){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO Paquete (Codigo, Peso, Prioridad, Destino, PrecioDestino, PrecioPriorizacion, PrecioLibra, PrecioTotal, DPICliente, FechaIngreso) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            declaracionSegura.setString(1, paquete.getCodigo());
            declaracionSegura.setDouble(2, paquete.getPeso());
            declaracionSegura.setBoolean(3, paquete.isPrioridad());
            declaracionSegura.setString(4, paquete.getDestino());
            declaracionSegura.setDouble(5, paquete.getPrecioDestino());
            declaracionSegura.setDouble(6, paquete.getPrecioPriorizacion());
            declaracionSegura.setDouble(7, paquete.getPrecioLibra());
            declaracionSegura.setDouble(8, paquete.getPrecioTotal());
            declaracionSegura.setString(9, paquete.getDPI());
            declaracionSegura.setTimestamp(10, paquete.getFechaIngreso());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            return ex.toString();
        }
        return "Paquete " + paquete.getCodigo()+ " ingresado exitosamente";
    }
     
     //-------------------------------------------------------------BODEGA---------------------------------------------------------------------------------------------------------
      public void guardarPaqueteEnBodega(Paquete paquete){
        try {
            this.conectarDB();
            declaracionSegura = conexion.prepareStatement("INSERT INTO Bodega (CodigoPaquete, Prioridad, Destino, FechaIngreso) VALUES(?, ?, ?, ?);");
            declaracionSegura.setString(1, paquete.getCodigo());
            declaracionSegura.setBoolean(2, paquete.isPrioridad());
            declaracionSegura.setString(3, paquete.getDestino());
            declaracionSegura.setTimestamp(4, paquete.getFechaIngreso());
            declaracionSegura.executeUpdate();
        }    
        catch (SQLException ex) {
            System.out.println(ex); 
        }
    }
}



