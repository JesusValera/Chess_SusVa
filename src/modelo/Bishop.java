package modelo;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author SusVa
 */
public class Bishop extends Piece {

    public Bishop(Point p, boolean esBlanco) {
        super(p, esBlanco);
    }

    public void setShape(boolean esBlanco) {
        if (esBlanco) {
            setImage("src/icons/balfil.png");
        } else {
            setImage("src/icons/nalfil.png");
        }
    }

    @Override
    public ArrayList<Point> calcularMovimientosDisponibles(Square[][] squares) {

        ArrayList<Point> boxes = new ArrayList<>();

        getSqTopLeft(squares, boxes);
        getSqTopRight(squares, boxes);
        getSqBottomLeft(squares, boxes);
        getSqBottomRight(squares, boxes);

        return boxes;
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
            // TODO. falla en sig linea? NO VA BIEN
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