package Backend;
/**
 *
 * @author helmuthluther
 */
public class Ruta {
    
    private String codigo;
    private String nombre;
    private String destino;
    private double cuotaDestino;
    private boolean activa;

    public Ruta(String codigo, String nombre, String destino, double cuotaDestino, boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.destino = destino;
        this.cuotaDestino = cuotaDestino;
        this.activa = activa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getCuotaDestino() {
        return cuotaDestino;
    }

    public void setCuotaDestino(double cuotaDestino) {
        this.cuotaDestino = cuotaDestino;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    
}
