package Backend;
import javax.swing.JLabel;
/**
 *
 * @author helmuthluther
 */
public class ManejadorHilos {
    
    /*
    Metodo encargado de dar una pausa de 2.5 segundos y posteriormente limpiar el contenido de la etiqueta
    que recibe como parametro.
    */
    public void limpiarEtiquetaAlerta(JLabel etiquetaAlerta){
        Thread hilo = new Thread(){
        @Override 
        public  void run(){
            try {
                Thread.sleep(2500);
                etiquetaAlerta.setText("");
            }    
            catch (Exception e) {
                System.out.println(e);
            }
        }};
        hilo.start();
    }

    
}
