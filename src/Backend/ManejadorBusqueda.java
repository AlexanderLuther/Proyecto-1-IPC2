package Backend;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author helmuthluther
 */
public class ManejadorBusqueda {
    
    //Instancias y variables de la clase.
    ManejadorDBSM manejadorDB = new ManejadorDBSM();
    List<Usuario> listadoUsuarios = new ArrayList<>();
    List<Usuario> listadoUsuariosFiltrado = new ArrayList<>();
    List<Ruta> listadoRutas = new ArrayList<>();
    List<Ruta> listadoRutasFiltrado = new ArrayList<>();
    List<Cliente> listadoClientes = new ArrayList<>();
    List<Cliente> listadoClientesFiltrado = new ArrayList<>();
    List<PuntoDeControl> listadoPuntosDeControl = new ArrayList<>();
    List<PuntoDeControl> listadoPuntosDeControlFiltrado = new ArrayList<>();
    List<Paquete> listadoPaquetes = new ArrayList<>();
    List<Paquete> listadoPaquetesFiltrado = new ArrayList<>();
    
    //==========================================================================USUARIOS==========================================================================
   
    /*
    Metodo encargado de obtener el listado de usuarios donde se realizara la busqueda. Recibe como parametro un entero que indica el 
    tipo de consulta a realizar en la base de datos.
    */
    public void obtenerUsuarios(int tipo){
        listadoUsuariosFiltrado.clear();
        listadoUsuarios.clear();
        if(tipo == 0){
            listadoUsuarios = this.manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario;");
        }
        else{
            listadoUsuarios = this.manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario WHERE Activo = TRUE && Tipo = 'Operador';");
        }    
    }
    
    /*
    Metodo encargado de realizar una busqueda de usuarios segun el primer nombre del usuario.
    */
    public List busquedaUsuarioPorNombres(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
        for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getNombres().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de usuarios segun el primer apellido del usuario.
    */
     public List busquedaUsuarioPorApellidos(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
        for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getApellidos().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
     
    /*
    Metodo encargado de realizar una busqueda de usuarios segun el nombre de usuario del usuario.
    */ 
    public List busquedaPorNombreUsuario(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
       for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getNombreUsuario().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    } 
     
    /*
    Metodo encargado de realizar una busqueda de usuarios segun el tipo de usuario del usuario.
    */
    public List busquedaUsuarioPorTipo(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
       for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getTipo().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
    /*
    Metodo encargado de realizar una busqueda de usuarios segun el estado del usuario.
    */
    public List busquedaUsuarioPorEstado(String patronBusqueda, int tipo){
       this.obtenerUsuarios(tipo);
        if(patronBusqueda.equals("Activado")){
           for(int i = 0; i < listadoUsuarios.size(); i++){
                if(listadoUsuarios.get(i).isActivo()){
                    listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
                }
            }    
       }
       if(patronBusqueda.equals("Desactivado")){
           for(int i = 0; i < listadoUsuarios.size(); i++){
                if(!listadoUsuarios.get(i).isActivo()){
                    listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
                }
            } 
        }
        return listadoUsuariosFiltrado;
    }
    
    
    //==========================================================================RUTAS==========================================================================
      
    /*
    Metodo encargado de obtener el listado de rutas donde se realizara la busqueda.
    */
    public void obtenerRutas(){
        listadoRutasFiltrado.clear();
        listadoRutas.clear();
        listadoRutas = this.manejadorDB.obtenerListadoRutas("SELECT* FROM Ruta;");
    }
    
    /*
    Metodo encargado de obtener el listado de rutas donde se realizara la busqueda.
    */
    public void obtenerRutasSinDuplicados(){
        listadoRutasFiltrado.clear();
        listadoRutas.clear();
        listadoRutas = this.manejadorDB.obtenerListadoRutas("SELECT DISTINCT Destino FROM Ruta WHERE Activa = TRUE;");
    }
    
    /*
    Metodo encargado de realizar una busqueda de rutas segun el nombre de la ruta.
    */
    public List busquedaRutaPorNombre(String patronBusqueda){
        this.obtenerRutas();
        for(int i = 0; i < listadoRutas.size(); i++){
            if(listadoRutas.get(i).getNombre().startsWith(patronBusqueda)){
                listadoRutasFiltrado.add(listadoRutas.get(i));
            }
        }
        return listadoRutasFiltrado;
    }  
    
    /*
    Metodo encargado de realizar una busqueda de rutas segun el destino de la ruta.
    */
    public List busquedaRutaPorDestino(String patronBusqueda, int tipo){
        if(tipo == 0){
           this.obtenerRutas(); 
        }
        else{
            this.obtenerRutasSinDuplicados();
        }
        for(int i = 0; i < listadoRutas.size(); i++){
            if(listadoRutas.get(i).getDestino().startsWith(patronBusqueda)){
                listadoRutasFiltrado.add(listadoRutas.get(i));
            }
        }
        return listadoRutasFiltrado;
    } 
    
    /*
    Metodo encargado de realizar una busqueda de rutas segun el estado de la ruta.
    */
    public List busquedaRutaPorEstado(String patronBusqueda){
       this.obtenerRutas();
        if(patronBusqueda.equals("Activada")){
           for(int i = 0; i < listadoRutas.size(); i++){
                if(listadoRutas.get(i).isActiva()){
                    listadoRutasFiltrado.add(listadoRutas.get(i));
                }
            }    
       }
       if(patronBusqueda.equals("Desactivada")){
           for(int i = 0; i < listadoRutas.size(); i++){
                if(!listadoRutas.get(i).isActiva()){
                    listadoRutasFiltrado.add(listadoRutas.get(i));
                }
            } 
        }
        return listadoRutasFiltrado;
    }
    
    //==========================================================================CLIENTES==========================================================================
    
    /*
    Metodo encargado de obtener el listado de clientes donde se realizara la busqueda.
    */
    public void obtenerClientes(){
        listadoClientesFiltrado.clear();
        listadoClientes.clear();
        listadoClientes = this.manejadorDB.obtenerListadoClientes("SELECT* FROM Cliente;");    
    }
    
    /*
    Metodo encargado de realizar una busqueda de clientes segun el primer nombre del cliente.
    */
    public List busquedaClientePorNombres(String patronBusqueda){
        this.obtenerClientes();
        for(int i = 0; i < listadoClientes.size(); i++){
            if(listadoClientes.get(i).getNombre().startsWith(patronBusqueda)){
                listadoClientesFiltrado.add(listadoClientes.get(i));
            }
        }
        return listadoClientesFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de clientes segun el primer apellido del cliente.
    */
    public List busquedaClientePorApellidos(String patronBusqueda){
        this.obtenerClientes();
        for(int i = 0; i < listadoClientes.size(); i++){
            if(listadoClientes.get(i).getApellido().startsWith(patronBusqueda)){
                listadoClientesFiltrado.add(listadoClientes.get(i));
            }
        }
        return listadoClientesFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de clientes segun  la direccion del cliente.
    */
    public List busquedaClientePorDireccion(String patronBusqueda){
        this.obtenerClientes();
        for(int i = 0; i < listadoClientes.size(); i++){
            if(listadoClientes.get(i).getDireccion().startsWith(patronBusqueda)){
                listadoClientesFiltrado.add(listadoClientes.get(i));
            }
        }
        return listadoClientesFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de clientes segun el NIT del cliente.
    */
    public List busquedaClientePorNIT(String patronBusqueda){
        this.obtenerClientes();
        for(int i = 0; i < listadoClientes.size(); i++){
            if(listadoClientes.get(i).getNIT().startsWith(patronBusqueda)){
                listadoClientesFiltrado.add(listadoClientes.get(i));
            }
        }
        return listadoClientesFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de clientes segun el DPI del cliente.
    */
    public List busquedaClientePorDPI(String patronBusqueda){
        this.obtenerClientes();
        for(int i = 0; i < listadoClientes.size(); i++){
            if(listadoClientes.get(i).getDPI().startsWith(patronBusqueda)){
                listadoClientesFiltrado.add(listadoClientes.get(i));
            }
        }
        return listadoClientesFiltrado;
    }
    
    
    //==========================================================================PUNTOS DE CONTROL==========================================================================
    
    /*
    Metodo encargado de obtener el listado de puntos de control donde se realizara la busqueda.
    */
    public void obtenerPuntosDeControl(String nombreUsuario){
        listadoPuntosDeControlFiltrado.clear();
        listadoPuntosDeControl.clear();
        listadoPuntosDeControl = this.manejadorDB.obtenerListadoPuntosDeControl("SELECT* FROM PuntoDeControl WHERE OperadorAsignado = '"+nombreUsuario+"';");    
    }
    
    /*
    Metodo encargado de realizar una busqueda de puntos de control segun el nombre del punto de control.
    */
    public List busquedaPuntoDeControlPorNombre(String patronBusqueda, String nombreUsuario){
        this.obtenerPuntosDeControl(nombreUsuario);
        for(int i = 0; i < listadoPuntosDeControl.size(); i++){
            if(listadoPuntosDeControl.get(i).getNombre().startsWith(patronBusqueda)){
                listadoPuntosDeControlFiltrado.add(listadoPuntosDeControl.get(i));
            }
        }
        return listadoPuntosDeControlFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de puntos de control segun el codigo de ruta del punto de control.
    */
    public List busquedaPuntoDeControlPorCodigoRuta(String patronBusqueda, String nombreUsuario){
        this.obtenerPuntosDeControl(nombreUsuario);
        for(int i = 0; i < listadoPuntosDeControl.size(); i++){
            if(listadoPuntosDeControl.get(i).getCodigoRuta().startsWith(patronBusqueda)){
                listadoPuntosDeControlFiltrado.add(listadoPuntosDeControl.get(i));
            }
        }
        return listadoPuntosDeControlFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de puntos de control segun el codigo del punto de control.
    */
    public List busquedaPuntoDeControlPorCodigo(String patronBusqueda, String nombreUsuario){
        this.obtenerPuntosDeControl(nombreUsuario);
        for(int i = 0; i < listadoPuntosDeControl.size(); i++){
            if(String.valueOf(listadoPuntosDeControl.get(i).getCodigo()).startsWith(patronBusqueda)){
                listadoPuntosDeControlFiltrado.add(listadoPuntosDeControl.get(i));
            }
        }
        return listadoPuntosDeControlFiltrado;
    }
     
    
    //==========================================================================PAQUETES==========================================================================
    
     /*
    Metodo encargado de obtener el listado de paquetes donde se realizara la busqueda.
    */
    public void obtenerPaquetes(int tipo){
        listadoPaquetesFiltrado.clear();
        listadoPaquetes.clear();
        if(tipo == 0){
            listadoPaquetes = this.manejadorDB.obtenerListadoPaquetes("SELECT A.Codigo, A.Peso, A.Destino, A.DPICliente, A.PrecioTotal FROM Paquete A, PaqueteAsignadoRuta B WHERE B.EnDestino = TRUE && Entregado = FALSE && A.Codigo = B.CodigoPaquete;");    
        }
        else{
            listadoPaquetes = this.manejadorDB.obtenerListadoPaquetes("SELECT A.Codigo, A.Peso, A.Destino, A.PrecioTotal, A.DPICliente FROM Paquete A, PaqueteAsignadoRuta B WHERE B.EnDestino = FALSE && A.Codigo = B.CodigoPaquete");
        }
    }
      
    /*
    Metodo encargado de realizar una busqueda de paquetes segun el DPI del cliente del paquete.
    */
    public List busquedaPaquetePorDPI(String patronBusqueda, int tipo){
        this.obtenerPaquetes(tipo);
        for(int i = 0; i < listadoPaquetes.size(); i++){
            if(String.valueOf(listadoPaquetes.get(i).getDPI()).startsWith(patronBusqueda)){
                listadoPaquetesFiltrado.add(listadoPaquetes.get(i));
            }
        }
        return listadoPaquetesFiltrado;
    }  
    
    /*
    Metodo encargado de realizar una busqueda de paquetes segun el codigo del paquete.
    */
    public List busquedaPaquetePorCodigo(String patronBusqueda, int tipo){
        this.obtenerPaquetes(tipo);
        for(int i = 0; i < listadoPaquetes.size(); i++){
            if(String.valueOf(listadoPaquetes.get(i).getCodigo()).startsWith(patronBusqueda)){
                listadoPaquetesFiltrado.add(listadoPaquetes.get(i));
            }
        }
        return listadoPaquetesFiltrado;
    }
    
    /*
    Metodo encargado de realizar una busqueda de paquetes segun el destino del paquete.
    */
    public List busquedaPaquetePorDestino(String patronBusqueda, int tipo){ 
        this.obtenerPaquetes(tipo);       
        for(int i = 0; i < listadoPaquetes.size(); i++){
            if(String.valueOf(listadoPaquetes.get(i).getDestino()).startsWith(patronBusqueda)){
                listadoPaquetesFiltrado.add(listadoPaquetes.get(i));
            }
        }
        return listadoPaquetesFiltrado;
    } 

}
