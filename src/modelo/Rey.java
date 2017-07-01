package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author SusVa
 */
public class Rey extends Pieza {
    
    public Rey(Point p, boolean esBlanco) {
        super(p, esBlanco);
        valor = PESO_REY;
    }
    
    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/brey2.png");
        } else {
            imagen = new ImageIcon("src/icons/nrey2.png");
        }
    }
    
    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Casilla[][] casillas) {
        return new ArrayList<>();
    }
}
