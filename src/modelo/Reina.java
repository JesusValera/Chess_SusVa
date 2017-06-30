package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author SusVa
 */
public class Reina extends Pieza {
    
    public Reina(Point p, String id, boolean esBlanco) {
        super(p, id, esBlanco);
        valor = PESO_REINA;
    }
    
    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/breina.png");
        } else {
            imagen = new ImageIcon("src/icons/nreina.png");
        }
    }
    
    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(ArrayList<Casilla> casillas) {
        System.out.println("Reina");
        return new ArrayList<>();
    }
}
