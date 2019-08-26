package Backend;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author helmuthluther
 */
public class ManejadorFechas {
    
    private Timestamp fecha;
    
    /*
    Metodo encargado de devolver la fecha actual.
    */
    public Timestamp obtenerFechaActual(){
        return fecha = new Timestamp(System.currentTimeMillis());
    }
    /*
    Metodo encargado e convertir una fecha con formato Date a una fecha con formato Timestamp
    */
    public Timestamp convertirFecha(Date fecha){
        this.fecha = new Timestamp(fecha.getTime());  
        return this.fecha; 
    }
}
