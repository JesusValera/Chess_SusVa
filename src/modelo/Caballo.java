package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author SusVa
 */
public class Caballo extends Pieza {
    
    public Caballo(Point p, String id, boolean esBlanco) {
        super(p, id, esBlanco);
        valor = PESO_CABALLO;
    }
    
    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/bcaballo.png");
        } else {
            imagen = new ImageIcon("src/icons/ncaballo.png");
        }
    }
    
    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(ArrayList<Casilla> casillas) {

        ArrayList<Pieza> piezas = new ArrayList<>();
        for (int i = 0; i < casillas.size(); i++) {
            if (casillas.get(i).getPieza() != null) {
                piezas.add(casillas.get(i).getPieza());
            }
        }

        ArrayList<Pieza> copiaPiezas = piezas;
        copiaPiezas.remove(this);

        ArrayList<Point> movimientosDisponibles = posiblesMovimientos();

        return movimientosCaballo(movimientosDisponibles, copiaPiezas);
    }

    private ArrayList<Point> posiblesMovimientos() {
        ArrayList<Point> posiblesMovimientos = new ArrayList<>();
        Point[] posicionesCandidatas = new Point[] {
                new Point(-2, -1), new Point(-2, 1),
                new Point(-1, -2), new Point(-1, 2),
                new Point(1, -2), new Point(1, 2),
                new Point(2, -1), new Point(2, 1)
        };

        for (int i = 0; i < posicionesCandidatas.length; i++) {
            int x = this.getPoint().x + posicionesCandidatas[i].x;
            int y = this.getPoint().y + posicionesCandidatas[i].y;
            if (discriminarFueraTablero(x, y)) {
                posiblesMovimientos.add(new Point(x, y));
            }
        }

        return posiblesMovimientos;
    }

    private boolean discriminarFueraTablero(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        return true;
    }

    private ArrayList<Point> movimientosCaballo(ArrayList<Point> movimientosDisponibles,
                                                ArrayList<Pieza> piezas) {
        ArrayList<Point> aux = new ArrayList<>();
        for (Point movs: movimientosDisponibles) {
            for (Pieza p: piezas) {
                if (p.esBlanca == this.esBlanca) {
                    if (movs.equals(p.getPoint())) {
                        aux.add(movs);
                    }
                }
            }
        }
        movimientosDisponibles.removeAll(aux);

        return movimientosDisponibles;
    }
}
