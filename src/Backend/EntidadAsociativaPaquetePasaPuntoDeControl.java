package Backend;
import java.sql.Timestamp;

/**
 *
 * @author helmuthluther
 */
public class EntidadAsociativaPaquetePasaPuntoDeControl {
    
    private String codigoPaquete;
    private String codigoRuta;
    private int codigoPuntoDeControl;
    private double tarifaOperacion;
    private double cantidadHoras;
    private double costo;
    private boolean finalizado;
    private boolean enTurno;
    private Timestamp fechaIngreso;
    private boolean prioridad;

    public EntidadAsociativaPaquetePasaPuntoDeControl(String codigoPaquete, String codigoRuta, int codigoPuntoDeControl, double tarifaOperacion, double cantidadHoras, double costo, boolean finalizado, boolean enTurno, Timestamp fechaIngreso, boolean prioridad) {
        this.codigoPaquete = codigoPaquete;
        this.codigoRuta = codigoRuta;
        this.codigoPuntoDeControl = codigoPuntoDeControl;
        this.tarifaOperacion = tarifaOperacion;
        this.cantidadHoras = cantidadHoras;
        this.costo = costo;
        this.finalizado = finalizado;
        this.enTurno = enTurno;
        this.fechaIngreso = fechaIngreso;
        this.prioridad = prioridad;
    }

    public String getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(String codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public int getCodigoPuntoDeControl() {
        return codigoPuntoDeControl;
    }

    public void setCodigoPuntoDeControl(int codigoPuntoDeControl) {
        this.codigoPuntoDeControl = codigoPuntoDeControl;
    }

    public double getTarifaOperacion() {
        return tarifaOperacion;
    }

    public void setTarifaOperacion(double tarifaOperacion) {
        this.tarifaOperacion = tarifaOperacion;
    }

    public double getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(double cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public boolean isEnTurno() {
        return enTurno;
    }

    public void setEnTurno(boolean enTurno) {
        this.enTurno = enTurno;
    }

    public Timestamp isFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isPrioridad() {
        return prioridad;
    }

    public void setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
    }
    
    
    
    
}
