package Backend;
import javax.swing.JPanel;

public class CambiadorPaneles {
    
    private JPanel panelActual;
    private JPanel panelNuevo;
    
    /*
    Metodo que recibe como parametro dos JPane, se encarga de intercambiar paneles
    sustituye el panelActual por el panelNuevo.
    */
    public void cambiarPanel(JPanel panelActual, JPanel panelNuevo){
        this.panelActual = panelActual;
        this.panelNuevo = panelNuevo;
        panelActual.removeAll();
        panelActual.revalidate();
        panelActual.repaint();
        panelActual.add(panelNuevo);
        panelActual.revalidate();
        panelActual.repaint();
    }
    
}
