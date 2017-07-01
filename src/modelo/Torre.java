package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;

/**
 * @author SusVa
 */
public class Torre extends Pieza {

    public Torre(Point p, boolean esBlanco) {
        super(p, esBlanco);
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
    public ArrayList<Point> calcularMovimientosDisponibles(Casilla[][] casillas) {
        ArrayList<Point> puntos = new ArrayList<>();
        Casilla[] ejeVertical = obtenerCasillasEjeVertical(casillas, getPoint().y);
        obtenerMovimientosEjeX(casillas[getPoint().x], puntos);
        obtenerMovimientosEjeY(ejeVertical, puntos);

        return puntos;
    }

    private Casilla[] obtenerCasillasEjeVertical(Casilla[][] casillas, int eje) {
        Casilla[] ejeVertical = new Casilla[8];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (eje == j) {
                    ejeVertical[i] = casillas[i][j];
                }
            }
        }

        return ejeVertical;
    }

    private void obtenerMovimientosEjeX(Casilla[] casillas, ArrayList<Point> puntos) {
        anadirMovimientoIzquierda(casillas, puntos);
        anadirMovimientoDerecha(casillas, puntos);
    }

    private void anadirMovimientoIzquierda(Casilla[] casillas, ArrayList<Point> puntos) {
        for (int i = getPoint().y - 1; i >= 0; i--) {
            if (casillas[i].getPieza() == null) {
                puntos.add(new Point(getPoint().x, i));
            } else {
                if (casillas[i].getPieza().esBlanca != this.esBlanca) {
                    puntos.add(new Point(getPoint().x, i));
                }
                break;
            }
        }
    }

    private void anadirMovimientoDerecha(Casilla[] casillas, ArrayList<Point> puntos) {
        for (int i = getPoint().y + 1; i < casillas.length; i++) {
            if (casillas[i].getPieza() == null) {
                puntos.add(new Point(getPoint().x, i));
            } else {
                if (casillas[i].getPieza().esBlanca != this.esBlanca) {
                    puntos.add(new Point(getPoint().x, i));
                }
                break;
            }
        }
    }

    private void obtenerMovimientosEjeY(Casilla[] casillas, ArrayList<Point> puntos) {
        anadirMovimientoArriba(casillas, puntos);
        anadirMovimientoAbajo(casillas, puntos);
    }

    private void anadirMovimientoArriba(Casilla[] casillas, ArrayList<Point> puntos) {
        for (int i = getPoint().x - 1; i >= 0; i--) {
            if (casillas[i].getPieza() == null) {
                puntos.add(new Point(i, getPoint().y));
            } else {
                if (casillas[i].getPieza().esBlanca != this.esBlanca) {
                    puntos.add(new Point(i, getPoint().y));
                }
                break;
            }
        }
    }

    private void anadirMovimientoAbajo(Casilla[] casillas, ArrayList<Point> puntos) {
        for (int i = getPoint().x + 1; i < casillas.length; i++) {
            if (casillas[i].getPieza() == null) {
                puntos.add(new Point(i, getPoint().y));
            } else {
                if (casillas[i].getPieza().esBlanca != this.esBlanca) {
                    puntos.add(new Point(i, getPoint().y));
                }
                break;
            }
        }
    }

}
