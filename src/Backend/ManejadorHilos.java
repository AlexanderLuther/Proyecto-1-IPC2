package Backend;
import javax.swing.JLabel;
/**
 *
 * @author helmuthluther
 */
public class ManejadorHilos {
    
    public void limpiarEtiquetaAlerta(JLabel etiquetaAlerta){
        Thread hilo = new Thread(){
        @Override 
        public  void run(){
            try {
                Thread.sleep(2000);
                etiquetaAlerta.setText("");
            }    
            catch (Exception e) {
                System.out.println(e);
            }
        }};
        hilo.start();
    }

    
}
