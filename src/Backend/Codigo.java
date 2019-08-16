package Backend;
/**
 *
 * @author helmuthluther
 */
public class Codigo {
    
    private String codigoPaquete;
    private String codigoRuta;

    public Codigo(String codigoPaquete, String codigoRuta) {
        this.codigoPaquete = codigoPaquete;
        this.codigoRuta = codigoRuta;
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
}
