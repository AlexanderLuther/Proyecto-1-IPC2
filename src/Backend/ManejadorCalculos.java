package Backend;
/**
 *
 * @author helmuthluther
 */
public class ManejadorCalculos {
    private int horas;
    private int minutos;
    private double minutosTotales;
    private double horasTotales;
    private String tiempoDesglosado[];
    private double costo;
    private double precio;
    private double ganancia;
    
    /*
    Metodo encargado de calcular el precio total que tiene un paquete. Recibe como parametro el peso, el precio de libra, la cuota de destino,
    la cuota de priorizacion y si se debe o cobrar la  priorizacion.
    */
    public double calcularPrecioPaquete(double peso, double precioLibra, double cuotaDestino, double cuotaPriorizacion, boolean priorizacion){
        if(priorizacion){
            precio =((peso * precioLibra) + cuotaDestino + cuotaPriorizacion);
            return precio;
        }
        else{
            precio = ((peso * precioLibra) + cuotaDestino); 
            return precio;
        }
    }
    
    /*
    Metodo encargado de calcular el tiempo en horas que tardo un paquete en un punto de control, posteriormente lo multiplica por la tarifa
    de operacion y devuelve el costo total del paquete para la empresa.
    */
    public double calcularCostoPaqueteEnPuntoDeControl(String tiempo, double tarifaOperacion){
        tiempoDesglosado = tiempo.split(":");
        horas = Integer.parseInt(tiempoDesglosado[0]);
        minutos = Integer.parseInt(tiempoDesglosado[1]);
        minutosTotales = ((horas*60) + minutos);
        horasTotales = minutosTotales/60;
        costo = horasTotales * tarifaOperacion;
        return costo;
    }
    
    /*
    Metodo encargado de regresar el valor almacenado en la variable horasTotales.
    */
    public double getHorasTotales(){
        return this.horasTotales;
    }
    
    /*
    Metodo encargado de devolver la ganancia total. Recibe como parametros el costo y los ingresos.
    */
    public double obtenerGanancia(double costo, double ingreso){
        ganancia = ingreso - costo;
        return ganancia;
    }   
}
