package modelo;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author SusVa
 */
public class Peon extends Pieza {

    public Peon(Point p, String id, boolean esBlanco) {
        super(p, id, esBlanco);
        valor = PESO_PEON;
    }

    public void anadirColor(boolean esBlanco) {
        if (esBlanco) {
            imagen = new ImageIcon("src/icons/bpeon.png");
        } else {
            imagen = new ImageIcon("src/icons/npeon.png");
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

        ArrayList<Point> posiblesMovimientos = new ArrayList<>();
        Point posicionCandidata, posicionCandidataX2,
                posicionCandidataIzq, posicionCandidataDer;
        int posInicial;
        int posFinalTablero;
        ArrayList<Pieza> copiaPiezas = piezas;
        copiaPiezas.remove(this);

        if (esBlanca) {
            posFinalTablero = 0;
            posicionCandidata = new Point(this.getPoint().x - 1, this.getPoint().y);
            posInicial = 6;
            posicionCandidataX2 = new Point(this.getPoint().x - 2, this.getPoint().y);
            posicionCandidataIzq = new Point(this.getPoint().x - 1, this.getPoint().y + 1);
            posicionCandidataDer = new Point(this.getPoint().x - 1, this.getPoint().y - 1);
        } else {
            posFinalTablero = 7;
            posicionCandidata = new Point(this.getPoint().x + 1, this.getPoint().y);
            posInicial = 1;
            posicionCandidataX2 = new Point(this.getPoint().x + 2, this.getPoint().y);
            posicionCandidataIzq = new Point(this.getPoint().x + 1, this.getPoint().y + 1);
            posicionCandidataDer = new Point(this.getPoint().x + 1, this.getPoint().y - 1);
        }

        if (posFinalTablero == this.getPoint().x) {
            posiblesMovimientos.add(new Point(-1, -1));
            // Transformar pieza en otra ficha. TODO.
            return posiblesMovimientos;
        }

        zancadaSimple(copiaPiezas, posiblesMovimientos, posicionCandidata);
        doblezancada(copiaPiezas, posiblesMovimientos, posicionCandidataX2,
                posInicial);
        fichaContrariaComestible(copiaPiezas, posiblesMovimientos,
                posicionCandidataIzq, posicionCandidataDer);

        return posiblesMovimientos;
    }

    private void zancadaSimple(ArrayList<Pieza> piezas,
            ArrayList<Point> posiblesMovimientos, Point posicionCandidata) {

        posiblesMovimientos.add(posicionCandidata);

        for (Pieza pieza : piezas) {
            if (posicionCandidata.equals(pieza.getPoint())) {
                posiblesMovimientos.remove(posicionCandidata);
                break;
            }
        }
    }

    private void doblezancada(ArrayList<Pieza> piezas,
            ArrayList<Point> posiblesMovimientos,
            Point posicionCandidata, int posInicial) {

        if (this.getPoint().x == posInicial) {
            for (Pieza pieza : piezas) {
                if (!posicionCandidata.equals(pieza.getPoint())) {
                    posiblesMovimientos.add(posicionCandidata);
                }
            }
        }
    }

    private void fichaContrariaComestible(ArrayList<Pieza> piezas,
            ArrayList<Point> posiblesMovimientos,
            Point posicionCandidataIzq, Point posicionCandidataDer) {
        comerPiezaIzquierda(piezas, posiblesMovimientos, posicionCandidataIzq);
        comerPiezaDerecha(piezas, posiblesMovimientos, posicionCandidataDer);
    }

    private void comerPiezaIzquierda(ArrayList<Pieza> piezas,
                                     ArrayList<Point> posiblesMovimientos,
                                     Point posicionCandidataIzq) {
        for (Pieza pie : piezas) {
            if (pie.esBlanca != this.esBlanca) {
                if (pie.getPoint().equals(posicionCandidataIzq)) {
                    posiblesMovimientos.add(posicionCandidataIzq);
                    break;
                }
            }
        }
    }

    private void comerPiezaDerecha(ArrayList<Pieza> piezas,
                                     ArrayList<Point> posiblesMovimientos,
                                     Point posicionCandidataDer) {
        for (Pieza pie : piezas) {
            if (pie.esBlanca != this.esBlanca) {
                if (pie.getPoint().equals(posicionCandidataDer)) {
                    posiblesMovimientos.add(posicionCandidataDer);
                    break;
                }
            }
        }
    }

}
