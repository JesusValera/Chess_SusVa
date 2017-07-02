package modelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author SusVa
 */
public class Queen extends Piece {
    
    public Queen(Point p, boolean esBlanco) {
        super(p, esBlanco);
    }
    
    public void setShape(boolean esBlanco) {
        if (esBlanco) {
            setImage("src/icons/breina.png");
        } else {
            setImage("src/icons/nreina.png");
        }
    }
    
    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Square[][] squares) {

        ArrayList<Point> boxes = new ArrayList<>();
        Square[] ejeVertical = obtenerCasillasEjeVertical(squares, getPosition().y);
        obtenerMovimientosEjeX(squares[getPosition().x], boxes);
        obtenerMovimientosEjeY(ejeVertical, boxes);

        getSqTopLeft(squares, boxes);
        getSqTopRight(squares, boxes);
        getSqBottomLeft(squares, boxes);
        getSqBottomRight(squares, boxes);

        return boxes;
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

    private void getSqTopLeft(Square[][] squares, ArrayList<Point> boxes) {
        int axisX = this.getPosition().x - 1;
        int axisY = this.getPosition().y - 1;
        boolean keepOn = true;

        if (!piezaEstaEnBorde(axisX, axisY, squares.length)) {
            return;
        }
        for (; axisX >= 0 && axisY >= 0 && keepOn; axisX--, axisY--) {
            keepOn = addSquare(boxes, squares[axisX][axisY], new Point(axisX, axisY));
        }
    }

    private void getSqTopRight(Square[][] squares, ArrayList<Point> boxes) {
        int axisX = this.getPosition().x - 1;
        int axisY = this.getPosition().y + 1;
        boolean keepOn = true;

        if (!piezaEstaEnBorde(axisX, axisY, squares.length)) {
            return;
        }
        for (; axisX >= 0 && axisY < squares.length && keepOn; axisX--, axisY++) {
            keepOn = addSquare(boxes, squares[axisX][axisY], new Point(axisX, axisY));

        }
    }

    private void getSqBottomLeft(Square[][] squares, ArrayList<Point> boxes) {
        int axisX = this.getPosition().x + 1;
        int axisY = this.getPosition().y - 1;
        boolean keepOn = true;

        if (!piezaEstaEnBorde(axisX, axisY, squares.length)) {
            return;
        }

        for (; axisX < squares.length && axisY >= 0 && keepOn; axisX++, axisY--) {
            keepOn = addSquare(boxes, squares[axisX][axisY], new Point(axisX, axisY));
        }
    }

    private void getSqBottomRight(Square[][] squares, ArrayList<Point> boxes) {
        int axisX = this.getPosition().x + 1;
        int axisY = this.getPosition().y + 1;
        boolean keepOn = true;

        if (!piezaEstaEnBorde(axisX, axisY, squares.length)) {
            return;
        }
        for (; axisX <= squares.length && axisY < squares.length && keepOn; axisX++, axisY++) {
            keepOn = addSquare(boxes, squares[axisX][axisY], new Point(axisX, axisY));
        }
    }

    private boolean piezaEstaEnBorde(int axisX, int axisY, int tamCasillas) {
        return (axisX > 0 && axisX < tamCasillas) && (axisY > 0 && axisY < tamCasillas);
    }

    private boolean addSquare(ArrayList<Point> boxes, Square square, Point point) {
        if (square.getPiece() == null) {
            boxes.add(point);
        } else {
            if (square.getPiece().isWhite() != this.isWhite()) {
                boxes.add(point);
            }
            return false;
        }

        return true;
    }
}
