package Backend;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author helmuthluther
 */
public class ManejadorBusqueda {
    
    ManejadorDBSM manejadorDB = new ManejadorDBSM();
    List<Usuario> listadoUsuarios = new ArrayList<>();
    List<Usuario> listadoUsuariosFiltrado = new ArrayList<>();
    
    List<Ruta> listadoRutas = new ArrayList<>();
    List<Ruta> listadoRutasFiltrado = new ArrayList<>();
    
    //==========================================================================USUARIOS==========================================================================
    public void obtenerUsuarios(int tipo){
        listadoUsuariosFiltrado.clear();
        listadoUsuarios.clear();
        if(tipo == 0){
            listadoUsuarios = this.manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario;", 1);
        }
        else{
            listadoUsuarios = this.manejadorDB.obtenerListadoUsuarios("SELECT* FROM Usuario WHERE Activo = TRUE && Tipo = 'Operador';", 1);
        }    
    }
    
    public List busquedaUsuarioPorNombres(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
        for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getNombres().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
    
     public List busquedaUsuarioPorApellidos(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
        for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getApellidos().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
     
     public List busquedaPorNombreUsuario(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
       for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getNombreUsuario().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    } 
     
    public List busquedaUsuarioPorTipo(String patronBusqueda, int tipo){
        this.obtenerUsuarios(tipo);
       for(int i = 0; i < listadoUsuarios.size(); i++){
            if(listadoUsuarios.get(i).getTipo().startsWith(patronBusqueda)){
                listadoUsuariosFiltrado.add(listadoUsuarios.get(i));
            }
        }
        return listadoUsuariosFiltrado;
    }
    
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
      
    public void obtenerRutas(){
        listadoRutasFiltrado.clear();
        listadoRutas.clear();
        listadoRutas = this.manejadorDB.obtenerListadoRutas("SELECT* FROM Ruta;", 1);
    }
      
    public List busquedaRutaPorNombre(String patronBusqueda){
        this.obtenerRutas();
        for(int i = 0; i < listadoRutas.size(); i++){
            if(listadoRutas.get(i).getNombre().startsWith(patronBusqueda)){
                listadoRutasFiltrado.add(listadoRutas.get(i));
            }
        }
        return listadoRutasFiltrado;
    }  
    
    public List busquedaRutaPorDestino(String patronBusqueda){
        this.obtenerRutas();
        for(int i = 0; i < listadoRutas.size(); i++){
            if(listadoRutas.get(i).getDestino().startsWith(patronBusqueda)){
                listadoRutasFiltrado.add(listadoRutas.get(i));
            }
        }
        return listadoRutasFiltrado;
    } 
    
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
    
   
    
     
}
