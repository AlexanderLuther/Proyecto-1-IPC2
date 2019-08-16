package Backend;
/**
 *
 * @author helmuthluther
 */
public class PuntoDeControl {
    
    private int codigo;
    private String codigoRuta;
    private String nombre;
    private double tarifaOperacion;
    private int cantidadPaquetesCola;
    private String operadorAsignado;
    private boolean ultimoPuntoDeControl;
    private boolean tarifaOperacionPropia;

    public PuntoDeControl(int codigo, String codigoRuta, String nombre, double tarifaOperacion, int cantidadPaquetesCola, String operadorAsignado, boolean ultimoPuntoDeControl, boolean tarifaOperacionPropia) {
        this.codigo = codigo;
        this.codigoRuta = codigoRuta;
        this.nombre = nombre;
        this.tarifaOperacion = tarifaOperacion;
        this.cantidadPaquetesCola = cantidadPaquetesCola;
        this.operadorAsignado = operadorAsignado;
        this.ultimoPuntoDeControl = ultimoPuntoDeControl;
        this.tarifaOperacionPropia = tarifaOperacionPropia;
    }

    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTarifaOperacion() {
        return tarifaOperacion;
    }

    public void setTarifaOperacion(double tarifaOperacion) {
        this.tarifaOperacion = tarifaOperacion;
    }

    public int getCantidadPaquetesCola() {
        return cantidadPaquetesCola;
    }

    public void setCantidadPaquetesCola(int cantidadPaquetesCola) {
        this.cantidadPaquetesCola = cantidadPaquetesCola;
    }

    public String getOperadorAsignado() {
        return operadorAsignado;
    }

    public void setOperadorAsignado(String operadorAsignado) {
        this.operadorAsignado = operadorAsignado;
    }

    public boolean isUltimoPuntoDeControl() {
        return ultimoPuntoDeControl;
    }

    public void setUltimoPuntoDeControl(boolean ultimoPuntoDeControl) {
        this.ultimoPuntoDeControl = ultimoPuntoDeControl;
    }

    public boolean isTarifaOperacionPropia() {
        return tarifaOperacionPropia;
    }

    public void setTarifaOperacionPropia(boolean tarifaOperacionPropia) {
        this.tarifaOperacionPropia = tarifaOperacionPropia;
    }

    
}
