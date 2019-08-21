package Backend;

import java.sql.Timestamp;

/**
 *
 * @author helmuthluther
 */
public class ManejadorFechas {
    
    private Timestamp fecha;
    
    public Timestamp obtenerFechaActual(){
        return fecha = new Timestamp(System.currentTimeMillis());
    }
    
}
