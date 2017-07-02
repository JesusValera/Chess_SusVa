package modelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author SusVa
 */
public class Rook extends Piece {

    public Rook(Point p, boolean esBlanco) {
        super(p, esBlanco);
    }

    public void setShape(boolean esBlanco) {
        if (esBlanco) {
            setImage("src/icons/btorre.png");
        } else {
            setImage("src/icons/ntorre.png");
        }
    }

    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Square[][] squares) {
        ArrayList<Point> puntos = new ArrayList<>();
        Square[] ejeVertical = obtenerCasillasEjeVertical(squares, getPosition().y);
        obtenerMovimientosEjeX(squares[getPosition().x], puntos);
        obtenerMovimientosEjeY(ejeVertical, puntos);

        return puntos;
    }

    private Square[] obtenerCasillasEjeVertical(Square[][] squares, int eje) {
        Square[] ejeVertical = new Square[8];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                if (eje == j) {
                    ejeVertical[i] = squares[i][j];
                }
            }
        }

        return ejeVertical;
    }

    private void obtenerMovimientosEjeX(Square[] squares, ArrayList<Point> puntos) {
        anadirMovimientoIzquierda(squares, puntos);
        anadirMovimientoDerecha(squares, puntos);
    }

    private void anadirMovimientoIzquierda(Square[] squares, ArrayList<Point> puntos) {
        for (int i = getPosition().y - 1; i >= 0; i--) {
            if (squares[i].getPiece() == null) {
                puntos.add(new Point(getPosition().x, i));
            } else {
                if (squares[i].getPiece().isWhite() != this.isWhite()) {
                    puntos.add(new Point(getPosition().x, i));
                }
                break;
            }
        }
    }

    private void anadirMovimientoDerecha(Square[] squares, ArrayList<Point> puntos) {
        for (int i = getPosition().y + 1; i < squares.length; i++) {
            if (squares[i].getPiece() == null) {
                puntos.add(new Point(getPosition().x, i));
            } else {
                if (squares[i].getPiece().isWhite() != this.isWhite()) {
                    puntos.add(new Point(getPosition().x, i));
                }
                break;
            }
        }
    }

    private void obtenerMovimientosEjeY(Square[] squares, ArrayList<Point> puntos) {
        anadirMovimientoArriba(squares, puntos);
        anadirMovimientoAbajo(squares, puntos);
    }

    private void anadirMovimientoArriba(Square[] squares, ArrayList<Point> puntos) {
        for (int i = getPosition().x - 1; i >= 0; i--) {
            if (squares[i].getPiece() == null) {
                puntos.add(new Point(i, getPosition().y));
            } else {
                if (squares[i].getPiece().isWhite() != this.isWhite()) {
                    puntos.add(new Point(i, getPosition().y));
                }
                break;
            }
        }
    }

    private void anadirMovimientoAbajo(Square[] squares, ArrayList<Point> puntos) {
        for (int i = getPosition().x + 1; i < squares.length; i++) {
            if (squares[i].getPiece() == null) {
                puntos.add(new Point(i, getPosition().y));
            } else {
                if (squares[i].getPiece().isWhite() != this.isWhite()) {
                    puntos.add(new Point(i, getPosition().y));
                }
                break;
            }
        }
    }

}
