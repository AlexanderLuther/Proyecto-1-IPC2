package Backend;
import java.sql.Timestamp;
/**
 *
 * @author helmuthluther
 */
public class Paquete {
    
    private String codigo;
    private double peso;
    private boolean prioridad;
    private String destino;
    private double precioDestino;
    private double precioPriorizacion;
    private double precioLibra;
    private double precioTotal;
    private String DPI;
    private Timestamp fechaIngreso;

    public Paquete(String codigo, double peso, boolean prioridad, String destino, double precioDestino, double precioPriorizacion, double precioLibra, double precioTotal, String DPI, Timestamp fechaIngreso) {
        this.codigo = codigo;
        this.peso = peso;
        this.prioridad = prioridad;
        this.destino = destino;
        this.precioDestino = precioDestino;
        this.precioPriorizacion = precioPriorizacion;
        this.precioLibra = precioLibra;
        this.precioTotal = precioTotal;
        this.DPI = DPI;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecioDestino() {
        return precioDestino;
    }

    public void setPrecioDestino(double precioDestino) {
        this.precioDestino = precioDestino;
    }

    public double getPrecioPriorizacion() {
        return precioPriorizacion;
    }

    public void setPrecioPriorizacion(double precioPriorizacion) {
        this.precioPriorizacion = precioPriorizacion;
    }

    public double getPrecioLibra() {
        return precioLibra;
    }

    public void setPrecioLibra(double precioLibra) {
        this.precioLibra = precioLibra;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
   
}
