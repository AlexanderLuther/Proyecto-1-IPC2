package Backend;
/**
 *
 * @author helmuthluther
 */
public class Ruta {
    
    private String codigo;
    private String nombre;
    private String destino;
    private boolean activa;

    public Ruta(String codigo, String nombre, String destino, boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.destino = destino;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    
}
