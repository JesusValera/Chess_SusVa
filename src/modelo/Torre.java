package modelo;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * @author SusVa
 */
public class Torre extends Pieza {

    public Torre(Point p, String id, boolean esBlanco) {
        super(p, id, esBlanco);
        valor = PESO_TORRE;
    }

    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/btorre.png");
        } else {
            imagen = new ImageIcon("src/icons/ntorre.png");
        }
    }

    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(ArrayList<Casilla> casillas) {
        ArrayList<Point> puntos = new ArrayList<>();
        obtenerPuntosEjeX(casillas, puntos);
        obtenerPuntosEjeY(casillas, puntos);
        //eliminarPuntoPropio(puntos);

        return puntos;
    }

    private void obtenerPuntosEjeY(ArrayList<Casilla> casillas, ArrayList<Point> puntos) {
        for (int i = getPoint().y; i < 8; i++) {

            for (Casilla casilla: casillas) {
                if (casilla.getPieza() != null) {
                    if (casilla.getPieza().esBlanca != this.esBlanca) {
                        puntos.add(new Point(i, this.getPoint().y));
                    }
                    break;
                } else {
                    puntos.add(new Point(i, this.getPoint().y));
                }
            }
        }

        for (int i = getPoint().y; i <= 0; i--) {

            for (Casilla casilla: casillas) {
                if (casilla.getPieza() != null) {
                    if (casilla.getPieza().esBlanca != this.esBlanca) {
                        puntos.add(new Point(i, this.getPoint().y));
                    }
                    break;
                } else {
                    puntos.add(new Point(i, this.getPoint().y));
                }
            }
        }
    }

    private void obtenerPuntosEjeX(ArrayList<Casilla> casillas, ArrayList<Point> puntos) {
        for (int i = 0; i < 8; i++) {
            puntos.add(new Point(this.getPoint().x, i));
        }



    }

    private void eliminarPuntoPropio(ArrayList<Point> puntos) {
        puntos.remove(this.getPoint());
    }
}
