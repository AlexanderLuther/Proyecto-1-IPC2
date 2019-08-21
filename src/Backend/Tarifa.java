package Backend;
/**
 *
 * @author helmuthluther
 */
public class Tarifa {
    
    private double tarifaOperacionGlobal;
    private double precioLibraGlobal;
    private double cuotaPriorizacionGlobal;
    private double cuotaDestinoGlobal;

    public Tarifa(double tarifaOperacionGlobal, double precioLibraGlobal, double cuotaPriorizacionGlobal, double cuotaDestinoGlobal) {
        this.tarifaOperacionGlobal = tarifaOperacionGlobal;
        this.precioLibraGlobal = precioLibraGlobal;
        this.cuotaPriorizacionGlobal = cuotaPriorizacionGlobal;
        this.cuotaDestinoGlobal = cuotaDestinoGlobal;
    }

    public double getTarifaOperacionGlobal() {
        return tarifaOperacionGlobal;
    }

    public void setTarifaOperacionGlobal(double tarifaOperacionGlobal) {
        this.tarifaOperacionGlobal = tarifaOperacionGlobal;
    }

    public double getPrecioLibraGlobal() {
        return precioLibraGlobal;
    }

    public void setPrecioLibraGlobal(double precioLibraGlobal) {
        this.precioLibraGlobal = precioLibraGlobal;
    }

    public double getCuotaPriorizacionGlobal() {
        return cuotaPriorizacionGlobal;
    }

    public void setCuotaPriorizacionGlobal(double cuotaPriorizacionGlobal) {
        this.cuotaPriorizacionGlobal = cuotaPriorizacionGlobal;
    }

    public double getCuotaDestinoGlobal() {
        return cuotaDestinoGlobal;
    }

    public void setCuotaDestinoGlobal(double cuotaDestinoGlobal) {
        this.cuotaDestinoGlobal = cuotaDestinoGlobal;
    }
    
    
}
