package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author SusVa
 */
public class Alfil extends Pieza {
    
    public Alfil(Point p, boolean esBlanco) {
        super(p, esBlanco);
        valor = PESO_ALFIL;
    }
    
    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/balfil.png");
        } else {
            imagen = new ImageIcon("src/icons/nalfil.png");
        }
    }
    
    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Casilla[][] casillas) {
        System.out.println("Alfil");
        return new ArrayList<>();
    }
}
