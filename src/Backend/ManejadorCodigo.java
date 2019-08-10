package Backend;
import java.util.Formatter;
/**
 *
 * @author helmuthluther
 */
public class ManejadorCodigo {
   
    private Formatter numeroConFormato;
    private String[] codigoDesglosado = new String[2];
    private String parteLiteral;
    private String parteNumerica;
    private int contadorNumerico;
    private String nuevoCodigo;
    private boolean modificarParteLiteral = false;
    private char primeraLetra;
    private char segundaLetra;
    private char terceraLetra;

    /*
    Metodo encargado de la obtencion de un nuevo codigo. Recibe como parametro el codigo actual, y devuelve
    un String conteniendo el valor del nuevoCodigo.
    */
    public String obtenerNuevoCodigo(String codigo){
        this.desglosarCodigo(codigo);
        this.aumentarContadorNumerico();
        this.crearNuevaParteNumerica();
        if(modificarParteLiteral){
            this.modificarParteLiteral();
            this.crearNuevaParteLiteral();
        }
        this.unirNuevoCodigo();
        return nuevoCodigo;
    }
    
    /*
    Metodo encargado de partir en dos el codigo recibido como parametro. Parte el String a partir del simbolo
    "-" y asigna los valores resultantes en la variables de tipo String parteLiteral y parteNumerica.
    */
    private void desglosarCodigo(String codigo){
        codigoDesglosado = codigo.split("-");
        parteLiteral = codigoDesglosado[0];
        parteNumerica = codigoDesglosado[1];
    }
    
    /*
    Metodo encarado de obtener el valor numerico de la parteNumerica y almacenarlo en la variable contadorNumerico.
    Posteriormente se suma uno al contador y se valida que no sea igual a 1000, de lo contrario se establece el valor 
    del contador en 0 y se establece en true el valor de la variable modificarParteLiteral.
    */
    private void aumentarContadorNumerico(){
        numeroConFormato = new Formatter();
        contadorNumerico = Integer.parseInt(String.valueOf(numeroConFormato.format("%d", Integer.parseInt(parteNumerica))));
        contadorNumerico++;
        if(contadorNumerico == 1000){
            contadorNumerico = 0;
            modificarParteLiteral = true;
        }
    }
    
    /*
    Metodo encargado de obtener cada una de las letras que componen la variable parteLiteral.
    Realiza comparaciones y modifica el valor de la letra correspondiente incrementando en 
    uno el valor ascii del caracter.
    */
    private void modificarParteLiteral(){       
        primeraLetra = parteLiteral.charAt(0);
        segundaLetra = parteLiteral.charAt(1);
        terceraLetra = parteLiteral.charAt(2);   
        if(primeraLetra != 'Z'){ 
            primeraLetra++;
        }
        else{
            if(segundaLetra != 'Z'){ 
                segundaLetra++;
            }
            else{
                if(terceraLetra != 'Z'){ 
                    terceraLetra++;
                }
                else{
                    System.out.println("Se llego al limite de codigos disponibles");
                }
            }
        }
        modificarParteLiteral = false;
    }
    
    /*
    Metodo encargado de establecer un formato sobre el numero almacenado en la variable contadorNumerico.
    */
    private void crearNuevaParteNumerica(){
       numeroConFormato = new Formatter(); 
       parteNumerica = String.valueOf(numeroConFormato.format("%03d",contadorNumerico));    
    }
    
    /*
    Metodo encargado de concatenar tres caracteres y establecer el nuevo valor de la parteLiteral
    */
    private void crearNuevaParteLiteral(){
        parteLiteral = String.valueOf(primeraLetra) + String.valueOf(segundaLetra) + String.valueOf(terceraLetra);
    }
    
    /*
    Metodo encargado de concatenar las variables de tipo String parteLiteral y parteNumerica y asigna el 
    valor resultante en la variable nuevoCodigo
    */
    private void unirNuevoCodigo(){
        nuevoCodigo = parteLiteral + "-" +parteNumerica;
    }
}
