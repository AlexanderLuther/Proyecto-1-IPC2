package Backend;
/**
 *
 * @author helmuthluther
 */
public class Cliente {
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String DPI;
    private String NIT;

    public Cliente(String nombre, String apellido, String ciudad, String DPI, String NIT) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = ciudad;
        this.DPI = DPI;
        this.NIT = NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String ciudad) {
        this.direccion = ciudad;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
    
    
}
