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
abstract class Pieza {

    protected final int PESO_PEON = 1, PESO_CABALLO = 2, PESO_ALFIL = 3,
    PESO_TORRE = 4, PESO_REINA = 5, PESO_REY = 100;
    private Point p;
    protected ImageIcon imagen;
    protected boolean esBlanca;
    protected String id;
    protected int valor;
    
    public Pieza(Point p, String id, boolean esBlanca) {
        this.p = p;
        this.id = id;
        this.esBlanca = esBlanca;
    }
    
    public ImageIcon getImagen() {
        return imagen;
    }

    public String getId() {
        return id;
    }
    
    public void setPosicion(Point p) {
        this.p = p;
    }

    public Point getPoint() {
        return p;
    }

    public abstract ArrayList<Point> calcularMovimientosDisponibles(ArrayList<Casilla> casillas);

    public abstract void anadirColor(boolean esBlanca);
    
}
