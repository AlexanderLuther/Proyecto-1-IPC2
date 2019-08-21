package Backend;
/**
 *
 * @author helmuthluther
 */
public class ManejadorCalculos {
    
    public double calcularPrecioPaquete(double peso, double precioLibra, double cuotaDestino, double cuotaPriorizacion, boolean priorizacion){
        if(priorizacion){
            return ((peso * precioLibra) + cuotaDestino + cuotaPriorizacion);
        }
        else{
            return ((peso * precioLibra) + cuotaDestino);
        }
    }
}
